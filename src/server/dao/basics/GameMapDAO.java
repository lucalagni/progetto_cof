/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import model.basics.Bonus;
import model.basics.GameMap;
import model.basics.Village;
import model.basics.builders.GameMapBuilder;
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
public class GameMapDAO {
    
      
    @SuppressWarnings("unchecked")
	public GameMap readData(JSONObject jsonRoot) throws BuilderException, GameMapException{
		JSONObject json = (JSONObject)jsonRoot.get(DAOFields.GAME_MAP);
		JSONArray jArrayB = (JSONArray) json.get(DAOFields.BONUS);
                JSONArray jArrayV = (JSONArray) json.get(DAOFields.VILLAGES);
                JSONArray jArrayC = (JSONArray) json.get(DAOFields.CONNECTIONS);
		Bonus[] bonus =  new Bonus[GameMapConstants.COLOR_BONUS.length];
                Village[] villages = new Village[15];
                int[][] connections = new int[VillageConstants.VILLAGES_NAME.length][VillageConstants.VILLAGES_NAME.length];
                
             
	
                    for (int i = 0; i < bonus.length; i++){
                        JSONObject json1 =(JSONObject) jArrayB.get(i); 
                        bonus[i] = new BonusDAO().readData(json1);
                    }
                    
                    for (int i = 0; i < villages.length; i++){
                        JSONObject json2 =(JSONObject) jArrayV.get(i); 
                        villages[i] = new VillageDAO().readData(json2);
                    }
                    
                    for(int i = 0; i < connections.length; i++){
                        for(int j = 0; j < connections[i].length; j++){
                            int n = i+j;
                            int c = Integer.parseInt(String.valueOf(jArrayC.get(i+j)));
                            connections[i][j] = c;   
                        }
                    } 
                    
                
        
        
                return new GameMapBuilder().setBonus(bonus)
                                           .setVillages(villages)
                                           .setConnections(connections)
                                           .build();
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(GameMap gameMap){
		JSONObject jObj = new JSONObject();
		JSONArray jArrayB = new JSONArray();
                JSONArray jArrayV = new JSONArray();
                JSONArray jArrayC = new JSONArray();
		Bonus[] bonus = gameMap.getColorsBonus();
                Village[] villages = gameMap.getVillages();
                
                
		
                for(Bonus b: bonus){
                    jArrayB.add(new BonusDAO().writeData(b, true));  
                }
                
                
                for(Village v: villages){
                    jArrayV.add(new VillageDAO().writeData(v));  
                }
                
                for(int i = 0; i < gameMap.getConnections().length; i++){
                    for(int j = 0; j < gameMap.getConnections()[i].length; j++){
                        jArrayC.add(gameMap.getConnections()[i][j]);
                    }
                }

                  
               
                jObj.put(DAOFields.BONUS, jArrayB);
                jObj.put(DAOFields.VILLAGES, jArrayV);
                jObj.put(DAOFields.CONNECTIONS, jArrayC);
                
		
//                
//		JSONObject root = new JSONObject();
//                        root.put(DAOFields.GAME_MAP, jObj);
//			return root;

            return jObj;
	}
    
}
