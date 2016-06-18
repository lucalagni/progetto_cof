package model.basics;

import model.basics.constants.NoblesPoolConstants;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.codes.NoblesPoolExceptionCode;

public class NoblesPool {
	
	private int blackNoblesLeft;
	private int whiteNoblesLeft;
	private int cyanNoblesLeft;
	private int pinkNoblesLeft;
	private int magentaNoblesLeft ;
	private int orangeNoblesLeft;
	
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
	
	private void setBlackNoblesLeft(int blackNoblesLeft){ this.blackNoblesLeft = blackNoblesLeft;}
	private void setWhiteNoblesLeft(int whiteNoblesLeft){ this.whiteNoblesLeft = whiteNoblesLeft;}
	private void setCyanNoblesLeft(int cyanNoblesLeft){ this.cyanNoblesLeft = cyanNoblesLeft; }
	private void setPinkNoblesLeft(int pinkNoblesLeft){ this.pinkNoblesLeft = pinkNoblesLeft; }
	private void setMagentaNoblesLeft(int magentaNoblesLeft){ this.magentaNoblesLeft = magentaNoblesLeft; }
	private void setOrangeNoblesLeft(int orangeNoblesLeft){ this.orangeNoblesLeft = orangeNoblesLeft; }
	
	public void subBlackNoble() throws NoblesPoolException{
		if(this.getBlackNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_BLACK_NOBLES_AVAILABLE.getExceptionCode());
		else this.blackNoblesLeft-- ;
	}
	
	public void subWhiteNoble() throws NoblesPoolException{
		if(this.getWhiteNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_WHITE_NOBLES_AVAILABLE.getExceptionCode());
		else this.whiteNoblesLeft-- ;
	}
	
	public void subCyanNoble() throws NoblesPoolException{
		if(this.getCyanNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_CYAN_NOBLES_AVAILABLE.getExceptionCode());
		else this.cyanNoblesLeft-- ;
	}
	
	public void subPinkNoble() throws NoblesPoolException{
		if(this.getPinkNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_PINK_NOBLES_AVAILABLE.getExceptionCode());
		else this.pinkNoblesLeft-- ;
	}
	
	public void subMagentaNoble() throws NoblesPoolException{
		if(this.getMagentaNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_MAGENTA_NOBLES_AVAILABLE.getExceptionCode());
		else this.magentaNoblesLeft-- ;
	}
	
	public void subOrangeNoble() throws NoblesPoolException{
		if(this.getOrangeNoblesLeft() <= NoblesPoolConstants.MIN_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.NO_MORE_ORANGE_NOBLES_AVAILABLE.getExceptionCode());
		else this.orangeNoblesLeft-- ;
	}
	
	public void addBlackNoble() throws NoblesPoolException{
		if(this.getBlackNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_BLACK_NOBLES_BUFFER.getExceptionCode());
		else this.blackNoblesLeft++ ;
	}
	
	public void addWhiteNoble() throws NoblesPoolException{
		if(this.getWhiteNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_WHITE_NOBLES_BUFFER.getExceptionCode());
		else this.whiteNoblesLeft++ ;
	}
	
	public void addCyanNoble() throws NoblesPoolException{
		if(this.getCyanNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_CYAN_NOBLES_BUFFER.getExceptionCode());
		else this.cyanNoblesLeft++ ;
	}
	
	public void addPinkNoble() throws NoblesPoolException{
		if(this.getPinkNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_PINK_NOBLES_BUFFER.getExceptionCode());
		else this.pinkNoblesLeft++ ;
	}
	
	public void addMagentaNoble() throws NoblesPoolException{
		if(this.getMagentaNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_MAGENTA_NOBLES_BUFFER.getExceptionCode());
		else this.magentaNoblesLeft++ ;
	}
	
	public void addOrangeNoble() throws NoblesPoolException{
		if(this.getOrangeNoblesLeft() > NoblesPoolConstants.MAX_NOBLES_FOR_COLOR) throw new NoblesPoolException(NoblesPoolExceptionCode.FULL_ORANGE_NOBLES_BUFFER.getExceptionCode());
		else this.orangeNoblesLeft++ ;
	}
	
	public int getBlackNoblesLeft(){ return this.blackNoblesLeft;}
	public int getWhiteNoblesLeft(){ return this.whiteNoblesLeft;}
	public int getCyanNoblesLeft(){ return this.cyanNoblesLeft; }
	public int getPinkNoblesLeft(){ return this.pinkNoblesLeft; }
	public int getMagentaNoblesLeft(){ return this.magentaNoblesLeft; }
	public int getOrangeNoblesLeft(){ return this.orangeNoblesLeft; }
	
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
