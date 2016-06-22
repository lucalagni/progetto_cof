
	package model.market;

	import java.util.ArrayList;

	import model.basics.Match;
	import model.basics.exceptions.GamerException;

	public class Market {

		private ArrayList<Agent> agents;
		private Match match;
		
		public Market(ArrayList<Agent> agents,Match match){
			this.agents = agents;
		}
		
		public ArrayList<Agent> getArraListAgent(){
			return agents;	
		}

		public void helpersTransaction(Agent seller, Agent buyer,ItemHelpers item) throws GamerException{
			if(buyer.getGamer().getCoins() < item.getPrice()); //Lancio eccezzione
			if(item.getAvailable() == false) ; //lancio eccezzione
			
			seller.sellHelpers(item);
			buyer.buyHelpers(item);
			
		}
		
		
		
	}

