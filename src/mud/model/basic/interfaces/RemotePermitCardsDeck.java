package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemotePermitCardsDeck extends Remote{
	
	public RemotePermitCard pickupCard(RemotePermitCard card) throws RemoteException ;
	public void doubleAction() throws RemoteException;
	public void shuffleCards() throws RemoteException;
	public boolean isAvailable(RemotePermitCard card);
	public ArrayList<RemotePermitCard> getAvailableCardsList();
	public RemotePermitCard[] getUnhiddenCards();
}
