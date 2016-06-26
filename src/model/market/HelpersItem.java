package model.market;

import java.io.Serializable;

/**
 * Classe wrapper per la rappresentazione di un lotto di aiutanti da vendere
 * @author Luca Lagni
 *
 */
public class HelpersItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Boolean available;
	private Integer helpers;
	private Integer price;
	
	public HelpersItem(int helpers,int price){
		this.setHelpers(helpers);
		this.setPrice(price);
		this.setAvailable(true);
	}
	
	private void setHelpers(int helpers){ this.helpers = new Integer(helpers); }
	private void setPrice(int price){ this.price = new Integer(price); }
	private void setAvailable(boolean available){ this.available = new Boolean(available); }
	
	public void sell(){ this.setAvailable(false); }
	
	public int getHelpers(){ return this.helpers.intValue(); }
	public int getPrice(){ return this.price.intValue(); }
	public boolean getAvailable(){ return this.available.booleanValue(); }
	
	@Override 
	public String toString(){
		String s = new String("\nHelpersItem\n");
		
		s += "available: " + this.getAvailable() + "\n";
		s += "helpers: " + this.getHelpers() + "\n";
		s += "price: " + this.getPrice() + "\n";
		
		return s;
	} 
}
