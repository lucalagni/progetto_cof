package client.view.cli.setup;

import java.util.concurrent.Executors;

import client.controller.connections.scheduler.GameConnectionScheduler;
import client.view.cli.utils.CliClearConsole;

/**
 * Classe che gestisce l'attesa per il matchCode
 * 
 * @author Luca Lagni
 *
 */
public class CliWaitForMatchCode {
	@SuppressWarnings("unused")
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
		
		try {
			t.join();
		} catch (InterruptedException e) {}
		
		System.out.println("\nDone");
		return 0;
	}
}
