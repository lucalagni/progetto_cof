package commons.messages;

import java.io.Serializable;

/**
 * Enumerazione che definisce i tipi di contenuti che un server message può implementare
 * 
 * SERVER_RESPONSE_MATCHCODE = Il server ha fornito il matchCode come risposta
 * SERVER_RESPONSE_MATCH = Il server ha fornito il match come risposta
 * SERVER_RESPONSE_GAMER_TURN = il server ha fornito il numero del giocatore come risposta
 * SERVER_RESPONSE_ACTION_SYNOPTIC = Il server ha fornito il sinottico delle azioni ammissibili per il giocatore
 * SERVER_RESPONSE_MARKET_TIME = Il server ha fornito una risposta per quanto riguarda la conoscenza del turno di market
 * SERVER_RESPONSE_MATCH_TIME = Il server ha fornito una risposta per quanto riguarda la conoscenza del turno di match
 * SERVER_RESPONSE_MARKET_SETTER = Il server ha fornito una risposta per quanto riguarda la conoscenza del tempo di settaggio del market
 * SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE = Il server ha risposto che il giocatore e' stato aggiunto in coda
 * SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY = il server risponde che il numero di giocatori non e' piu' suffciente per giocare
 * SEREVR_RESPONSE_MATCHCODE_NOT_AVAILABLE_YET = il server risponde che il match non è ancora pronto per il giocatore
 * SERVER_RESPONSE_MATCH_NOT_FOUND = il server risponde che non vi è alcun match nel repository con quel matchCode
 * SERVER_RESPONSE_INVALID_MESSAGE = il server risponde che il messaggio inviato non è corretto
 * SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW = il server risponde che l'utente non è abilitato a compiere azioni
 * SERVER_RESPONSE_NOT_MARKET_SETTER = Il server comunica al client che non è ancora il momento di decidere cosa vendere
 * SERVER_RESPONSE_NOT_MARKET_TIME = Il server comunica al client che la fase di market non è ancora iniziata
 * SERVER_RESPONSE_NOT_MATCH_TIME = Il server comunica al client che la fase di match non è ancora ripresa
 * SERVER_RESPONSE_USERNAME_NOT_AVAILABLE = Il server comunica che lo username e' gia stato preso
 * SERVER_RESPONSE_GAMER_NOT_IN_MATCH = Il server risponde che il giocatore non fa parte del match
 * SERVER_RESPONSE_GAMER_OFFLINE = Il server ha risposto che il giocatore è offline
 * 
 * =============={ RISPOSTE DEL SERVER ALLE AZIONI ]===================
 * 
 * SEREVR_RESPONSE_KING_MOVED = Il server risponde che il re e' stato mosso
 * SERVER_RESPONSE_SHOP_PLACED = Il server risponde che il negozio e' stato piazzato
 * SERVER_RESPONSE_PERMIT_CARD_BUYED = Il server risponde che la carta permesso e' stata acquistata
 * SERVER_RESPONSE_NOBLE_CHANGED = Il server risponde  che il nobile e' stato cambiato
 * SERVER_RESPONSE_MAIN_ACTION_FAILURE = Il server risponde che non e' stato possibile effettuare l'azione principale
 * 
 * SERVER_RESPONSE_DOUBLE_ACTION_DONE = Il server risponde che la doppia azione e' stata effettuata
 * SERVER_RESPONSE_NOBLE_BUYED = Il server risponde che il nobile e' stato acquistato
 * SERVER_RESPONSE_NEW_MAIN_ACTION_BUYED = Il server risponde che la nuova azione e' stata comprata
 * SEREVR_RESPONSE_HELPERS_ACTION_FAILURE = Il server risponde che non e' stao possibile eseguire l'azione secondaria
 * 
 * SERVER_RESPONSE_PERMIT_CARD_ACQUIRED =Il server risponde che e' stata acquisita la carta permesso senza pagarne il prezzo
 * SERVER_RESPONSE_PERMIT_BONUS_REUSED = Il server comunica che e' stato riutilizzato un bonus
 * SERVER_RESPONSE_SINGLE_VILLAGE_BONUS_ACQUIRED = Il server comunica che e' stato acquisito il bonus di un singolo villaggio
 * SERVER_RESPONSE_DOUBLE_VILLAGE_BONUS_ACQUIRED = Il server comunica che e' stato acquisito il bonus da due villaggi
 * SEREVR_RESPONSE_SPECIAL_ACTION_FAILURE = Il server comunica che l'azione speciale e' fallita
 * 
 * ================{ RISPOSTE DEL SERVER AL SETTER }=================
 * 
 * SERVER_RESPONSE_AGENT_SETTED = il server risponde che l'agente e' stato settato
 * SERVER_RESPONSE_AGENT_SET_FAILURE = Il server segnala problemi nella fase di settaggio del market
 * 
 * ================{ RISPOSTE DEL SERVER AL MARKET }=================
 *SERVER_RESPONSE_HELPERS_ITEM_BUYED = il server risponde al client che il lotto di aiutanti è stato acquistato
 *SERVER_RESPONSE_HELPERS_ITEM_TRANSACTION_FAILURE = il server risponde che non è stato possibile comprare il lotto di aiutanti
 *SERVER_RESPONSE_PERMIT_CARD_ITEM_BUYED = Il server risponde al client che la carta permesso è stata acquistata
 *SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE = il server risponde che non è stato possibile comprare la carta permesso
 *SERVER_RESPONSE_POLITICAL_CARD_ITEM_BUYED = il server risponde al client che la carta politica è stata acquistata
 *SERVER_RESPONSE_POLITICAL_CARD_ITEM_TRANSACTION_FAILURE =  il server risponde che non è stato possibile comprare la carta politica
 */

