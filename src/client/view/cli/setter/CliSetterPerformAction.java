package client.view.cli.setter;

import java.util.Scanner;

import client.controller.ControllerRepository;
import client.controller.data.GameDataController;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe che implementa la CLI per il settaggio degli oggetti da vendere
 * @author Luca Lagni
 *
 */

public class CliSetterPerformAction {
	private GameDataController dataController;
	private Scanner input ;
	
	public CliSetterPerformAction(Scanner input){
		this.dataController = ControllerRepository.getInstance().getGameDataController();
		this.input = input;
		CliClearConsole.clearConsole(false);
	}
	
	private void showContinue(){
		System.out.println("\n[press any key to continue]");
		this.input.nextLine();
		return;
	}
	
	public void setPermitCardItem(){
		
	}
}
