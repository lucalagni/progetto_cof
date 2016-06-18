package examples.example1;

import java.util.ArrayList;

import model.basics.PoliticalCard;
import model.basics.PoliticalCardsDeck;
import model.basics.builders.PoliticalCardBuilder;
import model.basics.builders.PoliticalCardsDeckBuilder;
import model.basics.constants.ColorConstants;
import model.basics.constants.PoliticalCardsDeckConstants;
import model.basics.supports.QuequedGamer;

public class PoliticalCardsDeckExample {
	private PoliticalCardsDeck politicalCardsDeck;
	
	public PoliticalCardsDeckExample(){
		ArrayList<PoliticalCard> acl = new ArrayList<PoliticalCard>();
		
		for(int i = 0; i < PoliticalCardsDeckConstants.JOLLY_CARDS_NUMBER; i++){
			acl.add(new PoliticalCardBuilder().setColor(ColorConstants.JOLLY_CARD_COLOR).setJolly(true).build());
		}
		
		for(int i = 0; i < PoliticalCardsDeckConstants.CARDS_FOR_COLOR; i++){
			acl.add(new PoliticalCardBuilder().setColor(ColorConstants.POLITICAL_COLORS[0]).setJolly(false).build());
			acl.add(new PoliticalCardBuilder().setColor(ColorConstants.POLITICAL_COLORS[1]).setJolly(false).build());
			acl.add(new PoliticalCardBuilder().setColor(ColorConstants.POLITICAL_COLORS[2]).setJolly(false).build());
			acl.add(new PoliticalCardBuilder().setColor(ColorConstants.POLITICAL_COLORS[3]).setJolly(false).build());
			acl.add(new PoliticalCardBuilder().setColor(ColorConstants.POLITICAL_COLORS[4]).setJolly(false).build());
			acl.add(new PoliticalCardBuilder().setColor(ColorConstants.POLITICAL_COLORS[5]).setJolly(false).build());
		}
		
		this.politicalCardsDeck = new PoliticalCardsDeckBuilder().setAvailableCards(acl)
																 .setQueque(new ArrayList<QuequedGamer>())
																 .build();
																 
	}
	
	public PoliticalCardsDeck getPoliticalCardsDeck(){ return this.politicalCardsDeck; }
}
