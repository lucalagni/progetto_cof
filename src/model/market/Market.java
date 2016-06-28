package model.market;

import java.io.Serializable;
import java.util.ArrayList;

import model.basics.exceptions.GamerException;
import model.market.exceptions.MarketException;
import model.market.exceptions.codes.MarketExceptionCode;

public class Market implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Agent> agents;
	private Boolean editable;
	
	public Market(){
		this.setEditable(true);
		this.agents = new ArrayList<Agent>();
	}
	
	private void setEditable(boolean editable){ this.editable = new Boolean(editable);}
	
	public synchronized void buyItem(String buyer,String seller,ItemType type, int position) throws MarketException, GamerException{
		if(this.getEditable() == true) return ;
		switch(type){
			case HELPERS_ITEM:
				this.buyHelpers(buyer, seller, position);
				break;
			case POLITICAL_CARD_ITEM:
				this.buyPoliticalCard(buyer, seller, position);
				break;
			case PERMIT_CARD_ITEM:
				this.buyPermitCard(buyer, seller, position);
				break;
			default :
				break;
				
		}
	}
	
	public synchronized void addAgent(Agent agent) throws MarketException{
		if(this.getEditable() == false) return ;
		for(Agent a : this.agents){
			if(a == agent) throw new MarketException(MarketExceptionCode.AGENT_ALREADY_PRESENT.getExceptionCode());
		}
		
		this.agents.add(agent);
	}
	
	private synchronized void buyPoliticalCard(String b,String s,int position) throws MarketException, GamerException{
		Agent seller = this.retriveAgent(s);
		Agent buyer = this.retriveAgent(b);
		
		if(seller == buyer) throw new MarketException(MarketExceptionCode.INVALID_AGENTS.getExceptionCode());
		if(seller.getPoliticalCardStock().size() <= position ) throw new MarketException(MarketExceptionCode.ITEM_NOT_FOUND.getExceptionCode());
		if(seller.getPoliticalCardStock().get(position).getAvailable() == false) throw new MarketException(MarketExceptionCode.ITEM_NOT_AVAILABLE.getExceptionCode());
		
		PoliticalCardItem item = seller.getPoliticalCardStock().get(position);
		
		if(buyer.getGamer().getCoins() < item.getPrice()) throw new MarketException(MarketExceptionCode.BUYER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		buyer.getGamer().subCoins(item.getPrice());
		seller.getGamer().addCoins(item.getPrice());
		
		seller.getGamer().subPoliticalCard(item.getPosition());
		buyer.getGamer().addPoliticalCard(item.getPoliticalCard());
		
		item.sell();
	}
	
	public void ready(){ this.setEditable(false); }
	
	private synchronized void buyPermitCard(String b,String s,int position) throws MarketException, GamerException{
		Agent seller = this.retriveAgent(s);
		Agent buyer = this.retriveAgent(b);
		
		if(seller == buyer) throw new MarketException(MarketExceptionCode.INVALID_AGENTS.getExceptionCode());
		if(seller.getPermitCardStock().size() <= position ) throw new MarketException(MarketExceptionCode.ITEM_NOT_FOUND.getExceptionCode());
		if(seller.getPermitCardStock().get(position).getAvailable() == false) throw new MarketException(MarketExceptionCode.ITEM_NOT_AVAILABLE.getExceptionCode());
		
		PermitCardItem item = seller.getPermitCardStock().get(position);
		
		if(buyer.getGamer().getCoins() < item.getPrice()) throw new MarketException(MarketExceptionCode.BUYER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		buyer.getGamer().subCoins(item.getPrice());
		seller.getGamer().addCoins(item.getPrice());
		
		seller.getGamer().subPermitCard(item.getPermitCard());
		buyer.getGamer().addPermitCard(item.getPermitCard());
		
		item.sell();
	}
	
	private synchronized void buyHelpers(String b,String s,int position) throws MarketException, GamerException{
		Agent seller = this.retriveAgent(s);
		Agent buyer = this.retriveAgent(b);
		
		if(seller == buyer) throw new MarketException(MarketExceptionCode.INVALID_AGENTS.getExceptionCode());
		if(seller.getHelpersStock().size() <= position ) throw new MarketException(MarketExceptionCode.ITEM_NOT_FOUND.getExceptionCode());
		if(seller.getHelpersStock().get(position).getAvailable() == false) throw new MarketException(MarketExceptionCode.ITEM_NOT_AVAILABLE.getExceptionCode());
		
		HelpersItem item = seller.getHelpersStock().get(position);
		
		if(buyer.getGamer().getCoins() < item.getPrice()) throw new MarketException(MarketExceptionCode.BUYER_HAS_TOO_FEAW_COINS.getExceptionCode());
		if(seller.getGamer().getHelpers() < item.getHelpers()) throw new MarketException(MarketExceptionCode.SELLER_HAS_TOO_FEAW_HELPERS.getExceptionCode());
		
		buyer.getGamer().subCoins(item.getPrice());
		seller.getGamer().addCoins(item.getPrice());
		
		buyer.getGamer().addHelpers(item.getHelpers());
		seller.getGamer().subHelpers(item.getHelpers());
		
		item.sell();
	}
	
	private synchronized Agent retriveAgent(String username) throws MarketException{
		Agent agent = null;
		
		for(int i = 0; i < this.agents.size(); i++){
			if(this.agents.get(i).getUsername().equals(username)){
				agent = this.agents.get(i);
				break;
			}
		}
		
		if(agent == null) throw new MarketException(MarketExceptionCode.AGENT_NOT_FOUND.getExceptionCode());
		
		return agent;
	}
	
	public ArrayList<Agent> getAgents(){ return this.agents; }
	public boolean getEditable(){ return this.editable.booleanValue(); }
	
	@Override
	public String toString(){
		String s = "\nMarket\n";
		
		
		for(Agent a : this.agents) s += "agent: " + a.toString() + "\n";
		
		return s;
	}
}
