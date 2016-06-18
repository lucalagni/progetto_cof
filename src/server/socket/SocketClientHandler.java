package server.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClientHandler implements Runnable{
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	
	public SocketClientHandler(Socket socket){ 
		this.socket = socket; 
	}
	
	@Override
	public void run(){
		try{
			this.in = new Scanner(this.socket.getInputStream());
			this.out = new PrintWriter(this.socket.getOutputStream());
			
			while(true){
				break;
			}
			
			this.in.close();
			this.out.close();
			this.socket.close();
			
		}catch(IOException ex){}
	}
}
