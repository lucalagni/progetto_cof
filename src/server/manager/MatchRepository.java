package server.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.basics.Match;

public class MatchRepository {
	private static MatchRepository instance = null;
	private HashMap<String, Match> matches;
	
	private MatchRepository(){
		this.matches = new HashMap<String,Match>();
	}
	
	public Match getMatch(String matchCode){
		Iterator<Map.Entry<String, Match>> it = this.matches.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, Match> entry = it.next();
			if(entry.getKey().equals(matchCode)) return entry.getValue();
		}
		return null;
	}
	
	public void addMatch(Match match){
		this.matches.put(match.getMatchCode(), match);
	}
	
	public static MatchRepository getInstance(){
		if(instance == null) instance = new MatchRepository();
		return instance ;
	}
}
