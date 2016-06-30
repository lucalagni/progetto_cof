package client.controller.exceptions.codes;

/**
 * Enumerazione che contiene i codici di eccezzione generabili dal controller repository
 * 
 * CLIENT_CONTROLLER_UNDEFINED_YET = Il client conbtroller non e' ancora stato definito
 * GAME_DATA_CONTROLLER_UNDEFINED_YET = Il controller per UserData non e' stato ancora definito
 * 
 * @author lucal
 *
 */
public enum ControllerRepositoryExceptionCode {
	CLIENT_CONTROLLER_UNDEFINED_YET(" CLIENT_CONTROLLER_UNDEFINED_YET"),
	GAME_DATA_CONTROLLER_UNDEFINED_YET("GAME_DATA_CONTROLLER_UNDEFINED_YET");
	private String exceptionCode ;
	ControllerRepositoryExceptionCode(String exceptionCode){}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
