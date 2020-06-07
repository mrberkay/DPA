package bmm;

import java.util.ArrayList;
import java.util.List;
import database.Data;
import graph.Edge;
import graph.Port;
import graph.Vertice;
import graph.Vertice.colour;

public class BmmAlgorithm {

	//Edge edge1 = new Edge(0, port1_vertice1 , port2_vertice1);
	//Edge edge1 = new Edge(0, port1_vertice1 , port2_vertice1);
	// Vertice vertice1 = new Vertice (1,2);
	// Port port1_vertice1 = new Port(vertice1, 1);
	Data dataContainer = new Data();

	colour white = colour.White;
	colour black = colour.Black;
	
	public void create2ColouredNetwork(List<Vertice> graph) 
	{
		for(Vertice i: graph) 
		{
			Vertice whiteVertice = new Vertice (i.getVerticeID(),i.getDegree(), white);
			Vertice blackVertice = new Vertice (i.getVerticeID(),i.getDegree(), black);
			dataContainer.addVertice(blackVertice);
			dataContainer.addVertice(whiteVertice);
			
			for(int k = 0; k < i.getDegree() ; k++) 
			{
				Port wp = new Port(whiteVertice, k+1);
				Port bp = new Port(blackVertice, k+1);
				dataContainer.addPort(wp);
				dataContainer.addPort(bp);
			}
		}		
		//System.out.println(dataContainer.getListOfVertices().size());
		
		for(Port i : dataContainer.getListOfPorts()) 
		{
			System.out.println(i.verticeInstance.getVerticeColour() + " " + i.verticeInstance.getVerticeID()
			+ "." + i.getPortNumber());
		}
		
		int numberOfPorts = dataContainer.getListOfPorts().size();
		System.out.println("number of ports: " + numberOfPorts);
	
	}
	
	public void natascha(List<Edge> graph) 
	{
		System.out.println("number of edges: " + graph.size());
		//dataContainer.getListOfPorts().
		
		for(Edge i: graph) 
		{
			int leftVerticeID = i.getLeftEnd().verticeInstance.getVerticeID();
			int leftPortID = i.getLeftEnd().getPortNumber();
			
			int rightVerticeID = i.getRightEnd().verticeInstance.getVerticeID();
			int rightPortID = i.getRightEnd().getPortNumber();
			
			Port whitePortLeft = dataContainer.getPortByVertice(dataContainer.getListOfPorts(), leftVerticeID, leftPortID, colour.White);
			Port blackPortLeft = dataContainer.getPortByVertice(dataContainer.getListOfPorts(), leftVerticeID, leftPortID, colour.Black);
			Port whitePortRight = dataContainer.getPortByVertice(dataContainer.getListOfPorts(), rightVerticeID, rightPortID, colour.White);
			Port blackPortRight = dataContainer.getPortByVertice(dataContainer.getListOfPorts(), rightVerticeID, rightPortID, colour.Black);
			
			Edge edge1 = new Edge(whitePortLeft , blackPortRight);
			Edge edge2 = new Edge(blackPortLeft , whitePortRight);
			dataContainer.addEdge(edge1);
			dataContainer.addEdge(edge2);
		}
	}
	
	
	public List<Edge> getListOfEdges()
	{
		return dataContainer.getListOfEdges();
	}
	
	
}
