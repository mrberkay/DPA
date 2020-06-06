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
		/*
		//Edge edge1 = new Edge(port1_vertice1 , port2_vertice1);
		//dataContainer.addEdge(edge1);
		
		for(int i = 0; i < (numberOfPorts/2) ; i++) 
		{
			Edge newEdge = new Edge(dataContainer.getListOfPorts().get(i), dataContainer.getListOfPorts().get(i + (numberOfPorts/2)));
			dataContainer.addEdge(newEdge);
		}
		*/
	}
	/*
	public void natascha(List<Edge> graph) 
	{
		System.out.println("number of edges: " + graph.size());
		dataContainer.getListOfPorts().
		
		for(Edge i: graph) 
		{
			i.getLeftEnd().verticeInstance.getVerticeID();
			i.getLeftEnd().getPortNumber();
			
			Edge edge1 = new Edge(white , black);
			Edge edge2 = new Edge(black , white);
			dataContainer.addEdge(edge1);
			dataContainer.addEdge(edge2);
		}
	}
	*/
	/*
	public List<Edge> getListOfEdges()
	{
		return dataContainer.getListOfEdges();
	}
	*/
	
}
