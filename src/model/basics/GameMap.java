package model.basics;

import java.io.Serializable;

import model.basics.constants.ColorConstants;
import model.basics.constants.GameMapConstants;
import model.basics.constants.VillageConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.codes.GameMapExceptionCode;

public class GameMap implements Serializable {
	private static final long serialVersionUID = 1L;
	private Village[] villages;
	private Integer[][] connections ;
	private Bonus[] colorsBonus ;
	
	public GameMap(Village[] villages,Bonus[] colorBonus)throws GameMapException{
		this.villages = new Village[VillageConstants.VILLAGES_NAME.length];
		this.connections = new Integer[VillageConstants.VILLAGES_NAME.length][VillageConstants.VILLAGES_NAME.length];
		if(villages.length != this.villages.length) throw new GameMapException(GameMapExceptionCode.INVALID_NUMBER_OF_VILLAGES.getExceptionCode());
		
		for(int i = 0; i < this.connections.length; i++){
			for(int j = 0; j < this.connections[i].length; i++){
				if(i == j) this.connections[i][j] = GameMapConstants.ITSELF;
				else this.connections[i][j] = GameMapConstants.NOT_CONNECTED;
			}
		}
		this.setVillages(villages);
		this.setColorsBonus(colorBonus);
	}
	
	public int getVillagePosition(Village v) throws GameMapException{
		for(int i = 0; i < this.villages.length; i++) if(this.villages[i] == v)return i;
		
		throw new GameMapException(GameMapExceptionCode.VILLAGE_NOT_FOUND.getExceptionCode());
	}
	
	public GameMap(Village[] villages, int connections[][],Bonus[] colorBonus)throws GameMapException{
		this.villages = new Village[VillageConstants.VILLAGES_NAME.length];
		this.connections = new Integer[VillageConstants.VILLAGES_NAME.length][VillageConstants.VILLAGES_NAME.length];
		
		for(int i = 0; i < this.connections.length; i++){
			for(int j = 0; j < this.connections[i].length; j++){
				if(i == j) this.connections[i][j] = new Integer(GameMapConstants.ITSELF);
				else this.connections[i][j] = new Integer(GameMapConstants.NOT_CONNECTED);
			}
		}
		
		this.setVillages(villages);
		this.setConnections(connections);
		this.setColorsBonus(colorBonus);
	}
	
	private void setVillages(Village[] villages){ this.villages = villages; }
	private void setConnections(int connections[][]){ 
		for(int i = 0; i < this.connections.length; i++){
			for(int j = 0; j < this.connections[i].length; j++){
				this.connections[i][j] = new Integer(connections[i][j]);
			}
		}
	}
	private void setColorsBonus(Bonus[] colorsBonus){ 
		this.colorsBonus = colorsBonus; 
	}
	
	private boolean checkVillage(Village village){
		for(int i = 0;i < this.villages.length; i++) if(this.villages[i] == village) return true;
		return false;
	}
	
	private int villagePosition(Village village){
		for(int i = 0; i < this.villages.length; i++) if(this.villages[i] == village) return i;
		return -1;
	}
	
	public boolean addConnection(Village village1,Village village2) throws GameMapException{
		int position1, position2 ;
		if((village1 == null)||(village2 == null)) throw new GameMapException(GameMapExceptionCode.NULL_VILLAGE_PASSED.getExceptionCode());
		if(village1 == village2) throw new GameMapException(GameMapExceptionCode.SAME_VILLAGE_PASSED.getExceptionCode());
		if(this.checkVillage(village1) == false) throw new GameMapException(GameMapExceptionCode.VILLAGE_NOT_FOUND.getExceptionCode());
		if(this.checkVillage(village2) == false) throw new GameMapException(GameMapExceptionCode.VILLAGE_NOT_FOUND.getExceptionCode());
		
		position1 = this.villagePosition(village1);
		position2 = this.villagePosition(village2);
		
		this.connections[position1][position2] = new Integer(GameMapConstants.CONNECTED);
		this.connections[position2][position1] = new Integer(GameMapConstants.CONNECTED);
		
		return true;
	}
	
	public Village[] getVillages(){ return this.villages; }
	public int[][] getConnections(){
		int[][] con = new int[this.connections.length][this.connections.length];
		
		for(int i = 0; i < this.connections.length; i++){
			for(int j = 0; j < this.connections[i].length; j++) con[i][j] = this.connections[i][j].intValue();
		}
		
		return con;
	}
	public Bonus[] getColorsBonus(){ return this.colorsBonus; }
	
	public String printConnections(String s){
		int length = 0;
		
		s += "\nLegenda: \n";
		s += "CONNECTED: " + GameMapConstants.CONNECTED + " \n";
		s += "ITSELF: " + GameMapConstants.ITSELF + " \n";
		s += "NOT_CONNECTED: " + GameMapConstants.NOT_CONNECTED + " \n";
		
		for(int i = 0; i < this.villages.length; i++)if(this.villages[i].getName().length() > length) length = this.villages[i].getName().length();
		s += createSpaceString(this.villages[0].getName().length() + length);
		for(int i = 0; i < this.villages.length; i++) s += addSpaces(this.villages[i].getName(), length, false);
		for(int i = 0; i < this.connections.length; i++){
			s += "\n";
			
			s += this.addSpaces(this.villages[i].getName(), length,true);
			for(int j = 0; j < this.connections[i].length; j++){
				s += this.createSpaceString(this.villages[j].getName().length() - 1);
				s += this.addSpaces("" + this.connections[i][j].intValue(), length ,false);
			}
		}
		
		s += "\n\n";
		return s;
	}
	
	private String createSpaceString(int len){
		String s = new String();
		for(int i = 0; i < len; i++) s += " ";
		return s;
	}
	
	private String addSpaces(String s, int space,boolean reverse){
		String spaces = new String("");
		
		for(int i = 0; i < space; i++) spaces += " ";
		if(reverse == false) {
			spaces += s;
			return spaces;
		}
		else {
			s += spaces;
			return s;
		}
	}
	
	@Override
	public String toString(){
		String s = new String("\nmap\n");
		
		s += "villages: \n";
		for(int i = 0; i < this.villages.length; i++) s += this.villages[i].toString() + "\n";
		s += "connections: \n";
		s += this.printConnections(s);
		s += "colors bonus: \n";
		for(int i = 0; i < this.colorsBonus.length; i++){
			s += "color: " + ColorConstants.VILLAGES_COLORS[i].toString() + " ";
			s += "value: " + this.colorsBonus[i].toString() + "\n";
		}
		return s;
		
	}
	
}
