package client.command.actions.basics;

import java.awt.Color;
import java.util.ArrayList;

import commons.data.UserData;
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
	public void moveKing(ArrayList<String> path){
		ClientMessage message = new ClientMessage(this.user);
		ServerMessage response = null;
		ArrayList<String[]> pm = new ArrayList<String[]>();
		String[] parameters = new String[path.size()];
		
		for(int i = 0; i < path.size(); i++) parameters[i] = new String("" + path.get(i));
		pm.add(parameters);
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_MOVE_KING_ACTION, pm);
		
		response = this.client.sendMessage(message);
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_KING_MOVED);
	}
	
	/**
	 * Metodo che costruisce il messaggio per richiedere al server di cambiare un nobile
	 * @param king
	 * @param regionNumber
	 * @param noble
	 * @param mainAction
	 */
	public void changeNoble(boolean king,int regionNumber,Color noble, boolean mainAction){
		ClientMessage message = new ClientMessage(this.user);
		String[] parameters = new String[4];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + king);
		parameters[1] = new String("" + regionNumber);
		parameters[2] = new String("" + noble);
		parameters[3] = new String("" + mainAction);
		params.add(parameters);
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_CHANGE_NOBLE, params);
		this.client.sendMessage(message);
	}
	
	/**
	 * Metodo che costruisce ed invia il messaggio per richiedere al server di acquisire una carta permesso di costruzione
	 * @param king
	 * @param regionNumber
	 * @param noble
	 * @param mainAction
	 */
	public void buyPermitCard(int regionNumber,int permitCardNumber,int[] politicalCardsIndex){
		ClientMessage message = new ClientMessage(this.user);
		String[] parameters = new String[politicalCardsIndex.length + 2];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + regionNumber);
		parameters[1] = new String("" + permitCardNumber);
		for(int i = 2; i < parameters.length; i++) parameters[i] = new String("" + politicalCardsIndex[i]);
		params.add(parameters);
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_PERMIT_CARD, params);
		this.client.sendMessage(message);
	}
	
	/**
	 * Metodo che costruisce ed invia al server un messaggio per la costruzione di un emporio
	 * @param village
	 * @param permitCardIndex
	 */
	public void placeShop(char village,int permitCardIndex){
		ClientMessage message = new ClientMessage(this.user);
		String[] parameters = new String[2];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + village);
		parameters[1] = new String("" + permitCardIndex);
		params.add(parameters);
		
		message.addContent(ClientMessageContentType.CLIENT_REQUEST_TO_PLACE_A_SHOP, params);
		this.client.sendMessage(message);
	}
	
	/**
	 * Metodo che costruisce ed invia il messaggio al server per l'acuisto di aiutanti
	 * @param helpers
	 */
	public void buyHelper(boolean queque){
		ClientMessage request = new ClientMessage(this.user);
		String[] params = new String[2];
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		
		params[0] = new String("1");
		params[1] = new String("" + queque);
		parameters.add(params);
		
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_HELPERS, parameters);
		this.client.sendMessage(request);
	}
	
	/**
	 * Metodo che genera ed invia al server il messaggio  di acquisto di una nuova azione principale
	 */
	public void buyNewMainAction(){
		ClientMessage request = new ClientMessage(this.user);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_BUY_NEW_MAIN_ACTION, null);
		this.client.sendMessage(request);
	}
	
	/**
	 * Metodo che genera ed invia il messaggio di doppia azione
	 * @param regionNumber
	 */
	public void doubleAction(int regionNumber){
		ClientMessage request = new ClientMessage(this.user);
		String[] parameters = new String[1];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + regionNumber);
		params.add(parameters);
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_DOUBLE_ACTION, params);
		this.client.sendMessage(request);
	}
	
	/**
	 * Metodo per la generazione e l'invio di richiesta per acquisire una carta permesso 
	 * senza pagare
	 * @param regionNumber
	 * @param permitCardIndex
	 */
	public void acquirePermitCard(int regionNumber,int permitCardIndex){
		ClientMessage request = new ClientMessage(this.user);
		String[] parameters = new String[2];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + regionNumber);
		parameters[1] = new String("" + permitCardIndex);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACQUIRE_PERMIT_CARD, params);
		this.client.sendMessage(request);
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per l'acquisizione di un bonus
	 * afferente ad un villaggio in cui il giocatore ha precedentemente costruito un emporio (no shift)
	 * @param villageFirstLetter
	 */
	public void acquireSingleVillageBonus(char villageFirstLetter){
		ClientMessage request = new ClientMessage(this.user);
		String[] parameters = new String[1];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + villageFirstLetter);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACQUIRE_SINGLE_VILLAGE_BONUS, params);
		this.client.sendMessage(request);
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per l'acquisizione di un bonus
	 * afferente a due villaggi in cui il giocatore ha precedentemente costruito un emporio (no shift)
	 * @param villageFirstLetter
	 */
	public void acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter){
		ClientMessage request = new ClientMessage(this.user);
		String[] parameters = new String[2];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + firstVillageFirstLetter);
		parameters[1] = new String("" + secondVillageFirstLetter);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_ACQUIRE_DOUBLE_VILLAGE_BONUS, params);
		this.client.sendMessage(request);
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per il riutilizzo di un bonus
	 * afferente ad una carta permesso precedentemente utilizzata
	 * @param permitCardIndex
	 */
	public void reusePermitCardBonus(int permitCardIndex){
		ClientMessage request = new ClientMessage(this.user);
		String[] parameters = new String[1];
		ArrayList<String[]> params = new ArrayList<String[]>();
		
		parameters[0] = new String("" + permitCardIndex);
		params.add(parameters);
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_REUSE_PERMIT_CARD_BONUS, params);
		this.client.sendMessage(request);
	}
}