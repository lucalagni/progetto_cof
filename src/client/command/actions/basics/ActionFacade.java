package client.command.actions.basics;

import java.awt.Color;
import java.util.ArrayList;

import client.command.actions.basics.exceptions.ActionFacadeException;
import client.command.actions.basics.exceptions.codes.ActionFacadeExceptionCode;
import commons.data.UserData;
import commons.data.GameMode;
import client.controller.ControllerRepository;

//da cambiare
import server.command.basic.actions.exceptions.MainActionCommandException;
import server.command.basic.actions.exceptions.codes.MainActionCommandExceptionCode;

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
	public String moveKing(ArrayList<String> path,ArrayList<Integer> politicalCardsPosition) throws ActionFacadeException{
		//Verifico che l'utente possa effettuare la mossa
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().moveKing(path, politicalCardsPosition);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().moveKing(path, politicalCardsPosition);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	}
	
	/**
	 * Metodo per l'esecuzione della mossa eleggi consigliere
	 * @param king
	 * @param regionNumber
	 * @param noble
	 * @param mainAction indica se è un'azione primaria o secondaria
	 * @throws ActionFacadeException
	 * @throws MainActionCommandException 
	 */
	public String changeNoble(boolean king,int regionNumber,Color noble, boolean mainAction) throws ActionFacadeException, MainActionCommandException{
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket().changeNoble(king, regionNumber, noble, mainAction);
		}
		if(this.mode == GameMode.RMI){
			new ActionEncoderRmi().changeNoble(king, regionNumber, noble, mainAction);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	}
	
	/**
	 * Metodo per l'esecuzione della mossa acquista carta permesso 
	 * @param regionNumber
	 * @param permitCardNumber
	 * @param politicalCardsIndex
	 * @throws ActionFacadeException
	 */
	public String buyPermitCard(int regionNumber,int permitCardNumber,int[] politicalCardsIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().buyPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().buyPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	}
	
	/**
	 * Metodo per l'esecuzione della mossa acquista piazza emporio 
	 * @param village
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 * @throws MainActionCommandException 
	 */
	public String placeShop(char village,int permitCardIndex) throws ActionFacadeException, MainActionCommandException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.data.getGamer().getUnusedPermitCards().size() < permitCardIndex) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD.getExceptionCode());
		if(permitCardIndex < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD.getExceptionCode());
		
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().placeShop(village, permitCardIndex);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().placeShop(village, permitCardIndex);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	
	/**
	 * Metodo per l'esecuzione della mossa acquista aiutante
	 * @param queque
	 * @throws ActionFacadeException
	 */
	public String buyHelper(boolean queque) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().buyHelper(queque);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().buyHelper(queque);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	
	/**
	 * Metodo per l'esecuzione della mossa acquista compra nuova azione ( azione secondaria )
	 * @throws ActionFacadeException
	 */
	public String buyNewMainAction() throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().buyNewMainAction();
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().buyNewMainAction();
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	
	/**
	 * Metodo che esegue la doppia azione di rimmettere delle carte permesso nel mazzo e rimescolare 
	 * 
	 * @param regionNumber
	 * @throws ActionFacadeException
	 */
	public String doubleAction(int regionNumber) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().doubleAction(regionNumber);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().doubleAction(regionNumber);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	/**
	 * Metodo che consente di acquisire una carta permesso senza pagare
	 * @param regionNumber
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public String acquirePermitCard(int regionNumber,int permitCardIndex) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquirePermitCardNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().acquirePermitCard(regionNumber, permitCardIndex);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().acquirePermitCard(regionNumber, permitCardIndex);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	
	/**
	 * Metodo che consente di acquisire il bonus di un villaggio in cui si e' costruito in precedenza  no shift
	 * @param villageFirstLetter
	 * @throws ActionFacadeException
	 */
	public String acquireSingleVillageBonus(char villageFirstLetter) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquireSingleVillageBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().acquireSingleVillageBonus(villageFirstLetter);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().acquireSingleVillageBonus(villageFirstLetter);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	
	/**
	 * Metodo che consente di acquisire il bonus di due villaggio in cui si e' costruito in precedenza no shift
	 * @param villageFirstLetter
	 * @throws ActionFacadeException
	 */
	public String acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getAcquireDoubleVillageBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().acquireDoubleVillageBonus(firstVillageFirstLetter, secondVillageFirstLetter);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().acquireDoubleVillageBonus(firstVillageFirstLetter, secondVillageFirstLetter);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	
	/**
	 * Metodo che consente di riutilizzare il bonus di una carta permesso
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 */
	public String reusePermitCardBonus(int permitCardIndex,boolean usedPermitCard) throws ActionFacadeException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getReusePermitCardBonusNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_SPECIAL_ACTION_OF_THIS_TYPE.getExceptionCode());
		
		if(this.mode == GameMode.SOCKET){
			return new ActionEncoderSocket().reusePermitCardBonus(permitCardIndex, usedPermitCard);
		}
		if(this.mode == GameMode.RMI){
			return new ActionEncoderRmi().reusePermitCardBonus(permitCardIndex, usedPermitCard);
		}
		return new  String("GAME_MODE_NOT_AVAILABLE");
	} 
	
	public UserData getUserData(){ return this.data; }
	public GameMode getGameMode(){ return this.mode; }
}
