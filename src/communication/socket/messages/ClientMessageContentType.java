package communication.socket.messages;

import java.io.Serializable;

/**
 * enumerazione che definisce il tipo di contenuto possibile per  la classe del messaggio spedito dal client
 * 
 * CLIENT_REQUEST_ADD_ME = il client richiede l'adesione ad un match
 * CLIENT_REQUEST_MATCH = il client richiede l'adesione ad un match
 * CLIENT_REQUEST_GAMER_TURN = Il client richiede di chi e' il turno di gioco
 * CLIENT_REQUEST_ACTION_SYNOPTIC = Il client richiede il sinottico delle azioni per sapere quali azioni puo' fare
 * CLIENT_REQUEST_MARKET_TIME = Il client richiede se si e' passati alla modalita' market
 * CLIENT_REQUEST_MATCH_TIME = Il client richiede se si e' passati alla modalita' match
 * CLIENT_REQUEST_MARKET_SETTER = Il client richiede se si e' passati alla modalita' di creazione del match
 * CLIENT_REQUEST_CAN_I_PLAY = Il client chiede se e' stato creato il match con cui puo' giocare...
 */

public enum ClientMessageContentType implements Serializable{
	CLIENT_REQUEST_MATCH("CLIENT_REQUEST_MATCH"),             
	CLIENT_REQUEST_GAMER_TURN("CLIENT_REQUEST_GAMER"),
	CLIENT_REQUEST_ACTION_SYNOPTIC("CLIENT_REQUEST_ACTION_SYNOPTIC"),
	CLIENT_REQUEST_MARKET_TIME("CLIENT_REQUEST_MARKET_TIME"),
	CLIENT_REQUEST_MATCH_TIME("CLIENT_REQUEST_MATCH_TIME"),
	CLIENT_REQUEST_MARKET_SETTER(" CLIENT_REQUEST_MARKET_SETTER"),
	CLIENT_REQUEST_ADD_ME("CLIENT_REQUEST_ADD_ME"),
	CLIENT_REQUEST_CAN_I_PLAY("CLIENT_REQUEST_CAN_I_PLAY");
	
	private String clientMessageContentType;
	ClientMessageContentType(String clientMessageContentType){ this.setClientMessageContentType(clientMessageContentType); }
	private void setClientMessageContentType(String clientMessageContentType){ this.clientMessageContentType = clientMessageContentType; }
	public String getClientMessageContentType(){ return this.clientMessageContentType; }
}
