package server.managers.socket.messages;

import java.util.ArrayList;

import model.basics.Gamer;
import model.basics.Match;
import model.basics.PoliticalCard;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.supports.MatchPhase;
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
			   System.out.println("\n[CLIENT_REQUEST_CAN_I_PLAY] username: " + msg.getUserData().getUsername() + " request to play at " + System.currentTimeMillis());
			   response = this.clientRequestCanIPlay(msg.getUserData());
			   System.out.println("\n[SERVER_RESPONSE_TO_CAN_I_PLAY] of username: " + response.getContent() + " to request to play at " + System.currentTimeMillis());
			   break;
		   case CLIENT_REQUEST_MATCH:
			   response = this.clientRequestMatch(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_GO_OFFLINE:
			   response = clientRequestGoOffline(msg.getUserData());
			   break;
		   case CLIENT_REQUEST_GAMER_TURN:
			   System.out.println("\n[CLIENT_REQUEST_GAMER_TURN] username: " + msg.getUserData().getUsername() + " request gamer turn at " + System.currentTimeMillis());
			   response = this.clientRequestGamerTurn(msg.getUserData());
			   System.out.println("\n[SERVER_RESPONSE_TO_GAMER_TURN] of username: " + msg.getUserData().getUsername() + " response gamer turn at " + System.currentTimeMillis());
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
		   case CLIENT_REQUEST_MOVE_KING:
			   ServerMatchActionHandler smah1 = new ServerMatchActionHandler();
			   response = smah1.clientRequestMoveKing(msg);
			   break;
		   case CLIENT_REQUEST_CHANGE_NOBLE:
			   ServerMatchActionHandler smah2 = new ServerMatchActionHandler();
			   response = smah2.clientRequestChangeNoble(msg);
			   break;
		   case CLIENT_REQUEST_BUY_PERMIT_CARD:
			   ServerMatchActionHandler smah3 = new ServerMatchActionHandler();
			   response = smah3.clientRequestBuyPermitCard(msg);
			   break;
		   case CLIENT_REQUEST_TO_PLACE_A_SHOP:
			   ServerMatchActionHandler smah4 = new ServerMatchActionHandler();
			   response = smah4.clientRequestPlaceShop(msg);
			   break;
		   case CLIENT_REQUEST_BUY_HELPERS:
			   ServerMatchActionHandler smah5 = new ServerMatchActionHandler();
			   response = smah5.clientRequestBuyHelper(msg);
			   break;
		   case CLIENT_REQUEST_BUY_NEW_MAIN_ACTION:
			   ServerMatchActionHandler smah6 = new ServerMatchActionHandler();
			   response = smah6.clientRequestBuyNewMainAction(msg);
			   break;
		   case CLIENT_REQUEST_DOUBLE_ACTION:
			   ServerMatchActionHandler smah7 = new ServerMatchActionHandler();
			   response = smah7.clientRequestDoubleAction(msg);
			   break;
		   case CLIENT_REQUEST_ACQUIRE_PERMIT_CARD:
			   ServerMatchActionHandler smah8 = new ServerMatchActionHandler();
			   response = smah8.clientRequestAcquirePermitCard(msg);
			   break;
		   case CLIENT_REQUEST_ACQUIRE_SINGLE_VILLAGE_BONUS:
			   ServerMatchActionHandler smah9 = new ServerMatchActionHandler();
			   response = smah9.clientRequestAcquireSingleVillageBonus(msg);
			   break; 
		   case CLIENT_REQUEST_ACQUIRE_DOUBLE_VILLAGE_BONUS:
			   ServerMatchActionHandler smah10 = new ServerMatchActionHandler();
			   response = smah10.clientRequestAcquireDoubleVillageBonus(msg);
			   break; 
		   case CLIENT_REQUEST_REUSE_PERMIT_CARD_BONUS:
			   ServerMatchActionHandler smah11 = new ServerMatchActionHandler();
			   response = smah11.clientRequestReusePermitCardBonus(msg);
			   break; 
		   case CLIENT_REQUEST_SET_AGENT:
			   ServerSetterActionHandler ssah = new ServerSetterActionHandler();
			   response = ssah.updateAgent(msg);
			   break;
		   case CLIENT_REQUEST_BUY_HELPERS_ITEM:
			   ServerMarketActionHandler skah1 = new ServerMarketActionHandler();
			   response = skah1.clientRequestBuyHelpersItem(msg);
			   break;
		   case CLIENT_REQUEST_BUY_POLITICAL_CARD_ITEM:
			   ServerMarketActionHandler skah2 = new ServerMarketActionHandler();
			   response = skah2.clientRequestBuyPoliticalCardItem(msg);
			   break;
		   case CLIENT_REQUEST_BUY_PERMIT_CARD_ITEM:
			   ServerMarketActionHandler skah3 = new ServerMarketActionHandler();
			   response = skah3.clientRequestBuyPermitCardItem(msg);
			   break;
		   
		   default:
			   response = this.invalidMessageFromClient(msg.getUserData());
			   break;
		}
		
		return response;
	}
	
	private ServerMessage clientRequestGoOffline(UserData data){
		ServerMessage response = null;
		Match m = this.matchRepository.getMatch(data.getMatchCode());
		
		if(m == null){
			response = new ServerMessage(data);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MATCH_NOT_FOUND, null);
		}
		else
		{
			boolean flag = false ;
			for(int i = 0; i < m.getGamers().size(); i++){
				if(m.getGamers().get(i).getUsername().equals(data.getUsername())){
					m.getGamers().get(i).toggleOffline();
					flag = true;
					MatchRepository.getInstance().updateMatch(m);
					break;
				}
			}
			
			response = new ServerMessage(data);
			if(flag == true){
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_OFFLINE, null);
			}
			else{
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_NOT_IN_MATCH, null);
			}
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
	@SuppressWarnings("unused")
	private synchronized ServerMessage clientRequestGamerTurn(UserData data){
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
	
	/**
	 * Metodo che gestisce la richiesta di un client di sapere se è associato ad un match
	 * @param data
	 * @return
	 */
	private ServerMessage clientRequestCanIPlay(UserData data){
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
	
	/**
	 * Metodo che gestisce la richiesta di gioco di un utente
	 * @param data
	 * @return
	 */
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
