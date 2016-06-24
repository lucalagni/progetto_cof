package model.basics;

import java.awt.Color;
import java.io.Serializable;

import model.basics.constants.ColorConstants;

public class PoliticalCard implements Serializable{
	private static final long serialVersionUID = 1L;
	private Color color;
	private Boolean jolly;
	
	public PoliticalCard(Color color,boolean jolly) {
		this.setJolly(jolly);
		this.setColor(color);
	}
	
	public boolean checkColorConsistence(Color color){
		for(Color c: ColorConstants.POLITICAL_COLORS) if(c.equals(color)) return true;
		return false;
	}
	
	public boolean checkJollyColorConsistence(Color color){
		if(ColorConstants.JOLLY_CARD_COLOR.equals(color)) return true;
		else return false;
	}
	
	private void setColor(Color color) { this.color = color;}
	private void setJolly(boolean jolly) { this.jolly = new Boolean(jolly);}
	
	public boolean getJolly() { return this.jolly.booleanValue();}
	public Color getColor() { return this.color; }
	
	@Override
	public String toString(){
		String pcString = "\npolitical card\n";
		
		pcString += "color: " + this.getColor().toString() + "\n";
		pcString += "jolly: " + this.getJolly() +"\n";
		
		return pcString;
	}
}