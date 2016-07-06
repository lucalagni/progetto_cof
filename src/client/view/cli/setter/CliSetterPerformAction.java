package client.view.cli.setter;

import java.util.Scanner;

import model.basics.Gamer;
import client.controller.ControllerRepository;
import client.controller.actions.setter.SetterActionController;
import client.controller.data.GameDataController;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe che implementa la CLI per il settaggio degli oggetti da vendere
 * @author Luca Lagni
 *
 */

public class CliSetterPerformAction {
	private GameDataController dataController;
	private SetterActionController actionController ;
	private Scanner input ;
	
	public CliSetterPerformAction(){
		this.dataController = ControllerRepository.getInstance().getGameDataController();
		ControllerRepository.getInstance().setSetterActionController();
		this.actionController = ControllerRepository.getInstance().getSetterActionController();
		this.input = new Scanner(System.in);
		CliClearConsole.clearConsole(false);
	}
	
	private void showContinue(){
		System.out.println("\n[press any key to continue]");
		this.input.nextLine();
		return;
	}
	
	/**
	 * Metodo per la scelta della vendita di una carta permesso
	 */
	public void addPermitCardItem(){
		Gamer g = this.dataController.getUserData().getGamer();
		char sell = 'n';
		int price = 0;
		System.out.println("\n\n----------{ Permit Card Item Setter }----------\n");
		
		for(int i = 0; i < g.getUnusedPermitCards().size(); i++){
			System.out.println("\n+++{Permit Card}+++\n");
			System.out.println(g.getUnusedPermitCards().get(i).toString());
			System.out.println("\nSell this permit card? [y/n]: ");
			sell = this.input.nextLine().toLowerCase().charAt(0);
			if(sell == 'y'){
				System.out.println("\nInsert a price: ");
				try{
					price = Integer.parseInt(this.input.nextLine());
					System.out.println("Price: " + price);
					this.actionController.addPermitCardItem(i, price);
				}catch(Exception ex){
					System.out.println("\nInvalid input data");
					continue;
				}
			}
			
			this.showContinue();
		}
		
		this.showContinue();
	}
	
	/**
	 * Metodo per la scelta della vendita di una carta politica
	 */
	public void addPoliticalCardItem(){
		Gamer g = this.dataController.getUserData().getGamer();
		char sell = 'n';
		int price = 0;
		System.out.println("\n\n----------{ Political Card Item Setter }----------\n");
		
		for(int i = 0; i < g.getPoliticalCards().size(); i++){
			System.out.println("\n+++{Political Card}+++\n");
			System.out.println(g.getPoliticalCards().get(i).toString());
			System.out.println("\nSell this political card? [y/n]: ");
			sell = this.input.nextLine().toLowerCase().charAt(0);
			if(sell == 'y'){
				System.out.println("\nInsert a price: ");
				try{
					price = Integer.parseInt(this.input.nextLine());
					this.actionController.addPoliticalCardItem(i, price);
				}catch(Exception ex){
					System.out.println("\nInvalid input data");
					continue;
				}
			}
			
			this.showContinue();
		}
		
		this.showContinue();
	}
	
	public void sendAgent(){ this.actionController.sendAgent(); }
	
	public void resetAgent(){ this.actionController.resetAgent(); }
	
	/**
	 * Metodo per la scelta della vendita di un lotto di aiutanti
	 */
	public void addHelpersItem(){
		Gamer g = this.dataController.getUserData().getGamer();
		int quantity = 0;
		int price = 0;
		System.out.println("\n\n----------{ Helpers Item Setter }----------\n");
		System.out.println("\nNumber of helpers left: " + g.getHelpers());
		
		System.out.println("\nInsert a quantity: ");
		try{
			quantity = Integer.parseInt(this.input.nextLine());
		}catch(Exception ex){
			System.out.println("\nInvalid input data");
		}
		
		System.out.println("\nInsert a price: ");
		try{
			price = Integer.parseInt(this.input.nextLine());
			this.actionController.addHelpersItem(quantity, price);
			System.out.println("done: ");
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("\nInvalid input data");
		}
		
		this.showContinue();
	}
}
