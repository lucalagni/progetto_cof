package examples.example1;

import java.awt.Color;

import model.basics.Bonus;
import model.basics.King;
import model.basics.builders.BonusBuilder;
import model.basics.builders.CouncilBuilder;
import model.basics.builders.KingBuilder;
import model.basics.constants.ColorConstants;
import model.basics.constants.CouncilConstants;
import model.basics.constants.KingConstants;

public class KingExample {
	public King king ;
	
	public KingExample(){
		this.setKing();
	}
	
	public King getKing(){ return this.king; }
	
	private void setKing(){
		Color[] nobles = new Color[CouncilConstants.NOBLES_NUMBER];
		Bonus[] bonus = new Bonus[KingConstants.KING_BONUS];
		
		for(int i = 0; i < nobles.length; i++){
			nobles[i] = ColorConstants.POLITICAL_COLORS[i];
		}
		
		bonus[0] = new BonusBuilder().setCoins(0)
									 .setHelpers(0)
									 .setPoints(25)
									 .setShifts(0)
									 .setPoliticalCards(0)
									 .setNewMainAction(false)
									 .setReusePermitBonus(false)
									 .setAcquirePermitCard(false)
									 .setAcquireSingleVillageBonus(false)
									 .setAcquireDoubleVillageBonus(false)
									 .setAvailable(true)
									 .build();
		
		bonus[1] = new BonusBuilder().setCoins(0)
				 .setHelpers(0)
				 .setPoints(18)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		
		bonus[2] = new BonusBuilder().setCoins(0)
				 .setHelpers(0)
				 .setPoints(12)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		
		bonus[3] = new BonusBuilder().setCoins(0)
				 .setHelpers(0)
				 .setPoints(7)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		
		bonus[4] = new BonusBuilder().setCoins(0)
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
				 .build();
		
		this.king = new KingBuilder().setCouncil(new CouncilBuilder().setNobles(nobles).build())
									 .setPosition(KingConstants.INIT_KING_POSITION)
									 .setBonus(bonus)
									 .build();
	}
}
