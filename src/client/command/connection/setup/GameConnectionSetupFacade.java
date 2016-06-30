package client.command.connection.setup;

import commons.data.GameMode;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;

import client.controller.ControllerRepository;

/**
 * Classe che implementa i metodi per la richiesta di connessione ad un match
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupFacade {
	private GameMode mode;
	
	@SuppressWarnings("static-access")
	public GameConnectionSetupFacade(){
		this.mode = ControllerRepository.getInstance().getClientController().getInstance().getClient().getGameMode();
	}
	
	
	/**
	 * Metodo per la richiesta di adesione ad un match
	 * @param username
	 * @return
	 */
	public String requireMatch(){
		ServerMessage response = null;
		String message = null;
		
		switch(this.mode){
			case SOCKET:
				response = new GameConnectionSetupSocket().requireMatch();
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
