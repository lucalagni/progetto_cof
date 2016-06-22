package model.market;

import model.basics.PermitCard;

public class ItemPermitCard {

	private PermitCard permitCard;
	private int price;
	private boolean available;
	
	public void setPermitCard(PermitCard permitCard){
		this.permitCard = permitCard;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setAvailable(boolean available){
		this.available = available;
	}
	
	public PermitCard getPermitCard(){
		return permitCard;		
	}
	
	public int getPrice(){
		return price;
	}
	
	public boolean getAvailable(){
		return available;
	}
	
	
}
