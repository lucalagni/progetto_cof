package commons.messages;

import java.io.Serializable;

/**
 * enumerazione che definisce il tipo di contenuto possibile per  la classe del messaggio spedito dal client
 * 
 * CLIENT_REQUEST_ADDME = il client richiede l'adesione ad un match
 * CLIENT_REQUEST_MATCH = il client richiede l'adesione ad un match
 * CLIENT_REQUEST_GAMER_TURN = Il client richiede di chi e' il turno di gioco
 * CLIENT_REQUEST_ACTION_SYNOPTIC = Il client richiede il sinottico delle azioni per sapere quali azioni puo' fare
 * CLIENT_REQUEST_MARKET_TIME = Il client richiede se si e' passati alla modalita' market
 * CLIENT_REQUEST_MATCH_TIME = Il client richiede se si e' passati alla modalita' match
 * CLIENT_REQUEST_MARKET_SETTER = Il client richiede se si e' passati alla modalita' di creazione del match
 * CLIENT_REQUEST_CAN_I_PLAY = Il client chiede se e' stato creato il match con cui puo' giocare...
 * 
 * ============={ messaggi azioni principali }===============
 * CLIENT_REQUEST_MOVE_KING = Il client richiede di poter spostare il re (azione principale)
 * CLIENT_REQUEST_CHANGE_NOBLE = Il client richiede di cambiare un nobile (azione principale o secondaria)
 * CLIENT_REQUEST_BUY_PERMIT_CARD = il client richiede di comprare una carta permesso di costruzione (azione principale) 
 * CLIENT_REQUEST_TO_PLACE_A_SHOP = il client richiede di piazzare un emporio
 * 
 * ============={ messaggi azioni secondarie }===============
 * CLIENT_REQUEST_BUY_HELPERS = il client richiede di comprare aiutanti
 * CLIENT_REQUEST_BUY_NEW_MAIN_ACTION = il client richiede di comprare una nuova azione principale
 * CLIENT_REQUEST_DOUBLE_ACTION = Il client richiede al server di eseguire la doppia azione
 * 
 * ============={ messaggi per azioni speciali }=============
 * CLIENT_REQUEST_ACQUIRE_PERMIT_CARD = il client richiede al server l'acquisizione di una carta permesso senza pagare
 * CLIENT_REQUEST_ACQUIRE_SINGLE_VILLAGE_BONUS = il client richiede di poter riutilizzare il bonus di un villagio in cui ha costruito in precedenza
 * CLIENT_REQUEST_ACQUIRE_DOUBLE_VILLAGE_BONUS = il client richiede di poter riutilizzare il bonus di un villagio in cui ha costruito in precedenza
 * CLIENT_REQUEST_REUSE_PERMIT_CARD_BONUS = il client richiede di poter riutilizzare il bonus di una carta permesso precedentemente usata
 * 
 * ============={ messaggi per la fase di setter }============
 * CLIENT_REQUEST_CREATE_AGENT = Il client chiede di essere trasformato in un agent
 * CLIENT_REQUEST_MODIFY_AGENT = Il client chiede di essere modificato
 * CLIENT_REQUEST_SKIP_MARKET = Il client richiede di non partecipare alla fase di market
 */

public enum ClientMessageContentType implements Serializable{
	CLIENT_REQUEST_MATCH("CLIENT_REQUEST_MATCH"),             
	CLIENT_REQUEST_GAMER_TURN("CLIENT_REQUEST_GAMER"),
	CLIENT_REQUEST_ACTION_SYNOPTIC("CLIENT_REQUEST_ACTION_SYNOPTIC"),
	CLIENT_REQUEST_MARKET_TIME("CLIENT_REQUEST_MARKET_TIME"),
	CLIENT_REQUEST_MATCH_TIME("CLIENT_REQUEST_MATCH_TIME"),
	CLIENT_REQUEST_MARKET_SETTER(" CLIENT_REQUEST_MARKET_SETTER"),
	CLIENT_REQUEST_ADD_ME("CLIENT_REQUEST_ADD_ME"),
	CLIENT_REQUEST_CAN_I_PLAY("CLIENT_REQUEST_CAN_I_PLAY"),
	
	CLIENT_REQUEST_MOVE_KING("CLIENT_REQUEST_MOVE_KING"),
	CLIENT_REQUEST_CHANGE_NOBLE("CLIENT_REQUEST_CHANGE_NOBLE"),
	CLIENT_REQUEST_BUY_PERMIT_CARD("CLIENT_REQUEST_BUY_PERMIT_CARD"),
	CLIENT_REQUEST_TO_PLACE_A_SHOP("CLIENT_REQUEST_TO_PLACE_A_SHOP"),
	
	CLIENT_REQUEST_BUY_HELPERS("CLIENT_REQUEST_BUY_HELPERS"),
	CLIENT_REQUEST_BUY_NEW_MAIN_ACTION("CLIENT_REQUEST_BUY_NEW_MAIN_ACTION"),
	CLIENT_REQUEST_DOUBLE_ACTION("CLIENT_REQUEST_DOUBLE_ACTION"),
	
	CLIENT_REQUEST_ACQUIRE_PERMIT_CARD("CLIENT_REQUEST_ACQUIRE_PERMIT_CARD"),
	CLIENT_REQUEST_ACQUIRE_SINGLE_VILLAGE_BONUS("CLIENT_REQUEST_ACQUIRE_SINGLE_VILLAGE_BONUS"),
	CLIENT_REQUEST_ACQUIRE_DOUBLE_VILLAGE_BONUS("CLIENT_REQUEST_ACQUIRE_DOUBLE_VILLAGE_BONUS"),
	CLIENT_REQUEST_REUSE_PERMIT_CARD_BONUS("CLIENT_REQUEST_REUSE_PERMIT_CARD_BONUS"),
	
	CLIENT_REQUEST_CREATE_AGENT("CLIENT_REQUEST_CREATE_AGENT"),
	CLIENT_REQUEST_MODIFY_AGENT("CLIENT_REQUEST_MODIFY_AGENT"),
	CLIENT_REQUEST_SKIP_MARKET("CLIENT_REQUEST_SKIP_MARKET");
	
	private String clientMessageContentType;
	ClientMessageContentType(String clientMessageContentType){ this.setClientMessageContentType(clientMessageContentType); }
	private void setClientMessageContentType(String clientMessageContentType){ this.clientMessageContentType = clientMessageContentType; }
	public String getClientMessageContentType(){ return this.clientMessageContentType; }
}
