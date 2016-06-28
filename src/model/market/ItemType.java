package model.market;

import java.io.Serializable;

/**
 * enumerazione che elenca il tipo di oggetti che possono essere venduti nel market
 * 
 * HELEPRS_ITEM = indica un lotto di aiutanti
 * POLITICAL_CARD_ITEM = indica un lotto di carte politiche
 * PERMIT_CARD_ITEM = indica un lotto di carte permesso
 * 
 * @author Luca Lagni
 *
 */

public enum ItemType implements Serializable{
	HELPERS_ITEM("HELPERS_ITEM"),
	POLITICAL_CARD_ITEM("POLITICAL_CARD_ITEM"),
	PERMIT_CARD_ITEM("PERMIT_CARD_ITEM");
	
	private String itemType;
	ItemType(String itemType){ this.setItemType(itemType); }
	private void setItemType(String itemType){ this.itemType = itemType; }
	public String getItemType(){ return this.itemType; }
}
