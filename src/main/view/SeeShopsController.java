package main.view;

import main.Client;
import main.ClientLogic;
import model.basics.Board;
import model.basics.Match;
import model.basics.Village;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import examples.example1.MatchExample;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SeeShopsController {
	private Stage seeShopsStage;    
	private Client mainApp;
	private Match matchNew;
	private Board be;
	private String village;
	
	@FXML private Button buttonClose;
	
	
	@FXML  Label shopsPlayer0;
	@FXML  Label shopsPlayer1;
	@FXML  Label shopsPlayer2;
	@FXML  Label shopsPlayer3;
	@FXML  Label shopsPlayer4;
	@FXML  Label shopsPlayer5;
	@FXML  Label shopsPlayer6;
	
	@FXML
    private void initialize() {
		
		try {
			matchNew = new MatchExample().getMatch();
			ClientLogic.getInstance().setMatch(matchNew);
			//istazio la board
			be = ClientLogic.getInstance().getMatch().getBoard();
		} catch (BuilderException | MatchException | GameMapException | PoliticalCardsDeckException e) {
			//pop-up con messaggio di errore (finestre di dialogo)
			e.printStackTrace();
		}
		
		
	}
	
	public void setSeeShopsStage(Stage seeShopsStage) {
	        this.seeShopsStage = seeShopsStage;
	
	    }
	
	@FXML
	private void clickToClose(){
		seeShopsStage.close();
	}
	
	
	public void setTextShopsPlayer(String village){
		this.village = village;
		Village[] villages= be.getGameMap().getVillages();
		Village v = null;
		for(Village tmp : villages){
			if(tmp.getName().equals(village)){
				v = tmp;
				break;
			}
		}
		
		String [] gamer = new String[7];
				
		gamer =	v.getShops();
		shopsPlayer0.setText("" + gamer[0]);
		shopsPlayer1.setText("" + gamer[1]);
		shopsPlayer2.setText("" + gamer[2]);
		shopsPlayer3.setText("" + gamer[3]);
		shopsPlayer4.setText("" + gamer[4]);
		shopsPlayer5.setText("" + gamer[5]);
		shopsPlayer6.setText("" + gamer[6]);
		
	}
	
	
	
	
	
	
}
