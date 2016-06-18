package examples.example1;

import model.basics.Bonus;
import model.basics.NobiltyPath;
import model.basics.builders.BonusBuilder;
import model.basics.builders.NobiltyPathBuilder;
import model.basics.constants.BonusConstants;
import model.basics.constants.NobiltyPathConstants;

public class NobiltyPathExample {
	public NobiltyPath nobiltyPath;
	
	public NobiltyPathExample(){
		Bonus bonus[] = new Bonus[NobiltyPathConstants.TOTAL_TILES];
		
		bonus[0] = BonusConstants.NULL_BONUS;
		bonus[1] = BonusConstants.NULL_BONUS;
		bonus[2] = new BonusBuilder().setCoins(2)
									 .setPoints(2)
									 .setHelpers(0)
									 .setShifts(0)
									 .setPoliticalCards(0)
									 .setNewMainAction(false)
									 .setReusePermitBonus(false)
									 .setAcquirePermitCard(false)
									 .setAcquireSingleVillageBonus(false)
									 .setAcquireDoubleVillageBonus(false)
									 .setAvailable(true)
									 .build();
		bonus[3] = BonusConstants.NULL_BONUS;
		bonus[4] = new BonusBuilder().setCoins(0)
				 .setPoints(0)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(true)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[5] = BonusConstants.NULL_BONUS;
		bonus[6] = new BonusBuilder().setCoins(0)
				 .setPoints(0)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(true)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[7] = BonusConstants.NULL_BONUS;
		bonus[8] = new BonusBuilder().setCoins(0)
				 .setPoints(3)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(1)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[9] = BonusConstants.NULL_BONUS;
		bonus[10] = new BonusBuilder().setCoins(0)
				 .setPoints(0)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(true)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[11] = BonusConstants.NULL_BONUS;
		bonus[12] = new BonusBuilder().setCoins(0)
				 .setPoints(5)
				 .setHelpers(2)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[13] = BonusConstants.NULL_BONUS;
		bonus[14] = new BonusBuilder().setCoins(0)
				 .setPoints(0)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(true)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[15] = BonusConstants.NULL_BONUS;
		bonus[16] = new BonusBuilder().setCoins(0)
				 .setPoints(0)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(true)
				 .setAvailable(true)
				 .build();
		bonus[17] = BonusConstants.NULL_BONUS;
		bonus[18] = new BonusBuilder().setCoins(0)
				 .setPoints(8)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[19] = new BonusBuilder().setCoins(0)
				 .setPoints(2)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		bonus[20] = new BonusBuilder().setCoins(0)
				 .setPoints(3)
				 .setHelpers(0)
				 .setShifts(0)
				 .setPoliticalCards(0)
				 .setNewMainAction(false)
				 .setReusePermitBonus(false)
				 .setAcquirePermitCard(false)
				 .setAcquireSingleVillageBonus(false)
				 .setAcquireDoubleVillageBonus(false)
				 .setAvailable(true)
				 .build();
		this.nobiltyPath = new NobiltyPathBuilder().setBonus(bonus).build();
	}
	
	public NobiltyPath getNobiltyPath(){ return this.nobiltyPath; }
}
