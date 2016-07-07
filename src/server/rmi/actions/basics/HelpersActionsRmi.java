package server.rmi.actions.basics;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.basics.exceptions.CouncilException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.PermitCardsDeckException;
import server.command.actions.basics.HelpersActionCommand;
import server.command.basic.actions.exceptions.HelpersActionCommandException;
import server.command.basic.actions.exceptions.MainActionCommandException;
import server.managers.match.MatchRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.basics.HelpersActionsRmiInterface;

/**
 * Classe che implementa le chiamate alle azioni secondarie in RMI
 * @author Luca Lagni
 *
 */

public class HelpersActionsRmi extends UnicastRemoteObject implements HelpersActionsRmiInterface {
	private static final long serialVersionUID = 1L;
	public HelpersActionsRmi() throws RemoteException { super();}

	@Override
	public ServerMessage buyHelper(UserData data) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		try {
			HelpersActionCommand hac = new HelpersActionCommand(data);
			
			//compro gli aiutanti
			hac.buyHelpers(1, false);
			
			//Aggiorno i dati del repository
			hac.getMatch().updateGamer(hac.getGamer());
			MatchRepository.getInstance().updateMatch(hac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(hac.getGamer());
			ud.setupMatch(hac.getMatch());
			ud.updateActionSynoptic(hac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOBLE_BUYED, null);
			
		} catch (HelpersActionCommandException | HelpersPoolException| GamerException e) { 
			
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
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

	@Override
	public ServerMessage changeNoble(UserData data, Boolean isKing,Integer regionNumber, Color noble) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception ;
		try{
		HelpersActionCommand hac = new HelpersActionCommand(data);
		
		//eseguo lo spostamento del nobile
		hac.changeNoble(isKing.booleanValue(), regionNumber.intValue(), noble);
		
		//Aggiorno i dati del repository
		hac.getMatch().updateGamer(hac.getGamer());
		MatchRepository.getInstance().updateMatch(hac.getMatch());
		
		//Aggiorno i dati utente
		UserData ud = new UserData(data.getUsername());
		ud.setupGamer(hac.getGamer());
		ud.setupMatch(hac.getMatch());
		ud.updateActionSynoptic(hac.getActionSynoptic());
		
		response = new ServerMessage(ud);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOBLE_CHANGED, null);
		
		} catch (MainActionCommandException | HelpersActionCommandException | CouncilException | NoblesPoolException | GamerException e) { 
			
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		
		} 
	
	
	return response ;
	}

	@Override
	public ServerMessage doubleAction(UserData data, Integer regionNumber1) throws RemoteException {
	ServerMessage response = null;
	ArrayList<String[]> parameters;
	String[] exception ;
	
	try {
		int regionNumber = regionNumber1.intValue();
		HelpersActionCommand hac = new HelpersActionCommand(data);
		
		//eseguo la doppia azione
		hac.doubleAction(regionNumber);
		
		//Aggiorno i dati del repository
		hac.getMatch().updateGamer(hac.getGamer());
		MatchRepository.getInstance().updateMatch(hac.getMatch());
		
		//Aggiorno i dati utente
		UserData ud = new UserData(data.getUsername());
		ud.setupGamer(hac.getGamer());
		ud.setupMatch(hac.getMatch());
		ud.updateActionSynoptic(hac.getActionSynoptic());
		
		response = new ServerMessage(ud);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_DOUBLE_ACTION_DONE, null);
		
	} catch (HelpersActionCommandException | PermitCardsDeckException | GamerException e) { 
		
		parameters = new ArrayList<String[]>();
		exception = new String[1];
		exception[0] = e.getMessage();
		parameters.add(exception);
		response = new ServerMessage(data);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
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

	@Override
	public ServerMessage buyNewMainAction(UserData data) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] exception;
		
		try {
			HelpersActionCommand hac = new HelpersActionCommand(data);
			
			//compro la nuova azione
			hac.buyNewMainAction();
			
			//Aggiorno i dati del repository
			hac.getMatch().updateGamer(hac.getGamer());
			MatchRepository.getInstance().updateMatch(hac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(hac.getGamer());
			ud.setupMatch(hac.getMatch());
			ud.updateActionSynoptic(hac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_NEW_MAIN_ACTION_BUYED, null);
			
		} catch (HelpersActionCommandException | HelpersPoolException | GamerException e) { 
			
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
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

}
