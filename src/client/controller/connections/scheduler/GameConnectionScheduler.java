package client.controller.connections.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import client.controller.ControllerRepository;
import client.controller.connections.GameConnectionSetupController;
import commons.messages.ServerMessageContentType;
import commons.schedulers.client.ClientSchedulersConstants;

/**
 * Classe che esgue in polling delle richieste fino al verificarsi di una certa condizione
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionScheduler extends Thread{
	public static final int NUM_THREADS = 1;       					//Indica quanti thread far partire
	public static final boolean DONT_INTERRUPT_IF_RUNNING = false ; //Indica che il thread può essere bloccato anche se esso non ha concluso la sua esecuzione
	public static final int SLEEP_TIME = 100;
	
	private static ScheduledExecutorService scheduler;
	private static GameConnectionSetupController controller;
	
	private static boolean gamerAddedToTheQueuque;
	private static boolean usernameNotAvailable ;
	
	public GameConnectionScheduler(){
		scheduler = Executors.newScheduledThreadPool(NUM_THREADS);
		ControllerRepository.getInstance().setGameConnectionSetupController();
		controller = ControllerRepository.getInstance().getGameConnectionSetupController();
		gamerAddedToTheQueuque = false ;
		usernameNotAvailable = false ;
	}
	
	/**
	 * Metodo che attiva lo scheduler per il polling
	 */
	private void activateAddMe(){
		try{
		Runnable addMeTask = new AddMeTask();
		ScheduledFuture<?> requireAddMe = scheduler.scheduleWithFixedDelay(addMeTask, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_DELAY, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_PERIOD, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIME_UNIT);
		Runnable stopAddMeTask = new StopAddMeTask(requireAddMe);
		
		scheduler.schedule(stopAddMeTask, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIMEOUT, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIME_UNIT);
		try {
			scheduler.awaitTermination(ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_AWAIT, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIME_UNIT);
		} catch (InterruptedException e) {}
		
		}catch(Exception ex){}
	}
	
	/**
	 * Se il giocatore e' stato aggiunto alla coda fermo la richiesta dello scheduler di 
	 * aggiungere un giocatore
	 */
	private static void gamerAddedToTheQueue(){ 
		gamerAddedToTheQueuque = true; 
		usernameNotAvailable = false ;
		scheduler.shutdown();
	}
	
	/**
	 * Se il giocatore ha scelto uno username non disponibile , notifico la mancata ricezione
	 */
	private static void usernameNotAvailable(){
		usernameNotAvailable = true ;
		gamerAddedToTheQueuque = false ;
		scheduler.shutdown();
	}
	
	public boolean getGamerAddedToTheQueque(){ return gamerAddedToTheQueuque; }
	public boolean getUsernameNotAvailable(){ return usernameNotAvailable; }
	
	private static final class AddMeTask implements Runnable{

		@Override
		public void run() {
			String result = controller.clientRequireAddMe();
			
			if(result.equals(ServerMessageContentType.SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE.getServerMessageContentType())) gamerAddedToTheQueue();
			if(result.equals(ServerMessageContentType.SERVER_RESPONSE_USERNAME_NOT_AVAILABLE.getServerMessageContentType())) usernameNotAvailable();
			System.out.println(result);
		}
		
	}
	
	private static final class StopAddMeTask implements Runnable {
		private ScheduledFuture<?> futureScheduled ;
		
	    StopAddMeTask(ScheduledFuture<?> futureScheduled){ this.futureScheduled = futureScheduled; }
		
		@Override
		public void run() {
			futureScheduled.cancel(DONT_INTERRUPT_IF_RUNNING);
			scheduler.shutdown();
		}
		
	}
	
	@Override
	public void run(){
		try {
			this.activateAddMe();
			sleep(SLEEP_TIME);
		} catch (InterruptedException e) {}
	}
}
