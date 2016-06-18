package main.view;

import java.util.HashSet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Client;
import main.ClientLogic;
import model.basics.Board;
import model.basics.PermitCard;

public class SeeBonusController {
	private Stage seeBonusVillageStage;    
	private Client mainApp;    
	
	@FXML private Label bonusHelpers;
	@FXML private Label bonusShifts;
	@FXML private Label bonusPoliticalCards;
	@FXML private Label bonusCoins;
	@FXML private Label bonusPoints;
	@FXML private Label bonusNewMainAction;
	@FXML private Label bonusReusePermitCard;
	@FXML private Label bonusAcquirePermitCard;
	@FXML private Label bonusAcquireSingleVillageBonus;
	@FXML private Label bonusAcquireDoubleVillageBonus;

	Board be;
		
	public SeeBonusController()
	{
		be = ClientLogic.getInstance().getBoard();
	}
	
	public void setBonusHelpers(String s){
		bonusHelpers.setText(s);
	}
	
	public void setBonusShifts(String s){
		bonusShifts.setText(s);
	}
	
	public void setBonusPoliticalCards(String s){
		bonusPoliticalCards.setText(s);
	}
	
	public void setBonusCoins(String s){
		bonusCoins.setText(s);
	}
	
	public void setBonusPoints(String s){
		bonusPoints.setText(s);
	}
	
	public void setBonusNewMainAction(String s){
		bonusNewMainAction.setText(s);
	}
	
	public void setBonusReusePermitCard(String s){
		bonusReusePermitCard.setText(s);
	}
	
	public void setBonusAcquirePermitCard(String s){
		bonusAcquirePermitCard.setText(s);
	}
	
	public void setBonusAcquireSingleVillageBonus(String s){
		bonusAcquireSingleVillageBonus.setText(s);
	}
	
	public void setBonusAcquireDoubleVillageBonus(String s){
		bonusAcquireDoubleVillageBonus.setText(s);
	}

	
	/*@FXML
    private void initialize() {
		this.setBonusHelpers("");
		this.setBonusShifts("");
		this.setBonusPoliticalCards("");
		this.setBonusCoins("");
		this.setBonusPoints("");
		this.setBonusNewMainAction("");
		this.setBonusReusePermitCard("");
		this.setBonusAcquirePermitCard("");
		this.setBonusAcquireSingleVillageBonus("");
		this.setBonusAcquireDoubleVillageBonus("");
		
		
    }*/
	
    public void seeBonusVillage(int i){
    	
    	setBonusHelpers("" + be.getGameMap().getVillages()[i].getBonus().getHelpers());
		setBonusShifts("" + be.getGameMap().getVillages()[i].getBonus().getShifts());
	    setBonusPoliticalCards("" + be.getGameMap().getVillages()[i].getBonus().getPoliticalCards());
	    setBonusCoins("" + be.getGameMap().getVillages()[i].getBonus().getCoins());
	    setBonusPoints("" + be.getGameMap().getVillages()[i].getBonus().getPoints());
	    setBonusNewMainAction("" + be.getGameMap().getVillages()[i].getBonus().getNewMainAction());
	    setBonusReusePermitCard("" + be.getGameMap().getVillages()[i].getBonus().getReusePermitBonus());
	    setBonusAcquirePermitCard("" + be.getGameMap().getVillages()[i].getBonus().getAcquirePermitCard());
	    setBonusAcquireSingleVillageBonus("" + be.getGameMap().getVillages()[i].getBonus().getAcquireSingleVillageBonus());
	    setBonusAcquireDoubleVillageBonus("" + be.getGameMap().getVillages()[i].getBonus().getAcquireDoubleVillageBonus());
	}
    
    public void seeBonusPermitCard(int i, int card){
    	
    	PermitCard pc;
    	pc = be.getRegions()[i].getPermitCardsDeck().getAvailableCardsList().get(card);
    	setBonusHelpers("" + pc.getBonus().getHelpers());
    	setBonusShifts("" + pc.getBonus().getShifts());
    	setBonusPoliticalCards("" + pc.getBonus().getPoliticalCards());
    	setBonusCoins("" + pc.getBonus().getCoins());
    	setBonusPoints("" + pc.getBonus().getPoints());
    	setBonusNewMainAction("" + pc.getBonus().getNewMainAction());
    	setBonusReusePermitCard("" + pc.getBonus().getReusePermitBonus());
    	setBonusAcquirePermitCard("" + pc.getBonus().getAcquirePermitCard());
    	setBonusAcquireSingleVillageBonus("" + pc.getBonus().getAcquireSingleVillageBonus());
    	setBonusAcquireDoubleVillageBonus("" + pc.getBonus().getAcquireDoubleVillageBonus());
    	
    	
    	
    	
    }
	
	
	public void setSeeBonusVillageStage(Stage seeBonusVillageStage) {
	        this.seeBonusVillageStage = seeBonusVillageStage;
	
	       
	    }
	
	
	
}
