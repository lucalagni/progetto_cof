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
	private ArrayList<Gamer> gamers = null;
	
	public MatchGenerator() throws BuilderException, GameMapException{
		this.b = new BoardRetriver().retriveBoard();
		this.gamers = new ArrayList<Gamer>();
	}
	
	public String getMatchCode(){ return ""+actualCode; }
	
	public int getGamersNumber(){ return this.gamers.size(); }
	
	public int addGamer(String username) throws PoliticalCardsDeckException{
		ArrayList<PoliticalCard> pc = new ArrayList<PoliticalCard>();
		
		for(int i = 0 ; i < GamerConstants.INITIAL_NUMBER_OF_CARDS; i++) {
			this.b.getPoliticalCardsDeck().shuffleCards();
			pc.add(this.b.getPoliticalCardsDeck().pickupCard());
		}
		
		Gamer g = new GamerBuilder().setUsername(username)
									.setColor(GamerConstants.GAMERS_COLORS[this.gamers.size()])
									.setCoins(CoinsPoolConstants.FIRST_GAMER_COINS + this.gamers.size())
									.setHelpers(HelpersPoolConstants.FIRST_GAMER_HELPERS + this.gamers.size())
									.setPoints(GamerConstants.INITIAL_NUMBER_OF_POINTS)
									.setShifts(GamerConstants.INITIAL_NUMBER_OF_SHIFTS)
									.setShops(GamerConstants.INITIAL_NUMBER_OF_SHOPS)
									.setPoliticalCards(pc)
									.setMatch("" + actualCode)
									.setStatus(GamerStatus.ONLINE)
									.setUnusedPermitCards(new ArrayList<PermitCard>())
									.setUsedPermitCards(new ArrayList<PermitCard>())
									.build();
		
		this.gamers.add(g);
		
		return this.gamers.size();
	}
	
	
	public Match createMatch() throws PoliticalCardsDeckException, BuilderException, GameMapException, MatchException{
		
		Match m = new MatchBuilder().setBoard(this.b)
									.setMatchCode("" + actualCode)
									.setDate(new Date())
									.setTitle(actualTitle)
									.setGamers(this.gamers)
									.setActualGamer(0)
									.setStatus(MatchStatus.READY)
									.build();
		
		actualCode++;
		return m;
	}
}
