package model.basics.builders;

import java.awt.Color;

import model.basics.Council;
import model.basics.constants.CouncilConstants;
import model.basics.builders.exceptions.BuilderException;

public class CouncilBuilder {
	private Color nobles[];
	
	public CouncilBuilder(){ this.nobles = new Color[CouncilConstants.NOBLES_NUMBER]; }
	
	public CouncilBuilder setNobles(Color[] nobles){ 
		this.nobles = nobles; 
		return this;
	}
	
	public Council build() throws BuilderException{
		//for(Color c: this.nobles) if(Council.checkSingleColorConsistence(c) == false) throw new BuilderException(CouncilExceptionCode.INCONSISTENCE_BETWEEN_NEW_NOBLE_AND_PALETTE.getExceptionCode());
		
		return new Council(this.nobles);
	}
}
