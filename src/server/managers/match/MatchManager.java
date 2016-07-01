package server.managers.match;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import commons.schedulers.server.ServerSchedulersConstants;

import model.basics.Match;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;

/**
 * Classe che gestisce l'aggiunta di giocatori ad un match
 * 
 * @author Luca Lagni
 *
 */
public class MatchManager {
	public static final int NUM_THREADS = 1;
	public static final boolean SGS_DONT_INTERRUPT_IF_RUNNING = false ;
	public static final boolean MGS_DONT_INTERRUPT_IF_RUNNING = true ;
	
	private static ScheduledExecutorService singleGamerScheduler;    
	private static ScheduledExecutorService multipleGamerScheduler ;
	
	private ArrayList<String> gamersQueque;
	private MatchRepository matchRepository;
	private static MatchManager instance = null;
	
	private String aloneGamer;
	private ScheduledFuture<?> singleGamerTask;
	
	private MatchManager(){
		this.gamersQueque = new ArrayList<String>();
		this.matchRepository = MatchRepository.getInstance();
		this.aloneGamer = null;
		
		singleGamerScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
		multipleGamerScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
	}
	
	public synchronized boolean addGamer(String gamer){ 
		if(this.checkName(gamer) == false) return false; //Evito che venga inserito un utente duplicato 
		this.gamersQueque.add(gamer); 
		if(this.gamersQueque.size() == 1) this.startSingleGamerScheduler();
		if(this.gamersQueque.size() == MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY){
			
			System.out.println("\nDoppio giocatore");
			this.singleGamerTask.cancel(true);
			singleGamerScheduler.shutdownNow();
			this.aloneGamer = null;
			this.startMultipleGamerScheduler();
		}
		
		return true;
	}
	
	private synchronized void startMultipleGamerScheduler(){
		try{
			Runnable startMultipleGamersTask = new StartMultipleGamersTask();
			ScheduledFuture<?> multipleGamersTask = multipleGamerScheduler.scheduleWithFixedDelay(startMultipleGamersTask, 
																			   					  ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_DELAY, 
																			   					  ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_PERIOD, 
																			   					  ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_TIME_UNIT);
			Runnable stopMultipleGamersTask = new StopMultipleGamersTask(multipleGamersTask);
			multipleGamerScheduler.schedule(stopMultipleGamersTask, 
					                        ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_TIMEOUT, 
					                        ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_TIME_UNIT);
			multipleGamerScheduler.awaitTermination(ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_AWAIT, 
													ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_TIME_UNIT);
		}catch(Exception ex){}
	}
	
	private class StartMultipleGamersTask implements Runnable {

		public void run() {	System.out.println("\nGiocatori multipli in coda");}
		
	}
	
	private class StopMultipleGamersTask implements Runnable {
		private ScheduledFuture<?> futureScheduled;
		
		StopMultipleGamersTask(ScheduledFuture<?> futureScheduled){ this.futureScheduled = futureScheduled; }
		
		@Override
		public void run() {
			MatchRepository.getInstance().addAloneGamer(aloneGamer);
			this.futureScheduled.cancel(SGS_DONT_INTERRUPT_IF_RUNNING);
			multipleGamerScheduler.shutdown();
			genMatches();
		}
	}
	
	/**
	 * Questo metodo si occupa della gestione del timeout del singolo giocatore, in quanto,
	 * se dopo un certo tot di tempo non si sono aggiunti altri giocatori, viene tenuto
	 * in memoria che esso non ha potuto partecipare a match
	 */
	private synchronized void startSingleGamerScheduler(){
		try{
			Runnable startSingleGamerTask = new StartSingleGamerTask();
			this.singleGamerTask = singleGamerScheduler.scheduleWithFixedDelay(startSingleGamerTask, ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_DELAY, 
																												   ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_PERIOD, 
																												   ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_TIME_UNIT);
			Runnable stopSingleGamerTask = new StopSingleGamerTask(singleGamerTask);
			singleGamerScheduler.schedule(stopSingleGamerTask, ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_TIMEOUT, 
															   ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_MANAGER_TIME_UNIT);
			
		}catch(Exception ex){}
	}
	
	private class StartSingleGamerTask implements Runnable {
		@Override
		public void run() {
			//Non fa nulla , attende unicamente lo scadere del quanto di tempo
			System.out.println("\nGiocatore singolo");
		}
		
	}
	
	private class StopSingleGamerTask implements Runnable {
		private ScheduledFuture<?> futureScheduled;
		
		StopSingleGamerTask(ScheduledFuture<?> futureScheduled){ this.futureScheduled = futureScheduled; }
		
		@Override
		public void run() {
			MatchRepository.getInstance().addAloneGamer(aloneGamer);
			this.futureScheduled.cancel(SGS_DONT_INTERRUPT_IF_RUNNING);
			singleGamerScheduler.shutdown();
			System.out.println("\nGiocatore singolo aggiunto alla coda di attesa");
		}
		
	}
	
	/**
	 * Metodo che verifica se uno username e' gia stato inserito.
	 * @param username
	 * @return
	 */
	private synchronized boolean checkName(String username){
		for(String name : this.gamersQueque){
			if(name.equals(username)) return false ;
		}
		
		return true;
	}
	
	
	private void genMatches(){
		ArrayList<String> users = this.gamersQueque;
		this.gamersQueque = new ArrayList<String>();
		int numMatches = (users.size() / MatchConstants.MID_NUMBER_OF_GAMERS);
		MatchGenerator[] mg ;
		Match m ;
		int index = 0;
		int len = users.size();
		
		if(numMatches > 0) mg = new MatchGenerator[numMatches];
		else mg = new MatchGenerator[1];
		
		if(numMatches == 0){
			try {
				mg[0] = new MatchGenerator();
				for(String username : users){
					System.out.println("username: " + username);
					mg[0].addGamer(username);
				}
				
				m = mg[0].createMatch();
				this.matchRepository.addMatch(m);
			} catch (BuilderException | GameMapException | PoliticalCardsDeckException | MatchException e) {
				e.printStackTrace();
			}
		}
		else{
			//definisco quanti match creare
			for(int i = 0; i < numMatches; i++){
				try {
					mg[i] = new MatchGenerator();
					for(int j = 0; j < len; j++){
						if(index >= len) break;
						mg[i].addGamer(users.get(j));
						users.remove(j);
						index++;
					}
				} catch (BuilderException | GameMapException | PoliticalCardsDeckException e) { e.printStackTrace(); }
			}
			
			//Nel caso non abbia aggiunto tutti gli utenti, vado a distribuire i rimanenti sulle partite
			for(int i = 0; i < numMatches; i++){
				if(users.size() > 0){
					try {
						mg[i].addGamer(users.get(0));
						users.remove(0);
					} catch (PoliticalCardsDeckException e) {
						e.printStackTrace();
					}
					
				}
			}
			
			//aggiungo i match appena creati al repository
			for(MatchGenerator matchGen: mg){
				try {
					m = matchGen.createMatch();
					this.matchRepository.addMatch(m);
				} catch (BuilderException | PoliticalCardsDeckException
						| GameMapException | MatchException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized static MatchManager getInstance(){
		if(instance == null) instance = new MatchManager();
		return instance ;
	}
}
