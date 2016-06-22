package server.managers.socket.client;

import server.managers.match.MatchManager;
import model.basics.Gamer;
import model.basics.Match;

public class ClientGameData {
	private Gamer gamer = null;
	private Match match = null;
	
	public ClientGameData(String username){
		MatchManager mm = MatchManager.getInstance();
		mm.addGamer(username);
	}
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setMatch(Match match){ this.match = match; }
	
	public Gamer getGamer(){ return this.gamer; }
	public Match getMatch(){ return this.match; }
}
