package main;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.basics.Board;
import model.basics.Gamer;
import model.basics.Match;
import model.market.Market;

public class ClientLogic {
	
    private static ClientLogic instance;
    private final ObjectProperty<Match> match; //new 23.06
	//private Match match;
    private Board be;
    private String username;
    private Gamer gamer;
    private Market market;

	
	public ClientLogic()
	{
		match = new SimpleObjectProperty<Match>(null); 
	}
	
    public static ClientLogic getInstance()
    {
    	if( instance==null ){
    		instance = new ClientLogic();
    	}
    	return instance;
    }
    
    
    public void setMatch(Match m){
    	// match = m;
    	
    	//NEW 23.06
    	this.match.set(m);
    	if( match!=null ){
    		gamer=null;
    		for( Gamer g:getMatch().getGamers() ){
    			System.out.format("G: %s\nUser: %s\n", g.getUsername(), username);
    			if( g.getUsername().equals(username) ){
    				gamer=g;
    				break;
    			}
    		}
        market = getMatch().getMarket();
    	}
    }
    
    public Match getMatch(){
    	//return match;
    	
    	 //NEW 23.06
    	return match.get();
    	
    }
    //NEW 23.06
    public ObjectProperty<Match> matchProperty() {
        return match;
    }
    
    public void setUsername(String username){ 
    	this.username = username;	
    	
    }

    public String getUsername(){
    	return username;
    }
    
    public Gamer getGamer(){
    	return gamer;
    }
  
    
    public Market getMarket(){
        return market;
    }
    
    
    
    public void initRMIConnection()
    {
    	// lookup dell'oggetto remoto
    	//    	req = ... 
    }
   
}
