package view.client.setup;

import java.util.Scanner;

import client.GameMode;
import controller.ControllerRepository;

public class ViewGameModeSetup {
	private String text;
	
	private String setText(){
		text = "\n=========={ Game Mode : Connection Setup }==========\n\n";
		
		text += "1)Socket \n";
		text += "2)RMI \n";
		text += "3)Exit \n";
		text += "\nChoiche> ";
		
		return text;
	}
	
	public boolean show(){
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int mode;
		boolean flag = false;
		boolean setted = false;
		this.setText();
		
		do{
			System.out.println(this.text);
			mode = Integer.parseInt(input.nextLine());
			
			switch(mode){
				case 1:
					ControllerRepository.getInstance().setClientController(GameMode.SOCKET);
					flag = true;
					setted = true;
					break;
				case 2:
					ControllerRepository.getInstance().setClientController(GameMode.RMI);
					flag = true;
					setted = true;
					break;
				case 3:
					flag = true;
					setted = false;
					break;
				default:
					System.out.println("\nInvalid input, retry\n");
					flag = false ;
					setted = false ;
					break;
			}
		}while(flag == false);
		
		return setted;
		
	}
	
	public ViewGameModeSetup(){
	}
}
