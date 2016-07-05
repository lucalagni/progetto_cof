package client.controller.actions.market;

import client.command.actions.basics.exceptions.ActionFacadeException;
import client.command.actions.market.MarketActionFacade;
import client.command.actions.market.exceptions.MarketActionFacadeException;

/**
 * Classe controller per le azioni del market
 * @author lucal
 *
 */
public class MarketActionController {
	private static MarketActionController instance = null;
	public static final String OK_MESSAGE = "MARKET_ACTION_CONTROLLER_PERFORMED_CORRECTLY";
	
	private MarketActionController(){
	}
	
	public String buyHelpersItem(int seller,int helperIndex){
		try {
			MarketActionFacade maf = new MarketActionFacade();
			maf.buyHelpersItem(seller, helperIndex);
			return OK_MESSAGE ;
		} catch (ActionFacadeException | MarketActionFacadeException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String buyPermitCardItem(int seller,int permitCardIndex){
		try {
			MarketActionFacade maf = new MarketActionFacade();
			maf.buyPermitCardItem(seller, permitCardIndex);
			return OK_MESSAGE ;
		} catch (ActionFacadeException | MarketActionFacadeException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String buyPoliticalCardItem(int seller,int politicalCardIndex){
		try {
			MarketActionFacade maf = new MarketActionFacade();
			maf.buyPoliticalCardItem(seller, politicalCardIndex);
			return OK_MESSAGE ;
		} catch (ActionFacadeException | MarketActionFacadeException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public static MarketActionController getInstance(){
		if(instance == null) instance = new MarketActionController();
		return instance; 
	}
}
