package server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.managers.socket.client.ClientHandlerThread;
import server.managers.socket.messages.ServerMessageHandler;

/**
 * Classe per la gestione del server che gestisce le richieste dei
 * client
 * @author lucal
 *
 */
public class SocketMultiserver implements Runnable {
    private int port ;
    private ServerSocket server ;
    private ArrayList<ClientHandlerThread> chts;
    
    public SocketMultiserver(int port){
        this.port = port; 
        this.chts = new ArrayList<ClientHandlerThread>();
    }
    
    public void start(){
        try{
            this.server = new ServerSocket(port);
            
            while(true){
            	System.out.println("\n[SocketMultiserver] ACCEPT NEW REQUEST ...  ");
                Socket newClient = this.server.accept();
                
                ClientHandlerThread cht = new ClientHandlerThread(newClient);
                cht.setHandler(new ServerMessageHandler());
                this.chts.add(cht);
                cht.start();
            }
            
        }catch(Exception e){}
    }
    
    public void stop(){
    	try {
			this.server.close();
			for(ClientHandlerThread c: this.chts){
				c.forceClose();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void run() {
		start();
	}
}

