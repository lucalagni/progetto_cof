package client.command.actions.setter;

import java.util.ArrayList;

import client.Client;
import client.controller.ControllerRepository;
import commons.data.UserData;
import commons.messages.ClientMessage;
import commons.messages.ClientMessageContentType;
import commons.messages.ServerMessage;

/**
 * Classe che si occupa dello scambio di messaggi finalizzazto alla fase di setting del
 * merket
 * @author lucal
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
	 * Metodo che crea l'agente del market
	 * @param permitCardsIndex
	 * @param politicalCardsIndex
	 * @param helpersQuantity
	 * @return
	 */
	public ServerMessage createAgent(ArrayList<Integer> permitCardsIndex, ArrayList<Integer> politicalCardsIndex,int helpersQuantity){
		ClientMessage request = new ClientMessage(this.data);
		ServerMessage response = null;
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		String[] permit = new String[permitCardsIndex.size()];
		String[] political = new String[politicalCardsIndex.size()];
		String[] helpers = new String[1];
		
		for(int i = 0; i < permit.length; i++) permit[i] = "" + permitCardsIndex.get(i);
		for(int i = 0; i < political.length; i++) political[i] = "" + politicalCardsIndex.get(i);
		helpers[0] = "" + helpersQuantity;
		
		parameters.add(permit);
		parameters.add(political);
		parameters.add(helpers);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_CREATE_AGENT, parameters);
		
		response = this.client.sendMessage(request);
		
		return response;
	}
	
	public UserData getUserData(){ return this.data; }
	public Client getClient(){ return this.client; }
}
