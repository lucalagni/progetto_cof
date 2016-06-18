package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.socket.SocketClientHandler;

public class Server {
	private int port;
	private String ip;
	private ExecutorService executor;
	private ServerSocket serverSocket;
	
	public Server(int port,String ip){
		this.port = port;
		this.ip = ip;
	}
	
	public void startServer(){
		this.executor = Executors.newCachedThreadPool();
		
		try{
			this.serverSocket = new ServerSocket(this.port);
			this.socketMode();
		}catch(IOException ioe){
			System.err.println(ioe.getMessage());
			return;
		}
		
		this.executor.shutdown();
		
	}
	
	private void socketMode(){
		while(true){
			try{
				Socket socket = this.serverSocket.accept();
				this.executor.submit(new SocketClientHandler(socket));
			}catch(IOException ex){
				break;
			}
		}
		
	
	}
	
	public int getPort(){ return this.port; }
	public String getIP(){ return this.ip; }
}
