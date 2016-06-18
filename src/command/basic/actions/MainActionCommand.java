package command.basic.actions;

import java.awt.Color;
import java.util.ArrayList;

import command.basic.actions.exceptions.MainActionCommandException;
import command.basic.actions.exceptions.codes.MainActionCommandExceptionCode;
import model.basics.Bonus;
import model.basics.GameMap;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PermitCardsDeck;
import model.basics.PoliticalCard;
import model.basics.Village;
import model.basics.constants.CouncilConstants;
import model.basics.constants.GameMapConstants;
import model.basics.constants.HelpersPoolConstants;
import model.basics.constants.PermitCardsDeckConstants;
import model.basics.constants.RegionConstants;
import model.basics.constants.VillageConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.GamerException;
import model.basics.exceptions.HelpersPoolException;
import model.basics.exceptions.NobiltyPathException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.exceptions.VillageException;

public class MainActionCommand {
	private Gamer gamer;
	private Match match;
	
	private int newMainAction;
	private int reusePermitBonus;
	private int acquirePermitCard;
	private int acquireSingleVillageBonus;
	private int acquireDoubleVillageBonus;
	private int virtualHelpers ;
	private int virtualCoins ;
	
	
	public MainActionCommand(Match match,Gamer gamer,int virtualHelpers,int virtualCoins){
		this.setMatch(match);
		this.setGamer(gamer);
		this.setNewMainAction(0);
		this.setReusePermitBonus(0);
		this.setAcquirePermitCard(0);
		this.setAcquireSingleVillageBonus(0);
		this.setAcquireDoubleVillageBonus(0);
		this.setVirtualHelpers(virtualHelpers);
		this.setVirtualCoins(virtualCoins);
	}
	
	private void setMatch(Match match){ this.match = match; }
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	private void setNewMainAction(int newMainAction){ this.newMainAction = newMainAction; }
	private void setReusePermitBonus(int reusePermitBonus){ this.reusePermitBonus = reusePermitBonus; }
	private void setAcquirePermitCard(int acquirePermitCard){ this.acquirePermitCard = acquirePermitCard; }
	private void setAcquireSingleVillageBonus(int acquireSingleVillageBonus){ this.acquireSingleVillageBonus = acquireSingleVillageBonus; }
	private void setAcquireDoubleVillageBonus(int acquireDoubleVillageBonus){ this.acquireDoubleVillageBonus = acquireDoubleVillageBonus; }
	private void setVirtualHelpers(int virtualHelpers){ this.virtualHelpers = virtualHelpers; }
	private void setVirtualCoins(int virtualCoins){ this.virtualCoins = virtualCoins; }
	
	public boolean buyHelpers(int helpers, boolean queque) throws MainActionCommandException, HelpersPoolException, GamerException{
		int realPrice = 0;
		
		if(helpers <= HelpersPoolConstants.MIN_HELPERS_NUMBER) throw new MainActionCommandException(MainActionCommandExceptionCode.TOO_FEAW_HELPERS_SELECTED.getExceptionCode());
		if(this.match.getBoard().getHelpersPool().getActualTotal() < HelpersPoolConstants.MIN_HELPERS_NUMBER){
			if(queque == true) {
				this.match.getBoard().getHelpersPool().quequeGamer(this.gamer, helpers);
				return false;
			}
			else throw new MainActionCommandException(MainActionCommandExceptionCode.TOO_FEAW_HELPERS_AVAILABLES.getExceptionCode());
		}
		
		if(this.virtualCoins < (HelpersPoolConstants.HELPERS_COINS_PRICE * helpers)) {
			if((this.virtualCoins + this.gamer.getCoins()) < (HelpersPoolConstants.HELPERS_COINS_PRICE * helpers)) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
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
		return true;
	}
	
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
		for(String s: card.getVillages()){
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
		
	}
	
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
	
	public void packVirtualHelpers() throws HelpersPoolException{
		this.match.getBoard().getHelpersPool().quequeGamer(this.gamer, this.virtualHelpers);
		this.setVirtualHelpers(0);
	}
	
	private void manageBonus(Bonus bonus) throws GamerException, HelpersPoolException, PoliticalCardsDeckException, NobiltyPathException{
		this.gamer.addCoins(bonus.getCoins());
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
		
		this.gamer.addPoints(bonus.getPoints());
		
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
		
		if(bonus.getNewMainAction() == true) this.newMainAction++;
		if(bonus.getReusePermitBonus() == true) this.reusePermitBonus++;
		if(bonus.getAcquireSingleVillageBonus() == true) this.acquireSingleVillageBonus++;
		if(bonus.getAcquireDoubleVillageBonus() == true) this.acquireDoubleVillageBonus++;
		
		Bonus.useBonus(bonus);
		
	}
	
	public void checkNumberOfShops() throws MainActionCommandException{
		if(this.gamer.getShops() == 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_NO_MORE_AVAILABLE_SHOPS.getExceptionCode());
	}
	
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
	}
	
	public void moveKing(int politicalCardsPosition[],char villagePath[]) throws MainActionCommandException, GamerException{
		//Verifico l'effettiva connessione tra il percorso e i villaggi
		if(villagePath.length < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_VILLAGE_PATH.getExceptionCode());
		if((politicalCardsPosition.length < 1) || (politicalCardsPosition.length > CouncilConstants.NOBLES_NUMBER)) throw new MainActionCommandException(MainActionCommandExceptionCode.INVALID_POLITICAL_CARDS_NUMBER.getExceptionCode());
		
		//Verifico la correttezza del percorso inseritomi
		boolean index[] = new boolean[villagePath.length];
		char villageFirst[] = new char[this.match.getBoard().getGameMap().getVillages().length];
		Village[] village = this.match.getBoard().getGameMap().getVillages();
		
		for(int i = 0; i < villageFirst.length; i++) villageFirst[i] = village[i].getFirstLetter();
		for(int i = 0; i < index.length; i++) index[i] = false;
		
		//Controllo che i villaggi esistano effettivamente
		
		
		//Verifico la consistenza tra le carte politiche e i consiglieri del re
				boolean positions[] = new boolean[politicalCardsPosition.length];
				int coins = 0;
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
				
				//verifico la disponibilità di monete del giocatore
				if(this.virtualCoins < coins){
					if((this.gamer.getCoins() - (coins - this.virtualCoins)) < 0) throw new MainActionCommandException(MainActionCommandExceptionCode.GAMER_HAS_TOO_FEAW_COINS.getExceptionCode());
					else{
						this.gamer.subCoins(coins-this.virtualCoins);
						this.virtualCoins = 0;
					}
				}
				else this.virtualCoins = this.virtualCoins - coins;
				
				
	}
	
	public Match getMatch(){ return this.match; }
	public Gamer getGamer(){ return this.gamer; }
	public int getAcquirePermitCard(){ return this.acquirePermitCard; }
	public int getNewMainAction(){ return this.newMainAction; }
	public int getReusePermitBonus(){ return this.reusePermitBonus++; }
	public int getAcquireSingleVillageBonus(){ return this.acquireSingleVillageBonus; }
	public int getAcquireDoubleVillageBonus(){ return this.acquireDoubleVillageBonus; }
	public int getVirtualHelpers(){ return this.virtualHelpers ; }
	public int getVirtualCoins(){ return this.virtualCoins; }
}
