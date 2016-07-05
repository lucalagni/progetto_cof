package server.managers.socket.messages;

import java.util.ArrayList;

import model.basics.exceptions.GamerException;
import server.command.actions.market.MarketActionCommand;
import server.command.actions.market.exceptions.MarketActionCommandException;
import server.managers.match.MatchRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ClientMessage;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;

/**
 * Classe per la gestione dei messaggi di azioni afferenti al market larto server
 * @author Luca Lagni
 *
 */
public class ServerMarketActionHandler {
	
	/**
	 * Metodo per la gestione della compravendita di lotti di aiutanti
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestBuyHelpersItem(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		int sellerIndex = Integer.parseInt(msg.getParameters().get(0)[0]);
		int helpersItemIndex = Integer.parseInt(msg.getParameters().get(0)[1]);
		
		try {
			MarketActionCommand mac = new MarketActionCommand(msg.getUserData());
			
			mac.buyHelpersItem(sellerIndex, helpersItemIndex);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ITEM_BUYED, null);
			
		} catch (MarketActionCommandException | GamerException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ITEM_TRANSACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			
		}
		
		return response;
	}
	
	public ServerMessage clientRequestBuyPoliticalCardItem(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		int seller = Integer.parseInt(msg.getParameters().get(0)[0]);
		int itemIndex = Integer.parseInt(msg.getParameters().get(0)[1]);
		
		try {
			MarketActionCommand mac = new MarketActionCommand(msg.getUserData());
			
			mac.buyPoliticalCardItem(seller, itemIndex);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_POLITICAL_CARD_ITEM_BUYED, null);
			
		} catch (MarketActionCommandException | GamerException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			
		}
		
		return response;
	}
	
	public ServerMessage clientRequestBuyPermitCardItem(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		int seller = Integer.parseInt(msg.getParameters().get(0)[0]);
		int itemIndex = Integer.parseInt(msg.getParameters().get(0)[1]);
		
		try {
			MarketActionCommand mac = new MarketActionCommand(msg.getUserData());
			
			mac.buyPermitCardItem(seller, itemIndex);
			
			//Aggiorno i dati del repository
			mac.getMatch().updateGamer(mac.getGamer());
			MatchRepository.getInstance().updateMatch(mac.getMatch());
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_BUYED, null);
			
		} catch (MarketActionCommandException | GamerException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			
		}
		
		return response;
	}
}
