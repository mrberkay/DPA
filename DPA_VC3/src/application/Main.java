package application;

import java.util.ArrayList;
import graph.Edge;
import java.util.List;

import bmm.BmmAlgorithm;
import database.Data;
import graph.Vertice;
import graph.Port;

public class Main {
	
	public static void main(String[] args) {
		
		Data dataContainer = new Data();
		
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
		
		int numberOfNodes = dataContainer.getListOfVertices().size();
		
		for(Edge i: dataContainer.getListOfEdges()) 
		{
			System.out.println("Edge" + 
			"  " + i.getLeftEnd().verticeInstance.getVerticeID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <---> " + i.getRightEnd().verticeInstance.getVerticeID() +
				"." +	i.getRightEnd().getPortNumber());
		}
		

		
		
		BmmAlgorithm algorithm = new BmmAlgorithm();
		
		List<Edge> virtualNetwork = new ArrayList<Edge>();
		virtualNetwork = algorithm.createVirtualNetwork(dataContainer.getListOfVertices(), dataContainer.getListOfEdges());
		
		
		for(Edge i: virtualNetwork) 
		{
			System.out.println("Edge " + i.getLeftEnd().verticeInstance.getVerticeColour() +
			"  " + i.getLeftEnd().verticeInstance.getVerticeID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <---> " + i.getRightEnd().verticeInstance.getVerticeColour() + i.getRightEnd().verticeInstance.getVerticeID() +
				"." +	i.getRightEnd().getPortNumber());
		}
		
	
	}
	

}
