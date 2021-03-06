package server.command.basic.actions.exceptions.codes;

/**
 * Enumerazione che contiene i codici per le eccezioni generabili dalle azioni primarie
 * @author Luca Lagni
 *
 */

public enum MainActionCommandExceptionCode {
	GAMER_HAS_TOO_FEAW_COINS("GAMER_HAS_TOO_FEAW_COINS"),
	TOO_FEAW_HELPERS_SELECTED("TOO_FEAW_HELPERS_SELECTED"),
	TOO_FEAW_HELPERS_AVAILABLES("TOO_FEAW_HELPERS_AVAILABLES"),
	VILLAGE_NOT_FOUND("VILLAGE_NOT_FOUND"),
	INSISTENCE_BETWEEN_PERMIT_CARD_AND_VILLAGE("INSISTENCE_BETWEEN_PERMIT_CARD_AND_VILLAGE"),
	GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD("GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD"),
	GAMER_SHOP_ALREADY_PLACED_IN_THAT_VILLAGE("GAMER_SHOP_ALREADY_PLACED_IN_THAT_VILLAGE"),
	GAMER_HAS_NO_MORE_AVAILABLE_SHOPS("GAMER_HAS_NO_MORE_AVAILABLE_SHOPS"),
	INVALID_REGION_NUMBER("INVALID_REGION_NUMBER"),
	INVALID_PERMIT_CARD_NUMBER("INVALID_PERMIT_CARD_NUMBER"),
	INVALID_POLITICAL_CARDS_NUMBER("INVALID_POLITICAL_CARDS_NUMBER"),
	INCONCISTENCE_BEETWEEN_POLITICAL_CARDS_AND_NOBLES("INCONCISTENCE_BEETWEEN_POLITICAL_CARDS_AND_NOBLES"),
	INVALID_VILLAGE_PATH("INVALID_VILLAGE_PATH"),
	INVALID_NOBLE_COLOR("INVALID_NOBLE_COLOR"),
	OPERATION_NOT_VALID("OPERATION_NOT_VALID"),
	CANNOT_DO_THIS_ACTION("CANNOT_DO_THIS_ACTION");
	
	private String exceptionCode;
	MainActionCommandExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
