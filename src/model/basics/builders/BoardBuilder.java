package model.basics.builders;

import model.basics.Board;
import model.basics.GameMap;
import model.basics.HelpersPool;
import model.basics.King;
import model.basics.NobiltyPath;
import model.basics.NoblesPool;
import model.basics.PoliticalCardsDeck;
import model.basics.Region;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.codes.BoardExceptionCode;

public class BoardBuilder {
	private King king;
	private PoliticalCardsDeck politicalCardsDeck;
	private Region[] regions;
	private HelpersPool helpersPool;
	private NobiltyPath nobiltyPath;
	private GameMap map;
	private NoblesPool noblesPool;
	
	public Board build() throws BuilderException {
		if(this.king == null) throw new BuilderException(BoardExceptionCode.NULL_KING.getExceptionCode());
		if(this.politicalCardsDeck == null) throw new BuilderException(BoardExceptionCode.NULL_POLITICAL_CARDS_DECK.getExceptionCode());
		if(this.regions == null) throw new BuilderException(BoardExceptionCode.NULL_REGIONS.getExceptionCode());
		if(this.regions.length != 3) throw new BuilderException(BoardExceptionCode.EMPTY_REGIONS_LIST.getExceptionCode());
		if(this.helpersPool == null) throw new BuilderException(BoardExceptionCode.NULL_HELPERS_POOL.getExceptionCode());
		if(this.nobiltyPath == null) throw new BuilderException(BoardExceptionCode.NULL_NOBILTY_PATH.getExceptionCode());
		if(this.map == null) throw new BuilderException(BoardExceptionCode.NULL_NOBILTY_PATH.getExceptionCode());
		if(this.noblesPool == null) throw new BuilderException(BoardExceptionCode.NULL_NOBLES_POOL.getExceptionCode());
		
		return new Board(this.king, this.politicalCardsDeck, this.regions, this.helpersPool, this.nobiltyPath, this.map, this.noblesPool);
	}
	
	public BoardBuilder setNoblesPool(NoblesPool noblesPool){
		this.noblesPool = noblesPool;
		return this;
	}
	
	public BoardBuilder setGameMap(GameMap map){
		this.map = map;
		return this;
	}
	
	public BoardBuilder setKing(King king){ 
		this.king = king; 
		return this;
	}
	public BoardBuilder setPoliticalCardsDeck(PoliticalCardsDeck politicalCardsDeck){ 
		this.politicalCardsDeck = politicalCardsDeck; 
		return this;
	}
	public BoardBuilder setRegions(Region[] regions){ 
		this.regions = regions; 
		return this;
	}
	public BoardBuilder setHelpersPool(HelpersPool helpersPool) { 
		this.helpersPool = helpersPool; 
		return this;
	}
	public BoardBuilder setNobiltyPath(NobiltyPath nobiltyPath) { 
		this.nobiltyPath = nobiltyPath;
		return this;
	}
	
}
