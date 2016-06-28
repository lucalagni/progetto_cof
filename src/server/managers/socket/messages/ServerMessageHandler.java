package server.managers.socket.messages;

import model.basics.Match;
import model.basics.constants.MatchConstants;
import server.managers.match.MatchManager;
import server.managers.match.MatchRepository;
import command.basic.actions.ActionSynoptic;
import communication.socket.messages.ClientMessage;
import communication.socket.messages.ServerMessage;
import communication.socket.messages.ServerMessageContentType;

public class ServerMessageHandler {
	private MatchManager matchManager ;
	private MatchRepository matchRepository;
	
	public ServerMessageHandler(){
		this.matchManager = MatchManager.getInstance();
		this.matchRepository = MatchRepository.getInstance();
	}
	
	public ServerMessage handle(ClientMessage msg){
		ServerMessage response = null;
		String username = msg.getUsername();
		String matchCode = msg.getMatchCode();
		
		switch(msg.getContent()){
		   case CLIENT_REQUEST_ADD_ME:
			   response = this.clientRequestToBeAddedToAMatch(username);
			   break;
		   case CLIENT_REQUEST_CAN_I_PLAY:
			   response = this.clientRequestCanIPlay(username);
			   break;
		   case CLIENT_REQUEST_MATCH:
			   response = this.clientRequestMatch(username, matchCode);
			   break;
		   case CLIENT_REQUEST_GAMER_TURN:
			   response = this.clientRequestGamerTurn(username, matchCode);
			   break;
		   case CLIENT_REQUEST_ACTION_SYNOPTIC:
			   response = this.clientRequestActionSynoptic(username, matchCode);
			   break;
		   case CLIENT_REQUEST_MARKET_SETTER:
			   response = this.clientRequestMarketSetter(username, matchCode);
			   break;
		   case CLIENT_REQUEST_MARKET_TIME:
			   response = this.clientRequestMarketTime(username, matchCode);
			   break;
		   case CLIENT_REQUEST_MATCH_TIME:
			   response = this.clientRequestMatchTime(username, matchCode);
			   break;
			   
		   default:
			   response = this.invalidMessageFromClient(username);
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
	private ServerMessage clientRequestMatchTime(String username,String matchCode){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(matchCode);
		
		if(m == null) return this.invalidMessageFromClient(username);
		
		response = new ServerMessage(username,matchCode);
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
	private ServerMessage clientRequestMarketTime(String username, String matchCode){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(matchCode);
		
		if(m == null) return this.invalidMessageFromClient(username);
		
		response = new ServerMessage(username, matchCode);
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
	private ServerMessage clientRequestMarketSetter(String username,String matchCode){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(matchCode);
		
		if(m == null) return this.invalidMessageFromClient(username);
		response = new ServerMessage(username, matchCode);
		
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
	private ServerMessage clientRequestActionSynoptic(String username,String matchCode){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(matchCode);
		int gamerPosition = -1;
		
		//Verifico che il giocatore sia quello che effettivamente può svolgere azioni
		for(int i = 0; i < m.getGamers().size(); i++){
			if(m.getGamers().get(i).getUsername().equals(username)){
				gamerPosition = i;
				break;
			}
		}
		
		if(gamerPosition == -1) return this.invalidMessageFromClient(username);
		
		response = new ServerMessage(username, matchCode);
		
		if(m.getActualGamer() != gamerPosition){
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW, null);
			response.setActionSynoptic(null);
		}
		else {
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_ACTION_SYNOPTIC, null);
			response.setActionSynoptic(new ActionSynoptic(username, matchCode));
		}
		
		return response;
	}
	
	/**
	 * Metodo che restituisce un messaggio di richiesta del client non valida
	 * @param username
	 * @return
	 */
	private ServerMessage invalidMessageFromClient(String username){
		ServerMessage response = null;
		
		response = new ServerMessage(username, null);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, null);
		
		return response;
	}
	
	/**
	 * Metodo che restituisce il codice dell'utente che di cui è il turno di gioco
	 * @param username
	 * @param matchCode
	 * @return
	 */
	private ServerMessage clientRequestGamerTurn(String username, String matchCode){
		ServerMessage response = null;
		Match m = null;
		String[] params = new String[1];
		
		
		m = this.matchRepository.getMatch(matchCode);
		response = new ServerMessage(username, matchCode);
		if(m == null){
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_NOT_FOUND, null);
		}
		else {
			params[0] = new String("" + m.getActualGamer());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_TURN, params);
		}
		
		return response;
	}
	
	//Metodo che, se il match è pronto , lo restituisce al client
	private ServerMessage clientRequestMatch(String username, String matchCode){
		ServerMessage response = null;
		String mCode = null;
		Match m = null;
		
		mCode = this.matchRepository.getMatchCodeAssociatedTo(username);
		if(mCode.equals(matchCode) == false); //gestisco il fatto del disallineamento con un'eccezione
		
		m =  this.matchRepository.getMatchAssociatedTo(username);
		
		response = new ServerMessage(username, matchCode);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH, null);
		response.setMatch(m);
		
		return response;
	}
	
	//Metodo che gestisce la richiesta del client per sapere se il match è pronto
	private ServerMessage clientRequestCanIPlay(String username){
		ServerMessage response = null;
		String matchCode = null;
		
		matchCode = this.matchRepository.getMatchCodeAssociatedTo(username);
		if(matchCode == null){
			if(this.matchRepository.getAloneGamerAssociatedTo(username) == true){
				response = new ServerMessage(username, null);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY, null);
			}
			
			response = new ServerMessage(username, null);
			response.addContent(ServerMessageContentType.SEREVR_RESPONSE_MATCHCODE_NOT_AVAILABLE_YET, null);
		}
		else{
			response = new ServerMessage(username, matchCode);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_CODE, null);
		}
		
		return response ;
	}
	
	private ServerMessage clientRequestToBeAddedToAMatch(String username){
		ServerMessage response = null;
		
		this.matchManager.addGamer(username);
		response = new ServerMessage(username, null);
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE, null);
		 
		return response;
	}
	
}
