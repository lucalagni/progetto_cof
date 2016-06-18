package model.basics.builders;

import java.awt.Color;

import model.basics.Bonus;
import model.basics.Village;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.GamerConstants;
import model.basics.exceptions.codes.VillageExceptionCode;

public class VillageBuilder {
	private String name;
	private Color color;
	private Bonus bonus;
	private String[] shops;
	
	public VillageBuilder(){}
	
	public VillageBuilder setName(String name) { 
		this.name = name; 
		return this;
	}
	
	public VillageBuilder setColor(Color color) { 
		this.color = color; 
		return this;
	}
	public  VillageBuilder setBonus(Bonus bonus) { 
		this.bonus = bonus;
		return this;
	}
	
	public VillageBuilder setShops(String[] shops){
		this.shops = shops;
		return this;
	}
	
	public Village build() throws BuilderException{
		if(Village.checkValidVillageName(name) == false) throw new BuilderException(VillageExceptionCode.INVALID_VILLAGE_NAME.getExceptionCode());
		if(Village.checkValidColor(color) == false) throw new BuilderException(VillageExceptionCode.INCONSISTENCE_BEETWEEN_COLOR_AND_PALETTE.getExceptionCode());
		if(this.bonus == null) throw new BuilderException(VillageExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(this.shops == null) this.shops = new String[GamerConstants.GAMERS_COLORS.length];
		
		return new Village(this.name, this.color, this.bonus, this.shops);
		
	}
}
