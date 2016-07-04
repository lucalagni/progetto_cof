package examples.example1;

import java.awt.Color;

import model.basics.Bonus;
import model.basics.Region;
import model.basics.builders.BonusBuilder;
import model.basics.builders.CouncilBuilder;
import model.basics.builders.RegionBuilder;
import model.basics.constants.ColorConstants;
import model.basics.constants.VillageConstants;


public class Region3Example {
	private Region region;
	
	public Region3Example(){
		Color[] nobles = { ColorConstants.POLITICAL_COLORS[0], ColorConstants.POLITICAL_COLORS[1], ColorConstants.POLITICAL_COLORS[2],ColorConstants.POLITICAL_COLORS[3]};
		String[] villages = {VillageConstants.KULTOS, VillageConstants.LYRAM, VillageConstants.MERKATIM,VillageConstants.NARIS,VillageConstants.OSIUM};
		Bonus b = new BonusBuilder().setCoins(0)
									.setHelpers(0)
									.setPoints(5)
									.setShifts(0)
									.setPoliticalCards(0)
									.setReusePermitBonus(false)
									.setAcquirePermitCard(false)
									.setAcquireSingleVillageBonus(false)
									.setAcquireDoubleVillageBonus(false)
									.setNewMainAction(false)
									.setAvailable(true)
									.build();
		this.region = new RegionBuilder().setNumber(2)
										 .setVillages(villages)
										 .setBonus(b)
										 .setCouncil(new CouncilBuilder().setNobles(nobles).build())
										 .setPermitCardDeck(new PermitCardsDeck3Example().getDeck())
										 .build();
	}
	
	public Region getRegion(){ return this.region; }
}
