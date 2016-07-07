package commons.rmi.interfaces.connections;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;
import commons.messages.ServerMessage;

/**
 * Interfaccia per la richiesta del match qualora esso sia disponibile
 * @author Luca Lagni
 *
 */
public interface ClientRequestCanIPlayRmiInterface extends Remote{
	public ServerMessage clientRequestCanIPlay(UserData data) throws RemoteException;
}
