package model.basics;

import model.basics.constants.NoblesPoolConstants;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.codes.NoblesPoolExceptionCode;
import java.io.Serializable;

public class NoblesPool implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer blackNoblesLeft;
	private Integer whiteNoblesLeft;
	private Integer cyanNoblesLeft;
	private Integer pinkNoblesLeft;
	private Integer magentaNoblesLeft ;
	private Integer orangeNoblesLeft;
	
	public NoblesPool(int blackNoblesLeft,int whiteNoblesLeft,int cyanNoblesLeft, int pinkNoblesLeft,int magentaNoblesLeft,int orangeNoblesLeft){
		
		this.setBlackNoblesLeft(blackNoblesLeft);
		this.setWhiteNoblesLeft(whiteNoblesLeft);
		this.setCyanNoblesLeft(cyanNoblesLeft);
		this.setPinkNoblesLeft(pinkNoblesLeft);
		this.setMagentaNoblesLeft(magentaNoblesLeft);
		this.setOrangeNoblesLeft(orangeNoblesLeft);
	}
	
	public NoblesPool(){
		
		this.setBlackNoblesLeft(NoblesPoolConstants.MAX_NOBLES_FOR_COLOR);
		this.setWhiteNoblesLeft(NoblesPoolConstants.MAX_NOBLES_FOR_COLOR);
		this.setCyanNoblesLeft(NoblesPoolConstants.MAX_NOBLES_FOR_COLOR);
		this.setPinkNoblesLeft(NoblesPoolConstants.MAX_NOBLES_FOR_COLOR);
		this.setMagentaNoblesLeft(NoblesPoolConstants.MAX_NOBLES_FOR_COLOR);
		this.setOrangeNoblesLeft(NoblesPoolConstants.MAX_NOBLES_FOR_COLOR);
	}
	
	private void setBlackNoblesLeft(int blackNoblesLeft){ this.blackNoblesLeft = new Integer(blackNoblesLeft);}
	private void setWhiteNoblesLeft(int whiteNoblesLeft){ this.whiteNoblesLeft = new Integer(whiteNoblesLeft);}
	private void setCyanNoblesLeft(int cyanNoblesLeft){ this.cyanNoblesLeft = new Integer(cyanNoblesLeft); }
	private void setPinkNoblesLeft(int pinkNoblesLeft){ this.pinkNoblesLeft = new Integer(pinkNoblesLeft); }
	private void setMagentaNoblesLeft(int magentaNoblesLeft){ this.magentaNoblesLeft = new Integer(magentaNoblesLeft); }
	private void setOrangeNoblesLeft(int orangeNoblesLeft){ this.orangeNoblesLeft = new Integer(orangeNoblesLeft); }
	
	public void subBlackNoble() throws NoblesPoolException{
		if(this.getBlackNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_BLACK_NOBLES_AVAILABLE.getExceptionCode());
		else this.setBlackNoblesLeft(this.getBlackNoblesLeft() - 1);
	}
	
	public void subWhiteNoble() throws NoblesPoolException{
		if(this.getWhiteNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_WHITE_NOBLES_AVAILABLE.getExceptionCode());
		else this.setWhiteNoblesLeft(this.getWhiteNoblesLeft() - 1);
	}
	
	public void subCyanNoble() throws NoblesPoolException{
		if(this.getCyanNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_CYAN_NOBLES_AVAILABLE.getExceptionCode());
		else this.setCyanNoblesLeft(this.getCyanNoblesLeft() - 1);
	}
	
	public void subPinkNoble() throws NoblesPoolException{
		if(this.getPinkNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_PINK_NOBLES_AVAILABLE.getExceptionCode());
		else this.setPinkNoblesLeft(this.getPinkNoblesLeft() - 1); ;
	}
	
	public void subMagentaNoble() throws NoblesPoolException{
		if(this.getMagentaNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_MAGENTA_NOBLES_AVAILABLE.getExceptionCode());
		else this.setMagentaNoblesLeft(this.getMagentaNoblesLeft() - 1); ;
	}
	
	public void subOrangeNoble() throws NoblesPoolException{
		if(this.getOrangeNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_ORANGE_NOBLES_AVAILABLE.getExceptionCode());
		else this.setOrangeNoblesLeft(this.getOrangeNoblesLeft() - 1); ;
	}
	
	public void addBlackNoble() throws NoblesPoolException{
		if(this.getBlackNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_BLACK_NOBLES_BUFFER.getExceptionCode());
		else this.setBlackNoblesLeft(this.getBlackNoblesLeft() + 1);
	}
	
	public void addWhiteNoble() throws NoblesPoolException{
		if(this.getWhiteNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_WHITE_NOBLES_BUFFER.getExceptionCode());
		else this.setWhiteNoblesLeft(this.getWhiteNoblesLeft() + 1);
	}
	
	public void addCyanNoble() throws NoblesPoolException{
		if(this.getCyanNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_CYAN_NOBLES_BUFFER.getExceptionCode());
		else this.setCyanNoblesLeft(this.getCyanNoblesLeft() + 1);
	}
	
	public void addPinkNoble() throws NoblesPoolException{
		if(this.getPinkNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_PINK_NOBLES_BUFFER.getExceptionCode());
		else this.setPinkNoblesLeft(this.getPinkNoblesLeft() + 1);
	}
	
	public void addMagentaNoble() throws NoblesPoolException{
		if(this.getMagentaNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_MAGENTA_NOBLES_BUFFER.getExceptionCode());
		else this.setMagentaNoblesLeft(this.getMagentaNoblesLeft() + 1);
	}
	
	public void addOrangeNoble() throws NoblesPoolException{
		if(this.getOrangeNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_ORANGE_NOBLES_BUFFER.getExceptionCode());
		else this.setOrangeNoblesLeft(this.getOrangeNoblesLeft() + 1);
	}
	
	public int getBlackNoblesLeft(){ return this.blackNoblesLeft.intValue();}
	public int getWhiteNoblesLeft(){ return this.whiteNoblesLeft.intValue();}
	public int getCyanNoblesLeft(){ return this.cyanNoblesLeft.intValue(); }
	public int getPinkNoblesLeft(){ return this.pinkNoblesLeft.intValue(); }
	public int getMagentaNoblesLeft(){ return this.magentaNoblesLeft.intValue(); }
	public int getOrangeNoblesLeft(){ return this.orangeNoblesLeft.intValue(); }
	
	@Override
	public String toString(){
		String s = new String("\n");
		
		s += "black nobles left: " + this.getBlackNoblesLeft() + "\n";
		s += "white nobles left: " + this.getWhiteNoblesLeft() + "\n";
		s += "cyan nobles left: " + this.getCyanNoblesLeft() + "\n";
		s += "pink nobles left: " + this.getPinkNoblesLeft() + "\n";
		s += "magenta nobles left: " + this.getMagentaNoblesLeft() + "\n";
		s += "orange nobles left: " + this.getOrangeNoblesLeft() + "\n";
		
		return s;
	}
}
