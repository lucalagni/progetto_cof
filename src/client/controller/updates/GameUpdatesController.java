package client.controller.updates;

import model.basics.Match;
import model.basics.constants.MatchConstants;
import command.basic.actions.ActionSynoptic;
import client.command.updates.GameUpdatesFacade;
import client.command.updates.exceptions.GameUpdatesFacadeException;

/**
 * Controller per la ricerca di aggiornamenti
 * @author lucal
 *
 */
public class GameUpdatesController {
	private static GameUpdatesController instance = null;
	public static final int GAMER_TURN_FAILURE_CODE = MatchConstants.MAX_NUMBER_OF_GAMERS + 1;
	public static final Match MATCH_FAILURE = null;
	public static final ActionSynoptic ACTION_SYNOPTIC_FAILURE = null;
	
	private GameUpdatesFacade updates = null;
	
	private GameUpdatesController(){this.updates = new GameUpdatesFacade();}
	
	
	public int getGamerTurn() {
		try {
			return this.updates.getGamerTurn();
		} catch (GameUpdatesFacadeException e) {
			return GAMER_TURN_FAILURE_CODE;
		}
	}
	
	
	public Match getMatch(){
		try {
			return this.updates.getMatch();
		} catch (GameUpdatesFacadeException e) {
			return MATCH_FAILURE ;
		}
	}
	
	public ActionSynoptic getActionSynoptic(){
		try {
			return this.updates.getActionSynoptic();
		} catch (GameUpdatesFacadeException e) {
			return ACTION_SYNOPTIC_FAILURE;
		}
	}
	
	public static GameUpdatesController getInstance(){
		if(instance == null) instance = new GameUpdatesController();
		return instance ;
	}
}
