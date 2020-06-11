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
	
	public L searchMessage(Port recievedPort) 
	{
		for(Message message : bufferOfMessages) 
		{
			if(message.getTargetPortFromMessage().equals(recievedPort)) 
			{
				message.getMessageContent();
			}
		}
	}

}
