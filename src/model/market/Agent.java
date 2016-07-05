package model.market;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe wrapper che rappresenta il giocatore come agente del mercato (venditore o compratore)
 * @author Luca Lagni, Maria Antonietta Palermo
 *
 */
public class Agent implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private Boolean editable;
	private ArrayList<HelpersItem> helpersStock;
	private ArrayList<PoliticalCardItem> politicalCardStock;
	private ArrayList<PermitCardItem> permitCardStock;
	
	public Agent(String username){
		this.setUsername(username);
		this.helpersStock = new ArrayList<HelpersItem>();
		this.politicalCardStock = new ArrayList<PoliticalCardItem>();
		this.permitCardStock = new ArrayList<PermitCardItem>();
		this.setEditable(false);
	}
	
	private void setUsername(String username){ this.username = username; }
	
	public void addHelpersItem(HelpersItem helpersItem){ this.helpersStock.add(helpersItem); }
	public void addPoliticalCardItem(PoliticalCardItem politicalCardItem){ this.politicalCardStock.add(politicalCardItem); }
	public void addPermitCardItem(PermitCardItem permitCardItem){ this.permitCardStock.add(permitCardItem); }
	public void setEditable(boolean editable){ this.editable = new Boolean(editable); }
	
	public void resetAgent(){
		this.helpersStock = new ArrayList<HelpersItem>();
		this.permitCardStock = new ArrayList<PermitCardItem>();
		this.politicalCardStock = new ArrayList<PoliticalCardItem>();
	}

	public HelpersItem subHelpersItem(int position){
		//gestire il fatto che la posizione ecceda quella dello stock
		return this.helpersStock.remove(position);
	}
	
	public PoliticalCardItem subPoliticalCard(int position){
		//gestire il fatto che la posizione non sia valida
		return this.politicalCardStock.remove(position);
	}
	
	public PermitCardItem subPermitCardItem(int position){
		//gestire il fatto che la posizione non sia valida
		return this.permitCardStock.remove(position);
	}
	
	public String getUsername(){ return this.username; }
	public ArrayList<HelpersItem> getHelpersStock(){ return this.helpersStock; }
	public ArrayList<PoliticalCardItem> getPoliticalCardStock(){ return this.politicalCardStock; }
	public ArrayList<PermitCardItem> getPermitCardStock(){ return this.permitCardStock; }
	public boolean getEditable(){ return this.editable.booleanValue(); }
	
	@Override
	public String toString(){
		String s = new String("\nAgent\n");
		
		s += "username: " + this.getUsername() + "\n";
		s += "helpers stock : \n";
		for(HelpersItem hi : this.getHelpersStock()) s += "item: " + hi.toString() + "\n";
		s += "political card stock : \n";
		for(PoliticalCardItem pci: this.getPoliticalCardStock()) s += "item: " + pci.toString() + "\n";
		s += "permit card stock: \n";
		for(PermitCardItem pci: this.getPermitCardStock()) s += "item: " + pci.toString() + "\n"; 
		
		return s;
	}
}
