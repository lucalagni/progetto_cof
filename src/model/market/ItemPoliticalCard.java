package model.market;

import model.basics.PermitCard;
import model.basics.PoliticalCard;

public class ItemPoliticalCard {

	private PoliticalCard politicalCard;
	private int price;
	private boolean available;
	
	public void setPoliticalCard(PoliticalCard politicalCard){
		this.politicalCard = politicalCard;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setAvailable(boolean available){
		this.available = available;
	}
	
	public PoliticalCard getPoliticalCard(){
		return politicalCard;		
	}
	
	public int getPrice(){
		return price;
	}
	
	public boolean getAvailable(){
		return available;
	}
}
