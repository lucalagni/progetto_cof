package contoller.communications.client.socket.dto;

import java.io.Serializable;
import java.util.HashMap;

import contoller.communications.client.socket.dto.messagges.DTO;
import contoller.communications.client.socket.dto.messagges.support.MessageType;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String matchCode;
	protected String sender;
	protected HashMap<DTO,MessageType> actions;
	
	public Message(String matchCode,String sender){
		this.matchCode = matchCode;
		this.sender = sender;
		this.actions = new HashMap<DTO,MessageType>();
	}
	
	public void addAction(DTO action, MessageType type){
		this.actions.put(action, type);
	}
	
	public String getMatchCode(){ return this.matchCode; }
	public String getSender(){ return this.sender; }
	public HashMap<DTO, MessageType> getActions(){ return this.actions; }
}
