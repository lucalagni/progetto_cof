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
		
		//Ciclo fino a quando non mi viene inserito uno username valido
		do {
			wait = new CliUsernameSetup().show(); //Acquisisco lo username
			wait = new CliWaitForMatchCode().show(); //Richiedo al server di unirmi alla coda degli utenti
			if(wait == CliWaitForMatchCode.USERNAME_NOT_AVAILABLE) System.out.println("\nUsername not available, retry");
		}while(wait == CliWaitForMatchCode.USERNAME_NOT_AVAILABLE);
		
		if(wait == CliWaitForMatchCode.GAMER_ADDED_TO_THE_QUEQUE){
		}
		
	}
	
	private void setTitle(){ this.title = new String("\n++++++++++{ Council Of Four }++++++++++\n\n"); }
}
