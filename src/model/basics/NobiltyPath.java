package model.basics;

import java.io.Serializable;

import model.basics.exceptions.NobiltyPathException;
import model.basics.exceptions.codes.NobiltyPathExceptionCode;
import model.basics.constants.NobiltyPathConstants;

public class NobiltyPath implements Serializable{
	private static final long serialVersionUID = 1L;
	private Bonus[] bonus;
	
	public NobiltyPath(Bonus[] bonus){
		this.setBonus(bonus);
	}
	
	private void setBonus(Bonus[] bonus){ this.bonus =  bonus; }
	
	public Bonus getSingleBonus(int position) throws NobiltyPathException{
		if(position >= NobiltyPathConstants.TOTAL_TILES) throw new NobiltyPathException(NobiltyPathExceptionCode.INVALID_POSITION.getExceptionCode());
		
		return this.bonus[position];
	}
	
	public Bonus[] getBonus(){ return this.bonus; }
	
	@Override
	public String toString(){
		String npString = new String("\nNobilty Path\n");
		
		npString += "bonus: \n" ;
		for(Bonus b: this.getBonus()) npString += b.toString() + "\n";
		
		return npString;
	}
}