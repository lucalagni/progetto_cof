package server.command.actions.basics;

import java.awt.Color;
import java.util.ArrayList;

import commons.data.*;
import server.command.basic.actions.exceptions.MainActionCommandException;
import server.command.basic.actions.exceptions.codes.MainActionCommandExceptionCode;
import model.basics.Bonus;
import model.basics.GameMap;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PermitCardsDeck;
import model.basics.PoliticalCard;
import model.basics.Village;
import model.basics.constants.CoinsPoolConstants;
import model.basics.constants.ColorConstants;
import model.basics.constants.CouncilConstants;
import model.basics.constants.GameMapConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.constants.KingConstants;
import model.basics.constants.PermitCardsDeckConstants;
import model.basics.constants.RegionConstants;
import model.basics.constants.VillageConstants;
import model.basics.exceptions.CouncilException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.KingException;
import model.basics.exceptions.NobiltyPathException;
import model.basics.exceptions.NoblesPoolException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.exceptions.VillageException;

/**
 * Classe che implementa i  metodi per l'esecuzione delle azionin pricipali eseguibili dal giocatore interessato
 * La classe non è serializzata in quanto eseguita unicamente dal server
 * @author Luca Lagni
 *
 */

public class MainActionCommand {
	private Gamer gamer;
	private Match match;
	private ActionSynoptic actionSynoptic;
	
	private int virtualHelpers ;
	private int virtualCoins ;
	
	
	public MainActionCommand(Match match,Gamer gamer,int virtualHelpers,int virtualCoins,ActionSynoptic actionSynoptic) throws MainActionCommandException{
		if(actionSynoptic.getMainActionNumber() <= ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER){
			throw new MainActionCommandException(MainActionCommandExceptionCode.CANNOT_DO_THIS_ACTION.getExceptionCode());
		}
		this.setActionSynoptic(actionSynoptic);
		this.setMatch(match);
		this.setGamer(gamer);
		this.setVirtualHelpers(virtualHelpers);
		this.setVirtualCoins(virtualCoins);
	}
	
	private void setMatch(Match match){ this.match = match; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setVirtualHelpers(int virtualHelpers){ this.virtualHelpers = virtualHelpers; }
	private void setVirtualCoins(int virtualCoins){ this.virtualCoins = virtualCoins; }
	private void setActionSynoptic(ActionSynoptic actionSynoptic){ this.actionSynoptic = actionSynoptic; }
	
	/**
	 * Metodo per il piazzamento di un emporio da parte del giocatore di turno
	 * @param permitCardPosition indica la posizione della carta permesso all'interno dell'arraylist di carte permesso del giocatore
	 * @param villageFirstLetter indica la prima lettera identificante il villaggio in cui costruire
	 * @throws MainActionCommandException 
	 * @throws GamerException lanciabile qualora l'utente non abbia più empori a disposizione o la carta permesso non sia valida
	 * @throws HelpersPoolException lanciabile qualora il giocatore non abbia più aiutanti per costruire in un villaggio in cui qualcuno ha già costruito
	 * @throws VillageException lanciabile qualora l'utente abbia già costruito in quel villaggio
	 * @throws GameMapException lanciabile qualora il villaggio sia insesistente
	 * @throws PoliticalCardsDeckException 
	 * @throws NobiltyPathException
	 */
	@SuppressWarnings("unused")
	public void placeShop(int permitCardPosition,char villageFirstLetter) throws MainActionCommandException, GamerException, HelpersPoolException, VillageException, GameMapException, PoliticalCardsDeckException, NobiltyPathException{
		GameMap gp = this.match.getBoard().getGameMap();
		boolean flag = false;
		int index = 0;
		Village position = null;
		
		
		//Verifico che l'utente abbia la carta
		if(this.gamer.getUnusedPermitCards().size() < permitCardPosition)throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD.getExceptionCode());
		PermitCard card = this.gamer.getUnusedPermitCards().get(permitCardPosition);
		
		//Verifico se la lettera passatami appartiene ad un villaggio
				for(Village v: gp.getVillages()) {
					if(v.getFirstLetter() == villageFirstLetter){
						position = v;
						flag = true;
						break;
					}
					index++;
				}
				
				if(flag == false) throw new MainActionCommandException(MainActionCommandExceptionCode.VILLAGE_NOT_FOUND.getExceptionCode());
				else flag = false;
		
		//Verifico la consistenza tra la carta e la lettera	
		for(String s: card.getVillagesName()){
			if(s.charAt(0) == villageFirstLetter){
				flag = true;
				break;
			}
		}
		
		if(flag == false) throw new MainActionCommandException(MainActionCommandExceptionCode.INSISTENCE_BETWEEN_PERMIT_CARD_AND_VILLAGE.getExceptionCode());
		else flag = false ;
		
		//Verifico che l'utente non abbia gia un emporio in quel villaggio
		for(String s: position.getShops()){
			if(s.equals(this.gamer.getUsername())){
				flag = true;
				break;
			}
		}
		if(flag == false)throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_SHOP_ALREADY_PLACED_IN_THAT_VILLAGE.getExceptionCode());
		
