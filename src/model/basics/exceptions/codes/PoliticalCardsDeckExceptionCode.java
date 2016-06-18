package model.basics.exceptions.codes;

public enum PoliticalCardsDeckExceptionCode {
	CARD_NOT_FOUND_IN_LIST("PCEC.CNFL"),
	NO_MORE_AVAILABLE_CARDS("PCEC.NMAC"),
	INVALID_INPUT_DATA("PCEC.IIDA");
	
	private String exceptionCode ;
	
	PoliticalCardsDeckExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
