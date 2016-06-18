package model.basics.builders;

import model.basics.Bonus;
import model.basics.NobiltyPath;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.NobiltyPathConstants;
import model.basics.exceptions.codes.NobiltyPathExceptionCode;

public class NobiltyPathBuilder {
	public Bonus[] bonus;
	
	public NobiltyPathBuilder setBonus(Bonus[] bonus){
		this.bonus = bonus;
		return this;
	}
	
	public NobiltyPath build() throws BuilderException {
		if(this.bonus.length != NobiltyPathConstants.TOTAL_TILES) throw new BuilderException(NobiltyPathExceptionCode.INVALID_POSITION.getExceptionCode());
		return new NobiltyPath(this.bonus);
	}
}
