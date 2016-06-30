package server.managers.socket.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import communication.socket.messages.ClientMessage;
import communication.socket.messages.ServerMessage;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import server.managers.socket.messages.ServerMessageHandler;
import examples.example1.MatchExample;

@SuppressWarnings("unused")
public class ClientHandlerThread extends Thread{
	
    private Socket client = null;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    private ServerMessageHandler handler;
    
    private static int index = 0;
    private int localIndex;
    
    public ClientHandlerThread(Socket client){
        this.client = client;
        
        index++;
        localIndex = index;
    }
    
    public void setHandler(ServerMessageHandler handler){
    	this.handler = handler;
    }
    
    public void communication() throws IOException{
        this.input = new ObjectInputStream(this.client.getInputStream());
        this.output = new ObjectOutputStream(this.client.getOutputStream());
        
        while(true){
        	
        	
            
            ClientMessage request;
			try {		
				System.out.println("Messaggio ricevuto");
				request = (ClientMessage)this.input.readObject(); 
				System.out.println("Messaggio ricevuto");
	            ServerMessage response = handler.handle(request);
	            this.output.writeObject(response);
	            
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            break;
        }
        
        System.out.println("Chiusura socket");
        this.client.close();
        this.output.close();
		this.input.close();
    }
   
    
    public void forceClose(){
        System.out.println("Chiusura TH: "+localIndex);
        try {
			this.client.close();
			this.output.close();
			this.input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("Chiusura socket");
        
    }
    
    @Override
    public void run(){
    	try {
			this.communication();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

