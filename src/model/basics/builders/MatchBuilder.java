package model.basics.builders;

import java.util.ArrayList;
import java.util.Date;

import model.basics.Board;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.codes.MatchExceptionCode;
import model.basics.supports.MatchStatus;

public class MatchBuilder {
	private String title ;
	private Date date ;
	private Board board;
	private String matchCode ;
	private MatchStatus status ;
	private ArrayList<Gamer> gamers;
	private int actualGamer;
	
	public Match build() throws BuilderException, MatchException {
		if(this.title == null) throw new BuilderException(MatchExceptionCode.NULL_TITLE.getExceptionCode());
		if(board == null) throw new BuilderException(MatchExceptionCode.NULL_BOARD.getExceptionCode());
		if(date == null) date = new Date();
		if(gamers.size() < MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY)throw new BuilderException(MatchExceptionCode.TOO_FEAW_GAMERS_TO_PLAY.getExceptionCode());
		if(gamers.size() > MatchConstants.MAX_NUMBER_OF_GAMERS)throw new BuilderException(MatchExceptionCode.TOO_MANY_GAMERS.getExceptionCode());
		if((this.actualGamer < 0) || (this.actualGamer >= this.gamers.size()))throw new BuilderException(MatchExceptionCode.NULL_GAMER.getExceptionCode());
		
		return new Match(this.matchCode, this.title, this.date, this.board, this.gamers, this.actualGamer, this.status);
	}
	
	public MatchBuilder setActualGamer(int actualGamer){
		this.actualGamer = actualGamer;
		return this;
	}
	
	public MatchBuilder setGamers(ArrayList<Gamer> gamers){
		this.gamers = gamers;
		return this;
	}
	
	public MatchBuilder setStatus(MatchStatus status){
		this.status = status ;
		return this;
	}
	
	public MatchBuilder setMatchCode(String matchCode){
		this.matchCode = matchCode;
		return this;
	}
	
	public MatchBuilder setTitle(String title){
		this.title = title;
		return this;
	}
	
	public MatchBuilder setDate(Date date) {
		this.date = date;
		return this;
	}
	
	public MatchBuilder setBoard(Board board) {
		this.board = board;
		return this;
	}
}
