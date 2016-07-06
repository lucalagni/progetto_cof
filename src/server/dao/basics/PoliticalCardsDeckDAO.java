/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.util.ArrayList;
import java.util.Iterator;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import model.basics.PoliticalCard;
import model.basics.PoliticalCardsDeck;
import model.basics.builders.PoliticalCardsDeckBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.supports.QuequedGamer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;


/**
 *
 * @author Antonietta
 */
public class PoliticalCardsDeckDAO {
    @SuppressWarnings("unchecked")
	public  PoliticalCardsDeck readData(JSONObject jsonRoot) throws BuilderException{
                JSONObject jsonCardDeck = (JSONObject)jsonRoot.get(DAOFields.POLITICAL_CARDS_DECK);
                    JSONArray jArray = (JSONArray)jsonCardDeck.get(DAOFields.AVAILABLE_CARDS_LIST);
                  //  Iterator<PoliticalCard> it = jArray.iterator();
		ArrayList<PoliticalCard> availableCardsList = new ArrayList<PoliticalCard>();
                
                for (int i = 0; i < jArray.size(); i++) {
                
                    JSONObject jsonCard =(JSONObject) jArray.get(i);
                    availableCardsList.add(new PoliticalCardDAO().readData(jsonCard)); 
                }
                
                return new PoliticalCardsDeckBuilder().setAvailableCards(availableCardsList)
                                                      .build();	
	}
        
        @SuppressWarnings("unchecked")
	public JSONObject writeData(PoliticalCardsDeck politicalCardsDeck,boolean indipendent){
                JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		Iterator<PoliticalCard> it = politicalCardsDeck.getAvailableCardsList().iterator();

               
                

                while(it.hasNext()){
                    jArray.add(new PoliticalCardDAO().writeData(it.next(), true));
                }
                
                jObj.put(DAOFields.AVAILABLE_CARDS_LIST, jArray);

//                JSONObject root = new JSONObject();
//			
//			root.put(DAOFields.POLITICAL_CARDS_DECK, jObj);
//			return root;
                        
               return jObj;

	}
    
    
}
