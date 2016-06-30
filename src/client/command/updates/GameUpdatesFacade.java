package client.command.updates;

import model.basics.Match;
import command.basic.actions.ActionSynoptic;
import commons.data.UserData;
import client.GameMode;
import client.command.updates.exceptions.GameUpdatesFacadeException;
import client.command.updates.exceptions.codes.GameUpdatesFacadeExceptionCode;
import client.controller.ControllerRepository;

public class GameUpdatesFacade {
	private GameMode mode;
	private UserData data;
	
	@SuppressWarnings("static-access")
	public GameUpdatesFacade(){
		this.mode = ControllerRepository.getInstance().getClientController().getInstance().getClient().getGameMode();
		this.data = ControllerRepository.getInstance().getGameDataController().getInstance().getUserData();
	}
	
	/**
	 * Metodo che , in base al match code, richiede al server di chi e' il turno di gioco
	 * @return
	 * @throws GameUpdatesFacadeException
	 */
	public int getGamerTurn() throws GameUpdatesFacadeException{
		if(this.data.getMatchCode() == null) throw new GameUpdatesFacadeException(GameUpdatesFacadeExceptionCode.NULL_MATCH_CODE.getExceptionCode());
		int gamer = 0;
		
		switch(this.mode){
		case SOCKET:
			gamer = new GameUpdatesSocket().getGamerTurn(this.data.getMatchCode());
			break;
		default:
			break;
		}
		
		return gamer;
	}
	
	/**
	 * Metodo che , in base al match code, richiede al server il match aggiornato
	 * @return
	 * @throws GameUpdatesFacadeException
	 */
	public Match getMatch() throws GameUpdatesFacadeException{
		if(this.data.getMatchCode() == null) throw new GameUpdatesFacadeException(GameUpdatesFacadeExceptionCode.NULL_MATCH_CODE.getExceptionCode());
		Match m = null ;
		
		switch(this.mode){
		case SOCKET :
			m = new GameUpdatesSocket().getMatch(this.data.getMatchCode());
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
		if(this.data.getMatchCode() == null) throw new GameUpdatesFacadeException(GameUpdatesFacadeExceptionCode.NULL_MATCH_CODE.getExceptionCode());
		if(this.data.getUsername() == null) throw new GameUpdatesFacadeException(GameUpdatesFacadeExceptionCode.NULL_USERNAME.getExceptionCode());
		ActionSynoptic as = null;
		
		switch(this.mode){
		case SOCKET :
			as = new GameUpdatesSocket().getActionSynoptic(this.data.getMatchCode(), this.data.getUsername());
			break;
		default :
			break;
		}
		
		return as;
	}
}
