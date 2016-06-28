package view.basic.cli.client;

import java.util.Scanner;

import model.basics.constants.MatchConstants;
import controller.basic.StartGameController;

public class ViewCLI {
	private Scanner input;
	private String matchCode;
	private StartGameController sgc;

	public ViewCLI() {
		AddGamer ag = new AddGamer();
		this.input = new Scanner(System.in);

		this.splashScreen();
		this.matchCode = ag.addGamerMenu();
		if (this.matchCode == MatchConstants.MATCH_NOT_CREATED)
			System.out.println("\nMatch not created");
		else {
			this.sgc = new StartGameController();

			int gamerID = this.sgc.startGame(matchCode);

		}
	}

	private void splashScreen() {
		System.out.println("=========={ Council Of Four }===========");
		System.out.println("press any key to continue...");

		this.input.nextLine();
	}
}
