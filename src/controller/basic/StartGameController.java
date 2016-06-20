package controller.basic;

import server.manager.MatchRepository;
import model.basics.Match;
import model.basics.Village;
import model.basics.constants.GamerConstants;
import model.basics.constants.MatchConstants;
import model.basics.constants.RegionConstants;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.VillageException;

public class StartGameController {
	
	public int startGame(String matchCode){
		Match m = MatchRepository.getInstance().getMatch(matchCode);
		
		if(m.getMatchCode().equals(MatchConstants.MATCH_NOT_CREATED)) return -1;
		
		if(m.getGamers().size() == MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY)
			try {
				
				this.twoGamersInitialSetup(m);
				
			} catch (VillageException | GamerException e) {
				e.printStackTrace();
		}
		return m.getActualGamer();
	}
	
	private void twoGamersInitialSetup(Match match) throws VillageException, GamerException{
			char[] c = new char[3];
			String fakeGamer = GamerConstants.FAKE_GAMER;
			Village[] villages = match.getBoard().getGameMap().getVillages();
			int random = (int) (Math.random() * RegionConstants.VILLAGES_REGION_1.length); 
			
			c[0] = RegionConstants.VILLAGES_REGION_1[random].charAt(0);
			random = (int) (Math.random() * RegionConstants.VILLAGES_REGION_2.length); 
			c[1] = RegionConstants.VILLAGES_REGION_2[random].charAt(0);
			random = (int) (Math.random() * RegionConstants.VILLAGES_REGION_3.length); 
			c[2] = RegionConstants.VILLAGES_REGION_3[random].charAt(0);
			
			for(int i = 0; i < villages.length; i++){
				if(villages[i].getFirstLetter() == c[0]) villages[i].addShop(fakeGamer);
				if(villages[i].getFirstLetter() == c[1]) villages[i].addShop(fakeGamer);
				if(villages[i].getFirstLetter() == c[2]) villages[i].addShop(fakeGamer);
			}
		
	}
	
}
