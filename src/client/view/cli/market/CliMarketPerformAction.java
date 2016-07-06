package client.view.cli.market;

import java.util.Scanner;

import client.controller.ControllerRepository;
import client.controller.actions.market.MarketActionController;
import client.controller.data.GameDataController;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe che implementa la CLI per il market
 * @author Luca Lagni
 *
 */
public class CliMarketPerformAction {
	private GameDataController dataController;
	private MarketActionController marketController;
	private Scanner input ;
	
	public CliMarketPerformAction(){
		this.dataController = ControllerRepository.getInstance().getGameDataController();
		ControllerRepository.getInstance().setMarketActionController();
		this.marketController = ControllerRepository.getInstance().getMarketActionController();
		this.input = new Scanner(System.in);
		CliClearConsole.clearConsole(false);
	}
	
	private void showContinue(){
		System.out.println("\n[press any key to continue]");
		this.input.nextLine();
		return;
	}
	
	/**
	 * Metodo per la visualizzazione degli oggetti in vendita dell'utente
	 */
	public void showYourItems(){
		System.out.println("\n\n----------{ Your Items }-----------\n");
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().size(); i++){
			if(this.dataController.getUserData().getMatch().getMarket().getAgents().get(i).getUsername().equals(this.dataController.getUserData().getUsername())){
				System.out.println(this.dataController.getUserData().getMatch().getMarket().getAgents().get(i).toString());
				break;
			}
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione degli oggetti del market
	 */
	public void showMarket(){
		System.out.println("\n\n----------{ Market }-----------\n");
		System.out.println(this.dataController.getUserData().getMatch().getMarket().toString());
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la visualizzazione di uno specifico agente
	 */
	public void showSpecificAgent(){
		String username = null;
		System.out.println("\n\n----------{ Show Specific Agent }-----------\n");
		
		System.out.println("\nInsert the username of the seller: ");
		username = this.input.nextLine();
		
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().size(); i++){
			if(this.dataController.getUserData().getMatch().getMarket().getAgents().get(i).getUsername().equals(username)){
				System.out.println(this.dataController.getUserData().getMatch().getMarket().getAgents().get(i).toString());
				break;
			}
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la scelta dell'oggetto aiutante da comprare
	 */
	public void buyHelpersItem(){
		int seller = 0;
		int item = 0;
		System.out.println("\n\n----------{ Buy Helpers Item }-----------\n");
		
		System.out.println("\n+++{ Sellers }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().size(); i++){
			System.out.println("" + i + "] username: " + this.dataController.getUserData().getMatch().getMarket().getAgents().get(i).getUsername());
		}
		
		System.out.println("\nInsert the number of the seller: ");
		try{
			seller = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		if((seller < 0) || (seller >= this.dataController.getUserData().getMatch().getMarket().getAgents().size())){
			System.out.println("\nInvalid agent number");
			this.showContinue();
			return;
		}
		
		System.out.println("\n+++{ Helpers Items }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getHelpersStock().size(); i++){
			System.out.println("" + i + " ] " + this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getHelpersStock().get(i).toString());
		}
		
		System.out.println("\nInsert the number of the item: ");
		try{
			item = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		if((item < 0) || (item >= this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getHelpersStock().size())){
			System.out.println("\nInvalid helpers number");
			this.showContinue();
			return;
		}
		
		this.marketController.buyHelpersItem(seller, item);
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la scelta dell'oggetto carta politica da comprare
	 */
	public void buyPoliticalCardItem(){
		int seller = 0;
		int item = 0;
		System.out.println("\n\n----------{ Political Card Item }-----------\n");
		
		System.out.println("\n+++{ Sellers }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().size(); i++){
			System.out.println("" + i + "] username: " + this.dataController.getUserData().getMatch().getMarket().getAgents().get(i).getUsername());
		}
		
		System.out.println("\nInsert the number of the seller: ");
		try{
			seller = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		if((seller < 0) || (seller >= this.dataController.getUserData().getMatch().getMarket().getAgents().size())){
			System.out.println("\nInvalid agent number");
			this.showContinue();
			return;
		}
		
		System.out.println("\n+++{ Political Cards Items }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getPoliticalCardStock().size(); i++){
			System.out.println("" + i + " ] " + this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getPoliticalCardStock().get(i).toString());
		}
		
		System.out.println("\nInsert the number of the item: ");
		try{
			item = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		if((item < 0) || (item >= this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getPoliticalCardStock().size())){
			System.out.println("\nInvalid political card number");
			this.showContinue();
			return;
		}
		
		this.marketController.buyPoliticalCardItem(seller, item);
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la scelta dell'oggetto carta permesso da comprare
	 */
	public void buyPermitCardItem(){
		int seller = 0;
		int item = 0;
		System.out.println("\n\n----------{ Political Card Item }-----------\n");
		
		System.out.println("\n+++{ Sellers }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().size(); i++){
			System.out.println("" + i + "] username: " + this.dataController.getUserData().getMatch().getMarket().getAgents().get(i).getUsername());
		}
		
		System.out.println("\nInsert the number of the seller: ");
		try{
			seller = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		if((seller < 0) || (seller >= this.dataController.getUserData().getMatch().getMarket().getAgents().size())){
			System.out.println("\nInvalid agent number");
			this.showContinue();
			return;
		}
		
		System.out.println("\n+++{ Permit Cards Items }+++\n");
		for(int i = 0; i < this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getPermitCardStock().size(); i++){
			System.out.println("" + i + " ] " + this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getPermitCardStock().get(i).toString());
		}
		
		System.out.println("\nInsert the number of the item: ");
		try{
			item = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
			this.showContinue();
			return ;
		}
		
		if((item < 0) || (item >= this.dataController.getUserData().getMatch().getMarket().getAgents().get(seller).getPermitCardStock().size())){
			System.out.println("\nInvalid permit card number");
			this.showContinue();
			return;
		}
		
		this.marketController.buyPermitCardItem(seller, item);
		
		this.showContinue();
	}
}
