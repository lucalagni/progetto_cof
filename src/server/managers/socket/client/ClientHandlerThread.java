package server.managers.socket.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import server.managers.socket.messages.ServerMessageHandler;
import communication.socket.messages.SocketMessage;
import examples.example1.MatchExample;

@SuppressWarnings("unused")
public class ClientHandlerThread extends Thread{
	
    private Socket client = null;
	private String inputMessage ;
    private String outputMessage ;
    private BufferedReader input ;
    private PrintWriter output;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private ClientGameData clientGameData;
    
    private ServerMessageHandler handler;
    
    private static int index = 0;
    private int localIndex;
    
    public ClientHandlerThread(Socket client){
        this.client = client;
        this.clientGameData = null;
        
        index++;
        localIndex = index;
    }
    
    public void setHandler(ServerMessageHandler handler){
    	this.handler = handler;
    }
    
    public void communication() throws IOException{
        this.input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        is = new ObjectInputStream(this.client.getInputStream());
        
        this.output = new PrintWriter(this.client.getOutputStream(), true);
        os = new ObjectOutputStream(this.client.getOutputStream());
        
        while(true){
        	System.out.println("[ClientHandlerThread] ricevuto msg "+inputMessage+" - TH: "+localIndex);
            
            this.inputMessage = this.input.readLine();
            if(this.inputMessage.equals("ESCI")) break;
            
//            SocketMessage request;
//			try {
//				request = (SocketMessage)is.readObject();
//	            if( request==null ){
//	            	break;
//	            }
//	            
//	            SocketMessage response = handler.handle(request);
//	            os.writeObject(response);
//	            
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            
            try {
            	os.writeObject(new MatchExample().getMatch());
			} catch (BuilderException | MatchException | GameMapException
					| PoliticalCardsDeckException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //this.output.println("fatto");
        }
        
        this.output.close();
        this.input.close();
        System.out.println("Chiusura socket");
        this.client.close();
        this.os.close();
		this.is.close();
    }
   
    
    public void forceClose(){
        System.out.println("Chiusura TH: "+localIndex);
        try {
        	this.output.close();
			this.input.close();
			this.client.close();
			this.os.close();
			this.is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("Chiusura socket");
        
    }
    
    @Override
    public void run(){
    	try {
			communication();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

