package commons.rmi.interfaces.connections;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;
import commons.messages.ServerMessage;

/**
 * Interfaccia per la richiesta del turno di gioco al server
 * @author Luca Lagni
 *
 */
public interface ClientRequestGamerTurnRmiInterface extends Remote {
	public ServerMessage clientRequestGamerTurn(UserData data) throws RemoteException;
}
