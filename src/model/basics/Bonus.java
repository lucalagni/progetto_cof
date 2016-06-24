package model.basics;

import java.io.Serializable;

public class Bonus implements Serializable {	 
	private static final long serialVersionUID = 1L;
	
	private Boolean available;
	private Integer coins ;
	private Integer helpers; 
	private Integer politicalCards ; 
	private Integer points;   			  			 
	private Integer shifts;  			 			 
	private Boolean newMainAction;	 
	private Boolean reusePermitBonus ;
	private Boolean acquirePermitCard;
	private Boolean acquireSingleVillageBonus;
	private Boolean acquireDoubleVillageBonus;
	
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
	
	private void setAvailable(boolean available){this.available = new Boolean(available);}
	private void setAcquirePermitCard(boolean acquirePermitCard){ this.acquirePermitCard = new Boolean(acquirePermitCard); }
	private void setCoins(int coins) { this.coins = new Integer(coins); }
	private void setPoints(int points){ this.points = new Integer(points); }
	private void setShifts(int shifts){ this.shifts = new Integer(shifts); }
	private void setPoliticalCards(int politicalCards){ this.politicalCards = new Integer(politicalCards); }
	private void setHelpers(int helpers){ this.helpers = new Integer(politicalCards); }
	private void setNewMainAction(boolean newMainAction){ this.newMainAction = new Boolean(newMainAction); }
	private void setReusePermitBonus(boolean reusePermitBonus){ this.reusePermitBonus = new Boolean(reusePermitBonus); }
	private void setAcquireSingleVillageBonus(boolean acquireSingleVillageBonus){ this.acquireSingleVillageBonus = new Boolean(acquireSingleVillageBonus); }
	private void setAcquireDoubleVillageBonus(boolean acquireDoubleVillageBonus){ this.acquireDoubleVillageBonus = new Boolean(acquireDoubleVillageBonus); }
	
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
	
	public int getCoins(){ return this.coins.intValue(); }
	public int getPoints(){ return this.points.intValue(); }
	public int getShifts(){ return this.shifts.intValue(); }
	public int getPoliticalCards(){ return this.politicalCards.intValue(); }
	public int getHelpers(){ return this.helpers.intValue(); }
	public boolean getNewMainAction(){ return this.newMainAction.booleanValue(); }
	public boolean getReusePermitBonus(){ return this.reusePermitBonus.booleanValue(); }
	public boolean getAcquirePermitCard(){ return this.acquirePermitCard.booleanValue(); }
	public boolean getAcquireSingleVillageBonus(){ return this.acquireSingleVillageBonus.booleanValue(); }
	public boolean getAcquireDoubleVillageBonus(){ return this.acquireDoubleVillageBonus.booleanValue(); }
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