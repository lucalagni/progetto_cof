package server.command.actions.setter;

import commons.data.UserData;

import model.basics.Match;
import model.market.Agent;

/**
 * Classe che implementa i metodi per l'esecuzione delle azioni di settaggio del market
 * @author Luca Lagni
 *
 */
public class SetterActionCommand {
	private Match match;
	private Agent agent;
	
	public SetterActionCommand(UserData data){
		this.setMatch(data.getMatch());
		String username = data.getUsername();
		for(int i = 0; i < data.getMatch().getMarket().getAgents().size(); i++){
			if(username.equals(data.getMatch().getMarket().getAgents().get(i))){
				this.setAgent(data.getMatch().getMarket().getAgents().get(i));
				break;
			}
		}
	}
	
	private void setMatch(Match match){ this.match = match; }
	private void setAgent(Agent agent){ this.agent = agent; }
	
	
	public Match getMatch(){ return this.match; }
	public Agent getAgent(){ return this.agent; }
}
