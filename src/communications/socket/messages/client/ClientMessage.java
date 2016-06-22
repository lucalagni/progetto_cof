package communications.socket.messages.client;

import java.io.Serializable;

public class ClientMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ClientMessageContentType contentType;
	private String messageData;
	
	
}
