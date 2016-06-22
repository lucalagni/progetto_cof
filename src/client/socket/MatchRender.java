package client.socket;

import client.exception.MatchRenderException;
import client.exception.codes.MatchRenderExceptionCode;
import server.managers.match.MatchRepository;
import model.basics.Council;
import model.basics.GameMap;
import model.basics.Gamer;
import model.basics.HelpersPool;
import model.basics.Match;
import model.basics.NobiltyPath;
import model.basics.NoblesPool;
import model.basics.PermitCard;
import model.basics.Village;
import model.basics.constants.RegionConstants;

public class MatchRender {
	private Match match = null;
	private Gamer gamer = null;
	
	public MatchRender(String matchCode,int gamerID) throws MatchRenderException{
		if((this.match = MatchRepository.getInstance().getMatch(matchCode)) == null) throw new MatchRenderException(MatchRenderExceptionCode.MATCH_NOT_FOUND.getExceptionCode());
		if((gamerID < 0) || (gamerID >= this.match.getGamers().size())) throw new MatchRenderException(MatchRenderExceptionCode.GAMER_NOT_IN_MATCH.getExceptionCode());
		
		this.gamer = this.match.getGamers().get(gamerID);
	}
	
	public int getActualGamer(){ return this.match.getActualGamer(); }
	public int[][] getConnections(){ return this.match.getBoard().getGameMap().getConnections(); }
	public GameMap getMap(){ return this.match.getBoard().getGameMap();}
	public NobiltyPath getNobiltyPath(){ return this.match.getBoard().getNobiltyPath(); }
	public Village getVillage(char firstLetter) throws MatchRenderException{
		for(Village tmp: this.match.getBoard().getGameMap().getVillages()) if(tmp.getFirstLetter() == firstLetter) return tmp;
		throw new MatchRenderException(MatchRenderExceptionCode.VILLAGE_NOT_VALID.getExceptionCode());
	}
	
	public Council getCouncil(int regionNumber) throws MatchRenderException{
		this.checkRegionNumber(regionNumber);
		return this.match.getBoard().getRegions()[regionNumber].getCouncil();
	}
	
	private void checkRegionNumber(int regionNumber) throws MatchRenderException{if((regionNumber < 0) || (regionNumber >= RegionConstants.NUMBER_OF_REGIONS))throw new MatchRenderException(MatchRenderExceptionCode.REGION_NOT_VALID.getExceptionCode()); }
	
	public NoblesPool getCouncilPool(){ return this.match.getBoard().getNoblesPool(); }
	public HelpersPool getHelpersPool(){ return this.match.getBoard().getHelpersPool(); }
	public Gamer getGamer(){ return this.gamer; }
	public Council getKingCouncil(){ return this.match.getBoard().getKing().getCouncil(); }
	public PermitCard[] getUnhiddenPermitCards(int regionNumber) throws MatchRenderException{
		this.checkRegionNumber(regionNumber);
		return this.match.getBoard().getRegions()[regionNumber].getPermitCardsDeck().getUnhiddenCards();
	}
}
