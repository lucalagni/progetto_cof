package commons.rmi.interfaces.actions.basics;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;
import commons.messages.ServerMessage;

/**
 * Interfaccia per la comunicazione in RMI delle azioni speciali
 * @author Luca Lagni
 *
 */

public interface HelpersActionsRmiInterface extends Remote{
	public ServerMessage buyHelper(UserData data) throws RemoteException ;
	public ServerMessage changeNoble(UserData data, Boolean isKing,Integer regionNumber,Color noble) throws RemoteException;
	public ServerMessage doubleAction(UserData data, Integer regionNumber) throws RemoteException ;
	public ServerMessage buyNewMainAction(UserData data) throws RemoteException;
}
