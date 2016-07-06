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
import model.basics.GameMap;
import model.basics.Match;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.dao.basics.GameMapDAO;

/**
 *
 * @author Antonietta
 */
public class TestGameMapDAO {
    
     public static void main(String[] args) throws BuilderException, MatchException, GameMapException, PoliticalCardsDeckException, IOException {
        Match m;
        m = new MatchExample().getMatch();

       GameMapDAO pcEncoder = new GameMapDAO();
        
       GameMap gm = m.getBoard().getGameMap();
            

        JSONObject json = pcEncoder.writeData(gm);
   
        
        // Scrittura nel file
        try {
            File logFile = new File("test-json.txt");
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.write (json.toJSONString());
            writer.close();
            
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String jsonSTR = reader.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonRead = (JSONObject) parser.parse(jsonSTR);
            
           GameMap gm2 = pcEncoder.readData(jsonRead);
            
            System.out.println(" ");
        } 
   
        catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    
    
}
