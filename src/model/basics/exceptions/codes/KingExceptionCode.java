package model.basics.exceptions.codes;

public enum KingExceptionCode {
	PATH_NOT_AVAILABLE("KECE.PNAE"),
	INVALID_INPUT_DATA("KECE.IIDA"),
	INVALID_BONUS_NUMBER("KingExceptionCode: invalid bonus number"),
	NULL_BONUS("KingExceptionCode: null bonus");
	
	private String exceptionCode;
	
	KingExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
