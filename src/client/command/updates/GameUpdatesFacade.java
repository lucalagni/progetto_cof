package client.command.updates;

import model.basics.Match;
import commons.data.ActionSynoptic;
import commons.data.GameMode;
import commons.data.exceptions.UserDataException;
import client.command.updates.exceptions.GameUpdatesFacadeException;
import client.controller.ControllerRepository;

public class GameUpdatesFacade {
	private GameMode mode;
	
	@SuppressWarnings("static-access")
	public GameUpdatesFacade(){
		this.mode = ControllerRepository.getInstance().getClientController().getInstance().getClient().getGameMode();
	}
	
	/**
	 * Metodo che , in base al match code, richiede al server di chi e' il turno di gioco
	 * @return
	 * @throws GameUpdatesFacadeException
	 */
	public int getGamerTurn() throws GameUpdatesFacadeException{
		int gamer = 0;
		
		switch(this.mode){
		case SOCKET:
			gamer = new GameUpdatesSocket().getGamerTurn();
			break;
		case RMI:
			gamer = new GameUpdatesRmi().getGamerTurn();
		default:
			break;
		}
		
		return gamer;
	}
	
	/**
	 * Metodo che , in base al match code, richiede al server il match aggiornato
	 * @return
	 * @throws GameUpdatesFacadeException
	 * @throws UserDataException 
	 */
	public Match getMatch() throws GameUpdatesFacadeException, UserDataException{
		Match m = null ;
		
		switch(this.mode){
		case SOCKET :
			m = new GameUpdatesSocket().getMatch();
			break;
		default :
			break;
		}
		
		return m;
	}
	
	/**
	 * Metodo che, in base al match code ed allo username , ritorna il sinottico azioni corrispondente
	 * @return
	 * @throws GameUpdatesFacadeException
	 */
	public ActionSynoptic getActionSynoptic() throws GameUpdatesFacadeException{
		ActionSynoptic as = null;
		
		switch(this.mode){
		case SOCKET :
			as = new GameUpdatesSocket().getActionSynoptic();
			break;
		default :
			break;
		}
		
		return as;
	}
}
