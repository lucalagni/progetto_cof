package commons.data.exceptions.codes;

/**
 * Enumerazione che contiene i codici di eccezione generabili dalla user data
 * 
 * INCONCISTENCE_BEETWEEN_MATCH_AND_MATCH_CODE = Il matchcode e il match hanno codici differenti
 * GAMER_NOT_IN_THIS_MATCH = Il giocatore non compare in tale match;
 * ACTION_SYNOPTIC_NOT_FOR_THIS_CASE = L'actionSynoptic non e' associato a questo match o a questo utente.
 * MATCH_ALREADY_DEFINED = Il match e' gia stato definito
 * GAMER_ALREADY_DEFINED = il giocatore e' gia' stato definito
 * INCONCISTENCE_BEETWEEN_GAMER_AND_USERNAME = Il gamer passato non possiede lo stesso username
 * 
 * @author Luca Lagni
 *
 */
public enum UserDataExceptionCode {
	
	INCONCISTENCE_BEETWEEN_MATCH_AND_MATCH_CODE("INCONCISTENCE_BEETWEEN_MATCH_AND_MATCH_CODE"),
	GAMER_NOT_IN_THIS_MATCH("GAMER_NOT_IN_THIS_MATCH"),
	ACTION_SYNOPTIC_NOT_FOR_THIS_CASE(" ACTION_SYNOPTIC_NOT_FOR_THIS_CASE"),
	MATCH_ALREADY_DEFINED("MATCH_ALREADY_DEFINED"),
	GAMER_ALREADY_DEFINED("GAMER_ALREADY_DEFINED"),
	INCONCISTENCE_BEETWEEN_GAMER_AND_USERNAME("INCONCISTENCE_BEETWEEN_GAMER_AND_USERNAME");
	
	private String exceptionCode ;
	UserDataExceptionCode(String exceptionCode){this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
