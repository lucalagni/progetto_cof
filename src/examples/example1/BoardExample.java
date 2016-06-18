package examples.example1;

import model.basics.Board;
import model.basics.NoblesPool;
import model.basics.builders.BoardBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.Region;

public class BoardExample {
	private Board board;
	
	public BoardExample() throws BuilderException, GameMapException{
		Region[] regions = new Region[3];
		
		regions[0] = new Region1Example().getRegion();
		regions[1] = new Region2Example().getRegion();
		regions[2] = new Region3Example().getRegion();
		
		this.board = new BoardBuilder().setHelpersPool(new HelpersPoolExample().getHelpersPool())
									   .setGameMap(new GameMapExample().getGameMap())
									   .setKing(new KingExample().getKing())
									   .setNobiltyPath(new NobiltyPathExample().getNobiltyPath())
									   .setPoliticalCardsDeck(new PoliticalCardsDeckExample().getPoliticalCardsDeck())
									   .setRegions(regions)
									   .setNoblesPool(new NoblesPool())
									   .build();
	}
	
	public Board getBoard(){ return this.board; }
}
