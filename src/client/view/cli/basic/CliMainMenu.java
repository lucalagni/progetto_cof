package client.view.cli.basic;

import java.util.ArrayList;
import java.util.Scanner;

import client.controller.ControllerRepository;
import client.controller.actions.basics.ActionController;
import client.controller.data.GameDataController;
import client.view.cli.utils.CliClearConsole;
import model.basics.PermitCard;
import model.basics.Region;

/**
 * Classe che mostra il sinottico principale per le azioni di gioco in modalità CLI
 * 
 * @author Luca Lagni
 *
 */
public class CliMainMenu{
	private String text ;
	private boolean gamerTurn;
	private Scanner input;
	private GameDataController controller;
	private ActionController actionController;
	
	public CliMainMenu(boolean gamerTurn){ 
		this.input = new Scanner(System.in);
		this.setGamerTurn(gamerTurn);
		this.controller = ControllerRepository.getInstance().getGameDataController();
	}
	
	private void setGamerTurn(boolean gamerTurn){ this.gamerTurn = gamerTurn; }
	private boolean getGamerTurn(){ return this.gamerTurn ; }
	
	private void setBasicText(){
		this.text += "\n=========={ Main Menu }==========\n";
		
		this.text += "\n-----{ Show Game Data}-----\n";
		
		this.text += "\n01)Connections";
		this.text += "\n02)Village";
		this.text += "\n03)Region council";
		this.text += "\n04)King council";
		this.text += "\n05)Unhidden permit cards";
		this.text += "\n06)Nobilty path";
		this.text += "\n07)Helpers Pool";
		this.text += "\n08)Council Pool";
		
		this.text += "\n\n-----{ Show Gamer Data}-----\n";
		
		this.text += "\n09)Username";
		this.text += "\n10)Unhused Permit Cards";
		this.text += "\n11)Used Permit Cards";
		this.text += "\n12)Political Cards";
		this.text += "\n13)Coins";
		this.text += "\n14)Helpers";
		this.text += "\n15)Points";
		this.text += "\n16)Shifts";
		
		this.text += "\n\n-----{ Show Match Data}-----\n";
		
		this.text += "\n17)Match Code";
		this.text += "\n18)Match Title";
		this.text += "\n19)Match Data";
		this.text += "\n20)Match positions";
		this.text += "\n21)Actual gamer";
		this.text += "\n22)Next gamer";
	}
	
	private void setActionText(){
		if(this.gamerTurn == false) return ;

		this.text += "\n\n=========={ Action Menu }==========\n";
		
		this.text += "\n\n-----{ Action Synoptic }-------\n";
		this.text += "\n23)Show Action Synoptic";
		
		this.text += "\n\n-----{ Main Actions }-----\n";
		this.text += "\n24)Build shop";
		this.text += "\n25)Move King";
		this.text += "\n26)Buy Permit Card";
		this.text += "\n27)Change noble";
		
		this.text += "\n\n-----{ Helpers Actions }-----\n";
		this.text += "\n28)Buy helper";
		this.text += "\n29)Change noble";
		this.text += "\n30)Double action";
		this.text += "\n31)New Main Action";
		
		this.text += "\n\n-----{ Special Actions }------\n";
		this.text += "\n32)Acquire permit card";
		this.text += "\n33)Reuse permit card bonus";
		this.text += "\n34)Acquire single village bonus";
		this.text += "\n35)Acquire double village bonus";
	}
	
	private void setOptionText(){
		this.text +=  "\n\n------{ Options }------\n";
		this.text += "\n0)Exit";
		this.text += "\n\nChoice> ";
	}
	
	public int show(){
		int choice = -1;
		boolean flag = false ;
		
		do
		{
			this.text = null;
			CliClearConsole.clearConsole(false);
			this.setBasicText();
			if(this.gamerTurn == true) this.setActionText();
			this.setOptionText();
			
			System.out.print(this.text);
			
			try {
				choice = Integer.parseInt(this.input.nextLine());
			}catch(Exception ex){
				System.out.println("\nInvalid input data, retry");
				flag = false ;
				continue;
			}
			
			switch(choice){
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				case 12:
					break;
				case 13:
					break;
				case 14:
					break;
				case 15:
					break;
				case 16:
					break;
				case 17:
					break;
				case 18:
					break;
				case 19:
					break;
				case 20:
					break;
				case 21:
					break;
				case 22:
					break;
				case 23:
					if(this.gamerTurn == false) continue;
					this.showActionSynoptic();
					break;
				case 24:
					if(this.gamerTurn == false) continue;
					break;
				case 25:
					if(this.gamerTurn == false) continue;
					break;
				case 26:
					if(this.gamerTurn == false) continue;
					break;
				case 27:
					if(this.gamerTurn == false) continue;
					break;
				case 28:
					if(this.gamerTurn == false) continue;
					break;
				case 29:
					if(this.gamerTurn == false) continue;
					break;
				case 30:
					if(this.gamerTurn == false) continue;
					break;
				case 31:
					if(this.gamerTurn == false) continue;
					break;
				case 32:
					if(this.gamerTurn == false) continue;
					break;
				case 33:
					if(this.gamerTurn == false) continue;
					break;
				case 34:
					if(this.gamerTurn == false) continue;
					break;
				case 35:
					if(this.gamerTurn == false) continue;
					break;
				case 0:
					break;
				default:
					break;
			}
			
		}while(flag == false);
		
		return choice;
	}
	
	private void showActionSynoptic(){
		System.out.println("\n----------{ Action Synoptic }----------\n");
		System.out.println(this.controller.getUserData().getActionSynoptic().toString());
	}
	
	private void changeNoble(boolean mainAction){
		boolean king = false ;
		int in = -1 ;
		
		System.out.println("\n----------{ Change Noble }----------\n");
		
		System.out.println("\n1)king noble");
		System.out.println("\n2)region noble");
		System.out.println("\nchoice: ");
		in = Integer.parseInt(this.input.nextLine());
		
		if(in == 2){
			System.out.println("\n Select region number: ");
			in = Integer.parseInt(this.input.nextLine());
			Region r = this.controller.getUserData().getMatch().getBoard().getRegions()[in];
			for(int i = 0; i < r.getCouncil().getNobles().length; i++){
				System.out.print(" " + r.getCouncil().getNobles()[i]);
			}
		}
	}
	
	private void buildShop(){
		System.out.println("\n----------{ Build Shop }----------\n");
		ArrayList<PermitCard> unusedPermitsCards = this.controller.getUserData().getGamer().getUnusedPermitCards();
		boolean flag = false ;
		int permitCardIndex = -1;
		char village;
		
		for(int i = 0; i < unusedPermitsCards.size(); i++){
			System.out.print("\ni)permit card: " + unusedPermitsCards.get(i).toString());
		}
			
		System.out.print("\nSelect a permit card: ");
		permitCardIndex = Integer.parseInt(this.input.nextLine());
		
		System.out.println("\n----------{ Permit Card }----------\n");	
		System.out.println("\nnumber: " + permitCardIndex);
		System.out.println(unusedPermitsCards.get(permitCardIndex).toString());
		System.out.println("\nSelect village: ");
		village = this.input.nextLine().charAt(0);
		
		this.actionController.placeShop(village, permitCardIndex);
	}
}