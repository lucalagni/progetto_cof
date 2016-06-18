package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.basics.Match;

public interface MatchRequest extends Remote {
	/**
	 * invia al server la richiesta di inizio del match
	 * @param user
	 * @return FALSE se ci sono stati problemi di connessione, TRUE altrimenti
	 * @throws RemoteException
	 */
	public Boolean requestMatch(String user) throws RemoteException;
	
	/**
	 * Il client richiede al server se il match è stato creato per l'utente
	 * @param user
	 * @return TRUE se la partita è stata creata, FALSE altrimenti
	 * @throws RemoteException
	 */
	public Boolean isMatchReady(String user) throws RemoteException;
	
	/**
	 * Una volta creata la partita nel server, con questo metodo è possibile
	 * prelevare il Match associato ad un certo User.
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	public Match getMatch(String user) throws RemoteException;
	
}
