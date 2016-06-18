package model.basics.builders;

import java.awt.Color;
import java.util.ArrayList;

import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.GamerConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.exceptions.codes.GamerExceptionCode;
import model.basics.supports.GamerStatus;

public class GamerBuilder {

	private String username;
	private Color color;
	private int helpers;
	private int points;
	private int shifts;
	private int shops;
	private int coins;
	private String match;
	private ArrayList<PoliticalCard> politicalCards;
	private ArrayList<PermitCard> unusedPermitCards;
	private ArrayList<PermitCard> usedPermitCards;
	private GamerStatus status;
	
	public GamerBuilder(){
		this.username = null;
		this.color = null;
		this.helpers = -1;
		this.coins = -1;
		this.match = null;
		this.politicalCards = null;
	}
	
	public Gamer build() throws BuilderException{
		boolean flag = false;
		if(color == null) throw new BuilderException(GamerExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		for(Color c : GamerConstants.GAMERS_COLORS) if(c.equals(color)) flag = true;
		if(flag == false)throw new BuilderException(GamerExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(username == null) throw new BuilderException(GamerExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(username.length() == 0) throw new BuilderException(GamerExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		if(helpers < HelpersPoolConstants.MIN_HELPERS_NUMBER_FOR_GAMER)throw new BuilderException(GamerExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
		if(helpers > HelpersPoolConstants.MAX_HELPERS_NUMBER_FOR_GAMER)throw new BuilderException(GamerExceptionCode.TOO_MANY_HELPERS.getExceptionCode());
		if(coins < CoinsPoolConstants.MIN_NUMBER_OF_COINS_FOR_GAMER)throw new BuilderException(GamerExceptionCode.TOO_FEAW_COINS.getExceptionCode());
		if(coins > CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER)throw new BuilderException(GamerExceptionCode.TOO_MANY_COINS.getExceptionCode());
		if(match == null)throw new BuilderException(GamerExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		//if(politicalCards == null) throw new BuilderException(GamerExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		//if(politicalCards.toArray().length != GamerConstants.INITIAL_NUMBER_OF_CARDS)throw new BuilderException(GamerExceptionCode.INVALID_INPUT_DATA.getExceptionCode());
		//if(this.unusedPermitCards == null) this.setUnusedPermitCards(new HashSet<PermitCard>());
		//if(this.usedPermitCards == null) this.setUsedPermitCards(new HashSet<PermitCard>());
		
		return new Gamer(this.username, this.color, this.coins, this.points, this.shifts, this.shops,this.helpers, this.match, this.politicalCards, this.usedPermitCards , this.unusedPermitCards, this.status);
	
	}
	
	public GamerBuilder setUnusedPermitCards(ArrayList<PermitCard> unusedPermitCards) { 
		this.unusedPermitCards = unusedPermitCards;
		return this;
	}
	public GamerBuilder setUsedPermitCards(ArrayList<PermitCard> usedPermitCards) { 
		this.usedPermitCards = usedPermitCards; 
		return this;
	}
	
	
	public GamerBuilder setStatus(GamerStatus status){ 
		this.status = status;
		return this;
	}
	
	public GamerBuilder setShops(int shops){ 
		this.shops = shops;
		return this;
	}
	
	public GamerBuilder setShifts(int shifts){ 
		this.shifts = shifts;
		return this;
	}
	
	public GamerBuilder setPoints(int points){ 
		this.points = points;
		return this;
	}
	
	public GamerBuilder setUsername(String username){ 
		this.username = username;
		return this;
	}
	public GamerBuilder setColor(Color color){ 
		this.color = color;
		return this;
	}
	public GamerBuilder setHelpers(int helpers){ 
		this.helpers = helpers; 
		return this;
	}
	public GamerBuilder setCoins(int coins){
		this.coins = coins;
		return this;
	}
	public GamerBuilder setMatch(String match){ 
		this.match = match; 
		return this;
	}
	public GamerBuilder setPoliticalCards(ArrayList<PoliticalCard> politicalCards){ 
		this.politicalCards = politicalCards;
		return this;
	}

}
