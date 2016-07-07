package client.command.connection.setup;

import commons.data.GameMode;
import commons.data.exceptions.UserDataException;
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
		String message = null;
		
		switch(this.mode){
			case SOCKET:
				message = new GameConnectionSetupSocket().clientRequireAddMe();	
				break;
			default:
				message = new String("GAME_MODE_NOT_AVAILABLE");
				break;
		}
		
		return message ;
		
	}
	/**
	 * Metodo per la richiesta di andare offline da parte dell'utente
	 * @return
	 */
	public String clientRequestToGoOffline(){
		String response = new String("[ClientRequestGoOffline] No response");
		
		switch(this.mode){
			case SOCKET:
					response = new GameConnectionSetupSocket().clientRequestToGoOffline();
				break;
			default:
				response = new String("GAME_MODE_NOT_AVAILABLE");
				break;
		}
		
		return response ;
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
				response = new String("GAME_MODE_NOT_AVAILABLE");
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
		int gamerTurn = -1;
		
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
