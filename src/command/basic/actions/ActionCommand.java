package command.basic.actions;

import model.basics.Gamer;
import model.basics.Match;

public class ActionCommand {
	private Match match;
	private Gamer gamer;
	
	private int virtualHelpers ;
	private int virtualCoins ;
	
	public ActionCommand(Match match,Gamer gamer){
		this.setGamer(gamer);
		this.setMatch(match);
	}
	
	private void setMatch(Match match){ this.match = match; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	
	
	public Match getMatch(){ return this.match; }
	public Gamer getGamer(){ return this.gamer; }
	
}
