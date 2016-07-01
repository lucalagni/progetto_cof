package client.controller.connections.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import client.controller.ControllerRepository;
import client.controller.connections.GameConnectionSetupController;
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
	
	private static ScheduledExecutorService scheduler;
	private static GameConnectionSetupController controller;
	
	public GameConnectionScheduler(){
		scheduler = Executors.newScheduledThreadPool(NUM_THREADS);
		ControllerRepository.getInstance().setGameConnectionSetupController();
		controller = ControllerRepository.getInstance().getGameConnectionSetupController();
	}
	
	/**
	 * Metodo che attiva lo scheduler per il polling
	 */
	public void activateAddMe(){
		Runnable addMeTask = new AddMeTask();
		ScheduledFuture<?> requireAddMe = scheduler.scheduleWithFixedDelay(addMeTask, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_DELAY, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_PERIOD, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIME_UNIT);
		Runnable stopAddMeTask = new StopAddMeTask(requireAddMe);
		
		scheduler.schedule(stopAddMeTask, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIMEOUT, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIME_UNIT);
		try {
			scheduler.awaitTermination(ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_AWAIT, ClientSchedulersConstants.CLIENT_SCHEDULER_CONNECTIONS_TIME_UNIT);
		} catch (InterruptedException e) {}
	}
	
	private static final class AddMeTask implements Runnable{

		@Override
		public void run() {
			System.out.println(controller.requireMatch());
		}
		
	}
	
	private static final class StopAddMeTask implements Runnable {
		private ScheduledFuture<?> futureScheduled ;
		
	    StopAddMeTask(ScheduledFuture<?> futureScheduled){ this.futureScheduled = futureScheduled; }
		
		@Override
		public void run() {
			System.out.println("\nStop require add me");
			futureScheduled.cancel(DONT_INTERRUPT_IF_RUNNING);
			scheduler.shutdown();
		}
		
	}
	
	@Override
	public void run(){
		activateAddMe();
		try {
			this.activateAddMe();
			sleep(1000);
		} catch (InterruptedException e) {}
	}
}
