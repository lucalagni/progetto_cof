package model.basics.supports;

import java.io.Serializable;

public class QuequedGamer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private int position;
	private int value ;
	
	public QuequedGamer(int position,String username,int value){
		this.setPosition(position);
		this.setUsername(username);
		this.setValue(value);
	}
	
	private void setUsername(String username){ this.username = username; }
	private void setPosition(int position){ this.position = position; }
	private void setValue(int value){ this.value = value; }
	
	public String getUsername(){ return this.username; }
	public int getPosition(){ return this.position; }
	public int getValue(){ return this.value; }
	
	@Override
	public String toString(){
		String s = new String("\nquequed user\n");
		
		s += "position: " + this.getPosition() + "\n";
		s += "username: " + this.getUsername() + "\n";
		s += "value: " + this.getValue() + "\n";
		
		return s;
	}
}
