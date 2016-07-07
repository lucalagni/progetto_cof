package client.command.actions.market;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import client.Client;
import client.command.actions.basics.exceptions.codes.ActionFacadeExceptionCode;
import client.controller.ControllerRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.market.MarketActionsRmiInterface;

/**
 * Classe che implementa i metodi per la richiesta tramite socket
 * delle operazioni da fare per il market
 * 
 * @author Luca Lagni
 *
 */
public class MarketActionEncoderRmi {
	private UserData data;
	private Client client;
	private static final String objectName = "market_actions_rmi";
	
	public MarketActionEncoderRmi(){
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
		ServerMessage response = null;
		
		try {
			MarketActionsRmiInterface sari = (MarketActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(objectName);
			response = sari.buyHelpersItem(data, new Integer(sellerIndex), new Integer(helpersItemIndex));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ITEM_TRANSACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_HELPERS_ITEM_TRANSACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la richiesta di acquisto di una carta politica
	 * @param sellerIndex
	 * @param ItemIndex
	 * @return
	 */
	public String buyPoliticalCardItem(int sellerIndex,int politicalCardItemIndex){
		ServerMessage response = null;
		try {
			MarketActionsRmiInterface sari = (MarketActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(objectName);
			response = sari.buyPoliticalCardsItem(data, new Integer(sellerIndex), new Integer(politicalCardItemIndex));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_POLITICAL_CARD_ITEM_TRANSACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_POLITICAL_CARD_ITEM_TRANSACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la richiesta di acquisto di una carta permesso
	 * @param sellerIndex
	 * @param ItemIndex
	 * @return
	 */
	public String buyPermitCardItem(int sellerIndex,int permitCardItemIndex){
		ServerMessage response = null;
		
		try {
			MarketActionsRmiInterface sari = (MarketActionsRmiInterface) this.client.getRmiClient().getRegistry().lookup(objectName);
			response = sari.buyPermitCardItem(data, new Integer(sellerIndex), new Integer(permitCardItemIndex));
			
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(response.getUserData().getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(response.getUserData().getGamer());
		} catch (UserDataException | RemoteException | NotBoundException e) {
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
		
		if(response.getContent().getServerMessageContentType().equals(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE)){
			return ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE + " : "+response.getParameters().get(0)[0];
		}
		
		return response.getContent().getServerMessageContentType();
	}
}
