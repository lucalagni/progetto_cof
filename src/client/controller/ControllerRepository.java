package client.controller;

import commons.data.UserData;
import commons.data.GameMode;
import client.controller.actions.basics.ActionController;
import client.controller.actions.setter.SetterActionController;
import client.controller.connections.GameConnectionSetupController;
import client.controller.data.GameDataController;
import client.controller.updates.GameUpdatesController;

/**
 * Classe lato client che contiene tutti i controller utili al giocatore
 * per l'evoluzione della partita
 * 
 * @author Luca Lagni
 * @pattern Singleton
 *
 */
public class ControllerRepository {
	private static ControllerRepository instance = null;
	private ClientController clientController ;
	private GameDataController gameDataController ;
	private ActionController actionController ;
	private GameUpdatesController gameUpdatesController;
	private GameConnectionSetupController gameConnectionSetupController;
	private SetterActionController setterActionController;
	
	private ControllerRepository(){
		this.clientController = null;
		this.gameDataController = null;
		this.actionController = null;
		this.gameUpdatesController = null;
		this.gameConnectionSetupController = null;
		this.setterActionController = null;
	}
	
	/**
	 * Metodo per il settaggio del controller contenente le informazioni per la communicazione 
	 * client server lato client
	 * @param mode
	 */
	public void setClientController(GameMode mode){
		if(this.clientController == null){
			this.clientController = ClientController.getInstance();
			this.clientController.setGameMode(mode);
		}
	}
	
	/**
	 * Metodo per il settaggio del controller che contiene le informazioni
	 * dell'utente e del match lato client
	 * @param userData
	 */
	public void setGameDataController(UserData userData){
		if(this.gameDataController != null) return ;
		
		this.gameDataController = GameDataController.getInstance();
		this.gameDataController.setUserData(userData);
	}
	
	/**
	 * Metodo per il settaggio del controller per la gestione delle azioni di gioco
	 * @param userData
	 */
	public void setActionController(){
		if(this.actionController != null) return ;
		
		this.actionController =  ActionController.getInstance();
	}
	
	public void setGameUpdatesController(){
		if(this.gameUpdatesController != null) return ;
		
		this.gameUpdatesController = GameUpdatesController.getInstance();
	}
	
	public void setGameConnectionSetupController(){
		if(this.gameConnectionSetupController != null) return ;
		
		this.gameConnectionSetupController = GameConnectionSetupController.getInstance();
	}
	
	public void setSetterActionController(){
		if(this.setterActionController != null) return;
		
		this.setterActionController = SetterActionController.getInstance();
	}
	
	public ClientController getClientController(){ return this.clientController; }
	public GameDataController getGameDataController(){ return this.gameDataController; }
	public ActionController getActionController(){ return this.actionController; }
	public GameUpdatesController getGameUpdatesController(){ return this.gameUpdatesController; }
	public GameConnectionSetupController getGameConnectionSetupController(){ return this.gameConnectionSetupController; }
	public SetterActionController getSetterActionController(){ return this.setterActionController; }
	
	public static ControllerRepository getInstance(){
		if(instance == null) instance = new ControllerRepository();
		return instance;
	}
}

