package bmm;

import graph.Port;

public class Message {
	
	String messageContent;
	Port targetPort;
	
	public Message(String message, Port targetPort) {
		super();
		this.messageContent = message;
		this.targetPort = targetPort;
	}
	
	public Port getTargetPortFromMessage() 
	{
		return targetPort;
	}
	
	public String getMessageContent() 
	{
		return messageContent;
	}


}
