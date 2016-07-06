package model.market;

import java.io.Serializable;
import java.util.ArrayList;

import model.market.exceptions.MarketException;
/**
 * Classe per la realizzazione della funzionalità market
 * 
 * @author Luca Lagni, Maria Antonietta Palermo
 *
 */
public class Market implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Agent> agents;
	private Boolean editable;
	
	public Market(){
		this.setEditable(true);
		this.agents = new ArrayList<Agent>();
	}
	
	private void setEditable(boolean editable){ this.editable = new Boolean(editable);}
	
	public void updateAgent(Agent agent) throws MarketException{
		boolean find = false ;
		if(this.getEditable() == false) return ;
		
		for(int i = 0; i < this.agents.size(); i++){
			if(agent.equals(this.agents.get(i))){
				this.agents.set(i, agent);
				find = true;
				break;
			}
		}
		
		if(find == false)this.agents.add(agent);
	}
	
	public void ready(){ this.setEditable(false); }
	
	
	public ArrayList<Agent> getAgents(){ return this.agents; }
	public boolean getEditable(){ return this.editable.booleanValue(); }
	
	@Override
	public String toString(){
		String s = "\nMarket\n";
		for(Agent a : this.agents) s += "agent: " + a.toString() + "\n";
		
		return s;
	}
}
