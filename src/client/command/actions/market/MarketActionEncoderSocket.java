package client.command.actions.market;

import java.util.ArrayList;

import client.Client;
import client.controller.ControllerRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ClientMessage;
import commons.messages.ClientMessageContentType;
import commons.messages.ServerMessage;

/**
 * Classe che implementa i metodi per la richiesta tramite socket
 * delle operazioni da fare per il market
 * 
 * @author Luca Lagni
 *
 */
public class MarketActionEncoderSocket {
	private UserData data;
	private Client client;
	
	public MarketActionEncoderSocket(){
		this.data = ControllerRepository.getInstance().getGameDataController().getUserData();
		this.client = ControllerRepository.getInstance().getClientController().getClient();
	}
	
	/**
	 * Metodo per la richiesta di acquisto di un lotto di aiutanti nel market
	 * @param sellerIndex
	 * @param helpersItemIndex
	 * @return
	 */
	public String buyHelpersItem(int sellerIndex,int helpersItemIndex){
		ClientMessage message = new ClientMessage(this.data);
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] param = new String[2];
		
		param[0] = new String("" + sellerIndex);
		param[1] = new String("" + helpersItemIndex);
		parameters = new ArrayList<String[]>();
		
		parameters.add(param);
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_HELPERS_ITEM, parameters);
		response = this.client.sendMessage(message);
		
		try {
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
		} catch (UserDataException e) { e.printStackTrace(); }
		
		System.out.println("\nServerMessageContent: " + response.getContent().getServerMessageContentType());
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la richiesta di acquisto di una carta politica
	 * @param sellerIndex
	 * @param ItemIndex
	 * @return
	 */
	public String buyPoliticalCardItem(int sellerIndex,int politicalCardItemIndex){
		ClientMessage message = new ClientMessage(this.data);
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] param = new String[2];
		
		param[0] = new String("" + sellerIndex);
		param[1] = new String("" + politicalCardItemIndex);
		parameters = new ArrayList<String[]>();
		
		parameters.add(param);
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_POLITICAL_CARD_ITEM, parameters);
		response = this.client.sendMessage(message);
		
		try {
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
		} catch (UserDataException e) { e.printStackTrace(); }
		
		System.out.println("\nServerMessageContent: " + response.getContent().getServerMessageContentType());
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la richiesta di acquisto di una carta permesso
	 * @param sellerIndex
	 * @param ItemIndex
	 * @return
	 */
	public String buyPermitCardItem(int sellerIndex,int permitCardItemIndex){
		ClientMessage message = new ClientMessage(this.data);
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] param = new String[2];
		
		param[0] = new String("" + sellerIndex);
		param[1] = new String("" + permitCardItemIndex);
		parameters = new ArrayList<String[]>();
		
		parameters.add(param);
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_PERMIT_CARD_ITEM, parameters);
		response = this.client.sendMessage(message);
		
		try {
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
		} catch (UserDataException e) { e.printStackTrace(); }
		
		System.out.println("\nServerMessageContent: " + response.getContent().getServerMessageContentType());
		return response.getContent().getServerMessageContentType();
	}
}
