/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.awt.Color;
import java.util.Iterator;
import model.basics.Village;
import model.basics.builders.VillageBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.GamerConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;

/**
 *
 * @author Antonietta
 */
public class VillageDAO {
    
    @SuppressWarnings("unchecked")
	public Village readData(JSONObject jsonRoot) throws BuilderException{
		JSONObject json = (JSONObject)jsonRoot.get(DAOFields.VILLAGE);
		JSONArray jArray = (JSONArray) json.get(DAOFields.SHOPS);
                Iterator<String> it = jArray.iterator();
		String[] shops =  new String[GamerConstants.GAMERS_COLORS.length];
             
		
		for (int i = 0; i < shops.length; i++){
                    while(it.hasNext()) shops[i] = it.next();
                    
                }
		
                int rgb = ((Long)json.get(DAOFields.COLOR)).intValue();
                Color c = new Color(rgb);
                
		return new VillageBuilder().setName(json.get(DAOFields.NAME).toString())
                                           .setColor(c)
                                           .setBonus(new BonusDAO().readData(json))
                                           .setShops(shops)
									  .build();
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(Village village){
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		String[] shops = village.getShops();
		
                for(String s: shops){
                    jArray.add(s);  
                }
                  
                jObj.put(DAOFields.NAME, village.getName());
                jObj.put(DAOFields.COLOR, village.getColor().getRGB());
                jObj.put(DAOFields.BONUS,  new BonusDAO().writeData(village.getBonus(), false));
		jObj.put(DAOFields.SHOPS, jArray);
		
                
		JSONObject root = new JSONObject();
                        root.put(DAOFields.VILLAGE, jObj);
			return root;
	}
    
}
