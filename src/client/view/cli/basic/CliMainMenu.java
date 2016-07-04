package client.view.cli.basic;

import java.util.Scanner;

import client.view.cli.utils.CliClearConsole;


/**
 * Classe che mostra il sinottico principale per le azioni di gioco in modalità CLI
 * 
 * @author Luca Lagni
 *
 */
public class CliMainMenu extends Thread{
	private String text ;
	private boolean gamerTurn;
	private Scanner input;
	
	public CliMainMenu(boolean gamerTurn){ 
		this.input = new Scanner(System.in);
		this.setGamerTurn(gamerTurn);
	}
	
	private void setGamerTurn(boolean gamerTurn){ this.gamerTurn = gamerTurn; }
	public boolean getGamerTurn(){ return this.gamerTurn ; }
	
	private void setBasicText(){
		this.text += "\n=========={ Main Menu }==========\n";
		
		this.text += "\n-----{ Show Game Data}-----\n";
		
		this.text += "\n01)Connections";
		this.text += "\n02)Village";
		this.text += "\n03)Region council";
		this.text += "\n04)King";
		this.text += "\n05)Unhidden permit cards";
		this.text += "\n06)Nobilty path";
		this.text += "\n07)Helpers Pool";
		this.text += "\n08)Nobles Pool";
		
		this.text += "\n\n-----{ Show Gamer Data}-----\n";
		
		this.text += "\n09)Username";
		this.text += "\n10)Unhused Permit Cards";
		this.text += "\n11)Used Permit Cards";
		this.text += "\n12)Political Cards";
		this.text += "\n13)Coins";
		this.text += "\n14)Helpers";
		this.text += "\n15)Points";
		this.text += "\n16)Shifts";
		this.text += "\n17)Show all";
		
		this.text += "\n\n-----{ Show Match Data}-----\n";
		
		this.text += "\n18)Match Code";
		this.text += "\n19)Match Title";
		this.text += "\n20)Match Data";
		this.text += "\n21)Match Status";
		this.text += "\n22)Actual gamer";
		this.text += "\n23)Next gamer";
		this.text += "\n24)Positions" ;
		
		this.text += "\n\n-----{ Action Synoptic }-------\n";
		this.text += "\n25)Show Action Synoptic";
	}
	
	private void setActionText(){
		if(this.gamerTurn == false) return ;

		this.text += "\n\n=========={ Action Menu }==========\n";
		
		this.text += "\n\n-----{ Main Actions }-----\n";
		this.text += "\n26)Build shop";
		this.text += "\n27)Move King";
		this.text += "\n28)Buy Permit Card";
		this.text += "\n29)Change noble";
		
		this.text += "\n\n-----{ Helpers Actions }-----\n";
		this.text += "\n30)Buy helper";
		this.text += "\n31)Change noble";
		this.text += "\n32)Double action";
		this.text += "\n33)New Main Action";
		
		this.text += "\n\n-----{ Special Actions }------\n";
		this.text += "\n34)Acquire permit card";
		this.text += "\n35)Reuse permit card bonus";
		this.text += "\n36)Acquire single village bonus";
		this.text += "\n37)Acquire double village bonus";
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
					new CliShowGameData(true).showConnections();
					break;
				case 2:
					new CliShowGameData(true).showVillage();
					break;
				case 3:
					new CliShowGameData(true).showRegionCouncil();
					break;
				case 4:
					new CliShowGameData(true).showKingCouncil();
					break;
				case 5:
					new CliShowGameData(true).showUnhiddenPermitCards();
					break;
				case 6:
					new CliShowGameData(true).showNobiltyPath();
					break;
				case 7:
					new CliShowGameData(true).showHelpersPool();
					break;
				case 8:
					new CliShowGameData(true).showCouncilPool();
					break;
				case 9:
					new CliShowGameData(true).showUsername();
					break;
				case 10:
					new CliShowGameData(true).showUnusedPermitCards();
					break;
				case 11:
					new CliShowGameData(true).showUsedPermitCards();
					break;
				case 12:
					new CliShowGameData(true).showPoliticalCards();
					break;
				case 13:
					new CliShowGameData(true).showCoins();
					break;
				case 14:
					new CliShowGameData(true).showHelpers();
					break;
				case 15:
					new CliShowGameData(true).showPoints();
					break;
				case 16:
					new CliShowGameData(true).showShifts();
					break;
				case 17:
					new CliShowGameData(true).showAllGamerData();
					break;
				case 18:
					new CliShowGameData(true).showMatchCode();
					break;
				case 19:
					new CliShowGameData(true).showMatchTitle();
					break;
				case 20:
					new CliShowGameData(true).showMatchData();
					break;
				case 21:
					new CliShowGameData(true).showMatchStatus();
					break;
				case 22:
					new CliShowGameData(true).showMatchActualGamer();
					break;
				case 23:
					new CliShowGameData(true).showMatchNextGamer();
					break;
				case 24:
					new CliShowGameData(true).showPositions();
					break;
				case 25:
					new CliShowGameData(true).showActionSynoptic();
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
	
	@Override
	public void run(){
		this.show();
	}
	
}