package server.command.actions.basics;

import commons.data.ActionSynopticConstants;
import commons.data.ActionSynoptic;
import server.command.basic.actions.exceptions.SpecialActionCommandException;
import server.command.basic.actions.exceptions.codes.SpecialActionCommandExceptionCode;
import model.basics.Bonus;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PermitCardsDeck;
import model.basics.Village;
import model.basics.constants.PermitCardsDeckConstants;
import model.basics.constants.RegionConstants;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.NobiltyPathException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;

/**
 * Classe per la gestione delle azioni speciali
 * @author Luca Lagni
 *
 */

public class SpecialActionCommand {
	private Gamer gamer;
	private Match match;
	private ActionSynoptic actionSynoptic;
	
	private int virtualHelpers ;
	private int virtualCoins ;
	
	public SpecialActionCommand(Match match,Gamer gamer,int virtualHelpers,int virtualCoins,ActionSynoptic actionSynoptic){
		this.setMatch(match);
		this.setGamer(gamer);
		this.setVirtualCoins(virtualCoins);
		this.setVirtualHelpers(virtualHelpers);
		this.setActionSynoptic(actionSynoptic);
	}
	
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setMatch(Match match){ this.match = match; }
	private void setActionSynoptic(ActionSynoptic actionSynoptic){ this.actionSynoptic = actionSynoptic; }
	private void setVirtualHelpers(int virtualHelpers){ this.virtualHelpers = virtualHelpers; }
	private void setVirtualCoins(int virtualCoins){ this.virtualCoins = virtualCoins; }
	
	/**
	 * Metodo che consente di riutilizzare i bonus di una carta permesso già usata in precedenza
	 * @param permitCardPosition
	 * @throws GamerException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 * @throws SpecialActionCommandException 
	 */
	public void reusePermitCardBonus(int permitCardPosition) throws  GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException, SpecialActionCommandException{
		if(this.actionSynoptic.getReusePermitCardBonusNumber() <= ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER){
			throw new SpecialActionCommandException(SpecialActionCommandExceptionCode.CANNOT_PERFORM_THIS_ACTION.getExceptionCode());
		}
		int size = gamer.getUsedPermitCards().size();
		if((permitCardPosition < 0) || (size <= permitCardPosition)){
			throw new SpecialActionCommandException(SpecialActionCommandExceptionCode.INVALID_PERMIT_CARD_POSITION.getExceptionCode());
		}
		
		this.manageBonus(gamer.getUsedPermitCards().get(permitCardPosition).getBonus());
		
	    this.actionSynoptic.useReusePermitCardBonus();
	}
	
	/**
	 * Metodo per l'acquisizone di  bonus in un doppio villaggio in cui ho già costruito in precedenza
	 * @param villageFirstLetter
	 * @param standalone
	 * @throws SpecialActionCommandException
	 * @throws GamerException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 */
	public void acquireDoubleVillageBonus(char firstVillageFirstLetter, char secondVillageFirstLetter) throws SpecialActionCommandException, GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException{
		try{
			this.acquireSingleVillageBonus(firstVillageFirstLetter, false);
			this.acquireSingleVillageBonus(secondVillageFirstLetter, false);
		}
		finally{
			this.actionSynoptic.useAcquireDoubleVillageBonus();
		}
	}
	
	/**
	 * Metodo per l'acquisizone di un bonus in un singolo villaggio in cui ho già costruito in precedenza
	 * @param villageFirstLetter
	 * @param standalone
	 * @throws SpecialActionCommandException
	 * @throws GamerException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 */
	public void acquireSingleVillageBonus(char villageFirstLetter, boolean standalone) throws SpecialActionCommandException, GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException{
		Village[] villages = this.getMatch().getBoard().getGameMap().getVillages();
		Village found = null;
		boolean flag = false;
		
		//estrapolo il villaggio di cui mi interessa acquisire il bonus
		for(Village v : villages){
			if(v.getFirstLetter() == villageFirstLetter){
				found = v;
				break;
			}
		}
		
		//Se il villaggio non è stato trovato lancio l'eccezione
		if(found == null) throw new SpecialActionCommandException(SpecialActionCommandExceptionCode.INVALID_VILLAGE_SELECTED.getExceptionCode());
		
		//verifico che il giocatore abbia già costruito un emporio in tale villaggio
		for(String s: found.getShops()){
			if(s.equals(this.gamer.getUsername())){
				flag = true;
				break;
			}
		}
		
		//Nel caso il giocatore non sia presente lancio un'eccezione
		if(flag == false) new SpecialActionCommandException(SpecialActionCommandExceptionCode.GAMER_NOT_FOUND_IN_THIS_VILLAGE.getExceptionCode());
		
		//verifico che non vi siano shift nel bonus
		if(found.getBonus().getShifts() > 0) new SpecialActionCommandException(SpecialActionCommandExceptionCode.CANNOT_ACQUIRE_BONUS_WITH_SHIFTS.getExceptionCode());
		
		//nel caso vada tutto bene gestisco il bonus
		this.manageBonus(found.getBonus());
		
		//nel caso di acquisizione di singolo bonus (non doppio) riduco il numero di tali azioni disponibili
		if(standalone == true) this.actionSynoptic.useAcquireSingleVillageBonus();
	}
	
