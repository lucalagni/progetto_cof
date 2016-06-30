package client;

import communication.socket.messages.ClientMessage;
import communication.socket.messages.ServerMessage;
import client.socket.SocketClient;

/**
 * Classe per la gestione delle connessioni di rete lato client
 * La classe non è serializzata poichè residente sul client , senza alcuna interazione con il server
 * 
 * @author Luca Lagni
 *
 */

public class Client {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int PORT = 6789;
	private SocketClient client;
	private GameMode gameMode;
	
	/**
	 * Costruttore della classe client , richiede la scelta del metodo di comunicazione
	 * (socket o rmi)
	 * @param mode
	 */
	public Client(GameMode mode){
		this.setGameMode(mode);
		
		switch(this.gameMode){
			case SOCKET:
				this.client = new SocketClient(SERVER_IP, PORT);
				break;
			case RMI:
				break;
		}
	}
	
	/**
	 *  Metodo che , se si è scelta la modalità di comunicazione socket,
	 *  consente di inviare messaggi al server per 
	 * @param cientMessage
	 * @return
	 */
	public ServerMessage sendMessage(ClientMessage cm){
		if(this.getGameMode() != GameMode.SOCKET) return null;
		ServerMessage sm = null;

		sm = this.client.sendMessage(cm);
		System.out.println(sm.getMatch().toString());
		return sm;
	}
	
	private void setGameMode(GameMode gm){ this.gameMode = gm; }
	public GameMode getGameMode(){ return this.gameMode; }
	
	@Override
	public String toString(){
		String s = new String("\nClient\n");
		
		s += "GameMode: " + this.getGameMode().getGameMode() + "\n";
		
		return s;
	}
}
