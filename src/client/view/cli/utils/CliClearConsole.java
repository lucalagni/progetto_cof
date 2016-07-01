package client.view.cli.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

/**
 * Classe per la pulizia della console di output
 * 
 * @author Luca Lagni
 *
 */
public class CliClearConsole {
	private static CliClearConsole instance = null;
	private static boolean ide = false;
	
	private CliClearConsole(){}
	
	/**
	 * Metodo per la pulizia della console
	 * @param verbose indica che , in caso di eccezione , viene visualizzato un messaggio di errore
	 */
	public static final void clearConsole(boolean verbose){
		if(ide == true){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = (int) Math.ceil(screenSize.getHeight());
			
			for(int i = 0; i < height; i++) System.out.println(" ");
			
		}
		else
		{
			final String os = System.getProperty("os.name");

				try {
					if(os.contains("Windows")) Runtime.getRuntime().exec("cls");
					else Runtime.getRuntime().exec("clear");
				} catch (IOException e) {
					if(verbose == true)System.out.println("\nCannot clear console");
				}
		}
	}
	
	/**
	 * Definisce se si sta lavorando in un ide
	 */
	public static void isIde(){ ide = true; }
	public static boolean getIde(){ return ide; }
	
	public static CliClearConsole getInstance(){
		if(instance == null) instance = new CliClearConsole();
		return instance;
	}
}
