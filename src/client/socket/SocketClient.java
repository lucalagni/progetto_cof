package client.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import commons.messages.*;

/**
 * Classe per la gestione del meccanismo di comunicazione socket lato client
 * Non serializzata in quanto residente interamente sul client e mai richiamabile dal server
 * @author Luca Lagni
 *
 */

public class SocketClient {
	
	private int serverPort;
	private String serverIp;
	private Socket client;
	private ObjectInputStream input;
    private ObjectOutputStream output;
	
	public SocketClient(String ip,int port){
		this.serverIp = ip;
		this.serverPort = port;
		this.client = null;
		this.input = null;
		this.output = null;
	}
	
	/**
	 * Metodo per l'invio e la ricezione di messaggi da client verso il server
	 * @param ClientMessage
	 * @return
	 */
	public ServerMessage sendMessage(ClientMessage cm){
		this.initCommunication();
		ServerMessage sm = null;
		
		try {
			this.output.writeObject(cm);
			sm = (ServerMessage)this.input.readObject();
		} catch (IOException | ClassNotFoundException e) { 
			System.out.println("\n[SocketClient] Send Message Failure");
			System.out.println("error: " + e.getMessage());
		}
		
		return sm;
	}
	
	/**
	 * Metodo per l'inizializzazione dei parametri di comunicazione socket
	 * Implementato separatamente dal costruttore in quanto gli oggetti per la communicazione
	 * potrebbero non essere implementati qualora si scegliesse il metodo rmi
	 */
	private void initCommunication(){
		try {
			this.client = new Socket(this.serverIp, this.serverPort);
			this.output = new ObjectOutputStream(this.client.getOutputStream());
			
			InputStream is = this.client.getInputStream();
			this.input = new ObjectInputStream(is);
			
			
		} catch (IOException e) { 
			System.out.println("\n[SocketClient] Connection Establishment Failure");
			System.out.println("error: " + e.getMessage());
		}
	}
	
	/**
	 * Metodo che forza la chiusura del socket
	 */
	public void forceClose(){
		try{
			if(this.client != null) this.client.close();
			if(this.input != null) this.input.close();
			if(this.output != null) this.output.close();
		}catch(Exception ex){ 
			System.out.println("\n[SocketClient] Client Forced Close failure");
			System.out.println("error: " + ex.getMessage());
		}
	}
	
	public String getServerIp(){ return this.serverIp ; }
	public int getServerPort(){ return this.serverPort; }
}
