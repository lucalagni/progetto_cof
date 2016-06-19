package view.basic.cli.client;

import java.io.IOException;
import java.util.Scanner;

import model.basics.builders.exceptions.BuilderException;
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
		
		this.addGamerMenu();
	}

	public void addGamerMenu() {
		char choice;
		String username = null;
		input = new Scanner(System.in);
		
		do{
			CliUtils.clearConsole();
			try {
				Runtime.getRuntime().exec("clear");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("\n=========={ Council Of Four }==========\n");
			System.out.println("\n-----{ Add Gamer }------");
			System.out.println("y => yes");
			System.out.println("n => no");
			System.out.println("s => start");
			System.out.println("e => exit");
			System.out.print("add new gamer (y/n/e)? ");
			
			choice = input.nextLine().charAt(0);
			
			switch(choice){
				case 'y':
				case 'Y':
						System.out.print("\nInsert username: ");
						username = input.nextLine();
						try {
								this.agc.addGamer(username);
						} catch (PoliticalCardsDeckException e) {
							System.out.println(e.getMessage());
						}
						break;
				case 'n':
				case 'N':
						try {
								this.agc.done();
							} catch (BuilderException | PoliticalCardsDeckException| GameMapException | MatchException e) {
								System.err.println(e.getMessage());
							}
						break;
				case 's':
				case 'S':
						try {
								this.agc.done();
							} catch (BuilderException | PoliticalCardsDeckException| GameMapException | MatchException e) {
								System.err.println(e.getMessage());
							}
						break;
				case 'e':
				case 'E':
					System.exit(0);
					break;
				default:
					System.out.println("\nInvalid choice , retry");
					break;
				
			}
			
		}while((choice != 'n')&&(choice != 'N'));
	}
}
