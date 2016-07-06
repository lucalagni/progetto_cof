package client.command.actions.market.exceptions.codes;

/**
 * Enumerazione che contiene i codici di errore generabili dalle azioni del market
 * 
 * @author Luca Lagni
 * 
 * INVALID_SELLER_INDEX = l'indice del venditore non esiste
 * INVALID_ITEM_INDEX = l'indice dell'oggetto non è valido
 * GAMER_HAS_TOO_FEAW_COINS = Il gamer non ha abbastanza soldi
 *
 */
public enum MarketActionFacadeExceptionCode {
	INVALID_SELLER_INDEX("INVALID_SELLER_INDEX"),
	INVALID_ITEM_INDEX("INVALID_ITEM_INDEX"),
	GAMER_HAS_TOO_FEAW_COINS("GAMER_HAS_TOO_FEAW_COINS");
	
	private String exceptionCode;
	MarketActionFacadeExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
