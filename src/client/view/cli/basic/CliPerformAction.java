package client.view.cli.basic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import model.basics.constants.KingConstants;
import model.basics.constants.PermitCardsDeckConstants;
import client.controller.ControllerRepository;
import client.controller.actions.basics.ActionController;
import client.controller.data.GameDataController;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe che si occupa di offrire la cli per la gestione delle mosse
 * @author lucal
 *
 */
public class CliPerformAction {
	private GameDataController dataController ;
	private ActionController actionController ;
	private Scanner input;
	
	public CliPerformAction(Scanner input){
		this.dataController = ControllerRepository.getInstance().getGameDataController();
		ControllerRepository.getInstance().setActionController();
		this.actionController = ControllerRepository.getInstance().getActionController();
		this.input = input;
		CliClearConsole.clearConsole(false);
		
	}
	
	private void showContinue(){
		System.out.println("\n[press any key to continue]");
		this.input.nextLine();
		return;
	}
	
	/**
	 * Metodo per lo spostamento del re
	 */
	public void moveKing(){
		ArrayList<String> pathList = new ArrayList<String>();
		ArrayList<Integer> cards = new ArrayList<Integer>();
		boolean flag = true ;
		
		System.out.println("\n----------{ Move King }----------\n");
		System.out.println("\nActual Position: " + this.dataController.getUserData().getMatch().getBoard().getKing().getPosition());
		System.out.println("\nCoins for movement: " + KingConstants.COINS_FOR_MOVEMENT);
		System.out.println(this.dataController.getUserData().getMatch().getBoard().getKing().getCouncil().toString());
		new CliShowGameData(false, this.input).showPoliticalCards();
		
		do{
			System.out.println("\nSelect a card index: ");
			try{
				cards.add(Integer.parseInt(this.input.nextLine()));
			}catch(Exception ex){
				System.out.println("\nInvalid input data");
				continue;
			}
			System.out.println("\nSelect another card (true/false): ");
			try{
				flag = Boolean.parseBoolean(this.input.nextLine());
			}catch(Exception ex){
				System.out.println("\nInvalid input data");
				continue;
			}
		}while(flag == true);
		
		new CliShowGameData(false, this.input).showConnections();
		do{
			System.out.println("\nSelect village: ");
			pathList.add(""+this.input.nextLine().charAt(0));
			System.out.println("\nSelect another village (true/false)? ");
			try{
				flag = Boolean.parseBoolean(this.input.nextLine());
			}catch(Exception ex){
				System.out.println("\nInvalid input data");
				this.showContinue();
				continue;
			}
		}while(flag == true);
		
		this.actionController.moveKing(pathList, cards);
		this.showContinue();
		
	}
	
	/**
	 * Metodo per l'acquisizione del bonus di un singolo villaggio
	 */
	public void acquireSingleVillageBonus(){
		char village ;
		System.out.println("\n----------{ Acquire Single Village Bonus }----------\n");
		System.out.println("\nSelect a village: ");
		village = this.input.nextLine().charAt(0);
		
		this.actionController.acquireSingleVillageBonus(village);
		this.showContinue();
	}
	
	/**
	 * Metodo per l'acquisizione del bonus di due villaggi
	 */
	public void acquireDoubleVillageBonus(){
		char village1, village2 ;
		System.out.println("\n----------{ Acquire Double Village Bonus }----------\n");
		System.out.println("\nSelect the first village: ");
		village1 = this.input.nextLine().charAt(0);
		System.out.println("\nSelect the second village: ");
		village2 = this.input.nextLine().charAt(0);
		
		this.actionController.acquireDoubleVillageBonus(village1, village2);
		this.showContinue();
	}
	
