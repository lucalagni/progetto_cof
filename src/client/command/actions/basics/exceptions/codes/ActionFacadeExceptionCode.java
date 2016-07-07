package client.command.actions.basics.exceptions.codes;

/**
 * Enumerazione che contiene tutte le costanti con i codici di eccezione generabili
 * dall'esecuzione delle mosse attraverso facade
 * 
 * GAMER_CANNOT_PLAY_NOW = non è il turno del giocatore
 * GAMER_HAS_NO_MORE_MAIN_ACTIONS = il giocatore non possiede più azioni pricipali
 * GAMER_HAS_NO_MORE_HELPERS_ACTIONS = Il giocatore non possiede più azioni secondarie
 * GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE = il giocatore non ha più mosse speciali di un determinato tipo
 * UNSETTED_USER_DATA = Gli UserData non sono stati settati
 * UNSETTED_GAME_MODE = La modalita' di communicazione non e' stata settata
 * CORRUPTED_USER_DATA = I dati tornati dal server non sono corretti
 * 
 * @author Luca Lagni
 *
 */
public enum ActionFacadeExceptionCode {
	GAMER_CANNOT_PLAY_NOW("GAMER_CANNOT_PLAY_NOW"),
	GAMER_HAS_NO_MORE_MAIN_ACTIONS("GAMER_HAS_NO_MORE_MAIN_ACTIONS"),
	GAMER_HAS_NO_MORE_HELPERS_ACTIONS("GAMER_HAS_NO_MORE_HELPERS_ACTIONS"),
	UNSETTED_GAME_MODE("UNSETTED_GAME_MODE"),
	GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE("GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE"),
	UNSETTED_USER_DATA("UNSETTED_USER_DATA"),
	CORRUPTED_USER_DATA("CORRUPTED_USER_DATA");

	private String exceptionCode ;
	ActionFacadeExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
