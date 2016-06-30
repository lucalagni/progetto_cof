package client.command.updates.exceptions.codes;

/**
 * Enumerazione che contiene i codici di errori lanciabili
 * dall'update facade
 * 
 * NULL_MATCH_CODE = Non e' ancora stato definito un valore di match code su cui fare 
 * 					 aggiornamenti.
 * 
 * NULL_MATCH = Non mi e' stato inviato alcun match
 * NULL_USERNAME = il giocatore non e' ancora stato definito
 * 
 * @author Luca Lagni
 *
 */

public enum GameUpdatesFacadeExceptionCode {
	NULL_MATCH_CODE("NULL_MATCH_CODE"),
	NULL_MATCH("NULL_MATCH"),
	NULL_USERNAME("NULL_USERNAME");
	
	private String exceptionCode ;
	GameUpdatesFacadeExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode  = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
