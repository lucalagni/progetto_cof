package client.controller.actions.basics;

import java.awt.Color;
import java.util.ArrayList;

import command.client.actions.ActionFacade;
import command.client.actions.exceptions.ActionFacadeException;

/**
 * Classe controller per la gestione delle mosse afferenti al gioco base
 * 
 * @author Luca Lagni
 *
 */
public class ActionController {
	private static ActionController instance = null;
	public static final String OK_MESSAGE = "ACTION_CONTROLLER_OPERATION_PERFORMED_CORRECTLY";
	
	private ActionController(){}
	
	/**
	 * Metodo per l'esecuzione della mossa di movimento del re
	 * @param path
	 * @throws ActionFacadeException
	 */
	public String moveKing(ArrayList<String> path,int politicalCardsPosition[]) {
		try {
			ActionFacade af = new ActionFacade();
			af.moveKing(path, politicalCardsPosition);
			return OK_MESSAGE ;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	}
	
	/**
	 * Metodo per l'esecuzione della mossa eleggi consigliere
	 * @param king
	 * @param regionNumber
	 * @param noble
	 * @param mainAction indica se è un'azione primaria o secondaria
	 * @throws ActionFacadeException
	 */
	public String changeNoble(boolean king,int regionNumber,Color noble, boolean mainAction) {
			try {
				ActionFacade af = new ActionFacade();
				af.changeNoble(king, regionNumber, noble, mainAction);
				return OK_MESSAGE;
			} catch (ActionFacadeException e) {
				return e.getMessage().toString();
			}
		
	}
	
	/**
	 * Metodo per l'esecuzione della mossa acquista carta permesso 
	 * @param regionNumber
	 * @param permitCardNumber
	 * @param politicalCardsIndex
	 * @throws ActionFacadeException
	 */
	public String buyPermitCard(int regionNumber,int permitCardNumber,int[] politicalCardsIndex) {
		try {
			ActionFacade af = new ActionFacade();
			af.buyPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	}
	
	/**
	 * Metodo per l'esecuzione della mossa acquista piazza emporio 
	 * @param village
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public String placeShop(char village,int permitCardIndex){
		try {
			ActionFacade af = new ActionFacade();
			af.placeShop(village, permitCardIndex);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	
	/**
	 * Metodo per l'esecuzione della mossa acquista aiutante
	 * @param queque
	 * @throws ActionFacadeException
	 */
	public String buyHelper(boolean queque) {
		try {
			ActionFacade af = new ActionFacade();
			af.buyHelper(queque);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	
	/**
	 * Metodo per l'esecuzione della mossa acquista compra nuova azione ( azione secondaria )
	 * @throws ActionFacadeException
	 */
	public String buyNewMainAction() {
		try {
			ActionFacade af = new ActionFacade();
			af.buyNewMainAction();
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	
	/**
	 * Metodo che esegue la doppia azione di rimmettere delle carte permesso nel mazzo e rimescolare 
	 * 
	 * @param regionNumber
	 * @throws ActionFacadeException
	 */
	public String doubleAction(int regionNumber) {
		try {
			ActionFacade af = new ActionFacade();
			af.doubleAction(regionNumber);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	/**
	 * Metodo che consente di acquisire una carta permesso senza pagare
	 * @param regionNumber
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public String acquirePermitCard(int regionNumber,int permitCardIndex) {
		try {
			ActionFacade af = new ActionFacade();
			af.acquirePermitCard(regionNumber, permitCardIndex);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	
	/**
	 * Metodo che consente di acquisire il bonus di un villaggio in cui si e' costruito in precedenza  no shift
	 * @param villageFirstLetter
	 * @throws ActionFacadeException
	 */
	public String acquireSingleVillageBonus(char villageFirstLetter){
		try {
			ActionFacade af = new ActionFacade();
			af.acquireSingleVillageBonus(villageFirstLetter);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	
	/**
	 * Metodo che consente di acquisire il bonus di due villaggio in cui si e' costruito in precedenza no shift
	 * @param villageFirstLetter
	 * @throws ActionFacadeException
	 */
	public String acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter) {
		try {
			ActionFacade af = new ActionFacade();
			af.acquireDoubleVillageBonus(firstVillageFirstLetter, secondVillageFirstLetter);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	
	/**
	 * Metodo che consente di riutilizzare il bonus di una carta permesso
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public String reusePermitCardBonus(int permitCardIndex){
		try {
			ActionFacade af = new ActionFacade();
			af.reusePermitCardBonus(permitCardIndex);
			return OK_MESSAGE;
		} catch (ActionFacadeException e) {
			return e.getMessage().toString();
		}
	} 
	
	public static ActionController getInstance(){
		if(instance == null) instance = new ActionController();
		return instance;
	}
}
