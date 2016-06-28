package server;

import java.util.Scanner;

import server.socket.SocketMultiserver;

public class Server {
	private static final int PORT = 6789;
	private static final String IP = "127.0.0.1";
	private static Scanner input;
	
	public static void main(String[] args){
		System.out.println(" *** CLI server ***");
		SocketMultiserver socketServer = new SocketMultiserver(PORT);
		Thread socketThread = new Thread(socketServer);
		socketThread.start();
		input = new Scanner(System.in);
				
		while(true){
			String cmd = "";
			System.out.println("command: ");
			cmd = input.nextLine();
			
			if( cmd.equals("ESCI") ){
				socketThread.interrupt();
				socketServer.stop();
				break;
			}
		}
	} 

	public int getPort(){ return PORT; }
	public String getIP(){ return IP; }
}
