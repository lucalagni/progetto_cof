package contoller.communications.client.socket.dto.messagges;

import java.io.Serializable;

public class KingDTO implements DTO, Serializable{
	private static final long serialVersionUID = 1L;
	public String position;
	
	public KingDTO(String position){
		this.position = position;
	}
	
	public String getPosition(){ return this.position; }
}
