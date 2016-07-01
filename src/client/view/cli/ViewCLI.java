package client.view.cli;

import client.view.cli.setup.CliConsoleType;
import client.view.cli.setup.CliGameModeSetup;
import client.view.cli.setup.CliUsernameSetup;

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
		@SuppressWarnings("unused")
		int wait = 0;
		
		System.out.println(this.title);
		
		wait = new CliConsoleType().show();   //Acquisisco il tipo di terminale sul quale visualizzare le informazioni
		wait = new CliGameModeSetup().show(); //Acquisisco la modalità di trasmissione dati
		wait = new CliUsernameSetup().show(); //Acquisisco lo username
	}
	
	private void setTitle(){ this.title = new String("\n++++++++++{ Council Of Four }++++++++++\n\n"); }
}
