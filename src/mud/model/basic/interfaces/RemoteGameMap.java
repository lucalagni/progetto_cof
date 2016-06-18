package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGameMap extends Remote{
	public int getVillagePosition(RemoteVillage v) throws RemoteException;
	//public boolean addConnection(RemoteVillage village1,RemoteVillage village2) throws RemoteException;
	public RemoteVillage[] getVillages();
	public int[][] getConnections();
	public RemoteBonus[] getColorsBonus();
	public String toString();
	
}
