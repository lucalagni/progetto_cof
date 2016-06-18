package mud.model.basic.interfaces;

import java.awt.Color;
import java.rmi.Remote;


public interface RemoteVillage extends Remote{
	
	public String getName();
	public char getFirstLetter();
	public Color getColor();
	public RemoteBonus getBonus();
	public String[] getShops();
}
