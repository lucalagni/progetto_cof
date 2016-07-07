package server.managers.socket.messages;

import java.util.ArrayList;

import server.managers.match.MatchRepository;
import commons.data.exceptions.UserDataException;
import commons.messages.ClientMessage;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;

/**
 * Classe per la gestione delle operazioni di setter per il market
 * lato server
 * @author Luca Lagni
 *
 */
public class ServerSetterActionHandler {
	
	/**
	 * Metodo per l'aggiornamento di un agente del market
	 * @param msg
	 * @return
	 */
	public ServerMessage updateAgent(ClientMessage msg){
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		ServerMessage response = null;
		
		//Aggiorno il match nel repository
		MatchRepository.getInstance().updateMatch(msg.getUserData().getMatch());
		for(int i = 0; i < msg.getUserData().getMatch().getGamers().size(); i++){
			if(msg.getUserData().getMatch().getGamers().get(i).getUsername().equals(msg.getUserData().getGamer().getUsername())){
				try {
					msg.getUserData().updateGamer(msg.getUserData().getMatch().getGamers().get(i));
				} catch (UserDataException e) {
					parameters = new ArrayList<String[]>();
					exception = new String[1];
					exception[0] = e.getMessage();
					parameters.add(exception);
					response = new ServerMessage(msg.getUserData());
					response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
				}
				break;
			}
		}
		
		//Aggiorno i dati dell'utente
		response = new ServerMessage(msg.getUserData());
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_AGENT_SETTED, null);
		
		return response ;
	}
}
