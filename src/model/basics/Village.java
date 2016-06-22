package model.basics;

import java.awt.Color;
import java.io.Serializable;

import model.basics.constants.GamerConstants;
import model.basics.constants.VillageConstants;
import model.basics.constants.ColorConstants;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.VillageException;
import model.basics.exceptions.codes.VillageExceptionCode;

public class Village implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Color color;
	private Bonus bonus;
	private String[] shops;
	
	public Village(String name,Color color,Bonus bonus) {
		
		this.setName(name);
		this.setColor(color);
		this.setBonus(bonus);	
		this.shops = new String[GamerConstants.GAMERS_COLORS.length];
		for(int i = 0; i < this.shops.length; i++) this.shops[i] = VillageConstants.NULL_GAMER;
	}
	
	public Village(String name,Color color,Bonus bonus,String[] shops) {
		
		this.setName(name);
		this.setColor(color);
		this.setBonus(bonus);
		this.setShops(shops);
		for(int i = 0; i < this.shops.length; i++) if(this.shops[i] == null)this.shops[i] = VillageConstants.NULL_GAMER;
	}
	
	private void setName(String name) { this.name = name; }
	private void setColor(Color color) { this.color = color; }
	private void setBonus(Bonus bonus) { this.bonus = bonus; }
	private void setShops(String[] shops){ this.shops = shops; }
	
	public static boolean checkValidVillageName(String name){
		for(String name1: VillageConstants.VILLAGES_NAME)if(name1.equals(name)) return true;
		
		return false;
	}
	
	public static boolean checkValidColor(Color color){
		for(Color c: ColorConstants.VILLAGES_COLORS) if(c.equals(color)) return true;
		
		return false ;
	}
	
	public boolean hasGamerAShopHere(Gamer gamer){
		for(int i = 0; i < this.shops.length; i++) if(this.shops[i].equals(gamer.getUsername())) return true;
		return false;
	}
	
	private void checkGamerPresence(Gamer gamer) throws VillageException{
		for(int i = 0; i < this.shops.length; i++) if(this.shops[i].equals(gamer.getUsername()))throw new VillageException(VillageExceptionCode.GAMER_ALREADY_PRESENT.getExceptionCode());
	}
	
	private void checkGamerPresence(String gamer) throws VillageException{
		for(int i = 0; i < this.shops.length; i++){
			if(this.shops[i].equals(gamer))throw new VillageException(VillageExceptionCode.GAMER_ALREADY_PRESENT.getExceptionCode());
		}
	}
	
	public void addShop(Gamer gamer)throws VillageException,GamerException{
		this.checkGamerPresence(gamer);
		for(int i = 0; i < this.shops.length; i++) if(this.shops[i] == VillageConstants.NULL_GAMER) this.shops[i] = gamer.getUsername();
	}
	
	public void addShop(String gamer)throws VillageException,GamerException{
		
		this.checkGamerPresence(gamer);
		for(int i = 0; i < this.shops.length; i++){
			if(this.shops[i].equals(VillageConstants.NULL_GAMER)){
				this.shops[i] = gamer;
				break;
			}
		}
	}
	
	
	public String getFirstGamer(){ return this.shops[0]; }
	
	public String getName() { return this.name; }
	public char getFirstLetter(){ return this.getName().charAt(0); }
	public Color getColor() { return this.color; }
	public Bonus getBonus() { return this.bonus; }
	public String[] getShops(){ return this.shops; }
	
	@Override
	public String toString()
	{
		String vString = "\nvillage\n";
		
		vString += "name: " + this.getName() + "\n";
		vString += "color: "+ this.getColor().toString() + "\n";
		vString += "bonus: "+ this.getBonus() + "\n";
		vString += "shops: \n";
		for(int i = 0; i < this.shops.length; i++) vString += this.shops[i] + " ";
		
		return vString;
	}
}
