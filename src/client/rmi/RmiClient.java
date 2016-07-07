package client.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RmiClient {
	private int serverPort;
	private String serverIp;
	private Registry registry;
	
	public RmiClient(String ip,int port){
		this.serverIp = ip;
		this.serverPort = port;
		this.registry = null;
		this.initCommunication();
	}
	
	/**
	 * Metodo per l'inizializzazione dei parametri di comunicazione RMI
	 */
	private void initCommunication(){
		try {
			this.registry = LocateRegistry.getRegistry(this.serverIp, this.serverPort);
			
		} catch (Exception e) { 
			System.out.println("\n[SocketClient] Connection Establishment Failure");
			System.out.println("error: " + e.getMessage());
		}
	}
	
	public Registry getRegistry(){ return this.registry; }
}
