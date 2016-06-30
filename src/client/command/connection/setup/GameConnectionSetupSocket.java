package client.command.connection.setup;

import commons.data.UserData;
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
	public ServerMessage requireMatch(){
		ClientMessage cm = new ClientMessage(this.data);
		ServerMessage sm = null;
		
		cm.addContent(ClientMessageContentType.CLIENT_REQUEST_ADD_ME, null);
		sm = this.client.sendMessage(cm);
		
		return sm;
	}

}
