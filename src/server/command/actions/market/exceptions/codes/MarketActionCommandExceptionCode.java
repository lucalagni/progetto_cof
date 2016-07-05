package server.command.actions.market.exceptions.codes;

/**
 * Enumerazione con i codice di errore generabili dalle azioni del market
 * 
 * @author Luca Lagni
 *
 */
public enum MarketActionCommandExceptionCode {
	GAMER_HAS_TOO_FEAW_COINS("GAMER_HAS_TOO_FEAW_COINS"),
	ITEM_NOT_AVAILABLE("ITEM_NOT_AVAILABLE"),
	INVALID_SELLER_INDEX("INVALID_SELLER_INDEX"),
	INVALID_ITEM_INDEX("INVALID_ITEM_INDEX"),
	AGENT_NOT_FOUND("AGENT_NOT_FOUND"),
	GAMER_NOT_FOUND("GAMER_NOT_FOUND"),
	GAMER_HAS_TOO_MANY_HELPERS("GAMER_HAS_TOO_MANY_HELPERS"),
	NULL_MATCH("NULL_MATCH");
	
	private String exceptionCode;
	MarketActionCommandExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
