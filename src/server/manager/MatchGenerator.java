package server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.basics.Board;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.builders.GamerBuilder;
import model.basics.builders.MatchBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.GamerConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.supports.GamerStatus;
import model.basics.supports.MatchStatus;

public class MatchGenerator {
	private static int actualCode = 1;
	private static String actualTitle = "match_" + actualCode;
	
	private Board b = null;
	private HashMap<Gamer,Integer> positions = null;
	
	public MatchGenerator() throws BuilderException, GameMapException{
		this.b = new BoardRetriver().retriveBoard();
		this.positions = new HashMap<Gamer,Integer>();
	}
	
	public String getMatchCode(){ return ""+actualCode; }
	
	public int getGamersNumber(){ return this.positions.size(); }
	
	public int addGamer(String username) throws PoliticalCardsDeckException{
		ArrayList<PoliticalCard> pc = new ArrayList<PoliticalCard>();
		
		for(int i = 0 ; i < GamerConstants.INITIAL_NUMBER_OF_CARDS; i++) {
			this.b.getPoliticalCardsDeck().shuffleCards();
			pc.add(this.b.getPoliticalCardsDeck().pickupCard());
		}
		
		Gamer g = new GamerBuilder().setUsername(username)
									.setColor(GamerConstants.GAMERS_COLORS[this.positions.size()])
									.setCoins(CoinsPoolConstants.FIRST_GAMER_COINS + this.positions.size())
									.setHelpers(HelpersPoolConstants.FIRST_GAMER_HELPERS + this.positions.size())
									.setPoints(GamerConstants.INITIAL_NUMBER_OF_POINTS)
									.setShifts(GamerConstants.INITIAL_NUMBER_OF_SHIFTS)
									.setShops(GamerConstants.INITIAL_NUMBER_OF_SHOPS)
									.setPoliticalCards(pc)
									.setMatch("" + actualCode)
									.setStatus(GamerStatus.ONLINE)
									.setUnusedPermitCards(new ArrayList<PermitCard>())
									.setUsedPermitCards(new ArrayList<PermitCard>())
									.build();
		
		this.positions.put(g, Integer.valueOf(MatchConstants.MIN_POSITION));
		
		return this.positions.size();
	}
	
	
	public Match createMatch() throws PoliticalCardsDeckException, BuilderException, GameMapException, MatchException{
		Iterator<Map.Entry<Gamer, Integer>> it = this.positions.entrySet().iterator();
		Map.Entry<Gamer, Integer> entry = it.next();
		
		Match m = new MatchBuilder().setBoard(this.b)
									.setMatchCode("" + actualCode)
									.setDate(new Date())
									.setTitle(actualTitle)
									.setPositions(this.positions)
									.setActualGamer(entry.getKey().getUsername())
									.setStatus(MatchStatus.READY)
									.build();
		
		actualCode++;
		return m;
	}
}
