package model.basics.exceptions.codes;

public enum MatchExceptionCode {
	
	SETTINGS_NULL("MECE.SSNL"),
	NULL_TITLE("MECE.NLTE"),
	NULL_GAMER("MECE.NLGR"),
	NULL_BOARD("MECE.NLBD"),
	NULL_GAMERS("MECE.NLGS"),
	NULL_SETTINGS("MECE.NLSS"),
	TOO_MANY_GAMERS("MECE.TMGS"),
	TOO_FEAW_GAMERS_TO_PLAY("too feaw gamers to play");
	
	private String exceptionCode;
	
	MatchExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
