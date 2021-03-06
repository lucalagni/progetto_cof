package examples.example1;

import java.util.ArrayList;
import java.util.HashSet;

import model.basics.Bonus;
import model.basics.PermitCard;
import model.basics.PermitCardsDeck;
import model.basics.builders.BonusBuilder;
import model.basics.builders.PermitCardBuilder;
import model.basics.builders.PermitCardsDeckBuilder;
import model.basics.constants.PermitCardsDeckConstants;
import model.basics.constants.VillageConstants;

public class PermitCardsDeck3Example {
	private PermitCardsDeck pcd;
	private PermitCard[] deck ;
	
	public PermitCardsDeck3Example(){
		this.deck = new PermitCard[PermitCardsDeckConstants.CARDS_FOR_REGION];
		ArrayList<PermitCard> list = new ArrayList<PermitCard>();
		PermitCard[] pc = new PermitCard[2];
		this.setDeck();
		
		pc[0] = this.deck[0];
		pc[1] = this.deck[1];
		for(int i = 2; i < this.deck.length; i++) list.add(this.deck[i]);
		
		pcd = new PermitCardsDeckBuilder().setAvailableCardsSet(list).setUnhiddenCards(pc).build();
	}
	
	public PermitCardsDeck getDeck(){ return this.pcd; }
	
	private void setDeck(){
		HashSet<String> villages = new HashSet<String>();
		Bonus bonus = new BonusBuilder().setCoins(0)
										.setHelpers(0)
										.setPoints(2)
										.setShifts(0)
										.setPoliticalCards(2)
										.setNewMainAction(false)
										.setReusePermitBonus(false)
										.setAcquirePermitCard(false)
										.setAcquireSingleVillageBonus(false)
										.setAcquireDoubleVillageBonus(false)
										.setAvailable(true)
										.build();
		villages.add(VillageConstants.NARIS);
		villages.add(VillageConstants.OSIUM);
		this.deck[0] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(1)
								  .setHelpers(3)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(0)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.LYRAM);
		this.deck[1] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(3)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.MERKATIM);
		villages.add(VillageConstants.LYRAM);
		this.deck[2] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(0)
								  .setShifts(1)
								  .setPoliticalCards(2)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.OSIUM);
		villages.add(VillageConstants.KULTOS);
		this.deck[3] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(4)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.KULTOS);
		this.deck[4] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(1)
								  .setShifts(1)
								  .setPoliticalCards(0)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.MERKATIM);
		villages.add(VillageConstants.NARIS);
		villages.add(VillageConstants.OSIUM);
		this.deck[5] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(2)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(1)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.KULTOS);
		villages.add(VillageConstants.LYRAM);
		this.deck[6] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(1)
								  .setHelpers(1)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(0)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.KULTOS);
		villages.add(VillageConstants.LYRAM);
		villages.add(VillageConstants.MERKATIM);
		this.deck[7] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(0)
								  .setShifts(1)
								  .setPoliticalCards(3)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.OSIUM);
		this.deck[8] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(0)
								  .setNewMainAction(true)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.MERKATIM);
		villages.add(VillageConstants.NARIS);
		this.deck[9] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(1)
								  .setHelpers(0)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(1)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.KULTOS);
		villages.add(VillageConstants.LYRAM);
		villages.add(VillageConstants.OSIUM);
		this.deck[10] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(1)
								  .setShifts(0)
								  .setPoliticalCards(1)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.KULTOS);
		villages.add(VillageConstants.NARIS);
		villages.add(VillageConstants.OSIUM);
		this.deck[11] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(7)
								  .setHelpers(0)
								  .setPoints(0)
								  .setShifts(0)
								  .setPoliticalCards(0)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.NARIS);
		this.deck[12] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(3)
								  .setShifts(0)
								  .setPoliticalCards(0)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.LYRAM);
		villages.add(VillageConstants.MERKATIM);
		villages.add(VillageConstants.NARIS);
		this.deck[13] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
		villages = new HashSet<String>();
		bonus = new BonusBuilder().setCoins(0)
								  .setHelpers(0)
								  .setPoints(5)
								  .setShifts(1)
								  .setPoliticalCards(0)
								  .setNewMainAction(false)
								  .setReusePermitBonus(false)
								  .setAcquirePermitCard(false)
								  .setAcquireSingleVillageBonus(false)
								  .setAcquireDoubleVillageBonus(false)
								  .setAvailable(true)
								  .build();
		villages.add(VillageConstants.MERKATIM);
		this.deck[14] = new PermitCardBuilder().setBonus(bonus).setVillages(villages).build();
		
	}
}
