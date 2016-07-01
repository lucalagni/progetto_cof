package server.managers.socket.messages;

import java.util.ArrayList;

import model.basics.Match;
import model.basics.constants.MatchConstants;
import server.managers.match.MatchManager;
import server.managers.match.MatchRepository;
import commons.data.ActionSynoptic;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.*;

public class ServerMessageHandler {
	private MatchManager matchManager ;
	private MatchRepository matchRepository;
	
	public ServerMessageHandler(){
		this.matchManager = MatchManager.getInstance();
		this.matchRepository = MatchRepository.getInstance();
	}
	
	public ServerMessage handle(ClientMessage msg){
		ServerMessage response = null;
		
		switch(msg.getContent()){
		   case CLIENT_REQUEST_ADD_ME:
			   response = this.clientRequestToBeAddedToAMatch(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_CAN_I_PLAY:
			   response = this.clientRequestCanIPlay(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_MATCH:
			   response = this.clientRequestMatch(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_GAMER_TURN:
			   response = this.clientRequestGamerTurn(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_ACTION_SYNOPTIC:
			   response = this.clientRequestActionSynoptic(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_MARKET_SETTER:
			   response = this.clientRequestMarketSetter(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_MARKET_TIME:
			   response = this.clientRequestMarketTime(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_MATCH_TIME:
			   response = this.clientRequestMatchTime(msg.getUserData());
			   break;
			   
		   default:
			   response = this.invalidMessageFromClient(msg.getUserData());
			   break;
		}
		
		return response;
	}
	
	/**
	 * Metodo che verifica se è ripresa la fase di gioco
	 * @param username
	 * @param matchCode
	 * @return
	 */
	private ServerMessage clientRequestMatchTime(UserData data){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(data.getMatchCode());
		
		if(m == null) return this.invalidMessageFromClient(data);
		
		response = new ServerMessage(data);
		if((m.getActualGamer() != MatchConstants.TIME_TO_MARKET) && (m.getActualGamer() != MatchConstants.TIME_TO_MARKET_SETUP)){
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_TIME, null);
		}
		else {
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOT_MATCH_TIME, null);
		}
		
		return response;
	}
	
	/**
	 * Metodo che verifica se è stata avviata la fase di market
	 * @param username
	 * @param matchCode
	 * @return
	 */
	private ServerMessage clientRequestMarketTime(UserData data){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(data.getMatchCode());
		
		if(m == null) return this.invalidMessageFromClient(data);
		
		response = new ServerMessage(data);
		if(m.getActualGamer() == MatchConstants.TIME_TO_MARKET) response.addContent(ServerMessageContentType.SERVER_RESPONSE_MARKET_TIME, null);
		else response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOT_MARKET_TIME, null);
		
		return response;
	}
	
	/**
	 * Metodo che verifica se è il momento che il client prepari i dati per il market
	 * @param username
	 * @param matchCode
	 * @return
	 */
	private ServerMessage clientRequestMarketSetter(UserData data){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(data.getMatchCode());
		
		if(m == null) return this.invalidMessageFromClient(data);
		response = new ServerMessage(data);
		
		if(m.getActualGamer() == MatchConstants.TIME_TO_MARKET_SETUP) response.addContent(ServerMessageContentType.SERVER_RESPONSE_MARKET_SETTER, null);
		else response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOT_MARKET_SETTER, null);
		
		return response;
	}
	
	/**
	 * Metodo che , nel caso sia il turno del giocatore, gli restituisce il sinottico con le azioni
	 * che può eseguire
	 * @param username
	 * @param matchCode
	 * @return
	 */
	private ServerMessage clientRequestActionSynoptic(UserData data){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(data.getMatchCode());
		int gamerPosition = -1;
		
		//Verifico che il giocatore sia quello che effettivamente può svolgere azioni
		for(int i = 0; i < m.getGamers().size(); i++){
			if(m.getGamers().get(i).getUsername().equals(data.getUsername())){
				gamerPosition = i;
				break;
			}
		}
		
		if(gamerPosition == -1) return this.invalidMessageFromClient(data);
		
		response = new ServerMessage(data);
		
		if(m.getActualGamer() != gamerPosition){
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW, null);
		}
		else {
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_ACTION_SYNOPTIC, null);
			try {
				data.updateActionSynoptic(new ActionSynoptic(data.getUsername(), data.getMatchCode()));
			} catch (UserDataException e) {
				e.printStackTrace();
			}
		}
		
		return response;
	}
	
	/**
	 * Metodo che restituisce un messaggio di richiesta del client non valida
	 * @param username
	 * @return
	 */
	private ServerMessage invalidMessageFromClient(UserData data){
		ServerMessage response = null;
		
		response = new ServerMessage(data);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, null);
		
		return response;
	}
	
	/**
	 * Metodo che restituisce il codice dell'utente che di cui è il turno di gioco
	 * @param username
	 * @param matchCode
	 * @return
	 */
	private ServerMessage clientRequestGamerTurn(UserData data){
		ServerMessage response = null;
		Match m = null;
		String[] params = new String[1];
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		
		
		m = this.matchRepository.getMatch(data.getMatchCode());
		response = new ServerMessage(data);
		if(m == null){
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_NOT_FOUND, null);
		}
		else {
			params[0] = new String("" + m.getActualGamer());
			parameters.add(params);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_TURN, parameters);
		}
		
		return response;
	}
	
	//Metodo che, se il match è pronto , lo restituisce al client
	private ServerMessage clientRequestMatch(UserData data){
		ServerMessage response = null;
		String mCode = null;
		Match m = null;
		
		mCode = this.matchRepository.getMatchCodeAssociatedTo(data.getUsername());
		if(mCode.equals(data.getMatchCode()) == false); //gestisco il fatto del disallineamento con un'eccezione
		
		m =  this.matchRepository.getMatchAssociatedTo(data.getUsername());
		
		response = new ServerMessage(data);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH, null);
		try {
			response.getUserData().updateMatch(m);
		} catch (UserDataException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	//Metodo che gestisce la richiesta del client per sapere se il match è pronto
	private ServerMessage clientRequestCanIPlay(UserData data){
		ServerMessage response = null;
		String matchCode = null;
		
		matchCode = this.matchRepository.getMatchCodeAssociatedTo(data.getUsername());
		if(matchCode == null){
			if(this.matchRepository.getAloneGamerAssociatedTo(data.getUsername()) == true){
				response = new ServerMessage(data);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY, null);
			}
			
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SEREVR_RESPONSE_MATCHCODE_NOT_AVAILABLE_YET, null);
		}
		else{
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_CODE, null);
		}
		
		return response ;
	}
	
	private ServerMessage clientRequestToBeAddedToAMatch(UserData data){
		ServerMessage response = new ServerMessage(data);
		boolean available = this.matchManager.addGamer(data.getUsername());
		
		if(available == false) {
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_USERNAME_NOT_AVAILABLE, null);
		}
		else {
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE, null);
		}
		return response;
	}
	
}
