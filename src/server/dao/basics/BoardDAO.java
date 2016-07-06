/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.basics.Board;
import model.basics.GameMap;
import model.basics.HelpersPool;
import model.basics.King;
import model.basics.NobiltyPath;
import model.basics.NoblesPool;
import model.basics.PoliticalCardsDeck;
import model.basics.Region;
import model.basics.Village;
import model.basics.builders.BoardBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.GameMapConstants;
import model.basics.constants.VillageConstants;
import model.basics.exceptions.GameMapException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.dao.basics.fields.DAOFields;

/**
 *
 * @author Antonietta
 */
public class BoardDAO {
    
    @SuppressWarnings("unchecked")
	public Board readData(JSONObject jsonRoot) throws BuilderException, GameMapException {
		JSONObject json = (JSONObject)jsonRoot.get(DAOFields.BOARD);
                JSONArray jArray = (JSONArray) json.get(DAOFields.REGIONS);
                Region[] regions = new Region[3];
                File f[] = new File[6];
                FileWriter fw[] = new FileWriter[6];
                f[0] = new File("king.txt");
                f[1] = new File("helpers.txt");
                f[2] = new File("nobility.txt");
                f[3] = new File("gameMap.txt");
                f[4] = new File("nobles.txt");
                f[5] = new File("political.txt");
                    
                    for (int i = 0; i < regions.length; i++){
                        JSONObject json1 =(JSONObject) jArray.get(i); 
                        regions[i] = new RegionDAO().readData(json1);
                        System.out.println("Region[" + i + "] :    "  + regions[i]);
                    }
                    
//                    try{
                    King k = new KingDAO().readData(json);
//                    fw[0] = new FileWriter(f[0]);
//                    fw[0].write(k.toString());
//                    fw[0].close();
                    
                    HelpersPool hp = new HelpersPoolDAO().readData(json);
//                    fw[1] = new FileWriter(f[1]);
//                    fw[1].write(k.toString());
//                    fw[1].close();
//                    JOptionPane.showMessageDialog(null, hp.toString(), "Helpers Pool", JOptionPane.INFORMATION_MESSAGE);
                    
                    NobiltyPath np = new NobiltyPathDAO().readData(json);
//                    fw[2] = new FileWriter(f[2]);
//                    fw[2].write(k.toString());
//                    fw[2].close();
//                    JOptionPane.showMessageDialog(null, np.toString(), "Nobility Path", JOptionPane.INFORMATION_MESSAGE);
                    
                    GameMap gm = new GameMapDAO().readData(json);
//                    fw[3] = new FileWriter(f[3]);
//                    fw[3].write(k.toString());
//                    fw[3].close();
//                    JOptionPane.showMessageDialog(null, gm.toString(), "Game Map", JOptionPane.INFORMATION_MESSAGE);
                    
                    NoblesPool npl = new NoblesPoolDAO().readData(json);
//                    fw[4] = new FileWriter(f[4]);
//                    fw[4].write(k.toString());
//                    fw[4].close();
//                    JOptionPane.showMessageDialog(null, npl.toString(), "Nobles Pool", JOptionPane.INFORMATION_MESSAGE);
                    
                    PoliticalCardsDeck pcd = new PoliticalCardsDeckDAO().readData(json);
//                    fw[5] = new FileWriter(f[5]);
//                    fw[5].write(k.toString());
//                    fw[5].close();
//                    JOptionPane.showMessageDialog(null, pcd.toString(), "Political Card", JOptionPane.INFORMATION_MESSAGE);
                    
  
        
                Board b = new BoardBuilder().setKing(k)
                                         .setPoliticalCardsDeck(pcd)
                                         .setHelpersPool(hp)
                                         .setNobiltyPath(np)
                                         .setGameMap(gm)
                                         .setNoblesPool(npl)
                                         .setRegions(regions)
                                           .build();
               
               return b; //}catch(IOException e){ e.printStackTrace(); } return null;
                    
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(Board board){
		JSONObject jObj = new JSONObject();
                JSONArray jArray = new JSONArray();
                
	
                Region[] regions = board.getRegions();
                
                for(Region r: regions){
                    jArray.add(new RegionDAO().writeData(r));  
                }
                
          
                jObj.put(DAOFields.KING, new KingDAO().writeData(board.getKing()));
                jObj.put(DAOFields.POLITICAL_CARDS_DECK, new PoliticalCardsDeckDAO().writeData(board.getPoliticalCardsDeck(), false));
                jObj.put(DAOFields.HELPERS_POOL, new HelpersPoolDAO().writeData(board.getHelpersPool()));
                jObj.put(DAOFields.NOBILTY_PATH, new NobiltyPathDAO().writeData(board.getNobiltyPath()));
                jObj.put(DAOFields.GAME_MAP, new GameMapDAO().writeData(board.getGameMap()));
                jObj.put(DAOFields.NOBLES_POOL, new NoblesPoolDAO().writeData(board.getNoblesPool()));
                jObj.put(DAOFields.REGIONS, jArray);
                
		JSONObject root = new JSONObject();
                        root.put(DAOFields.BOARD, jObj);
			return root;
	}
    
    
}
