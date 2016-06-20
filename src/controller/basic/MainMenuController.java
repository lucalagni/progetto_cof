package controller.basic;

import client.exception.MatchRenderException;
import client.socket.MatchRender;

public class MainMenuController {
	private String matchCode;
	private int gamerID;
	private MatchRender render;
	
	public MainMenuController(String matchCode,int gamerID){
		this.matchCode = matchCode;
		this.gamerID = gamerID;
		
		try {
			this.render = new MatchRender(matchCode,gamerID);
			
		} catch (MatchRenderException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String getConnections(){
		return this.render.getMap().toString();
	}
}
