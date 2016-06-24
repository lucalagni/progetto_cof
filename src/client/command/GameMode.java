package client.command;

public enum GameMode {
	SOCKET("SOCKET"),
	RMI("RMI"),
	LOCAL("LOCAL");
	
	private String modeCode;
	GameMode(String modeCode){this.setGameMode(modeCode);}
	private void setGameMode(String modeCode){ this.modeCode = modeCode; }
	public String getGameMode(){ return this.modeCode; }
}
