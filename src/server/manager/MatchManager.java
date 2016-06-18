package server.manager;

import java.util.ArrayList;
import java.util.Timer;

import model.basics.Match;
import model.basics.constants.MatchConstants;

public class MatchManager {
	private ArrayList<Match> matches;
	private ArrayList<String> gamersQueque;
	private Thread timer;
	
	public MatchManager(){
		this.matches = new ArrayList<Match>();
		this.gamersQueque = new ArrayList<String>();
	}
	
	public void addGamer(String gamer){ 
		this.gamersQueque.add(gamer); 
		if(this.gamersQueque.size() >= MatchConstants.MIN_NUMBER_OF_GAMERS_TO_PLAY){
			//timer.start();
			timer = genTimer();
			timer.start();
			
		}
	}
	
	private Thread genTimer(){
		return new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(MatchConstants.MATCH_TIMEOUT * 1000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
	}
	
	private void genMatches(){
		ArrayList<String> users = this.gamersQueque;
		this.gamersQueque = new ArrayList<String>();
		int numMatches = (users.size() / MatchConstants.MID_NUMBER_OF_GAMERS);
		MatchGenerator[] mg ;
		
		if(numMatches > 0) mg = new MatchGenerator[numMatches];
		else mg = new MatchGenerator[1];
		
		if(numMatches == 0){
			
		}
		else{
			for(int i = 0; i < numMatches; i++){
				matches.add(new MatchGenerator());
			}
		}
	}
	
	
}
