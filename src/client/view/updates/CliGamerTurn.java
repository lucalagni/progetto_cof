package client.view.updates;

import client.controller.updates.scheduler.GameUpdatesScheduler;

/**
 * Classe per la visualizzazione del giocatore attuale per il match
 * @author Luca Lagni
 *
 */
public class CliGamerTurn {
	public static final int ITS_MY_TURN_TO_PLAY = 0;
	public static final int ITS_NOT_MY_TURN_TO_PLAY = ITS_MY_TURN_TO_PLAY + 1;
	private String text = null;
	
	public CliGamerTurn(){
		this.setText();
	}
	
	private void setText(){
		this.text = new String("\n\n=========={ Client Require Gamer Turn } ==========\n");
	}
	
	public int show(){
		GameUpdatesScheduler gus = new GameUpdatesScheduler();
		Thread t = new Thread(gus);
		t.start();
		
		System.out.println(this.text);
		
		try {
			t.join();
		} catch (InterruptedException e) {}
		
		if(gus.getItsMyTurnToPlay() == false) return ITS_NOT_MY_TURN_TO_PLAY;
		else return ITS_MY_TURN_TO_PLAY;
	}
}