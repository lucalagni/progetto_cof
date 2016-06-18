package model.basics.builders;

import java.util.ArrayList;

import model.basics.PoliticalCard;
import model.basics.PoliticalCardsDeck;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.PoliticalCardsDeckConstants;
import model.basics.exceptions.codes.PoliticalCardsDeckExceptionCode;
import model.basics.supports.QuequedGamer;

public class PoliticalCardsDeckBuilder {
	private ArrayList<PoliticalCard> availableCards;
	private ArrayList<QuequedGamer> queque;
	
	public PoliticalCardsDeckBuilder setAvailableCards(ArrayList<PoliticalCard> availableCards){
		this.availableCards = availableCards;
		return this;
	}
	
	public PoliticalCardsDeckBuilder setQueque(ArrayList<QuequedGamer> queque){
		this.queque = queque;
		return this;
	}
	
	public PoliticalCardsDeck build() throws BuilderException{
		//Controllare che il numero di carte per colore sia corretto
		//Controllare che il numero di jolly sia corretto
		if(this.availableCards == null) throw new BuilderException(PoliticalCardsDeckExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.availableCards.toArray().length != PoliticalCardsDeckConstants.TOTAL_CARDS)throw new BuilderException(PoliticalCardsDeckExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		
		return new PoliticalCardsDeck(this.availableCards,this.queque);
	}
}
