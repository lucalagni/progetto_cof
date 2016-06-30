package commons.messages;

import java.io.Serializable;
import java.util.ArrayList;

import commons.data.UserData;

/**
 * Classe che definisce la struttura di un messaggio creato dal server e destinato ad 
 * essere inviato (e processato) dal client
 * @author Luca Lagni
 *
 */
public class ServerMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserData data;
	private ServerMessageContentType content;
	private ArrayList<String[]> parameters;
	
	public ServerMessage(UserData data){ this.setUserData(data);}
	
	private void setUserData(UserData data){ this.data = data; }
	
	/**
	 * Metodo per la definzione del contenuto del messaggio e relativi parametri per la gestione
	 * @param content definisce il contenuto del messaggio
	 * @param parameters definisce i parametri di utilità del messaggio
	 */
	public void addContent(ServerMessageContentType content,ArrayList<String[]> parameters){
		this.content = content;
		this.parameters = parameters;
	}
	
	public ServerMessageContentType getContent(){ return this.content; }
	public ArrayList<String[]> getParameters(){ return this.parameters; }
	public String getUsername(){ return this.data.getUsername(); }
	public String getMatchCode(){ return this.data.getMatchCode(); }
	public UserData getUserData(){ return this.data; }
	
	@Override
	public String toString(){
		String s = new String("\nserver message\n");
		
		s += "username: " + this.getUsername() + "\n";
		s += "match code: " + this.getMatchCode() + "\n";
		s += "content: " + this.getContent().getServerMessageContentType() + "\n";
		s += "parameters: \n";
		if(this.parameters.size() > 0){
			for(int i = 0; i < this.getParameters().size(); i++){
				for(int j = 0; j < this.getParameters().get(i).length; j++){
					s += this.getParameters().get(i)[j] + " , ";
				}
				
				s += "\n";
			}
		}
		s += "\n";
		
		return s;
	}
}
