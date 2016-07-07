package commons.rmi.interfaces.actions.setter;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;

/**
 * Interfaccia per la richiesta di settaggio del market al server
 * @author Luca Lagni
 *
 */

public interface SetterActionsRmiInterfaces extends Remote{
	public UserData setupAgent(UserData data) throws RemoteException ;
}
