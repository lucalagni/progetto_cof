package model.market.exceptions;

import java.io.Serializable;

public class MarketException extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;
	public MarketException(String s){ super(s); }

}
