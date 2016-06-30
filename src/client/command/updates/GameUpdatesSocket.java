package client.command.updates;

import model.basics.Match;
import client.Client;
import client.controller.ControllerRepository;
import commons.data.ActionSynoptic;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.*;

/**
 * Classe che implementa i metodi di richiesta di aggiornamenti per i dati del match
 * uno valota che questo è stato creato e l'utente vi è stato connesso
 * 
 * @author Luca Lagni
 *
 */

public class GameUpdatesSocket {
	private Client client;
	private UserData data;
	
	public GameUpdatesSocket(){ 
		this.client = ControllerRepository.getInstance().getClientController().getClient(); 
		this.data = ControllerRepository.getInstance().getGameDataController().getUserData();
	}
	
	/**
	 * Metodo che richiede al server il giocatore di turno mediante tecnologia socket
	 * @param matchCode
	 * @return
	 */
	public int getGamerTurn(){
		ClientMessage message = new ClientMessage(this.data);
		ServerMessage response = null;
		int gamer = 0;
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_GAMER_TURN, null);
		response = this.client.sendMessage(message);
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_GAMER_TURN){
			gamer = Integer.parseInt(response.getParameters().get(0)[0]);
		}
		else ;//lanciare un'eccezione per notificare eventuali errori
		
		return gamer;
	}
	
	/**
	 * Metodo che richiede al server il match aggiornato relativo ad un determinato matchcode
	 * @param matchCode
	 * @return
	 * @throws UserDataException 
	 */
	public Match getMatch() throws UserDataException{
		ClientMessage request = new ClientMessage(this.data);
		ServerMessage response = null;
		Match m = null;
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_MATCH, null);
		response = this.client.sendMessage(request);
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_MATCH){
			m = response.getUserData().getMatch();
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(m);
		}
		else ; //lancio un'eccezione 
		
		return m;
	}
	
	/**
	 * Metodo che richiede al server il sinottico per le azioni dell'utente aggiornato attraverso tecnologia socket
	 * @param matchCode
	 * @param username
	 * @return
	 */
	public ActionSynoptic getActionSynoptic(){
		ClientMessage request = new ClientMessage(this.data);
		ServerMessage response = null;
		ActionSynoptic as = null;
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACTION_SYNOPTIC, null);
		response = this.client.sendMessage(request);
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_ACTION_SYNOPTIC){
			as = response.getUserData().getActionSynoptic();
			this.data = response.getUserData();
		}
		else; //lancio messaggio di avvertimento
		
		return as;
		
	}
}
