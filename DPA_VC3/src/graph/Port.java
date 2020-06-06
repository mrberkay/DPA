package graph;

public class Port {
 
	int portNumber;
	public Vertice verticeInstance;
	
	public Port(Vertice node, int portNumber) {
		this.verticeInstance= node;
		this.portNumber=portNumber;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public Vertice getVerticeInstance() {
		return verticeInstance;
	}

	public void setVerticeInstance(Vertice verticeInstance) {
		this.verticeInstance = verticeInstance;
	}
	
	
}
