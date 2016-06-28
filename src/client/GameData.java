package client;

import command.basic.actions.ActionSynoptic;

import model.basics.Gamer;
import model.basics.Match;

/**
 * Classe che contiene i dati generali relativi al match a cui è collegato il giocatore
 * @author lucal
 *
 */
public class GameData {
	private String username ;
	private String matchCode ;
	private Gamer gamer ;
	private Match match;
	private ActionSynoptic actionSynoptic;
	
	public GameData(String username){
		this.setUsername(username);
		this.setMatchCode(null);
		this.setGamer(null);
		this.setMatch(null);
		this.setActionSynoptic(null);
	}
	
	private void setUsername(String username){ this.username = username; }
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setMatch(Match match){ this.match = match; }
	private void setActionSynoptic(ActionSynoptic actionSynoptic){ this.actionSynoptic = actionSynoptic; }
	
	/**
	 * Metodo che viene richiamato quando all'utente viene assegnato un matchCode
	 * @param matchCode
	 */
	public void defineMatchCode(String matchCode){
		if(this.matchCode == null) this.setMatchCode(matchCode);
	}
	
	/**
	 * Metodo che viene richiamato quando la partita è in corso per aggiornare i dati del match
	 * @param match
	 */
	public void updateMatch(Match match){
		this.setMatch(match);
		this.updateGamer();
	}
	
	/**
	 * Metodo che viene invocato quando la partita è in corso per aggiornare il giocatore
	 */
	private void updateGamer(){
		for(Gamer g : this.getMatch().getGamers()){
			if(g.getUsername().equals(this.getUsername())){
				this.setGamer(g);
				break;
			}
		}
	}
	
	/**
	 * Metodo che viene invocato quando la partita è in corso per aggiornare le azioni eseguibili dal giocatore
	 * @param actionSynoptic
	 */
	public void updateActionSynoptic(ActionSynoptic actionSynoptic){
		int position = -1 ;
		
		for(int i = 0; i < this.getMatch().getGamers().size(); i++){
			if(this.getMatch().getGamers().get(i).getUsername().equals(this.getUsername())){
				position = i;
				break;
			}
		}
		
		if(position == this.getMatch().getActualGamer()) this.setActionSynoptic(actionSynoptic);
		else this.setActionSynoptic(null);
	}
	
	public String getUsername(){ return this.username; }
	public String getMatchCode(){ return this.matchCode; }
	public Gamer getGamer(){ return this.gamer; }
	public Match getMatch(){ return this.match; }
	public ActionSynoptic getActionSynoptic(){ return this.actionSynoptic; }
}
