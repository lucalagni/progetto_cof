package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RemotePoliticalCardsDeck extends Remote{
	
	public RemotePoliticalCard pickupCard() throws RemoteException;
	public void addCard(RemotePoliticalCard politicalCard)throws RemoteException;
	public ArrayList<RemotePoliticalCard> getAvailableCardsList();
	public String toString();
}
