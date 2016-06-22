package main;

import view.basic.cli.client.ViewCLI;
import model.basics.builders.exceptions.BuilderException;

public class ProvaCli {
	public static void main(String[] args) {
		try {
			
			new ViewCLI();
			
		} catch (BuilderException  e) {
			e.printStackTrace();
		}
		
	}
}
