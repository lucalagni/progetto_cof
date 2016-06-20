package view.basic.cli.client;

import java.util.Scanner;

import controller.basic.MainMenuController;

public class MainMenu {
	private String menu;
	private MainMenuController mmc;
	
	public MainMenu(String matchCode,int gamerID){
		this.menu(true);
		this.mmc = new MainMenuController(matchCode,gamerID);
	}
	
	private void basicMenuString(){
		
		menu += "\n=========={ Main Menu }==========\n";
		
		menu += "\n-----{ Show Game Data}-----\n";
		
		menu += "\n01)Connections";
		menu += "\n02)Village";
		menu += "\n03)Region council";
		menu += "\n04)King council";
		menu += "\n05)Unhidden permit cards";
		menu += "\n06)Nobilty path";
		menu += "\n07)Helpers Pool";
		menu += "\n08)Council Pool";
		
		menu += "\n\n-----{ Show Gamer Data}-----\n";
		
		menu += "\n09)Gamer";
		menu += "\n10)Unhused Permit Cards";
		menu += "\n11)Used Permit Cards";
		menu += "\n12)Political Cards";
		menu += "\n13)Coins";
		menu += "\n14)Helpers";
		menu += "\n15)Points";
		menu += "\n16)Shifts";
		
		menu += "\n\n-----{ Show Match Data}-----\n";
		
		menu += "\n17)Match Code";
		menu += "\n18)Match Title";
		menu += "\n19)Match Data";
		menu += "\n20)Match positions";
		menu += "\n21)Actual gamer";
		menu += "\n22)Next gamer";
		
	}
	
	private void actionsMenuString(){
		
		menu += "\n\n=========={ Action Menu }==========\n";
		
		menu += "\n\n-----{ Main Actions }-----\n";
		
		menu += "\n23)Build shop";
		menu += "\n24)Move King";
		menu += "\n25)Buy Permit Card";
		menu += "\n26)Change noble";
		
		menu += "\n\n-----{ Helpers Actions }-----\n";
		
		menu += "\n27)Buy helper";
		menu += "\n28)Change noble";
		menu += "\n29)Double action";
		menu += "\n30)New Main Action";
		
	}
	
	private void optionsMenuString(){
		
		menu +=  "\n\n+++++{ Options }+++++\n";
		
		menu += "\n0)Exit";
	}
	
	public int menu(boolean gameTime){
		int choice = -1;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		do
		{
			CliUtils.clearConsole();
			this.basicMenuString();
			if(gameTime == true) this.actionsMenuString();
			this.optionsMenuString();
			
			System.out.println(menu);
			
			System.out.print("\nChoice>> ");
			choice = Integer.parseInt(input.nextLine());
			
			switch(choice){
				case 1:
					System.out.println(this.mmc.getConnections());
					break;
			}
			
		}while(choice != 0);
		
		return choice;
	}
	
}
