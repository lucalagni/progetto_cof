package commons.data;

import java.io.Serializable;

import model.basics.Gamer;
import model.basics.Match;
import commons.data.ActionSynoptic;
import commons.data.exceptions.UserDataException;
import commons.data.exceptions.codes.UserDataExceptionCode;

/**
 * Classe che contiene tutte le informazioni utili per la gestione dell'interazione
 * dell'utente con l'ambiente di gioco
 * 
 * @author Luca Lagni
 *
 */
public class UserData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Boolean matchUpdated;
	private Boolean gamerUpdated;
	private Boolean synopticUpdated;
	
	private String username;
	private String matchCode;
	private Gamer gamer;
	private Match match;
	private ActionSynoptic actionSynoptic;
	
	public UserData(String username){
		
		this.setUsername(username);
		this.setMatchCode(null);
		this.setGamer(null);
		this.setMatch(null);
		this.setActionSynoptic(null);
		this.setMatchUpdated(false);
		this.setGamerUpdated(false);
		this.setActionSynopticUpdated(false);
		
	}
	
	private void setUsername(String username){ this.username = username; }
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setMatch(Match match){ this.match = match; }
	private void setActionSynoptic(ActionSynoptic synoptic){ this.actionSynoptic = synoptic; }
	private void setMatchUpdated(boolean matchUpdated){ this.matchUpdated = new Boolean(matchUpdated); }
	private void setGamerUpdated(boolean gamerUpdated){ this.gamerUpdated = new Boolean(gamerUpdated); }
	private void setActionSynopticUpdated(boolean synopticUpdated){ this.synopticUpdated = new Boolean(synopticUpdated); }
	
	/**
	 * Metodo che setta i parametri inerenti al match del giocatore
	 * @param match
	 * @throws UserDataException 
	 */
	public void setupMatch(Match match) throws UserDataException{
		if(this.getMatch() != null) throw new UserDataException(UserDataExceptionCode.MATCH_ALREADY_DEFINED.getExceptionCode());
		
		this.setMatchCode(match.getMatchCode());
		this.setMatch(match);
		this.setMatchUpdated(true);
		
	}
	
	/**
	 * Metodo che setta i parametri inerenti al giocatore
	 * @param gamer
	 * @throws UserDataException 
	 */
	public void setupGamer(Gamer gamer) throws UserDataException{
		if(this.getGamer() != null) throw new UserDataException(UserDataExceptionCode.GAMER_ALREADY_DEFINED.getExceptionCode());
		
		if(this.getUsername().equals(gamer.getUsername()))
		{
			this.setGamer(gamer);
			this.setGamerUpdated(true);
		}
		else throw new UserDataException(UserDataExceptionCode.INCONCISTENCE_BEETWEEN_GAMER_AND_USERNAME.getExceptionCode());
	}
	
	/**
	 * Metodo che aggiorna i dati del match del giocatore
	 * @param match
	 * @throws UserDataException 
	 */
	public void updateMatch(Match match) throws UserDataException{
		if(this.getMatchCode().equals(match.getMatchCode())){
			this.setMatch(match);
			this.setMatchUpdated(true);
		}
		else throw new UserDataException(UserDataExceptionCode.INCONCISTENCE_BEETWEEN_MATCH_AND_MATCH_CODE.getExceptionCode());
	}
	
	/**
	 * Metodo che aggiorna i dati del giocatore
	 * @param match
	 * @throws UserDataException 
	 */
	public void updateGamer(Gamer gamer) throws UserDataException{
		//Se i codici corrispondo allora aggiorno il giocatore
		if(this.getGamer().getUsername().equals(gamer.getUsername())){
			this.setGamer(gamer);
			this.setGamerUpdated(true);
		}
		else throw new UserDataException(UserDataExceptionCode.INCONCISTENCE_BEETWEEN_GAMER_AND_USERNAME.getExceptionCode());
	}
	
	/**
	 * Metodo che aggiorna i dati delle azioni eseguibili dal giocatore
	 * @param match
	 * @throws UserDataException 
	 */
	public void updateActionSynoptic(ActionSynoptic actionSynoptic) throws UserDataException{
		//Se i codici corrispondo allora aggiorno il giocatore
		if(this.getGamer().getUsername().equals(actionSynoptic.getUsername())){
			if(this.getMatchCode().equals(actionSynoptic.getMatchCode())){
				this.setActionSynoptic(actionSynoptic);
				this.setActionSynopticUpdated(true);
				return ;
			}
		}
		
		throw new UserDataException(UserDataExceptionCode.ACTION_SYNOPTIC_NOT_FOR_THIS_CASE.getExceptionCode());
	}
	
	public String getUsername(){ return this.username; }
	public String getMatchCode(){ return this.matchCode ; }
	public Gamer getGamer(){ 
		this.setGamerUpdated(false);
		return this.gamer; 
	}
	public Match getMatch(){
		this.setMatchUpdated(false);
		return this.match; 
	}
	public ActionSynoptic getActionSynoptic(){ return this.actionSynoptic; }
	public boolean getMatchUpdated(){ return this.matchUpdated.booleanValue(); }
	public boolean getGamerUpdated(){ return this.gamerUpdated.booleanValue(); }
	public boolean getActionSynopticUpdated(){ return this.synopticUpdated.booleanValue(); }
}
