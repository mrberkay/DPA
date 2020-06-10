package bmm;

import graph.Port;

public class Message {
	
	String message;
	Port targetPort;
	
	public Message(String message, Port targetPort) {
		super();
		this.message = message;
		this.targetPort = targetPort;
	}
	


}
