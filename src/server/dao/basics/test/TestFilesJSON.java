package server.dao.basics.test;

import examples.example1.MatchExample;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import server.dao.basics.PermitCardDAO;
import server.dao.basics.PoliticalCardDAO;

/**
 *
 * @author Antonietta
 */
public class TestFilesJSON {
    
    public static void main(String[] args) throws BuilderException, MatchException, GameMapException, PoliticalCardsDeckException {
        Match m;
        m = new MatchExample().getMatch();

        PermitCardDAO pcEncoder = new PermitCardDAO();
        PoliticalCardDAO pcEnco = new PoliticalCardDAO();
        
        PermitCard pc1 = m.getBoard().getRegions()[0].getPermitCardsDeck().getUnhiddenCards()[0];
        PoliticalCard pc = m.getBoard().getPoliticalCardsDeck().getAvailableCardsList().get(0);
        
        
        JSONObject json = pcEncoder.writeData(pc1, true);
        JSONObject json1 = pcEnco.writeData(pc,true);
        
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
            
            PermitCard pcR = pcEncoder.readData(jsonRead);
            System.out.println("fine");
            
            
            File logFile1 = new File("test-json1.txt");
            
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(logFile1));
            writer1.write (json1.toJSONString());
            writer1.close();
            
            BufferedReader reader1 = new BufferedReader(new FileReader(logFile1));
            String jsonSTR1 = reader1.readLine();
            JSONParser parser1 = new JSONParser();
            JSONObject jsonRead1 = (JSONObject) parser1.parse(jsonSTR1);
            
            PoliticalCard pcR1 = pcEnco.readData(jsonRead1);
            System.out.println("color: " + pcR1.getColor().getRGB() + "Jolly :  " + pcR1.getJolly());


            
        } 
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        } 
        catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
}
