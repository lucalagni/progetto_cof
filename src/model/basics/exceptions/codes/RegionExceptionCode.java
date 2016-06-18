package model.basics.exceptions.codes;

public enum RegionExceptionCode {
	INVALID_INPUT_DATA("RECE.IIDA");
	
	private String exceptionCode ;
	RegionExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
