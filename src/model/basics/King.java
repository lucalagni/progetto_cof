package model.basics;

import java.io.Serializable;

import model.basics.constants.VillageConstants;
import model.basics.exceptions.KingException;
import model.basics.exceptions.codes.KingExceptionCode;

public class King implements Serializable{
	private static final long serialVersionUID = 1L;
	private Council council;
	private String position;
	private Bonus bonus[];
	
	public King(String position,Council council,Bonus[] bonus){
		this.setPosition(position);
		this.setCouncil(council);
		this.setBonus(bonus);
	}
	
	private void setPosition(String position) { this.position = position; }
	private void setCouncil(Council council) { this.council = council; }
	private void setBonus(Bonus[] bonus){ this.bonus = bonus; }
	
	public Council getCouncil() { return this.council; }
	public String getPosition() { return this.position; }
	public Bonus[] getBonus(){ return this.bonus; }
	
	public void moveKing(String newLocation) throws KingException{ 
		boolean flag = false ;
		for(String v: VillageConstants.VILLAGES_NAME){
			if(v.equals(newLocation)){
				flag = true;
				break;
			}
		}
		if(flag == false) throw new KingException(KingExceptionCode.PATH_NOT_AVAILABLE.getExceptionCode());
		this.setPosition(newLocation); 
	}
	
	@Override
	public String toString(){
		String kString = new String("\nking\n");
		
		kString += "council: " + this.getCouncil().toString() + "\n";
		kString += "position: "+ this.getPosition() + "\n";
		kString += "bonus: \n";
		for(Bonus b: bonus) kString += b.toString() + "\n";
		return kString;
	}
	
	
}
