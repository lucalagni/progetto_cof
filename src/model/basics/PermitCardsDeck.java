package model.basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import model.basics.constants.PermitCardsDeckConstants;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.codes.PermitCardsDeckExceptionCode;

public class PermitCardsDeck implements Serializable {
	private static final long serialVersionUID = 1L;
	private PermitCard[] unhiddenCards;
	private ArrayList<PermitCard> availableCardsSet;
	
	public PermitCardsDeck(ArrayList<PermitCard> availableCardsSet){
		PermitCard[] unhiddenPermitCard = new PermitCard[PermitCardsDeckConstants.UNHIDDEN_CARDS_FOR_REGION];
		for(int i = 0; i < PermitCardsDeckConstants.UNHIDDEN_CARDS_FOR_REGION; i++){
			unhiddenPermitCard[i] = availableCardsSet.get(i);
			availableCardsSet.remove(i);
		}
		this.setUnhiddenCards(unhiddenPermitCard);
		this.setAvailableCardsSet(availableCardsSet);
	}
	
	public PermitCardsDeck(PermitCard[] unhiddencards,ArrayList<PermitCard> availableCardsSet){
		this.setUnhiddenCards(unhiddencards);
		this.setAvailableCardsSet(availableCardsSet);
	}

	private void setUnhiddenCards(PermitCard[] unhiddenCards) { this.unhiddenCards = unhiddenCards; }
	private void setAvailableCardsSet(ArrayList<PermitCard> availableCardsSet) { this.availableCardsSet = availableCardsSet; }

	public PermitCard pickupCard(int position) throws PermitCardsDeckException{
		PermitCard pc = this.unhiddenCards[position];
		this.newUnhiddenCard(position);
		return pc;
	}
	
	public PermitCard pickupCard(PermitCard card) throws PermitCardsDeckException {
		
		for(int i = 0; i < this.getUnhiddenCards().length; i++){
			if(this.unhiddenCards[i] == card){
				this.newUnhiddenCard(i);
				return card;
			}
			
		}
		throw new PermitCardsDeckException(PermitCardsDeckExceptionCode.CARD_NOT_FOUND_IN_LIST.getExceptionCode());
	}
	
	public void doubleAction() throws PermitCardsDeckException{
		this.checkCardsAvailability();
		this.shuffleCards();
		for(int i = 0; i < this.availableCardsSet.size(); i++){
			this.unhiddenCards[i] = this.availableCardsSet.get(i);
		}
	}
	
	private void newUnhiddenCard(int position) throws PermitCardsDeckException {
		this.checkCardsAvailability();
		this.unhiddenCards[position] = this.getAvailableCardsList().get(0);
		this.getAvailableCardsList().remove(0);
	}
	
	private void checkCardsAvailability() throws PermitCardsDeckException {
		Iterator<PermitCard> it = this.getAvailableCardsList().iterator();
		if(it.hasNext() == false) throw new PermitCardsDeckException(PermitCardsDeckExceptionCode.FINISHED_AVAILABLE_CARDS.getExceptionCode());
	}
	
	public void shuffleCards() throws PermitCardsDeckException{
		this.checkCardsAvailability();
		ArrayList<PermitCard> tmp = new ArrayList<PermitCard>();
		
		while(!this.availableCardsSet.isEmpty()){
			int randomPosition = (int)(Math.random() * this.availableCardsSet.size());
			tmp.add(this.availableCardsSet.get(randomPosition));
			this.availableCardsSet.remove(randomPosition);
		}
		
		this.availableCardsSet = tmp;
	}
	
	private boolean isCardInList(PermitCard permitCard,ArrayList<PermitCard> list){
		Iterator<PermitCard> it = list.iterator();
		
		while(it.hasNext()) if(it.next() == permitCard) return true ;
		
		return false ;
	}
	
	public boolean isAvailable(PermitCard card){
		return this.isCardInList(card, this.getAvailableCardsList());
	}
	
	public ArrayList<PermitCard> getAvailableCardsList() { return this.availableCardsSet; }
	public PermitCard[] getUnhiddenCards() { return this.unhiddenCards; }
	
	@Override
	public String toString(){
		String pcdString = new String("\npermit cards deck\n");
		Iterator<PermitCard> it = this.availableCardsSet.iterator();
		
		pcdString += "unhidden cards: \n" ;
		for(int i = 0; i < this.getUnhiddenCards().length; i++) pcdString += this.unhiddenCards[i].toString();
		pcdString += "available cards: \n";
		//for(int i = 0; i < 12; i++)System.out.println(it.next().toString());
		while(it.hasNext()) pcdString += it.next().toString() + "\n";
		
		return pcdString;
	}
}
	
	