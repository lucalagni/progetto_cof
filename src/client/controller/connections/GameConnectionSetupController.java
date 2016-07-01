package client.controller.connections;

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
	
	public String requireMatch(){
		String response = null;
		
		//do{
		response = this.gcsp.requireMatch();
		//while((response.equals(ServerMessageContentType.SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE)));
		
		return response;
	}
	
	public static GameConnectionSetupController getInstance(){
		if(instance == null) instance = new GameConnectionSetupController();
		return instance;
	}
}
