package server.managers.board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.dao.basics.BoardDAO;
import examples.example1.BoardExample;
import model.basics.Board;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;

/**
 * Classe per il ritiro di una board dal repository
 * @author Luca Lagni, Maria Antonietta Palermo
 *
 */
public class BoardRetriver {
	private static final String MAPS_FILE = "maps.txt";
	
	public BoardRetriver(){ }
	
	public Board retriveBoard() throws BuilderException, GameMapException{
		try {
			BoardDAO pcEncoder = new BoardDAO();
			File logFile = new File("test-json.txt");
			BufferedReader reader = new BufferedReader(new FileReader(logFile));
			String jsonSTR = reader.readLine();
	         
	         JSONParser parser = new JSONParser();
	         JSONObject jsonRead = (JSONObject) parser.parse(jsonSTR);
	         
	         Board b1 = pcEncoder.readData(jsonRead);
	         return b1;
			
		} catch (IOException | ParseException | BuilderException | GameMapException e) { 
			return new BoardExample().getBoard();
		}
	}
	
	
}
