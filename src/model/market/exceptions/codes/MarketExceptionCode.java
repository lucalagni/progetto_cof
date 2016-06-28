package model.market.exceptions.codes;

import java.io.Serializable;

/**
 * enumerazione che indica le eccezioni lanciabili all'interno del market
 * AGENT_NOT_FOUND = l'agente non è stato trovato nel market associato a tale match
 * INVALID_AGENTS = gli agenti coinvolti non sono validi (sono lo stesso agente).
 * ITEM_NOT_FOUND = Oggetto non trovato
 * ITEM_NOT_AVAILABLE = oggetto non disponibile
 * BUYER_HAS_TOO_FEAW_COINS = Il compratore non ha abbastanza soldi
 * SELLER_HAS_TOO_FEAW_HELPERS = Il venditore non ha abbastanza aiutanti per il lotto
 * AGENT_ALREADY_PRESENT = l'agente è gia presente nel market
 * @author Luca Lagni
 *
 */

public enum MarketExceptionCode implements Serializable{
	
	AGENT_NOT_FOUND("AGENT_NOT_FOUND"),
	INVALID_AGENTS("INVALID_AGENTS"),
	ITEM_NOT_FOUND("ITEM_NOT_FOUND"),
	ITEM_NOT_AVAILABLE("ITEM_NOT_AVAILABLE"),
	BUYER_HAS_TOO_FEAW_COINS("BUYER_HAS_TOO_FEAW_COINS"),
	SELLER_HAS_TOO_FEAW_HELPERS("SELLER_HAS_TOO_FEAW_HELPERS"),
	AGENT_ALREADY_PRESENT("AGENT_ALREADY_PRESENT");
	
	private String excpetionCode;
	MarketExceptionCode(String exceptionCode){}
	private void setExceptionCode(String exceptionCode){ this.excpetionCode = exceptionCode; }
	public String getExceptionCode(){ return this.excpetionCode; }
}
