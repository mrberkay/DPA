package graph;

public class Port {
 
	int portNumber;
	public Vertex vertexInstance;
	boolean connected;
	
	public Port(Vertex node, int portNumber) {
		this.vertexInstance= node;
		this.portNumber=portNumber;
		this.connected = false;
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
	
	public boolean isConnected() 
	{
		return connected;
	}
	
	public void markAsConnected() 
	{
		connected = true;
	}
	
	public boolean compare(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Port port = (Port) o;
        return portNumber == port.portNumber && vertexInstance.compare(port.getVerticeInstance());
    }
	
	
}
