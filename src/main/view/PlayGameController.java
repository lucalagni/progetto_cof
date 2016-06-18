package main.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import main.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class PlayGameController {

	private Client mainApp;

	
	@FXML
	private void goPlay() throws IOException{
		
		Client.showSelectConnection();
		
	}
	



	
	
}
