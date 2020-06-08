package graph;

public class Port {
 
	int portNumber;
	public Vertex vertexInstance;
	
	public Port(Vertex node, int portNumber) {
		this.vertexInstance= node;
		this.portNumber=portNumber;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public Vertex getVerticeInstance() {
		return vertexInstance;
	}

	public void setVerticeInstance(Vertex vertexInstance) {
		this.vertexInstance = vertexInstance;
	}
	
	
}
