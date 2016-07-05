package model.market;

import java.io.Serializable;

import model.basics.PoliticalCard;

/**
 * Classe wrapper per la vendita di una carta permesso
 * @author Luca Lagni, Maria Antonietta Palermo
 *
 */
public class PoliticalCardItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Boolean available;
	private PoliticalCard politicalCard;
	private Integer price;
	private Integer position; //indica la posizione della carta nell'array list di gamer

	public PoliticalCardItem(PoliticalCard politicalCard,int price,int position){
		this.setAvailable(true);
		this.setPoliticalCard(politicalCard);
		this.setPrice(price);
		this.setPosition(position);
	}
	
	private void setPoliticalCard(PoliticalCard politicalCard){ this.politicalCard = politicalCard;}
	private void setPrice(int price){ this.price = new Integer(price); }
	private void setAvailable(boolean available){ this.available = new Boolean(available); }
	private void setPosition(int position){ this.position = new Integer(position); }
	
	public void sell(){ this.setAvailable(false); }
	
	public PoliticalCard getPoliticalCard(){ return this.politicalCard; }
	public int getPrice(){ return this.price.intValue(); }
	public boolean getAvailable(){ return this.available.booleanValue(); }
	public int getPosition(){ return this.position.intValue(); }
	
	@Override
	public String toString(){
		String s = new String("\nPoliticalCardItem\n");
		
		s += "available: " + this.getAvailable() + "\n";
		s += "political card: " + this.getPoliticalCard().toString() + "\n";
		s += "price: " + this.getPrice() + "\n";
		
		return s;
	}
}
