package server.command.actions.market;

import server.command.actions.market.exceptions.MarketActionCommandException;
import server.command.actions.market.exceptions.codes.MarketActionCommandExceptionCode;
import server.managers.match.MatchRepository;
import commons.data.UserData;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.constants.HelpersPoolConstants;
import model.basics.exceptions.GamerException;
import model.market.Agent;
import model.market.HelpersItem;
import model.market.PermitCardItem;
import model.market.PoliticalCardItem;

/**
 * Classe che implementa i metodi per eseguire le azioni di market
 * 
 * @author Luca Lagni
 *
 */
public class MarketActionCommand {
	private Match match;
	private Agent agent;
	private int buyerIndex;
	
	public MarketActionCommand(UserData data) throws MarketActionCommandException{
		this.setMatch(data.getMatch());
		this.buyerIndex = -1;
		for(int i = 0; i < this.getMatch().getMarket().getAgents().size(); i++){
			if(this.getMatch().getMarket().getAgents().get(i).getUsername().equals(data.getUsername())){
				this.setAgent(this.getMatch().getMarket().getAgents().get(i));
				this.buyerIndex = i;
				break;
			}
		}
		if(buyerIndex == -1)throw new MarketActionCommandException(MarketActionCommandExceptionCode.AGENT_NOT_FOUND.getExceptionCode());
	}
	
	private void setMatch(Match match) throws MarketActionCommandException{
		if(match == null) throw new MarketActionCommandException(MarketActionCommandExceptionCode.NULL_MATCH.getExceptionCode());
		this.match = match; 
	}
	public Match getMatch(){ return this.match; }
	
	/**
	 * Metodo per la compravendita di carte permesso
	 * @param sellerIndex
	 * @param permitCardItemIndex
	 * @throws MarketActionCommandException
	 * @throws GamerException
	 */
	public void buyPermitCardItem(int sellerIndex,int permitCardItemIndex) throws MarketActionCommandException, GamerException{
		int gamerSellerIndex = -1;
		int gamerBuyerIndex = -1;
		if((sellerIndex < 0) || (sellerIndex >= this.match.getMarket().getAgents().size()))throw new MarketActionCommandException(MarketActionCommandExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((permitCardItemIndex < 0) || (permitCardItemIndex >= this.match.getMarket().getAgents().get(sellerIndex).getPermitCardStock().size()))throw new MarketActionCommandException(MarketActionCommandExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		Agent seller = this.match.getMarket().getAgents().get(sellerIndex);
		
		for(int i = 0; i < this.match.getGamers().size(); i++){
			if(this.match.getGamers().get(i).getUsername().equals(seller.getUsername())){
				gamerSellerIndex = i;
				break;
			}
		}
		
		for(int i = 0; i < this.match.getGamers().size(); i++){
			if(this.match.getGamers().get(i).getUsername().equals(agent.getUsername())){
				gamerBuyerIndex = i;
				break;
			}
		}
		
		if(gamerSellerIndex == -1) throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_NOT_FOUND.getExceptionCode());
		if(gamerBuyerIndex == -1) throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_NOT_FOUND.getExceptionCode());
		
		
		
		PermitCardItem pci = seller.subPermitCardItem(permitCardItemIndex);
		
		//Controllo che il compratore abbia abbastanza soldi
		if(this.match.getGamers().get(gamerBuyerIndex).getCoins() < pci.getPrice()){
			seller.addPermitCardItem(pci);
			throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		}
		
		//Faccio lo scambio degli aiutanti
		this.match.getGamers().get(gamerSellerIndex).subPermitCard(pci.getPermitCard());
		this.match.getGamers().get(gamerBuyerIndex).addPermitCard(pci.getPermitCard());
		
		//Faccio lo scambio dei soldi
		this.match.getGamers().get(gamerSellerIndex).addCoins(pci.getPrice());
		this.match.getGamers().get(gamerBuyerIndex).subCoins(pci.getPrice());
		
		//Aggiorno il compratore ed il venditore
		this.match.getMarket().getAgents().set(sellerIndex, seller);
		this.match.getMarket().getAgents().set(buyerIndex, agent);
		
		MatchRepository.getInstance().updateMatch(this.match);
		
	}
	
