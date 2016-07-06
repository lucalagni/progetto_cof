package client.view;

import java.util.Scanner;

import client.view.cli.ViewCLI;
import commons.data.ViewMode;

/**
 * Classe che avvia il il tipo di interfaccia per l'interazione con l'utente
 * 
 * @author Luca Lagni
 *
 */
public class View {
	private ViewMode mode = null;
	private Scanner input;
	
	public View(){
		String view = new String("\n++++++++++{ Council Of Four }++++++++++\n\n");
		this.input = new Scanner(System.in);
		int choice = -1;
		boolean flag = false ;
		
		System.out.println(view);
		do{
			System.out.println("\n=========={ View Mode }==========\n" );
			System.out.print("\n1)CLI");
			System.out.print("\n2)GUI");
			System.out.print("\n0)Exit");
			System.out.print("\nChoice> ");
			
			try{
				choice = Integer.parseInt(this.input.nextLine());
			}catch(Exception ex){
				System.out.println("\nInvalid input type, retry");
				flag = false ;
				continue;
			}
			
			switch(choice){
				case 1:
					this.mode = ViewMode.CLI;
					System.out.println("\nCLI View Mode selected");
					this.CLIMode();
					flag = true;
					break;
				case 2:
					this.mode = ViewMode.GUI;
					System.out.println("\nGUI View Mode selected");
					this.GUIMode();
					flag = true;
					break;
				case 0:
					System.out.println("\nExited");
					flag = true ;
					break;
				default:
					System.out.println("\nInvalid choice retry\n");
					flag = false;
					break;
			}
		}while(flag == false);
	}
	
	private void CLIMode(){
		 new ViewCLI();
	}
	
	private void GUIMode(){}
	
	public ViewMode getViewMode(){ return this.mode; }
}

