package command.connection.setup;

import commons.data.UserData;
import communication.socket.messages.ServerMessage;
import communication.socket.messages.ServerMessageContentType;
import client.controller.ControllerRepository;
import client.GameMode;

/**
 * Classe che implementa i metodi per la richiesta di connessione ad un match
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupFacade {
	private GameMode mode;
	private UserData user;
	
	@SuppressWarnings("static-access")
	public GameConnectionSetupFacade(){
		this.mode = ControllerRepository.getInstance().getClientController().getInstance().getClient().getGameMode();
		this.user = ControllerRepository.getInstance().getGameDataController().getUserData();
	}
	
	
	/**
	 * Metodo per la richiesta di adesione ad un match
	 * @param username
	 * @return
	 */
	public String requireMatch(){
		ServerMessage response = null;
		String message = null;
		String username = user.getUsername();
		
		switch(this.mode){
			case SOCKET:
				response = new GameConnectionSetupSocket().requireMatch(username);
				//Se mi viene restituito il matchcode allora ritorno quello, altrimenti ritorno il messaggio del server
				if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_MATCH_CODE) message = response.getMatchCode();
				else message = response.getContent().getServerMessageContentType();
				break;
			default:
				break;
		}
		
		return message;
		
	}
}
