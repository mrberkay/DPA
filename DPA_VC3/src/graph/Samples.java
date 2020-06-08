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
	
	public void createGraph5() 
	{
		// Clear lists first
		dataContainer.clearLists();
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
