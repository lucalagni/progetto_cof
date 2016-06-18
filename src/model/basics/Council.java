package model.basics;

import java.awt.Color;
import java.io.Serializable;

import model.basics.constants.CouncilConstants;
import model.basics.exceptions.CouncilException;
import model.basics.exceptions.codes.CouncilExceptionCode;
import model.basics.constants.ColorConstants;

public class Council implements Serializable {
	private static final long serialVersionUID = 1L;
	private Color[] nobles;
	
	public Council(Color[] nobles) {
		this.setNobles(nobles);
	}
	
	private void setNobles(Color[] nobles){ this.nobles = nobles; }
	
	public boolean checkSingleColorConsistence(Color noble){
		boolean flag = false ;
		for(Color c: ColorConstants.POLITICAL_COLORS) {
			if(c == noble){
				flag = true ;
				break;
			}
		}
		
		return flag;
	}
	
	public int slideNoble(Color noble) throws CouncilException{
		Color tmp = noble, app ;
		Color[] nobles = this.getNobles();
		if(checkSingleColorConsistence(noble) == false) throw new CouncilException(CouncilExceptionCode.INCONSISTENCE_BETWEEN_NEW_NOBLE_AND_PALETTE.getExceptionCode());
		for(int i = 0; i < (nobles.length - 1); i++){
			if(i == 0){
				tmp = nobles[i];
				nobles[i] = noble;
			}
			else
			{
				app = nobles[i];
				nobles[i] = tmp;
				tmp = app;
			}
		}
		
		return CouncilConstants.REWARD_COINS;
	}
	
	public Color[] getNobles(){ return this.nobles; }
	
	@Override
	public String toString(){
		String councilString = "\nCouncil \n";
		
		councilString += "colors: \n";
		for(int i = 0; i < this.getNobles().length; i++) councilString += nobles[i].toString() + "\n";
		
		return councilString;
	}
}
