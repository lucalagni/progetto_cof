package server.schedulers.match;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import server.managers.match.MatchRepository;
import commons.schedulers.server.ServerSchedulersConstants;
import model.basics.Match;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.supports.GamerStatus;
import model.basics.supports.MatchPhase;
import model.basics.supports.MatchStatus;

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
		System.out.println("\n[MatchScheduler] MATCH_CODE: " + getMatchCode() + " GAMER: " + getMatch().getActualGamer());
	}
	
	public void setMatch(Match match){ this.match = match; }
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
			boolean flag = true;
			int gamers = getMatch().getGamers().size();
			
			do{
				getMatch().done();
				if(getMatch().getGamers().get(getMatch().getActualGamer()).getStatus().equals(GamerStatus.OFFLINE)){
					flag = true;
				}
				else flag = false ;
				gamers--;
				
			}while(flag == true);
			
			if(getMatch().getMatchPhase().equals(MatchPhase.MATCH_PHASE)){
				if(getMatch().getActualGamer() == 0){
					for(int i = 0; i < getMatch().getMarket().getAgents().size(); i++)getMatch().getMarket().getAgents().get(i).resetAgent();
					MatchRepository.getInstance().updateMatch(getMatch());
				}
			}
			
			if(getMatch().getMatchStatus().equals(MatchStatus.TERMINATED)){
				if(getMatch().getMatchPhase().equals(MatchPhase.SETTER_PHASE)){
					if(getMatch().getLastTurnStarted() == true){
						MatchRepository.getInstance().updateMatch(getMatch());
						this.stopMatchScheduler(MatchStatus.TERMINATED);
					}
				}
			}
			
			//if(this.checkGamerStatus() == true) this.nextTurn();
			//this.endMatch();
			//this.resetMatchAgents();
			
			System.out.println("\n[MatchScheduler] MATCH_CODE: " + getMatchCode() + " GAMER: " + getMatch().getActualGamer());
		}
		
		/**
		 * Metodo che attua la fine del match per fine partita
		 */
		private void endMatch(){
			
		}
		
		/**
		 * Metodo che resetta i dati agente della parita
		 */
		private void resetMatchAgents(){
		}
		
		/**
		 * Metodo che ferma l'esecuzione del match scheduler per fine partita
		 */
		private void stopMatchScheduler(MatchStatus status){
			getMatch().changeMatchStatus(status);
			MatchRepository.getInstance().updateMatch(getMatch());
			scheduledTask.cancel(true);
			scheduler.shutdown();
		}
		
		/**
		 * Metodo che verifica se nella partita ci sono ancora giocatori
		 */
		private boolean checkGamerStatus(){
			boolean flag = true;
			
			//Verifico che ci siano ancora giocatori connessi
			for(int i = 0; i < getMatch().getGamers().size(); i++){
				if(getMatch().getGamers().get(i).getStatus().equals(GamerStatus.ONLINE)) {
					flag = false ;
					break;
				}
			}
			
			if(flag == true){
				this.stopMatchScheduler(MatchStatus.FREEZED);
				return false;
			}
			else return true;
		}
		
	}
}
