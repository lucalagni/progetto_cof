package server.rmi.actions.setter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server.managers.match.MatchRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.setter.SetterActionsRmiInterfaces;

/**
 * Classe per l'invio di richieste al server mediante tecnologia RMI
 * per la fase di settaggio del market
 * @author Luca Lagni
 *
 */
public class SetterActionsRmi extends UnicastRemoteObject implements SetterActionsRmiInterfaces{
	public SetterActionsRmi() throws RemoteException { super();}

	private static final long serialVersionUID = 1L;

	@Override
	public UserData setupAgent(UserData data) throws RemoteException {
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		ServerMessage response = null;
		
		//Aggiorno il match nel repository
		MatchRepository.getInstance().updateMatch(data.getMatch());
		for(int i = 0; i < data.getMatch().getGamers().size(); i++){
			if(data.getMatch().getGamers().get(i).getUsername().equals(data.getGamer().getUsername())){
				try {
					data.updateGamer(data.getMatch().getGamers().get(i));
				} catch (UserDataException e) {
					parameters = new ArrayList<String[]>();
					exception = new String[1];
					exception[0] = e.getMessage();
					parameters.add(exception);
					response = new ServerMessage(data);
					response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
				}
				break;
			}
		}
		
		return data ;
	}



}
