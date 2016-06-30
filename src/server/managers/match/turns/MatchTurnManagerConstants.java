package server.managers.match.turns;

/**
 * 	Classe che contiene le costanti lato server che tengono conto del delay time
 *  dei vari thread che interessano la gestione della partita.
 *  
 *  MATCH_BUILDER_DELAY = contiene il tempo che il thread di connessione deve aspettare
 *  					  per vedere se si connettono abbastanza utenti per creare un match
 *  
 *  MATCH_GAMER_TURN_DELAY = contiene il tempo che il thread di gestione dei turni di gioco deve
 *  						 attendere prima di poter passare al giocatore successivo.
 *  
 *  MATCH_MARKET_DELAY = contiene il tempo che il thread di gestione del market deve attendere prima
 *  					 di poter nuovamente passare alla modalita' match.
 *  
 *  MATCH_MARKET_SETTER_DELAY = contiene il tempo che il thread di gestione della fase di settaggio del 
 *  							market deve attendere prima di poter avviare la fase di market.
 *  
 *  MATCH_MARKET_GAMER_SETTER_DELAY = contiene il tempo che il thread di gestione della fase di market 
 *  								  relativa ad un singolo giocatore deve attendere prima di non consentirgli 
 *  								  ulteriori settaggi.
 * 
 *  @author Luca Lagni
 *
 */
public final class MatchTurnManagerConstants {
	
	public static final int MATCH_BUILDER_DELAY = 20;
}
