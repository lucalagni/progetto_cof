package contoller.communications.client.socket.dto.messagges;

import java.io.Serializable;
import java.util.ArrayList;

import model.basics.PoliticalCard;
import model.basics.supports.QuequedGamer;

public class PoliticalCardsDeckDTO implements DTO, Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<PoliticalCard> availableCardsList ;
	private ArrayList<QuequedGamer> queque;
	
	public PoliticalCardsDeckDTO(ArrayList<PoliticalCard> availableCardsList,ArrayList<QuequedGamer> queque){
		this.availableCardsList = availableCardsList;
		this.queque = queque;
	}
	
	public ArrayList<PoliticalCard> getAvailableCardList(){ return this.availableCardsList; }
	public ArrayList<QuequedGamer> getQueque(){ return this.queque; }
	
}
