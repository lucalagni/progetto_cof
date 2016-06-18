package model.basics.exceptions.codes;

public enum GameMapExceptionCode {
	INVALID_NUMBER_OF_VILLAGES("GameMapExceptionCode: invalid number of villages"),
	NULL_VILLAGE_PASSED("GameMapExceptionCode: null village passed"),
	NULL_GAMER_PASSED("GameMapExceptionCode: numm gamer passed"),
	VILLAGE_NOT_FOUND("GameMapExceptionCode: village not found"),
	SAME_VILLAGE_PASSED("GameMapExceptionCode: same village passed"),
	COLORS_BONUS_INVALD_SET("GameMapExceptionCode: colors bonus invalid set");
	
	private String exceptionCode ;
	GameMapExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