	/**
	 * Metodo per l'acuisizione di una carta permesso da parte del giocatore senza che questi debba pagare nulla
	 * @param regionNumber indica il numero della regione da cui pescare la carta permesso
	 * @param permitCardNumber indica la posizione della carta permesso da prendere
	 * @throws SpecialActionCommandException
	 * @throws PermitCardsDeckException
	 * @throws GamerException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 */
	public void acquirePermitCard(int regionNumber,int permitCardNumber) throws SpecialActionCommandException, PermitCardsDeckException, GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException{
		if((regionNumber > RegionConstants.NUMBER_OF_REGIONS)||(regionNumber < 1)) throw new SpecialActionCommandException(SpecialActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		if((permitCardNumber < 0) || (permitCardNumber > (PermitCardsDeckConstants.UNHIDDEN_CARDS_FOR_REGION - 1))) throw new SpecialActionCommandException(SpecialActionCommandExceptionCode.INVALID_PERMIT_CARD_POSITION.getExceptionCode());
		
		//Prendo la carta permesso
		PermitCardsDeck pcd = this.match.getBoard().getRegions()[regionNumber].getPermitCardsDeck();
		PermitCard pcr = pcd.pickupCard(permitCardNumber);
		
		//do la carta permesso al giocatore
		this.manageBonus(pcr.getBonus());
		this.gamer.addPermitCard(pcr);
		
		this.actionSynoptic.useAcquirePermitCard();
	}
	
	/**
	 * Metodo per l'utilizzo di un bonus da parte di un giocatore
	 * @param bonus indica il bonus da cui prendere i valori
	 * @throws GamerException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 */
	private void manageBonus(Bonus bonus) throws GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException{
		//aggiungo le monete del bonus al giocatore
		this.gamer.addCoins(bonus.getCoins());
		/*
		 * Aggiungo aiutanti al giocatore e, qualora non ve ne siano a sufficienza, incremento il numero 
		 * di aiutanti virtuali
		 */
		if(bonus.getHelpers() > 0){
			if(this.match.getBoard().getHelpersPool().getActualGamerHelpers() < bonus.getHelpers()){
				this.virtualHelpers += (bonus.getHelpers() - this.match.getBoard().getHelpersPool().getActualTotal());
				this.gamer.addHelpers(this.match.getBoard().getHelpersPool().getActualGamerHelpers());
				this.match.getBoard().getHelpersPool().subHelpers(this.match.getBoard().getHelpersPool().getActualGamerHelpers());
			}
			else
			{
				this.gamer.addHelpers(bonus.getHelpers());
				this.match.getBoard().getHelpersPool().subHelpers(bonus.getHelpers());
			}
		}
		
		//aggiungo punti al giocatore qualora il bonus li contempli
		this.gamer.addPoints(bonus.getPoints());
		
		/*
		 * Gestisco l'acquisizione di carte politiche da parte del giocatore,
		 * qualora non ve ne siano a sufficienza metto il giocatore in coda
		 */
		if(bonus.getPoliticalCards() > 0){
			if(this.match.getBoard().getPoliticalCardsDeck().getAvailableCardsList().size() < bonus.getPoliticalCards()){
				this.match.getBoard().getPoliticalCardsDeck().quequeGamer(gamer, bonus.getPoliticalCards());
			}
			else {
				for(int i = 0; i < bonus.getPoliticalCards(); i++) this.gamer.addPoliticalCard(this.match.getBoard().getPoliticalCardsDeck().pickupCard());
			}
		}
		
		//Gestione degli shifts
		for(int i = 0;i < bonus.getShifts(); i++){
			if((i + this.gamer.getShifts()) < this.match.getBoard().getNobiltyPath().getBonus().length){
				if(this.match.getBoard().getNobiltyPath().getSingleBonus(i + this.gamer.getShifts()) != null){
					this.manageBonus(this.match.getBoard().getNobiltyPath().getSingleBonus(i + this.gamer.getShifts()));
				}
			}
			
			this.gamer.addShifts(bonus.getShifts());
		}
		
		//Gestione ddelle azioni speciali
		if(bonus.getNewMainAction() == true) {
			this.actionSynoptic.addMainAction();
		}
		if(bonus.getReusePermitBonus() == true){
			this.actionSynoptic.addReusePermitCardBonus();
		}
		if(bonus.getAcquirePermitCard() == true){
			this.actionSynoptic.addAcquirePermitCard();
		}
		if(bonus.getAcquireSingleVillageBonus() == true){
			this.actionSynoptic.addAcquireSingleVillageBonus();
		}
		if(bonus.getAcquireDoubleVillageBonus() == true){
			this.actionSynoptic.addAcquireDoubleVillageBonus();
		}
				
	}
	
	public Match getMatch(){ return this.match; }
	public Gamer getGamer(){ return this.gamer; }
	public int getVirtualHelpers(){ return this.virtualHelpers ; }
	public int getVirtualCoins(){ return this.virtualCoins; }
	public ActionSynoptic getActionSynoptic(){ return this.actionSynoptic; }
}
