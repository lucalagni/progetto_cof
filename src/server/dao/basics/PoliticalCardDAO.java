/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;
import java.awt.Color;

import model.basics.PoliticalCard;
import model.basics.builders.PoliticalCardBuilder;
import model.basics.builders.exceptions.BuilderException;

import org.json.simple.JSONObject;

import server.dao.basics.fields.DAOFields;

public class PoliticalCardDAO {

	public PoliticalCard readData(JSONObject jsonRoot) throws BuilderException{
		JSONObject jsonCard = (JSONObject)jsonRoot.get(DAOFields.POLITICAL_CARD);
                int rgb = ((Long)jsonCard.get(DAOFields.COLOR)).intValue();
                Color c = new Color(rgb);
               
            return new PoliticalCardBuilder().setColor(c)
                                             .setJolly((boolean)jsonCard.get(DAOFields.JOLLY))
                                             .build();     
	}
        
      
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(PoliticalCard card, boolean indipendent){
		JSONObject jObj = new JSONObject();
		
		jObj.put(DAOFields.COLOR, card.getColor().getRGB());
		jObj.put(DAOFields.JOLLY, card.getJolly());
              
                
                if(indipendent == true){
			JSONObject root = new JSONObject();  
                        root.put(DAOFields.POLITICAL_CARD, jObj);
			return root;
		}
		
		return jObj;
	}
}
