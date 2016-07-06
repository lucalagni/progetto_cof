package model.basics.exceptions.codes;

public enum VillageExceptionCode {
	INCONSISTENCE_BEETWEEN_COLOR_AND_PALETTE("VECE.IBCP"),
	GAMER_ALREADY_PRESENT("GAMER ALREADY PRESENT"),
	INVALID_VILLAGE_NAME("VECE.OVNE"),
	INVALID_INPUT_DATA("VECE.IIDA");
	
	private String exceptionCode ;
	
	VillageExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode); }
	
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
