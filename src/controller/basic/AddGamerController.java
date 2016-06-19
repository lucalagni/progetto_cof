package controller.basic;

import server.manager.MatchGenerator;
import server.manager.MatchRepository;
import model.basics.Match;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;

public class AddGamerController {
	private MatchGenerator mg;
	
	public AddGamerController() throws BuilderException, GameMapException{ 
			mg = new MatchGenerator();
	}
	
	public void addGamer(String username) throws PoliticalCardsDeckException{
		mg.addGamer(username);
	}
	
	public String done() throws BuilderException, PoliticalCardsDeckException, GameMapException, MatchException{
		if(mg.getGamersNumber() < MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY) {
			System.out.println("\nToo feaw gamers to play");
			return null;
		}
		Match m = mg.createMatch();
		MatchRepository.getInstance().addMatch(m);
		System.out.println(m.toString());
		return m.getMatchCode();
	}
}
