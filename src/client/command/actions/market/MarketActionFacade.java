package client.command.actions.market;

import client.command.actions.basics.exceptions.ActionFacadeException;
import client.command.actions.basics.exceptions.codes.ActionFacadeExceptionCode;
import client.command.actions.market.exceptions.MarketActionFacadeException;
import client.command.actions.market.exceptions.codes.MarketActionFacadeExceptionCode;
import client.controller.ControllerRepository;
import commons.data.GameMode;
import commons.data.UserData;

/**
 * Classe per l'accesso unificato alle azioni del market
 * @author Luca Lagni
 *
 */
public class MarketActionFacade {
	private UserData data;
	private GameMode mode;
	
	public MarketActionFacade() throws ActionFacadeException{
		this.setUserData(ControllerRepository.getInstance().getGameDataController().getUserData());
		this.setGameMode(ControllerRepository.getInstance().getClientController().getClient().getGameMode());
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
	 * Metodo per l'acquisizione di un item di tipo aiutante
	 * @param sellerIndex
	 * @param helpersItemIndex
	 * @throws MarketActionFacadeException
	 */
	public String buyHelpersItem(int sellerIndex,int helpersItemIndex) throws MarketActionFacadeException{
		if((sellerIndex < 0) || (sellerIndex >= this.data.getMatch().getMarket().getAgents().size())) throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((helpersItemIndex < 0) || (helpersItemIndex >= this.data.getMatch().getMarket().getAgents().get(sellerIndex).getHelpersStock().size()))throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		if(this.data.getGamer().getCoins() < this.data.getMatch().getMarket().getAgents().get(sellerIndex).getHelpersStock().get(helpersItemIndex).getPrice())throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new MarketActionEncoderSocket().buyHelpersItem(sellerIndex, helpersItemIndex);
		}
		if(this.mode == GameMode.RMI){
			return new MarketActionEncoderRmi().buyHelpersItem(sellerIndex, helpersItemIndex);
		}
		
		return new String("GAME_MODE_NOT_AVAILABLE");
	}
	
	/**
	 * Metodo per la richiesta di acquisizione di una carta permesso di tipo item
	 * @param sellerIndex
	 * @param politicalCardItemIndex
	 * @return
	 * @throws MarketActionFacadeException
	 */
	public String buyPoliticalCardItem(int sellerIndex,int politicalCardItemIndex) throws MarketActionFacadeException{
		if((sellerIndex < 0) || (sellerIndex >= this.data.getMatch().getMarket().getAgents().size())) throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((politicalCardItemIndex < 0) || (politicalCardItemIndex >= this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPoliticalCardStock().size()))throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		if(this.data.getGamer().getCoins() < this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPoliticalCardStock().get(politicalCardItemIndex).getPrice())throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new MarketActionEncoderSocket().buyPoliticalCardItem(sellerIndex, politicalCardItemIndex);
		}
		if(this.mode == GameMode.RMI){
			return new MarketActionEncoderRmi().buyPoliticalCardItem(sellerIndex, politicalCardItemIndex);
		}
		
		return new String("GAME_MODE_NOT_AVAILABLE");
	}
	
	/**
	 * Metodo per l'acquisizione di una carta permesso item
	 * @param sellerIndex
	 * @param permitCardItemIndex
	 * @return
	 * @throws MarketActionFacadeException
	 */
	public String buyPermitCardItem(int sellerIndex,int permitCardItemIndex) throws MarketActionFacadeException{
		if((sellerIndex < 0) || (sellerIndex >= this.data.getMatch().getMarket().getAgents().size())) throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((permitCardItemIndex < 0) || (permitCardItemIndex >= this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPermitCardStock().size()))throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		if(this.data.getGamer().getCoins() < this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPermitCardStock().get(permitCardItemIndex).getPrice())throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new MarketActionEncoderSocket().buyPermitCardItem(sellerIndex, permitCardItemIndex);
		}
		if(this.mode == GameMode.RMI){
			return new MarketActionEncoderRmi().buyPermitCardItem(sellerIndex, permitCardItemIndex);
		}
		return new String("GAME_MODE_NOT_AVAILABLE");
	}
}