public enum ServerMessageContentType implements Serializable{
	SERVER_RESPONSE_MATCH("SERVER_RESPONSE_MATCH"),
	SERVER_RESPONSE_MATCH_CODE("SERVER_RESPONSE_MATCH_CODE"),
	SERVER_RESPONSE_GAMER_TURN("SERVER_RESPONSE_GAMER_TURN"),
	SERVER_RESPONSE_ACTION_SYNOPTIC("SERVER_RESPONSE_ACTION_SYNOPTIC"),
	SERVER_RESPONSE_MARKET_TIME("SERVER_RESPONSE_MARKET_TIME"),
	SERVER_RESPONSE_MATCH_TIME("SERVER_RESPONSE_MATCH_TIME"),
	SERVER_RESPONSE_MARKET_SETTER("SERVER_RESPONSE_MARKET_SETTER"),
	SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY("SERVER_RESPONSE_TOO_FEAW_GAMERS_TO_PLAY"),
	SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE("SERVER_RESPONSE_GAMER_ADDED_TO_QUEQUE"),
	SERVER_RESPONSE_MATCH_NOT_AVAILABLE_YET("SERVER_RESPONSE_MATCH_NOT_AVAILABLE_YET"),
	SERVER_RESPONSE_MATCH_NOT_FOUND("SERVER_RESPONSE_MATCH_NOT_FOUND"),
	SERVER_RESPONSE_INVALID_MESSAGE("SERVER_RESPONSE_INVALID_MESSAGE"),
	SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW("SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW"),
	SERVER_RESPONSE_NOT_MARKET_SETTER("SERVER_RESPONSE_NOT_MARKET_SETTER"),
	SERVER_RESPONSE_NOT_MARKET_TIME("SERVER_RESPONSE_NOT_MARKET_TIME"),
	SERVER_RESPONSE_NOT_MATCH_TIME("SERVER_RESPONSE_NOT_MATCH_TIME"),
	SERVER_RESPONSE_USERNAME_NOT_AVAILABLE("SERVER_RESPONSE_USERNAME_NOT_AVAILABLE"),
	SERVER_RESPONSE_GAMER_NOT_IN_MATCH("SERVER_RESPONSE_GAMER_NOT_IN_MATCH"),
	SERVER_RESPONSE_GAMER_OFFLINE("SERVER_RESPONSE_GAMER_OFFLINE"),
	
