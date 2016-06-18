package model.basics.exceptions.codes;

public enum PermitCardExceptionCode {
	INVALID_VILLAGE("permit card: invalid village"),
	INVALID_BONUS("permit card: invalid bonus");
	
	private String exceptionCode ;
	PermitCardExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }

}
