package examples.example1;

import model.basics.HelpersPool;
import model.basics.builders.HelpersPoolBuilder;
import model.basics.constants.HelpersPoolConstants;

public class HelpersPoolExample {
	private HelpersPool helpersPool;
	
	public HelpersPoolExample(){
		this.helpersPool = new HelpersPoolBuilder().setActualTotal(HelpersPoolConstants.MAX_HELPERS_NUMBER - HelpersPoolConstants.FIRST_GAMER_HELPERS)
												   .setActualGamerHelpers(HelpersPoolConstants.FIRST_GAMER_HELPERS)
												   .setQueque(null)
												   .build();
	}
	
	public HelpersPool getHelpersPool(){ return this.helpersPool; }
}
