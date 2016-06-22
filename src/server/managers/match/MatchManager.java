package server.managers.match;

import java.util.ArrayList;

import model.basics.Match;
import model.basics.builders.exceptions.BuilderException;
import model.basics.constants.MatchConstants;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;

public class MatchManager {
	private Thread timer;
	private ArrayList<String> gamersQueque;
	private MatchRepository matchRepository;
	private static MatchManager instance = null;
	
	private MatchManager(){
		this.gamersQueque = new ArrayList<String>();
		this.matchRepository = MatchRepository.getInstance();
	}
	
	public void addGamer(String gamer){ 
		this.gamersQueque.add(gamer); 
		if(this.gamersQueque.size() >= MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY){
			timer = genTimer();
			timer.start();
		}
	}
	
	private Thread genTimer(){
		return new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(MatchConstants.MATCH_TIMEOUT * 1000);
					genMatches();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}});
	}
	
	private void genMatches(){
		ArrayList<String> users = this.gamersQueque;
		this.gamersQueque = new ArrayList<String>();
		int numMatches = (users.size() / MatchConstants.MID_NUMBER_OF_GAMERS);
		MatchGenerator[] mg ;
		Match m ;
		int index = 0;
		int len = users.size();
		
		if(numMatches > 0) mg = new MatchGenerator[numMatches];
		else mg = new MatchGenerator[1];
		
		if(numMatches == 0){
			try {
				mg[0] = new MatchGenerator();
				for(String username : this.gamersQueque){
					mg[0].addGamer(username);
				}
				
				m = mg[0].createMatch();
				this.matchRepository.addMatch(m);
			} catch (BuilderException | GameMapException | PoliticalCardsDeckException | MatchException e) {
				e.printStackTrace();
			}
		}
		else{
			//definisco quanti match creare
			for(int i = 0; i < numMatches; i++){
				try {
					mg[i] = new MatchGenerator();
					for(int j = 0; j < len; j++){
						if(index >= len) break;
						mg[i].addGamer(users.get(j));
						users.remove(j);
						index++;
					}
				} catch (BuilderException | GameMapException | PoliticalCardsDeckException e) { e.printStackTrace(); }
			}
			
			//Nel caso non abbia aggiunto tutti gli utenti, vado a distribuire i rimanenti sulle partite
			for(int i = 0; i < numMatches; i++){
				if(users.size() > 0){
					try {
						mg[i].addGamer(users.get(0));
						users.remove(0);
					} catch (PoliticalCardsDeckException e) {
						e.printStackTrace();
					}
					
				}
			}
			
			//aggiungo i match appena creati al repository
			for(MatchGenerator matchGen: mg){
				try {
					m = matchGen.createMatch();
					this.matchRepository.addMatch(m);
				} catch (BuilderException | PoliticalCardsDeckException
						| GameMapException | MatchException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static MatchManager getInstance(){
		if(instance == null) instance = new MatchManager();
		return instance ;
	}
}
