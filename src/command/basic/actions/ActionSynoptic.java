package command.basic.actions;

import java.io.Serializable;

import command.basic.actions.constants.ActionSynopticConstants;

/**
 * Classe che definisce le azioni eseguibili da un determinato utente 
 * @author Luca Lagni
 *
 */
public class ActionSynoptic implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username ;
	private String matchCode ;
	private Integer mainActionNumber ;
	private Integer helpersActionNumber ;
	private Integer reusePermitCardBonusNumber;
	private Integer acquirePermitCardNumber;
	private Integer acquireSingleVillageBonusNumber;
	private Integer acquireDoubleVillageBonusNumber;
	
	public ActionSynoptic(String username,String matchCode){
		this.setUsername(username);
		this.setMatchCode(matchCode);
		this.setMainActionNumber(ActionSynopticConstants.INITIAL_MAIN_ACTIONS);
		this.setHelpersActionNumber(ActionSynopticConstants.INITIAL_HELPERS_ACTIONS);
		this.setReusePermitCardBonusNumber(ActionSynopticConstants.INITIAL_SPECIAL_ACTIONS);
		this.setAcquirePermitCardNumber(ActionSynopticConstants.INITIAL_SPECIAL_ACTIONS);
		this.setAcquireSingleVillageBonusNumber(ActionSynopticConstants.INITIAL_SPECIAL_ACTIONS);
		this.setAcquireDoubleVillageBonusNumber(ActionSynopticConstants.INITIAL_SPECIAL_ACTIONS);
	}
	
	private void setUsername(String username){ this.username = username; }
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setMainActionNumber(int mainActionNumber){ this.mainActionNumber = new Integer(mainActionNumber); }
	private void setHelpersActionNumber(int helpersActionNumber){ this.helpersActionNumber = new Integer(helpersActionNumber); }
	private void setReusePermitCardBonusNumber(int reusePermitCardBonus){ this.reusePermitCardBonusNumber = new Integer(reusePermitCardBonus); }
	private void setAcquirePermitCardNumber(int acquirePermitCard){this.acquirePermitCardNumber = new Integer(acquirePermitCard); }
	private void setAcquireSingleVillageBonusNumber(int acquireSingleVillageBonus){ this.acquireSingleVillageBonusNumber = new Integer(acquireSingleVillageBonus); }
	private void setAcquireDoubleVillageBonusNumber(int acquireDoubleVillageBonus){ this.acquireDoubleVillageBonusNumber = new Integer(acquireDoubleVillageBonus); }
	
	public void useMainAction(){
		if(this.getMainActionNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setMainActionNumber(this.getMainActionNumber() - 1);
	}
	
	public void useHelpersAction(){
		if(this.getHelpersActionNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setHelpersActionNumber(this.getHelpersActionNumber() - 1);
	}
	
	public void useReusePermitrCardBonus(){
		if(this.getReusePermitCardBonusNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return;
		this.setReusePermitCardBonusNumber(this.getReusePermitCardBonusNumber() - 1);
	}
	
	public void useAcquirePermitCard(){
		if(this.getAcquirePermitCardNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setAcquirePermitCardNumber(this.getAcquirePermitCardNumber() - 1);
	}
	
	public void useAcquireSingleVillageBonus(){
		if(this.getAcquireSingleVillageBonusNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setAcquireSingleVillageBonusNumber(this.getAcquireSingleVillageBonusNumber() - 1);
	}
	
	public void useAcquireDoubleVillageBonus(){
		if(this.getAcquireDoubleVillageBonusNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return;
		this.setAcquireDoubleVillageBonusNumber(this.getAcquireDoubleVillageBonusNumber() - 1);
	}
	
	public void addMainAction(){
		this.setMainActionNumber(this.getMainActionNumber() + 1);
	}
	
	public void addHelpersAction(){
		this.setHelpersActionNumber(this.getHelpersActionNumber() + 1);
	}
	
	public void addReusePermitrCardBonus(){
		this.setReusePermitCardBonusNumber(this.getReusePermitCardBonusNumber() + 1);
	}
	
	public void addAcquirePermitCard(){
		this.setAcquirePermitCardNumber(this.getAcquirePermitCardNumber() + 1);
	}
	
	public void addAcquireSingleVillageBonus(){
		this.setAcquireSingleVillageBonusNumber(this.getAcquireSingleVillageBonusNumber() + 1);
	}
	
	public void addAcquireDoubleVillageBonus(){
		this.setAcquireDoubleVillageBonusNumber(this.getAcquireDoubleVillageBonusNumber() + 1);
	}
	
	public String getUsername(){ return this.username; }
	public String getMatchCode(){ return this.matchCode; }
	public int getMainActionNumber(){ return this.mainActionNumber.intValue(); }
	public int getHelpersActionNumber(){ return this.helpersActionNumber.intValue(); }
	public int getReusePermitCardBonusNumber(){ return this.reusePermitCardBonusNumber.intValue(); }
	public int getAcquirePermitCardNumber(){ return this.acquirePermitCardNumber.intValue(); }
	public int getAcquireSingleVillageBonusNumber(){ return this.acquireSingleVillageBonusNumber.intValue(); }
	public int getAcquireDoubleVillageBonusNumber(){ return this.acquireDoubleVillageBonusNumber.intValue(); }
	
	@Override
	public String toString(){
		String s = "\nActionSynoptic\n";
		
		s += "username: " + this.getUsername() + "\n";
		s += "matchCode: " + this.getMatchCode() + "\n";
		s += "main action number: " + this.getMainActionNumber() + "\n";
		s += "helpers action number: " + this.getHelpersActionNumber() + "\n";
		s += "reuse permit card bonus number: " + this.getReusePermitCardBonusNumber() + "\n";
		s += "acquire permit card number: " + this.getAcquirePermitCardNumber() + "\n";
		s += "acquire single village bonus number: " + this.getAcquireSingleVillageBonusNumber() + "\n";
		s += "acquire double village bonus number: " + this.getAcquireDoubleVillageBonusNumber() ;
		
		return s;
	}
}
