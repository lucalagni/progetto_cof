package client.view.cli.basic;

import java.util.Scanner;

import model.basics.Village;
import client.controller.ControllerRepository;
import client.controller.data.GameDataController;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe per la visuazlizzazione in modalità CLI dei dati comuni di gioco
 * 
 * @author Luca Lagni
 *
 */
public class CliShowGameData {
	private GameDataController dataController;
	private Scanner input;
	private boolean indipendent ;
	
	public CliShowGameData(boolean indipendent,Scanner input){
		this.dataController = ControllerRepository.getInstance().getGameDataController();
		this.input = input;
		//CliClearConsole.clearConsole(false);
		this.indipendent = indipendent;
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI delle connessioni tra le citta
	 */
	public void showConnections(){
		String text = "\n----------{ Map Connections }----------\n\n ";
		
		text += this.dataController.getUserData().getMatch().getBoard().getGameMap().printConnections(new String());
		System.out.print(text);
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI dei dati di un villaggio
	 */
	public void showVillage(){
		int village = 0,villageNumber;
		boolean flag = false ;
		String text = "\n----------{ Village }----------\n\n";
		
		for(Village v : this.dataController.getUserData().getMatch().getBoard().getGameMap().getVillages()){
			text += "\n" + village + "] " + v.getName();
			village++;
		}
		
		text += "\n\nSelect a village number: ";
		System.out.print(text);
		
		try {
			villageNumber = this.input.nextInt();
		}catch(Exception ex){
			System.out.println("\nInvalid input data, retry");
			return ;
		}
		
		if(villageNumber < 0){
			System.out.println("\n[Village not found]");
			flag = true;
		}
		if(villageNumber >= this.dataController.getUserData().getMatch().getBoard().getGameMap().getVillages().length){
			 System.out.println("\n[Village not found]");
			 flag = true;
		}
		
		if(flag == false){
			System.out.println(this.dataController.getUserData().getMatch().getBoard().getGameMap().getVillages()[villageNumber].toString());
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione delle carte scoperte di una certa regione
	 */
	public void showUnhiddenPermitCards(){
		int len = this.dataController.getUserData().getMatch().getBoard().getRegions().length;
		int region = 0;
		
		System.out.print("\n----------{ Unhidden Permit Cards }----------\n\n");
		System.out.print("Select a region number beetween 0 and " + (len-1) + " : ");
		
		try {
			region = this.input.nextInt();
		}catch(Exception ex){
			System.out.println("\nInvalid input data, retry");
			return;
		}
		
		if((region < 0) || (region >= len)) System.out.print("\n[Region not found]");
		else{
			for(int i = 0; i < 2; i++) System.out.print("\n" + i + "]" + this.dataController.getUserData().getMatch().getBoard().getRegions()[region].getPermitCardsDeck().getUnhiddenCards()[i].toString());
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI dello username del giocatore
	 */
	public void showUsername(){
		System.out.print("\n----------{ Gamer Username }----------\n\n");
		System.out.print(this.dataController.getUserData().getGamer().getUsername());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI delle carte permesso inutilizzate
	 */
	public void showUnusedPermitCards(){
		System.out.print("\n----------{ Gamer Unused Permit Cards }----------\n\n");
		for(int i = 0; i < this.dataController.getUserData().getGamer().getUnusedPermitCards().size(); i++){
			System.out.print("\n" + i + "] " + this.dataController.getUserData().getGamer().getUnusedPermitCards().get(i).toString());
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI delle carte permesso utilizzate
	 */
	public void showUsedPermitCards(){
		System.out.print("\n----------{ Gamer Used Permit Cards }----------\n\n");
		for(int i = 0; i < this.dataController.getUserData().getGamer().getUnusedPermitCards().size(); i++){
			System.out.print("\n" + i + "] " + this.dataController.getUserData().getGamer().getUsedPermitCards().get(i).toString());
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI delle carte politiche del giocatore
	 */
	public void showPoliticalCards(){
		System.out.print("\n----------{ Gamer Political Cards }----------\n\n");
		for(int i = 0; i < this.dataController.getUserData().getGamer().getPoliticalCards().size(); i++){
			System.out.print("\n" + i + "] " + this.dataController.getUserData().getGamer().getPoliticalCards().get(i).toString());
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI delle monete del giocatore
	 */
	public void showCoins(){
		System.out.print("\n----------{ Gamer Coins }----------\n\n");
		System.out.print("" + this.dataController.getUserData().getGamer().getCoins());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI degli aiutanti
	 */
	public void showHelpers(){
		System.out.print("\n----------{ Gamer Helpers }----------\n\n");
		System.out.print("" + this.dataController.getUserData().getGamer().getHelpers());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI dei punti del giocatore
	 */
	public void showPoints(){
		System.out.print("\n----------{ Gamer Points }----------\n\n");
		System.out.print("" + this.dataController.getUserData().getGamer().getPoints());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del match code
	 */
	public void showMatchCode(){
		System.out.print("\n----------{ Match Code }----------\n\n");
		System.out.print(this.dataController.getUserData().getMatchCode());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del match title
	 */
	public void showMatchTitle(){
		System.out.print("\n----------{ Match Title }----------\n\n");
		System.out.print(this.dataController.getUserData().getMatch().getTitle());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI dello stato del match
	 */
	public void showMatchStatus(){
		System.out.print("\n----------{ Match Status }----------\n\n");
		System.out.print(this.dataController.getUserData().getMatch().getMatchStatus());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del giocatore attuale
	 */
	public void showMatchActualGamer(){
		System.out.print("\n----------{ Match Actual Gamer }----------\n\n");
		System.out.print("" + this.dataController.getUserData().getMatch().getActualGamer());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del giocatore prossimo
	 */
	public void showMatchNextGamer(){
		System.out.print("\n----------{ Match Next Gamer }----------\n\n");
		System.out.print("" + this.dataController.getUserData().getMatch().getNextGamer());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del match data
	 */
	public void showMatchData(){
		System.out.print("\n----------{ Match Data }----------\n\n");
		System.out.print(this.dataController.getUserData().getMatch().getDate());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI delle posizioni
	 */
	public void showPositions(){
		System.out.print("\n----------{ Match Positions }----------\n\n");
		String username ;
		int points ;
		for(int i = 0; i < this.dataController.getUserData().getMatch().getGamers().size(); i++){
			username = this.dataController.getUserData().getMatch().getGamers().get(i).getUsername();
			points = this.dataController.getUserData().getMatch().getGamers().get(i).getPoints();
			System.out.print("\nindex: " + i+ " username: " + username + " points: " + points);
		}
		
		this.showContinue();
	}
	
	
	/**
	 * Metodo per la visualizzazione in modalità CLI degli shifts del giocatore
	 */
	public void showShifts(){
		System.out.print("\n----------{ Gamer Shifts }----------\n\n");
		System.out.print("" + this.dataController.getUserData().getGamer().getShifts());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del sinottico azioni
	 */
	public void showActionSynoptic(){
		System.out.print("\n----------{ Gamer Action Synoptic }----------\n\n");
		System.out.print(this.dataController.getUserData().getActionSynoptic().toString());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI dei dati del giocatore
	 */
	public void showAllGamerData(){
		System.out.print("\n----------{ Gamer All Data }----------\n\n");
		System.out.print(this.dataController.getUserData().getGamer().toString());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del pool degli aiutanti
	 */
	public void showHelpersPool(){
		System.out.print("\n----------{ Helpers Pool }----------\n\n");
		System.out.print(this.dataController.getUserData().getMatch().getBoard().getHelpersPool().toString());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI del pool dei consiglieri
	 */
	public void showCouncilPool(){
		System.out.print("\n----------{ Nobles Pool }----------\n\n");
		System.out.print(this.dataController.getUserData().getMatch().getBoard().getNoblesPool().toString());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità cli del percorso nobiltà
	 */
	public void showNobiltyPath(){
		System.out.print("\n----------{ Nobilty Path }----------\n\n");
		System.out.print(this.dataController.getUserData().getMatch().getBoard().getNobiltyPath().toString());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione in modalità CLI dei dati del consiglio del re
	 */
	public void showKingCouncil(){
		System.out.print("\n----------{ King }----------\n\n");
		System.out.print("" + this.dataController.getUserData().getMatch().getBoard().getKing().toString());
		
		this.showContinue();
	}
	
	private void showContinue(){
		if(this.indipendent == false) return ;
		System.out.println("\n[press any key to continue]");
		this.input.nextLine();
		this.input.nextLine();
		return;
	}
	
	/**
	 * metodo per la visualizzazione in modalità CLI dei dati di un consiglio di regione
	 */
	public void showRegionCouncil(){
		int regionNumber = 0;
		int len = this.dataController.getUserData().getMatch().getBoard().getRegions().length;
		
		System.out.print("\n----------{ Region Council }----------\n\n");
		
		System.out.print("select a number beetween 0 and " + (len-1) + " : ");
		
		try {
			regionNumber = this.input.nextInt();
		}catch(Exception ex){
			System.out.println("\nInvalid input data, retry");
			return ;
		}
		
		if((regionNumber < 0) || (regionNumber >= len)) System.out.print("\n[Region not found]");
		else System.out.print("\n" + this.dataController.getUserData().getMatch().getBoard().getRegions()[regionNumber].toString());
		
		this.showContinue();
	}
}