		//Verifico che l'utente abbia ancora empori a disposizione
		this.checkNumberOfShops();
		
		//Verifico se l'utente e' il primo 
		if(position.getFirstGamer() != null){
			//Nel caso non lo sia, pago un aiutante
			if(this.gamer.getHelpers() < HelpersPoolConstants.HELPERS_FOR_SHOP){
				if(this.virtualHelpers < HelpersPoolConstants.HELPERS_FOR_SHOP) throw new MainActionCommandException(MainActionCommandExceptionCode.TOO_FEAW_HELPERS_AVAILABLES.getExceptionCode());
				else this.virtualHelpers = this.virtualHelpers - HelpersPoolConstants.HELPERS_FOR_SHOP;
				
			}
			this.gamer.subHelpers(HelpersPoolConstants.HELPERS_FOR_SHOP);
			this.match.getBoard().getHelpersPool().addHelpers(HelpersPoolConstants.HELPERS_FOR_SHOP);
		}
		
		//Piazzo l'emporio
		position.addShop(this.gamer);
		this.gamer.subShop();
		
		//Gestisco i derivanti bonus
		this.manageBonusChain(position);
		
		//Tolgo la carta dalle carte permesso inutilizzate e la metto insieme alle carte permesso usate
		this.gamer.usePermitCard(card);
		
		//Verifico se la partita deve finire
		this.checkNumberOfShops();
		
