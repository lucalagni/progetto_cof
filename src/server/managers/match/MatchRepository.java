package server.managers.match;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import model.basics.Gamer;
import model.basics.Match;

public class MatchRepository {
	private static MatchRepository instance = null;
	private HashMap<String, Match> matches;
	private HashSet<String> aloneGamers;
	
	private MatchRepository(){
		this.matches = new HashMap<String,Match>();
		this.aloneGamers = new HashSet<String>();
	}
	
	public synchronized Match getMatch(String matchCode){
		Iterator<Map.Entry<String, Match>> it = this.matches.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, Match> entry = it.next();
			if(entry.getKey().equals(matchCode)) return entry.getValue();
		}
		return null;
	}
	
	public synchronized void addAloneGamer(String gamer){
		this.aloneGamers.add(gamer);
	}
	
	public synchronized void addMatch(Match match){
		this.matches.put(match.getMatchCode(), match);
	}
	
	public synchronized boolean getAloneGamerAssociatedTo(String username){
		for(String gamer : this.aloneGamers){
			System.out.println("\nVerifico");
			System.out.println(username);
			System.out.println(gamer);
			if(gamer.equals(username)) return true;
		}
		
		return false;
	}
	
	public synchronized Match getMatchAssociatedTo(String username){
		Iterator<Map.Entry<String, Match>> it = this.matches.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, Match> entry = it.next();
			for(Gamer g: entry.getValue().getGamers()){
				if(g.getUsername().equals(username)) return entry.getValue();
			}
		}
		
		return null;
	}
	
	public synchronized String getMatchCodeAssociatedTo(String username){
		Match m = this.getMatchAssociatedTo(username);
		
		if(m == null) return null;
		else return m.getMatchCode(); 
	}
	
	public synchronized static MatchRepository getInstance(){
		if(instance == null) instance = new MatchRepository();
		return instance ;
	}
}
