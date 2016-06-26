package client;

import communication.socket.messages.ClientMessage;
import communication.socket.messages.ServerMessage;
import client.socket.SocketClient;


public class Client {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int PORT = 6789;
	private SocketClient client;
	private GameMode gameMode;
	
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
	
	public ServerMessage sendMessage(ClientMessage cm){
		if(this.getGameMode() != GameMode.SOCKET) return null;
		ServerMessage sm = null;
		
		sm = this.client.sendMessage(cm);
		
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
