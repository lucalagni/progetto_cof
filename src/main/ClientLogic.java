package main;

import model.basics.Board;
import model.basics.Match;

public class ClientLogic {
	
	private static ClientLogic instance;
	private Match match;
    private Board be;
	
	public ClientLogic()
	{
		
	}
	
    public static ClientLogic getInstance()
    {
    	if( instance==null ){
    		instance = new ClientLogic();
    	}
    	return instance;
    }
    
    
    public void setMatch(Match m){
    	match = m;
    	be = match.getBoard();
    }
    
    public Match getMatch(){
    	return match;
    }
    
    public void setBoard(Board be){ // da rimuovere
    	this.be = be;	
    }

    public Board getBoard(){
    	return be;
    }
    
    public void initRMIConnection()
    {
    	// lookup dell'oggetto remoto
    	//    	req = ...
    }
   
}
