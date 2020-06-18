package graph;

import java.util.List;

import database.Data;

public class Samples {
	
	
	Data dataContainer = new Data();
	
	public void createGraph4() 
	{
		// Clear lists first
		dataContainer.clearLists();
		
		// Create vertices
		Vertex vertice1 = new Vertex (1,2);
		Vertex vertice2 = new Vertex (2,2);
		Vertex vertice3 = new Vertex (3,4);
		Vertex vertice4 = new Vertex (4,1);
		Vertex vertice5 = new Vertex (5,1);
		// Add vertices to database
		dataContainer.addVertice(vertice1);
		dataContainer.addVertice(vertice2);
		dataContainer.addVertice(vertice3);
		dataContainer.addVertice(vertice4);
		dataContainer.addVertice(vertice5);
		// Create ports for vertices
		Port v1p1 = new Port(vertice1, 1);
		Port v1p2 = new Port(vertice1, 2);
		Port v2p1 = new Port(vertice2, 1);
		Port v2p2 = new Port(vertice2, 2);
		Port v3p1 = new Port(vertice3, 1);
		Port v3p2 = new Port(vertice3, 2);
		Port v3p3 = new Port(vertice3, 3);
		Port v3p4 = new Port(vertice3, 4);
		Port v4p1 = new Port(vertice4, 1);
		Port v5p1 = new Port(vertice5, 1);

		// Add ports to database
		dataContainer.addPort(v1p1);
		dataContainer.addPort(v1p2);
		dataContainer.addPort(v2p1);
		dataContainer.addPort(v2p2);
		dataContainer.addPort(v3p1);
		dataContainer.addPort(v3p2);
		dataContainer.addPort(v3p3);
		dataContainer.addPort(v4p1);
		dataContainer.addPort(v3p4);
		dataContainer.addPort(v5p1);

		
		// Create Edges
		Edge edge1 = new Edge(v1p1, v2p1);
		Edge edge2 = new Edge(v1p2, v3p1);
		Edge edge3 = new Edge(v3p2, v2p2);
		Edge edge4 = new Edge(v3p3, v4p1);
		Edge edge5 = new Edge(v3p4, v5p1);

		// Add edges to database
		dataContainer.addEdge(edge1);
		dataContainer.addEdge(edge2);
		dataContainer.addEdge(edge3);
		dataContainer.addEdge(edge4);
		dataContainer.addEdge(edge5);
	}
	
	public void createGraph6() 
	{
		// Clear lists first
		dataContainer.clearLists();
		// Create vertices
		Vertex vertice1 = new Vertex (1,1);
		Vertex vertice2 = new Vertex (2,1);
		Vertex vertice3 = new Vertex (3,1);
		Vertex vertice4 = new Vertex (4,5);
		Vertex vertice5 = new Vertex (5,2);
		Vertex vertice6 = new Vertex (6,2);
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
	
	public List<Vertex> getListOfVertices() 
	{
		return dataContainer.getListOfVertices();
	}

}
