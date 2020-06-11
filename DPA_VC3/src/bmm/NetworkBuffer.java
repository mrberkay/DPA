package bmm;

import java.util.ArrayList;
import java.util.List;

import graph.Port;
import graph.Vertex;

public class NetworkBuffer {
	
	List<Message> bufferOfMessages = new ArrayList<Message>();

	public NetworkBuffer() {
		super();
	}

	public List<Message> getBufferOfMessages() {
		return bufferOfMessages;
	}

	public void setBufferOfMessages(List<Message> bufferOfMessages) {
		this.bufferOfMessages = bufferOfMessages;
	}
	
	public void addMessage(Message message) 
	{
		bufferOfMessages.add(message);
	}
	
	public List<String> getAllMessagesByVertex(Vertex recievedVertex, List<Message> messages)
	{
		List <String> listOfMessages = new ArrayList<String>();
		for(Message message : messages) 
		{
			if(message.getTargetVertex() == recievedVertex) 
			{
				String s = Integer.toString(message.getTargetPortFromMessage().getPortNumber());
				String s1 = message.getMessageContent();
				String s2 = s + s1;
				listOfMessages.add(s2);
			}
		}
		return listOfMessages;
	}
	
	
	public List<String> getAllMessagesByPort(Port recievedPort) 
	{
		List <String> listOfMessages = new ArrayList<String>();
		for(Message message : bufferOfMessages) 
		{
			if(message.getTargetPortFromMessage().equals(recievedPort)) 
			{
				listOfMessages.add(message.getMessageContent());
			}
		}
		return listOfMessages;
	}

}