	/**
	 * Metodo per la compravendita di carte politiche
	 * @param sellerIndex
	 * @param politicalCardItemIndex
	 * @throws MarketActionCommandException
	 * @throws GamerException
	 */
	public void buyPoliticalCardItem(int sellerIndex,int politicalCardItemIndex) throws MarketActionCommandException, GamerException{
		int gamerSellerIndex = -1;
		int gamerBuyerIndex = -1;
		if((sellerIndex < 0) || (sellerIndex >= this.match.getMarket().getAgents().size()))throw new MarketActionCommandException(MarketActionCommandExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((politicalCardItemIndex < 0) || (politicalCardItemIndex >= this.match.getMarket().getAgents().get(sellerIndex).getPoliticalCardStock().size()))throw new MarketActionCommandException(MarketActionCommandExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		Agent seller = this.match.getMarket().getAgents().get(sellerIndex);
		
		for(int i = 0; i < this.match.getGamers().size(); i++){
			if(this.match.getGamers().get(i).getUsername().equals(seller.getUsername())){
				gamerSellerIndex = i;
				break;
			}
		}
		
		for(int i = 0; i < this.match.getGamers().size(); i++){
			if(this.match.getGamers().get(i).getUsername().equals(agent.getUsername())){
				gamerBuyerIndex = i;
				break;
			}
		}
		
		if(gamerSellerIndex == -1) throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_NOT_FOUND.getExceptionCode());
		if(gamerBuyerIndex == -1) throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_NOT_FOUND.getExceptionCode());
		
		
		
		PoliticalCardItem pci = seller.subPoliticalCard(politicalCardItemIndex);
		
		//Controllo che il compratore abbia abbastanza soldi
		if(this.match.getGamers().get(gamerBuyerIndex).getCoins() < pci.getPrice()){
			seller.addPoliticalCardItem(pci);
			throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		}
		
		//Faccio lo scambio degli aiutanti
		this.match.getGamers().get(gamerSellerIndex).subPoliticalCard(pci.getPoliticalCard());
		this.match.getGamers().get(gamerBuyerIndex).addPoliticalCard(pci.getPoliticalCard());
		
		//Faccio lo scambio dei soldi
		this.match.getGamers().get(gamerSellerIndex).addCoins(pci.getPrice());
		this.match.getGamers().get(gamerBuyerIndex).subCoins(pci.getPrice());
		
		//Aggiorno il compratore ed il venditore
		this.match.getMarket().getAgents().set(sellerIndex, seller);
		this.match.getMarket().getAgents().set(buyerIndex, agent);
		
		MatchRepository.getInstance().updateMatch(this.match);
	}
	
	/**
	 * Metodo per la compravendita di aiutanti
	 * @param sellerIndex
	 * @param helpersItemIndex
	 * @throws MarketActionCommandException 
	 * @throws GamerException 
	 */
	public void buyHelpersItem(int sellerIndex,int helpersItemIndex) throws MarketActionCommandException, GamerException{
		int gamerSellerIndex = -1;
		int gamerBuyerIndex = -1;
		if((sellerIndex < 0) || (sellerIndex >= this.match.getMarket().getAgents().size()))throw new MarketActionCommandException(MarketActionCommandExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((helpersItemIndex < 0) || (helpersItemIndex >= this.match.getMarket().getAgents().get(sellerIndex).getHelpersStock().size()))throw new MarketActionCommandException(MarketActionCommandExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		Agent seller = this.match.getMarket().getAgents().get(sellerIndex);
		
		for(int i = 0; i < this.match.getGamers().size(); i++){
			if(this.match.getGamers().get(i).getUsername().equals(seller.getUsername())){
				gamerSellerIndex = i;
				break;
			}
		}
		
		for(int i = 0; i < this.match.getGamers().size(); i++){
			if(this.match.getGamers().get(i).getUsername().equals(agent.getUsername())){
				gamerBuyerIndex = i;
				break;
			}
		}
		
		if(gamerSellerIndex == -1) throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_NOT_FOUND.getExceptionCode());
		if(gamerBuyerIndex == -1) throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_NOT_FOUND.getExceptionCode());
		
		
		
		HelpersItem hi = seller.subHelpersItem(helpersItemIndex);
		
		//Controllo che il compratore abbia abbastanza soldi
		if(this.match.getGamers().get(gamerBuyerIndex).getCoins() < hi.getPrice()){
			seller.addHelpersItem(hi);
			throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		}
		
		//Controllo che il compratore non abbia troppi aiutanti
		if(this.match.getGamers().get(gamerBuyerIndex).getHelpers() + hi.getHelpers() > HelpersPoolConstants.MAX_HELPERS_NUMBER_FOR_GAMER){
			seller.addHelpersItem(hi);
			throw new MarketActionCommandException(MarketActionCommandExceptionCode.GAMER_HAS_TOO_MANY_HELPERS.getExceptionCode());
		}
		
		//Faccio lo scambio degli aiutanti
		this.match.getGamers().get(gamerSellerIndex).subHelpers(hi.getHelpers());
		this.match.getGamers().get(gamerBuyerIndex).addHelpers(hi.getHelpers());
		
		//Faccio lo scambio dei soldi
		this.match.getGamers().get(gamerSellerIndex).addCoins(hi.getPrice());
		this.match.getGamers().get(gamerBuyerIndex).subCoins(hi.getPrice());
		
		//Aggiorno il compratore ed il venditore
		this.match.getMarket().getAgents().set(sellerIndex, seller);
		this.match.getMarket().getAgents().set(buyerIndex, agent);
		
		MatchRepository.getInstance().updateMatch(this.match);
	}
	
	public Gamer getGamer(){ return match.getGamers().get(buyerIndex); }
	
	private void setAgent(Agent agent) throws MarketActionCommandException{ 
		if(agent == null)throw new MarketActionCommandException(MarketActionCommandExceptionCode.AGENT_NOT_FOUND.getExceptionCode());
		this.agent = agent; 
	}
	public Agent getAgent(){ return this.agent; }
}
