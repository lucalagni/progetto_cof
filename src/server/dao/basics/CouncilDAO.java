/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao.basics;

import java.awt.Color;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.basics.Council;
import server.dao.basics.fields.DAOFields;
import model.basics.builders.CouncilBuilder;
import model.basics.constants.CouncilConstants;

/**
 *
 * @author Antonietta
 */
public class CouncilDAO {
	
	@SuppressWarnings("unchecked")
	public Council readData(JSONObject jsonDataRoot){
                JSONObject json = (JSONObject) jsonDataRoot.get(DAOFields.COUNCIL);
		JSONArray jArray = (JSONArray) json.get(DAOFields.NOBLES);
		Color[] nobles = new Color[CouncilConstants.NOBLES_NUMBER];
                 Iterator<String> it = jArray.iterator();


            while(it.hasNext()){
                for (int i = 0; i < nobles.length; i++){
                int rgb = Integer.parseInt(String.valueOf(it.next()));
                Color c = new Color(rgb);
                nobles[i] = c;       
                }          
            }

		return new CouncilBuilder().setNobles(nobles).build();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(Council council){
		JSONObject root = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		for(Color c: council.getNobles()) jArray.add(c.getRGB());
		root.put(DAOFields.NOBLES, jArray);
                return root;
	}   
}

