package server.manager;

import examples.example1.BoardExample;
import model.basics.Board;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;

public class BoardRetriver {
	
	//Metodo che si occupa ti fornire una nuova board al chiamante
	protected Board retriveBoard() throws BuilderException, GameMapException{
		return new BoardExample().getBoard();
	}
	
	
}
