package communication.socket.messages;

import java.io.Serializable;

/**
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
	SEREVR_RESPONSE_MATCHCODE_NOT_AVAILABLE_YET("SEREVR_RESPONSE_MATCHCODE_NOT_AVAILABLE_YET"),
	SERVER_RESPONSE_MATCH_NOT_FOUND("SERVER_RESPONSE_MATCH_NOT_FOUND"),
	SERVER_RESPONSE_INVALID_MESSAGE("SERVER_RESPONSE_INVALID_MESSAGE"),
	SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW("SERVER_RESPONSE_GAMER_CANNOT_PLAY_NOW"),
	SERVER_RESPONSE_NOT_MARKET_SETTER("SERVER_RESPONSE_NOT_MARKET_SETTER"),
	SERVER_RESPONSE_NOT_MARKET_TIME("SERVER_RESPONSE_NOT_MARKET_TIME"),
	SERVER_RESPONSE_NOT_MATCH_TIME("SERVER_RESPONSE_NOT_MATCH_TIME");
	
	private String serverMessageContentType;
	ServerMessageContentType(String serverMessageContentType){this.setServerMessageContentType(serverMessageContentType);}
	private void setServerMessageContentType(String serverMessageContentType){ this.serverMessageContentType = serverMessageContentType; }
	public String getServerMessageContentType(){ return this.serverMessageContentType; }
}
