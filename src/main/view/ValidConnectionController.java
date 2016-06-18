package main.view;

import main.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ValidConnectionController {

	private Stage validDialogStage;    
	private Client mainApp;    
	@FXML
	private Button buttonOk;
	
	@FXML
    private void initialize() {
    }
	
	public void setValidDialogStage(Stage validDialogStage) {
	        this.validDialogStage = validDialogStage;
	    }

	@FXML
	
	private void selectOk(){
	
		validDialogStage.close();
		mainApp.showMenuGame();
	
	
	}
	
	
	
	
	
	
}
