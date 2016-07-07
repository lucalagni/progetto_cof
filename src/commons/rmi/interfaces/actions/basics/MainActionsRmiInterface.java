package commons.rmi.interfaces.actions.basics;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;
import commons.messages.ServerMessage;

/**
 * Interfaccia per l'implementazione delle azioni principali in RMI
 * 
 * @author Luca Lagni
 *
 */
public interface MainActionsRmiInterface extends Remote{
	public ServerMessage placeShop(UserData data, Integer permitCardPosition,Character villageFirstLetter) throws RemoteException;
	public ServerMessage pickupPermitCard(UserData data, Integer regionNumber,Integer permitCardNumber,Integer politicalCardsPosition[]) throws RemoteException;
	public ServerMessage moveKing(UserData data, Integer politicalCardsPosition[],Character villagePath[]) throws RemoteException ;
	public ServerMessage changeNoble(UserData data, Boolean isKing,Integer regionNumber,Color noble) throws RemoteException;
}
