package model.basics.builders;

import java.util.ArrayList;

import model.basics.PermitCard;
import model.basics.PermitCardsDeck;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.PermitCardsDeckConstants;
import model.basics.exceptions.codes.PermitCardsDeckExceptionCode;

public class PermitCardsDeckBuilder {
	private ArrayList<PermitCard> availableCardsSet;
	private PermitCard[] unhiddenCards;
	
	public PermitCardsDeckBuilder setAvailableCardsSet(ArrayList<PermitCard> availableCardsSet){
		this.availableCardsSet = availableCardsSet;
		return this;
	}
	
	public PermitCardsDeckBuilder setUnhiddenCards(PermitCard[] unhiddenCards){
		this.unhiddenCards = unhiddenCards;
		return this;
	} 
	
	public PermitCardsDeck build() throws BuilderException{
		if(this.availableCardsSet == null) throw new BuilderException(PermitCardsDeckExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		//if(this.availableCardsSet.toArray().length != PermitCardsDeckConstants.CARDS_FOR_REGION)throw new BuilderException(PermitCardsDeckExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.unhiddenCards == null)throw new BuilderException(PermitCardsDeckExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.unhiddenCards.length != PermitCardsDeckConstants.UNHIDDEN_CARDS_FOR_REGION) throw new BuilderException(PermitCardsDeckExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		
		return new PermitCardsDeck(this.unhiddenCards,this.availableCardsSet);
	}
}
