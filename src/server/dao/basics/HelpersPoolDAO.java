/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;


import model.basics.HelpersPool;
import model.basics.builders.HelpersPoolBuilder;
import model.basics.builders.exceptions.BuilderException;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;

/**
 *
 * @author Antonietta
 */
public class HelpersPoolDAO {
    
    public HelpersPool readData(JSONObject jsonHPRoot) throws BuilderException{
		JSONObject jsonHP = (JSONObject)jsonHPRoot.get(DAOFields.HELPERS_POOL);
                
                HelpersPool hp = new HelpersPoolBuilder().setActualGamerHelpers(((Long)jsonHP.get(DAOFields.ACTUAL_GAMER_HELPERS)).intValue())
                                                         .setActualTotal(((Long)jsonHP.get(DAOFields.ACTUAL_TOTAL)).intValue())
                                                         .build();
                return hp;
    }
    
    
    
    
    @SuppressWarnings("unchecked")   
   public JSONObject writeData(HelpersPool helpersPool){
		JSONObject jObj = new JSONObject();
		
		jObj.put(DAOFields.ACTUAL_GAMER_HELPERS, helpersPool.getActualGamerHelpers());
                jObj.put(DAOFields.ACTUAL_TOTAL, helpersPool.getActualTotal());
                
//			JSONObject root = new JSONObject();
//			root.put(DAOFields.HELPERS_POOL, jObj);
//			return root;
		
                 return jObj;
	}
    
}
