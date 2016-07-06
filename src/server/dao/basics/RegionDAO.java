/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.util.Iterator;
import model.basics.Region;
import model.basics.builders.RegionBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.RegionConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;

/**
 *
 * @author Antonietta
 */
public class RegionDAO {
    
    
    @SuppressWarnings("unchecked")
	public Region readData(JSONObject jsonRoot) throws BuilderException{
		JSONObject json = (JSONObject)jsonRoot.get(DAOFields.REGION);
		JSONArray jArray = (JSONArray) json.get(DAOFields.VILLAGES);
                Iterator<String> it = jArray.iterator();
		String[] villages =  new String[RegionConstants.NUMBER_OF_VILLAGES_FOR_REGION];
             
		
		for (int i = 0; i < villages.length; i++){
                    while(it.hasNext()) villages[i] = it.next();
                    
                }
		
                
		return new RegionBuilder().setNumber(((Long)json.get(DAOFields.NUMBER)).intValue())
                                          .setBonus(new BonusDAO().readData(json))
                                          .setCouncil(new CouncilDAO().readData(json))
                                          .setVillages(villages)
                                          .setPermitCardDeck(new PermitCardsDeckDAO().readData(json))
                                          .build();
		
	}
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
	public JSONObject writeData(Region region){
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		String[] villages = region.getVillages();
		
                for(String v: villages){
                    jArray.add(v);  
                }
                  
                jObj.put(DAOFields.NUMBER, region.getNumber()); 
                jObj.put(DAOFields.BONUS,  new BonusDAO().writeData(region.getBonus(), false));
                jObj.put(DAOFields.COUNCIL, new CouncilDAO().writeData(region.getCouncil()));
                jObj.put(DAOFields.PERMIT_CARDS_DECK, new PermitCardsDeckDAO().writeData(region.getPermitCardsDeck(), false));
		jObj.put(DAOFields.VILLAGES, jArray);
		
                
		JSONObject root = new JSONObject();
                        root.put(DAOFields.REGION, jObj);
			return root;
//                return jObj;
	}
    
}
