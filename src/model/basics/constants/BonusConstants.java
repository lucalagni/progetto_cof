package model.basics.constants;

import model.basics.Bonus;
import model.basics.builders.BonusBuilder;

public final class BonusConstants {
	public static final int MIN_COINS  =  0;
	public static final int MAX_COINS  =  7;
	public static final int MIN_HELPERS = 0;
	public static final int MAX_HELPERS = 4;
	public static final int MIN_POINTS =  0;
	public static final int MAX_POINTS =  25;
	public static final int MIN_SHIFTS =  0;
	public static final int MAX_SHIFTS =  3;
	public static final int MIN_POLITICAL_CARDS = 0;
	public static final int MAX_POLITICAL_CARDS = 4;
	
	public static final Bonus NULL_BONUS = new BonusBuilder().setCoins(0)
															 .setHelpers(0)
															 .setPoints(0)
															 .setShifts(0)
															 .setPoliticalCards(0)
															 .setNewMainAction(false)
															 .setAcquirePermitCard(false)
															 .setReusePermitBonus(false)
															 .setAcquireSingleVillageBonus(false)
															 .setAcquireDoubleVillageBonus(false)
															 .build();
}
