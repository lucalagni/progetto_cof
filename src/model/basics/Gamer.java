package model.basics;

import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.GamerConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.constants.NobiltyPathConstants;
import model.basics.exceptions.BonusException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.exceptions.codes.GamerExceptionCode;
import model.basics.supports.GamerStatus;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe che contiene tutti i dati di interesse del giocatore
 * 
 * @author Luca Lagni
 *
 */
public class Gamer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private Color color;
	private Integer helpers;
	private Integer coins;
	private Integer points;
	private Integer shifts;
	private Integer shops;
	private String matchCode;
	private GamerStatus status;
	private ArrayList<PoliticalCard> politicalCards;
	private ArrayList<PermitCard> unusedPermitCards;
	private ArrayList<PermitCard> usedPermitCards;
	
	
	public Gamer(String username,Color color, int coins,int points,int shifts, int shops,int helpers,String matchCode, GamerStatus status, ArrayList<PoliticalCard> politicalCards){
		this.setUsername(username);
		this.setColor(color);
		this.setMatch(matchCode);
		this.setStatus(status);
		this.setHelpers(helpers);
		this.setCoins(coins);
		this.setPoints(points);
		this.setShifts(shifts);
		this.setShops(shops);
		this.setPoliticalCards(politicalCards);
		this.setUnusedPermitCards(new ArrayList<PermitCard>());
		this.setUsedPermitCards(new ArrayList<PermitCard>());
	}
	
	public Gamer(String username, Color color, int coins, int points,
			int shifts, int shops, int helpers, String match,
			ArrayList<PoliticalCard> politicalCards,
			ArrayList<PermitCard> usedPermitCards,
			ArrayList<PermitCard> unusedPermitCards, GamerStatus status) {
		this(username, color, coins, points, shifts, shops, helpers, match, status, politicalCards);
		
		this.setUnusedPermitCards(unusedPermitCards);
		this.setUsedPermitCards(usedPermitCards);
	}
	
	protected void setUsername(String username) { this.username = username; }
	protected void setColor(Color color) { this.color = color; }
	protected void setHelpers(int helpers) { this.helpers = new Integer(helpers); }
	protected void setCoins(int coins) { this.coins = new Integer(coins); }
	protected void setPoints(int points) { this.points = new Integer(points); }
	protected void setShifts(int shifts){ this.shifts = new Integer(shifts); }
	protected void setShops(int shops) { this.shops = new Integer(shops); }
	protected void setMatch(String match) { this.matchCode = match; }
	protected void setStatus(GamerStatus status) { this.status = status; }
	
	public String getUsername() { return this.username; }
	public Color getColor() { return this.color; }
	public int getHelpers() { return this.helpers.intValue(); }
	public int getCoins() { return this.coins.intValue(); }
	public int getPoints() { return this.points.intValue(); }
	public int getShops() { return this.shops.intValue(); }
	public String getMatch() { return this.matchCode; }
	public GamerStatus getStatus() { return this.status; }
	public int getShifts(){ return this.shifts.intValue(); }
	
	private void setPoliticalCards(ArrayList<PoliticalCard> politicalCards){ this.politicalCards = politicalCards; }
	private void setUnusedPermitCards(ArrayList<PermitCard> unusedPermitCards){ this.unusedPermitCards = unusedPermitCards; }
	private void setUsedPermitCards(ArrayList<PermitCard> usedPermitCards){ this.usedPermitCards = usedPermitCards; }
	
	public ArrayList<PoliticalCard> getPoliticalCards() { return this.politicalCards; }
	public ArrayList<PermitCard> getUnusedPermitCards() {return unusedPermitCards; }
	public ArrayList<PermitCard> getUsedPermitCards() { return usedPermitCards; }
	
	public void addPermitCard(PermitCard permitCard){ this.getUnusedPermitCards().add(permitCard); }
	public void addPoliticalCard(PoliticalCard politicalCard){ this.politicalCards.add(politicalCard); }
	
	private void swapPermitCardInSet(PermitCard permitCard, ArrayList<PermitCard> oldSet, ArrayList<PermitCard> newSet)throws GamerException{
		Iterator<PermitCard> it = oldSet.iterator();
		
		if(it.hasNext() == false) throw new GamerException(GamerExceptionCode.EMPTY_PERMIT_CARD_SET.getExceptionCode());
		while(it.hasNext()){
			if(it.next() == permitCard){
				it.remove();
				newSet.add(permitCard);
				return ;
			}
		}
		
		throw new GamerException(GamerExceptionCode.PERMIT_CARD_NOT_FOUND.getExceptionCode());
	}
	
	public PermitCard subPermitCard(PermitCard permitCard) throws GamerException{
		for(int i = 0; i < this.unusedPermitCards.size(); i++){
			if(this.unusedPermitCards.get(i).equals(permitCard)) return this.unusedPermitCards.remove(i);
		}
		return null;
	}
	
	public void usePermitCard(PermitCard permitCard) throws GamerException{
		this.swapPermitCardInSet(permitCard, this.unusedPermitCards, this.usedPermitCards);
	}
	
	public void useBonus(Bonus bonus,PoliticalCardsDeck politicalCardsDeck) throws PoliticalCardsDeckException, BonusException{
		this.setCoins(this.getCoins() + bonus.getCoins());
		this.setPoints(this.getPoints() + bonus.getPoints());
		this.setHelpers(this.getHelpers() + bonus.getHelpers());
		this.setShifts(this.getShifts() + bonus.getShifts());
		if(bonus.getPoliticalCards() > 0) for(int i = 0; i < bonus.getPoliticalCards(); i++)this.addPoliticalCard(politicalCardsDeck.pickupCard());
		
	}
	
	public void toggleOnline(){ this.setStatus(GamerStatus.ONLINE); }
	public void toggleOffline(){ this.setStatus(GamerStatus.OFFLINE); }
	
	public PoliticalCard subPoliticalCard(PoliticalCard pc)  {
		for(int i = 0; i < this.politicalCards.size(); i++){
			if(this.politicalCards.get(i).equals(pc)) return this.politicalCards.remove(i);
		}
		return null;
	}
	
	public PoliticalCard subPoliticalCard(int position)  {
		PoliticalCard pc = this.politicalCards.remove(position);
		System.out.println(pc.toString());
		return pc;
	}
	
	
	public void addCoins(int coins) throws GamerException{
		int cns = this.coins.intValue();
		cns += coins ;
		if(cns > CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER) {
			this.setCoins(CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER);
			throw new GamerException(GamerExceptionCode.TOO_MANY_COINS.getExceptionCode());
		}
		else this.setCoins(cns);
	} 
	
	public void subCoins(int coins) throws GamerException{
		if((this.getCoins() - coins) < CoinsPoolConstants.MIN_NUMBER_OF_COINS_FOR_GAMER) {
			throw new GamerException(GamerExceptionCode.TOO_FEAW_COINS.getExceptionCode());
		}
		else this.setCoins(this.getCoins() - coins);
	}
	
	public void addHelpers(int helpers) throws GamerException{
		if((this.getHelpers() + helpers) > HelpersPoolConstants.MAX_HELPERS_NUMBER_FOR_GAMER) {
			this.setHelpers(HelpersPoolConstants.MAX_HELPERS_NUMBER_FOR_GAMER);
			throw new GamerException(GamerExceptionCode.TOO_MANY_HELPERS.getExceptionCode());
		}
		else this.setHelpers(this.getHelpers() + helpers);
	}
	
	public int subHelpers(int helepers) throws GamerException{
		if((this.getHelpers() - helpers) < HelpersPoolConstants.MIN_HELPERS_NUMBER_FOR_GAMER) {
			throw new GamerException(GamerExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
		}
		else this.setHelpers(this.getHelpers() - helpers);
		return helepers;
	}
	
	public void subShop() throws GamerException{
		if((this.getShops() - 1) < GamerConstants.MIN_NUMBER_OF_SHOPS){
			throw new GamerException(GamerExceptionCode.ENDED_GAMER_SHOPS.getExceptionCode());
		}
		else this.setShops(this.getShops() - 1);
	}
	
	public void addShifts(int shifts) throws GamerException{
		if((this.getShifts() + shifts) > NobiltyPathConstants.TOTAL_TILES){
			this.setShifts(NobiltyPathConstants.TOTAL_TILES);
			throw new GamerException(GamerExceptionCode.ENDED_NOBILTY_PATH.getExceptionCode());
		}
		else this.setShifts(this.getShifts() + shifts);
		
	}
	
	public void addPoints(int points){ this.setPoints(this.getPoints() + points); }
	
	@Override
	public String toString()
	{
		
		Iterator<PoliticalCard> itpc = this.getPoliticalCards().iterator();
		Iterator<PermitCard> itec = this.getUnusedPermitCards().iterator();
		String gString = new String("\ngamer\n");
		
		gString += "username: " + this.getUsername() + "\n";
		gString += "color: " + this.getColor().toString() + "\n";
		gString += "coins: " + this.getCoins() + "\n";
		gString += "helpers: " + this.getHelpers() + "\n";
		gString += "points: " + this.getPoints() + "\n";
		gString += "shifts: " + this.getShifts() + "\n";
		gString += "shops: " + this.getShops() + "\n";
		gString += "statis: " + this.getStatus().getGamerStatus() + "\n";
		gString += "match: " + this.getMatch().toString() + "\n";
		gString += "political cards:\n";
		while(itpc.hasNext()) gString += itpc.next() + "\n";
		gString += "unhused permit cards:\n";
		while(itec.hasNext()) gString += itpc.next() + "\n";
		itec = this.getUsedPermitCards().iterator();
		gString += "used permit cards:\n";
		while(itec.hasNext()) gString += itpc.next() + "\n";
		
		return gString;
	}
	
}
