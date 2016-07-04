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
	public void moveKing(ArrayList<String> path,ArrayList<Integer> politicalCardsPosition) throws ActionFacadeException{
		//Verifico che l'utente possa effettuare la mossa
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket().moveKing(path, politicalCardsPosition);
		}
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
	public void changeNoble(boolean king,int regionNumber,Color noble, boolean mainAction) throws ActionFacadeException, MainActionCommandException{
		/*int totalRegions = this.data.getMatch().getBoard().getRegions().length;
		boolean flag = false ;
		
		//Verifica che l'utente abbia a disposizione le mosse necessarie
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(mainAction == true)if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		else if(this.data.getActionSynoptic().getHelpersActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_HELPERS_ACTIONS.getExceptionCode());
		
		//Verifico che l'utente abbia selezionato la regione corretta
		if(king == false){
			if(regionNumber < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
			if(regionNumber >= totalRegions) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		}
		
		//Verificare che il nobile sia del colore corretto*/
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket().changeNoble(king, regionNumber, noble, mainAction);
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
			new ActionEncoderSocket().buyPermitCard(regionNumber, permitCardNumber, politicalCardsIndex);
		}
	}
	
	/**
	 * Metodo per l'esecuzione della mossa acquista piazza emporio 
	 * @param village
	 * @param permitCardIndex
	 * @throws ActionFacadeException
	 * @throws MainActionCommandException 
	 */
	public void placeShop(char village,int permitCardIndex) throws ActionFacadeException, MainActionCommandException{
		if(this.data.getActionSynoptic() == null) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_CANNOT_PLAY_NOW.getExceptionCode());
		if(this.data.getActionSynoptic().getMainActionNumber() <= 0) throw new ActionFacadeException(ActionFacadeExceptionCode.GAMER_HAS_NO_MORE_MAIN_ACTIONS.getExceptionCode());
		
		if(this.data.getGamer().getUnusedPermitCards().size() < permitCardIndex) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD.getExceptionCode());
		if(permitCardIndex < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD.getExceptionCode());
		
		
		if(this.mode == GameMode.SOCKET){
			new ActionEncoderSocket().placeShop(village, permitCardIndex);
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
			new ActionEncoderSocket().buyHelper(queque);
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
			new ActionEncoderSocket().buyNewMainAction();
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
			new ActionEncoderSocket().doubleAction(regionNumber);
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
			new ActionEncoderSocket().acquirePermitCard(regionNumber, permitCardIndex);
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
			new ActionEncoderSocket().acquireSingleVillageBonus(villageFirstLetter);
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
			new ActionEncoderSocket().acquireDoubleVillageBonus(firstVillageFirstLetter, secondVillageFirstLetter);
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
			new ActionEncoderSocket().reusePermitCardBonus(permitCardIndex);
		}
	} 
	
	public UserData getUserData(){ return this.data; }
	public GameMode getGameMode(){ return this.mode; }
}
