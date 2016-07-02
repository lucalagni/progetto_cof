package client.view.cli.setup;

import client.controller.connections.scheduler.ClientRequestCanIPlayScheduler;
import client.view.cli.utils.CliClearConsole;

/**
 * Visualizzazione grafica CLI dello stato di gioco del giocatore
 * 
 * @author Luca Lagni
 *
 */
public class CliRequestCanIPlay {
	public static final int TOO_FEAW_GAMERS_TO_PLAY = 0;
	public static final int GAMER_NOT_IN_MATCH = TOO_FEAW_GAMERS_TO_PLAY + 1;
	public static final int MATCH_NOT_AVAILABLE_YET = GAMER_NOT_IN_MATCH + 1;
	public static final int MATCH = MATCH_NOT_AVAILABLE_YET + 1;
	private String text ;
	
	public CliRequestCanIPlay(){
		this.setText();
	}
	
	private void setText(){
		this.text = new String("\n=========={ Client Request Can I Play }==========\n\n");
		this.text += "waiting... ";
	}
	
	public int show(){
//		CliClearConsole.clearConsole(false);
		ClientRequestCanIPlayScheduler scheduler = new ClientRequestCanIPlayScheduler();
		Thread t = new Thread(scheduler);
		t.start();
		
		System.out.println(this.text);
		
		try {
			System.out.println("[CliRequestCanIPlay] sto aspettando che termina lo scheduler ... ");
			t.join();
			System.out.println("[CliRequestCanIPlay] Iuppiii è terminato!!! ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(scheduler.getServerResponseGamerNotInMatch() == true) return GAMER_NOT_IN_MATCH ;
		if(scheduler.getServerResponseTooFeawGamersToPlay() == true) return TOO_FEAW_GAMERS_TO_PLAY;
		if(scheduler.getServerResponseMatch() == true) return MATCH;
		return MATCH_NOT_AVAILABLE_YET;
	}
}
