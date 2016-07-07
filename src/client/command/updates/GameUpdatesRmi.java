package client.command.updates;

import server.rmi.connections.ClientRequestGamerTurnRmi;
import model.basics.Match;
import client.Client;
import client.controller.ControllerRepository;
import commons.data.ActionSynoptic;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.*;
import commons.rmi.interfaces.actions.setter.SetterActionsRmiInterfaces;
import commons.rmi.interfaces.connections.ClientRequestGamerTurnRmiInterface;

/**
 * Classe che implementa i metodi di richiesta di aggiornamenti per i dati del match
 * uno valota che questo è stato creato e l'utente vi è stato connesso
 * 
 * @author Luca Lagni
 *
 */

public class GameUpdatesRmi {
	private Client client;
	private UserData data;
	private static final String ADD_STRING = "client_require_to_play_rmi";
	private static final String PLAY_STRING = "client_request_can_i_play_rmi";
	private static final String TURN_STRING = "client_request_gamer_turn_rmi";
	
	public GameUpdatesRmi(){ 
		this.client = ControllerRepository.getInstance().getClientController().getClient(); 
		this.data = ControllerRepository.getInstance().getGameDataController().getUserData();
	}
	
	/**
	 * Metodo che richiede al server il giocatore di turno mediante tecnologia socket
	 * @param matchCode
	 * @return
	 */
	public int getGamerTurn(){
		ServerMessage response = null;
		int gamer = 0;
		
		try{
			ClientRequestGamerTurnRmiInterface mari = (ClientRequestGamerTurnRmiInterface) this.client.getRmiClient().getRegistry().lookup(TURN_STRING);
			response  = mari.clientRequestGamerTurn(data);
		}catch(Exception ex){
			return -1;
		}
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_GAMER_TURN){
			gamer = Integer.parseInt(response.getParameters().get(0)[0]);
			try {
				this.data.updateMatch(response.getUserData().getMatch());
				this.data.updateGamer(response.getUserData().getGamer());
			} catch (UserDataException e) { return -1 ; }
		}
		else return -2;
		
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
	
}
