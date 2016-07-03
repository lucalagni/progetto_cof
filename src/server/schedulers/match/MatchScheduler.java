package server.schedulers.match;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import commons.schedulers.server.ServerSchedulersConstants;
import model.basics.Match;

/**
 * Classe che si occupa di gestire i turni dei match
 * @author Luca Lagni
 *
 */
@SuppressWarnings("unused")
public class MatchScheduler {
	private static final int NUM_THREADS = 1;
	private static final boolean DONT_INTERRUPT_IF_RUNNING = false;
	
	private Match match;
	private String matchCode;
	
	private ScheduledExecutorService scheduler;
	
	private ScheduledFuture<?> scheduledTask;
	
	private boolean hasPlayed ; //Indica se nel suo quanto di tempo il giocatore ha fatto una mossa
	private boolean fistTurn ;
	
	public MatchScheduler(Match match){
		this.setMatch(match);
		this.setMatchCode(match.getMatchCode());
		this.setHasPlayed(false);
		this.setFirstTurn(true);
		this.scheduler = Executors.newScheduledThreadPool(NUM_THREADS);
	}
	
	private void setMatch(Match match){ this.match = match; }
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setHasPlayed(boolean hasPlayed){ this.hasPlayed = hasPlayed; }
	private void setFirstTurn(boolean firstTurn){ this.fistTurn = firstTurn; }
	
	public void matchScheduler(){
		try{
			Runnable startMatchScheduler = new StartMatchScheduler();
			this.scheduledTask = this.scheduler.scheduleWithFixedDelay(startMatchScheduler, 
																	   ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_SCHEDULER_DELAY,
																	   ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_SCHEDULER_PERIOD,
																	   ServerSchedulersConstants.SERVER_SCHEDULER_MATCH_SCHEDULER_TIME_UNIT);
			
		}catch(Exception ex){}
	}
	
	public Match getMatch(){ return this.match; }
	public String getMatchCode(){ return this.match.getMatchCode(); }
	public boolean getHasPlayed(){ return this.hasPlayed; }
	public boolean getFirstTurn(){ return this.fistTurn; }
	
	private class StartMatchScheduler implements Runnable {

		public void run() {
			if(getFirstTurn() == true) setFirstTurn(false);
			else getMatch().done();
			System.out.println("\n[MatchScheduler] MATCH_CODE: " + getMatchCode() + " GAMER: " + getMatch().getActualGamer());
		}
		
	}
}
