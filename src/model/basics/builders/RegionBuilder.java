package model.basics.builders;

import model.basics.Bonus;
import model.basics.Council;
import model.basics.PermitCardsDeck;
import model.basics.Region;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.RegionConstants;
import model.basics.exceptions.codes.RegionExceptionCode;

public class RegionBuilder {
	private int number;
	private Bonus bonus;
	private Council council;
	private String[] villages;
	private PermitCardsDeck permitCardsDeck;
	
	public RegionBuilder setNumber(int number) { 
		this.number = number; 
		return this;
	}
	public  RegionBuilder setBonus(Bonus bonus) { 
		this.bonus = bonus; 
		return this;
	}
	public RegionBuilder setCouncil(Council council) { 
		this.council = council; 
		return this;
	}
	public RegionBuilder setVillages(String[] villages) { 
		this.villages = villages; 
		return this;
	}
	public RegionBuilder setPermitCardDeck(PermitCardsDeck permitCardsDeck) { 
		this.permitCardsDeck = permitCardsDeck; 
		return this;
	}
	
	public Region build() throws BuilderException {
		//Verificare che le città inserite appartengano effettivamente alla regione
		//Verificare che le carte del mazzo siano effettivamente della città
		//if((this.number != 1) || (this.number != 2) || (this.number != 3)) throw new BuilderException(RegionExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.bonus == null)throw new BuilderException(RegionExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.council == null) throw new BuilderException(RegionExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.villages == null)throw new BuilderException(RegionExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.villages.length != RegionConstants.NUMBER_OF_VILLAGES_FOR_REGION)throw new BuilderException(RegionExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.permitCardsDeck == null)throw new BuilderException(RegionExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		
		return new Region(this.number,this.bonus,this.council, this.villages, this.permitCardsDeck);
	}
}
