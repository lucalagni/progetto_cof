package model.basics.exceptions.codes;

public enum PoliticalCardExceptionCode {
	INCONSISTENCE_BETWEEN_NEW_COLOR_AND_PALETTE("PCEC.IBCP"),
	INCONSISTENCE_BETWEEN_JOLLY_AND_COLOR("PCEC.IBJC");
	
	private String exceptionCode;
	
	PoliticalCardExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
