package client.command.actions.market;

import client.command.actions.basics.exceptions.ActionFacadeException;
import client.command.actions.basics.exceptions.codes.ActionFacadeExceptionCode;
import client.command.actions.market.exceptions.MarketActionFacadeException;
import client.command.actions.market.exceptions.codes.MarketActionFacadeExceptionCode;
import client.controller.ControllerRepository;
import commons.data.GameMode;
import commons.data.UserData;

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
	
	public void buyHelpersItem(int sellerIndex,int helpersItemIndex) throws MarketActionFacadeException{
		if((sellerIndex < 0) || (sellerIndex >= this.data.getMatch().getMarket().getAgents().size())) throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((helpersItemIndex < 0) || (helpersItemIndex >= this.data.getMatch().getMarket().getAgents().get(sellerIndex).getHelpersStock().size()))throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		if(this.data.getGamer().getCoins() < this.data.getMatch().getMarket().getAgents().get(sellerIndex).getHelpersStock().get(helpersItemIndex).getPrice())throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new MarketActionEncoderSocket().buyHelpersItem(sellerIndex, helpersItemIndex);
		}
	}
	
	public void buyPoliticalCardItem(int sellerIndex,int politicalCardItemIndex) throws MarketActionFacadeException{
		if((sellerIndex < 0) || (sellerIndex >= this.data.getMatch().getMarket().getAgents().size())) throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((politicalCardItemIndex < 0) || (politicalCardItemIndex >= this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPoliticalCardStock().size()))throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		if(this.data.getGamer().getCoins() < this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPoliticalCardStock().get(politicalCardItemIndex).getPrice())throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new MarketActionEncoderSocket().buyPoliticalCardItem(sellerIndex, politicalCardItemIndex);
		}
	}
	
	public void buyPermitCardItem(int sellerIndex,int permitCardItemIndex) throws MarketActionFacadeException{
		if((sellerIndex < 0) || (sellerIndex >= this.data.getMatch().getMarket().getAgents().size())) throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_SELLER_INDEX.getExceptionCode());
		if((permitCardItemIndex < 0) || (permitCardItemIndex >= this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPermitCardStock().size()))throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.INVALID_ITEM_INDEX.getExceptionCode());
		
		if(this.data.getGamer().getCoins() < this.data.getMatch().getMarket().getAgents().get(sellerIndex).getPermitCardStock().get(permitCardItemIndex).getPrice())throw new MarketActionFacadeException(MarketActionFacadeExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new MarketActionEncoderSocket().buyPermitCardItem(sellerIndex, permitCardItemIndex);
		}
	}
}
