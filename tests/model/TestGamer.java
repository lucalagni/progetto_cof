package model;

import static org.junit.Assert.*;
import model.basics.Gamer;
import model.basics.Match;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;

import org.junit.Test;

import examples.example1.MatchExample;

public class TestGamer {

	@Test
	public void test() 
	{
		try {
			Match m = new MatchExample().getMatch();
//			m.getBoard().
			
		} catch (BuilderException | MatchException | GameMapException
				| PoliticalCardsDeckException e) {
			System.out.println("[TestGamer] ERRORE inizializzazione Match");
			assertTrue(false);
		}
		
	}

}
