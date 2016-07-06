/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import model.basics.Board;
import model.basics.GameMap;
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
              
            
                    
                    for (int i = 0; i < regions.length; i++){
                        JSONObject json1 =(JSONObject) jArray.get(i); 
                        regions[i] = new RegionDAO().readData(json1);
                        System.out.println("Region[" + i + "] :    "  + regions[i]);
                    }
  
        
                return new BoardBuilder().setKing(new KingDAO().readData(json))
                                         .setPoliticalCardsDeck(new PoliticalCardsDeckDAO().readData(json))
                                         .setHelpersPool(new HelpersPoolDAO().readData(json))
                                         .setNobiltyPath(new NobiltyPathDAO().readData(json))
                                         .setGameMap(new GameMapDAO().readData(json))
                                         .setNoblesPool(new NoblesPoolDAO().readData(json))
                                         .setRegions(regions)
                                           .build();
		
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
