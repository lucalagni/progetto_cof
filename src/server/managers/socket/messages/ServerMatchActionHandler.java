package server.managers.socket.messages;

import java.awt.Color;
import java.util.ArrayList;

import model.basics.Match;
import model.basics.exceptions.CouncilException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.KingException;
import model.basics.exceptions.NobiltyPathException;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.exceptions.VillageException;
import commons.data.UserData;
import commons.data.exceptions.UserDataException;
import commons.messages.ClientMessage;
import commons.messages.ServerMessage;
import commons.messages.ServerMessageContentType;
import server.command.actions.basics.HelpersActionCommand;
import server.command.actions.basics.MainActionCommand;
import server.command.actions.basics.SpecialActionCommand;
import server.command.basic.actions.exceptions.HelpersActionCommandException;
import server.command.basic.actions.exceptions.MainActionCommandException;
import server.command.basic.actions.exceptions.SpecialActionCommandException;
import server.managers.match.MatchRepository;

/**
 * Classe per la gestione delle operazioni lato server
 * @author Luca Lagni
 *
 */
public class ServerMatchActionHandler {
	
	public void ServerMacthActionHandler(){ }
	
	
	/**
	 * Metodo che consente di ottenere il match più aggiornato (utile per vedere se un giocatore può ù
	 * ancora svolgere delle azioni
	 * @param matchCode
	 * @return
	 */
	private Match retriveMatch(String matchCode){ 
		 return MatchRepository.getInstance().getMatch(matchCode); 
	}
	
	/**
	 * Metodo che verifica se il giocatore che richiede le mosse è ancora nelle condizioni di poterlo fare
	 * @param data
	 * @return
	 */
	private boolean isActualGamer(UserData data){
		Match m = this.retriveMatch(data.getMatchCode());
		
		if(m.getGamers().get(m.getActualGamer()).equals(data.getUsername())) return true;
		return false ;
	}
	
	/**
	 * Metodo che fornisce una risposta pronta nel caso il giocatore non sia nelle condizioni di poter fare
	 * la mossa 
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unused")
	private ServerMessage serverResponseGamerCannotPlay(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		try {
			msg.getUserData().updateGamer(msg.getUserData().getGamer());
			msg.getUserData().updateMatch(this.retriveMatch(msg.getMatchCode()));
		} catch (UserDataException e){ 
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
		}
		response = new ServerMessage(msg.getUserData());
		response.addContent(ServerMessageContentType.SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW, parameters);
		
		return response ;
	}
	
	/*============================================{AZIONI SPECIALI}====================================================== */
	
	/**
	 * Metodo che consente di riutilizzare il bonus di una carta permesso precedentemente utilizzata
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestReusePermitCardBonus(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		int index = Integer.parseInt(msg.getParameters().get(0)[0]);
		boolean usedPermitCard = Boolean.parseBoolean(msg.getParameters().get(0)[1]);
		
		try {
			SpecialActionCommand sac = new SpecialActionCommand(msg.getUserData());
			
			//compro la nuova azione
			sac.reusePermitCardBonus(index, usedPermitCard);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_BONUS_REUSED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
	}
	/**
	 * Metodo per la richiesta di acquisizione del bonus di due villaggi in cui si è già costruito
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestAcquireDoubleVillageBonus(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] exception = null;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		char village1 = msg.getParameters().get(0)[0].charAt(0);
		char village2 = msg.getParameters().get(0)[1].charAt(0);
		
		try {
			SpecialActionCommand sac = new SpecialActionCommand(msg.getUserData());
			
			//compro la nuova azione
			sac.acquireDoubleVillageBonus(village1, village2);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_DOUBLE_VILLAGE_BONUS_ACQUIRED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
	}
	/**
	 * Metodo per la richiesta di acquisizione del bonus di un villaggio singolo in cui si è già costruito
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestAcquireSingleVillageBonus(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] exception ;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		char village = msg.getParameters().get(0)[0].charAt(0);
		
		try {
			SpecialActionCommand sac = new SpecialActionCommand(msg.getUserData());
			
			//compro la nuova azione
			sac.acquireSingleVillageBonus(village, true);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SINGLE_VILLAGE_BONUS_ACQUIRED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
	}
	
	/**
	 * Metodo per il processamento della richiesta da parte dell'utente dell'acquisizione di una carta permesso 
	 * senza pagare
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestAcquirePermitCard(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		int regionNumber = Integer.parseInt(msg.getParameters().get(0)[0]);
		int permitCard = Integer.parseInt(msg.getParameters().get(0)[1]);
		
		try {
			SpecialActionCommand sac = new SpecialActionCommand(msg.getUserData());
			
			//compro la nuova azione
			sac.acquirePermitCard(regionNumber, permitCard);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(sac.getGamer());
			ud.setupMatch(sac.getMatch());
			ud.updateActionSynoptic(sac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_BUYED, null);
			
		} catch (SpecialActionCommandException| NobiltyPathException | PermitCardsDeckException | PoliticalCardsDeckException | HelpersPoolException | GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SPECIAL_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
		
	}
	
	/*============================================{AZIONI SECONDARIE}====================================================== */
	
