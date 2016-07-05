package client.view.cli.setter;

import java.util.Scanner;

import client.controller.ControllerRepository;
import client.controller.data.GameDataController;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe che mostra il sinottico per le azioni di settaggio del market
 * in modalità CLI
 * 
 * @author Luca Lagni
 *
 */
public class CliSetterMainMenu extends Thread{
	private String text;
	private boolean gamerTurn;
	private GameDataController dataController;
	
	public CliSetterMainMenu(boolean gamerTurn){
		this.setGamerTurn(gamerTurn);
		this.dataController = ControllerRepository.getInstance().getGameDataController();
	}
	
	public void setGamerTurn(boolean gamerTurn){ this.gamerTurn = gamerTurn; }
	public boolean getGamerTurn(){ return this.gamerTurn; }
	
	private void setSetterBasicText(){
		this.text += "\n=========={ Setter Menu }==========\n";
		this.text += "turn of: " + this.dataController.getUserData().getMatch().getGamers().get(this.dataController.getUserData().getMatch().getActualGamer()).getUsername();
	}
	
	private void setSetterActionText(){
		if(this.gamerTurn == false) return ;
		
		this.text += "\n\n----------{ Choose Item to Sell }----------\n";
		this.text += "\n1)Reset Agent (Deselect all his items)";
		this.text += "\n2)Permit Card";
		this.text += "\n3)Political Card";
		this.text += "\n4)Helpers";
		this.text += "\n5)Send Agent";
		this.text += "\n\nChoice> ";
	}
	
	public int show(){
		int choice = 0;
		boolean flag = false ;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		do{
			this.text = new String();
			CliClearConsole.clearConsole(false);
			this.setSetterBasicText();
			if(this.gamerTurn == true)this.setSetterActionText();
			
			System.out.println(this.text);
			
			try {
				choice = in.nextInt();
				
			}catch(Exception ex){
				System.out.println("\nInvalid input data, retry");
				flag = false ;
				continue;
			}
			
			switch(choice){
				case 1:
					if(this.gamerTurn == false)continue;
					new CliSetterPerformAction().resetAgent();
					break;
				case 2:
					if(this.gamerTurn == false)continue;
					new CliSetterPerformAction().addPermitCardItem();
					break;
				case 3:
					if(this.gamerTurn == false)continue;
					new CliSetterPerformAction().addPoliticalCardItem();
					break;
				case 4:
					if(this.gamerTurn == false)continue;
					new CliSetterPerformAction().addHelpersItem();
					break;
				case 5:
					if(this.gamerTurn == false)continue;
					new CliSetterPerformAction().sendAgent();
					break;
				default:
					break;
			}
		}while(flag == false);
		
		return choice;
	}
	
	@Override
	public void run(){
		this.show();
	}
}
