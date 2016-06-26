package communication.socket.messages;

import java.io.Serializable;

import command.basic.actions.ActionSynoptic;

import model.basics.Match;

/**
 * Classe che definisce la struttura di un messaggio creato dal server e destinato ad 
 * essere inviato (e processato) dal client
 * @author Luca Lagni
 *
 */
public class ServerMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String matchCode;
	private Match match;
	private ActionSynoptic actionSynoptic ;
	
	private ServerMessageContentType content;
	private String[] parameters;
	
	public ServerMessage(String username,String matchCode){
		this.setMatchCode(matchCode);
		this.setUsername(username);
		this.setMatch(null);
	}
	
	private void setUsername(String username){ this.username = username; }
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	public void setMatch(Match match){ this.match = match; }
	public void setActionSynoptic(ActionSynoptic as){ this.actionSynoptic = as; }
	
	/**
	 * Metodo per la definzione del contenuto del messaggio e relativi parametri per la gestione
	 * @param content definisce il contenuto del messaggio
	 * @param parameters definisce i parametri di utilità del messaggio
	 */
	public void addContent(ServerMessageContentType content,String[] parameters){
		this.content = content;
		this.parameters = parameters;
	}
	
	public ServerMessageContentType getContent(){ return this.content; }
	public String[] getParameters(){ return this.parameters; }
	public String getUsername(){ return this.username; }
	public String getMatchCode(){ return this.matchCode; }
	public Match getMatch(){ return this.match; }
	public ActionSynoptic getActionSynoptic(){ return this.actionSynoptic; }
	
	@Override
	public String toString(){
		String s = new String("\nserver message\n");
		
		s += "username: " + this.getUsername() + "\n";
		s += "match code: " + this.getMatchCode() + "\n";
		s += "content: " + this.getContent().getServerMessageContentType() + "\n";
		s += "parameters: \n";
		if(this.getParameters().length > 0){
			for(String tmp : this.getParameters()) s+= tmp + "," ;
		}
		s += "\n";
		
		return s;
	}
}
