package examples.example1;

import model.basics.Village;
import model.basics.builders.BonusBuilder;
import model.basics.builders.VillageBuilder;
import model.basics.constants.BonusConstants;
import model.basics.constants.ColorConstants;
import model.basics.constants.MatchConstants;
import model.basics.constants.VillageConstants;

public class VillagesExample {
	public Village[] villages;
	
	public VillagesExample(){
		this.villages = new Village[VillageConstants.VILLAGES_NAME.length];
		
		//Villaggi regione 1
		
		
		String[] gamerShop = new String[MatchConstants.MAX_NUMBER_OF_GAMERS];
		
		gamerShop[0] = "Gamer1";
		
		this.villages[0] = new VillageBuilder().setName(VillageConstants.ARKON)
											   .setColor(ColorConstants.VILLAGES_COLORS[0])
											   //.setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
											   .setShops(gamerShop)
											   .setBonus(new BonusBuilder().setCoins(0)
													   					   .setHelpers(0)
													   					   .setPoints(3)
													   					   .setShifts(0)
													   					   .setPoliticalCards(0)
													   					   .setNewMainAction(false)
													   					   .setReusePermitBonus(false)
													   					   .setAcquirePermitCard(false)
													   					   .setAcquireSingleVillageBonus(false)
													   					   .setAcquireDoubleVillageBonus(false)
													   					   .setAvailable(true)
													   					   .build())
											   .build();
		
		this.villages[1] = new VillageBuilder().setName(VillageConstants.BURGEN)
				   .setColor(ColorConstants.VILLAGES_COLORS[1])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(0)
						   					   .setPoints(0)
						   					   .setShifts(1)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[2] = new VillageBuilder().setName(VillageConstants.CASTRUM)
				   .setColor(ColorConstants.VILLAGES_COLORS[2])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(1)
						   					   .setHelpers(2)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[3] = new VillageBuilder().setName(VillageConstants.DORFUL)
				   .setColor(ColorConstants.VILLAGES_COLORS[2])
				   //.setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setShops(gamerShop)
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(1)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[4] = new VillageBuilder().setName(VillageConstants.ESTI)
				   .setColor(ColorConstants.VILLAGES_COLORS[3])
//				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setShops(gamerShop)
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(0)
						   					   .setPoints(1)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		//Villaggi regione 2
		this.villages[5] = new VillageBuilder().setName(VillageConstants.FRAMEK)
				   .setColor(ColorConstants.VILLAGES_COLORS[1])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(2)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[6] = new VillageBuilder().setName(VillageConstants.GRADEN)
				   .setColor(ColorConstants.VILLAGES_COLORS[2])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(3)
						   					   .setHelpers(0)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[7] = new VillageBuilder().setName(VillageConstants.HELLAR)
				   .setColor(ColorConstants.VILLAGES_COLORS[1])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(1)
						   					   .setHelpers(0)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		this.villages[8] = new VillageBuilder().setName(VillageConstants.INDUR)
				   .setColor(ColorConstants.VILLAGES_COLORS[3])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(0)
						   					   .setPoints(0)
						   					   .setShifts(1)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[9] = new VillageBuilder().setName(VillageConstants.JUVELAR)
				   .setColor(ColorConstants.VILLAGES_COLORS[4])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(BonusConstants.NULL_BONUS)
				   .build();
		
		
		//Villaggi regione 3
		this.villages[10] = new VillageBuilder().setName(VillageConstants.KULTOS)
				   .setColor(ColorConstants.VILLAGES_COLORS[1])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(0)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(1)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[11] = new VillageBuilder().setName(VillageConstants.LYRAM)
				   .setColor(ColorConstants.VILLAGES_COLORS[2])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(1)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(2)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[12] = new VillageBuilder().setName(VillageConstants.MERKATIM)
				   .setColor(ColorConstants.VILLAGES_COLORS[0])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(2)
						   					   .setHelpers(0)
						   					   .setPoints(0)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[13] = new VillageBuilder().setName(VillageConstants.NARIS)
				   .setColor(ColorConstants.VILLAGES_COLORS[3])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(0)
						   					   .setPoints(2)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
		
		this.villages[14] = new VillageBuilder().setName(VillageConstants.OSIUM)
				   .setColor(ColorConstants.VILLAGES_COLORS[0])
				   .setShops(new String[MatchConstants.MAX_NUMBER_OF_GAMERS])
				   .setBonus(new BonusBuilder().setCoins(0)
						   					   .setHelpers(0)
						   					   .setPoints(3)
						   					   .setShifts(0)
						   					   .setPoliticalCards(0)
						   					   .setNewMainAction(false)
						   					   .setReusePermitBonus(false)
						   					   .setAcquirePermitCard(false)
						   					   .setAcquireSingleVillageBonus(false)
						   					   .setAcquireDoubleVillageBonus(false)
						   					   .setAvailable(true)
						   					   .build())
				   .build();
	}
	
	public Village[] getVillages(){ return this.villages; }
}
