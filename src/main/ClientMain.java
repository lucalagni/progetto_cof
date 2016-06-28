package main;

import view.View;

public class ClientMain {
	public static void main(String args[]){
		boolean setted = false ;
		String username = null;
		View view = new View();
		
		setted = view.viewGameModeSetup();
		if(setted == true){
			username = view.viewSetupUsername();
		}
		System.out.println("\nExited");
	}
}
