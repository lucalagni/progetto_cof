package client;

public enum GameMode {
	SOCKET("SOCKET"),
	RMI("RMI");
	
	private String gameMode ;
	GameMode(String gameMode){ this.setGameMode(gameMode); }
	private void setGameMode(String gameMode){ this.gameMode = gameMode; }
	public String getGameMode(){ return this.gameMode; }
}
