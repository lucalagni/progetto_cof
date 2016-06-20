package view.basic.cli.client;

import java.util.Scanner;

import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import controller.basic.AddGamerController;

public class AddGamer {
	private Scanner input;
	private AddGamerController agc;
	
	public AddGamer(){ 
		try {
			this.agc = new AddGamerController();
		} catch (BuilderException | GameMapException e) {
			System.err.println(e.getMessage());
		} 
	}

	public String addGamerMenu() {
		char choice;
		String username = null;
		String matchCode;
		input = new Scanner(System.in);
		
		do{
			CliUtils.clearConsole();

			System.out.println("\n=========={ Council Of Four }==========\n");
			System.out.println("\n-----{ Local Game Options }------");
			System.out.println("n => new gamer");
			System.out.println("s => start match");
			System.out.println("e => exit");
			System.out.print("choice (n/s/e)> ");
			
			choice = input.nextLine().charAt(0);
			
			switch(choice){
				case 'n':
				case 'N':
						System.out.print("\nInsert username: ");
						username = input.nextLine();
						username = username.toLowerCase();
						try {
								this.agc.addGamer(username);
						} catch (PoliticalCardsDeckException e) {
							System.out.println(e.getMessage());
						}
						break;
				case 's':
				case 'S':
						try {
								matchCode = this.agc.done();
								return matchCode;
							} catch (BuilderException | PoliticalCardsDeckException| GameMapException | MatchException e) {
								System.err.println(e.getMessage());
							}
						break;
				case 'e':
				case 'E':
					break;
				default:
					System.out.println("\nInvalid choice , retry");
					break;
				
			}
			
		}while((choice != 'e')&&(choice != 'E'));
		
		return MatchConstants.MATCH_NOT_CREATED;
	}
}
