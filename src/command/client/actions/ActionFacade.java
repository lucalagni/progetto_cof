package command.client.actions;

import java.awt.Color;
import java.util.ArrayList;

import command.client.actions.exceptions.ActionFacadeException;
import command.client.actions.exceptions.codes.ActionFacadeExceptionCode;
import commons.data.UserData;
import client.controller.ControllerRepository;
import client.GameMode;

/**
 * Classe che contiene la facciata per l'esecuzione delle azioni del match
 * in modo indipendente dalla modalità di comunicazione
 * 
 * @author Luca Lagni
 *
 */
public class ActionFacade {
	private UserData data;
	private GameMode mode;
	
	public ActionFacade() throws ActionFacadeException{
		this.setUserData(ControllerRepository.getInstance().getGameDataController().getUserData());
		this.setGameMode(ControllerRepository.getInstance().getClientController().getClient().getGameMode());
	}
	
	private void setUserData(UserData data) throws ActionFacadeException{ 
		if(data == null) throw new ActionFacadeException(ActionFacadeExceptionCode.UNSETTED_USER_DATA.getExceptionCode());
		this.data = data; 
	}
	private void setGameMode(GameMode mode) throws ActionFacadeException{ 
		if(mode == null) throw new ActionFacadeException(ActionFacadeExceptionCode.UNSETTED_GAME_MODE.getExceptionCode());
		this.mode = mode; 
	}
	
	/**
	 * Metodo per l'esecuzione della mossa di movimento del re
	 * @param path
	 * @throws ActionFacadeException
	 */
	public void moveKing(ArrayList<String> path,int politicalCardsPosition[]) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).moveKing(path);
		}
	}
	
	/**
	 * Metodo per l'esecuzione della mossa eleggi consigliere
	 * @param king
	 * @param regionNumber
	 * @param noble
	 * @param mainAction indica se è un'azione primaria o secondaria
	 * @throws ActionFacadeException
	 */
	public void changeNoble(boolean king,int regionNumber,Color noble, boolean mainAction) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(mainAction == true)if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		else if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_HELPERS_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).changeNoble(king, regionNumber, noble, mainAction);
		}
	}
	
	/**
	 * Metodo per l'esecuzione della mossa acquista carta permesso 
	 * @param regionNumber
	 * @param permitCardNumber
	 * @param politicalCardsIndex
	 * @throws ActionFacadeException
	 */
	public void buyPermitCard(int regionNumber,int permitCardNumber,int[] politicalCardsIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).buyPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
		}
	}
	
	/**
	 * Metodo per l'esecuzione della mossa acquista piazza emporio 
	 * @param village
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public void placeShop(char village,int permitCardIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).placeShop(village, permitCardIndex);
		}
	} 
	
	/**
	 * Metodo per l'esecuzione della mossa acquista aiutante
	 * @param queque
	 * @throws ActionFacadeException
	 */
	public void buyHelper(boolean queque) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).buyHelper(queque);
		}
	} 
	
	/**
	 * Metodo per l'esecuzione della mossa acquista compra nuova azione ( azione secondaria )
	 * @throws ActionFacadeException
	 */
	public void buyNewMainAction() throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).buyNewMainAction();
		}
	} 
	
	/**
	 * Metodo che esegue la doppia azione di rimmettere delle carte permesso nel mazzo e rimescolare 
	 * 
	 * @param regionNumber
	 * @throws ActionFacadeException
	 */
	public void doubleAction(int regionNumber) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).doubleAction(regionNumber);
		}
	} 
	/**
	 * Metodo che consente di acquisire una carta permesso senza pagare
	 * @param regionNumber
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public void acquirePermitCard(int regionNumber,int permitCardIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquirePermitCardNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).acquirePermitCard(regionNumber, permitCardIndex);
		}
	} 
	
	/**
	 * Metodo che consente di acquisire il bonus di un villaggio in cui si e' costruito in precedenza  no shift
	 * @param villageFirstLetter
	 * @throws ActionFacadeException
	 */
	public void acquireSingleVillageBonus(char villageFirstLetter) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquireSingleVillageBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).acquireSingleVillageBonus(villageFirstLetter);
		}
	} 
	
	/**
	 * Metodo che consente di acquisire il bonus di due villaggio in cui si e' costruito in precedenza no shift
	 * @param villageFirstLetter
	 * @throws ActionFacadeException
	 */
	public void acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquireDoubleVillageBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).acquireDoubleVillageBonus(firstVillageFirstLetter, secondVillageFirstLetter);
		}
	} 
	
	/**
	 * Metodo che consente di riutilizzare il bonus di una carta permesso
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public void reusePermitCardBonus(int permitCardIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getReusePermitCardBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket(this.data.getUsername(), this.getUserData().getMatchCode()).reusePermitCardBonus(permitCardIndex);
		}
	} 
	
	public UserData getUserData(){ return this.data; }
	public GameMode getGameMode(){ return this.mode; }
}
