package bmm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import database.Data;
import graph.Edge;
import graph.Port;
import graph.Vertex;
import graph.Vertex.colour;
import graph.Vertex.status;

public class BmmAlgorithm {

	Data dataContainer = new Data();
	colour white = colour.White;
	colour black = colour.Black;
	
	public List<Edge> createVirtualNetwork(List<Vertex> graph, List<Edge> graphEdges) {
		
		for(Vertex i: graph) 
		{
			Vertex whiteVertice = new Vertex (i.getVertexID(),i.getDegree(), white);
			Vertex blackVertice = new Vertex (i.getVertexID(),i.getDegree(), black);
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
			System.out.println(i.vertexInstance.getVertexColour() + " " + i.vertexInstance.getVertexID()
			+ "." + i.getPortNumber() + "  State: " + i.vertexInstance.getVertexStatus());
		}
		

		System.out.println("total number of ports in Virtual Network (black and white): " + dataContainer.getListOfPorts().size());
		//System.out.println("number of edges (old graph): " + graphEdges.size());
		
		for(Edge i: graphEdges) 
		{
			int leftVerticeID = i.getLeftEnd().vertexInstance.getVertexID();
			int leftPortID = i.getLeftEnd().getPortNumber();
			
			int rightVerticeID = i.getRightEnd().vertexInstance.getVertexID();
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
	
	public void runBMM() 
	{	
		int round = 1;
		while(isConverged() == 1) 
		{
			for(Vertex i : dataContainer.getListOfVertices())
			{
				// Round 2k-1
				if(round % 2 == 1) 
				{
					if(i.getVertexColour() == colour.White && i.getVertexStatus() == status.UR && round <= i.getDegree()) 
					{
						// send message to port v(round)
					}
					if(i.getVertexColour() == colour.White && i.getVertexStatus() == status.UR && round > i.getDegree()) 
					{
						i.setVertexStatus(status.US);
					}
					if(i.getVertexColour() == colour.White && i.getVertexStatus() == status.MR) 
					{
						// Send matched to all ports
						i.setVertexStatus(status.MS);
					}
					if(i.getVertexColour() == colour.Black && i.getVertexStatus() == status.UR) 
					{
						/*
						// receive matched
						if() 
						{
						
						}
						// receive proposal
						if() 
						{
							
						}
						*/
					}
					
				}
				// Round 2k
				else if(round % 2 == 0) 
				{
					if(i.getVertexColour() == colour.White && i.getVertexStatus() == status.UR) 
					{
						// if received message message is accept
						i.setVertexStatus(status.MR);
					}
					// To Do missing if 
					if(i.getVertexColour() == colour.Black && i.getVertexStatus() == status.UR) 
					{
						// m(v) != 0 
						// if() --> do: i = min M(v) and send accept to port v(i) and 
						// i.getVertexStatus() == status.MS;
						// x(v) == 0
						// if() --> do: i.getVertexStatus() == status.US;
						
					}		
				}	
			}
			
			round++;
		}
		
	}
	
	public int isConverged() 
	{
		return 1;
	}
	
	
}
