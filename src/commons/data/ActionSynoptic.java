package commons.data;

import java.io.Serializable;

import commons.data.ActionSynopticConstants;

/**
 * Classe che definisce le azioni eseguibili da un determinato utente 
 * @author Luca Lagni
 *
 */
public class ActionSynoptic implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username ;
	private String matchCode ;
	
	private Integer mainActionNumber ;                  //Indica il numero di azioni principali che posso compiere i giocatori
	private Integer helpersActionNumber ;               //Indica il numero di azioni secondarie che posso compiere i giocatori
	private Integer reusePermitCardBonusNumber;         //Indica il numero di bonus di carte permesso che posso riutilizzare
	private Integer acquirePermitCardNumber;            //Indica il numero di carte permesso che posso prendere senza pagare
	private Integer acquireSingleVillageBonusNumber;    //Indica il numero di bonus da un villaggio che posso prendere non shist
	private Integer acquireDoubleVillageBonusNumber;    //Indica il numero di bonus da due villaggi che posso prendere non shift
	
	private Integer virtualHelpers ;
	private Integer virtualCoins;
	
	public ActionSynoptic(String username,String matchCode){
		this.setUsername(username);
		this.setMatchCode(matchCode);
		this.setMainActionNumber(ActionSynopticConstants.INITIAL_MAIN_ACTIONS);
		this.setHelpersActionNumber(ActionSynopticConstants.INITIAL_HELPERS_ACTIONS);
		this.setReusePermitCardBonusNumber(0);
		this.setAcquirePermitCardNumber(0);
		this.setAcquireSingleVillageBonusNumber(0);
		this.setAcquireDoubleVillageBonusNumber(0);
		this.setVirtualHelpers(0);
		this.setVirtualCoins(0);
		
	}
	
	private void setUsername(String username){ this.username = username; }
	private void setMatchCode(String matchCode){ this.matchCode = matchCode; }
	private void setMainActionNumber(int mainActionNumber){ this.mainActionNumber = new Integer(mainActionNumber); }
	private void setHelpersActionNumber(int helpersActionNumber){ this.helpersActionNumber = new Integer(helpersActionNumber); }
	private void setReusePermitCardBonusNumber(int reusePermitCardBonus){ this.reusePermitCardBonusNumber = new Integer(reusePermitCardBonus); }
	private void setAcquirePermitCardNumber(int acquirePermitCard){this.acquirePermitCardNumber = new Integer(acquirePermitCard); }
	private void setAcquireSingleVillageBonusNumber(int acquireSingleVillageBonus){ this.acquireSingleVillageBonusNumber = new Integer(acquireSingleVillageBonus); }
	private void setAcquireDoubleVillageBonusNumber(int acquireDoubleVillageBonus){ this.acquireDoubleVillageBonusNumber = new Integer(acquireDoubleVillageBonus); }
	private void setVirtualHelpers(int virtualHelpers){ this.virtualHelpers = virtualHelpers; }
	private void setVirtualCoins(int virtualCoins){ this.virtualCoins = virtualCoins; }
	
	/**
	 * Metodo che riduce di una unità il numero di azioni principali del giocatore interessato
	 */
	public void useMainAction(){
		if(this.getMainActionNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setMainActionNumber(this.getMainActionNumber() - 1);
	}
	
	/**
	 * Metodo che riduce di una unità il numero di azioni secondarie del giocatore interessato
	 */
	public void useHelpersAction(){
		if(this.getHelpersActionNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setHelpersActionNumber(this.getHelpersActionNumber() - 1);
	}
	
	/**
	 * Metodo che riduce di una unità il numero di bonus di carte permesso che posso riutilizzare
	 */
	public void useReusePermitCardBonus(){
		if(this.getReusePermitCardBonusNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return;
		this.setReusePermitCardBonusNumber(this.getReusePermitCardBonusNumber() - 1);
	}
	
	/**
	 * Metodo che riduce di una unità il carte permesso che posso acquisire senza pagare
	 */
	public void useAcquirePermitCard(){
		if(this.getAcquirePermitCardNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setAcquirePermitCardNumber(this.getAcquirePermitCardNumber() - 1);
	}
	
	/**
	 * Metodo che riduce di una unità il numero di bonus che posso acquisire da un villaggio (no shift) senza dover 
	 * necessariamente costruire un emporio
	 */
	public void useAcquireSingleVillageBonus(){
		if(this.getAcquireSingleVillageBonusNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return ;
		this.setAcquireSingleVillageBonusNumber(this.getAcquireSingleVillageBonusNumber() - 1);
	}
	/**
	 * Metodo che riduce di una unità il numero di bonus che posso acquisire da due villaggi (no shift) senza dover 
	 * necessariamente costruire un emporio
	 */
	public void useAcquireDoubleVillageBonus(){
		if(this.getAcquireDoubleVillageBonusNumber() == ActionSynopticConstants.CANNOT_DO_THIS_ACTION_NUMBER) return;
		this.setAcquireDoubleVillageBonusNumber(this.getAcquireDoubleVillageBonusNumber() - 1);
	}
	
	/**
	 * Metodo che aggiunge una nuova azione principale al numero di azioni eseguibili dal giocatore
	 */
	public void addMainAction(){
		this.setMainActionNumber(this.getMainActionNumber() + 1);
	}
	/**
	 * Metodo che aggiunge una nuova azione secondaria al numero di azioni eseguibili dal giocatore
	 */
	public void addHelpersAction(){
		this.setHelpersActionNumber(this.getHelpersActionNumber() + 1);
	}
	/**
	 * Metodo che aggiunge una unità al numero di carte permesso di cui posso riutilizzare il bonus
	 */
	public void addReusePermitCardBonus(){
		this.setReusePermitCardBonusNumber(this.getReusePermitCardBonusNumber() + 1);
	}
	
	/**
	 * Metodo che aggiunge una unità al numero di carte permesso che posso acquisire senza pagare
	 */
	public void addAcquirePermitCard(){
		this.setAcquirePermitCardNumber(this.getAcquirePermitCardNumber() + 1);
	}
	
	/**
	 * Metodo che aggiunge una nuova unità al numero di bonus da un villaggio che posso acquisire
	 */
	public void addAcquireSingleVillageBonus(){
		this.setAcquireSingleVillageBonusNumber(this.getAcquireSingleVillageBonusNumber() + 1);
	}
	
	/**
	 * Metodo che aggiunge una nuova unità al numero di bonus da due villaggi che posso acquisire
	 */
	public void addAcquireDoubleVillageBonus(){
		this.setAcquireDoubleVillageBonusNumber(this.getAcquireDoubleVillageBonusNumber() + 1);
	}
	
	public void addVirtualHepers(int virtualHelpers){ this.setVirtualHelpers(this.getVirtualHelpers() + virtualHelpers); }
	public void subVirtualHelpers(int virtualHelpers){
		if((this.getVirtualHelpers() - virtualHelpers) < 0) this.setVirtualHelpers(0);
		else this.setVirtualHelpers(this.getVirtualHelpers() - virtualHelpers);
	}
	
	public void addVirtualCoins(int virtualCoins){ this.setVirtualCoins(this.getVirtualCoins() + virtualCoins); }
	public void subVirtualCoins(int virtualCoins){
		if((this.getVirtualCoins() - virtualCoins) < 0) this.setVirtualCoins(0);
		else this.setVirtualCoins(this.getVirtualCoins() - virtualCoins);
	}
	
	/**
	 * Metodo che consente di far tornare il sinottico delle azioni nella condizione di 
	 * inizio turno
	 */
	public void setupActionSynoptic(){
		this.setMainActionNumber(ActionSynopticConstants.INITIAL_MAIN_ACTIONS);
		this.setHelpersActionNumber(ActionSynopticConstants.INITIAL_HELPERS_ACTIONS);
	}
	
	/**
	 * metodo che restituisce lo username a cui è associato un certo sinottico
	 * @return
	 */
	public String getUsername(){ return this.username; }
	/**
	 * Metodo che indica a quale match si riferisce un certo sinottico
	 * @return
	 */
	public String getMatchCode(){ return this.matchCode; }
	/**
	 * Metodo che restituisce il numero di azioni principali che il giocatore interessato può svolgere
	 * @return
	 */
	public int getMainActionNumber(){ return this.mainActionNumber.intValue(); }
	/**
	 * Metodo che restituisce il numero di azioni secondarie che il giocatore interessato può svolgere
	 * @return
	 */
	public int getHelpersActionNumber(){ return this.helpersActionNumber.intValue(); }
	/**
	 * Metodo che restituisce il numero di carte permesso già usate in precedenza di cui posso riutilizzare i bonus
	 * @return
	 */
	public int getReusePermitCardBonusNumber(){ return this.reusePermitCardBonusNumber.intValue(); }
	/**
	 * Metodo che restituisce il numero di carte permesso che posso acquisire senza pagare
	 * @return
	 */
	public int getAcquirePermitCardNumber(){ return this.acquirePermitCardNumber.intValue(); }
	/**
	 * Metodo che restituisce il numero di bonus che posso prendere da un villaggio senza aver necessariamente
	 * costruito qualcosa (non posso prendere bonus shift)
	 * @return
	 */
	public int getAcquireSingleVillageBonusNumber(){ return this.acquireSingleVillageBonusNumber.intValue(); }
	/**
	 * Metodo che restituisce il numero di bonus che posso prendere da due villaggi senza aver necessariamente
	 * costruito qualcosa (non posso prendere bonus shift)
	 * @return
	 */
	public int getAcquireDoubleVillageBonusNumber(){ return this.acquireDoubleVillageBonusNumber.intValue(); }
	
	public int getVirtualHelpers(){ return this.virtualHelpers.intValue(); }
	public int getVirtualCoins(){ return this.virtualCoins.intValue(); }
	
	@Override
	public String toString(){
		String s = "\nActionSynoptic\n";
		
		s += "username: " + this.getUsername() + "\n";
		s += "matchCode: " + this.getMatchCode() + "\n";
		s += "main action number: " + this.getMainActionNumber() + "\n";
		s += "helpers action number: " + this.getHelpersActionNumber() + "\n";
		s += "reuse permit card bonus number: " + this.getReusePermitCardBonusNumber() + "\n";
		s += "acquire permit card number: " + this.getAcquirePermitCardNumber() + "\n";
		s += "acquire single village bonus number: " + this.getAcquireSingleVillageBonusNumber() + "\n";
		s += "acquire double village bonus number: " + this.getAcquireDoubleVillageBonusNumber() + "\n";
		s += "virtual helpers: " + this.getVirtualHelpers() + "\n";
		s += "virtual coins: " + this.getVirtualCoins() ;
		
		return s;
	}
}
