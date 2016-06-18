package model.basics;

import java.io.Serializable;

import model.basics.constants.RegionConstants;

public class Region implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int number;
	private Bonus bonus;
	private Council council;
	private String[] villages;
	private PermitCardsDeck permitCardsDeck;
	
	public Region(int number,Bonus bonus,Council council,String[] villages,PermitCardsDeck permitCardsDeck){
		this.setNumber(number);
		this.setBonus(bonus);
		this.setCouncil(council);
		this.setVillages(villages);
		this.setPermitCardDeck(permitCardsDeck);
	}
	
	public Region(int number,Bonus bonus,Council council,PermitCardsDeck permitCardsDeck){
		this.setNumber(number);
		this.setBonus(bonus);
		this.setCouncil(council);
		this.initVillages();
		this.setPermitCardDeck(permitCardsDeck);
	}
	
	private void initVillages(){
		switch(this.getNumber()){
			case 1:
				this.setVillages(RegionConstants.VILLAGES_REGION_1);
				break;
			case 2:
				this.setVillages(RegionConstants.VILLAGES_REGION_2);
				break;
			case 3:
				this.setVillages(RegionConstants.VILLAGES_REGION_3);
				break;
			default:
				break;
		}
	}
	
	private void setNumber(int number) { this.number = number; }
	private void setBonus(Bonus bonus) { this.bonus = bonus; }
	private void setCouncil(Council council) { this.council = council; }
	private void setVillages(String[] villages) { this.villages = villages; }
	private void setPermitCardDeck(PermitCardsDeck permitCardsDeck) { this.permitCardsDeck = permitCardsDeck; }
	
	public int getNumber() { return this.number; }
	public Bonus getBonus() { return this.bonus; }
	public Council getCouncil() { return this.council; }
	public String[] getVillages() { return this.villages; }
	public PermitCardsDeck getPermitCardsDeck() { return this.permitCardsDeck; }
	
	@Override
	public String toString(){
		String rString = new String("\nregion\n");
		
		rString += "number:  " + this.getNumber() + "\n";
		rString += "bonus:   " + this.getBonus() + "\n";
		rString += "council: " + this.council.toString() + "\n";
		rString += "villages: " ;
		for(int i = 0; i < this.getVillages().length; i++) rString += this.villages[i] + " ";
		rString += "\n";
		rString += "permit cards deck: " + this.getPermitCardsDeck().toString() + "\n";
		
		return rString;
	}
	
}