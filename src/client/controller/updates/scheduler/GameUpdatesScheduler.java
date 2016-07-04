package client.controller.updates.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import commons.schedulers.client.ClientSchedulersConstants;
import client.controller.ControllerRepository;
import client.controller.data.GameDataController;
import client.controller.updates.GameUpdatesController;
import client.view.cli.basic.CliMainMenu;

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
	
	private ScheduledExecutorService scheduler;
	private GameUpdatesController controller;
	private GameDataController dataController;
	
	private ScheduledFuture<?> scheduledTask;
	private int actualGamer ;
	private int myGamerID;
	
	private boolean itsMyTurnToPlay;
	
	public GameUpdatesScheduler(){
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
	
	private void setItsMyTurnToPlay(boolean itsMyTurnToPlay){ this.itsMyTurnToPlay = itsMyTurnToPlay;  }
	
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
		private Thread t1 ;
		
		StartRequestGamerTurn(){ 
			this.t1 = null;
		}
		
		public void run() {
			 controller.getGamerTurn();
			 int gamerTurn = dataController.getUserData().getMatch().getActualGamer();
			 if(myGamerID == gamerTurn){
				 setItsMyTurnToPlay(true);
				 
				 if(this.t1 != null)this.t1.interrupt(); 
				 this.t1 = new Thread(new CliMainMenu(true)); 
				 this.t1.start();
				 
				 System.out.println("\n========== SONO IL GIOCATORE DI TURNO ==========\n");
				 
			 }
			 else{
				 setItsMyTurnToPlay(false);
				 
				 if(this.t1 != null) this.t1.interrupt();
				 this.t1 = new Thread(new CliMainMenu(false)); 
				 this.t1.start();
				 
				 System.out.println("\n========== NON SONO IL GIOCATORE DI TURNO ==========\n");
			 }
		}
		
	}
	
	@Override
	public void run(){
		try{
			this.requestGamerTurn();
		}catch(Exception ex){}
	}
}
