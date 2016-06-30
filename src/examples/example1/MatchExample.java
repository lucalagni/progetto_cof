package examples.example1;

import java.util.ArrayList;

import model.basics.Board;
import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.Match;
import model.basics.builders.MatchBuilder;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PermitCardsDeckException;
import model.basics.exceptions.PoliticalCardsDeckException;
import model.basics.supports.MatchStatus;

public class MatchExample {
	
	public Match getMatch() throws BuilderException, MatchException, GameMapException, PoliticalCardsDeckException{
		Match m = null;
		Board b = new BoardExample().getBoard();
		ArrayList<PoliticalCard> pc1 = new ArrayList<PoliticalCard>();
		ArrayList<PoliticalCard> pc2 = new ArrayList<PoliticalCard>();
		ArrayList<Gamer> gamers = new ArrayList<Gamer>();
		
		b.getPoliticalCardsDeck().shuffleCards();
		//for(int i = 0; i <= GamerConstants.INITIAL_NUMBER_OF_CARDS; i++) pc1.add(b.getPoliticalCardsDeck().pickupCard());
		for(int i = 0; i <= 10; i++) pc1.add(b.getPoliticalCardsDeck().pickupCard());
		
		b.getPoliticalCardsDeck().shuffleCards();
		//for(int i = 0; i < GamerConstants.INITIAL_NUMBER_OF_CARDS; i++) pc2.add(b.getPoliticalCardsDeck().pickupCard());
		for(int i = 0; i < 10; i++) pc2.add(b.getPoliticalCardsDeck().pickupCard());
		
		
		ArrayList<PermitCard> pc = new ArrayList<PermitCard>() ;
		
		try {
			pc.add(b.getRegions()[0].getPermitCardsDeck().pickupCard(0));
			pc.add(b.getRegions()[0].getPermitCardsDeck().pickupCard(1));
			pc.add(b.getRegions()[1].getPermitCardsDeck().pickupCard(0));
			pc.add(b.getRegions()[1].getPermitCardsDeck().pickupCard(1));
			pc.add(b.getRegions()[2].getPermitCardsDeck().pickupCard(0));
			
		} catch (PermitCardsDeckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	ArrayList<PermitCard> pcu = new ArrayList<PermitCard>() ;
		
		try {
			pcu.add(b.getRegions()[1].getPermitCardsDeck().pickupCard(0));
			pcu.add(b.getRegions()[2].getPermitCardsDeck().pickupCard(1));
			pcu.add(b.getRegions()[0].getPermitCardsDeck().pickupCard(0));
			pcu.add(b.getRegions()[2].getPermitCardsDeck().pickupCard(0));
			pcu.add(b.getRegions()[1].getPermitCardsDeck().pickupCard(1));
			pcu.add(b.getRegions()[0].getPermitCardsDeck().pickupCard(1));
			
		} catch (PermitCardsDeckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gamer g1 = new Gamer1Example("1", pc1, pc, pcu).getGamer1();
		Gamer g2 = new Gamer2Example("1", pc2, new ArrayList<PermitCard>(), new ArrayList<PermitCard>()).getGamer2();
		
		
		gamers.add(g1);
		gamers.add(g2);
		
		m = new MatchBuilder().setTitle("Classic Match")
							  .setMatchCode("1")
							  .setBoard(new BoardExample().getBoard())
							  .setStatus(MatchStatus.ACTIVE)
							  .setGamers(gamers)
							  .setActualGamer(0)
							  .build();
		
		return m;
	}
}
