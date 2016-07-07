package model.basics.exceptions.codes;

/**
 * Enumerazione che contiene le eccezioni generabili dalla board
 * @author lucal
 *
 */
public enum BoardExceptionCode {
	NULL_KING("NULL_KING"),
	NULL_POLITICAL_CARDS_DECK("NULL_POLITICAL_CARDS_DECK"),
	NULL_REGIONS("BECE.NLRS"),
	EMPTY_REGIONS_LIST("BECE.ERLT"),
	NULL_COINS_POOL("BECE.NCPL"),
	NULL_HELPERS_POOL("BECE.NHPL"),
	NULL_MAIN_ACTIONS("BECE.NMAS"),
	NULL_HELPERS_ACTIONS("BECE.NHAS"),
	NULL_NOBLES_POOL("BECE.NNPL"),
	NULL_NOBILTY_PATH("BECE.NNPH");
	
	private String exceptionCode;
	
	BoardExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
