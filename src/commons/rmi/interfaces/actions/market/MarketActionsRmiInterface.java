package commons.rmi.interfaces.actions.market;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commons.data.UserData;
import commons.messages.ServerMessage;

/**
 * Interfacc
 * @author lucal
 *
 */
public interface MarketActionsRmiInterface extends Remote{
	public ServerMessage buyHelpersItem(UserData data, Integer sellerIndex,Integer helpersItemIndex) throws RemoteException;
	public ServerMessage buyPoliticalCardsItem(UserData data, Integer sellerIndex,Integer politicalCardItemIndex) throws RemoteException;
	public ServerMessage buyPermitCardItem(UserData data, Integer sellerIndex,Integer permitCardItemIndex)throws RemoteException;
}
