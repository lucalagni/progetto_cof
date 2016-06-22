package server.socket;

import java.net.ServerSocket;
import java.net.Socket;
import server.managers.socket.client.ClientHandlerThread;;

public class SocketMultiserver {
    private int port ;
    private ServerSocket server ;
    
    public SocketMultiserver(int port){
        this.port = port; 
    }
    
    public void start(){
        try{
            this.server = new ServerSocket(port);
            
            while(true){
                System.out.println("socket server in attesa");
                Socket newClient = this.server.accept();
                System.out.println("nuova connessione");
                ClientHandlerThread cht = new ClientHandlerThread(newClient);
                cht.start();
            }
            
        }catch(Exception e){}
    }
}

