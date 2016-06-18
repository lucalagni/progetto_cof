package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteHelpersPool extends Remote{
	public void subHelpers(int helpers) throws RemoteException;
	public int getActualTotal();
	public int getActualGamerHelpers();
}
