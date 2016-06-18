package model.basics.builders;

import java.awt.Color;

import model.basics.PoliticalCard;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.codes.PoliticalCardExceptionCode;

public class PoliticalCardBuilder {
	public Color color;
	public boolean jolly;
	
	public PoliticalCardBuilder(){}
	
	public PoliticalCardBuilder setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public PoliticalCardBuilder setJolly(boolean jolly) {
		this.jolly = jolly;
		return this;
	}
	
	public PoliticalCard build() throws BuilderException {
		/*if(this.jolly == false){
			if(PoliticalCard.checkColorConsistence(color) == false) throw new BuilderException(PoliticalCardExceptionCode.INCONSISTENCE_BETWEEN_NEW_COLOR_AND_PALETTE.getExceptionCode());
		}
		else{
			if(PoliticalCard.checkJollyColorConsistence(color) == false) throw new BuilderException(PoliticalCardExceptionCode.INCONSISTENCE_BETWEEN_JOLLY_AND_COLOR.getExceptionCode());
		}*/
			
		return new PoliticalCard(this.color, this.jolly);
	}
}
