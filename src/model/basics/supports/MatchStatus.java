package model.basics.supports;

public enum MatchStatus {
	ACTIVE("MS.ACTIVE"),
	READY("MS.READY"),
	IDLE("MS.IDLE"),
	FREEZED("MS.FREEZED"),
	TERMINATED("MS.TERMINATED"),
	FINISCHED("MS.FINISCHED"),
	ON_WORK("MS.ON_WORK");
	
	private String statusCode;
	
	MatchStatus(String statusCode){ this.setStatusCode(statusCode); }
	
	private void setStatusCode(String statusCode){ this.statusCode = statusCode; }
	public String getStatusCode(){ return this.statusCode; }
}
