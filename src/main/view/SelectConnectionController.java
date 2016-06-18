package main.view;

import java.io.IOException;

import main.Client;
import main.ClientLogic;
import mud.model.basic.interfaces.MatchRequest;
import javafx.fxml.FXML;

public class SelectConnectionController {
	
	private Client mainApp;
	
	

	@FXML
	private void selectSocket() throws IOException{
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
		
		mainApp.showMessageConnection();	
	}
	
}
