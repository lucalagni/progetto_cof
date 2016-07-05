package server.managers.socket.messages;

import server.managers.match.MatchRepository;
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
		ServerMessage response = null;
		
		//Aggiorno il match nel repository
		MatchRepository.getInstance().updateMatch(msg.getUserData().getMatch());
		
		//Aggiorno i dati dell'utente
		response = new ServerMessage(msg.getUserData());
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_AGENT_SETTED, null);
		
		return response ;
	}
}
