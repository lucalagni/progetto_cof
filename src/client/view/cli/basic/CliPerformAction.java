package client.view.cli.basic;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	public CliPerformAction(){
		this.dataController = ControllerRepository.getInstance().getGameDataController();
		ControllerRepository.getInstance().setActionController();
		this.actionController = ControllerRepository.getInstance().getActionController();
		this.input = new Scanner(System.in);
		CliClearConsole.clearConsole(false);
		
	}
	
	private void showContinue(){
		System.out.println("\n[press any key to continue]");
		this.input.nextLine();
		return;
	}
	
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
			System.out.println(this.dataController.getUserData().getMatch().getBoard().getRegions()[region].getCouncil().toString());
			System.out.println("\nSelect an index: ");
			index = Integer.parseInt(this.input.nextLine());
			new CliShowGameData(false, this.input).showPoliticalCards();
			do{
				System.out.println("\nSelect a cardb index: ");
				cards.add(Integer.parseInt(this.input.nextLine()));
				System.out.println("\nSelect another card (true/false): ");
				flag = Boolean.parseBoolean(this.input.nextLine());
			}while(flag == true);
			
			int pCards[] = new int[cards.size()];
			for(int i = 0; i < pCards.length; i++){
				pCards[i] = cards.get(i).intValue();
				System.out.println("\n" +pCards[i]);
			}
			
			this.actionController.buyPermitCard(region, index, pCards);
			
		}
		
		this.showContinue();
		
	}
	
	
	public void buildShop(){
		int permitCard;
		char village;
		System.out.print("\n----------{ Build Shop }----------\n\n");
		if(this.dataController.getUserData().getGamer().getUnusedPermitCards().size() == 0){
			System.out.print("Gamer doesn't have any permit card");
		}
		else {
			new CliShowGameData(false,this.input).showUnusedPermitCards();
			System.out.println("\nSelect permit card: ");
			permitCard = Integer.parseInt(this.input.nextLine());
			//Verificare che l'indice sia corretto
			System.out.print("\n" + this.dataController.getUserData().getGamer().getUnusedPermitCards().get(permitCard).getVillages());
			System.out.println("\nSelect village: ");
			village = this.input.nextLine().charAt(0);
			
			this.actionController.placeShop(village, permitCard);
		}
		
		this.showContinue();
	}
}
