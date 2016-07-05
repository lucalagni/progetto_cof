package server.managers.match;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import server.schedulers.match.MatchScheduler;
import model.basics.Gamer;
import model.basics.Match;

//I match semplici (non schedulati) probabilmente possono essere tolti
public class MatchRepository {
	private static MatchRepository instance = null;
	private HashMap<String, Match> matches;
	private HashSet<String> aloneGamers;
	private HashMap<String, MatchScheduler> scheduledMatches;
	
	private MatchRepository(){
		this.matches = new HashMap<String,Match>();
		this.aloneGamers = new HashSet<String>();
		this.scheduledMatches = new HashMap<String,MatchScheduler>();
	}
	
	public synchronized Match getMatch(String matchCode){
		Iterator<Map.Entry<String, Match>> it = this.matches.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, Match> entry = it.next();
			if(entry.getKey().equals(matchCode)) return entry.getValue();
		}
		return null;
	}
	
	public void updateMatch(Match m){
		Iterator<Map.Entry<String, Match>> it = this.matches.entrySet().iterator();
		Iterator<Map.Entry<String, MatchScheduler>> it2 = this.scheduledMatches.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, Match> entry = it.next();
			if(entry.getKey().equals(m.getMatchCode())) entry.setValue(m);
		}
		
		while(it2.hasNext()){
			Map.Entry<String, MatchScheduler> entry = it2.next();
			if(entry.getKey().equals(m.getMatchCode())) entry.getValue().setMatch(m);
		}
	}
	
	public synchronized void addAloneGamer(String gamer){
		this.aloneGamers.add(gamer);
	}
	
	public synchronized void addMatch(Match match){
		this.matches.put(match.getMatchCode(), match);
		this.scheduledMatches.put(match.getMatchCode(), new MatchScheduler(match));
		this.scheduledMatches.get(match.getMatchCode()).matchScheduler();
	}
	
	public synchronized boolean getAloneGamerAssociatedTo(String username){
		for(String gamer : this.aloneGamers){
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
