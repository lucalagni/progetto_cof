package model.basics.exceptions.codes;

public enum GamerExceptionCode {
	
	PERMIT_CARD_NOT_FOUND("GECE.PCNF"),
	EMPTY_PERMIT_CARD_SET("GECE.EPCS"),
	TOO_MANY_COINS("GAMER_HAS_TOO_MANY_COINS"),
	TOO_FEAW_COINS("GECE.TFCS"),
	TOO_MANY_HELPERS("GECE.TMHS"),
	TOO_FEAW_HELPERS("GECE.TFHS"),
	ENDED_NOBILTY_PATH("GECE.ENPH"),
	ENDED_GAMER_SHOPS("GECE.EGSS"),
	POLITICAL_CARD_NOT_FOUND("GECE.OCNF"),
	INVALID_INPUT_DATA("GECE.IIDA");
	
	private String exceptionCode ;
	
	GamerExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
