package model.basics.builders;

import java.util.ArrayList;

import model.basics.HelpersPool;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.HelpersPoolConstants;
import model.basics.exceptions.codes.HelpersPoolExceptionCode;
import model.basics.supports.QuequedGamer;

public class HelpersPoolBuilder {
	private int actualTotal;
	private ArrayList<QuequedGamer> queque;
	private int actualGamerHelpers;
	
	public HelpersPoolBuilder setActualGamerHelpers(int helpers){
		this.actualGamerHelpers = helpers;
		return this;
	}
	
	public HelpersPoolBuilder setActualTotal(int actualTotal){
		this.actualTotal = actualTotal;
		return this;
	}
	
	public HelpersPoolBuilder setQueque(ArrayList<QuequedGamer> queque){
		this.queque = queque;
		return this;
	}
	
	public HelpersPool build() throws BuilderException{
		if(this.actualTotal < HelpersPoolConstants.MIN_HELPERS_NUMBER) throw new BuilderException(HelpersPoolExceptionCode.INFERIOR_HELPERS_LIMIT_EXCEDED.getExceptionCode());
		if(this.actualTotal > HelpersPoolConstants.MAX_HELPERS_NUMBER) throw new BuilderException(HelpersPoolExceptionCode.SUPERIOR_HELPERS_LIMIT_EXCEDED.getExceptionCode());
		if(this.actualGamerHelpers < HelpersPoolConstants.MIN_HELPERS_NUMBER) throw new BuilderException(HelpersPoolExceptionCode.INFERIOR_HELPERS_LIMIT_EXCEDED.getExceptionCode());
		if(this.actualGamerHelpers > HelpersPoolConstants.MAX_HELPERS_NUMBER) throw new BuilderException(HelpersPoolExceptionCode.SUPERIOR_HELPERS_LIMIT_EXCEDED.getExceptionCode());
		if(this.queque == null) this.queque = new ArrayList<QuequedGamer>();
		
		return new HelpersPool(this.actualTotal, this.actualGamerHelpers, this.queque);
	}
}
