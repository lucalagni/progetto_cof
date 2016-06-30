package command.connection.setup;

import communication.socket.messages.ClientMessage;
import communication.socket.messages.ClientMessageContentType;
import communication.socket.messages.ServerMessage;
import communication.socket.messages.ServerMessageContentType;
import controller.ControllerRepository;
import client.Client;

/**
 * Classe che implementa le azioni preliminari per la realizzazione di un match
 * utilizzando la tecnologia Socket
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupSocket {
	private Client client = null;
	
	public GameConnectionSetupSocket(){
		this.client = ControllerRepository.getInstance().getClientController().getClient();
	}
	
	//Metodo che invia al server la richiesta di connessione ad un match
	public ServerMessage requireMatch(String username){
		ClientMessage cm = new ClientMessage(username, null);
		ServerMessage sm = null;
		
		cm.addContent(ClientMessageContentType.CLIENT_REQUEST_ADD_ME, null);
		sm = this.client.sendMessage(cm);
		
		return sm;
	}

}
