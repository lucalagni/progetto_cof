package model.basics;

import java.awt.Color;
import java.io.Serializable;

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
	
	public Color slideNoble(Color noble) throws CouncilException{
		Color old = null ;
		Color app = this.nobles[0];
		Color tmp = app;
		
		old = this.nobles[nobles.length - 1];
		for(int i = (this.nobles.length - 1); i > 0; i--){
			this.nobles[i] = this.nobles[i - 1];
		}
		this.nobles[0] = noble;
		
		return old;
	}
	
	public Color[] getNobles(){ return this.nobles; }
	
	@Override
	public String toString(){
		String councilString = "\nCouncil \n";
		
		councilString += "colors: \n";
		for(int i = 0; i < this.getNobles().length; i++){
			if(this.nobles[i].equals(Color.BLACK))  councilString += " BLACK \n";
			if(this.nobles[i].equals(Color.WHITE)) councilString += " WHITE \n";
			if(this.nobles[i].equals(Color.ORANGE))  councilString += " ORANGE \n";
			if(this.nobles[i].equals(Color.MAGENTA))  councilString += " MAGENTA \n";
			if(this.nobles[i].equals(Color.CYAN))  councilString += " CYAN \n";
			if(this.nobles[i].equals(Color.PINK))  councilString += " PINK \n";
		}
		
		return councilString;
	}
}
