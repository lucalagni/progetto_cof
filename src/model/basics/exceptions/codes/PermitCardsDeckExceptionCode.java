package model.basics.exceptions.codes;

public enum PermitCardsDeckExceptionCode {
	FINISHED_AVAILABLE_CARDS("PCDC.FACS"),
	CARD_NOT_FOUND_IN_LIST("PCDC.CNFL"),
	INVALID_INPUT_DATA("PCDC.IIDA");
	
	private String exceptionCode ;
	PermitCardsDeckExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
