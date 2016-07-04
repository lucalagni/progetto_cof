package examples.example1;

import java.util.ArrayList;

import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.builders.GamerBuilder;
import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.GamerConstants;
import model.basics.supports.GamerStatus;

public class Gamer1Example {
	private String match;
	private ArrayList<PoliticalCard> politicalCards;
	private ArrayList<PermitCard> unusedPermitCards;
	private ArrayList<PermitCard> usedPermitCards;
	
	public Gamer1Example(String match,ArrayList<PoliticalCard> politicalCards,ArrayList<PermitCard> unusedPermitCards, ArrayList<PermitCard> usedPermitCards){
		this.match = match;
		this.politicalCards = politicalCards;
		this.unusedPermitCards = unusedPermitCards;
		this.usedPermitCards = usedPermitCards;
	}
	
	
	public Gamer getGamer1(){
		Gamer g = null;
		
		g = new GamerBuilder().setUsername("Gamer1")
							  .setColor(GamerConstants.GAMERS_COLORS[0])
							  .setCoins(CoinsPoolConstants.FIRST_GAMER_COINS)
							  .setShops(GamerConstants.INITIAL_NUMBER_OF_SHOPS)
							  .setPoints(GamerConstants.INITIAL_NUMBER_OF_POINTS)
							  .setShifts(GamerConstants.INITIAL_NUMBER_OF_SHIFTS)
							  //.setHelpers(HelpersPoolConstants.FIRST_GAMER_HELPERS)
                                                          .setHelpers(6)
							  .setStatus(GamerStatus.ONLINE)
							  .setPoliticalCards(this.politicalCards)
							  .setUnusedPermitCards(this.unusedPermitCards)
							  .setUsedPermitCards(this.usedPermitCards)
							  .setMatch(this.match)
							  .build();
		
		return g;
	}
}
