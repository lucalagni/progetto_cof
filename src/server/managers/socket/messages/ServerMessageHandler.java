package server.managers.socket.messages;

import communication.socket.messages.MessageContentType;
import communication.socket.messages.SocketMessage;

public class ServerMessageHandler {
	
	public SocketMessage handle(SocketMessage msg)
	{
		SocketMessage response = null;
		if( msg.getContentType()==MessageContentType.MATCH_REQUEST )
		{
			String[] param = msg.getParameters();
			
			// processo ...
			
			response = new SocketMessage();
			// ...
		}
//		else if()
//		{
//			
//		}
		
		return response;
	}
	
	
	
}
