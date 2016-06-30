package server.managers.match;

import java.util.ArrayList;
import java.util.Date;

import server.managers.board.BoardRetriver;
import model.basics.Board;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.Village;
import model.basics.builders.GamerBuilder;
import model.basics.builders.MatchBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.GamerConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.constants.MatchConstants;
import model.basics.constants.RegionConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.exceptions.VillageException;
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
	
	private synchronized void updateActualCode(){ actualCode++;}
	
	
	public Match createMatch() throws PoliticalCardsDeckException, BuilderException, GameMapException, MatchException{
		
		Match m = new MatchBuilder().setBoard(this.b)
									.setMatchCode("" + actualCode)
									.setDate(new Date())
									.setTitle(actualTitle)
									.setGamers(this.gamers)
									.setActualGamer(0)
									.setStatus(MatchStatus.ACTIVE)
									.build();
		if(m.getGamers().size() == MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY)
			try {
				this.twoGamersInitialSetup(m);
			} catch (VillageException | GamerException e) {
				e.printStackTrace();
			}
		
		this.updateActualCode();
		return m;
	}
	
	private void twoGamersInitialSetup(Match match) throws VillageException, GamerException{
		char[] c = new char[3];
		String fakeGamer = GamerConstants.FAKE_GAMER;
		Village[] villages = match.getBoard().getGameMap().getVillages();
		int random = (int) (Math.random() * RegionConstants.VILLAGES_REGION_1.length); 
		
		c[0] = RegionConstants.VILLAGES_REGION_1[random].charAt(0);
		random = (int) (Math.random() * RegionConstants.VILLAGES_REGION_2.length); 
		c[1] = RegionConstants.VILLAGES_REGION_2[random].charAt(0);
		random = (int) (Math.random() * RegionConstants.VILLAGES_REGION_3.length); 
		c[2] = RegionConstants.VILLAGES_REGION_3[random].charAt(0);
		
		for(int i = 0; i < villages.length; i++){
			if(villages[i].getFirstLetter() == c[0]) villages[i].addShop(fakeGamer);
			if(villages[i].getFirstLetter() == c[1]) villages[i].addShop(fakeGamer);
			if(villages[i].getFirstLetter() == c[2]) villages[i].addShop(fakeGamer);
		}
	
}
}
