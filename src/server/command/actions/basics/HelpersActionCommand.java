package server.command.actions.basics;

import java.awt.Color;

import commons.data.*;
import server.command.basic.actions.exceptions.HelpersActionCommandException;
import server.command.basic.actions.exceptions.MainActionCommandException;
import server.command.basic.actions.exceptions.codes.HelpersActionCommandExceptionCode;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.constants.ColorConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.constants.PermitCardsDeckConstants;
import model.basics.constants.RegionConstants;
import model.basics.exceptions.CouncilException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.PermitCardsDeckException;

/**
 * Classe che implementa le azioni secondarie eseguibili da un'utente nel contesto di match
 * Non serializzata in quanto eseguita completamente lato server
 * @author Luca Lagni
 *
 */

public class HelpersActionCommand {
	private Gamer gamer;
	private Match match;

	private int virtualHelpers ;
	private int virtualCoins ;
	private ActionSynoptic actionSynoptic;
	
	public HelpersActionCommand(UserData data) throws HelpersActionCommandException{
		if(this.actionSynoptic.getHelpersActionNumber() <= ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER){
			throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.CANNOT_DO_THIS_ACTION.getExceptionCode());
		}
		this.setActionSynoptic(data.getActionSynoptic());
		this.setGamer(data.getGamer());
		this.setMatch(data.getMatch());
		this.setVirtualHelpers(data.getActionSynoptic().getVirtualHelpers());
		this.setVirtualCoins(data.getActionSynoptic().getVirtualCoins());
	}
	
	private void setMatch(Match match){ this.match = match; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setVirtualHelpers(int virtualHelpers){ this.virtualHelpers = virtualHelpers; }
	private void setVirtualCoins(int virtualCoins){ this.virtualCoins = virtualCoins; }
	private void setActionSynoptic(ActionSynoptic actionSynoptic){ this.actionSynoptic = actionSynoptic; }
	
	/**
	 * Metodo per l'acquisto di aiutanti
	 * Le eccezioni vengono generate qualora l'utente non abbia abbastanza soldi o non vi siano abbastanza aiutanti per soddisfare
	 * l'acquisto
	 * @param helpers indica la quantità di aiutanti da comprare
	 * @param queque indica se l'utente debba essere messo in coda qualora non vi fossero abbastanza aiutanti disponibili
	 * @return
	 * @throws HelpersPoolException
	 * @throws GamerException
	 * @throws HelpersActionCommandException
	 */
	public boolean buyHelpers(int helpers, boolean queque) throws  HelpersPoolException, GamerException, HelpersActionCommandException{
		int realPrice = 0;
		
		if(helpers <= HelpersPoolConstants.MIN_HELPERS_NUMBER) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
		if(this.match.getBoard().getHelpersPool().getActualTotal() < HelpersPoolConstants.MIN_HELPERS_NUMBER){
			if(queque == true) {
				/*
				 * Se non ci sono abbastanza aiutanti disponibili e ho attiva l'azione di messa in coda del giocatore lo accodo
				 * altrimenti viene lanciata un'eccezione che notifica che non ci sono abbastanza aiutanti disponibili
				 */
				this.match.getBoard().getHelpersPool().quequeGamer(this.gamer, helpers);
				return false;
			}
			else throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
		}
		
		/*
		 * Verifico che l'utente abbia abbastanza monete (reali o virtuali) per l'acquisto degli aiutanti
		 */
		if(this.virtualCoins < (HelpersPoolConstants.HELPERS_COINS_PRICE * helpers)) {
			/*
			 * Se non ha il giocatore non ha abbastanza monete viene lanciata un'eccezione di notifica 
			 * altrimenti gli vengono scalate tanteb monete quanti sono gli aiutanti moltiplicati per il loro prezzo
			 */
			if((this.virtualCoins + this.gamer.getCoins()) < (HelpersPoolConstants.HELPERS_COINS_PRICE * helpers)) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
			else{
				realPrice = HelpersPoolConstants.HELPERS_COINS_PRICE * helpers - this.virtualCoins;
				this.setVirtualCoins(0);
				this.gamer.subCoins(realPrice);
			}
		}
		else {
			this.setVirtualCoins(this.getVirtualCoins() - HelpersPoolConstants.HELPERS_COINS_PRICE * helpers);
		}
		
		this.gamer.addHelpers(helpers);
		this.match.getBoard().getHelpersPool().subHelpers(helpers);
		
		//riduco il numero di azioni secondrie che l'utente può svolgere
		this.actionSynoptic.useHelpersAction();
		return true;
	}
	
	/**
	 * Metodo per il cambio di un nobile in un determinato consiglio.
	 * Essendo un'azione secondaria , ciò non da soldi al giocatore.
	 * 
	 * @param isKing determina se è il consiglio del re
	 * @param regionNumber indica la regione di cui cambiare il consigliere
	 * @param noble indica il nuovo nobile da inserire nel consiglio della regione
	 * @throws MainActionCommandException 
	 * @throws CouncilException
	 * @throws NoblesPoolException
	 * @throws GamerException
	 * @throws HelpersActionCommandException
	 */
	public void changeNoble(boolean isKing,int regionNumber,Color noble) throws MainActionCommandException, CouncilException, NoblesPoolException, GamerException, HelpersActionCommandException{
		if((regionNumber >= RegionConstants.NUMBER_OF_REGIONS)||(regionNumber < 0)) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		boolean flag = false;
		Color old = null;
		
		/*
		 * Se il colore passato non fa parte della palette dei colori politici lancio un'eccezione che notifica 
		 * la non validità dell'operazione
		 */
		for(Color c: ColorConstants.POLITICAL_COLORS) if(c.equals(noble)) flag = true;
		if(flag == false) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.INVALID_NOBLE_COLOR.getExceptionCode());
		
		if(isKing == true) old = this.match.getBoard().getKing().getCouncil().slideNoble(noble);
		else old = this.match.getBoard().getRegions()[regionNumber].getCouncil().slideNoble(noble);
		
		/*
		 * Al cambio nobili bilancio il numero di nobili disponibili per categoria di colore
		 */
		if(noble.equals(ColorConstants.POLITICAL_COLORS[0])) this.match.getBoard().getNoblesPool().subBlackNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[1])) this.match.getBoard().getNoblesPool().subCyanNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[2])) this.match.getBoard().getNoblesPool().subPinkNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[3])) this.match.getBoard().getNoblesPool().subMagentaNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[4])) this.match.getBoard().getNoblesPool().subOrangeNoble();
		if(noble.equals(ColorConstants.POLITICAL_COLORS[5])) this.match.getBoard().getNoblesPool().subWhiteNoble();
		
		if(old.equals(ColorConstants.POLITICAL_COLORS[0])) this.match.getBoard().getNoblesPool().addBlackNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[1])) this.match.getBoard().getNoblesPool().addCyanNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[2])) this.match.getBoard().getNoblesPool().addPinkNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[3])) this.match.getBoard().getNoblesPool().addMagentaNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[4])) this.match.getBoard().getNoblesPool().addOrangeNoble();
		if(old.equals(ColorConstants.POLITICAL_COLORS[5])) this.match.getBoard().getNoblesPool().addWhiteNoble();
		
		//riduco il numero di azioni secondarie che l'utente può svolgere
		this.actionSynoptic.useHelpersAction();
	}
	
	/**
	 * Metodo che consente di rimettere le carte politiche scoperte nel deck della regione, rimescolare 
	 * ed estrarne due nuove
	 * 
	 * @param regionNumber indica la regione in cui effettuare tale azione
	 * @throws GamerException
	 * @throws HelpersActionCommandException
	 * @throws PermitCardsDeckException
	 */
	public void doubleAction(int regionNumber) throws GamerException, HelpersActionCommandException, PermitCardsDeckException{
		if((regionNumber < 0) ||(regionNumber >= RegionConstants.NUMBER_OF_REGIONS))throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		
		/*
		 * Verifico se ho abbastanza aiutanti (virtuali o reali) per poter eseguire la doppia azione
		 * se li ho li scalo dal giocatore (o dai virtuali) se non li ho lancio una notifica
		 */
		if(this.virtualHelpers < PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS){
			if(this.gamer.getHelpers() + this.virtualHelpers < PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
			else {
				this.gamer.subHelpers(PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS - this.virtualHelpers);
				this.virtualHelpers = 0;
			}
		}
		else this.virtualHelpers = this.virtualHelpers - PermitCardsDeckConstants.DOUBLE_ACTION_HELPERS;
		
		this.match.getBoard().getRegions()[regionNumber].getPermitCardsDeck().doubleAction();
		
		this.actionSynoptic.useHelpersAction();
	}
	
	/**
	 *  Metodo che consente di comprare una nuova azione principale mediante l'ausilio di aiutanti
	 *  Tale metodo consente di comprare un'unica azione principale per turno (come nel gioco)
	 * @throws HelpersActionCommandException
	 * @throws GamerException
	 * @throws HelpersPoolException
	 */
	public void buyNewMainAction() throws HelpersActionCommandException, GamerException, HelpersPoolException{
		/*
		 * Verifico di avere abbastanza aiutanti da poter eseguire l'azione, in caso contrario
		 * lancio una notifica di mancata esecuzione
		 */
		if(this.virtualHelpers < HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION){
			if(this.gamer.getHelpers() + this.virtualHelpers < HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION) throw new HelpersActionCommandException(HelpersActionCommandExceptionCode.TOO_FEAW_HELPERS.getExceptionCode());
			else {
				this.gamer.subHelpers(HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION - this.virtualHelpers);
				this.virtualHelpers = 0;
			}
		}
		else this.virtualHelpers = this.virtualHelpers - HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION;
		
		this.match.getBoard().getHelpersPool().addHelpers(HelpersPoolConstants.HELPERS_FOR_NEW_MAIN_ACTION);
		
		this.actionSynoptic.addMainAction();
	}
	
	public Match getMatch(){ return this.match; }
	public Gamer getGamer(){ return this.gamer; }
	public int getVirtualHelpers(){ return this.virtualHelpers ; }
	public int getVirtualCoins(){ return this.virtualCoins; }
	public ActionSynoptic getActionSynoptic(){ return this.actionSynoptic; }
}
