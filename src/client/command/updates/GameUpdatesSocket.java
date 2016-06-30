package client.command.updates;

import model.basics.Match;
import client.Client;
import client.controller.ControllerRepository;
import command.basic.actions.ActionSynoptic;
import communication.socket.messages.ClientMessage;
import communication.socket.messages.ClientMessageContentType;
import communication.socket.messages.ServerMessage;
import communication.socket.messages.ServerMessageContentType;

/**
 * Classe che implementa i metodi di richiesta di aggiornamenti per i dati del match
 * uno valota che questo è stato creato e l'utente vi è stato connesso
 * 
 * @author Luca Lagni
 *
 */

public class GameUpdatesSocket {
	private Client client;
	
	public GameUpdatesSocket(){ this.client = ControllerRepository.getInstance().getClientController().getClient(); }
	
	/**
	 * Metodo che richiede al server il giocatore di turno mediante tecnologia socket
	 * @param matchCode
	 * @return
	 */
	public int getGamerTurn(String matchCode){
		ClientMessage message = new ClientMessage(null, matchCode);
		ServerMessage response = null;
		int gamer = 0;
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_GAMER_TURN, null);
		response = this.client.sendMessage(message);
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_GAMER_TURN){
			gamer = Integer.parseInt(response.getParameters()[0]);
		}
		else ;//lanciare un'eccezione per notificare eventuali errori
		
		return gamer;
	}
	
	/**
	 * Metodo che richiede al server il match aggiornato relativo ad un determinato matchcode
	 * @param matchCode
	 * @return
	 */
	public Match getMatch(String matchCode){
		ClientMessage request = new ClientMessage(null, matchCode);
		ServerMessage response = null;
		Match m = null;
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_MATCH, null);
		response = this.client.sendMessage(request);
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_MATCH) m = response.getMatch();
		else ; //lancio un'eccezione 
		
		return m;
	}
	
	/**
	 * Metodo che richiede al server il sinottico per le azioni dell'utente aggiornato attraverso tecnologia socket
	 * @param matchCode
	 * @param username
	 * @return
	 */
	public ActionSynoptic getActionSynoptic(String matchCode,String username){
		ClientMessage request = new ClientMessage(username,matchCode);
		ServerMessage response = null;
		ActionSynoptic as = null;
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACTION_SYNOPTIC, null);
		response = this.client.sendMessage(request);
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_ACTION_SYNOPTIC) as = response.getActionSynoptic();
		else; //lancio messaggio di avvertimento
		
		return as;
		
	}
}
