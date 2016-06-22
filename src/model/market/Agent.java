package model.market;

import java.util.ArrayList;

import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.PoliticalCardsDeckException;

public class Agent {

	private Gamer gamer;
	private ArrayList<ItemHelpers> itemHelperArray = new ArrayList<ItemHelpers>();
	private ArrayList<ItemPermitCard> itemPermitCardArray = new ArrayList<ItemPermitCard>();
	private ArrayList<ItemPoliticalCard> itemPoliticalCardArray = new ArrayList<ItemPoliticalCard>();
	
	public Agent(Gamer gamer){
		this.setGamer(gamer);
	}
	
	private void setGamer(Gamer gamer){
		this.gamer = gamer;
	}
	public Gamer getGamer(){ 
		return this.gamer; 
	}	
	
	public ArrayList<ItemHelpers> getArrayListItemHelpers(){
		return itemHelperArray;
	}
	
	public ArrayList<ItemPermitCard> getArrayListItemPermitCard(){
		return itemPermitCardArray;
	}
	
	public ArrayList<ItemPoliticalCard> getArrayListItemPoliticalCard(){
		return itemPoliticalCardArray;
	}
	

	public void selectToSellHelpers(ItemHelpers addItemHelper, int price){
		
		addItemHelper.setPrice(price);
		itemHelperArray.add(addItemHelper);	
	}
	

	public void selectToSellPermitCard(ItemPermitCard addItemPermitCard, int price){
		
		addItemPermitCard.setPrice(price);
		itemPermitCardArray.add(addItemPermitCard);
		
	}
	

	public void selectToSellPoliticalCard(ItemPoliticalCard addItemPoliticalCard, int price){
		
		addItemPoliticalCard.setPrice(price);
		itemPoliticalCardArray.add(addItemPoliticalCard);
		
	}
	
	
	public void sellHelpers(ItemHelpers itemHelp) throws GamerException {
			
		gamer.subHelpers(itemHelp.getHelpers());
		gamer.addCoins(itemHelp.getPrice());
	}
	/**
	 * Quando il giocatore avviene la transizione di compra vendita, la carta permesso deve essere eliminata dalle proprietà del giocatore
	 * e devono essere aggiunti i coins relativi a  tale vendita.
	 * @param itemPermitCard è la carta che deve essere sottratta dalle proprietà del giocatore
	 * @throws GamerException
	 */
	public void sellPermitCard(ItemPermitCard itemPermitCard) throws GamerException{
		// vado a iterare l'array delle carte permesso non usate del giocatore per trovare il riscontro con quella che è stata venduta
		for (PermitCard card:gamer.getUnusedPermitCards()){
			if(card.equals(itemPermitCard.getPermitCard())){
			gamer.subPermitCard(card);
			gamer.addCoins(itemPermitCard.getPrice());
			}
		}
		
	}
	/**
	 * Quando il giocatore avviene la transizione di compra vendita, la carta politica deve essere eliminata dalle proprietà del giocatore
	 * e devono essere aggiunti i coins relativi a  tale vendita.
	 * @param itemPoliticalcard è la carta che deve essere sottratta dalle prorpietà del giocatore
	 * @throws GamerException
	 */
	public void sellPoliticalCard(ItemPoliticalCard itemPoliticalCard) throws PoliticalCardsDeckException, GamerException{
		ArrayList<PoliticalCard> arrayGamerPoliticalCard = gamer.getPoliticalCards();
		for (PoliticalCard card:arrayGamerPoliticalCard){
			if(card.equals(itemPoliticalCard.getPoliticalCard())){
			int position = arrayGamerPoliticalCard.indexOf(card);
			gamer.subPoliticalCard(position);
			gamer.addCoins(itemPoliticalCard.getPrice());
			}
		}

		
	}
	
	public void buyHelpers(ItemHelpers itemHelp) throws GamerException{
		for(ItemHelpers item: itemHelperArray){
			if(item.equals(itemHelp) && item.getAvailable()==true){
				gamer.addHelpers(item.getHelpers());
				gamer.subCoins(item.getPrice());
				item.setAvailable(false);
				}

			if(item.getAvailable() == false){ //lancio eccezione????
		
	 		}
		}
	}
	
	public void buyPermitCard(ItemPermitCard itemPermitCard) throws GamerException{
		for(ItemPermitCard item: itemPermitCardArray){
			if(item.equals(itemPermitCard) && item.getAvailable()==true){
				gamer.addPermitCard(item.getPermitCard());
				gamer.subCoins(item.getPrice());
				item.setAvailable(false);
				}

			if(item.getAvailable() == false){ //lancio eccezione????
		
	 		}
		}
	}
	
	
}
