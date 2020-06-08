package graph;

public class Port {
 
	int portNumber;
	public Vertex verticeInstance;
	
	public Port(Vertex node, int portNumber) {
		this.verticeInstance= node;
		this.portNumber=portNumber;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public Vertex getVerticeInstance() {
		return verticeInstance;
	}

	public void setVerticeInstance(Vertex verticeInstance) {
		this.verticeInstance = verticeInstance;
	}
	
	
}
