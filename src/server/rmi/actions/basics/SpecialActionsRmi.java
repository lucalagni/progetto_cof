package server.rmi.actions.basics;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.NobiltyPathException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;
import server.command.actions.basics.SpecialActionCommand;
import server.command.basic.actions.exceptions.SpecialActionCommandException;
import server.managers.match.MatchRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.basics.SpecialActionsRmiInterface;

public class SpecialActionsRmi extends UnicastRemoteObject implements SpecialActionsRmiInterface {
	public SpecialActionsRmi() throws RemoteException { super(); }

	private static final long serialVersionUID = 1L;

	@Override
	public ServerMessage reusePermitCardBonus(UserData data, Integer permitCardPosition, Boolean usedPermitCard1) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		try {
			int index = permitCardPosition.intValue();
			boolean usedPermitCard = usedPermitCard1.booleanValue();
			SpecialActionCommand sac = new SpecialActionCommand(data);
			
			//compro la nuova azione
			sac.reusePermitCardBonus(index, usedPermitCard);
			
			//Aggiorno i dati del repository
			sac.getMatch().updateGamer(sac.getGamer());
			MatchRepository.getInstance().updateMatch(sac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_BONUS_REUSED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
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
	public ServerMessage acquireDoubleVillageBonus(UserData data, Character firstVillageFirstLetter, Character secondVillageFirstLetter) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] exception = null;
		
		try {
			char village1 = firstVillageFirstLetter.charValue();
			char village2 = secondVillageFirstLetter.charValue();
			SpecialActionCommand sac = new SpecialActionCommand(data);
			
			//compro la nuova azione
			sac.acquireDoubleVillageBonus(village1, village2);
			
			//Aggiorno i dati del repository
			sac.getMatch().updateGamer(sac.getGamer());
			MatchRepository.getInstance().updateMatch(sac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_DOUBLE_VILLAGE_BONUS_ACQUIRED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
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
	public ServerMessage acquireSingleVillageBonus(UserData data, Character villageFirstLetter) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] exception ;
		
		try {
			char village = villageFirstLetter.charValue();
			SpecialActionCommand sac = new SpecialActionCommand(data);
			
			//compro la nuova azione
			sac.acquireSingleVillageBonus(village, true);
			
			//Aggiorno i dati del repository
			sac.getMatch().updateGamer(sac.getGamer());
			MatchRepository.getInstance().updateMatch(sac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SINGLE_VILLAGE_BONUS_ACQUIRED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
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
	public ServerMessage acquirePermitCard(UserData data, Integer regionNumber1, Integer permitCardNumber) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		try {
			int regionNumber = regionNumber1.intValue();
			int permitCard = permitCardNumber.intValue();
			SpecialActionCommand sac = new SpecialActionCommand(data);
			
			//compro la nuova azione
			sac.acquirePermitCard(regionNumber, permitCard);
			
			//Aggiorno i dati del repository
			sac.getMatch().updateGamer(sac.getGamer());
			MatchRepository.getInstance().updateMatch(sac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_BUYED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PermitCardsDeckException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
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
