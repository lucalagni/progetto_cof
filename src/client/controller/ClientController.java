package client.controller;

import client.Client;
import commons.data.GameMode;

public class ClientController {
	private static ClientController instance = null;
	private Client client;
	
	private ClientController(){ 
		this.client = null;
	}
	
	public void setGameMode(GameMode mode){
		if(this.client == null) this.client = new Client(mode);
	}
	
	public Client getClient(){ return this.client; }
	
	public static ClientController getInstance(){
		if(instance == null) instance = new ClientController();
		return instance;
	}
}
