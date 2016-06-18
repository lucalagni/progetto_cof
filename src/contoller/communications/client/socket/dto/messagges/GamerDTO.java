package contoller.communications.client.socket.dto.messagges;

import java.awt.Color;
import java.io.Serializable;

import model.basics.supports.GamerStatus;

public class GamerDTO implements DTO, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Color color;
	private int helpers;
	private int coins;
	private int points;
	private int shifts;
	private int shops;
	private String matchCode;
	private GamerStatus status;
	
	
	public GamerDTO(String username,Color color, int coins,int points,int shifts, int shops,int helpers,String matchCode, GamerStatus status){
		this.username = username;
		this.color = color;
		this.coins = coins;
		this.helpers = helpers;
		this.points = points;
		this.shifts = shifts;
		this.shops = shops;
		this.matchCode = matchCode;
		this.status = status;
	}
	
	public String getUsername() { return this.username; }
	public Color getColor() { return this.color; }
	public int getHelpers() { return this.helpers; }
	public int getCoins() { return this.coins; }
	public int getPoints() { return this.points; }
	public int getShops() { return this.shops; }
	public String getMatch() { return this.matchCode; }
	public GamerStatus getStatus() { return this.status; }
	public int getShifts(){ return this.shifts; }
	
}
