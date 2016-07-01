package client.view.cli.basic;

import java.util.Scanner;

public class MainCliMenu {
	private String menu ;
	
	private void basicMenuString(){
		
		this.menu += "\n=========={ Main Menu }==========\n";
		
		this.menu += "\n-----{ Show Game Data}-----\n";
		
		this.menu += "\n01)Connections";
		this.menu += "\n02)Village";
		this.menu += "\n03)Region council";
		this.menu += "\n04)King council";
		this.menu += "\n05)Unhidden permit cards";
		this.menu += "\n06)Nobilty path";
		this.menu += "\n07)Helpers Pool";
		this.menu += "\n08)Council Pool";
		
		this.menu += "\n\n-----{ Show Gamer Data}-----\n";
		
		this.menu += "\n09)Gamer";
		this.menu += "\n10)Unhused Permit Cards";
		this.menu += "\n11)Used Permit Cards";
		this.menu += "\n12)Political Cards";
		this.menu += "\n13)Coins";
		this.menu += "\n14)Helpers";
		this.menu += "\n15)Points";
		this.menu += "\n16)Shifts";
		
		this.menu += "\n\n-----{ Show Match Data}-----\n";
		
		this.menu += "\n17)Match Code";
		this.menu += "\n18)Match Title";
		this.menu += "\n19)Match Data";
		this.menu += "\n20)Match positions";
		this.menu += "\n21)Actual gamer";
		this.menu += "\n22)Next gamer";
		
	}
	
	private void actionsMenuString(){
		
		this.menu += "\n\n=========={ Action Menu }==========\n";
		
		this.menu += "\n\n-----{ Action Synoptic }-------\n";
		
		this.menu += "\n23)Show Action Synoptic";
		
		this.menu += "\n\n-----{ Main Actions }-----\n";
		
		this.menu += "\n24)Build shop";
		this.menu += "\n25)Move King";
		this.menu += "\n26)Buy Permit Card";
		this.menu += "\n27)Change noble";
		
		this.menu += "\n\n-----{ Helpers Actions }-----\n";
		
		this.menu += "\n28)Buy helper";
		this.menu += "\n29)Change noble";
		this.menu += "\n30)Double action";
		this.menu += "\n31)New Main Action";
		
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

			
		}while(choice != 0);
		
		return choice;
	}
}
