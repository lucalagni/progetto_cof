package mud.model.basic.interfaces;

import java.rmi.Remote;
import java.util.HashSet;

public interface RemotePermitCard extends Remote{
	public boolean verifyVillage(RemoteVillage village);
	public RemoteBonus getBonus();
	public HashSet<String> getVillages();
	public String toString();
}
