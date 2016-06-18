package main.view;

import main.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SeeShopsController {
	private Stage seeShopsStage;    
	private Client mainApp;   
	
	@FXML
	private Button buttonClose;
	
	@FXML
    private void initialize() {
    }
	
	public void setSeeShopsStage(Stage seeShopsStage) {
	        this.seeShopsStage = seeShopsStage;
	
	    }
	@FXML
	
	private void clickToClose(){
	
		seeShopsStage.close();
		
	
	}
	
	
}
