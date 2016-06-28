package client.command;

import model.basics.Gamer;
import model.basics.Match;

public class DataHandler {
	private String matchCode;
	private String username;
	
	private Match match;
	private Gamer gamer;
	
	public DataHandler(String matchCode,String username,GameMode mode){
		this.setMatchCode(matchCode);
		this.setUsername(username);
	}
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setUsername(String username){ this.username = username; }
	private void setMatch(Match match){ this.match = match; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	
	public String getMatchCode(){ return this.matchCode; }
	public String getUsername(){ return this.username; }
	public Match getMatch(){ return this.match; }
	public Gamer getGamer(){ return this.gamer; }
}
