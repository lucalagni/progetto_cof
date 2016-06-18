package contoller.communications.client.socket.dto.messagges;

import java.io.Serializable;

public class ShopDTO implements DTO, Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String village ;
	
	public ShopDTO(String username,String village){
		this.username = username;
		this.village = village;
	}
	
	public String getUsername(){ return this.username; }
	public String getVillage(){ return this.village; }
}
