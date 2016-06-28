package client;

/**
 * enumerazione che contiene le possibili modalità di comunicazione
 * SOCKET = utilizzando la tecnologia socket
 * RMI = utilizzando la tecnologia RMI
 * @author Luca Lagni
 *
 */

public enum GameMode {
	SOCKET("SOCKET"),
	RMI("RMI");
	
	private String gameMode ;
	GameMode(String gameMode){ this.setGameMode(gameMode); }
	private void setGameMode(String gameMode){ this.gameMode = gameMode; }
	public String getGameMode(){ return this.gameMode; }
}
