package client.view.cli.setup;

import client.controller.connections.scheduler.GameConnectionScheduler;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe che gestisce l'attesa per il match code
 * 
 * @author Luca Lagni
 *
 */
public class CliWaitForMatchCode {
	public static final int GAMER_ADDED_TO_THE_QUEQUE = 0;
	public static final int USERNAME_NOT_AVAILABLE =  GAMER_ADDED_TO_THE_QUEQUE + 1;
	public static final int UNKNOWN_VALUE = GAMER_ADDED_TO_THE_QUEQUE - 1;
	private String text = null;
	
	public CliWaitForMatchCode(){
		this.setText();
	}
	
	private void setText(){
		this.text = new String("\n\n=========={ Client Required MatchCode } ==========\n");
		
		this.text += "\nwaiting ...";
	}
	
	public int show(){
		CliClearConsole.clearConsole(false);
		GameConnectionScheduler gcs = new GameConnectionScheduler();
		Thread t = new Thread(gcs);
		t.start();
		
		System.out.println(this.text);
		
		try {
			t.join();
		} catch (InterruptedException e) {}
		
		if(gcs.getGamerAddedToTheQueque() == true) return GAMER_ADDED_TO_THE_QUEQUE ;
		if(gcs.getUsernameNotAvailable() == true) return USERNAME_NOT_AVAILABLE;
		
		return UNKNOWN_VALUE;
	}
}
