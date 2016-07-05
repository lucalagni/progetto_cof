package model.basics.supports;

import java.io.Serializable;

/**
 * Enumerazione che indica in che fase si trova il match
 * MATCH_PHASE = Indica la modalità gioco standard
 * SETTER_PHASE = indica la modalità di settaggio degli elementi per il market
 * MARKET_PHASE = indica la modalità di compravendita market
 * @author lucal
 *
 */
public enum MatchPhase implements Serializable {
	MATCH_PHASE("MATCH_PHASE"),
	SETTER_PHASE("SETTER_PHASE"),
	MARKET_PHASE("MARKET_PHASE");
	
	private String matchPhase;
	MatchPhase(String matchPhase){ this.setMatchPhase(matchPhase);}
	private void setMatchPhase(String matchPhase){ this.matchPhase = matchPhase; }
	public String getMatchPhase(){ return this.matchPhase; }
}
