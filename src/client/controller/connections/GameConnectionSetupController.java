package client.controller.connections;

import commons.data.exceptions.UserDataException;

import client.command.connection.setup.GameConnectionSetupFacade;

/**
 * 
 * Classe controller per la richiesta di connessione ad un match
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupController {
	private static GameConnectionSetupController instance = null;
	private GameConnectionSetupFacade gcsp = null;
	
	private GameConnectionSetupController(){ 
		this.gcsp = new GameConnectionSetupFacade(); 
	}
	
	/**
	 * Metodo che notifica se la richiesta del giocatore di 
	 * poter richiedere un match è stata eseguita
	 * @return
	 */
	public String clientRequireAddMe(){
		String response = null;
		response = this.gcsp.clientRequireAddMe();
		
		return response;
	}
	
	/**
	 * Metodo che serve al giocatore per sapere se è nelle condizioni di poter 
	 * giocare oppure no
	 * @return
	 */
	public String clientRequestCanIPlay(){
		String response = null;
		try {
			response = this.gcsp.clientRequestCanIPlay();
		} catch (UserDataException e) { e.printStackTrace(); }
		
		return response;
	}
	
	public int clientRequestGamerTurn(){
		int gamerTurn = 0;
		
		try {
			gamerTurn = this.gcsp.clientRequestGamerTurn();
		} catch (UserDataException e) {
			e.printStackTrace();
		}
		
		return gamerTurn;
	}
	
	public static GameConnectionSetupController getInstance(){
		if(instance == null) instance = new GameConnectionSetupController();
		return instance;
	}
}
