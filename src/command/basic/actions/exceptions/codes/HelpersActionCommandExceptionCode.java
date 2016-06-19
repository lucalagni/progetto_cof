package command.basic.actions.exceptions.codes;

public enum HelpersActionCommandExceptionCode {
	TOO_FEAW_HELPERS("HelpersActionCommandException: too feaw helpers"),
	GAMER_HAS_TOO_FEAW_COINS("HelpersActionCommandException: gamer has too feaw coins"),
	INVALID_NOBLE_COLOR("HelpersActionCommandException: invalid noble color"),
	INVALID_REGION_NUMBER("HelpersActionCommandException: invalid region number");
	
	private String exceptionCode;
	HelpersActionCommandExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode;}
	public String getExceptionCode(){ return this.exceptionCode; }
}
