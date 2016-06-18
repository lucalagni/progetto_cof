package mud.model.basic.interfaces;

import java.awt.Color;
import java.rmi.Remote;

public interface RemotePoliticalCard extends Remote{
	public boolean checkColorConsistence(Color color);
	public boolean checkJollyColorConsistence(Color color);
	public boolean getJolly();
	public Color getColor();
	public String toString();
}
