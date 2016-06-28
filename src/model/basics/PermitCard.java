<<<<<<< HEAD
package model.basics;

import java.io.Serializable;
import java.util.Iterator;
import java.util.HashSet;

public class PermitCard implements Serializable{
	private static final long serialVersionUID = 1L;
	private Bonus bonus;
	private HashSet<String> villages;
	
	public PermitCard(HashSet<String> villages,Bonus bonus){
		this.setVillages(villages);
		this.setBonus(bonus);
	}
	
	private void setBonus(Bonus bonus){ this.bonus = bonus; }
	private void setVillages(HashSet<String> villages){ this.villages = villages; }
	
	public boolean verifyVillage(Village village){
		Iterator<String> it = this.getVillages().iterator();
		
		while(it.hasNext()) if(it.next().equals(village.getName())) return true;
		
		return false ;
	}
	
	public Bonus getBonus(){ return this.bonus; }
	public HashSet<String> getVillages(){ return this.villages; }
	
	@Override
	public String toString(){
		String pcString = "\npermit card\n";
		Iterator<String> it = this.getVillages().iterator();
		
		pcString += "villages: \n" ;
		while(it.hasNext()) pcString += it.next().toString() + "\n";
		pcString += "bonus: " + this.getBonus().toString() ;
		
		return pcString ;
	}
}
=======
package model.basics;

import java.io.Serializable;
import java.util.Iterator;
import java.util.HashSet;

public class PermitCard implements Serializable{
	private static final long serialVersionUID = 1L;
	private Bonus bonus;
	private HashSet<String> villages;
	
	public PermitCard(HashSet<String> villages,Bonus bonus){
		this.setVillages(villages);
		this.setBonus(bonus);
	}
	
	private void setBonus(Bonus bonus){ this.bonus = bonus; }
	private void setVillages(HashSet<String> villages){ this.villages = villages; }
	
	public boolean verifyVillage(Village village){
		Iterator<String> it = this.getVillagesName().iterator();
		
		while(it.hasNext()) if(it.next().equals(village.getName())) return true;
		
		return false ;
	}
	
	public Bonus getBonus(){ return this.bonus; }
	public HashSet<String> getVillagesName(){ return this.villages; }
	public HashSet<Character> getVillages(){
		HashSet<Character> vil = new HashSet<Character>();
		
		for(String s: this.getVillagesName()) vil.add(new Character(s.charAt(0)));
		
		return vil;
	}
	
	@Override
	public String toString(){
		String pcString = "\npermit card\n";
		Iterator<String> it = this.getVillagesName().iterator();
		
		pcString += "villages: \n" ;
		while(it.hasNext()) pcString += it.next().toString() + "\n";
		pcString += "bonus: " + this.getBonus().toString() ;
		
		return pcString ;
	}
}
>>>>>>> 0ecfc63db6e8b0f97160ee713024b7a639e8d4f8
