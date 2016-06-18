package model.basics.exceptions.codes;

public enum HelpersPoolExceptionCode {
	SUPERIOR_HELPERS_LIMIT_EXCEDED("HPEC.SCLE"),
	INFERIOR_HELPERS_LIMIT_EXCEDED("HPEC.ICLE"),
	INSTANCE_ALREADY_DEFINED("HPEC.IADD"),
	INVALID_INPUT_DATA("HPEC.IIDA");
	
	public String exceptionCode ;
	
	HelpersPoolExceptionCode(String exceptionCode){
		this.setExceptionCode(exceptionCode);
	}
	
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	
	public String getExceptionCode(){ return this.exceptionCode; }
}
