package model.basics.builders;

import model.basics.Bonus;
import model.basics.Council;
import model.basics.King;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.KingConstants;
import model.basics.exceptions.codes.KingExceptionCode;

public class KingBuilder {
	private Council council;
	private String position;
	private Bonus[] bonus;
	
	public KingBuilder setCouncil(Council council){
		this.council = council;
		return this;
	}
	
	public KingBuilder setPosition(String position){
		this.position = position;
		return this;
	}
	
	public KingBuilder setBonus(Bonus[] bonus){
		this.bonus = bonus;
		return this;
	}
	
	public King build() throws BuilderException {
		if(this.council == null) throw new BuilderException(KingExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.position == null) this.position = KingConstants.INIT_KING_POSITION;
		if(this.bonus == null) throw new BuilderException(KingExceptionCode.NULL_BONUS.getExceptionCode());
		if(this.bonus.length != KingConstants.KING_BONUS) throw new BuilderException(KingExceptionCode.INVALID_BONUS_NUMBER.getExceptionCode());
		
		return new King(this.position, this.council, this.bonus);
	}
}
