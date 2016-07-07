package client.command.actions.setter;

import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.constants.CoinsPoolConstants;
import model.market.Agent;
import model.market.PoliticalCardItem;
import model.market.PermitCardItem;
import model.market.HelpersItem;
import client.command.actions.basics.exceptions.ActionFacadeException;
import client.command.actions.basics.exceptions.codes.ActionFacadeExceptionCode;
import client.command.actions.setter.exceptions.SetterActionFacadeException;
import client.command.actions.setter.exceptions.codes.SetterActionFacadeExceptionCode;
import client.controller.ControllerRepository;
import commons.data.GameMode;
import commons.data.UserData;

public class SetterActionFacade {
	private UserData data;
	private GameMode mode;
	private Agent agent;
	
	public SetterActionFacade() throws ActionFacadeException{
		this.setUserData(ControllerRepository.getInstance().getGameDataController().getUserData());
		this.setGameMode(ControllerRepository.getInstance().getClientController().getClient().getGameMode());
		this.setAgent(this.data);
	}
	private void setUserData(UserData data) throws ActionFacadeException{ 
		if(data == null) throw new ActionFacadeException(ActionFacadeExceptionCode.UNSETTED_USER_DATA.getExceptionCode());
		this.data = data; 
	}
	private void setGameMode(GameMode mode) throws ActionFacadeException{ 
		if(mode == null) throw new ActionFacadeException(ActionFacadeExceptionCode.UNSETTED_GAME_MODE.getExceptionCode());
		this.mode = mode; 
	}
	
	/**
	 * Metodo per il settaggio dell'agent
	 * @param data
	 */
	private void setAgent(UserData data){
		String username = data.getUsername();
		
		System.out.println("size: " +data.getMatch().toString());
		for(int i = 0; i < data.getMatch().getMarket().getAgents().size(); i++){
			if(data.getMatch().getMarket().getAgents().get(i).getUsername().equals(username)) {
				this.agent = data.getMatch().getMarket().getAgents().get(i);
				break;
			}
		}
	}
	
	/**
	 * metodo per l'azzeramento degli oggetti di vendita di un certo giocatore
	 */
	public void resetAgent(){ this.agent.resetAgent(); }
	
	/**
	 * Metodo per l'aggiunta di carte politiche da vendere
	 */
	public void addPoliticalCardItem(int politicalCardIndex, int price) throws SetterActionFacadeException{ 
		Gamer g = data.getGamer();
		PoliticalCard pc = null;
		PoliticalCardItem pci = null;
		
		for(int i = 0; i < this.agent.getPoliticalCardStock().size(); i++){
			if(politicalCardIndex == this.agent.getPoliticalCardStock().get(i).getPosition()){
				throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.GAMER_CANNOT_SELL_THAT_POLITICAL_CARD.getExceptionCode());
			}
		}
		
		if((politicalCardIndex < 0) || (politicalCardIndex >= g.getPoliticalCards().size()))
		{
			throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.GAMER_CANNOT_SELL_THAT_POLITICAL_CARD.getExceptionCode());
		}
		
		if((price < 0) || (price > CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER)){
			throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.INVALID_PRICE.getExceptionCode());
		}
		
		pc = g.getPoliticalCards().get(politicalCardIndex);
		pci = new PoliticalCardItem(pc, price, politicalCardIndex);
		
		this.agent.addPoliticalCardItem(pci); 
	}
	
	/**
	 * Metodo per l'aggiunta di carte permesso da vendere
	 */
	public void addPermitCardItem(int permitCardIndex, int price) throws SetterActionFacadeException{ 
		Gamer g = data.getGamer();
		PermitCard pc = null;
		PermitCardItem pci = null;
		
		for(int i = 0; i < this.agent.getPermitCardStock().size(); i++){
			if(permitCardIndex == this.agent.getPermitCardStock().get(i).getPosition()){
				throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.GAMER_CANNOT_SELL_THAT_PERMIT_CARD.getExceptionCode());
			}
		}
		
		if((permitCardIndex < 0) || (permitCardIndex >= g.getUnusedPermitCards().size()))
		{
			throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.GAMER_CANNOT_SELL_THAT_PERMIT_CARD.getExceptionCode());
		}
		
		if((price < 0) || (price > CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER)){
			throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.INVALID_PRICE.getExceptionCode());
		}
		
		pc = g.getUnusedPermitCards().get(permitCardIndex);
		pci = new PermitCardItem(pc, price, permitCardIndex);
		
		this.agent.addPermitCardItem(pci); 
	}
	
	/**
	 * Metodo per l'aggiunta di aiutanti
	 */
	public void addHelpersItem(int helpers, int price) throws SetterActionFacadeException{ 
		Gamer g = data.getGamer();
		HelpersItem hi = null;
		int amount = 0;
		
		if(helpers <= 0) throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.GAMER_CANNOT_SELL_THAT_QUANTITY_OF_HELPERS.getExceptionCode());
		
		for(int i = 0; i < this.agent.getHelpersStock().size(); i++) amount += this.agent.getHelpersStock().get(i).getHelpers();
		
		amount += helpers;
		if(amount > g.getHelpers()) throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.GAMER_CANNOT_SELL_THAT_QUANTITY_OF_HELPERS.getExceptionCode());
		
		if((price < 0) || (price > CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER)){
			throw new SetterActionFacadeException(SetterActionFacadeExceptionCode.INVALID_PRICE.getExceptionCode());
		}
		
		hi = new HelpersItem(helpers,price);
		this.agent.addHelpersItem(hi);
		System.out.println(this.agent.getHelpersStock().get(0).toString());
	}
	
	/**
	 * Metodo per l'invio dei dati dell'agente al server
	 */
	public String sendAgent(){
		String username = data.getUsername();
		
		for(int i = 0; i < data.getMatch().getMarket().getAgents().size(); i++){
			if(data.getMatch().getMarket().getAgents().get(i).getUsername().equals(username)) {
				this.data.getMatch().getMarket().getAgents().set(i, this.agent);
				break;
			}
		}
		
		if(this.mode == GameMode.SOCKET){
			return new SetterActionSocket().sendAgent(this.data);
		}
		if(this.mode == GameMode.RMI){
			return new SetterActionRmi().sendAgent(this.data);
		}
		
		return new String("INVALID_GAME_MODE");
	}
	
	public UserData getData(){ return this.data; }
	public GameMode getGameMode(){ return this.mode; }
	public Agent getAgent(){ return this.agent; }
}
