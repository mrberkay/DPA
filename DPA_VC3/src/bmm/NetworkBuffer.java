package bmm;

import java.util.ArrayList;
import java.util.Iterator;
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
		listOfMessages.clear();
		for(Message message : messages) 
		{
			if(message.getTargetVertex().compare(recievedVertex)) 
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
		List <String> listOfMessages1 = new ArrayList<String>();
		listOfMessages1.clear();
		for(Message message : bufferOfMessages) 
		{
			if(message.getTargetPortFromMessage().compare(recievedPort)) 
			{
				listOfMessages1.add(message.getMessageContent());
			}
		}
		return listOfMessages1;
	}
	
	public void deleteMessage(String message, Vertex reciever) 
	{
		for(Iterator<Message> iterator = bufferOfMessages.iterator(); iterator.hasNext();) 
		{
			Message m = iterator.next();
			if(m.targetVertex.compare(reciever) && m.getMessageContent().equalsIgnoreCase(message)) 
			{
				iterator.remove();
			}
		}
	}

}
