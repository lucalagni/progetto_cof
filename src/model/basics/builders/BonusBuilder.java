package model.basics.builders;

import model.basics.Bonus;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.BonusConstants;
import model.basics.exceptions.codes.BonusExceptionCodes;

public class BonusBuilder {
	
		private boolean available ;
		private int coins ;
		private int helpers; 
		private int politicalCards ; 
		private int points;   			  			 
		private int shifts;  			 			 
		private boolean newMainAction;	 
		private boolean reusePermitBonus ;
		private boolean acquirePermitCard;
		private boolean acquireSingleVillageBonus;
		private boolean acquireDoubleVillageBonus;
		
		public BonusBuilder setAvailable(boolean available){
			this.available = available;
			return this;
		}
	
		public BonusBuilder setAcquirePermitCard(boolean acquirePermitCard){ 
			this.acquirePermitCard = acquirePermitCard; 
			return this;
		}
		public BonusBuilder setCoins(int coins) { 
			this.coins = coins; 
			return this;
		}
		public BonusBuilder setPoints(int points){ 
			this.points = points; 
			return this;
		}
		public BonusBuilder setShifts(int shifts){ 
			this.shifts = shifts; 
			return this;
		}
		public BonusBuilder setPoliticalCards(int politicalCards){ 
			this.politicalCards = politicalCards; 
			return this;
		}
		public BonusBuilder setHelpers(int helpers){ 
			this.helpers = helpers; 
			return this;
		}
		public BonusBuilder setNewMainAction(boolean newMainAction){ 
			this.newMainAction = newMainAction; 
			return this;
		}
		public BonusBuilder setReusePermitBonus(boolean reusePermitBonus){ 
			this.reusePermitBonus = reusePermitBonus; 
			return this;
		}
		public BonusBuilder setAcquireSingleVillageBonus(boolean acquireSingleVillageBonus){ 
			this.acquireSingleVillageBonus = acquireSingleVillageBonus; 
			return this;
		}
		public BonusBuilder setAcquireDoubleVillageBonus(boolean acquireDoubleVillageBonus){ 
			this.acquireDoubleVillageBonus = acquireDoubleVillageBonus; 
			return this;
		}
		
		public Bonus build() throws BuilderException{
			if(this.coins < BonusConstants.MIN_COINS) throw new BuilderException(BonusExceptionCodes.TOO_FEW_COINS.getExceptionCodes());
			if(this.coins > BonusConstants.MAX_COINS) throw new BuilderException(BonusExceptionCodes.TOO_MANY_COINS.getExceptionCodes());
			if(this.helpers < BonusConstants.MIN_HELPERS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			if(this.helpers > BonusConstants.MAX_HELPERS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			if(this.politicalCards < BonusConstants.MIN_POLITICAL_CARDS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			if(this.politicalCards > BonusConstants.MAX_POLITICAL_CARDS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			if(this.points < BonusConstants.MIN_POINTS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			if(this.points > BonusConstants.MAX_POINTS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			if(this.shifts < BonusConstants.MIN_SHIFTS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			if(this.shifts > BonusConstants.MAX_SHIFTS) throw new BuilderException(BonusExceptionCodes.INVALID_INPUT_DATA.getExceptionCodes());
			
			return new Bonus(this.available, this.coins, this.helpers, this.politicalCards, this.points, this.shifts, this.newMainAction, this.reusePermitBonus, this.acquireSingleVillageBonus, this.acquireDoubleVillageBonus, this.acquirePermitCard);
		}
		

}
