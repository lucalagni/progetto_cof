package model.basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.exceptions.codes.PoliticalCardsDeckExceptionCode;
import model.basics.supports.QuequedGamer;

public class PoliticalCardsDeck implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<PoliticalCard> availableCardsList ;
	private ArrayList<QuequedGamer> queque;
	
	public PoliticalCardsDeck(ArrayList<PoliticalCard> availableCardsList){
		this.setAvailableCardsList(availableCardsList);
		this.setQueque(new ArrayList<QuequedGamer>());
	}
	
	public PoliticalCardsDeck(ArrayList<PoliticalCard> availableCardsList,ArrayList<QuequedGamer> queque){
		this.setAvailableCardsList(availableCardsList);
		this.setQueque(queque);
	}
	
	private void setAvailableCardsList(ArrayList<PoliticalCard> availableCardsList){ this.availableCardsList = availableCardsList; }
	private void setQueque(ArrayList<QuequedGamer> queque){ this.queque = queque; }
	
	public void quequeGamer(Gamer gamer,int cards){
		this.queque.add(new QuequedGamer(this.queque.size(), gamer.getUsername(), cards));
	}
	
	public PoliticalCard pickupCard() throws PoliticalCardsDeckException {
		PoliticalCard card = null;
		
		if(this.availableCardsList.get(0) == null)throw new PoliticalCardsDeckException(PoliticalCardsDeckExceptionCode.NO_MORE_AVAILABLE_CARDS.getExceptionCode());
		card = this.availableCardsList.get(0);
		this.availableCardsList.remove(0);
		return card;
	}
	
	public void addCard(PoliticalCard politicalCard)throws PoliticalCardsDeckException{
		if(politicalCard == null) throw new PoliticalCardsDeckException(PoliticalCardsDeckExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		this.availableCardsList.add(politicalCard);
	}
	
	public void shuffleCards() throws PoliticalCardsDeckException{
		ArrayList<PoliticalCard> shuffledDeck = new ArrayList<PoliticalCard>();
		ArrayList<PoliticalCard> tmp = this.getAvailableCardsList();

		
		while(!tmp.isEmpty()){
			int randomPosition = (int)(Math.random() * this.availableCardsList.size());
			shuffledDeck.add(tmp.get(randomPosition));
			tmp.remove(randomPosition);
		}
		

		this.availableCardsList = shuffledDeck;
	}
	
	
	public ArrayList<PoliticalCard> getAvailableCardsList(){return this.availableCardsList; }
	public ArrayList<QuequedGamer> getQueque(){ return this.queque; }
	
	@Override
	public String toString(){
		String pcdString = new String("\npolitical cards deck\n");
		Iterator<PoliticalCard> it = this.getAvailableCardsList().iterator();
		Iterator<QuequedGamer> itq = this.getQueque().iterator();
		
		pcdString += "available cards list: \n";
		while(it.hasNext()) pcdString += it.next().toString() + " \n";
		
		pcdString += "quequed gamers: \n";
		while(itq.hasNext()) pcdString += itq.next().toString() + " \n";
		
		return pcdString;
	}
}
