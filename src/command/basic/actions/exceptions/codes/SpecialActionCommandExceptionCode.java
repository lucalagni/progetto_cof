package command.basic.actions.exceptions.codes;

/**
 * Enumerazione che contiene i codici delle eccezioni generabili dalle azioni speciali
 * @author Luca Lagni
 *
 */

public enum SpecialActionCommandExceptionCode {
	CANNOT_PERFORM_THIS_ACTION("CANNOT_PERFORM_THIS_ACTION"),
	INVALID_PERMIT_CARD_POSITION("INVALID_PERMIT_CARD_POSITION"),
	INVALID_REGION_NUMBER("INVALID_REGION_NUMBER"),
	INVALID_VILLAGE_SELECTED("INVALID_VILLAGE_SELECTED"),
	GAMER_NOT_FOUND_IN_THIS_VILLAGE("GAMER_NOT_FOUND_IN_THIS_VILLAGE"),
	CANNOT_ACQUIRE_BONUS_WITH_SHIFTS("CANNOT_ACQUIRE_BONUS_WITH_SHIFTS");
	
	private String exceptionCode;
	SpecialActionCommandExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}

