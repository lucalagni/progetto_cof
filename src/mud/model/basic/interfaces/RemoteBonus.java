package mud.model.basic.interfaces;

import java.rmi.Remote;

public interface RemoteBonus extends Remote{
	
	public int getCoins();
	public int getPoints();
	public int getShifts();
	public int getPoliticalCards();
	public int getHelpers();
	public boolean getNewMainAction();
	public boolean getReusePermitBonus();
	public boolean getAcquirePermitCard();
	public boolean getAcquireSingleVillageBonus();
	public boolean getAcquireDoubleVillageBonus();
	
	public String toString();
}
