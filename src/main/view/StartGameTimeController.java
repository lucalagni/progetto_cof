package main.view;

import main.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartGameTimeController {

	private Client mainApp;
	private Stage startGameTimeStage; 
	
	@FXML Button buttonExit;
	@FXML Button buttonStart;
	
	@FXML
    private void initialize() {
    }
	
	public void setStartGameTimeStage(Stage startGameTimeStage) {
	        this.startGameTimeStage = startGameTimeStage;
	}
	
	@FXML
	private void goStart(){
		
		startGameTimeStage.close();
		mainApp.showMatchGame();
	}
	
	@FXML
	private void goMenu(){
		
		startGameTimeStage.close();
		// da aggiungere: mandare mess al server che il giocatore si è disconnesso
		Client.showSelectConnection();
	}
	
}
