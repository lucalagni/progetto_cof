package client.controller.data;

import model.basics.Gamer;
import model.basics.Match;
import commons.data.ActionSynoptic;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;

/**
 * Controller che contiene i dati di gioco
 * @author Luca Lagni
 *
 */
public class GameDataController {
	public static final String OK_MESSAGE = "GAME_DATA_CONTROLLER_ACTION_PERFORMED_CORRECTLY" ;
	
	private static GameDataController instance ;
	private UserData data;
	
	private GameDataController(){ this.setUserData(null); }
	
	public void setUserData(UserData userData){ 
		this.data = userData; 
	} 
	
	/**
	 * Metodo che gestisce le eccezioni nella configurazione del match
	 * 
	 * @param match
	 * @return
	 */
	public String setupMatch(Match match){
		try {
			this.getUserData().setupMatch(match);
			return OK_MESSAGE; 
		} catch (UserDataException e) {
			return e.getMessage().toString();
		}
	}
	
	/**
	 * Metodo che gestisce le eccezioni nella configurazione del gamer
	 * 
	 * @param match
	 * @return
	 */
	public String setupGamer(Gamer gamer){
		try {
			this.getUserData().setupGamer(gamer);
			return OK_MESSAGE; 
		} catch (UserDataException e) {
			return e.getMessage().toString();
		}
	}
	
	/**
	 * Metodo che aggiorna i dati del match del giocatore
	 * @param match
	 * @throws UserDataException 
	 */
	public String updateMatch(Match match) {
		try {
			this.getUserData().setupMatch(match);
			return OK_MESSAGE ;
		}catch (UserDataException e) {
			return e.getMessage().toString();
		}
	}
	
	/**
	 * Metodo che aggiorna i dati del match del giocatore
	 * @param match
	 * @throws UserDataException 
	 */
	public String updateGamer(Gamer gamer) {
		try {
			this.getUserData().updateGamer(gamer);
			return OK_MESSAGE ;
		}catch (UserDataException e) {
			return e.getMessage().toString();
		}
	}
	
	/**
	 * Metodo che aggiorna i dati del match del giocatore
	 * @param match
	 * @throws UserDataException 
	 */
	public String updateActionSynoptic(ActionSynoptic actionSynoptic) {
		try {
			this.getUserData().updateActionSynoptic(actionSynoptic);
			return OK_MESSAGE ;
		}catch (UserDataException e) {
			return e.getMessage().toString();
		}
	}

	public UserData getUserData(){ return this.data; }
	
	public static GameDataController getInstance(){
		if(instance == null) instance = new GameDataController();
		return instance;
	}
}
