package controller.client;

import client.Client;
import client.GameMode;

public class ClientController {
	private static ClientController instance = null;
	private Client client;
	
	private ClientController(){ }
	
	public void setGameMode(GameMode mode){
		if(this.client == null) this.client = new Client(mode);
	}
	
	public Client getClient(){ return this.client; }
	
	public static ClientController getInstance(){
		if(instance == null) instance = new ClientController();
		return instance;
	}
}
