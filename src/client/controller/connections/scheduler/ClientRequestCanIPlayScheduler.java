package client.controller.connections.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import commons.messages.ServerMessageContentType;
import commons.schedulers.client.ClientSchedulersConstants;
import client.controller.ControllerRepository;
import client.controller.connections.GameConnectionSetupController;

/**
 * Classe che implementa lo scheduler per la richiesta dello stato di gioc del giocatore
 * 
 * @author Luca Lagni
 *
 */
public class ClientRequestCanIPlayScheduler extends Thread{
	private static final int NUM_THREADS = 1;
	private static final boolean DONT_INTERRUPT_IF_RUNNING = false;
	private static final int SLEEP_TIME = 100 * 60;
	
	private static ScheduledExecutorService scheduler;
	private static GameConnectionSetupController controller;
	
	private static ScheduledFuture<?> scheduledTask;
	
	private static boolean serverResponseTooFeawGamersToPlay ;
	private static boolean serverResponseMatchNotAvailableYet ;
	private static boolean serverResponseGamerNotInMatch ;
	private static boolean serverResponseMatch;
	
	public ClientRequestCanIPlayScheduler(){
		scheduler = Executors.newScheduledThreadPool(NUM_THREADS);
		controller = ControllerRepository.getInstance().getGameConnectionSetupController();
		serverResponseGamerNotInMatch = false ;
		serverResponseMatch = false;
		serverResponseTooFeawGamersToPlay = false ;
		serverResponseMatchNotAvailableYet = false ;
	}
	
	public void requestCanIPlay(){
		try{
			Runnable startRequestTask = new StartRequestCanIPlay();
			scheduledTask = scheduler.scheduleWithFixedDelay(startRequestTask, 
															 ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_DELAY,
															 ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_PERIOD,
															 ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_TIME_UNIT);
			Runnable stopRequestTask = new StopRequestCanIPlay(scheduledTask);
			scheduler.scheduleWithFixedDelay(stopRequestTask, 
											 ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_DELAY,
											 ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_TIMEOUT,
											 ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_TIME_UNIT);
			
			try {
				scheduler.awaitTermination(ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_AWAIT, ClientSchedulersConstants.CLIENT_SCHEDULER_CAN_I_PLAY_TIME_UNIT);
			} catch (InterruptedException e) {}
								
		}catch(Exception ex){}
	}
	
	//Indica che non ci sono abbastanza giocatori per poter giocare
	private void setServerResponseTooFeawGamersToPlay(){
		serverResponseTooFeawGamersToPlay = true;
		serverResponseMatchNotAvailableYet = false ;
		serverResponseGamerNotInMatch = false ;
		serverResponseMatch = false;
		scheduledTask.cancel(true);
	}
	
	//Indica che non ci sono abbastanza giocatori per poter giocare
		private void setServerResponseMatch(){
			serverResponseTooFeawGamersToPlay = false;
			serverResponseMatchNotAvailableYet = false ;
			serverResponseGamerNotInMatch = false ;
			serverResponseMatch = true;
			scheduledTask.cancel(true);
		}
	
	//Indica che non ci sono abbastanza giocatori per poter giocare
		private void setServerResponseGamerNotInMatch(){
			serverResponseTooFeawGamersToPlay = false;
			serverResponseMatchNotAvailableYet = false ;
			serverResponseGamerNotInMatch = true ;
			serverResponseMatch = false;
			scheduledTask.cancel(true);
		}
	
	//Indica che non si può ancora dire nulla sull'eventuale match
	private void setServerResponseMatchNotAvailableYet(){
		serverResponseTooFeawGamersToPlay = false;
		serverResponseMatchNotAvailableYet = true ;
		serverResponseGamerNotInMatch = false ;
		serverResponseMatch = false;
	}
	
	public boolean getServerResponseTooFeawGamersToPlay(){ return serverResponseTooFeawGamersToPlay; }
	public boolean getServerResponseMatch(){ return serverResponseMatch; }
	public boolean getServerResponseGamerNotInMatch(){ return serverResponseGamerNotInMatch; }
	public boolean getServerResponseMatchNotAvailableYet(){ return serverResponseMatchNotAvailableYet; }
	
	private class StartRequestCanIPlay implements Runnable{

		@Override
		public void run() {	
			System.out.println("[ClientRequestCanIPlayScheduler] thread iniziato ");
			String result = controller.clientRequestCanIPlay();
			
			if(result.equals(ServerMessageContentType.SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY.getServerMessageContentType())){
				System.out.println("[ClientRequestCanIPlayScheduler] SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY ");
				setServerResponseTooFeawGamersToPlay();
			}
			
			if(result.equals(ServerMessageContentType.SERVER_RESPONSE_GAMER_NOT_IN_MATCH.getServerMessageContentType())){
				System.out.println("[ClientRequestCanIPlayScheduler] SERVER_RESPONSE_GAMER_NOT_IN_MATCH ");
				setServerResponseGamerNotInMatch();
			}
			
			if(result.equals(ServerMessageContentType.SERVER_RESPONSE_MATCH.getServerMessageContentType())){
				System.out.println("[ClientRequestCanIPlayScheduler] SERVER_RESPONSE_MATCH ");
				setServerResponseMatch();
			}
			
			if(result.equals(ServerMessageContentType.SERVER_RESPONSE_MATCH_NOT_AVAILABLE_YET.getServerMessageContentType())){
				System.out.println("[ClientRequestCanIPlayScheduler] SERVER_RESPONSE_MATCH_NOT_AVAILABLE_YET ");
				setServerResponseMatchNotAvailableYet();
			}
			
			System.out.println("[ClientRequestCanIPlayScheduler] Result: "+result);
			System.out.println(result);
		}
		
	}
	
	private class StopRequestCanIPlay implements Runnable{
		private ScheduledFuture<?> scheduled;
		
		StopRequestCanIPlay(ScheduledFuture<?> scheduled){ this.scheduled = scheduled; }
		
		@Override
		public void run() {	
			scheduled.cancel(DONT_INTERRUPT_IF_RUNNING);
			scheduler.shutdown();
			System.out.println("\nRichiesta scaduta");
		}
		
	}
	
	@Override
	public void run(){
		try {
			this.requestCanIPlay();
			sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
