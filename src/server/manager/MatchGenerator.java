package server.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import model.basics.Board;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.PoliticalCard;
import model.basics.builders.MatchBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.GamerConstants;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.supports.MatchStatus;

public class MatchGenerator {
	private static int actualCode = 1;
	private static String actualTitle = "match_" + actualCode;
	private HashSet<Gamer> gamers;
	//private String[] gamersName;
	
	public MatchGenerator(){
		this.gamers = new HashSet<Gamer>();
	}
	
	public void addGamer(String username){
		//Crea nuovo giocatore...
		//this.gamers.add(new GamerGenerator())
	}
	
	public Match createMatch() throws PoliticalCardsDeckException, BuilderException, GameMapException, MatchException{
		Board b = new BoardRetriver().retriveBoard();
		int position = 0;
		HashMap<Gamer,Integer> positions = new HashMap<Gamer,Integer>();
		
		for(String s: gamersName){
			HashSet<PoliticalCard> pc = new HashSet<PoliticalCard>();
			for(int i = 0; i < GamerConstants.INITIAL_NUMBER_OF_CARDS; i++){ pc.add(b.getPoliticalCardsDeck().pickupCard()); }
			this.gamers.add(new GamerGenerator().createGamer(s, position, actualTitle, pc));
		}
		for(Gamer g: gamers) positions.put(g, MatchConstants.MIN_POSITION);
		
		Match m = new MatchBuilder().setBoard(b)
									.setMatchCode("" + actualCode)
									.setDate(new Date())
									.setTitle(actualTitle)
									.setPositions(positions)
									.setActualGamer(this.gamersName[0])
									.setStatus(MatchStatus.ACTIVE)
									.build();
		
		actualCode++;
		return m;
	}
}
