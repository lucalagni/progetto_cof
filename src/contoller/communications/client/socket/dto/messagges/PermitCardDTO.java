package contoller.communications.client.socket.dto.messagges;

import java.io.Serializable;

public class PermitCardDTO implements DTO, Serializable{
	private static final long serialVersionUID = 1L;
	private int region ;
	private int index;
	
	public PermitCardDTO(int region,int index){
		this.region = region;
		this.index = index;
	}
	
	public int getRegion(){ return this.region; }
	public int getIndex(){ return this.index; }
}
