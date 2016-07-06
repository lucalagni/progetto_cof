/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.util.Iterator;
import model.basics.Bonus;
import model.basics.King;
import model.basics.builders.KingBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.KingConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;


/**
 *
 * @author Antonietta
 */
public class KingDAO {
    
    
    @SuppressWarnings("unchecked")
	public King readData(JSONObject jsonRoot) throws BuilderException{
		JSONObject json = (JSONObject)jsonRoot.get(DAOFields.KING);
		JSONArray jArray = (JSONArray) json.get(DAOFields.BONUS);
		Bonus[] bonus =  new Bonus[KingConstants.KING_BONUS];
             
	
                    for (int i = 0; i < bonus.length; i++){
                        JSONObject jsonCard =(JSONObject) jArray.get(i); 
                        bonus[i] = new BonusDAO().readData(jsonCard);
                        System.out.println("bonus[" + i + "] :" + bonus[i].toString());
                    }
                
        
        
                return new KingBuilder().setCouncil(new CouncilDAO().readData(json))
                                           .setPosition(json.get(DAOFields.POSITION).toString())
                                           .setBonus(bonus)
                                           .build();
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(King king){
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		Bonus[] bonus = king.getBonus();
		
                for(Bonus b: bonus){
                    jArray.add(new BonusDAO().writeData(b, true));  
                }
                  
                jObj.put(DAOFields.COUNCIL, new CouncilDAO().writeData(king.getCouncil()));
                jObj.put(DAOFields.POSITION, king.getPosition());
                jObj.put(DAOFields.BONUS, jArray);
		
//                
//		JSONObject root = new JSONObject();
//                        root.put(DAOFields.KING, jObj);
//			return root;

                return jObj;
	}
    
}
