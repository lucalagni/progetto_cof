package client.command.actions.setter.exceptions.codes;

/**
 * Codici di errore generabili dalla classe setter
 * 
 * @author Luca Lagni
 * 
 * GAMER_CANNOT_SELL_THAT_PERMIT_CARD = Il giocatore non possiede quella carta permesso
 * GAMER_CANNOT_SELL_THAT_POLITICAL_CARD = il giocatore non possiede quella carta politica
 * GAMER_CANNOT_SELL_THAT_QUANTITY_OF_HELPERS = Il giocatore non possiede quella quantità di aiutanti da vendere
 * INVALID_PRICE = Il prezzo non è valido
 *
 */
public enum SetterActionFacadeExceptionCode {
	
	GAMER_CANNOT_SELL_THAT_PERMIT_CARD("GAMER_CANNOT_SELL_THAT_PERMIT_CARD"),
	GAMER_CANNOT_SELL_THAT_POLITICAL_CARD("GAMER_CANNOT_SELL_THAT_POLITICAL_CARD"),
	GAMER_CANNOT_SELL_THAT_QUANTITY_OF_HELPERS("GAMER_CANNOT_SELL_THAT_QUANTITY_OF_HELPERS"),
	INVALID_PRICE("INVALID_PRICE");
	
	private String exceptionCode;
	SetterActionFacadeExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
