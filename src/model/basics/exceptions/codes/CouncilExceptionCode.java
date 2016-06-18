package model.basics.exceptions.codes;

public enum CouncilExceptionCode {
	COUNCIL_SETTINGS_NOT_DEFINED("CECS.CSNT"),
	INCONSISTENCE_BETWEEN_NOBLES_AND_PALETTE("CECS.IBNP"),
	INCONSISTENCE_BETWEEN_NEW_NOBLE_AND_PALETTE("CECS.INNP");
	
	private String exceptionCode ;
	
    CouncilExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
    private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
    public String getExceptionCode(){ return this.exceptionCode; }
}
