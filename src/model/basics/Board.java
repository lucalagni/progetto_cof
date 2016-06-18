package model.basics;

import java.io.Serializable;

public class Board implements Serializable{
	private static final long serialVersionUID = 1L;
	private King king;
	private PoliticalCardsDeck politicalCardsDeck;
	private Region[] regions;
	private HelpersPool helpersPool;
	private NobiltyPath nobiltyPath;
	private GameMap map;
	private NoblesPool noblesPool;
	
	public Board(King king,PoliticalCardsDeck pcd,Region[] regions,HelpersPool hp,NobiltyPath np,GameMap map,NoblesPool noblesPool) {
		this.setKing(king);
		this.setPoliticalCardsDeck(pcd);
		this.setRegions(regions);
		this.setHelpersPool(hp);
		this.setNobiltyPath(np);
		this.setGameMap(map);
		this.setNoblesPool(noblesPool);
	}
	
	private void setKing(King king) { this.king = king; }
	private void setPoliticalCardsDeck(PoliticalCardsDeck politicalCardsDeck) { this.politicalCardsDeck = politicalCardsDeck; }
	private void setRegions(Region[] regions){ this.regions = regions; }
	private void setHelpersPool(HelpersPool helpersPool){ this.helpersPool = helpersPool; }
	private void setNobiltyPath(NobiltyPath nobiltyPath){ this.nobiltyPath = nobiltyPath; }
	private void setGameMap(GameMap map){ this.map = map; }
	private void setNoblesPool(NoblesPool noblesPool){ this.noblesPool = noblesPool; }
	
	public King getKing(){ return this.king; }
	public PoliticalCardsDeck getPoliticalCardsDeck(){ return this.politicalCardsDeck; }
	public Region[] getRegions(){ return this.regions; }
	public HelpersPool getHelpersPool(){ return this.helpersPool; }
	public NobiltyPath getNobiltyPath(){ return this.nobiltyPath; }
	public GameMap getGameMap(){ return this.map; }
	public NoblesPool getNoblesPool(){ return this.noblesPool; }
	
	@Override
	public String toString(){
		String bString = new String("\nboard\n");
		
		bString += "king: " + this.getKing().toString() + "\n";
		bString += "political cards deck: " + this.getPoliticalCardsDeck().toString() + "\n";
		bString += "regions: \n";
		for(Region r: this.getRegions()) bString += r.toString() + "\n";
		bString += "game map: " + this.getGameMap().toString() + "\n";
		bString += "helpers pool: " + this.getHelpersPool().toString() + "\n";
		bString += "nobilty path: " + this.getNobiltyPath().toString() + "\n";
		bString += "nobles pool: " + this.getNoblesPool().toString() + "\n";
		
		return bString ;
	}
} 