		//Riduco il numero di azioni principali eseguibili dal giocatore
		this.actionSynoptic.useMainAction();
		
	}
	
	
	
	/**
	 * Metodo che permette di gestire la catena di bonus che si viene a creare quando si costruisce un emporio in un villaggio 
	 * collegato ad altri in cui si è costruito in precedenza
	 * @param v indica il villaggio di cui gestire i bonus
	 * @throws GameMapException
	 * @throws GamerException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 */
	private void manageBonusChain(Village v) throws GameMapException, GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException{
		Village[] villages = this.match.getBoard().getGameMap().getVillages();
		int[][] connections = this.match.getBoard().getGameMap().getConnections();
		ArrayList<Integer> connected = new ArrayList<Integer>();
		boolean flag = false ;
		int position = 0;
		
		//Cerco la posizione del villaggio nell'array
		position = this.match.getBoard().getGameMap().getVillagePosition(v);
		
		//prendo il bonus del villaggio
		this.manageBonus(v.getBonus());
		connected.add(position);
		
		//Verifico la connessione del villaggio con quelli a lui direttamente connessi
		for(int i = 0; i < connections[position].length; i++){
			if(connections[position][i] == GameMapConstants.CONNECTED){
				for(String s: villages[i].getShops()){
					if(s.equals(this.gamer.getUsername())){
						connected.add(i);
						this.manageBonus(villages[i].getBonus());
						break;
					}
				}
			}
		}
		
		//Verifico le connessioni a livelli superiori
		for(int i = 0; i < connections.length; i++){
			for(int j = 0; j < connections[i].length; j++){
				if(connections[i][j] == GameMapConstants.CONNECTED){
					flag = false;
					//Controllo che il villaggio non sia già stato visitato in altri cicli
					for(Integer index : connected){
						if(index == j){
							flag = true ;
							break;
						}
					}
					
					//c'è una connessione e il negozio non è ancora stato visitato
					if(flag == false){
						for(Integer index : connected){
							if(connections[index][j] == GameMapConstants.CONNECTED){
								connected.add(j);
								this.manageBonus(villages[j].getBonus());
								break;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Metodo da chiamare a fine turno per poter mettere in coda il giocatore qualora non abbia ricevuto
	 * tutti gli aiutanti guadagnati
	 * @throws HelpersPoolException
	 */
	public void packVirtualHelpers() throws HelpersPoolException{
		this.match.getBoard().getHelpersPool().quequeGamer(this.gamer, this.virtualHelpers);
		this.setVirtualHelpers(0);
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
	/**
	 * Metodo per la verifica del numero di negozi del giocatore rimasti
	 * @throws MainActionCommandException
	 */
	public void checkNumberOfShops() throws MainActionCommandException{
		if(this.gamer.getShops() == 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_NO_MORE_AVAILABLE_SHOPS.getExceptionCode());
	}
	
	/**
	 * Metodo per l'acqusizione di una carta permesso afferente ad una determinata regione
	 * @param regionNumber indica la regione da cui acquisire la carta permesso di costruzione
	 * @param permitCardNumber indica quale delle carte scoperte prendere
	 * @param politicalCardsPosition indica la posizione nell'array listi di quali carte politiche utilizzare per acuisire la carta permesso desiderata
	 * @throws MainActionCommandException
	 * @throws PermitCardsDeckException
	 * @throws GamerException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 */
	public void pickupPermitCard(int regionNumber,int permitCardNumber,int politicalCardsPosition[]) throws MainActionCommandException, PermitCardsDeckException, GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException{
		if((regionNumber > RegionConstants.NUMBER_OF_REGIONS)||(regionNumber < 1)) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		if((permitCardNumber < 0) || (permitCardNumber > (PermitCardsDeckConstants.UNHIDDEN_CARDS_FOR_REGION - 1))) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_PERMIT_CARD_NUMBER.getExceptionCode());
		
		//Verifico la quantità di carte passatami
		if((politicalCardsPosition.length < 1) || (politicalCardsPosition.length > CouncilConstants.NOBLES_NUMBER)) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_POLITICAL_CARDS_NUMBER.getExceptionCode());
		if(this.gamer.getPoliticalCards().size() < politicalCardsPosition.length) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_POLITICAL_CARDS_NUMBER.getExceptionCode());
		
		//Verifico che le carte passatemi siano coerenti con i nobili del consiglio
		boolean positions[] = new boolean[politicalCardsPosition.length];
		int coins = 0;
		for(int i = 0; i < positions.length; i++) positions[i] = false;
		
		for(Color c : this.match.getBoard().getRegions()[regionNumber].getCouncil().getNobles()){
			for(int i = 0; i < politicalCardsPosition.length; i++){
				if(positions[i] == true) continue;
				
				if(c.equals(this.gamer.getPoliticalCards().get(politicalCardsPosition[i]))){
					positions[i] = true;
					continue;
				}
				
				if(this.gamer.getPoliticalCards().get(politicalCardsPosition[i]).getJolly() == true ){
					positions[i] = true;
					coins += PermitCardsDeckConstants.JOLLY_CARDS_COINS;
					continue;
				}
				
				if(positions[i] == true) break;
			}
		}
		
		for(int i = 0; i < positions.length; i++) if(positions[i] == false)throw new MainActionCommandException(MainActionCommandExceptionCode.INCONCISTENCE_BEETWEEN_POLITICAL_CARDS_AND_NOBLES.getExceptionCode());
		
		//Calcolo l'eventuale costo aggiuntivo per comprare la carta 
		switch(positions.length){
			case 1:
				coins += PermitCardsDeckConstants.ONE_CARD_COINS;
				break;
			case 2:
				coins += PermitCardsDeckConstants.TWO_CARDS_COINS;
				break;
			case 3:
				coins += PermitCardsDeckConstants.THREE_CARDS_COINS;
				break;
			case 4:
				coins += PermitCardsDeckConstants.FOUR_CARDS_COINS;
				break;
			default:
				throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_POLITICAL_CARDS_NUMBER.getExceptionCode());
		}
		
		//verifico la disponibilità di monete del giocatore
		if(this.virtualCoins < coins){
			if((this.gamer.getCoins() - (coins - this.virtualCoins)) < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
			else{
				this.gamer.subCoins(coins-this.virtualCoins);
				this.virtualCoins = 0;
			}
		}
		else this.virtualCoins = this.virtualCoins - coins;
		
		//Tolgo le carte usate dal giocatore
		PoliticalCard pc = null;
		for(int i = 0; i < politicalCardsPosition.length; i++){
			pc = this.gamer.subPoliticalCard(politicalCardsPosition[i]);
			this.match.getBoard().getPoliticalCardsDeck().addCard(pc);
		}
		
		//Prendo la carta permesso
		PermitCardsDeck pcd = this.match.getBoard().getRegions()[regionNumber].getPermitCardsDeck();
		PermitCard pcr = pcd.pickupCard(permitCardNumber);
		
		//do la carta permesso al giocatore
		this.manageBonus(pcr.getBonus());
		this.gamer.addPermitCard(pcr);
		
		//Riduco il numero di azioni principali del giocatore
		this.actionSynoptic.useMainAction();
	}
	
	/**
	 * Metodo per lo spostamento del re in un altro villaggio rispetto a quello in cui è presente
	 * @param politicalCardsPosition indica le carte politiche che il giocatore intende utilizzare
	 * @param villagePath indica il percorso che l'utente vuole seguire
	 * @throws MainActionCommandException
	 * @throws GamerException
	 * @throws KingException
	 * @throws GameMapException
	 * @throws HelpersPoolException
	 * @throws PoliticalCardsDeckException
	 * @throws NobiltyPathException
	 * @throws VillageException
	 */
	public void moveKing(int politicalCardsPosition[],char villagePath[]) throws MainActionCommandException, GamerException, KingException, GameMapException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException, VillageException{
		//Verifico l'effettiva connessione tra il percorso e i villaggi
		if(villagePath.length < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_VILLAGE_PATH.getExceptionCode());
		if((politicalCardsPosition.length < 1) || (politicalCardsPosition.length > CouncilConstants.NOBLES_NUMBER)) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_POLITICAL_CARDS_NUMBER.getExceptionCode());
		
		//Verifico la correttezza del percorso inseritomi
		boolean index[] = new boolean[villagePath.length];
		char villageFirst[] = new char[this.match.getBoard().getGameMap().getVillages().length];
		Village[] village = this.match.getBoard().getGameMap().getVillages();
		int[][] connections = this.match.getBoard().getGameMap().getConnections();
		int coins = 0;
		Village v = null ;
		
		for(Village v1: village){
			if(v1.getFirstLetter() == villagePath[villagePath.length - 1]){
				v = v1;
				break;
			}
		}
		
		for(int i = 0; i < villageFirst.length; i++) villageFirst[i] = village[i].getFirstLetter();
		for(int i = 0; i < index.length; i++) index[i] = false;
		
		//Controllo che l'utente abbia ancora negozi disponibili
		if(this.gamer.getShops() <= 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_NO_MORE_AVAILABLE_SHOPS.getExceptionCode());
		
		//Verifico che l'utente non abbia gia' un negozio nel villaggio
		if(v.hasGamerAShopHere(this.gamer))  throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_SHOP_ALREADY_PLACED_IN_THAT_VILLAGE.getExceptionCode());
		
		
		//Controllo le connessioni tra villaggi
		for(int i = 0; i < villagePath.length - 1; i++){
			//Verifico che il path trovato il corrispettivo villaggio
			for(int j = 0; j < villageFirst.length; j++){
				if(villagePath[i] == villageFirst[j]){
					//Se lo trova va a vedere che il successivo villagio sia collegato
					for(int k = 0; k < villageFirst.length; k++){
						if(villagePath[i + 1] == villageFirst[k]){
							if(connections[j][k] == GameMapConstants.NOT_CONNECTED) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_VILLAGE_PATH.getExceptionCode());
							if(connections[j][k] == GameMapConstants.CONNECTED) coins += KingConstants.COINS_FOR_MOVEMENT;
						}
					}
				}
			}
		}
		
		
		//Verifico la consistenza tra le carte politiche e i consiglieri del re
				boolean positions[] = new boolean[politicalCardsPosition.length];
				for(int i = 0; i < positions.length; i++) positions[i] = false;
				
				for(Color c : this.match.getBoard().getKing().getCouncil().getNobles()){
					for(int i = 0; i < politicalCardsPosition.length; i++){
						if(positions[i] == true) continue;
						
						if(c.equals(this.gamer.getPoliticalCards().get(politicalCardsPosition[i]))){
							positions[i] = true;
							continue;
						}
						
						if(this.gamer.getPoliticalCards().get(politicalCardsPosition[i]).getJolly() == true ){
							positions[i] = true;
							coins += PermitCardsDeckConstants.JOLLY_CARDS_COINS;
							continue;
						}
						
						if(positions[i] == true) break;
					}
				}
				
				for(int i = 0; i < positions.length; i++) if(positions[i] == false)throw new MainActionCommandException(MainActionCommandExceptionCode.INCONCISTENCE_BEETWEEN_POLITICAL_CARDS_AND_NOBLES.getExceptionCode());
				
				//Calcolo l'eventuale costo aggiuntivo per comprare la carta 
				switch(positions.length){
					case 1:
						coins += PermitCardsDeckConstants.ONE_CARD_COINS;
						break;
					case 2:
						coins += PermitCardsDeckConstants.TWO_CARDS_COINS;
						break;
					case 3:
						coins += PermitCardsDeckConstants.THREE_CARDS_COINS;
						break;
					case 4:
						coins += PermitCardsDeckConstants.FOUR_CARDS_COINS;
						break;
					default:
						throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_POLITICAL_CARDS_NUMBER.getExceptionCode());
				}
				
				//Verifico la disponibilita' di monete
				if(this.virtualCoins < coins) if((this.gamer.getCoins() - (coins - this.virtualCoins)) < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
				
				//Verifico la disponibilita' di aiutanti
				if(v.getShops()[0] != VillageConstants.NULL_GAMER) if(this.virtualHelpers < 1) if(this.gamer.getHelpers() < 1) throw new MainActionCommandException(MainActionCommandExceptionCode.TOO_FEAW_HELPERS_AVAILABLES.getExceptionCode());
				
				//verifico la disponibilità di monete del giocatore
				if(this.virtualCoins < coins){
					if((this.gamer.getCoins() - (coins - this.virtualCoins)) < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
					else{
						this.gamer.subCoins(coins-this.virtualCoins);
						this.virtualCoins = 0;
					}
				}
				else this.virtualCoins = this.virtualCoins - coins;
				
				//Se non e' il primo giocatore verifico la disponibilita' di aiutanti
				if(v.getShops()[0] != VillageConstants.NULL_GAMER){
					if(this.virtualHelpers < 1){
						if(this.gamer.getHelpers() < 1) throw new MainActionCommandException(MainActionCommandExceptionCode.TOO_FEAW_HELPERS_AVAILABLES.getExceptionCode());
						else{
							this.match.getBoard().getHelpersPool().addHelpers(1);
							this.gamer.subHelpers(1);
						}
					}
					else {
						this.virtualHelpers = this.virtualHelpers - 1;
						this.match.getBoard().getHelpersPool().addHelpers(1);
					}
				}
				
				//aggiungo l'emporio
				v.addShop(this.gamer);
				this.gamer.subShop();
				
				//Sposto il re
				this.match.getBoard().getKing().moveKing(v.getName());
				
				//Gestisco i derivanti bonus
				this.manageBonusChain(v);
				
				//riduco il numero di azioni principali del giocatore
				this.actionSynoptic.useMainAction();
	}
	
	/**
	 * Metodo per il cambio di un nobile di una determinata regione (o consiglio del re).
	 * Essendo azione principale fornisce monete al giocatore
	 * @param isKing indica se si intende cambiare il nobile nel consiglio del re
	 * @param regionNumber indica il numero di regione di cui cambiare nobile (ignorato nel caso di consiglio del re)
	 * @param noble indica il nuovo nopbile da inserire
	 * @throws MainActionCommandException
	 * @throws CouncilException
	 * @throws NoblesPoolException
	 * @throws GamerException
	 */
	public void changeNoble(boolean isKing,int regionNumber,Color noble) throws MainActionCommandException, CouncilException, NoblesPoolException, GamerException{
		if((regionNumber >= RegionConstants.NUMBER_OF_REGIONS)||(regionNumber < 0)) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_REGION_NUMBER.getExceptionCode());
		boolean flag = false;
		Color old = null;
		
		//Verfico che il colore sia uno di quelli ammissibili
		for(Color c: ColorConstants.POLITICAL_COLORS) if(c.equals(noble)) flag = true;
		if(flag == false) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_NOBLE_COLOR.getExceptionCode());
		
		//determino dove cambiare il nobile
		if(isKing == true) old = this.match.getBoard().getKing().getCouncil().slideNoble(noble);
		else old = this.match.getBoard().getRegions()[regionNumber].getCouncil().slideNoble(noble);
		
		/*
		 * Bilancio la quota di nobili disponibili
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
		
		//Gestisco la ricompensa da fornire al giocatore per il cambio del nobile
		if(this.gamer.getCoins() + CouncilConstants.REWARD_COINS > CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER){
			this.virtualCoins = this.gamer.getCoins() + CouncilConstants.REWARD_COINS - CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER;
			this.gamer.addCoins(CoinsPoolConstants.MAX_NUMBER_OF_COINS_FOR_GAMER-this.gamer.getCoins());
		}
		else this.gamer.addCoins(this.gamer.getCoins() + CouncilConstants.REWARD_COINS);
		
		//Riduco il numero di azioni principali del giocatore
		this.actionSynoptic.useMainAction();
	}
	
	public Match getMatch(){ return this.match; }
	public Gamer getGamer(){ return this.gamer; }
	public int getVirtualHelpers(){ return this.virtualHelpers ; }
	public int getVirtualCoins(){ return this.virtualCoins; }
	public ActionSynoptic getActionSynoptic(){ return this.actionSynoptic; }
}
