/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.util.HashSet;
import java.util.Iterator;
import model.basics.PermitCard;
import model.basics.builders.PermitCardBuilder;
import model.basics.builders.exceptions.BuilderException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;
import server.dao.basics.fields.DAOFields;


/**
 *
 * @author Antonietta
 */
public class PermitCardDAO {
    
    
	@SuppressWarnings("unchecked")
	public PermitCard readData(JSONObject jsonRoot) throws BuilderException{
		JSONObject jsonCard = (JSONObject)jsonRoot.get(DAOFields.PERMIT_CARD);
		JSONArray jArray = (JSONArray) jsonCard.get(DAOFields.VILLAGES);
		Iterator<String> it = jArray.iterator();
		HashSet<String> villages = new HashSet<String>();
		
		while(it.hasNext()) villages.add(it.next());
		
		return new PermitCardBuilder().setBonus(new BonusDAO().readData(jsonCard))
                                                                      .setVillages(villages)
                                                                      .build();
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(PermitCard permitCard,boolean indipendent){
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		Iterator<String> it = permitCard.getVillagesName().iterator();
		
		while(it.hasNext()) jArray.add(it.next());
          
		jObj.put(DAOFields.VILLAGES, jArray);
		jObj.put(DAOFields.BONUS, new BonusDAO().writeData(permitCard.getBonus(), false));
		
		if(indipendent == true){
			JSONObject root = new JSONObject();
			
			root.put(DAOFields.PERMIT_CARD, jObj);
			return root;
		}
		return jObj;
	}
    
}
