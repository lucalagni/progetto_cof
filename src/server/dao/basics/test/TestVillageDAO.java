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
import model.basics.Match;
import model.basics.Village;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.dao.basics.VillageDAO;

/**
 *
 * @author Antonietta
 */
public class TestVillageDAO {
    
    public static void main(String[] args) throws BuilderException, MatchException, GameMapException, PoliticalCardsDeckException, IOException {
        Match m;
        m = new MatchExample().getMatch();

        VillageDAO pcEncoder = new VillageDAO();
        
        Village[] v = m.getBoard().getGameMap().getVillages();
            

        JSONObject json = pcEncoder.writeData(v[0]);
   
        
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
            
            Village village = pcEncoder.readData(jsonRead);
            
            System.out.println(" ");
        } 
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        } 
        catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
}
