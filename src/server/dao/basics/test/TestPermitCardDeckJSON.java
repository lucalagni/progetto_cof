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
import model.basics.PermitCardsDeck;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.dao.basics.PermitCardsDeckDAO;

/**
 *
 * @author Antonietta
 */
public class TestPermitCardDeckJSON {
    
        public static void main(String[] args) throws BuilderException, MatchException, GameMapException, PermitCardsDeckException, IOException, PoliticalCardsDeckException {
        Match m;
        m = new MatchExample().getMatch();

        PermitCardsDeckDAO pcEncoder = new PermitCardsDeckDAO();
        
        PermitCardsDeck pc = m.getBoard().getRegions()[0].getPermitCardsDeck();
        
        
        JSONObject json = pcEncoder.writeData(pc, true);
   
        
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
            
            PermitCardsDeck pcR = pcEncoder.readData(jsonRead);
            
            
//          if(pc.getAvailableCardsList().get(0).equals(pcR.getAvailableCardsList().get(0))){
//                System.out.println("uguali");
//            }
//            else System.out.println("diversi");
           
            //System.out.println(" "  +  pcR.getAvailableCardsList().get(0).getColor());
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
