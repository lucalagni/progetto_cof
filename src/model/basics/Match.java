package model.basics;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.basics.constants.MatchConstants;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.codes.MatchExceptionCode;
import model.basics.supports.MatchStatus;

public class Match implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matchCode;
	private String title ;
	private Date date ;
	private Board board;
	private HashMap<Gamer,Integer> positions;
	private String actualGamer;
	private String nextGamer;
	private MatchStatus status;
	
	public Match(String matchCode, String title,Date date, Board board,HashMap<Gamer,Integer> positions,String actualGamer,MatchStatus status) throws MatchException {
		this.setMatchCode(matchCode);
		this.setTitle(title);
		this.setDate(date);
		this.setBoard(board);
		this.setMatchStatus(status);
		this.setPositions(positions);
		this.setActualGamer(actualGamer);
		//this.setNextGamer();
	}
	
	public void setMatchStatus(MatchStatus status){this.status = status; }
	private void setTitle(String title) { this.title = title; }
	private void setDate(Date date) {
		if(date == null) date = new Date();
		this.date = date;
	}
	
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setBoard(Board board) { this.board = board;}
	private void setPositions(HashMap<Gamer,Integer> positions) throws MatchException {
		if(positions.size() == 0) throw new MatchException(MatchExceptionCode.NULL_GAMERS.getExceptionCode());
		if(positions.size() > MatchConstants.MAX_NUMBER_OF_GAMERS) throw new MatchException(MatchExceptionCode.TOO_MANY_GAMERS.getExceptionCode());
		
		this.positions = positions;
	}
	private void setActualGamer(String actualGamer){ this.actualGamer = actualGamer; }
	private void checkMatchStatus(){
		if(this.status != MatchStatus.READY) return;
		if(this.positions.size() >= MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY) { } //Far partire la partita 
	}
	
	public void addGamer(Gamer gamer) throws MatchException{
		if(this.positions == null) this.positions = new HashMap<Gamer,Integer>();
		if(this.positions.size() + 1 > MatchConstants.MAX_NUMBER_OF_GAMERS)throw new MatchException(MatchExceptionCode.TOO_MANY_GAMERS.getExceptionCode());
		
		this.positions.put(gamer, MatchConstants.MIN_POSITION);
		this.checkMatchStatus();
	}
	
	public Gamer getActualGamerObject(){
		Iterator<Map.Entry<Gamer, Integer>> it = this.getPositions().entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<Gamer, Integer> entry = it.next();
			if(entry.getKey().getUsername().equals(this.getActualGamer())) return entry.getKey();
		}
		
		return null;
	}
	
	/*public void setNextGamer(){
		Iterator<Map.Entry<Gamer, Integer>> it = this.getPositions().entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<Gamer, Integer> entry = it.next();
			if(entry.getKey().getUsername().equals(this.getActualGamer())){
				if(it.next().getKey() == null) this.nextGamer = MatchConstants.END_CYCLE_CODE;
				else this.nextGamer = it.next().getKey().getUsername();
			}
		}
		
	}*/
	
	public void done(){
		this.setActualGamer(this.nextGamer);
		//this.setNextGamer();
	}
	
	public String getTitle(){ return this.title; }
	public Date getDate(){ return this.date; }
	public Board getBoard(){ return this.board; }
	public HashMap<Gamer,Integer> getPositions(){ return this.positions; }
	public MatchStatus getMatchStatus(){ return this.status; }
	public String getMatchCode(){ return this.matchCode; }
	public String getActualGamer(){ return this.actualGamer ; }
	public String getNextGamer(){ return this.nextGamer; }
	
	@Override
	public String toString() {
		String mString = new String("\nMatch\n");
		Iterator<Map.Entry<Gamer, Integer>> it = this.getPositions().entrySet().iterator();
		
		mString += "match code: " + this.getMatchCode() + "\n";
		mString += "title: " + this.getTitle() + "\n";
		mString += "date: " + this.getDate() + "\n";
		mString += "status: "+ this.getMatchStatus().getStatusCode() + "\n";
		mString += "actual gamer: " + this.getActualGamer() + "\n";
		mString += "next gamer: " + this.getNextGamer() + "\n";
		mString += "board: " + this.getBoard().toString() + "\n";
		mString += "positions: \n" ;
		while(it.hasNext()) {
			Map.Entry<Gamer, Integer> entry = it.next();
			mString += "gamer: " + entry.getKey().getUsername() + " position: " + entry.getValue().intValue()  + "\n";
			mString += "data: " + entry.getKey().toString();
		}
		
		return mString ;
		
	}
}
