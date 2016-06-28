package command.connection.setup;

import communication.socket.messages.ClientMessage;
import communication.socket.messages.ClientMessageContentType;
import communication.socket.messages.ServerMessage;
import communication.socket.messages.ServerMessageContentType;
import controller.ControllerRepository;
import client.Client;

public class GameConnectionSetupSocket {
	private Client client = null;
	
	public GameConnectionSetupSocket(){
		this.client = ControllerRepository.getInstance().getClientController().getClient();
	}
	
	//Metodo che invia al server la richiesta di connessione ad un match
	public ServerMessageContentType requireMatch(String username){
		ClientMessage cm = new ClientMessage(username, null);
		ServerMessage sm = null;
		
		cm.addContent(ClientMessageContentType.CLIENT_REQUEST_ADD_ME, null);
		sm = this.client.sendMessage(cm);
		
		return sm.getContent();	
	}
	
	//Se l'utente è stato aggiunto alla coda vado a chiedere in polling al server quando è pronto il mio match
	public String requireMatchCode(String username){
		String matchCode = null;
		
		return null;
	}

}
