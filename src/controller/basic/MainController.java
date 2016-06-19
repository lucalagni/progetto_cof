package controller.basic;

import model.basics.Board;
import model.basics.Match;

public class MainController {
	private Match match;
	private Board board;
	
	public MainController(Board b){
		
	}
	
	private void setBoard(Board b){ this.board = board; }
	private void setMatch(Match m){ this.match = match; }
	
	public Board getBoard(){ return this.board; }
	public Match getMatch(){ return this.match; }

}
