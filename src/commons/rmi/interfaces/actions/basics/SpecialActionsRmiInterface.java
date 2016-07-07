package commons.rmi.interfaces.actions.basics;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;
import commons.messages.ServerMessage;

/**
 * Classe per la richiesta di esecuzione di azioni speciali attraverso la tecnologia RMI
 * 
 * @author Luca Lagni
 *
 */
public interface SpecialActionsRmiInterface extends Remote{
	public ServerMessage reusePermitCardBonus(UserData data, Integer permitCardPosition,Boolean usedPermitCard) throws RemoteException;
	public ServerMessage acquireDoubleVillageBonus(UserData data, Character firstVillageFirstLetter, Character secondVillageFirstLetter) throws RemoteException;
	public ServerMessage acquireSingleVillageBonus(UserData data, Character villageFirstLetter) throws RemoteException;
	public ServerMessage acquirePermitCard(UserData data, Integer regionNumber,Integer permitCardNumber) throws RemoteException;
}
