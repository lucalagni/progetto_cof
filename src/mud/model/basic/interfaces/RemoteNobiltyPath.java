package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteNobiltyPath extends Remote{
	public RemoteBonus getSingleBonus(int position) throws RemoteException;
	public RemoteBonus[] getBonus();
	public String toString();
}
