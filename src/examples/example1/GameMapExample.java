package examples.example1;

import model.basics.Bonus;
import model.basics.GameMap;
import model.basics.builders.BonusBuilder;
import model.basics.builders.GameMapBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.GameMapConstants;
import model.basics.constants.VillageConstants;
import model.basics.exceptions.GameMapException;

public class GameMapExample {
	private GameMap gameMap;
	private Bonus[] colorBonus;
	private int[][] connections;
	
	public GameMapExample() throws BuilderException, GameMapException{
		this.setupBonus();
		this.setupConnections();
		
		this.gameMap = new GameMapBuilder().setBonus(colorBonus)
										   .setVillages(new VillagesExample().getVillages())
										   .setConnections(this.connections)
										   .build();
	}
	
	public GameMap getGameMap(){ return this.gameMap; }
	
	private void setupConnections(){
		this.connections = new int[VillageConstants.VILLAGES_NAME.length][VillageConstants.VILLAGES_NAME.length];
		
		for(int i = 0; i < this.connections.length; i++)for(int j = 0; j < this.connections[i].length; j++) this.connections[i][j] = GameMapConstants.NOT_CONNECTED;
		
		for(int i = 0; i < this.connections.length; i++){
			for(int j = 0; j < this.connections[i].length; j++){
				if((i == j)) this.connections[i][j] = GameMapConstants.ITSELF;
				if(((i == 0) && (j == 1)) || ((i == 1) && (j == 0))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 0) && (j == 2)) || ((i == 2) && (j == 0))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 1) && (j == 3)) || ((i == 3) && (j == 1))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 1) && (j == 4)) || ((i == 4) && (j == 1))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 2) && (j == 5)) || ((i == 5) && (j == 2))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 3) && (j == 6)) || ((i == 6) && (j == 3))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 4) && (j == 7)) || ((i == 7) && (j == 4))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 5) && (j == 8)) || ((i == 8) && (j == 5))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 6) && (j == 9)) || ((i == 9) && (j == 6))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 7) && (j == 9)) || ((i == 9) && (j == 7))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 7) && (j == 12)) || ((i == 12) && (j == 7))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 8) && (j == 9)) || ((i == 9) && (j == 8))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 8) && (j == 10)) || ((i == 10) && (j == 8))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 9) && (j == 11)) || ((i == 11) && (j == 9))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 9) && (j == 12)) || ((i == 12) && (j == 9))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 10) && (j == 13)) || ((i == 13) && (j == 10))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 11) && (j == 14)) || ((i == 14) && (j == 11))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 12) && (j == 14)) || ((i == 14) && (j == 12))) this.connections[i][j] = GameMapConstants.CONNECTED;
				if(((i == 13) && (j == 14)) || ((i == 14) && (j == 13))) this.connections[i][j] = GameMapConstants.CONNECTED;
			}
		}
	}
	
	private void setupBonus() {
	    colorBonus = new Bonus[GameMapConstants.COLOR_BONUS.length];
		
		colorBonus[0] = new BonusBuilder().setCoins(0)
										  .setHelpers(0)
										  .setPoints(5)
										  .setShifts(0)
										  .setPoliticalCards(0)
										  .setNewMainAction(false)
										  .setAcquirePermitCard(false)
										  .setReusePermitBonus(false)
										  .setAcquireSingleVillageBonus(false)
										  .setAcquireDoubleVillageBonus(false)
										  .setAvailable(true)
										  .build();
		
		colorBonus[1] = new BonusBuilder().setCoins(0)
				  .setHelpers(0)
				  .setPoints(8)
				  .setShifts(0)
				  .setPoliticalCards(0)
				  .setNewMainAction(false)
				  .setAcquirePermitCard(false)
				  .setReusePermitBonus(false)
				  .setAcquireSingleVillageBonus(false)
				  .setAcquireDoubleVillageBonus(false)
				  .setAvailable(true)
				  .build();
		
		colorBonus[2] = new BonusBuilder().setCoins(0)
				  .setHelpers(0)
				  .setPoints(12)
				  .setShifts(0)
				  .setPoliticalCards(0)
				  .setNewMainAction(false)
				  .setAcquirePermitCard(false)
				  .setReusePermitBonus(false)
				  .setAcquireSingleVillageBonus(false)
				  .setAcquireDoubleVillageBonus(false)
				  .setAvailable(true)
				  .build();
		
		colorBonus[3] = new BonusBuilder().setCoins(0)
				  .setHelpers(0)
				  .setPoints(20)
				  .setShifts(0)
				  .setPoliticalCards(0)
				  .setNewMainAction(false)
				  .setAcquirePermitCard(false)
				  .setReusePermitBonus(false)
				  .setAcquireSingleVillageBonus(false)
				  .setAcquireDoubleVillageBonus(false)
				  .setAvailable(true)
				  .build();
	}
}
