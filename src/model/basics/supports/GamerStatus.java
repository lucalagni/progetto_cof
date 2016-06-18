package model.basics.supports;

import java.io.Serializable;

public enum GamerStatus implements Serializable{
	ONLINE("GS.ONLINE"),
	OFFLINE("GS.OFFLINE");
	/*PLAYING("GS.PLAYING"),
	READY("GS.READY"),
	IDLE("GS.IDLE"),
	FREEZED("GS.FREEZED"),
	EXITED("GS.EXITED"),
	ZOMBIE("GS.ZOMBIE");*/
	
	private String gamerStatus;
	
	GamerStatus(String gamerStatus){ this.setGamerStatus(gamerStatus); }
	
	private void setGamerStatus(String gamerStatus){ this.gamerStatus = gamerStatus; }
	public String getGamerStatus(){ return this.gamerStatus; }
}
