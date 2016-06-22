package client.exception.codes;

public enum MatchRenderExceptionCode {
	MATCH_NOT_FOUND("MatchRenderException: match not found"),
	GAMER_NOT_IN_MATCH("MatchRenderException: gamer not in match"),
	REGION_NOT_VALID("MatchRenderException: region not valid"),
	VILLAGE_NOT_VALID("MatchRenderException: village not valid");
	
	private String exceptionCode;
	MatchRenderExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
