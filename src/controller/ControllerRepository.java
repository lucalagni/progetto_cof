package controller;

import client.GameMode;
import controller.client.ClientController;

public class ControllerRepository {
	private static ControllerRepository instance = null;
	private ClientController clientController ;
	
	private ControllerRepository(){
		this.clientController = null;
	}
	
	public void setClientController(GameMode mode){
		if(this.clientController == null){
			this.clientController = ClientController.getInstance();
			this.clientController.setGameMode(mode);
		}
	}
	
	public ClientController getClientController(){ return this.clientController; }
	
	public static ControllerRepository getInstance(){
		if(instance == null) instance = new ControllerRepository();
		return instance;
	}
}