	SERVER_RESPONSE_KING_MOVED("SEREVR_RESPONSE_KING_MOVED"),
	SERVER_RESPONSE_SHOP_PLACED("SERVER_RESPONSE_SHOP_PLACED"),
	SERVER_RESPONSE_PERMIT_CARD_BUYED("SERVER_RESPONSE_PERMIT_CARD_BUYED"),
    SERVER_RESPONSE_NOBLE_CHANGED("SERVER_RESPONSE_NOBLE_CHANGED"),
    SERVER_RESPONSE_MAIN_ACTION_FAILURE("SERVER_RESPONSE_MAIN_ACTION_FAILURE"),

	SERVER_RESPONSE_DOUBLE_ACTION_DONE("SERVER_RESPONSE_DOUBLE_ACTION_DONE"),
	SERVER_RESPONSE_NOBLE_BUYED("SERVER_RESPONSE_NOBLE_BUYED"),
	SERVER_RESPONSE_NEW_MAIN_ACTION_BUYED("SERVER_RESPONSE_NEW_MAIN_ACTION_BUYED"),
	SERVER_RESPONSE_HELPERS_ACTION_FAILURE("SERVER_RESPONSE_HELPERS_ACTION_FAILURE"),
	SERVER_RESPONSE_PERMIT_CARD_ACQUIRED("SERVER_RESPONSE_PERMIT_CARD_ACQUIRED"),
	SERVER_RESPONSE_PERMIT_BONUS_REUSED("SSERVER_RESPONSE_PERMIT_BONUS_REUSED"),
	SERVER_RESPONSE_SINGLE_VILLAGE_BONUS_ACQUIRED("SERVER_RESPONSE_SINGLE_VILLAGE_BONUS_ACQUIRED"),
	SERVER_RESPONSE_DOUBLE_VILLAGE_BONUS_ACQUIRED("SERVER_RESPONSE_DOUBLE_VILLAGE_BONUS_ACQUIRED"),
	SERVER_RESPONSE_SPECIAL_ACTION_FAILURE("SERVER_RESPONSE_SPECIAL_ACTION_FAILURE"),
	
	SERVER_RESPONSE_AGENT_SETTED("SERVER_RESPONSE_AGENT_SETTED"),
	SERVER_RESPONSE_AGENT_SET_FAILURE("SERVER_RESPONSE_AGENT_SET_FAILURE"),
	
	SERVER_RESPONSE_HELPERS_ITEM_BUYED("SERVER_RESPONSE_HELPERS_ITEM_BUYED"),
	SERVER_RESPONSE_HELPERS_ITEM_TRANSACTION_FAILURE("SERVER_RESPONSE_HELPERS_ITEM_TRANSACTION_FAILURE"),
	SERVER_RESPONSE_PERMIT_CARD_ITEM_BUYED("SERVER_RESPONSE_PERMIT_CARD_ITEM_BUYED"),
	SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE("SERVER_RESPONSE_PERMIT_CARD_ITEM_TRANSACTION_FAILURE"),
	SERVER_RESPONSE_POLITICAL_CARD_ITEM_BUYED("SERVER_RESPONSE_POLITICAL_CARD_ITEM_BUYED"),
	SERVER_RESPONSE_POLITICAL_CARD_ITEM_TRANSACTION_FAILURE("SERVER_RESPONSE_POLITICAL_CARD_ITEM_TRANSACTION_FAILURE");
	
	private String serverMessageContentType;
	ServerMessageContentType(String serverMessageContentType){this.setServerMessageContentType(serverMessageContentType);}
	private void setServerMessageContentType(String serverMessageContentType){ this.serverMessageContentType = serverMessageContentType; }
	public String getServerMessageContentType(){ return this.serverMessageContentType; }
}
