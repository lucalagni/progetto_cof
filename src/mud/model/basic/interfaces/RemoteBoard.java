package mud.model.basic.interfaces;

import java.rmi.Remote;

public interface RemoteBoard extends Remote{
	public RemoteKing getKing();
	public RemotePoliticalCardsDeck getPoliticalCardsDeck();
	public RemoteRegion[] getRegions();
	public RemoteHelpersPool getHelpersPool();
	public RemoteNobiltyPath getNobiltyPath();
	public RemoteGameMap getGameMap();
	public String toString();
}
