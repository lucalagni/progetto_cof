package client.command.connection.setup;

import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.*;
import client.controller.ControllerRepository;
import client.Client;

/**
 * Classe che implementa le azioni preliminari per la realizzazione di un match
 * utilizzando la tecnologia Socket
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupSocket {
	private UserData data = null;
	private Client client = null;
	
	public GameConnectionSetupSocket(){
		this.client = ControllerRepository.getInstance().getClientController().getClient();
		this.data = ControllerRepository.getInstance().getGameDataController().getUserData();
	}
	
	//Metodo che invia al server la richiesta di connessione ad un match
	public ServerMessage clientRequireAddMe(){
		ClientMessage cm = new ClientMessage(this.data);
		ServerMessage sm = null;
		
		cm.addContent(ClientMessageContentType.CLIENT_REQUEST_ADD_ME, null);
		sm = this.client.sendMessage(cm);
		
		return sm;
	}
	
	/**
	 * Metodo che si occupa di tornare un match per il giocatore qualora esso sia disponibile
	 * @return
	 * @throws UserDataException 
	 */
	public String clientRequestCanIPlay() throws UserDataException{
		ClientMessage request = new ClientMessage(this.data);
		ServerMessage response = null;
		String responseString = null;
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_CAN_I_PLAY, null);
		response = this.client.sendMessage(request);
		
		responseString = response.getContent().getServerMessageContentType();
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_MATCH){
			this.data.setupGamer(response.getUserData().getGamer());
			this.data.setupMatch(response.getUserData().getMatch());
		}
		
		return responseString;
	}

}
