package client.command.connection.setup;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.*;
import commons.rmi.interfaces.connections.ClientRequestCanIPlayRmiInterface;
import commons.rmi.interfaces.connections.ClientRequestGamerTurnRmiInterface;
import commons.rmi.interfaces.connections.ClientRequireToPlayRmiInterface;
import client.controller.ControllerRepository;
import client.Client;

/**
 * Classe che implementa le azioni preliminari per la realizzazione di un match
 * utilizzando la tecnologia Socket
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionSetupRmi {
	private UserData data = null;
	private Client client = null;
	private static final String ADD_ME = "client_require_to_play_rmi";
	private static final String CAN_PLAY = "client_request_can_i_play_rmi";
	private static final String TURN = "client_request_gamer_turn_rmi";
	
	public GameConnectionSetupRmi(){
		this.client = ControllerRepository.getInstance().getClientController().getClient();
		this.data = ControllerRepository.getInstance().getGameDataController().getUserData();
	}
	
	//Metodo che invia al server la richiesta di connessione ad un match
	public String clientRequireAddMe(){
		String sm = null;
		
		ClientRequireToPlayRmiInterface crtpi;
		try {
			crtpi = (ClientRequireToPlayRmiInterface) this.client.getRmiClient().getRegistry().lookup(ADD_ME);
			sm = crtpi.clientRequireToPlay(data);
		} catch (RemoteException | NotBoundException e) {
			return sm;
		}
		
		return sm;
	}
	
	public String clientRequestToGoOffline(){
		ClientMessage request = new ClientMessage(this.data);
		ServerMessage response = null;
		
		request.addContent(ClientMessageContentType.CLIENT_REQUEST_GO_OFFLINE, null);
		response = this.client.sendMessage(request);
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo che si occupa di tornare un match per il giocatore qualora esso sia disponibile
	 * @return
	 * @throws UserDataException 
	 */
	public String clientRequestCanIPlay() throws UserDataException{
		ServerMessage response = null;
		
		ClientRequestCanIPlayRmiInterface crtpi;
		try {
			crtpi = (ClientRequestCanIPlayRmiInterface) this.client.getRmiClient().getRegistry().lookup(CAN_PLAY);
			response = crtpi.clientRequestCanIPlay(data);
		} catch (RemoteException | NotBoundException e) {
			return response.getContent().getServerMessageContentType();
		}
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_MATCH){
			this.data.setupGamer(response.getUserData().getGamer());
			this.data.setupMatch(response.getUserData().getMatch());
			this.data.updateActionSynoptic(response.getUserData().getActionSynoptic());
		}
		
		return response.getContent().getServerMessageContentType();
	}
	
	/**
	 * Metodo per la verifica del turno giocatore
	 * @return
	 * @throws UserDataException 
	 */
	public int clientRequestGamerTurn() throws UserDataException{
		ServerMessage response = null;
		int gamerTurn = 0;
		
		ClientRequestGamerTurnRmiInterface crtpi;
		try {
			crtpi = (ClientRequestGamerTurnRmiInterface) this.client.getRmiClient().getRegistry().lookup(TURN);
			response = crtpi.clientRequestGamerTurn(data);
		} catch (RemoteException | NotBoundException e) {
			return -1;
		}
		
		if(response.getContent() == ServerMessageContentType.SERVER_RESPONSE_GAMER_TURN){
			this.data.updateMatch(response.getUserData().getMatch());
			this.data.updateGamer(response.getUserData().getGamer());
			this.data.updateActionSynoptic(response.getUserData().getActionSynoptic());
			gamerTurn = Integer.parseInt(response.getParameters().get(0)[0]);
		}
		
		return gamerTurn;
	}

}
