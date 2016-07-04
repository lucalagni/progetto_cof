package client.controller.updates.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import commons.schedulers.client.ClientSchedulersConstants;
import client.controller.ControllerRepository;
import client.controller.data.GameDataController;
import client.controller.updates.GameUpdatesController;

/**
 * Classe per la gestione dello scheduler per la richiesta del turno di gioco
 * @author Luca Lagni
 *
 */

@SuppressWarnings("unused")
public class GameUpdatesScheduler extends Thread{
	private static final int NUM_THREADS = 1;
	private static final boolean DONT_INTERRUPT_IF_RUNNING = true ;
	private static final int SLEEP_TIME = 100;
	private static int counter = 0;
	
	private ScheduledExecutorService scheduler;
	private GameUpdatesController controller;
	private GameDataController dataController;
	
	private ScheduledFuture<?> scheduledTask;
	private int actualGamer ;
	private int myGamerID;
	
	private boolean itsMyTurnToPlay;
	
	public GameUpdatesScheduler(){
		counter++;
		this.scheduler = Executors.newScheduledThreadPool(NUM_THREADS);
		ControllerRepository.getInstance().setGameUpdatesController();
		this.dataController = ControllerRepository.getInstance().getGameDataController();
		this.controller = ControllerRepository.getInstance().getGameUpdatesController();
		for(int i = 0; i < this.dataController.getUserData().getMatch().getGamers().size(); i++){
			if(this.dataController.getUserData().getMatch().getGamers().get(i).getUsername().equals(this.dataController.getUserData().getUsername())){
				this.setMyGamerID(i);
				System.out.println("\n[GameUpdatesScheduler] MY GAMER ID IS: " + this.getMyGamerID());
				break;
			}
		}
		this.setActualGamer(this.dataController.getUserData().getMatch().getActualGamer());
	}
	
	private void setActualGamer(int actualGamer){ this.actualGamer = actualGamer; }
	private void setMyGamerID(int myGamerID){ this.myGamerID = myGamerID; }
	public int getActualGamer(){ return this.actualGamer; }
	public int getMyGamerID(){ return this.myGamerID; }
	
	private void setItsMyTurnToPlay(boolean itsMyTurnToPlay){ 
		this.itsMyTurnToPlay = itsMyTurnToPlay; 
		//this.scheduledTask.cancel(DONT_INTERRUPT_IF_RUNNING);
		//this.scheduler.shutdownNow();
	}
	
	public boolean getItsMyTurnToPlay(){ return this.itsMyTurnToPlay; }
	
	public void requestGamerTurn(){
		Runnable startRequestTask = new StartRequestGamerTurn();
		this.scheduledTask = this.scheduler.scheduleWithFixedDelay(startRequestTask, 
																   ClientSchedulersConstants.CLIENT_SCHEDULER_GAMER_TURN_DELAY, 
																   ClientSchedulersConstants.CLIENT_SCHEDULER_GAMER_TURN_PERIOD,
																   ClientSchedulersConstants.CLIENT_SCHEDULER_GAMER_TURN_TIME_UNIT);
		
		try{
			this.scheduler.awaitTermination(ClientSchedulersConstants.CLIENT_SCHEDULER_GAMER_TURN_AWAIT, 
											ClientSchedulersConstants.CLIENT_SCHEDULER_GAMER_TURN_TIME_UNIT);
		}catch(Exception ex){}
		
	}
	
	private class StartRequestGamerTurn implements Runnable  {

		public void run() {
			 System.out.println("counter: " + counter);
			 controller.getGamerTurn();
			 int gamerTurn = dataController.getUserData().getMatch().getActualGamer();
			 System.out.println("\nGamer turn: " + gamerTurn);
			 if(myGamerID == gamerTurn) setItsMyTurnToPlay(true);
			 else setItsMyTurnToPlay(false);
			
			/*if(controller.getGamerTurn() != getActualGamer()){
				//Verifico se è il mio turno di fare azioni
				System.out.println("[GameUpdatesScheduler] IT'S THE TURN OF: " + gamerTurn);
				setActualGamer(gamerTurn);
				if(gamerTurn == getMyGamerID()) {
					System.out.println("[GameUpdatesScheduler] IT'S MY TURN TO PLAY");
					//setItsMyTurnToPlay(true);
				}
				else{
					System.out.println("[GameUpdatesScheduler] IT'S NOT MY TURN TO PLAY");
					//setItsMyTurnToPlay(false);
				}
			}*/
		}
		
	}
	
	@Override
	public void run(){
		try{
			this.requestGamerTurn();
		}catch(Exception ex){}
	}
}
