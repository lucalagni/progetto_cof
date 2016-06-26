package client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import communication.socket.messages.ClientMessage;
import communication.socket.messages.ServerMessage;

public class SocketClient implements Runnable{
	
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
	
	public ServerMessage sendMessage(ClientMessage cm){
		ServerMessage sm = null;
		
		try {
			this.output.writeObject(cm);
			sm = (ServerMessage)this.input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return sm;
	}
	
	private void initCommunication(){
		try {
			this.client = new Socket(this.serverIp, this.serverPort);
			this.input = new ObjectInputStream(this.client.getInputStream());
			this.output = new ObjectOutputStream(this.client.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void forceClose(){
		try{
			if(this.client != null) this.client.close();
			if(this.input != null) this.input.close();
			if(this.output != null) this.output.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public String getServerIp(){ return this.serverIp ; }
	public int getServerPort(){ return this.serverPort; }

	@Override
	public void run() {
		try{
			this.initCommunication();
			
		}catch(Exception ex){}
		
	}
}
