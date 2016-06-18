package contoller.communications.client.socket.dto.messagges;

import java.awt.Color;
import java.io.Serializable;

public class CouncilDTO implements DTO, Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean king;
	private int region;
	private Color noble;
	
	public CouncilDTO(int region,boolean king,Color noble){
		this.king = king;
		this.region = region;
		this.noble = noble;
	}
	
	public int getRegion(){ return this.region; }
	public Color getNoble(){ return this.noble; }
	public boolean getKing(){ return this.king; }
}
