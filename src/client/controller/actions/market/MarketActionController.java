package client.controller.actions.market;

import client.command.actions.basics.exceptions.ActionFacadeException;
import client.command.actions.market.MarketActionFacade;
import client.command.actions.market.exceptions.MarketActionFacadeException;

/**
 * Classe controller per le azioni del market
 * @author Luca Lagni
 *
 */
public class MarketActionController {
	private static MarketActionController instance = null;
	public static final String OK_MESSAGE = "MARKET_ACTION_CONTROLLER_PERFORMED_CORRECTLY";
	
	private MarketActionController(){
	}
	
	/**
	 * metodo di controllo per la compravendita di aiutanti
	 * @param seller
	 * @param helperIndex
	 * @return
	 */
	public String buyHelpersItem(int seller,int helperIndex){
		try {
			MarketActionFacade maf = new MarketActionFacade();
			return maf.buyHelpersItem(seller, helperIndex);
		} catch (ActionFacadeException | MarketActionFacadeException e) { return e.getMessage(); }
	}
	
	/**
	 * metodo di controllo per la compravendita di oggetti di tipo carte permesso
	 * @param seller
	 * @param permitCardIndex
	 * @return
	 */
	public String buyPermitCardItem(int seller,int permitCardIndex){
		try {
			MarketActionFacade maf = new MarketActionFacade();
			return maf.buyPermitCardItem(seller, permitCardIndex);
		} catch (ActionFacadeException | MarketActionFacadeException e) {
			return e.getMessage();
		}
	}
	
	/**
	 * metodo di controllo per la compravendita di oggetti di tipo carte politica
	 * @param seller
	 * @param permitCardIndex
	 * @return
	 */
	public String buyPoliticalCardItem(int seller,int politicalCardIndex){
		try {
			MarketActionFacade maf = new MarketActionFacade();
			return maf.buyPoliticalCardItem(seller, politicalCardIndex);
		} catch (ActionFacadeException | MarketActionFacadeException e) {
			return e.getMessage();
		}
	}
	
	public static MarketActionController getInstance(){
		if(instance == null) instance = new MarketActionController();
		return instance; 
	}
}
