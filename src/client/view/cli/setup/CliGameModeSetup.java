package client.view.cli.setup;

import java.util.Scanner;

import commons.data.GameMode;

import client.controller.ControllerRepository;

/**
 * Classe per la visualizzazione grafica cli del metodo di interscambio 
 * dati tra client e server.
 * 
 * @author Luca Lagni
 *
 */
public class CliGameModeSetup {
	private String menu;
	private Scanner input;
	
	public CliGameModeSetup(){
		this.input = new Scanner(System.in);
		this.setupMenu();
	}
	
	private void setupMenu(){
		this.menu = new String("\n=========={ Game Mode Setup }==========\n");
		
		this.menu += "\n1)SOCKET";
		this.menu += "\n2)RMI" ;
		this.menu += "\n0)Exit";
		this.menu += "\n\nChoice> ";
	}
	
	public int show(){
		int choice = -1;
		boolean flag = false ;
		
		do
		{
			System.out.print(this.menu);
			try{
				choice = Integer.parseInt(this.input.nextLine());
			}catch(Exception ex){
				System.out.println("\nInvalid input data, retry");
				flag = false ;
				continue;
			}
			
			switch(choice){
				case 1:
					ControllerRepository.getInstance().setClientController(GameMode.SOCKET);
					System.out.println("\nSOCKET GameMode setted");
					flag = true;
					break;
				case 2:
					ControllerRepository.getInstance().setClientController(GameMode.RMI);
					System.out.println("\nRMI GameMode setted");
					flag = true;
					break;
				case 0:
					System.out.println("\nExited");
					flag = true;
					break;
				default :
					System.out.println("\nInvalid choice, retry");
					flag = false ;
					break;
			}
			
		}while(flag == false);
		
		return choice;
	}
}
