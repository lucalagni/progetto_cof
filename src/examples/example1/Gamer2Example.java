package examples.example1;

import java.util.ArrayList;

import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.builders.GamerBuilder;
import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.GamerConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.supports.GamerStatus;

public class Gamer2Example {
	private String match;
	private ArrayList<PoliticalCard> politicalCards;
	private ArrayList<PermitCard> unusedPermitCards;
	private ArrayList<PermitCard> usedPermitCards;
	
	public Gamer2Example(String match,ArrayList<PoliticalCard> politicalCards,ArrayList<PermitCard> unusedPermitCards, ArrayList<PermitCard> usedPermitCards){
		this.match = match;
		this.politicalCards = politicalCards;
		this.unusedPermitCards = unusedPermitCards;
		this.usedPermitCards = usedPermitCards;
	}
	
	
	public Gamer getGamer2(){
		Gamer g = null;
		
		g = new GamerBuilder().setUsername("Gamer2")
							  .setColor(GamerConstants.GAMERS_COLORS[1])
							  .setCoins(CoinsPoolConstants.FIRST_GAMER_COINS + CoinsPoolConstants.NEXT_GAMER_COINS)
							  .setShops(10)
							  .setPoints(GamerConstants.INITIAL_NUMBER_OF_POINTS)
							  .setShifts(GamerConstants.INITIAL_NUMBER_OF_SHIFTS)
							  .setHelpers(HelpersPoolConstants.FIRST_GAMER_HELPERS + HelpersPoolConstants.NEXT_GAMER_HELPERS)
							  .setStatus(GamerStatus.ONLINE)
							  .setPoliticalCards(this.politicalCards)
							  .setUnusedPermitCards(this.unusedPermitCards)
							  .setUsedPermitCards(this.usedPermitCards)
							  .setMatch(this.match)
							  .build();
		
		return g;
	}
}