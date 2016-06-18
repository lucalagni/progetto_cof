package model.basics.builders;

import java.util.HashSet;

import model.basics.Bonus;
import model.basics.PermitCard;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.PermitCardConstants;
import model.basics.exceptions.codes.PermitCardExceptionCode;

public class PermitCardBuilder {
	public Bonus bonus;
	public HashSet<String> villages;
	
	public PermitCardBuilder(){}
	
	public PermitCardBuilder setBonus(Bonus bonus) {
		this.bonus = bonus;
		return this;
	}
	
	public PermitCardBuilder setVillages(HashSet<String> villages) {
		this.villages = villages;
		return this;
	}
	
	public PermitCard build() throws BuilderException {
		if(this.bonus == null) throw new BuilderException(PermitCardExceptionCode.INVALID_BONUS.getExceptionCode());
		if(this.villages == null) throw new BuilderException(PermitCardExceptionCode.INVALID_VILLAGE.getExceptionCode());
		if(this.villages.toArray().length > PermitCardConstants.MAX_VILLAGES_NUMBER) throw new BuilderException(PermitCardExceptionCode.INVALID_VILLAGE.getExceptionCode());
		if(this.villages.toArray().length < PermitCardConstants.MIN_VILLAGES_NUMBER) throw new BuilderException(PermitCardExceptionCode.INVALID_VILLAGE.getExceptionCode());
		
		return new PermitCard(this.villages, this.bonus);
	}
}
