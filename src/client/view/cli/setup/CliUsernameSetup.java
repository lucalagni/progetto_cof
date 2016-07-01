package client.view.cli.setup;

import java.util.Scanner;

import client.controller.ControllerRepository;
import client.controller.connections.scheduler.GameConnectionScheduler;
import client.view.cli.utils.CliClearConsole;
import commons.data.UserData;

/**
 * Classe per la visualizzazione grafica CLI del metodo di immissione username
 * 
 * @author Luca Lagni
 *
 */
public class CliUsernameSetup {
	private String text ;
	private Scanner input;
	
	public CliUsernameSetup(){
		this.input = new Scanner(System.in);
		this.setText();
	}
	
	private void setText(){
		this.text = new String("\n=========={ Select Username }==========\n");
		
		this.text += "\n\nInsert: ";
	}
	
	public int show(){
		boolean flag = false;
		String username = null;
		
		do
		{
			CliClearConsole.clearConsole(false);
			System.out.print(this.text);
			try{
				username = this.input.nextLine();
			}catch(Exception ex){
				System.out.println("\nInvalid input type, retry");
				flag = false;
				continue;
			}
			
			if(username.length() < 1){
				System.out.println("\nInvalid username, retry");
				flag = false ;
			}
			else{
				System.out.println("\nUsername setted: " + username);
				ControllerRepository.getInstance().setGameDataController(new UserData(username));
				
				flag = true;
			}
			
		}while(flag == false);
		
		return 0;
	}
}
