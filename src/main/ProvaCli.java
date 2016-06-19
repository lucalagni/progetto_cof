package main;

import view.basic.cli.client.AddGamer;
import model.basics.Match;
import model.basics.PoliticalCard;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;
import examples.example1.MatchExample;

public class ProvaCli {
	public static void main(String[] args) {
		try {
			//Match m = new MatchExample().getMatch();
			//System.out.println(m.toString());
			
			new AddGamer();
			
		} catch (BuilderException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
