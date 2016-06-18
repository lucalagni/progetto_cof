package server.manager;

import java.util.ArrayList;

import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.builders.GamerBuilder;
import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.GamerConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.supports.GamerStatus;

public class GamerGenerator {
	
	public Gamer createGamer(String username,int position,String match,ArrayList<PoliticalCard> politicalCards){
		int coins = CoinsPoolConstants.FIRST_GAMER_COINS + (position * CoinsPoolConstants.NEXT_GAMER_COINS);
		int helpers = HelpersPoolConstants.FIRST_GAMER_HELPERS + (position * HelpersPoolConstants.NEXT_GAMER_HELPERS);
		
		Gamer gamer = new GamerBuilder().setUsername(username)
										.setColor(GamerConstants.GAMERS_COLORS[position])
										.setCoins(coins)
										.setHelpers(helpers)
										.setMatch(match)
										.setPoints(GamerConstants.INITIAL_NUMBER_OF_POINTS)
										.setShifts(GamerConstants.INITIAL_NUMBER_OF_SHIFTS)
										.setPoliticalCards(politicalCards)
										.setShops(GamerConstants.INITIAL_NUMBER_OF_SHOPS)
										.setStatus(GamerStatus.ONLINE)
										.setUsedPermitCards(new ArrayList<PermitCard>())
										.setUnusedPermitCards(new ArrayList<PermitCard>())
										.build();
		
		return gamer;
										
	}
}
