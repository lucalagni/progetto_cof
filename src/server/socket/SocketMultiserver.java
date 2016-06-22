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
                Socket newClient = this.server.accept();
                ClientHandlerThread cht = new ClientHandlerThread(newClient);
                cht.start();
            }
            
        }catch(Exception e){}
    }
}

