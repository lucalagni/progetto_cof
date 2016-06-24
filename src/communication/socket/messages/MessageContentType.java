package communication.socket.messages;

public enum MessageContentType {
	MATCH_REQUEST("MATCH_REQUEST"),
	MARKET_REQUEST("MARKET_REQUEST"),
	GAMER_TURN("GAMER_TURN"),
	MARKET_TIME("MARKET_TIME"),
	GAME_TIME("GAME_TYME");
	
	private String contentCode;
	MessageContentType(String contentCode){this.setContentType(contentCode);}
	private void setContentType(String contentCode){this.contentCode = contentCode; }
	public String getContentType(){ return this.contentCode; }
}
