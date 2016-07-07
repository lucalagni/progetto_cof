package server.rmi.connections;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server.managers.match.MatchRepository;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.PoliticalCard;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.supports.MatchPhase;
import commons.data.ActionSynoptic;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import commons.rmi.interfaces.connections.ClientRequestGamerTurnRmiInterface;

/**
 * Classe per l'implementazione del turno di gioco attraverso RMI
 * @author Luca Lagni
 *
 */
public class ClientRequestGamerTurnRmi extends UnicastRemoteObject implements  ClientRequestGamerTurnRmiInterface{
	private static final long serialVersionUID = 1L;
	private MatchRepository matchRepository;
	public ClientRequestGamerTurnRmi() throws RemoteException { 
		super(); 
		this.matchRepository = MatchRepository.getInstance();
	}

	@SuppressWarnings("unused")
	@Override
	public ServerMessage clientRequestGamerTurn(UserData data) throws RemoteException{
	ServerMessage response = null;
	Match m = null;
	Gamer g = null;
	PoliticalCard pc = null;
	String[] params = new String[1];
	ArrayList<String[]> parameters = new ArrayList<String[]>();
	
	
	m = this.matchRepository.getMatch(data.getMatchCode());
	for(int i = 0; i < m.getGamers().size(); i++){
		if(m.getGamers().get(i).getUsername().equals(data.getUsername())){
			g = m.getGamers().get(i);
			break;
		}
	}
	try {
		if(m.getMatchPhase().equals(MatchPhase.MATCH_PHASE)){
			if(g.getUsername().equals(m.getGamers().get(m.getActualGamer()).getUsername())){
				pc = m.getBoard().getPoliticalCardsDeck().pickupCard();
				g.addPoliticalCard(pc);
				m.updateGamer(g);
			}
		}
		
	} catch (PoliticalCardsDeckException e1) { e1.printStackTrace(); }
	this.matchRepository.updateMatch(m);
	
	if(m == null){
		response = new ServerMessage(data);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_NOT_FOUND, null);
	}
	else {
		try {
			data.updateMatch(m);
			data.updateGamer(g);
			response = new ServerMessage(data);
		} catch (UserDataException e) {
			e.printStackTrace();
		}
		
		if(data.getUsername().equals(m.getGamers().get(m.getActualGamer())) == false){
			try {
				data.updateActionSynoptic(new ActionSynoptic(data.getUsername(), data.getMatchCode()));
			} catch (UserDataException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				data.updateActionSynoptic(data.getActionSynoptic());
			} catch (UserDataException e) {
				e.printStackTrace();
			}
		}
		params[0] = new String("" + m.getActualGamer());
		parameters.add(params);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_TURN, parameters);
	}
	
	return response;
	}

}
