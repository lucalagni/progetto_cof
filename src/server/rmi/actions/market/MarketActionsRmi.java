package server.rmi.actions.market;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.basics.exceptions.GamerException;
import server.command.actions.market.MarketActionCommand;
import server.command.actions.market.exceptions.MarketActionCommandException;
import server.command.basic.actions.exceptions.MainActionCommandException;
import server.managers.match.MatchRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.market.MarketActionsRmiInterface;

/**
 * Classe per la gestione delle chiamate inerenti alla fase di market del gioco attraverso
 * tecnologia RMI
 * @author Luca Lagni
 *
 */
public class MarketActionsRmi extends UnicastRemoteObject implements MarketActionsRmiInterface{
	public MarketActionsRmi() throws RemoteException { super(); }

	private static final long serialVersionUID = 1L;

	@Override
	public ServerMessage buyHelpersItem(UserData data, Integer sellerIndex1,Integer helpersItemIndex1) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		int sellerIndex = sellerIndex1.intValue();
		int helpersItemIndex = helpersItemIndex1.intValue();
		
		try {
			MarketActionCommand mac = new MarketActionCommand(data);
			
			mac.buyHelpersItem(sellerIndex, helpersItemIndex);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ITEM_BUYED, null);
			
		} catch (MarketActionCommandException | MainActionCommandException | GamerException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ITEM_TRANSACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			
		}
		
		return response;
	}

	@Override
	public ServerMessage buyPoliticalCardsItem(UserData data,
			Integer sellerIndex, Integer politicalCardItemIndex)
			throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		int seller = sellerIndex.intValue();
		int itemIndex = politicalCardItemIndex.intValue();
		
		try {
			MarketActionCommand mac = new MarketActionCommand(data);
			
			mac.buyPoliticalCardItem(seller, itemIndex);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_POLITICAL_CARD_ITEM_BUYED, null);
			
		} catch (MarketActionCommandException | MainActionCommandException | GamerException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			
		}
		
		return response;
	}

	@Override
	public ServerMessage buyPermitCardItem(UserData data, Integer sellerIndex,
			Integer permitCardItemIndex) throws RemoteException {
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		int seller = sellerIndex.intValue();
		int itemIndex = permitCardItemIndex.intValue();
		
		try {
			MarketActionCommand mac = new MarketActionCommand(data);
			
			mac.buyPermitCardItem(seller, itemIndex);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(data.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_BUYED, null);
			
		} catch (MarketActionCommandException | MainActionCommandException | GamerException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			
		}
		
		return response;
	}

	

}
