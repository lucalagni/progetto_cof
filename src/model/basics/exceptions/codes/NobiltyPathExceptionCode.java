package model.basics.exceptions.codes;

public enum NobiltyPathExceptionCode {
	INVALID_POSITION("NPEC.IDPN");
	
	private String exceptionCode ;
	
	NobiltyPathExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
