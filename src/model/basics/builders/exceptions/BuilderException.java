package model.basics.builders.exceptions;


public class BuilderException extends IllegalStateException{
	private static final long serialVersionUID = 1L;

	public BuilderException(String exceptionCode){ super(exceptionCode); }
}
