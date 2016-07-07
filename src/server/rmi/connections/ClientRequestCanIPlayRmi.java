package server.rmi.connections;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.managers.match.MatchRepository;
import model.basics.Gamer;
import model.basics.Match;
import commons.data.ActionSynoptic;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.connections.ClientRequestCanIPlayRmiInterface;

/**
 * Classe lato server che implementa la funzionalità di richiesta di match lato client
 * @author Luca Lagni
 *
 */
public class ClientRequestCanIPlayRmi extends UnicastRemoteObject implements ClientRequestCanIPlayRmiInterface{
	private static final long serialVersionUID = 1L;
	private MatchRepository matchRepository;
	
	public ClientRequestCanIPlayRmi() throws RemoteException {
		this.matchRepository = MatchRepository.getInstance(); 
	}

	/**
	 * Metodo per la richiesta di adesione ad un match da parte di un'utente
	 */
	@Override
	public ServerMessage clientRequestCanIPlay(UserData data) throws RemoteException{
	ServerMessage response = null;
	String matchCode = null;
	Match m = null;
	boolean flag = false ;
	
	//cerco di ottenere il match associato ad un certo utente
	matchCode = this.matchRepository.getMatchCodeAssociatedTo(data.getUsername());
	if(matchCode == null){
		if(this.matchRepository.getAloneGamerAssociatedTo(data.getUsername()) == true)
		{
			/*
			 * Caso in cui il match non è disponibile e l'utente è stato messo nella coda
			 * degli utenti che non hanno la possibilità di giocare
			 */
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY, null);
		}
		else 
		{
			/*
			 * Il server risponde che non si sa ancora se il giocatore sia stato a omeno aggiunto 
			 * ad un match
			 */
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_NOT_AVAILABLE_YET, null);
		}
		
	}
	else{
		m = this.matchRepository.getMatch(matchCode);
		for(Gamer gam : m.getGamers()){
			if(gam.getUsername().equals(data.getUsername())){
				try {
					data.setupGamer(gam);
					data.setupMatch(m);
					flag = true;
					break;
				} catch (UserDataException e) { e.printStackTrace(); }
			}
		}
		
		if(flag == false){
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_NOT_IN_MATCH, null);
		}
		else {
			try {
				data.updateActionSynoptic(new ActionSynoptic(data.getUsername(), data.getMatchCode()));
			} catch (UserDataException e) {  }
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH, null);
		}
	}
	
	return response ;
	}

}
