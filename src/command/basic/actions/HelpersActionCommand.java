package command.basic.actions;

import java.awt.Color;

import command.basic.actions.exceptions.HelpersActionCommandException;
import command.basic.actions.exceptions.MainActionCommandException;
import command.basic.actions.exceptions.codes.HelpersActionCommandExceptionCode;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.constants.ColorConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.constants.PermitCardsDeckConstants;
import model.basics.constants.RegionConstants;
import model.basics.exceptions.CouncilException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.PermitCardsDeckException;

public class HelpersActionCommand {
	private Gamer gamer;
	private Match match;
	private int newMainAction;
	private int virtualHelpers ;
	private int virtualCoins ;
	
	public HelpersActionCommand(Gamer gamer,Match match,int virtualHelpers,int virtualCoins){
		this.setGamer(gamer);
		this.setMatch(match);
		this.setVirtualHelpers(virtualHelpers);
		this.setVirtualCoins(virtualCoins);
		this.setNewMainAction(0);
	}
	
	private void setMatch(Match match){ this.match = match; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setVirtualHelpers(int virtualHelpers){ this.virtualHelpers = virtualHelpers; }
	private void setVirtualCoins(int virtualCoins){ this.virtualCoins = virtualCoins; }
	private void setNewMainAction(int newMainAction){ this.newMainAction = newMainAction; }
	
	public boolean buyHelpers(int helpers, boolean queque) throws  HelpersPoolException, GamerException, HelpersActionCommandException{
		int realPrice = 0;
		
		if(helpers <= HelpersPoolConstants.MIN_HELPERS_NUMBER) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
		if(this.match.getBoard().getHelpersPool().getActualTotal() < HelpersPoolConstants.MIN_HELPERS_NUMBER){
			if(queque == true) {
				this.match.getBoard().getHelpersPool().quequeGamer(this.gamer, helpers);
				return false;
			}
			else throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
		}
		
		if(this.virtualCoins < (HelpersPoolConstants.HELPERS_COINS_PRICE * helpers)) {
			if((this.virtualCoins + this.gamer.getCoins()) < (HelpersPoolConstants.HELPERS_COINS_PRICE * helpers)) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
			else{
				realPrice = HelpersPoolConstants.HELPERS_COINS_PRICE * helpers - this.virtualCoins;
				this.setVirtualCoins(0);
				this.gamer.subCoins(realPrice);
			}
		}
		else {
			this.setVirtualCoins(this.getVirtualCoins() - HelpersPoolConstants.HELPERS_COINS_PRICE * helpers);
		}
		
		this.gamer.addHelpers(helpers);
		this.match.getBoard().getHelpersPool().subHelpers(helpers);
		return true;
	}
	
	public void changeNoble(boolean isKing,int regionNumber,Color noble) throws MainActionCommandException, CouncilException, NoblesPoolException, GamerException, HelpersActionCommandException{
		if((regionNumber >= RegionConstants.NUMBER_OF_REGIONS)||(regionNumber < 0)) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		boolean flag = false;
		Color old = null;
		
		for(Color c: ColorConstants.POLITICAL_COLORS) if(c.equals(noble)) flag = true;
		if(flag == false) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.INVALID_NOBLE_COLOR.getExceptionCode());
		
		if(isKing == true) old = this.match.getBoard().getKing().getCouncil().slideNoble(noble);
		else old = this.match.getBoard().getRegions()[regionNumber].getCouncil().slideNoble(noble);
		
		if(noble.equals(ColorConstants.POLITICAL_COLORS[0])) this.match.getBoard().getNoblesPool().subBlackNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[1])) this.match.getBoard().getNoblesPool().subCyanNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[2])) this.match.getBoard().getNoblesPool().subPinkNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[3])) this.match.getBoard().getNoblesPool().subMagentaNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[4])) this.match.getBoard().getNoblesPool().subOrangeNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[5])) this.match.getBoard().getNoblesPool().subWhiteNoble();
		
		if(old.equals(ColorConstants.POLITICAL_COLORS[0])) this.match.getBoard().getNoblesPool().addBlackNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[1])) this.match.getBoard().getNoblesPool().addCyanNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[2])) this.match.getBoard().getNoblesPool().addPinkNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[3])) this.match.getBoard().getNoblesPool().addMagentaNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[4])) this.match.getBoard().getNoblesPool().addOrangeNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[5])) this.match.getBoard().getNoblesPool().addWhiteNoble();
	}
	
	public void doubleAction(int regionNumber) throws GamerException, HelpersActionCommandException, PermitCardsDeckException{
		if((regionNumber < 0) ||(regionNumber >= RegionConstants.NUMBER_OF_REGIONS))throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		
		if(this.virtualHelpers < PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS){
			if(this.gamer.getHelpers() + this.virtualHelpers < PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
			else {
				this.gamer.subHelpers(PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS - this.virtualHelpers);
				this.virtualHelpers = 0;
			}
		}
		else this.virtualHelpers = this.virtualHelpers - PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS;
		
		this.match.getBoard().getRegions()[regionNumber].getPermitCardsDeck().doubleAction();
	}
	
	public void buyNewMainAction() throws HelpersActionCommandException, GamerException, HelpersPoolException{
		if(this.virtualHelpers < HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION){
			if(this.gamer.getHelpers() + this.virtualHelpers < HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
			else {
				this.gamer.subHelpers(HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION - this.virtualHelpers);
				this.virtualHelpers = 0;
			}
		}
		else this.virtualHelpers = this.virtualHelpers - HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION;
		
		this.match.getBoard().getHelpersPool().addHelpers(HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION);
		
		this.newMainAction++;
	}
	
	public Match getMatch(){ return this.match; }
	public Gamer getGamer(){ return this.gamer; }
	public int getVirtualHelpers(){ return this.virtualHelpers ; }
	public int getVirtualCoins(){ return this.virtualCoins; }
	public int getNewMainAction(){ return this.newMainAction; }
}
