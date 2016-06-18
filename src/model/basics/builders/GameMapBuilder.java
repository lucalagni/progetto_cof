package model.basics.builders;

import model.basics.Bonus;
import model.basics.GameMap;
import model.basics.Village;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.VillageConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.codes.GameMapExceptionCode;

public class GameMapBuilder {
	private Village[] villages;
	private int[][] connections;
	private Bonus[] colorsBonus;
	
	public GameMapBuilder setVillages(Village[] villages){
		this.villages = villages;
		return this;
	}
	
	public GameMapBuilder setConnections(int[][] connections){
		this.connections = connections;
		return this;
	}
	
	public GameMapBuilder setBonus(Bonus[] colorsBonus){
		this.colorsBonus = colorsBonus;
		return this;
	}
	
	public GameMap build() throws GameMapException, BuilderException{
		if(this.villages == null) throw new BuilderException(GameMapExceptionCode.NULL_VILLAGE_PASSED.getExceptionCode());
		if(this.villages.length != VillageConstants.VILLAGES_NAME.length) throw new BuilderException(GameMapExceptionCode.INVALID_NUMBER_OF_VILLAGES.getExceptionCode());
		//if(this.colorsBonus.length != ColorConstants.VILLAGES_COLORS.length) throw new BuilderException(GameMapExceptionCode.COLORS_BONUS_INVALD_SET.getExceptionCode());
		
		if(this.connections == null) return new GameMap(this.villages, this.colorsBonus);
		return new GameMap(this.villages, this.connections, this.colorsBonus);
	}
}
