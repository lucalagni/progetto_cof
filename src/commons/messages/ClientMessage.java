package commons.messages;

import java.io.Serializable;
import java.util.ArrayList;

import commons.data.UserData;

/**
 * Classe che definisce la struttura di un messaggio creato dal client e destinato ad 
 * essere inviato (e processato) dal server
 * @author Luca Lagni
 *
 */

public class ClientMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private UserData user;
	
	private ClientMessageContentType content;
	private ArrayList<String[]> parameters;
	
	public ClientMessage(UserData user){ this.setUserData(user); }
	
	/**
	 * Metodo per la definizione dei dati contenuti in un messaggio e dei parametri necessari
	 * per definire le azioni implementate dal messaggio
	 * @param content definisce il tipo di contenuto del messaggio
	 * @param parameters definisce i parametri del messaggio
	 */
	public void addContent(ClientMessageContentType content,ArrayList<String[]> parameters){
		this.content = content;
		this.parameters = parameters;
	}
	
	private void setUserData(UserData userData){ this.user = userData; }
	
	public ClientMessageContentType getContent(){ return this.content; }
	public ArrayList<String[]> getParameters(){ return this.parameters; }
	
	public String getUsername(){ return this.user.getUsername(); }
	public String getMatchCode(){ return this.user.getMatchCode(); }
	public UserData getUserData(){ return this.user; }
	
	@Override
	public String toString(){
		String s = new String("\nclient message\n");
		
		s += "username: " + this.getUsername() + "\n";
		s += "match code: " + this.getMatchCode() + "\n";
		s += "message content: " + this.getContent().getClientMessageContentType() + "\n";
		s += "parameters: \n";
		if(this.getParameters().size() > 0){
			for(int i = 0; i < this.getParameters().size(); i++){
				for(int j = 0; j < this.getParameters().get(i).length; j++){
					s +=  this.getParameters().get(i)[j] + " , ";
				}
				
				s += "\n";
			}
		}
		
		s += "\n";
		
		return s;
	}
}
