package main;

import java.awt.Color;

import model.basics.Board;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import examples.example1.BoardExample;



public class Main {
	public static void main(String[] args) {
		try {
			Board be = new BoardExample().getBoard();
			
			be.toString();
			//Region r = new Region1Example().getRegion();
			System.out.println(be.toString());
		} catch (BuilderException | GameMapException ge) {
			// TODO Auto-generated catch block
			ge.printStackTrace();
		}
	}
}
