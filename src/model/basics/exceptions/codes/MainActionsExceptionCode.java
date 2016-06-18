package model.basics.exceptions.codes;

public enum MainActionsExceptionCode {
	GAMER_CANT_DO_THIS_ACTION("MAEC.GCDA"),
	NULL_BOARD("MAEC.NLBD"),
	CARD_NOT_AVAILABLE("MAEC.CNAE"),
	NULL_NOBLE("MAEC.NLNE"),
	NULL_COUNCIL("MAEC.NLCL"),
	NULL_REGION("MAEC.NLRN"),
	NULL_GAMER("MAEC.NLGR"),
	NULL_VILLAGE("MAEC.NLVE");
	
	private String exceptionCode ;
	
	MainActionsExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
