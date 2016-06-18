package model.basics.exceptions;

public class CouncilException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CouncilException(String exceptionCode){ super(exceptionCode); }
}
