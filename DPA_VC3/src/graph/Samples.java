package graph;

import java.util.List;

import database.Data;

public class Samples {
	
	
	Data dataContainer = new Data();
	
	public void createGraph3() 
	{
		// Clear lists first
		dataContainer.clearLists();
		
		// Create vertices
		Vertice vertice1 = new Vertice (1,2);
		Vertice vertice2 = new Vertice (2,1);
		Vertice vertice3 = new Vertice (3,1);
		// Add vertices to database
		dataContainer.addVertice(vertice1);
		dataContainer.addVertice(vertice2);
		dataContainer.addVertice(vertice3);
		// Create ports for vertices
		Port port1_vertice1 = new Port(vertice1, 1);
		Port port1_vertice2 = new Port(vertice1, 2);
		Port port2_vertice1 = new Port(vertice2, 1);
		Port port3_vertice1 = new Port(vertice3, 1);
		// Add ports to database
		dataContainer.addPort(port1_vertice1);
		dataContainer.addPort(port1_vertice2);
		dataContainer.addPort(port2_vertice1);
		dataContainer.addPort(port3_vertice1);
		
		// Create Edges
		Edge edge1 = new Edge(port1_vertice1 , port2_vertice1);
		Edge edge2 = new Edge(port1_vertice2, port3_vertice1);
		// Add edges to database
		dataContainer.addEdge(edge1);
		dataContainer.addEdge(edge2);
	}
	
	public void createGraph6() 
	{
		// Clear lists first
		dataContainer.clearLists();
		// Create vertices
		Vertice vertice1 = new Vertice (1,1);
		Vertice vertice2 = new Vertice (2,1);
		Vertice vertice3 = new Vertice (3,1);
		Vertice vertice4 = new Vertice (4,5);
		Vertice vertice5 = new Vertice (5,2);
		Vertice vertice6 = new Vertice (6,2);
		// Add vertices to database
		dataContainer.addVertice(vertice1);
		dataContainer.addVertice(vertice2);
		dataContainer.addVertice(vertice3);
		dataContainer.addVertice(vertice4);
		dataContainer.addVertice(vertice5);
		dataContainer.addVertice(vertice6);
		// Create ports for vertices
		Port vertice1_port1 = new Port(vertice1, 1);
		Port vertice2_port1 = new Port(vertice2, 1);
		Port vertice3_port1 = new Port(vertice3, 1);
		Port vertice4_port1 = new Port(vertice4, 1);
		Port vertice4_port2 = new Port(vertice4, 2);
		Port vertice4_port3 = new Port(vertice4, 3);
		Port vertice4_port4 = new Port(vertice4, 4);
		Port vertice4_port5 = new Port(vertice4, 5);
		Port vertice5_port1 = new Port(vertice5, 1);
		Port vertice5_port2 = new Port(vertice5, 2);
		Port vertice6_port1 = new Port(vertice6, 1);
		Port vertice6_port2 = new Port(vertice6, 2);
		// Add ports to database
		dataContainer.addPort(vertice1_port1);
		dataContainer.addPort(vertice2_port1);
		dataContainer.addPort(vertice3_port1);
		dataContainer.addPort(vertice4_port1);
		dataContainer.addPort(vertice4_port2);
		dataContainer.addPort(vertice4_port3);
		dataContainer.addPort(vertice4_port4);
		dataContainer.addPort(vertice4_port5);
		dataContainer.addPort(vertice5_port1);
		dataContainer.addPort(vertice5_port2);
		dataContainer.addPort(vertice6_port1);
		dataContainer.addPort(vertice6_port2);
		// Create Edges
		Edge edge1 = new Edge(vertice1_port1 , vertice4_port1);
		Edge edge2 = new Edge(vertice2_port1 , vertice4_port2);
		Edge edge3 = new Edge(vertice3_port1 , vertice4_port3);
		Edge edge4 = new Edge(vertice5_port1 , vertice4_port4);
		Edge edge5 = new Edge(vertice5_port2 , vertice6_port2);
		Edge edge6 = new Edge(vertice6_port1 , vertice4_port5);
		dataContainer.addEdge(edge1);
		dataContainer.addEdge(edge2);
		dataContainer.addEdge(edge3);
		dataContainer.addEdge(edge4);
		dataContainer.addEdge(edge5);
		dataContainer.addEdge(edge6);
		
	}
	
	public List<Edge> getListOfEdges()
	{
		return dataContainer.getListOfEdges();
	}
	
	public List<Port> getListOfPorts() 
	{
		return dataContainer.getListOfPorts();
	}
	
	public List<Vertice> getListOfVertices() 
	{
		return dataContainer.getListOfVertices();
	}

}
