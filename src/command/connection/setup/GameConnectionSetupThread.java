package command.connection.setup;

import client.Client;
import communication.socket.messages.ClientMessage;
import communication.socket.messages.ClientMessageContentType;
import communication.socket.messages.ServerMessage;
import communication.socket.messages.ServerMessageContentType;
import controller.ControllerRepository;

/**
 * Classe che , una volta che il server mi ha detto che l'utente è stato messo in coda ,
 * chiede in polling al server di inviargli un match (oppure se il server non dispone di 
 * abbastanza utenti gli dice che non se ne fa nulla).
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupThread implements Runnable{
	private String username ;
	private String matchCode;
	private Client client;
	private ServerMessageContentType content;
	
	public GameConnectionSetupThread(String username){
		this.username = username;
		this.client = ControllerRepository.getInstance().getClientController().getClient();
		this.content = ServerMessageContentType.SEREVR_RESPONSE_MATCHCODE_NOT_AVAILABLE_YET;
	}
	
	public ServerMessageContentType getContent(){ return this.content; }

	//Continuo a richiedere il matchCode fino a quando il server non me ne fornisce uno
	@Override
	public void run() {
		ServerMessageContentType control = null;
		ClientMessage cm = new ClientMessage(this.username, null);
		cm.addContent(ClientMessageContentType.CLIENT_REQUEST_CAN_I_PLAY, null);
		
		do
		{
			ServerMessage sm = this.client.sendMessage(cm);
			if(sm.getContent() == ServerMessageContentType.SERVER_RESPONSE_MATCH_CODE){
				this.matchCode = sm.getMatchCode();
			}
			if(sm.getContent() == ServerMessageContentType.SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY); 
			
			this.content = sm.getContent();
			
		}while((control != ServerMessageContentType.SERVER_RESPONSE_MATCH_CODE) || (control != ServerMessageContentType.SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY));
		
	}

}
