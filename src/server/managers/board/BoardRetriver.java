package server.managers.board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
 * @author Luca Lagni
 *
 */
public class BoardRetriver {
	private static final String MAPS_FILE = "maps.txt";
	
	
	
	//Metodo che si occupa ti fornire una nuova board al chiamante
	public Board retriveBoard() throws BuilderException, GameMapException{
		System.out.println("+++ retriveBoard +++ ");
		
		try {
			BoardDAO pcEncoder = new BoardDAO();
			System.out.println("+++ qui +++ ");
			File logFile = new File("test-json.txt");
			BufferedReader reader = new BufferedReader(new FileReader(logFile));
			String jsonSTR = reader.readLine();
//			jsonSTR = jsonSTR.replaceAll("\n", "");
			System.out.println("+++ quo +++ ");
	         
	         JSONParser parser = new JSONParser();
	         JSONObject jsonRead = (JSONObject) parser.parse(jsonSTR);
	         System.out.println("+++ qua +++ ");
	         
	         Board b1 = pcEncoder.readData(jsonRead);
//	          System.out.println("Board acquisita da file :) \n\n" + b1.toString());
	          System.out.println("\n\n+++ Board acquisita da file +++ \n\n");
	         return b1;
			
		} catch (IOException | ParseException e) { e.printStackTrace(); }
		
		System.out.println("+++ Board di prova +++ ");
		return new BoardExample().getBoard();
	}
	
	
	
}
