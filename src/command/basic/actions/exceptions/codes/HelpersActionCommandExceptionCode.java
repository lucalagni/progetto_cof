package command.basic.actions.exceptions.codes;

/**
 * Enumerazione che contiene i codici per le eccezioni generabili dalle azioni secondarie
 * @author Luca Lagni
 *
 */

public enum HelpersActionCommandExceptionCode {
	TOO_FEAW_HELPERS("TOO_FEAW_HELPERS"),
	GAMER_HAS_TOO_FEAW_COINS("GAMER_HAS_TOO_FEAW_COINS"),
	INVALID_NOBLE_COLOR("INVALID_NOBLE_COLOR"),
	INVALID_REGION_NUMBER("INVALID_REGION_NUMBER");
	
	private String exceptionCode;
	HelpersActionCommandExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode;}
	public String getExceptionCode(){ return this.exceptionCode; }
}
