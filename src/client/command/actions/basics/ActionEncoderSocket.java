package client.command.actions.basics;

import java.awt.Color;
import java.util.ArrayList;

import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.*;
import client.controller.ControllerRepository;
import client.Client;

/**
 * Classe per la gestione delle azioni mediante tecnologia di communicazione socket
 * @author Luca Lagni
 *
 */
public class ActionEncoderSocket {
	private UserData user;
	private Client client;
	
	public ActionEncoderSocket(){
		this.user = ControllerRepository.getInstance().getGameDataController().getUserData();
		this.client = ControllerRepository.getInstance().getClientController().getClient();
	}
	
	/**
	 * Metodo che costruisce il messaggio per richiedere al server di poter spostare il re
	 * @param path
	 */
	public String moveKing(ArrayList<String> path,ArrayList<Integer> politicalCardsPosition){
		ClientMessage message = new ClientMessage(this.user);
		ServerMessage response = null;
		ArrayList<String[]> pm = new ArrayList<String[]>();
		String[] parameters = new String[path.size()];
		String[] parameters2 = new String[politicalCardsPosition.size()]; 
		
		for(int i = 0; i < path.size(); i++) parameters[i] = new String("" + path.get(i));
		pm.add(parameters);
		for(int i = 0; i < politicalCardsPosition.size(); i++) parameters2[i] = new String("" + politicalCardsPosition.get(i));
		pm.add(parameters2);
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_MOVE_KING, pm);
		
		response = this.client.sendMessage(message);
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che costruisce il messaggio per richiedere al server di cambiare un nobile
	 * @param king
	 * @param regionNumber
	 * @param noble
	 * @param mainAction
	 */
	public String changeNoble(boolean king,int regionNumber,Color noble, boolean mainAction){
		ClientMessage message = new ClientMessage(this.user);
		ServerMessage response = null;
		String[] parameters = new String[4];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + king);
		parameters[1] = new String("" + regionNumber);
		if(noble.equals(Color.BLACK)) parameters[2] = new String("BLACK");
		if(noble.equals(Color.CYAN)) parameters[2] = new String("CYAN");
		if(noble.equals(Color.PINK)) parameters[2] = new String("PINK");
		if(noble.equals(Color.MAGENTA)) parameters[2] = new String("MAGENTA");
		if(noble.equals(Color.ORANGE)) parameters[2] = new String("ORANGE");
		if(noble.equals(Color.WHITE)) parameters[2] = new String("WHITE");
		parameters[3] = new String("" + mainAction);
		params.add(parameters);
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_CHANGE_NOBLE, params);
		response = this.client.sendMessage(message);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che costruisce ed invia il messaggio per richiedere al server di acquisire una carta permesso di costruzione
	 * @param king
	 * @param regionNumber
	 * @param noble
	 * @param mainAction
	 */
	public String buyPermitCard(int regionNumber,int permitCardNumber,int[] politicalCardsIndex){
		ClientMessage message = new ClientMessage(this.user);
		ServerMessage response = null;
		String[] parameters = new String[2];
		String[] parameters2 = new String[politicalCardsIndex.length];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + regionNumber);
		parameters[1] = new String("" + permitCardNumber);
		for(int i = 0; i < parameters2.length; i++) parameters2[i] = new String("" + politicalCardsIndex[i]);
		params.add(parameters);
		params.add(parameters2);
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_PERMIT_CARD, params);
		response = this.client.sendMessage(message);
		
		try {
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che costruisce ed invia al server un messaggio per la costruzione di un emporio
	 * @param village
	 * @param permitCardIndex
	 */
	public String placeShop(char village,int permitCardIndex){
		ClientMessage message = new ClientMessage(this.user);
		ServerMessage response;
		String[] parameters = new String[2];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + village);
		parameters[1] = new String("" + permitCardIndex);
		params.add(parameters);
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_TO_PLACE_A_SHOP, params);
		response = this.client.sendMessage(message);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che costruisce ed invia il messaggio al server per l'acuisto di aiutanti
	 * @param helpers
	 */
	public String buyHelper(boolean queque){
		ClientMessage request = new ClientMessage(this.user);
		ServerMessage response = null;
		String[] params = new String[2];
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		
		params[0] = new String("1");
		params[1] = new String("" + queque);
		parameters.add(params);
		
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_HELPERS, parameters);
		response = this.client.sendMessage(request);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che genera ed invia al server il messaggio  di acquisto di una nuova azione principale
	 */
	public String buyNewMainAction(){
		ClientMessage request = new ClientMessage(this.user);
		ServerMessage response ;
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_NEW_MAIN_ACTION, null);
		response = this.client.sendMessage(request);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che genera ed invia il messaggio di doppia azione
	 * @param regionNumber
	 */
	public String doubleAction(int regionNumber){
		ClientMessage request = new ClientMessage(this.user);
		ServerMessage response = null;
		String[] parameters = new String[1];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + regionNumber);
		params.add(parameters);
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_DOUBLE_ACTION, params);
		response = this.client.sendMessage(request);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di richiesta per acquisire una carta permesso 
	 * senza pagare
	 * @param regionNumber
	 * @param permitCardIndex
	 */
	public String acquirePermitCard(int regionNumber,int permitCardIndex){
		ClientMessage request = new ClientMessage(this.user);
		ServerMessage response ;
		String[] parameters = new String[2];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + regionNumber);
		parameters[1] = new String("" + permitCardIndex);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACQUIRE_PERMIT_CARD, params);
		response = this.client.sendMessage(request);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per l'acquisizione di un bonus
	 * afferente ad un villaggio in cui il giocatore ha precedentemente costruito un emporio (no shift)
	 * @param villageFirstLetter
	 */
	public String acquireSingleVillageBonus(char villageFirstLetter){
		ClientMessage request = new ClientMessage(this.user);
		ServerMessage response ;
		String[] parameters = new String[1];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + villageFirstLetter);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACQUIRE_SINGLE_VILLAGE_BONUS, params);
		response = this.client.sendMessage(request);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per l'acquisizione di un bonus
	 * afferente a due villaggi in cui il giocatore ha precedentemente costruito un emporio (no shift)
	 * @param villageFirstLetter
	 */
	public String acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter){
		ClientMessage request = new ClientMessage(this.user);
		ServerMessage response = null;
		String[] parameters = new String[2];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + firstVillageFirstLetter);
		parameters[1] = new String("" + secondVillageFirstLetter);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACQUIRE_DOUBLE_VILLAGE_BONUS, params);
		response = this.client.sendMessage(request);
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per il riutilizzo di un bonus
	 * afferente ad una carta permesso precedentemente utilizzata
	 * @param permitCardIndex
	 */
	public String reusePermitCardBonus(int permitCardIndex){
		ClientMessage request = new ClientMessage(this.user);
		ServerMessage response = null;
		String[] parameters = new String[1];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + permitCardIndex);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_REUSE_PERMIT_CARD_BONUS, params);
		response = this.client.sendMessage(request);
		return response.getContent().getServerMessageContentType();
	}
}
