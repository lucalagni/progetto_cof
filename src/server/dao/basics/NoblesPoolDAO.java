/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import model.basics.NoblesPool;
import model.basics.builders.exceptions.BuilderException;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;

/**
 *
 * @author Antonietta
 */
public class NoblesPoolDAO {
    
    
    public NoblesPool readData(JSONObject jsonNPRoot) throws BuilderException{
		JSONObject jsonNP = (JSONObject)jsonNPRoot.get(DAOFields.NOBLES_POOL);
                
                NoblesPool np = new NoblesPool(((Long)jsonNP.get(DAOFields.BLACK_NOBLES_LEFT)).intValue(),
                                               ((Long)jsonNP.get(DAOFields.WHITE_NOBLES_LEFT)).intValue(),
                                               ((Long)jsonNP.get(DAOFields.CYAN_NOBLES_LEFT)).intValue(),
                                               ((Long)jsonNP.get(DAOFields.PYNK_NOBLES_LEFT)).intValue(),
                                               ((Long)jsonNP.get(DAOFields.MAGENTA_NOBLES_LEFT)).intValue(),
                                               ((Long)jsonNP.get(DAOFields.ORANGE_NOBLES_LEFT)).intValue());
     
                return np;
    }
    
    
    
    
    @SuppressWarnings("unchecked")   
   public JSONObject writeData(NoblesPool noblesPool){
		JSONObject jObj = new JSONObject();
		
		jObj.put(DAOFields.BLACK_NOBLES_LEFT, noblesPool.getBlackNoblesLeft());
                jObj.put(DAOFields.WHITE_NOBLES_LEFT, noblesPool.getWhiteNoblesLeft());
                jObj.put(DAOFields.CYAN_NOBLES_LEFT, noblesPool.getCyanNoblesLeft());
                jObj.put(DAOFields.PYNK_NOBLES_LEFT, noblesPool.getPinkNoblesLeft());
                jObj.put(DAOFields.MAGENTA_NOBLES_LEFT, noblesPool.getMagentaNoblesLeft());
                jObj.put(DAOFields.ORANGE_NOBLES_LEFT, noblesPool.getOrangeNoblesLeft());
	
		
		
//			JSONObject root = new JSONObject();
//			root.put(DAOFields.NOBLES_POOL, jObj);
//			return root;
		
                 return jObj;
	}
}