	/**
	 * Metodo per la scelta della carta permesso di cui riutilizzare il Bonus in versione CLI
	 */
	public void reusePermitCardBonus(){
		boolean used = false ;
		int permitCard = 0;
		System.out.println("\n----------{ Reuse Permit Card Bonus}----------\n");
		System.out.println("\n+++{ Unused permit cards }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getGamer().getUnusedPermitCards().size(); i++){
			System.out.println(this.dataController.getUserData().getGamer().getUnusedPermitCards().get(i).toString());
		}
		
		System.out.println("\n+++{ Used permit cards }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getGamer().getUsedPermitCards().size(); i++){
			System.out.println(this.dataController.getUserData().getGamer().getUsedPermitCards().get(i).toString());
		}
		
		System.out.println("\nSelect a used permit card? (true/false): ");
		try{
			used = Boolean.parseBoolean(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		System.out.println("\nSelect an idex: ");
		try{
			permitCard = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		this.actionController.reusePermitCardBonus(permitCard, used);
		this.showContinue();
	}
	
	/**
	 * Interfaccia CLI per l'acquisizione di una carta permesso senza pagare
	 */
	public void acquirePermitCard(){
		int region = 0;
		int permitCardIndex = 0;
		System.out.println("\n----------{ Acquire Permit Card }----------\n");
		System.out.println("\nSelect a region (0 - " + this.dataController.getUserData().getMatch().getBoard().getRegions().length + ") : ");
		
		try{
			region = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return;
		}
		
		if((region < 0) || (region > this.dataController.getUserData().getMatch().getBoard().getRegions().length)){
			System.out.println("\nInvalid input data");
			return ;
		}
		
		for(int i = 0; i < PermitCardsDeckConstants.UNHIDDEN_CARDS_FOR_REGION; i++) System.out.print("\n" + i + "]" + this.dataController.getUserData().getMatch().getBoard().getRegions()[region].getPermitCardsDeck().getUnhiddenCards()[i].toString());
		
		System.out.println("\nSelect a permit card index: ");
		try{
			permitCardIndex = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return;
		}
		
		this.actionController.acquirePermitCard(region, permitCardIndex);
		this.showContinue();
	}
	
	/**
	 * Interfaccia CLI per la doppia azione
	 */
	public void doubleAction(){
		int region = 0;
		System.out.println("\n----------{ Double Action }----------\n");
		System.out.println("\nSelect a region (0 - " + this.dataController.getUserData().getMatch().getBoard().getRegions().length + ") : ");
		
		try{
			region = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return;
		}
		
		if((region < 0) || (region > this.dataController.getUserData().getMatch().getBoard().getRegions().length)){
			System.out.println("\nInvalid input data");
			return ;
		}
		
		this.actionController.doubleAction(region);
		this.showContinue();
	}
	
	/**
	 * Interfaccia CLI per il piazzamento di un emporio
	 */
	public void buildShop(){
		int cardIndex = 0;
		char village = 0;
		System.out.println("\n----------{ Build Shop }----------\n");
		new CliShowGameData(false,this.input).showUnusedPermitCards();
		System.out.println("\nSelect a permit card index: ");
		try {
			cardIndex = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		if((cardIndex < 0) || (cardIndex >= this.dataController.getUserData().getGamer().getUnusedPermitCards().size())){
			System.out.println("\nInvalid card index");
			this.showContinue();
			return;
		}
		
		System.out.println("\nSelect a village: ");
		try {
			village = this.input.nextLine().charAt(0);
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		this.actionController.placeShop(village, cardIndex);
		this.showContinue();
	}
	
	/**
	 * Metodo per il cambio di un nobile nella regione
	 */
	public void changeNoble(boolean mainAction){
		int color = 0;
		int region = 0;
		boolean king = false;
		System.out.println("\n----------{ Change Noble }----------\n");
		System.out.println("Is a king noble (true/false): ");
		
		try{
			king = Boolean.parseBoolean(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			return ;
		}
		
		
		if(king == false){
			System.out.println("Select region (0, 1, 2): ");
			
			try{
				region = Integer.parseInt(this.input.nextLine());
			}catch(Exception ex){
				System.out.println("\nInvalid input data");
				return ;
			}
			
			if((region < 0) || (region > 2)){
				System.out.println("\nInvalid input data");
				return;
			}
			
			System.out.println("+++{ Council }+++");
			System.out.println( this.dataController.getUserData().getMatch().getBoard().getRegions()[region].getCouncil().toString() );
	
			
		}
		
		System.out.println("+++{Available Nobles}+++");
		if(this.dataController.getUserData().getMatch().getBoard().getNoblesPool().getBlackNoblesLeft() > 0){
			System.out.println("\n0)BLACK");
		}
		if(this.dataController.getUserData().getMatch().getBoard().getNoblesPool().getCyanNoblesLeft() > 0){
			System.out.println("\n1)CYAN");
		}
		
		if(this.dataController.getUserData().getMatch().getBoard().getNoblesPool().getMagentaNoblesLeft() > 0){
			System.out.println("\n2)MAGENTA");
		}
		
		if(this.dataController.getUserData().getMatch().getBoard().getNoblesPool().getOrangeNoblesLeft() > 0){
			System.out.println("\n3)GUGLIELMO D'(ORANGE)");
		}
		
		if(this.dataController.getUserData().getMatch().getBoard().getNoblesPool().getPinkNoblesLeft() > 0){
			System.out.println("\n4)PINK");
		}
		
		if(this.dataController.getUserData().getMatch().getBoard().getNoblesPool().getWhiteNoblesLeft() > 0){
			System.out.println("\n5)WHITE");
		}
		
		System.out.println("\nSelect color: ");
		
		try{
			color = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			return ;
		}
		
		if((color < 0) || (color > 5)) {
			System.out.println("\nInvalid color");
			return ;
		}
		
		if(king == true){
			if(color == 0) this.actionController.changeNoble(true, 0, Color.BLACK, mainAction);
			if(color == 1) this.actionController.changeNoble(true, 0, Color.CYAN, mainAction);
			if(color == 2) this.actionController.changeNoble(true, 0, Color.MAGENTA, mainAction);
			if(color == 3) this.actionController.changeNoble(true, 0, Color.ORANGE, mainAction);
			if(color == 4) this.actionController.changeNoble(true, 0, Color.PINK, mainAction);
			if(color == 5) this.actionController.changeNoble(true, 0, Color.WHITE, mainAction);
			
		}
		else
		{
			if(color == 0) this.actionController.changeNoble(false, region, Color.BLACK, mainAction);
			if(color == 1) this.actionController.changeNoble(false, region, Color.CYAN, mainAction);
			if(color == 2) this.actionController.changeNoble(false, region, Color.MAGENTA, mainAction);
			if(color == 3) this.actionController.changeNoble(false, region, Color.ORANGE, mainAction);
			if(color == 4) this.actionController.changeNoble(false, region, Color.PINK, mainAction);
			if(color == 5) this.actionController.changeNoble(false, region, Color.WHITE, mainAction);
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la compravedita di azioni
	 */
	public void buyNewMainAction(){
		boolean buyAction = false;
		System.out.println("\n----------{ Buy New Main Action }----------\n");
		System.out.println("buy new main action (true/false): ");
		
		try{
			buyAction = Boolean.parseBoolean(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
		}
		
		if(buyAction == true)this.actionController.buyNewMainAction();
		else System.out.println("\nAction not porformed");
		
		this.showContinue();
	}
	
	/**Metodo per la richiesta di compravendita di aiutanti mediante CLI
	 * 
	 */
	public void buyHelper(){
		boolean buyHelper = false;
		System.out.println("\n----------{ Buy Helper }----------\n");
		System.out.println("buy helper (true/false): ");
		
		try{
			buyHelper = Boolean.parseBoolean(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
		}
		
		if(buyHelper == true)this.actionController.buyHelper(false);
		else System.out.println("\nAction not porformed");
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la richiesta della carta permesso da acquisire tramite CLI
	 */
	public void buyPermitCard(){
		System.out.println("\n----------{ Buy Permit Card }----------\n\n");
		int len = this.dataController.getUserData().getMatch().getBoard().getRegions().length;
		int region,index;
		boolean flag = true;
		
		ArrayList<Integer> cards = new ArrayList<Integer>();
		System.out.println("Select a region number beetween 0 and " + (len-1) + " : ");
		region = Integer.parseInt(this.input.nextLine());
		
		if((region < 0) || (region >= len)) System.out.println("\n[Region not found]");
		else{
			System.out.println("\n+++{ Council }+++\n");
			System.out.println(this.dataController.getUserData().getMatch().getBoard().getRegions()[region].getCouncil().toString());
			System.out.println("\n+++{ Permit Cards }+++\n");
			System.out.println("0]" + this.dataController.getUserData().getMatch().getBoard().getRegions()[region].getPermitCardsDeck().getUnhiddenCards()[0]);
			System.out.println("1]" + this.dataController.getUserData().getMatch().getBoard().getRegions()[region].getPermitCardsDeck().getUnhiddenCards()[1]);
			System.out.println("\nSelect an index: ");
			index = Integer.parseInt(this.input.nextLine());
			new CliShowGameData(false, this.input).showPoliticalCards();
			do{
				System.out.println("\nSelect a card index: ");
				cards.add(Integer.parseInt(this.input.nextLine()));
				System.out.println("\nSelect another card (true/false): ");
				flag = Boolean.parseBoolean(this.input.nextLine());
			}while(flag == true);
			
			int pCards[] = new int[cards.size()];
			for(int i = 0; i < pCards.length; i++){
				pCards[i] = cards.get(i).intValue();
			}
			
			this.actionController.buyPermitCard(region, index, pCards);
			
		}
		
		this.showContinue();
		
	}
	
}
