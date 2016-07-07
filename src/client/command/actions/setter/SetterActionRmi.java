package client.command.actions.setter;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import client.Client;
import client.command.actions.basics.exceptions.codes.ActionFacadeExceptionCode;
import client.controller.ControllerRepository;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.actions.setter.SetterActionsRmiInterfaces;

/**
 * Classe che si occupa dello scambio di messaggi finalizzazto alla fase di setting del
 * merket
 * @author Luca Lagni
 *
 */
public class SetterActionRmi {
	private UserData data;
	private Client client;
	private static final String SETTER_OBJECT = "setter_actions_rmi";
	
	public SetterActionRmi(){
		this.setUserData(ControllerRepository.getInstance().getGameDataController().getUserData());
		this.setClient(ControllerRepository.getInstance().getClientController().getClient());
	}
	
	private void setUserData(UserData userData){ this.data = userData; }
	private void setClient(Client client){ this.client = client; }
	
	/**
	 * Metodo per l'invio delle modifiche al agent in modalità socket
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unused")
	public String sendAgent(UserData data){
		
		try {
			SetterActionsRmiInterfaces mari = (SetterActionsRmiInterfaces) this.client.getRmiClient().getRegistry().lookup(SETTER_OBJECT);
			data = mari.setupAgent(data);
			ControllerRepository.getInstance().getGameDataController().getUserData().updateMatch(data.getMatch());
			ControllerRepository.getInstance().getGameDataController().getUserData().updateGamer(data.getGamer());
		} catch (UserDataException | RemoteException | NotBoundException e) { 
			return new String(ActionFacadeExceptionCode.CORRUPTED_USER_DATA.getExceptionCode());
		}
		
		if(data == null) return ServerMessageContentType.SERVER_RESPONSE_AGENT_SET_FAILURE.getServerMessageContentType() ;

		
		return "AGENT_SETTED";
	}
	
	public UserData getUserData(){ return this.data; }
	public Client getClient(){ return this.client; }
}
