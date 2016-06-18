package model.basics.exceptions.codes;

public enum CoinsPoolExceptionCodes 
{
	SUPERIOR_COINS_LIMIT_EXCEDED("CPEC.SCLE"),
	INFERIOR_COINS_LIMIT_EXCEDED("CPEC.ICLE"),
	INSTANCE_ALREADY_DEFINED("CPEC.IADD"),
	INVALID_INPUT_DATA("CPEC.IIDA"),
	NULL_COINS_POOL_SETTINGS("CPEC.NCPS"),
	GAMERS_COINS_EXCEED_TOTAL_COINS("CPEC.GCET"),
	COINS_POOL_NOT_RESIZABLE("CPEC.CPNR");
	
	public String exceptionCode ;
	
	CoinsPoolExceptionCodes(String exceptionCode)
	{
		this.setExceptionCode(exceptionCode);
	}
	
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	
	public String getExceptionCode(){ return this.exceptionCode; }
}
