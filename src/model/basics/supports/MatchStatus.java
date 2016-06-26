package model.basics.supports;

import java.io.Serializable;

/**
 * Enumerazione che contiene le informazioni sullo stato del match
 * 
 * ACTIVE = il match è in corso
 * FREEZED = il match è sospeso per assenza di giocatori
 * TERMINATED = il match è finito
 * @author Luca Lagni
 *
 */
public enum MatchStatus implements Serializable{
	ACTIVE("MS.ACTIVE"),
	FREEZED("MS.FREEZED"),
	TERMINATED("MS.TERMINATED");
	
	private String statusCode;
	
	MatchStatus(String statusCode){ this.setStatusCode(statusCode); }
	
	private void setStatusCode(String statusCode){ this.statusCode = statusCode; }
	public String getStatusCode(){ return this.statusCode; }
}
