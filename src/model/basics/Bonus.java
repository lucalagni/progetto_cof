package model.basics;

import java.io.Serializable;

import mud.model.basic.interfaces.RemoteBonus;

public class Bonus implements RemoteBonus, Serializable {	 
	private static final long serialVersionUID = 1L;
	
	private boolean available;
	private int coins ;
	private int helpers; 
	private int politicalCards ; 
	private int points;   			  			 
	private int shifts;  			 			 
	private boolean newMainAction;	 
	private boolean reusePermitBonus ;
	private boolean acquirePermitCard;
	private boolean acquireSingleVillageBonus;
	private boolean acquireDoubleVillageBonus;
	
	public Bonus(boolean available, int coins,int helpers, int politicalCards, int points,int shifts,boolean newMainAction,boolean reusePermitBonus,boolean acquireSingleVillageBonus,boolean acquireDoubleVillageBonus,boolean acquirePermitCard) {
		
		this.setCoins(coins);
		this.setPoints(points);
		this.setShifts(shifts);
		this.setPoliticalCards(politicalCards);
		this.setHelpers(helpers);
		this.setNewMainAction(newMainAction);
		this.setReusePermitBonus(reusePermitBonus);
		this.setAcquirePermitCard(acquirePermitCard);
		this.setAcquireSingleVillageBonus(acquireSingleVillageBonus);
		this.setAcquireDoubleVillageBonus(acquireDoubleVillageBonus);
		this.setAvailable(available);
	}
	
	private void setAvailable(boolean available){this.available = available;}
	private void setAcquirePermitCard(boolean acquirePermitCard){ this.acquirePermitCard = acquirePermitCard; }
	private void setCoins(int coins) { this.coins = coins; }
	private void setPoints(int points){ this.points = points; }
	private void setShifts(int shifts){ this.shifts = shifts; }
	private void setPoliticalCards(int politicalCards){ this.politicalCards = politicalCards; }
	private void setHelpers(int helpers){ this.helpers = helpers; }
	private void setNewMainAction(boolean newMainAction){ this.newMainAction = newMainAction; }
	private void setReusePermitBonus(boolean reusePermitBonus){ this.reusePermitBonus = reusePermitBonus; }
	private void setAcquireSingleVillageBonus(boolean acquireSingleVillageBonus){ this.acquireSingleVillageBonus = acquireSingleVillageBonus; }
	private void setAcquireDoubleVillageBonus(boolean acquireDoubleVillageBonus){ this.acquireDoubleVillageBonus = acquireDoubleVillageBonus; }
	
	public static synchronized void useBonus(Bonus bonus){
		bonus.setAvailable(false);
		bonus.setCoins(0);
		bonus.setHelpers(0);
		bonus.setPoints(0);
		bonus.setShifts(0);
		bonus.setPoliticalCards(0);
		bonus.setHelpers(0);
		bonus.setNewMainAction(false);
		bonus.setReusePermitBonus(false);
		bonus.setAcquirePermitCard(false);
		bonus.setAcquireSingleVillageBonus(false);
		bonus.setAcquireDoubleVillageBonus(false);
	}
	
	public int getCoins(){ return this.coins; }
	public int getPoints(){ return this.points; }
	public int getShifts(){ return this.shifts; }
	public int getPoliticalCards(){ return this.politicalCards; }
	public int getHelpers(){ return this.helpers; }
	public boolean getNewMainAction(){ return this.newMainAction; }
	public boolean getReusePermitBonus(){ return this.reusePermitBonus; }
	public boolean getAcquirePermitCard(){ return this.acquirePermitCard; }
	public boolean getAcquireSingleVillageBonus(){ return this.acquireSingleVillageBonus; }
	public boolean getAcquireDoubleVillageBonus(){ return this.acquireDoubleVillageBonus; }
	public boolean getAvailable(){ return this.available; }
	
	@Override
	public String toString(){
		//String bonusString = new String("\nBonus \n");
	    String bonusString = new String("");
		
	    bonusString += "available : " + this.getAvailable()		          + "\n";
		bonusString += "coins : " + this.getCoins()     		          + "\n";
		bonusString += "helpers : " + this.getHelpers()   		          + "\n";
		bonusString += "political cards : " + this.getPoliticalCards()     		  + "\n";
		bonusString += "points : " + this.getPoints()    		          + "\n";
		bonusString += "shifts : " + this.getShifts()    		          + "\n";
		bonusString += "new main action  : " + this.getNewMainAction()	          + "\n";
		bonusString += "reuse permit card bonus : " + this.getReusePermitBonus()          + "\n";
		bonusString += "acquire permit card : " + this.getAcquirePermitCard()         + "\n";
		bonusString += "acquire single village bonus (no shift)	: " + this.getAcquireSingleVillageBonus() + "\n";
		bonusString += "acquire double village bonus (no shift)	: " + this.getAcquireDoubleVillageBonus() + "\n";
		
		return bonusString;
	}
}