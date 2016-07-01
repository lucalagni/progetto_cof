package client.view.cli;

import client.view.cli.setup.CliConsoleType;
import client.view.cli.setup.CliGameModeSetup;
import client.view.cli.setup.CliUsernameSetup;
import client.view.cli.setup.CliWaitForMatchCode;

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
		wait = new CliUsernameSetup().show(); //Acquisisco lo username
		wait = new CliWaitForMatchCode().show(); //Richiedo al server di unirmi alla coda degli utenti
		if(wait == CliWaitForMatchCode.GAMER_ADDED_TO_THE_QUEQUE){
			//fa qualcosa
		}
		else {
			System.out.println("\nGamer cannot play");
		}
	}
	
	private void setTitle(){ this.title = new String("\n++++++++++{ Council Of Four }++++++++++\n\n"); }
}
