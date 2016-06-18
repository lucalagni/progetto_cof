package model.basics.exceptions.codes;

public enum NoblesPoolExceptionCode {
	NO_MORE_BLACK_NOBLES_AVAILABLE("NoblesPoolException: no more black nobles available"),
	NO_MORE_WHITE_NOBLES_AVAILABLE("NoblesPoolException: no more white nobles available"),
	NO_MORE_CYAN_NOBLES_AVAILABLE("NoblesPoolException: no more cyan nobles available"),
	NO_MORE_PINK_NOBLES_AVAILABLE("NoblesPoolException: no more pink nobles available"),
	NO_MORE_MAGENTA_NOBLES_AVAILABLE("NoblesPoolException: no more magenta nobles available"),
	NO_MORE_ORANGE_NOBLES_AVAILABLE("NoblesPoolException: no more orange nobles available"),
	FULL_BLACK_NOBLES_BUFFER("NoblesPoolException: full black nobles buffer"),
	FULL_WHITE_NOBLES_BUFFER("NoblesPoolException: full white nobles buffer"),
	FULL_CYAN_NOBLES_BUFFER("NoblesPoolException: full cyan nobles buffer"),
	FULL_PINK_NOBLES_BUFFER("NoblesPoolException: full pink nobles buffer"),
	FULL_MAGENTA_NOBLES_BUFFER("NoblesPoolException: full magenta nobles buffer"),
	FULL_ORANGE_NOBLES_BUFFER("NoblesPoolException: full orange nobles buffer"),
	INVALID_INPUT_DATA("NoblesPoolException: invalid input data");
	
	private String exceptionCode;
	NoblesPoolExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
