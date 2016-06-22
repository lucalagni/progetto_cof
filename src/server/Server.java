package server;

import server.socket.SocketMultiserver;

public class Server {
	private static final int PORT = 6789;
	private static final String IP = "127.0.0.1";
	
	public static void main(String[] args){
		SocketMultiserver socketServer = new SocketMultiserver(PORT);
		socketServer.start();
	}

	public int getPort(){ return PORT; }
	public String getIP(){ return IP; }
}
