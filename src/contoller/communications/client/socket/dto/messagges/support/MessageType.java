package contoller.communications.client.socket.dto.messagges.support;

import java.io.Serializable;

public enum MessageType implements Serializable{
	GAMER_UPDATE_MESSAGE("gamer_update_message"),
	SHOP_BUILT_MESSAGE("shop_built_message"),
	HELPERS_POOL_CHANGED_MESSAGE("helpers_pool_changed_message"),
	PERMIT_CARD_TAKEN_MESSAGE("permit_card_taken_message"),
	POLITICAL_CARDS_DECK_CHANGED_MESSAGE("political_cards_deck_changed_message"),
	KING_RELOCATED_MESSAGE("king_relocated_message"),
	COLOR_BONUS_TAKEN_MESSAGE("color_bonus_taken_message"),
	REGION_BONUS_TAKEN_MESSAGE("region_bonus_taken_message"),
	KING_BONUS_TAKEN_MESSAGE("king_bonus_taken_message");
	
	private String messageCode;
	
	MessageType(String messageCode){ this.setMessageCode(messageCode); }
	
	private void setMessageCode(String messageCode){ this.messageCode = messageCode; }
	public String getMessageCode(){ return this.messageCode; }
}
