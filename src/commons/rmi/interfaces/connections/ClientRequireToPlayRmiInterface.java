package commons.rmi.interfaces.connections;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;

/**
 * Interfaccia per la richiesta di gioco in modlità RMI
 * @author Luca Lagni
 *
 */

public interface ClientRequireToPlayRmiInterface extends Remote{
	
	/**
	 * Metodo per la richiesta di poter partecipare ad un gioco
	 * @param data
	 * @return
	 */
	public String clientRequireToPlay(UserData data) throws RemoteException;
}
