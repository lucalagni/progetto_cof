package communication.socket.messages;

import java.io.Serializable;

import command.basic.actions.ActionSynoptic;

public class ClientMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String matchCode;
	private ActionSynoptic actionSynoptic;
	
	private ClientMessageContentType content;
	private String[] parameters;
	
	public ClientMessage(String username,String matchCode){
		this.setUsername(username);
		this.setMatchCode(matchCode);
		this.setActionSynoptic(null);
	}
	
	public void addContent(ClientMessageContentType content,String[] parameters){
		this.content = content;
		this.parameters = parameters;
	}
	
	private void setUsername(String username){ this.username = username; }
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	public void setActionSynoptic(ActionSynoptic as){ this.actionSynoptic = as;  }
	
	public ClientMessageContentType getContent(){ return this.content; }
	public String[] getParameters(){ return this.parameters; }
	
	public String getUsername(){ return this.username; }
	public String getMatchCode(){ return this.matchCode; }
	public ActionSynoptic getActionSynoptic(){ return this.actionSynoptic; }
}
