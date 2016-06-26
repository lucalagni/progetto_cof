package communication.socket.messages;

import java.io.Serializable;

import command.basic.actions.ActionSynoptic;

/**
 * Classe che definisce la struttura di un messaggio creato dal client e destinato ad 
 * essere inviato (e processato) dal server
 * @author Luca Lagni
 *
 */

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
	
	/**
	 * Metodo per la definizione dei dati contenuti in un messaggio e dei parametri necessari
	 * per definire le azioni implementate dal messaggio
	 * @param content definisce il tipo di contenuto del messaggio
	 * @param parameters definisce i parametri del messaggio
	 */
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
	
	@Override
	public String toString(){
		String s = new String("\nclient message\n");
		
		s += "username: " + this.getUsername() + "\n";
		s += "match code: " + this.getMatchCode() + "\n";
		s += "message content: " + this.getContent().getClientMessageContentType() + "\n";
		s += "parameters: \n";
		if(this.getParameters().length > 0){
			for(String tmp : this.getParameters()) s += tmp + "," ;
		}
		
		s += "\n";
		
		return s;
	}
}
