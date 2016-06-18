package main.view;

import main.Client;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class StartGameTimeController {

	private Client mainApp;
	private Stage startGameTimeStage; 
	
	
	
	@FXML
    private void initialize() {
    }
	
	public void setStartGameTimeStage(Stage startGameTimeStage) {
	        this.startGameTimeStage = startGameTimeStage;
	}
	
	
	@FXML
	private void goMenu(){
		
		startGameTimeStage.close();
		Client.showMenuGame();
	}
	
	@FXML
	private void matchGame(){
		
		startGameTimeStage.close();
		Client.showMatchGame();
	
	}
}
