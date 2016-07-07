package client.command.actions.basics;

import java.awt.Color;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;
import client.command.actions.basics.exceptions.codes.ActionFacadeExceptionCode;
import client.controller.ControllerRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.basics.HelpersActionsRmiInterface;
import commons.rmi.interfaces.actions.basics.MainActionsRmiInterface;
import commons.rmi.interfaces.actions.basics.SpecialActionsRmiInterface;

/**
 * Classe per la gestione delle richieste di esecuzione delle azioni lato client 
 * mediante RMI
 * @author Luca Lagni
 *
 */
public class ActionEncoderRmi {
	private UserData user;
	private Client client;
	private String objectName;
	private String helpersObjectName ;
	private String specialObjectName;
	
	public ActionEncoderRmi(){
		ControllerRepository.getInstance().setGameDataController(ControllerRepository.getInstance().getGameDataController().getUserData());
		this.user = ControllerRepository.getInstance().getGameDataController().getUserData();
		this.client = ControllerRepository.getInstance().getClientController().getClient();
		this.objectName = "main_actions_rmi";
		this.helpersObjectName = "helpers_actions_rmi";
		this.specialObjectName = "special_actions_rmi";
	}
	
	/**
	 * Metodo che costruisce il messaggio per richiedere al server di poter spostare il re
	 * @param path
	 */
	public String moveKing(ArrayList<String> path,ArrayList<Integer> politicalCardsPosition){
		ServerMessage response ;
		Integer politicalCardsPosition1[] = new Integer[politicalCardsPosition.size()];
		Character vp[] = new Character[path.size()];
		for(int i = 0; i < politicalCardsPosition1.length; i++){
			politicalCardsPosition1[i] = politicalCardsPosition.get(i);
		}
		for(int i = 0; i < vp.length; i++) vp[i] = new Character(path.get(i).charAt(0));
		
		String remoteObjectName = "main_actions_rmi";
		
		try {
			MainActionsRmiInterface mari = (MainActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(remoteObjectName);
			response = mari.moveKing(this.user, politicalCardsPosition1, vp);
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
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
		ServerMessage response = null;
		
		try {
			if(mainAction == true){
				MainActionsRmiInterface mari = (MainActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(objectName);
				response = mari.changeNoble(user, new Boolean(king), new Integer(regionNumber), noble);
			}
			else {
				HelpersActionsRmiInterface hari = (HelpersActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(helpersObjectName);
				response = hari.changeNoble(user, new Boolean(king), new Integer(regionNumber), noble);
			}
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
		
		if(mainAction == true){
			if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE)){
				return ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
			}
		}
		else
		{
			if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE)){
				return ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
			}
		}
		
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
		ServerMessage response = null;
		Integer positions[] = new Integer[politicalCardsIndex.length];
		for(int i = 0; i < positions.length; i++) positions[i] = new Integer(politicalCardsIndex[i]);
		
		try {
			MainActionsRmiInterface mari = (MainActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(objectName);
			response = mari.pickupPermitCard(user, new Integer(regionNumber), new Integer(permitCardNumber), positions);
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
			
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
			
			return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che costruisce ed invia al server un messaggio per la costruzione di un emporio
	 * @param village
	 * @param permitCardIndex
	 */
	public String placeShop(char village,int permitCardIndex){
		ServerMessage response;
		
		try {
			MainActionsRmiInterface mari = (MainActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(objectName);
			response = mari.placeShop(user, new Integer(permitCardIndex), new Character(village));
					
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
			
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
			
			return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che costruisce ed invia il messaggio al server per l'acuisto di aiutanti
	 * @param helpers
	 */
	public String buyHelper(boolean queque){
		ServerMessage response = null;
		
		try {
			HelpersActionsRmiInterface hari = (HelpersActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(helpersObjectName);
			response = hari.buyHelper(user);
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
			
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
			
			return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che genera ed invia al server il messaggio  di acquisto di una nuova azione principale
	 */
	public String buyNewMainAction(){
		ServerMessage response ;
		
		try {
			HelpersActionsRmiInterface hari = (HelpersActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(helpersObjectName);
			response = hari.buyNewMainAction(user);
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
			
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
			
			return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che genera ed invia il messaggio di doppia azione
	 * @param regionNumber
	 */
	public String doubleAction(int regionNumber){
		ServerMessage response = null;
		
		try {
			HelpersActionsRmiInterface hari = (HelpersActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(helpersObjectName);
			response = hari.doubleAction(user, new Integer(regionNumber));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) { return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode()); }
			
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
			
			return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di richiesta per acquisire una carta permesso 
	 * senza pagare
	 * @param regionNumber
	 * @param permitCardIndex
	 */
	public String acquirePermitCard(int regionNumber,int permitCardIndex){
		ServerMessage response ;
		
		try {
			SpecialActionsRmiInterface sari = (SpecialActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(this.specialObjectName);
			response = sari.acquirePermitCard(user, new Integer(regionNumber), new Integer(permitCardIndex));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) { return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode()); }
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per l'acquisizione di un bonus
	 * afferente ad un villaggio in cui il giocatore ha precedentemente costruito un emporio (no shift)
	 * @param villageFirstLetter
	 */
	public String acquireSingleVillageBonus(char villageFirstLetter){
		ServerMessage response ;
		
		try {
			SpecialActionsRmiInterface sari = (SpecialActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(this.specialObjectName);
			response = sari.acquireSingleVillageBonus(user, new Character(villageFirstLetter));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) { return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode()); }
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per l'acquisizione di un bonus
	 * afferente a due villaggi in cui il giocatore ha precedentemente costruito un emporio (no shift)
	 * @param villageFirstLetter
	 */
	public String acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter){
		ServerMessage response = null;
		
		try {
			
			SpecialActionsRmiInterface sari = (SpecialActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(this.specialObjectName);
			response = sari.acquireDoubleVillageBonus(user, new Character(firstVillageFirstLetter), new Character(secondVillageFirstLetter));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) { return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode()); }
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la generazione e l'invio di una richiesta al server per il riutilizzo di un bonus
	 * afferente ad una carta permesso precedentemente utilizzata
	 * @param permitCardIndex
	 */
	public String reusePermitCardBonus(int permitCardIndex,boolean usedPermitCard){
		ServerMessage response = null;
		
		try {
			SpecialActionsRmiInterface sari = (SpecialActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(this.specialObjectName);
			response = sari.reusePermitCardBonus(this.user, new Integer(permitCardIndex), new Boolean(usedPermitCard));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateActionSynoptic(response.getUserData().getActionSynoptic());
		} catch (UserDataException | RemoteException | NotBoundException e) { return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode()); }
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
		return response.getContent().getServerMessageContentType();
	}
}
