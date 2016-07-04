package client.command.connection.setup;

import commons.data.GameMode;
import commons.data.exceptions.UserDataException;
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
	
	public GameConnectionSetupFacade(){
		this.mode = ControllerRepository.getInstance().getClientController().getClient().getGameMode();
	}
	
	
	/**
	 * Metodo per la richiesta di adesione ad un match
	 * @param username
	 * @return
	 */
	public String clientRequireAddMe(){
		ServerMessage response = null;
		String message = null;
		
		switch(this.mode){
			case SOCKET:
				response = new GameConnectionSetupSocket().clientRequireAddMe();
				//Se mi viene restituito il matchcode allora ritorno quello, altrimenti ritorno il messaggio del server
				if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_MATCH_CODE) message = response.getMatchCode();
				else message = response.getContent().getServerMessageContentType();
				break;
			default:
				break;
		}
		
		return message;
		
	}
	
	/**
	 * Metodo che, una volta che il giocatore sa di essere statio aggiunto alla coda di attesa
	 * verifica se può giocare.
	 * @return
	 * @throws UserDataException 
	 */
	public String clientRequestCanIPlay() throws UserDataException{
		String response = null;
		
		switch(this.mode){
			case SOCKET:
						response = new GameConnectionSetupSocket().clientRequestCanIPlay();
						break;
			default:
				break;
		}
		
		return response;
	}
	
	/**
	 * Metodo che richiede al server di chi è il turno di gioco
	 * @return
	 * @throws UserDataException
	 */
	public int clientRequestGamerTurn() throws UserDataException{
		int gamerTurn = 0;
		
		switch(this.mode){
			case SOCKET:
				gamerTurn = new GameConnectionSetupSocket().clientRequestGamerTurn();
				break;
			default :
				break;
		}
		
		return gamerTurn ;
	}
}
