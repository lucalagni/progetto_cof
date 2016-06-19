package controller.basic;

import java.util.Iterator;
import java.util.Map;

import model.basics.Gamer;
import model.basics.Match;
import model.basics.Village;
import model.basics.constants.MatchConstants;
import model.basics.constants.RegionConstants;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.VillageException;

public class StartGameController {
	
	public void twoGamersInitialSetup(Match match) throws VillageException, GamerException{
		if(match.getPositions().size() == MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY){
			char[] c = new char[3];
			Iterator<Map.Entry<Gamer, Integer>> it = match.getPositions().entrySet().iterator();
			Map.Entry<Gamer, Integer> entry = it.next();
			String fakeGamer = "FAKE" ;
			Village[] villages = match.getBoard().getGameMap().getVillages();
			while(it.hasNext()) fakeGamer += entry.getKey();
			
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
	
}
