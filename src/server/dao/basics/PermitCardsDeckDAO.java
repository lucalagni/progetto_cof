/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.util.ArrayList;
import java.util.Iterator;
import model.basics.PermitCard;
import model.basics.PermitCardsDeck;
import model.basics.builders.PermitCardsDeckBuilder;
import model.basics.builders.exceptions.BuilderException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;

/**
 *
 * @author Antonietta
 */
public class PermitCardsDeckDAO {
    
     @SuppressWarnings("unchecked")
	public  PermitCardsDeck readData(JSONObject jsonRoot) throws BuilderException{
                JSONObject jsonCardDeck = (JSONObject)jsonRoot.get(DAOFields.PERMIT_CARDS_DECK);
                JSONArray jArray = (JSONArray)jsonCardDeck.get(DAOFields.AVAILABLE_CARDS_SET);
                JSONArray jArray2 = (JSONArray)jsonCardDeck.get(DAOFields.UNHIDDENCARDS);
		ArrayList<PermitCard> availableCardsList = new ArrayList<PermitCard>();
                PermitCard[] unhiddenCards = new PermitCard[2];
                
                for (int i = 0; i < jArray.size(); i++){
                
                    JSONObject jsonCard =(JSONObject) jArray.get(i);
                    availableCardsList.add(new PermitCardDAO().readData(jsonCard)); 
                }
                
                for (int i = 0; i < jArray2.size(); i++){
                
                    JSONObject jsonCard =(JSONObject) jArray2.get(i);
                    unhiddenCards[i] = (new PermitCardDAO().readData(jsonCard)); 
                }
                
                
                
                return new PermitCardsDeckBuilder().setAvailableCardsSet(availableCardsList)
                                                   .setUnhiddenCards(unhiddenCards)
                                                   .build();	
	}
        
        @SuppressWarnings("unchecked")
	public JSONObject writeData(PermitCardsDeck permitCardsDeck,boolean indipendent){
                JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
                JSONArray jArray2 = new JSONArray();
		Iterator<PermitCard> it = permitCardsDeck.getAvailableCardsList().iterator();
                PermitCard[] unhiddenCards = permitCardsDeck.getUnhiddenCards();

                while(it.hasNext()){
                    jArray.add(new PermitCardDAO().writeData(it.next(), true));  
                }
                
                for(PermitCard c: unhiddenCards){
                    jArray2.add(new PermitCardDAO().writeData(c, true));  
                }
                
                jObj.put(DAOFields.AVAILABLE_CARDS_SET, jArray);
                jObj.put(DAOFields.UNHIDDENCARDS, jArray2);
                
                
//                JSONObject root = new JSONObject();
//			
//			root.put(DAOFields.PERMIT_CARDS_DECK, jObj);
//			return root
                return jObj;
        }
    
}
