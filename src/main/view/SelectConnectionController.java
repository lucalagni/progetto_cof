package main.view;

import java.io.IOException;

import main.Client;
import main.ClientLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SelectConnectionController {
	
	private Client mainApp;
	
	@FXML TextField username;

	@FXML
	private void selectSocket() throws IOException{
		
            if(username.getText().trim().isEmpty()){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.initOwner(Client.getPrimaryStage());
		            alert.setTitle("Invalid Fields");
		            alert.setHeaderText("Non hai inserito nessun carattere\n");
		            alert.setContentText("Per favore, inserire username");

		            alert.showAndWait();
  
            }
           
            else{
		//ATTENZIONE
		ClientLogic.getInstance().setUsername(username.getText());
		mainApp.showMessageConnection();
            }
	}
	
	@FXML
	private void selectRmi() throws IOException{
//		ClientLogic.getInstance().initRMIConnection();
//		MatchRequest req = ClientLogic.getInstance().getMatchRequest();
//		
//		if( !req.requestMatch("user") ){
//			// segnala problemi di connessione
//			return;
//		}
		
//		while( !req.isMatchReady("user") ){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		Client.getInstance().setMatch(req.getMatch("user"));

         if(username.getText().trim().isEmpty()){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.initOwner(Client.getPrimaryStage());
		            alert.setTitle("Invalid Fields");
                            alert.setHeaderText("Non hai inserito nessun carattere\n");
		            alert.setContentText("Per favore, inserire username");

		            alert.showAndWait();
  
            }

        else{
		ClientLogic.getInstance().setUsername(username.getText());
		mainApp.showMessageConnection();            
            }   
	}
	
}
