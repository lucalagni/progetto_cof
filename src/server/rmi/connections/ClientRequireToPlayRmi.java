package server.rmi.connections;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.managers.match.MatchManager;
import commons.data.UserData;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.connections.ClientRequireToPlayRmiInterface;

/**
 * Implementazione in modalità RMI dell'interfaccia di adesione al match
 * @author lucal
 *
 */
public class ClientRequireToPlayRmi extends UnicastRemoteObject implements  ClientRequireToPlayRmiInterface{
	private static final long serialVersionUID = 1L;
	private MatchManager matchManager ;
	
	public ClientRequireToPlayRmi() throws RemoteException{
		this.matchManager = MatchManager.getInstance();
	}

	@Override
	public String clientRequireToPlay(UserData data) throws RemoteException {
		boolean available = this.matchManager.addGamer(data.getUsername());
		
		if(available == false) return ServerMessageContentType.SERVER_RESPONSE_USERNAME_NOT_AVAILABLE.getServerMessageContentType();
		else return ServerMessageContentType.SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE.getServerMessageContentType();
	}

}
