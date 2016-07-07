package server.rmi.actions.basics;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.basics.exceptions.CouncilException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.KingException;
import model.basics.exceptions.NobiltyPathException;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.exceptions.VillageException;
import server.command.actions.basics.MainActionCommand;
import server.command.basic.actions.exceptions.MainActionCommandException;
import server.managers.match.MatchRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.basics.MainActionsRmiInterface;

/**
 * Classe per la gestione delle azioni principali in RMI
 * @author Luca Lagni
 *
 */
public class MainActionsRmi extends UnicastRemoteObject implements  MainActionsRmiInterface{
	public MainActionsRmi() throws RemoteException { }
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metodo per il piqazzamento di un emporio in modalità RMI
	 */
	public ServerMessage placeShop(UserData data, Integer permitCardPosition,Character villageFirstLetter) throws RemoteException{
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		try {
			char village = villageFirstLetter.charValue();
			int permitCard = permitCardPosition.intValue();
			MainActionCommand mac = new MainActionCommand(data);
			
			//costruisco l'emporio
			mac.placeShop(permitCard, village);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			ud.updateActionSynoptic(mac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SHOP_PLACED, null);
			
		} catch (MainActionCommandException | GameMapException |VillageException|NobiltyPathException|HelpersPoolException|PoliticalCardsDeckException| GamerException e) { 
			
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
		} catch (UserDataException | NumberFormatException e) {
			
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
	}
	
	public ServerMessage pickupPermitCard(UserData data, Integer regionNumber1,Integer permitCardNumber1,Integer politicalCardsPosition1[]) throws RemoteException{
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception;
		
		try {
			int regionNumber = regionNumber1.intValue();
			int permitCardNumber = permitCardNumber1.intValue();
			int politicalCardsIndex[] = new int[politicalCardsPosition1.length];
			for(int i = 0 ; i < politicalCardsIndex.length; i++) politicalCardsIndex[i] = politicalCardsPosition1[i].intValue();
			MainActionCommand mac = new MainActionCommand(data);
			
			//compro la carta
			mac.pickupPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			ud.updateActionSynoptic(mac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_BUYED, null);
			
		} catch (MainActionCommandException |NobiltyPathException|PermitCardsDeckException|HelpersPoolException|PoliticalCardsDeckException| GamerException e) { 
			
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
		} catch (UserDataException | NumberFormatException e) {
			
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
	}
	
	public ServerMessage moveKing(UserData data, Integer politicalCardsPosition[],Character villagePath[]) throws RemoteException{
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception ;
		
			try {
				MainActionCommand mac = new MainActionCommand(data);
				char[] path = new char[villagePath.length];
				for(int i = 0; i < path.length; i++) path[i] = villagePath[i];
				int[] politicalCardsIndex = new int[politicalCardsPosition.length];
				for(int i = 0; i < politicalCardsIndex.length; i++) politicalCardsIndex[i] = politicalCardsPosition[i];
				//Sposto il re
				mac.moveKing(politicalCardsIndex, path);
				
				//Aggiorno i dati del repository
				mac.getMatch().updateGamer(mac.getGamer());
				MatchRepository.getInstance().updateMatch(mac.getMatch());
				
				//Aggiorno i dati utente
				UserData ud = new UserData(data.getUsername());
				ud.setupGamer(mac.getGamer());
				ud.setupMatch(mac.getMatch());
				ud.updateActionSynoptic(mac.getActionSynoptic());
				
				response = new ServerMessage(ud);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_KING_MOVED, null);
				
			} catch (MainActionCommandException | GamerException | KingException | GameMapException | HelpersPoolException | PoliticalCardsDeckException | NobiltyPathException | VillageException e) {
				
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(data);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
			} catch (UserDataException e) {
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(data);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			}
		
		return response;
	}
	
	public ServerMessage changeNoble(UserData data, Boolean isKing,Integer regionNumber1,Color noble1) throws RemoteException{
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception ;
		
		//Estraggo i parametri dal messaggio
		boolean king = isKing.booleanValue();
		int regionNumber = regionNumber1.intValue();
		Color noble = noble1;
		
			try {
				MainActionCommand mac = new MainActionCommand(data);
				
				//eseguo lo spostamento del nobile
				mac.changeNoble(king, regionNumber, noble);
				
				//Aggiorno i dati del repository
				mac.getMatch().updateGamer(mac.getGamer());
				MatchRepository.getInstance().updateMatch(mac.getMatch());
				
				//Aggiorno i dati utente
				UserData ud = new UserData(data.getUsername());
				ud.setupGamer(mac.getGamer());
				ud.setupMatch(mac.getMatch());
				ud.updateActionSynoptic(mac.getActionSynoptic());
				
				response = new ServerMessage(ud);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOBLE_CHANGED, null);
				
			} catch (MainActionCommandException | CouncilException | NoblesPoolException | GamerException e) { 
				
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(data);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
			} catch (UserDataException e) {
				
				response = new ServerMessage(data);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			}
		
		return response ;
	}
}
