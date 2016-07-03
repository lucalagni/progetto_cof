package model.basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import model.basics.exceptions.MatchException;
import model.basics.supports.MatchStatus;
import model.market.Market;

public class Match implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matchCode;
	private String title ;
	private Date date ;
	private Board board;
	private ArrayList<Gamer> gamers;
	private Integer actualGamer;
	private Integer nextGamer;
	private MatchStatus status;
	private Market market;
	private Boolean lastTurn;        //Indica se un giocatore ha finito tutti gli empori a sua disposizione -> ultimo tuno
	private Boolean lastTurnStarted; 
	/*
	 * lastTurn : indica se un giocatore ha terminato tutti i suoi empori e, in caso affermativo , decreta la presenza di solo
	 * un turno disponibile
	 * 
	 * lastTurnStarted : Poichè il giocatore che piazza tutti gli empori potrebbe non essere l'ultimo, 
	 * definisco se il ciclo finale è iniziato o meno
	 */
	
	public Match(String matchCode, String title,Date date, Board board,ArrayList<Gamer> gamers,int actualGamer,MatchStatus status) throws MatchException {
		this.setMatchCode(matchCode);
		this.setTitle(title);
		this.setDate(date);
		this.setBoard(board);
		this.setMatchStatus(status);
		this.setGamers(gamers);
		this.setActualGamer(actualGamer);
		this.setNextGamer();
		this.setLastTurn(false);
		this.setLastTurnStarted(false);
	}
	
	private void setMatchStatus(MatchStatus status){this.status = status; }
	private void setTitle(String title) { this.title = title; }
	private void setDate(Date date) {this.date = date;}
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setBoard(Board board) { this.board = board;}
	private void setGamers(ArrayList<Gamer> gamers){ this.gamers = gamers; }
	private void setActualGamer(int actualGamer){ this.actualGamer = new Integer(actualGamer); }
	private void setNextGamer(int nextGamer){ this.nextGamer = new Integer(nextGamer); }
	//private void setMarket(Market market){ this.market = market; }
	private void setLastTurn(boolean lastTurn){ this.lastTurn = new Boolean(lastTurn); }
	private void setLastTurnStarted(boolean lastTurnStarted){ this.lastTurnStarted = new Boolean(lastTurnStarted); }
	
	public void changeMatchStatus(MatchStatus newStatus){ this.setMatchStatus(status); }
	
	/**private void setNextGamer(){
		if((this.getActualGamer() + 1) >= this.gamers.size()){
			this.setNextGamer(MatchConstants.TIME_TO_MARKET_SETUP);
			this.setMarket(new Market());
		}
		else if(this.getActualGamer() == MatchConstants.TIME_TO_MARKET_SETUP){
			this.setNextGamer(MatchConstants.TIME_TO_MARKET);
		}
		else if(this.getActualGamer() == MatchConstants.TIME_TO_MARKET) this.setNextGamer(0);
		else{
			this.setNextGamer(this.getActualGamer() + 1);
			this.setMarket(null);
		}
	}*/
	
	private void setNextGamer(){
		if((this.getActualGamer() + 1) >= this.gamers.size()){
			this.setNextGamer(0);
			if(this.getLastTurn() == true) {
				if(this.lastTurnStarted == true){
					//La partita è finita
					this.setMatchStatus(MatchStatus.TERMINATED);
				}
				else this.setLastTurnStarted(true);
			}
		}
		else this.setNextGamer(this.getActualGamer() + 1);
	}
	
	public void done(){
		this.setActualGamer(this.getNextGamer());
		this.setNextGamer();
	}
	
	public String getTitle(){ return this.title; }
	public Date getDate(){ return this.date; }
	public Board getBoard(){ return this.board; }
	public ArrayList<Gamer> getGamers(){ return this.gamers; }
	public MatchStatus getMatchStatus(){ return this.status; }
	public String getMatchCode(){ return this.matchCode; }
	public int getActualGamer(){ return this.actualGamer.intValue() ; }
	public int getNextGamer(){ return this.nextGamer.intValue(); }
	public Market getMarket(){ return this.market; }
	public boolean getLastTurn(){ return this.lastTurn.booleanValue(); }
	public boolean getLastTurnStarted(){ return this.lastTurnStarted.booleanValue(); }
	
	@Override
	public String toString() {
		String mString = new String("\nMatch\n");
		
		mString += "match code: " + this.getMatchCode() + "\n";
		mString += "title: " + this.getTitle() + "\n";
		mString += "date: " + this.getDate() + "\n";
		mString += "status: "+ this.getMatchStatus().getStatusCode() + "\n";
		mString += "actual gamer: " + this.getActualGamer() + "\n";
		mString += "next gamer: " + this.getNextGamer() + "\n";
		mString += "board: " + this.getBoard().toString() + "\n";
		mString += "last turn: " + this.getLastTurn() + "\n";
		mString += "positions: \n" ;
		for(Gamer g : gamers) mString += "gamer: " + g.toString() + "\n";
		
		return mString ;
		
	}
}
