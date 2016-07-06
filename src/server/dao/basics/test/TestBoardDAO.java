/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics.test;

import examples.example1.MatchExample;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.basics.Board;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.dao.basics.BoardDAO;


/**
 *
 * @author Antonietta
 */
public class TestBoardDAO {
    
    
    public static void main(String[] args) throws BuilderException, MatchException, GameMapException, PoliticalCardsDeckException {
        Match m;
        m = new MatchExample().getMatch();

        BoardDAO pcEncoder = new BoardDAO();
        
        Board b = m.getBoard();
       
        JSONObject json = pcEncoder.writeData(b);
 
        
        // Scrittura nel file
        try {
            File logFile = new File("test-json.txt");
           // File logFile1 = new File("test-json1.txt");
            
                    
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.write (json.toJSONString());
            writer.close();
            
          
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String jsonSTR = reader.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonRead = (JSONObject) parser.parse(jsonSTR);
            
            Board b1 = pcEncoder.readData(jsonRead);
            
            System.out.println(" bo: " + b1);
//            JSONObject json = pcEncoder.writeData(b1);
             
//             
//            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile1));
//            writer.write (json.toJSONString());
//            writer.close();
            
            
           
            System.out.println("fine");
            
            
       


            
        } 
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        } 
        catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
}
