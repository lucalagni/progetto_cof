package server.managers.socket.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@SuppressWarnings("unused")
public class ClientHandlerThread extends Thread{
	
    private Socket client = null;
	private String inputMessage ;
    private String outputMessage ;
    private BufferedReader input ;
    private DataOutputStream output;
    private ClientGameData clientGameData;
    
    public ClientHandlerThread(Socket client){
        this.client = client;
        this.clientGameData = null;
    }
    
    public void communication() throws IOException{
        this.input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        this.output = new DataOutputStream(this.client.getOutputStream());
        
        while(true){
            this.inputMessage = this.input.readLine();
           break;
        }
        
        this.output.close();
        this.input.close();
        System.out.println("Chiusura socket");
        this.client.close();
    }
    
    @Override
    public void run(){
    }
}

