package bmm;

import java.util.ArrayList;
import java.util.List;
import database.Data;
import graph.Edge;
import graph.Port;
import graph.Vertice;
import graph.Vertice.colour;

public class BmmAlgorithm {

	Data dataContainer = new Data();
	colour white = colour.White;
	colour black = colour.Black;
	
	public List<Edge> createVirtualNetwork(List<Vertice> graph, List<Edge> graphEdges) {
		
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
		

		System.out.println("total number of ports in Virtual Network (black and white): " + dataContainer.getListOfPorts().size());
		//System.out.println("number of edges (old graph): " + graphEdges.size());
		
		for(Edge i: graphEdges) 
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
		
		
		return dataContainer.getListOfEdges();
	}
	
	
}