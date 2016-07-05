package model.market;

import java.io.Serializable;
import java.util.ArrayList;

import model.basics.Gamer;

/**
 * Classe wrapper che rappresenta il giocatore come agente del mercato (venditore o compratore)
 * @author Luca Lagni, Maria Antonietta Palermo
 *
 */
public class Agent implements Serializable{
	private static final long serialVersionUID = 1L;
	private Gamer gamer;
	private ArrayList<HelpersItem> helpersStock;
	private ArrayList<PoliticalCardItem> politicalCardStock;
	private ArrayList<PermitCardItem> permitCardStock;
	
	public Agent(Gamer gamer){
		this.setGamer(gamer);
		this.helpersStock = new ArrayList<HelpersItem>();
		this.politicalCardStock = new ArrayList<PoliticalCardItem>();
		this.permitCardStock = new ArrayList<PermitCardItem>();
	}
	
	private void setGamer(Gamer gamer){ this.gamer = gamer; }
	
	public void addHelpersItem(HelpersItem helpersItem){ this.helpersStock.add(helpersItem); }
	public void addPoliticalCardItem(PoliticalCardItem politicalCardItem){ this.politicalCardStock.add(politicalCardItem); }
	public void addPermitCardItem(PermitCardItem permitCardItem){ this.permitCardStock.add(permitCardItem); }

	public HelpersItem subHelpersItem(int position){
		//gestire il fatto che la posizione ecceda quella dello stock
		HelpersItem hi = this.helpersStock.get(position);
		this.helpersStock.remove(position);
		
		return hi;
	}
	
	public PoliticalCardItem subPoliticalCard(int position){
		//gestire il fatto che la posizione non sia valida
		PoliticalCardItem pci = this.politicalCardStock.get(position);
		this.politicalCardStock.remove(position);
		
		return pci;
	}
	
	public PermitCardItem subPermitCardItem(int position){
		//gestire il fatto che la posizione non sia valida
		PermitCardItem pci = this.permitCardStock.get(position);
		this.permitCardStock.remove(position);
		
		return pci;
	}
	
	public Gamer getGamer(){ return this.gamer; }
	public String getUsername(){ return this.gamer.getUsername(); }
	public ArrayList<HelpersItem> getHelpersStock(){ return this.helpersStock; }
	public ArrayList<PoliticalCardItem> getPoliticalCardStock(){ return this.politicalCardStock; }
	public ArrayList<PermitCardItem> getPermitCardStock(){ return this.permitCardStock; }
	
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
