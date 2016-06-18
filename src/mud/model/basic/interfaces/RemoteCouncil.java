package mud.model.basic.interfaces;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCouncil extends Remote{
	
	public boolean checkSingleColorConsistence(Color noble);
	public int slideNoble(Color noble) throws RemoteException;
}
