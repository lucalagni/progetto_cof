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
		JSONArray jArray = (JSONArray) jsonDataRoot.get(DAOFields.COUNCIL);
		Iterator<Color> it = jArray.iterator();
		Color[] nobles = new Color[CouncilConstants.NOBLES_NUMBER];
		for(int i = 0; i < CouncilConstants.NOBLES_NUMBER; i++) nobles[i] = it.next();
       
		return new CouncilBuilder().setNobles(nobles).build();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject writeData(Council council){
		JSONObject root = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		for(Color c: council.getNobles()) jArray.add(c);
		root.put(DAOFields.COUNCIL, jArray);
                return root;
	}   
}

