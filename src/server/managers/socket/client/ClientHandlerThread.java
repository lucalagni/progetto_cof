package server.managers.socket.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandlerThread extends Thread{
    private Socket client = null;
    private String inputMessage ;
    private String outputMessage ;
    private BufferedReader input ;
    private DataOutputStream output;
    
    public ClientHandlerThread(Socket client){
        this.client = client;
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

