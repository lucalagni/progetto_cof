package communication.socket.messages;

import java.util.ArrayList;

import model.basics.Match;

public class SocketMessage {
	private String username;
	private String matchCode;
	
	private Match match;
	
	private MessageContentType contentType;
	private String[] parameters;
	
	public void addTopic(MessageContentType content,String[] parameters){
		this.contentType = content;
		this.parameters = parameters;
	}
	
	public MessageContentType getContentType(){ return this.contentType; }
	public String[] getParameters(){ return this.parameters; }
	
}

//contebntType.add(new Obecjt1() :1
//