package model.market;

public class ItemHelpers {
	
	private int helpers;
	private int price;
	private boolean available;
	
	public void setHelpers(int helpers){
		this.helpers = helpers;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public void setAvailable(boolean available){
		this.available = available;
	}
	
	public int getHelpers(){
		return helpers;		
	}
	
	public int getPrice(){
		return price;
	}
	
	public boolean getAvailable(){
		return available;
	}
	
}
