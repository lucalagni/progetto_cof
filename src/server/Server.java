package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import server.rmi.actions.basics.HelpersActionsRmi;
import server.rmi.actions.basics.MainActionsRmi;
import server.rmi.actions.basics.SpecialActionsRmi;
import server.rmi.actions.market.MarketActionsRmi;
import server.rmi.actions.setter.SetterActionsRmi;
import server.rmi.connections.ClientRequestCanIPlayRmi;
import server.rmi.connections.ClientRequestGamerTurnRmi;
import server.rmi.connections.ClientRequireToPlayRmi;
import server.socket.SocketMultiserver;

public class Server {
	private static final int PORT = 6789;
	private static final String IP = "127.0.0.1";
	private static Scanner input;
	
	public static void main(String[] args){
		System.out.println(" *** CLI SERVER ***");
		
		//Inizializzazione del server socket
		SocketMultiserver socketServer = new SocketMultiserver(PORT);
		Thread socketThread = new Thread(socketServer);
		socketThread.start();
		input = new Scanner(System.in);
				
		//inizializzazione di RMI
		try {
			ClientRequireToPlayRmi clientRequireToPlayRmi = new ClientRequireToPlayRmi();
			ClientRequestCanIPlayRmi clientRequestCanIPlayRmi = new ClientRequestCanIPlayRmi();
			ClientRequestGamerTurnRmi clientRequestGamerTurnRmi = new ClientRequestGamerTurnRmi();
			
			MainActionsRmi mainActionsRmi = new MainActionsRmi();
			HelpersActionsRmi helpersActionsRmi = new HelpersActionsRmi();
			SpecialActionsRmi specialActionsRmi = new SpecialActionsRmi();
			
			SetterActionsRmi setterActionsRmi = new SetterActionsRmi();
			
			MarketActionsRmi marketActionsRmi = new MarketActionsRmi();
			
			Registry registry = LocateRegistry.getRegistry();
			
			registry.bind("client_require_to_play_rmi", clientRequireToPlayRmi);
			registry.bind("client_request_can_i_play_rmi", clientRequestCanIPlayRmi);
			registry.bind("client_request_gamer_turn_rmi", clientRequestGamerTurnRmi);
			
			registry.bind("main_actions_rmi", mainActionsRmi);
			registry.bind("helpers_actions_rmi", helpersActionsRmi);
			registry.bind("special_actions_rmi", specialActionsRmi);
			
			registry.bind("setter_actions_rmi", setterActionsRmi);
			registry.bind("market_actions_rmi", marketActionsRmi);
			
		} catch (RemoteException | AlreadyBoundException e) { 
			System.out.println("\n[Server] problems with RMI classes");
			System.out.println("\nerror: " + e.getMessage());
		}
		
		while(true){
			String cmd = "";
			System.out.println("[Server] COMMAND: ");
			cmd = input.nextLine();
			
			if( cmd.equals("EXIT") ){
				socketThread.interrupt();
				socketServer.stop();
				break;
			}
		}
	} 

	public int getPort(){ return PORT; }
	public String getIP(){ return IP; }
}
