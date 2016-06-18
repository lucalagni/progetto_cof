package main.view;

import main.Client;
import javafx.fxml.FXML;

public class MenuGameController {

	private Client mainApp;
	
	
	@FXML
	public void startGameTime(){
		
		mainApp.showMessageStartGame();
	}
	
	
	
	
}
