package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteKing extends Remote{
	
	public RemoteCouncil getCouncil();
	public String getPosition();
	public RemoteBonus[] getBonus();
	
	public void moveKing(String newLocation) throws RemoteException;
	public String toString();

}
