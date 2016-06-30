package main.view;

import java.io.IOException;

import main.Client;
import main.ClientLogic;
import mud.model.basic.interfaces.MatchRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SelectConnectionController {
	
	private Client mainApp;
	
	@FXML TextField username;

	@FXML
	private void selectSocket() throws IOException{
		
		//ATTENZIONE
		ClientLogic.getInstance().setUsername(username.getText());
		mainApp.showMessageConnection();	
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
		ClientLogic.getInstance().setUsername(username.getText());
		mainApp.showMessageConnection();	
	}
	
}
