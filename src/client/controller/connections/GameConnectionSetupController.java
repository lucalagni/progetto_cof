package client.controller.connections;

import command.connection.setup.GameConnectionSetupFacade;

/**
 * 
 * Classe controller per la richiesta di connessione ad un match
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupController {
	private GameConnectionSetupFacade gcsp = null;
	
	public GameConnectionSetupController(){ this.gcsp = new GameConnectionSetupFacade(); }
	
	public String requireMatch(){
		String response = null;
		
		//do{
		response = this.gcsp.requireMatch();
		//while((response.equals(ServerMessageContentType.SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE)));
		
		return response;
	}
}
