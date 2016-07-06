package client.view.cli.basic;

import java.util.Scanner;

import model.basics.supports.MatchPhase;
import client.view.cli.market.CliMarketPerformAction;
import client.view.cli.setter.CliSetterPerformAction;
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
	private MatchPhase phase ;
	
	public CliMainMenu(boolean gamerTurn,MatchPhase phase){ 
		this.setGamerTurn(gamerTurn);
		this.setMatchPhase(phase);
	}
	
	public void setMatchPhase(MatchPhase phase){
		this.phase = phase ;
		this.text = null;
	}
	
	public MatchPhase getMatchPhase(){ return this.phase; }
	
	public void setGamerTurn(boolean gamerTurn){ this.gamerTurn = gamerTurn; }
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
		this.text += "\n26)Build shop ";
		this.text += "\n27)Move King ";
		this.text += "\n28)Buy Permit Card";
		this.text += "\n29)Change noble";
		
		this.text += "\n\n-----{ Helpers Actions }-----\n";
		this.text += "\n30)Buy helper";
		this.text += "\n31)Change noble";
		this.text += "\n32)Double action ";
		this.text += "\n33)New Main Action";
		
		this.text += "\n\n-----{ Special Actions }------\n";
		this.text += "\n34)Acquire permit card (untested)";
		this.text += "\n35)Reuse permit card bonus (untested)";
		this.text += "\n36)Acquire single village bonus (untested)";
		this.text += "\n37)Acquire double village bonus (untested)";
	}
	
	private void setOptionText(){
		this.text +=  "\n\n------{ Options }------\n";
		this.text += "\n0)Go offline";
		this.text += "\n\nChoice> ";
	}
	
	private void setSetterBasicText(){
		this.text += "\n\n=========={ Setter Menu }==========\n";
	}
	
	private void setSetterActionText(){
		if(this.gamerTurn == false) return ;
		
		this.text += "\n\n----------{ Choose Item to Sell }----------\n";
		this.text += "\n38)Reset Agent (Deselect all his items)";
		this.text += "\n39)Permit Card";
		this.text += "\n40)Political Card";
		this.text += "\n41)Helpers";
		this.text += "\n42)Send Agent";
	}
	
	private void setMarketBasicText(){
		this.text += "\n\n=========={ Market Menu }==========\n";
		this.text += "\n43) Show your items";
		this.text += "\n----------{ Show Items From }----------\n";
		this.text += "\n44)Show Market";
		this.text += "\n45)Show Specific Agent";
	}
	
	private void setMarketActionText(){
		if(this.gamerTurn == false) return;
		this.text += "\n----------{ Buy Item }----------\n";
		this.text += "\n46)Helpers item";
		this.text += "\n47)Political Card Item";
		this.text += "\n48)Permit Card Item";
		
	}
	
	
	public int show(){
		int choice = 0;
		boolean flag = false ;
		Scanner in = new Scanner(System.in);
		
		do
		{
			this.text = new String();
			CliClearConsole.clearConsole(false);
			this.setBasicText();
			
			if(this.phase.equals(MatchPhase.SETTER_PHASE))this.setSetterBasicText();
			if(this.phase.equals(MatchPhase.MARKET_PHASE))this.setMarketBasicText();
			if(this.gamerTurn == true){
				if(this.phase.equals(MatchPhase.MATCH_PHASE))this.setActionText();
				if(this.phase.equals(MatchPhase.SETTER_PHASE))this.setSetterActionText();
				if(this.phase.equals(MatchPhase.MARKET_PHASE))this.setMarketActionText();
			}
			
			this.setOptionText();
			
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
					new CliShowGameData(true,in).showConnections();
					break;
				case 2:
					new CliShowGameData(true,in).showVillage();
					break;
				case 3:
					new CliShowGameData(true,in).showRegionCouncil();
					break;
				case 4:
					new CliShowGameData(true,in).showKingCouncil();
					break;
				case 5:
					new CliShowGameData(true,in).showUnhiddenPermitCards();
					break;
				case 6:
					new CliShowGameData(true,in).showNobiltyPath();
					break;
				case 7:
					new CliShowGameData(true,in).showHelpersPool();
					break;
				case 8:
					new CliShowGameData(true,in).showCouncilPool();
					break;
				case 9:
					new CliShowGameData(true,in).showUsername();
					break;
				case 10:
					new CliShowGameData(true,in).showUnusedPermitCards();
					break;
				case 11:
					new CliShowGameData(true,in).showUsedPermitCards();
					break;
				case 12:
					new CliShowGameData(true,in).showPoliticalCards();
					break;
				case 13:
					new CliShowGameData(true,in).showCoins();
					break;
				case 14:
					new CliShowGameData(true,in).showHelpers();
					break;
				case 15:
					new CliShowGameData(true,in).showPoints();
					break;
				case 16:
					new CliShowGameData(true,in).showShifts();
					break;
				case 17:
					new CliShowGameData(true,in).showAllGamerData();
					break;
				case 18:
					new CliShowGameData(true,in).showMatchCode();
					break;
				case 19:
					new CliShowGameData(true,in).showMatchTitle();
					break;
				case 20:
					new CliShowGameData(true,in).showMatchData();
					break;
				case 21:
					new CliShowGameData(true,in).showMatchStatus();
					break;
				case 22:
					new CliShowGameData(true,in).showMatchActualGamer();
					break;
				case 23:
					new CliShowGameData(true,in).showMatchNextGamer();
					break;
				case 24:
					new CliShowGameData(true,in).showPositions();
					break;
				case 25:
					new CliShowGameData(true,in).showActionSynoptic();
					break;
				case 26:
					if(this.gamerTurn == false) continue;
					new CliPerformAction().buildShop();
					break;
				case 27:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().moveKing();
					break;
				case 28:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().buyPermitCard();
					break;
				case 29:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().changeNoble(true);
					break;
				case 30:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().buyHelper();
					break;
				case 31:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().changeNoble(false);
					break;
				case 32:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().doubleAction();
					break;
				case 33:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().buyNewMainAction();
					break;
				case 34:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().acquirePermitCard();
					break;
				case 35:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().reusePermitCardBonus();
					break;
				case 36:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().acquireSingleVillageBonus();
					break;
				case 37:
					if(this.gamerTurn == false)continue;
					if(this.phase.equals(MatchPhase.MATCH_PHASE) == false)continue;
					new CliPerformAction().acquireDoubleVillageBonus();
					break;
				case 38:
					if(this.gamerTurn == false)continue;
					if(this.phase.equals(MatchPhase.SETTER_PHASE) == false)continue;
					new CliSetterPerformAction().resetAgent();
					break;
				case 39:
					if(this.gamerTurn == false)continue;
					if(this.phase.equals(MatchPhase.SETTER_PHASE) == false)continue;
					new CliSetterPerformAction().addPermitCardItem();
					break;
				case 40:
					if(this.gamerTurn == false)continue;
					if(this.phase.equals(MatchPhase.SETTER_PHASE) == false)continue;
					new CliSetterPerformAction().addPoliticalCardItem();
					break;
				case 41:
					if(this.gamerTurn == false)continue;
					if(this.phase.equals(MatchPhase.SETTER_PHASE) == false)continue;
					new CliSetterPerformAction().addHelpersItem();
					break;
				case 42:
					if(this.gamerTurn == false)continue;
					if(this.phase.equals(MatchPhase.SETTER_PHASE) == false)continue;
					new CliSetterPerformAction().sendAgent();
					break;
				case 43:
					if(this.phase.equals(MatchPhase.MARKET_PHASE) == false)continue;
					new CliMarketPerformAction().showYourItems();
					break;
				case 44:
					if(this.phase.equals(MatchPhase.MARKET_PHASE) == false)continue;
					new CliMarketPerformAction().showMarket();
					break;
				case 45:
					if(this.phase.equals(MatchPhase.MARKET_PHASE) == false)continue;
					new CliMarketPerformAction().showSpecificAgent();
					break;
				case 46:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MARKET_PHASE) == false)continue;
					new CliMarketPerformAction().buyHelpersItem();
					break;
				case 47:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MARKET_PHASE) == false)continue;
					new CliMarketPerformAction().buyPoliticalCardItem();
					break;
				case 48:
					if(this.gamerTurn == false) continue;
					if(this.phase.equals(MatchPhase.MARKET_PHASE) == false)continue;
					new CliMarketPerformAction().buyPermitCardItem();
					break;
				case 0:
					new CliShowGameData(true,in).goOffline();
					flag = true;
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