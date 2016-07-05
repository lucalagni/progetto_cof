package client.controller.actions.setter;

import client.command.actions.basics.exceptions.ActionFacadeException;
import client.command.actions.setter.SetterActionFacade;
import client.command.actions.setter.exceptions.SetterActionFacadeException;

/**
 * Classe controller per la gestione delle mosse per il settaggio del market
 * @author Luca Lagni
 *
 */
public class SetterActionController {
	private static final String OK_MESSAGE ="SETTER_ACTION_CONTROLLER_PERFORMED_CORRECTLY";
	private static SetterActionController instance = null;
	private SetterActionFacade saf;
	
	private SetterActionController(){
		try {
			this.saf = new SetterActionFacade();
		} catch (ActionFacadeException e) { e.printStackTrace(); }
	}
	
	public String resetAgent(){
		this.saf.resetAgent();
		
		return OK_MESSAGE;
	}
	
	public String addHelpersItem(int helpers,int price){
		try {
			this.saf.addHelpersItem(helpers, price);
		} catch (SetterActionFacadeException e) { return e.getMessage(); }
		
		return OK_MESSAGE;
	}
	
	public String addPoliticalCardItem(int politicalCardIndex,int price){
		try {
			this.saf.addPoliticalCardItem(politicalCardIndex, price);
		} catch (SetterActionFacadeException e) { return e.getMessage(); }
		
		return OK_MESSAGE;
	}
	
	public String addPermitCardItem(int permitCardIndex, int price){
		try {
			this.saf.addPermitCardItem(permitCardIndex, price);
		} catch (SetterActionFacadeException e) { return e.getMessage();}
		
		return OK_MESSAGE ;
	}
	
	public String sendAgent(){
		this.saf.sendAgent();
		
		return OK_MESSAGE;
	}
	
	public static SetterActionController getInstance(){
		if(instance == null) instance = new SetterActionController();
		return instance ;
	}
	
	public SetterActionFacade getSetterActionFacade(){ return this.saf; }
}
