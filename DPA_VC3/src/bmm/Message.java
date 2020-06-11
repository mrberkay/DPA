package bmm;

import graph.Port;
import graph.Vertex;

public class Message {
	
	String messageContent;
	Port targetPort;
	Vertex targetVertex;
	
	public Message(String message, Port targetPort, Vertex targetVertex) {
		super();
		this.messageContent = message;
		this.targetPort = targetPort;
		this.targetVertex = targetVertex;
	}
	
	public Port getTargetPortFromMessage() 
	{
		return targetPort;
	}
	
	public String getMessageContent() 
	{
		return messageContent;
	}
	
	public Vertex getTargetVertex() 
	{
		return targetVertex;
	}


}
