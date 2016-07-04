package client.view.cli.setup;

import java.util.Scanner;

import client.view.cli.utils.CliClearConsole;

/**
 * 
 * Classe che permette di selezionare il tipo di console su cui mostrare la cli
 * 
 * @author Luca Lagni
 *
 */
public class CliConsoleType {
	private String text ;
	private Scanner input ;
	
	public CliConsoleType(){
		this.input = new Scanner(System.in);
		this.setText();
	}
	
	private void setText(){
		this.text += "\n========={ Console Type }==========\n";
		
		this.text += "\n1)Operating System Console ";
		this.text += "\n2)IDE Console";
		this.text += "\n\nChoice> ";
	}
	
	public int show(){
		int choice = -1;
		boolean flag = false ;
		
		do{
			System.out.print(this.text);
			
			try{
				choice = Integer.parseInt(this.input.nextLine());
			}catch(Exception ex){
				System.out.println("\n[CliConsoleType] INVALID CHOICE , RETRY");
				flag = false ;
				continue;
			}
			
			switch(choice){
				case 1:
					CliClearConsole.getInstance();
					System.out.println("\nOS Console setted");
					flag = true;
					break;
				case 2:
					CliClearConsole.getInstance();
					CliClearConsole.isIde();
					System.out.println("\nIDE Console setted");
					flag = true;
					break;
				default:
					System.out.println("\nInvalit choice, retry");
					flag = false ;
					break;
			}
			
		}while(flag == false);
		
		return choice;
	}
}
