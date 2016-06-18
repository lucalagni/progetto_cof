package model.basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import model.basics.constants.HelpersPoolConstants;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.codes.HelpersPoolExceptionCode;
import model.basics.supports.QuequedGamer;

public class HelpersPool implements Serializable{
	private static final long serialVersionUID = 1L;
	private int actualTotal;
	private int actualGamerHelpers;
	private ArrayList<QuequedGamer> queque;
	
	public HelpersPool(){
		this.setActualTotal(HelpersPoolConstants.MAX_HELPERS_NUMBER);
		this.setActualGamerHelpers(HelpersPoolConstants.FIRST_GAMER_HELPERS);
		this.queque = new ArrayList<QuequedGamer>();
	}
	
	public HelpersPool(int total, int actualGamerHelpers,ArrayList<QuequedGamer> queque){
		this.setActualTotal(total);
		this.setActualGamerHelpers(actualGamerHelpers);
		this.setQueque(queque);
	}
	
	public void subHelpers(int helpers) throws HelpersPoolException{
		if(this.getActualTotal() < helpers) throw new HelpersPoolException(HelpersPoolExceptionCode.INFERIOR_HELPERS_LIMIT_EXCEDED.getExceptionCode());
		this.setActualTotal(this.getActualTotal() - helpers);
	}
	
	private void setActualTotal(int total){ this.actualTotal = total; }
	private void setActualGamerHelpers(int actualGamerHelpers){ this.actualGamerHelpers = actualGamerHelpers; }
	private void setQueque(ArrayList<QuequedGamer> queque){ this.queque = queque; }
	
	public void addHelpers(int helpers) throws HelpersPoolException { 
		if(helpers < 0) throw new HelpersPoolException(HelpersPoolExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		this.setActualTotal(this.getActualTotal() + helpers); 
	}
	
	
	public boolean dequequeGamer(Gamer gamer) throws GamerException{
		if(this.queque.size() < 1) return false ;
		if(this.queque.get(0).getValue() < this.actualGamerHelpers) return false ;
		
		if(gamer.getUsername().equals(this.getQueque().get(0).getUsername())){
			gamer.addHelpers(this.queque.get(0).getValue());
			this.setActualGamerHelpers(this.getActualGamerHelpers() - this.queque.get(0).getValue());
			this.queque.remove(this.queque.get(0));
		}
		return false;
	}
	
	public int addGamer() throws HelpersPoolException{
		int newGamerHelpers = this.getActualGamerHelpers() + HelpersPoolConstants.NEXT_GAMER_HELPERS;
		int helpersLeft = this.getActualTotal() - newGamerHelpers;
		
		if(helpersLeft < HelpersPoolConstants.MIN_HELPERS_NUMBER) throw new HelpersPoolException(HelpersPoolExceptionCode.INFERIOR_HELPERS_LIMIT_EXCEDED.getExceptionCode());
		this.setActualGamerHelpers(newGamerHelpers);
		this.setActualTotal(helpersLeft);
		return this.getActualGamerHelpers();
	}
	
	public void quequeGamer(Gamer gamer,int helpers) throws HelpersPoolException{
		if(gamer == null) throw new HelpersPoolException(HelpersPoolExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(helpers <= 0) throw new HelpersPoolException(HelpersPoolExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		
		this.queque.add(new QuequedGamer(this.getQueque().size(), gamer.getUsername(), helpers));
	}
	
	public int getActualTotal(){ return this.actualTotal; }
	public int getActualGamerHelpers(){ return this.actualGamerHelpers; }
	public ArrayList<QuequedGamer> getQueque(){ return this.queque; }
	
	@Override
	public String toString(){
		Iterator<QuequedGamer> it = this.getQueque().iterator();
		QuequedGamer tmp = null;
		String helpersPoolString = new String("\nhelpers pool\n");
		
		helpersPoolString += "actual gamer helpers:"  + this.getActualGamerHelpers()+ "\n";
		helpersPoolString += "actual total helpers available:"  + this.getActualTotal()+ "\n";
		helpersPoolString += "Queque: \n";
		while(it.hasNext()){
			tmp = it.next();
			helpersPoolString += "position: " + tmp.getPosition() + " username: " + tmp.getUsername() + " value: " + tmp.getValue() +"\n";
		}
		
		return helpersPoolString ;
	}
}
