package model.basics.exceptions.codes;

public enum BonusExceptionCodes 
{
	INVALID_INPUT_DATA("BECS.IIDA"),
	TOO_FEW_COINS("BECS.TFCS"),
	TOO_MANY_COINS("BECS.TMCS");
	
	private String exceptionCode;
	
	BonusExceptionCodes(String exceptionCodes){ this.setExceptionCodes(exceptionCodes); }
	private void setExceptionCodes(String exceptionCodes){ this.exceptionCode = exceptionCodes; }
	public String getExceptionCodes(){ return this.exceptionCode; }
}
