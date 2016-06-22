package communications.socket.messages.client;

import java.io.Serializable;

public enum ClientMessageContentType implements Serializable {
	
	DO_MAIN_ACTION("DO_MAIN_ACTION"),
	DO_HELPERS_ACTION("DO_HELPERS_ACTION"),
	DO_SPECIAL_ACTION("DO_SPECIAL_ACTION"),
	ADD_ME_TO_A_MATCH("ADD_ME_TO_A_MATCH"),
	I_HAVE_FINISHED("I_HAVE_FINISHED");
	
	private String contentCode;
	ClientMessageContentType(String contentCode){this.setContentCode(contentCode);}
	private void setContentCode(String contentCode){ this.contentCode = contentCode; }
	public String getContentCode(){ return this.contentCode; }
}
