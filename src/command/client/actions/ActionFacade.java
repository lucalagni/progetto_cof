package command.client.actions;

import java.awt.Color;

import command.client.actions.exceptions.ActionFacadeException;
import command.client.actions.exceptions.codes.ActionFacadeExceptionCode;
import controller.ControllerRepository;
import client.GameData;
import client.GameMode;

/**
 * Classe che contiene la facciata per l'esecuzione delle azioni del match
 * in modo indipendente dalla modalità di comunicazione
 * 
 * @author Luca Lagni
 *
 */
public class ActionFacade {
	private GameData data;
	private GameMode mode;
	
	public ActionFacade(GameData data){
		this.setGameData(data);
		this.setGameMode(ControllerRepository.getInstance().getClientController().getClient().getGameMode());
	}
	
	private void setGameData(GameData data){ this.data = data; }
	private void setGameMode(GameMode mode){ this.mode = mode; }
	
	public void moveKing(char path[]) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).moveKing(path);
		}
	}
	
	public void changeNoble(boolean king,int regionNumber,Color noble, boolean mainAction) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(mainAction == true)if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		else if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_HELPERS_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).changeNoble(king, regionNumber, noble, mainAction);
		}
	}
	
	public void buyPermitCard(int regionNumber,int permitCardNumber,int[] politicalCardsIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).buyPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
		}
	}
	
	public void placeShop(char village,int permitCardIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).placeShop(village, permitCardIndex);
		}
	} 
	
	public void buyHelper(boolean queque) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).buyHelper(queque);
		}
	} 
	
	public void buyNewMainAction() throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).buyNewMainAction();
		}
	} 
	
	public void doubleAction(int regionNumber) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).doubleAction(regionNumber);
		}
	} 
	
	public void acquirePermitCard(int regionNumber,int permitCardIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquirePermitCardNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).acquirePermitCard(regionNumber, permitCardIndex);
		}
	} 
	
	public void acquireSingleVillageBonus(char villageFirstLetter) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquireSingleVillageBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).acquireSingleVillageBonus(villageFirstLetter);
		}
	} 
	
	public void acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquireDoubleVillageBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).acquireDoubleVillageBonus(firstVillageFirstLetter, secondVillageFirstLetter);
		}
	} 
	
	public void reusePermitCardBonus(int permitCardIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getReusePermitCardBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getGameData().getMatchCode()).reusePermitCardBonus(permitCardIndex);
		}
	} 
	
	public GameData getGameData(){ return this.data; }
	public GameMode getGameMode(){ return this.mode; }
}
