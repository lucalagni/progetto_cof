package mud.model.basic.interfaces;

import java.rmi.Remote;

import model.basics.PermitCardsDeck;

public interface RemoteRegion extends Remote{
	public int getNumber();
	public RemoteBonus getBonus();
	public RemoteCouncil getCouncil();
	public String[] getVillages();
	public PermitCardsDeck getPermitCardsDeck();
}
