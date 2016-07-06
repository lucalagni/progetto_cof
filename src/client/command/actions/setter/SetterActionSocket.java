package client.command.actions.setter;

import client.Client;
import client.controller.ControllerRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ClientMessage;
import commons.messages.ClientMessageContentType;
import commons.messages.ServerMessage;

/**
 * Classe che si occupa dello scambio di messaggi finalizzazto alla fase di setting del
 * merket
 * @author Luca Lagni
 *
 */
public class SetterActionSocket {
	private UserData data;
	private Client client;
	
	public SetterActionSocket(){
		this.setUserData(ControllerRepository.getInstance().getGameDataController().getUserData());
		this.setClient(ControllerRepository.getInstance().getClientController().getClient());
	}
	
	private void setUserData(UserData userData){ this.data = userData; }
	private void setClient(Client client){ this.client = client; }
	
	/**
	 * Metodo per l'invio delle modifiche al agent in modalità socket
	 * @param data
	 * @return
	 */
	public String sendAgent(UserData data){
		ClientMessage message = new ClientMessage(data);
		ServerMessage response = null;
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_SET_AGENT, null);
		response = this.client.sendMessage(message);
		
		try {
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
		} catch (UserDataException e) { e.printStackTrace(); }
		
		System.out.println("\nServerMessageContent: " + response.getContent().getServerMessageContentType());
		return response.getContent().getServerMessageContentType();
	}
	
	public UserData getUserData(){ return this.data; }
	public Client getClient(){ return this.client; }
}