	/**
	 * Metodo che processa la richiesta dell'utente di comprare una nuova azione principale
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestBuyNewMainAction(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] exception;
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		try {
			HelpersActionCommand hac = new HelpersActionCommand(msg.getUserData());
			
			//compro la nuova azione
			hac.buyNewMainAction();
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(hac.getGamer());
			ud.setupMatch(hac.getMatch());
			ud.updateActionSynoptic(hac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_NEW_MAIN_ACTION_BUYED, null);
			
		} catch (HelpersActionCommandException | HelpersPoolException | GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		return response ;
		
	}
	
	/**
	 * Metodo per la richiesta di esecuzione della doppia azione da parte del client
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestDoubleAction(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters;
		String[] exception ;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		int regionNumber = Integer.parseInt(msg.getParameters().get(0)[0]);
		
		try {
			HelpersActionCommand hac = new HelpersActionCommand(msg.getUserData());
			
			//eseguo la doppia azione
			hac.doubleAction(regionNumber);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(hac.getGamer());
			ud.setupMatch(hac.getMatch());
			ud.updateActionSynoptic(hac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_DOUBLE_ACTION_DONE, null);
			
		} catch (HelpersActionCommandException | PermitCardsDeckException | GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		}
		
		return response ;
	}
	
	/**
	 * Metodo che processa la richiesta del client di comprare un aiutante
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestBuyHelper(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		int numberOfHelpers = Integer.parseInt(msg.getParameters().get(0)[0]);
		boolean queque = Boolean.parseBoolean(msg.getParameters().get(0)[1]);
		
		try {
			HelpersActionCommand hac = new HelpersActionCommand(msg.getUserData());
			
			//compro gli aiutanti
			hac.buyHelpers(numberOfHelpers, queque);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(hac.getGamer());
			ud.setupMatch(hac.getMatch());
			ud.updateActionSynoptic(hac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOBLE_BUYED, null);
			
		} catch (HelpersActionCommandException | HelpersPoolException| GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
		
		
	}
	
	/*============================================{AZIONI PRIMARIE}====================================================== */
	/**
	 * Metodo che processa la richiesta di costruire un emporio
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestPlaceShop(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception = null;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		char village = msg.getParameters().get(0)[0].charAt(0);
		int permitCard = Integer.parseInt(msg.getParameters().get(0)[1]);
		
		try {
			MainActionCommand mac = new MainActionCommand(msg.getUserData());
			
			//costruisco l'emporio
			mac.placeShop(permitCard, village);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			ud.updateActionSynoptic(mac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_SHOP_PLACED, null);
			
		} catch (MainActionCommandException | GameMapException |VillageException|NobiltyPathException|HelpersPoolException|PoliticalCardsDeckException| GamerException e) { 
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
	}
	
	/**
	 * Metodo che processa la richiesta del giocatore di poter acquistare una carta permesso di costruzione
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestBuyPermitCard(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception;
		
	//	if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		int regionNumber = Integer.parseInt(msg.getParameters().get(0)[0]);
		int permitCardNumber = Integer.parseInt(msg.getParameters().get(0)[1]);
		int dim = msg.getParameters().get(1).length;
		int politicalCardsIndex[] = new int[dim];
		for(int i = 0 ; i < dim; i++) politicalCardsIndex[i] = Integer.parseInt(msg.getParameters().get(1)[i]);
		
		try {
			MainActionCommand mac = new MainActionCommand(msg.getUserData());
			
			//compro la carta
			mac.pickupPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(mac.getGamer());
			ud.setupMatch(mac.getMatch());
			ud.updateActionSynoptic(mac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_PERMIT_CARD_BUYED, null);
			
		} catch (MainActionCommandException |NobiltyPathException|PermitCardsDeckException|HelpersPoolException|PoliticalCardsDeckException| GamerException e) { 
			e.printStackTrace(); 
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
		} catch (UserDataException e) {
			e.printStackTrace();
			parameters = new ArrayList<String[]>();
			exception = new String[1];
			exception[0] = e.getMessage();
			parameters.add(exception);
			response = new ServerMessage(msg.getUserData());
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
		} 
		
		return response ;
		
	}
	
	/**
	 * Metodo che processa la richiesta di cambio nobile
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestChangeNoble(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception ;
		
		//if(this.isActualGamer(msg.getUserData()) == false) return this.serverResponseGamerCannotPlay(msg);
		
		//Estraggo i parametri dal messaggio
		boolean king = Boolean.parseBoolean(msg.getParameters().get(0)[0]);
		int regionNumber = Integer.parseInt(msg.getParameters().get(0)[1]);
		Color noble = null;
		if(msg.getParameters().get(0)[2].equals("BLACK"))noble =Color.BLACK;
		if(msg.getParameters().get(0)[2].equals("CYAN"))noble =Color.CYAN;
		if(msg.getParameters().get(0)[2].equals("PINK"))noble =Color.PINK;
		if(msg.getParameters().get(0)[2].equals("MAGENTA"))noble =Color.MAGENTA;
		if(msg.getParameters().get(0)[2].equals("ORANGE"))noble =Color.ORANGE;
		if(msg.getParameters().get(0)[2].equals("WHITE"))noble =Color.WHITE;
		boolean mainAction = Boolean.parseBoolean(msg.getParameters().get(0)[3]);
		
		if(mainAction == true){
			try {
				MainActionCommand mac = new MainActionCommand(msg.getUserData());
				
				//eseguo lo spostamento del nobile
				mac.changeNoble(king, regionNumber, noble);
				
				//Aggiorno i dati utente
				UserData ud = new UserData(msg.getUsername());
				ud.setupGamer(mac.getGamer());
				ud.setupMatch(mac.getMatch());
				ud.updateActionSynoptic(mac.getActionSynoptic());
				
				response = new ServerMessage(ud);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOBLE_CHANGED, null);
				
			} catch (MainActionCommandException | CouncilException | NoblesPoolException | GamerException e) { 
				e.printStackTrace();
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(msg.getUserData());
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
			} catch (UserDataException e) {
				e.printStackTrace();
				response = new ServerMessage(msg.getUserData());
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			}
		}
		else{
			try {
			HelpersActionCommand hac = new HelpersActionCommand(msg.getUserData());
			
			//eseguo lo spostamento del nobile
			hac.changeNoble(king, regionNumber, noble);
			
			//Aggiorno i dati utente
			UserData ud = new UserData(msg.getUsername());
			ud.setupGamer(hac.getGamer());
			ud.setupMatch(hac.getMatch());
			ud.updateActionSynoptic(hac.getActionSynoptic());
			
			response = new ServerMessage(ud);
			response.addContent(ServerMessageContentType.SERVER_RESPONSE_NOBLE_CHANGED, null);
			
			} catch (MainActionCommandException | HelpersActionCommandException | CouncilException | NoblesPoolException | GamerException e) { 
				e.printStackTrace(); 
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(msg.getUserData());
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_HELPERS_ACTION_FAILURE, parameters);
			} catch (UserDataException e) {
				e.printStackTrace();
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(msg.getUserData());
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			
			} 
		}
		
		return response ;
	}
	
	/**
	 * Metodo che processa la richiesta di mossa del re
	 * @param msg
	 * @return
	 */
	public ServerMessage clientRequestMoveKing(ClientMessage msg){
		ServerMessage response = null;
		ArrayList<String[]> parameters = null;
		String[] exception ;
		
		if(this.isActualGamer(msg.getUserData()) == false) ;//return this.serverResponseGamerCannotPlay(msg);
		else {
			try {
				MainActionCommand mac = new MainActionCommand(msg.getUserData());
				char[] path = new char[msg.getParameters().get(0).length];
				int[] politicalCardsIndex = new int[msg.getParameters().get(1).length];
				
				//Estraggo il path del re
				for(int i = 0; i < path.length; i++) path[i] = msg.getParameters().get(0)[i].charAt(0);
				//Estraggo le carte permesso che l'utente vuole usare
				for(int i = 0; i < politicalCardsIndex.length; i++) politicalCardsIndex[i] = Integer.parseInt(msg.getParameters().get(1)[i]);
				
				//Sposto il re
				mac.moveKing(politicalCardsIndex, path);
				
				//Aggiorno i dati utente
				UserData ud = new UserData(msg.getUsername());
				ud.setupGamer(mac.getGamer());
				ud.setupMatch(mac.getMatch());
				ud.updateActionSynoptic(mac.getActionSynoptic());
				
				response = new ServerMessage(ud);
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_KING_MOVED, null);
				
			} catch (MainActionCommandException | GamerException | KingException | GameMapException | HelpersPoolException | PoliticalCardsDeckException | NobiltyPathException | VillageException e) {
				e.printStackTrace();
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(msg.getUserData());
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_MAIN_ACTION_FAILURE, parameters);
			} catch (UserDataException e) {
				parameters = new ArrayList<String[]>();
				exception = new String[1];
				exception[0] = e.getMessage();
				parameters.add(exception);
				response = new ServerMessage(msg.getUserData());
				response.addContent(ServerMessageContentType.SERVER_RESPONSE_INVALID_MESSAGE, parameters);
			}
			
		}
		
		return response;
	}
	
}
