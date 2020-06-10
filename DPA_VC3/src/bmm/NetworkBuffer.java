package bmm;

import java.util.ArrayList;
import java.util.List;

public class NetworkBuffer {
	
	List<Message> bufferOfMessages = new ArrayList<Message>();

	public NetworkBuffer(List<Message> bufferOfMessages) {
		super();
		this.bufferOfMessages = bufferOfMessages;
	}

	public List<Message> getBufferOfMessages() {
		return bufferOfMessages;
	}

	public void setBufferOfMessages(List<Message> bufferOfMessages) {
		this.bufferOfMessages = bufferOfMessages;
	}
	
	

}
