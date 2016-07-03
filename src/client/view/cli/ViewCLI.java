package client.view.cli;

import client.view.cli.basic.CliMainMenu;
import client.view.cli.setup.CliConsoleType;
import client.view.cli.setup.CliGameModeSetup;
import client.view.cli.setup.CliRequestCanIPlay;
import client.view.cli.setup.CliRequireToPlay;
import client.view.cli.setup.CliUsernameSetup;
import client.view.cli.utils.CliClearConsole;
import client.view.updates.CliGamerTurn;

/**
 * Classe che contiene la classe di visualizzazione in modalità CLI
 * 
 * @author Luca Lagni
 *
 */
public class ViewCLI {
	private String title ;
	
	public ViewCLI(){
		this.setTitle();
		int wait = 0;
		
		System.out.println(this.title);
		
		wait = new CliConsoleType().show();   //Acquisisco il tipo di terminale sul quale visualizzare le informazioni
		wait = new CliGameModeSetup().show(); //Acquisisco la modalità di trasmissione dati
		
		//Ciclo fino a quando non mi viene inserito uno username valido
		do {
			wait = new CliUsernameSetup().show(); //Acquisisco lo username
			wait = new CliRequireToPlay().show(); //Richiedo al server di unirmi alla coda degli utenti
			if(wait == CliRequireToPlay.USERNAME_NOT_AVAILABLE) System.out.println("\nUSERNAME_NOT_AVAILABLE, RETRY");
		}while(wait == CliRequireToPlay.USERNAME_NOT_AVAILABLE);
		
		if(wait == CliRequireToPlay.GAMER_ADDED_TO_THE_QUEQUE){
			do{
				wait = new CliRequestCanIPlay().show();
				
			}while(wait == CliRequestCanIPlay.MATCH_NOT_AVAILABLE_YET);
			
			if(wait == CliRequestCanIPlay.MATCH){
				CliClearConsole.getInstance().clearConsole(false);
				do{
					wait = new CliGamerTurn().show();
					
					if(wait == CliGamerTurn.ITS_MY_TURN_TO_PLAY) {
						//new CliMainMenu(true).show();
						System.out.println("\nITS MY TURN TO PLAY");
					}
					else{
						//new CliMainMenu(false).show();
						System.out.println("\nITS NOT MY TURN TO PLAY");
					}
				}while(true);
			}
			
		}
		else{
			System.out.println("\nCANNOT PLAY");
		}
		
	}
	
	private void setTitle(){ this.title = new String("\n++++++++++{ Council Of Four }++++++++++\n\n"); }
}
