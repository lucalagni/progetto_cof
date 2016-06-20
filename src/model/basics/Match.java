package model.basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import model.basics.exceptions.MatchException;
import model.basics.supports.MatchStatus;

public class Match implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matchCode;
	private String title ;
	private Date date ;
	private Board board;
	private ArrayList<Gamer> gamers;
	private int actualGamer;
	private int nextGamer;
	private MatchStatus status;
	
	public Match(String matchCode, String title,Date date, Board board,ArrayList<Gamer> gamers,int actualGamer,MatchStatus status) throws MatchException {
		this.setMatchCode(matchCode);
		this.setTitle(title);
		this.setDate(date);
		this.setBoard(board);
		this.setMatchStatus(status);
		this.setGamers(gamers);
		this.setActualGamer(actualGamer);
		this.setNextGamer();
	}
	
	private void setMatchStatus(MatchStatus status){this.status = status; }
	private void setTitle(String title) { this.title = title; }
	private void setDate(Date date) {this.date = date;}
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setBoard(Board board) { this.board = board;}
	private void setGamers(ArrayList<Gamer> gamers){ this.gamers = gamers; }
	private void setActualGamer(int actualGamer){ this.actualGamer = actualGamer; }
	
	public void changeMatchStatus(MatchStatus newStatus){ this.setMatchStatus(status); }
	
	private void setNextGamer(){
		if((this.actualGamer + 1) >= this.gamers.size()) this.nextGamer = 0;
		else this.nextGamer = this.actualGamer + 1;
	}
	
	public void done(){
		this.setActualGamer(this.nextGamer);
		this.setNextGamer();
	}
	
	public String getTitle(){ return this.title; }
	public Date getDate(){ return this.date; }
	public Board getBoard(){ return this.board; }
	public ArrayList<Gamer> getGamers(){ return this.gamers; }
	public MatchStatus getMatchStatus(){ return this.status; }
	public String getMatchCode(){ return this.matchCode; }
	public int getActualGamer(){ return this.actualGamer ; }
	public int getNextGamer(){ return this.nextGamer; }
	
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
		mString += "positions: \n" ;
		for(Gamer g : gamers) mString += "gamer: " + g.toString() + "\n";
		
		return mString ;
		
	}
}
