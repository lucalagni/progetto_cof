/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import model.basics.Bonus;
import model.basics.King;
import model.basics.NobiltyPath;
import model.basics.builders.KingBuilder;
import model.basics.builders.NobiltyPathBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.KingConstants;
import model.basics.constants.NobiltyPathConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;

/**
 *
 * @author Antonietta
 */
public class NobiltyPathDAO {
    
      @SuppressWarnings("unchecked")
	public NobiltyPath readData(JSONObject jsonRoot) throws BuilderException{
		JSONObject json = (JSONObject)jsonRoot.get(DAOFields.NOBILTY_PATH);
		JSONArray jArray = (JSONArray) json.get(DAOFields.BONUS);
		Bonus[] bonus =  new Bonus[NobiltyPathConstants.TOTAL_TILES];
             
	
                    for (int i = 0; i < bonus.length; i++){
                        JSONObject jsonCard =(JSONObject) jArray.get(i); 
                        bonus[i] = new BonusDAO().readData(jsonCard);
                        System.out.println("bonus[" + i + "] :" + bonus[i].toString());
                    }
                
        
        
                return new NobiltyPathBuilder().setBonus(bonus)
                                               .build();
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(NobiltyPath np){
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		Bonus[] bonus = np.getBonus();
		
                for(Bonus b: bonus){
                    jArray.add(new BonusDAO().writeData(b, true));  
                }
               
                jObj.put(DAOFields.BONUS, jArray);
		
                
//		JSONObject root = new JSONObject();
//                        root.put(DAOFields.NOBILTY_PATH, jObj);
//			return root;
//                        
              return jObj;
	}
    
}
