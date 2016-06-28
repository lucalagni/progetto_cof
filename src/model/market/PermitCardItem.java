package model.market;

import java.io.Serializable;

import model.basics.PermitCard;

/**
 *	Classe wrapper per la vendita di una carta permesso 
 * @author Luca Lagni
 *
 */
public class PermitCardItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Boolean available;
	private Integer position; //indica la posizione della carta nell'arraylist del gamer
	private PermitCard permitCard;
	private Integer price ;
	
	public PermitCardItem(PermitCard permitCard,int price,int position){
		this.setAvailable(true);
		this.setPrice(price);
		this.setPermitCard(permitCard);
		this.setPosition(position);
	}
	
	private void setAvailable(boolean available){ this.available = new Boolean(available); }
	private void setPrice(int price){ this.price = new Integer(price); }
	private void setPermitCard(PermitCard permitCard){ this.permitCard = permitCard; }
	private void setPosition(int position){ this.position = new Integer(position); }
	
	public void sell(){ this.setAvailable(false); }
	
	public boolean getAvailable(){ return this.available.booleanValue(); }
	public int getPrice(){ return this.price.intValue(); }
	public PermitCard getPermitCard(){ return this.permitCard; }
	public int getPosition(){ return this.position.intValue(); }
	
	@Override
	public String toString(){
		String s = new String("\nPermitCardItem\n");
		
		s += "available: " + this.getAvailable() + "\n";
		s += "permit card: " + this.getPermitCard().toString() + "\n";
		s += "price: " + this.getPrice() + "\n";
		
		return s;
	}

}
