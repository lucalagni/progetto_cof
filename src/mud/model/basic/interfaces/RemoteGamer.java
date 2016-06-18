package mud.model.basic.interfaces;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;
import model.basics.supports.GamerStatus;

public interface RemoteGamer extends Remote{
	public String getUsername();
	public Color getColor();
	public int getHelpers();
	public int getCoins();
	public int getPoints();
	public int getShops();
	public String getMatch();
	public GamerStatus getStatus();
	public int getShifts();
	
	public HashSet<RemotePoliticalCard> getPoliticalCards();
	public HashSet<RemotePermitCard> getUnusedPermitCards();
	public HashSet<RemotePermitCard> getUsedPermitCards();
	
	public void addPermitCard(RemotePermitCard permitCard);
	public void addPoliticalCard(RemotePoliticalCard politicalCard);
	
	public RemotePermitCard subPermitCard(RemotePermitCard permitCard) throws RemoteException;
	public void usePermitCard(RemotePermitCard permitCard) throws RemoteException;
	
	public void addCoins(int coins) throws RemoteException;
	public void subCoins(int coins) throws RemoteException;
	public void addHelpers(int helpers) throws RemoteException;
	public void subHelpers(int helpers) throws RemoteException;
	public void subShop() throws RemoteException;
	public void addShifts(int shifts) throws RemoteException;
	public void addPoints(int points);
	
	public String toString();
}
