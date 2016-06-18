package contoller.communications.client.socket.dto.messagges;

import java.io.Serializable;
import java.util.ArrayList;

import model.basics.supports.QuequedGamer;

public class HelpersPoolDTO implements DTO, Serializable{
	private static final long serialVersionUID = 1L;
	
	private int actualTotal;
	private ArrayList<QuequedGamer> queque;
	
	
	public HelpersPoolDTO(int total, ArrayList<QuequedGamer> queque){
		this.actualTotal = total;
		this.queque = queque;
	}
	
	public int getActualTotal(){ return this.actualTotal; }
	public ArrayList<QuequedGamer> getQueque(){ return this.queque; }
}
