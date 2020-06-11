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
	NetworkBuffer networkBuffer = new NetworkBuffer();
	colour white = colour.White;
	colour black = colour.Black;
	List <String> localMessages = new ArrayList<String>();
	List <Port> solution = new ArrayList<Port>();
	
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
	/*
	public void print() 
	{
		int round_number = 1;
		for(Vertex i : dataContainer.getListOfVertices()) 
		{
			System.out.println("Current Vertex " + i.getVertexColour() + " " + i.getVertexID() + " Sends from Port: " + round_number);
			Port targetPort = dataContainer.getTargetPort(dataContainer.getListOfEdges(), i, round_number);
			System.out.println("Target Port: " + targetPort.vertexInstance.getVertexColour() + targetPort.vertexInstance.getVertexID() + "." + targetPort.getPortNumber());
		}
	}
	*/
	
	List<Vertex> bipatiteVertex = dataContainer.getListOfVertices();
	
	public void runBMM() 
	{	
		int k = 1;
		int round = 1;
		while(isConverged() == 0) 
		{
			for(Vertex vertex : bipatiteVertex)
			{ 
				// Odd Round
				if(round % 2 == 1) 
				{
					if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR && k <= vertex.getDegree()) 
					{
						// get target port
						Port targetPort = dataContainer.getTargetPort(dataContainer.getListOfEdges(), vertex, k);
						// send message to port v(round)
						networkBuffer.addMessage(vertex.sendProposal(targetPort, targetPort.vertexInstance));
					}
					if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR && k > vertex.getDegree()) 
					{
						vertex.setVertexStatus(status.US);
					}
					if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.MR) 
					{
						// Send matched to all ports
						for(int i = 1; i <= vertex.getDegree(); i++) 
						{
							Port targetPort = dataContainer.getTargetPort(dataContainer.getListOfEdges(), vertex, i);
							networkBuffer.addMessage(vertex.sendMatched(targetPort, targetPort.vertexInstance));
						} 
						vertex.setVertexStatus(status.MS);
					}
					if(vertex.getVertexColour() == colour.Black && vertex.getVertexStatus() == status.UR) 
					{		
						localMessages = networkBuffer.getAllMessagesByVertex(vertex, networkBuffer.getBufferOfMessages());
						// receive matched
						for(String currentMessage : localMessages) 
						{
							int recievedPortNumber = Integer.parseInt(currentMessage.substring(0, 1));
							String m = currentMessage.substring(1);
							if(m.equalsIgnoreCase("matched")) 
							{
								vertex.removeXV(recievedPortNumber);
							}
							if(m.equalsIgnoreCase("proposal")) 
							{
								vertex.fillMV(recievedPortNumber);
							}
						}
					}
					round++;
					
				}
				// Even Round 
				else if(round % 2 == 0) 
				{
					if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR) 
					{
						localMessages = networkBuffer.getAllMessagesByVertex(vertex, networkBuffer.getBufferOfMessages());
						for(String currentMessage : localMessages) 
						{	
							String m = currentMessage.substring(1);
							if(m.equalsIgnoreCase("accept")) 
							{
								//solution.add(currentMessage.ge) ToDo
								vertex.setVertexStatus(status.MR);
							}
						}
					}
					if(vertex.getVertexColour() == colour.Black && vertex.getVertexStatus() == status.UR) 
					{
						if(vertex.getMV().size() != 0) 
						{
							//int j = vertex.getMV().
						}
						
					}		
				}	
				round++;
			}	
			k++;		
		}
		
	}
	
	public int isConverged() 
	{
		for(Vertex vertex : bipatiteVertex) 
		{
			if(status.MR == vertex.getVertexStatus() || status.UR == vertex.getVertexStatus()) 
			{
				return 0;
			}
			
		}
		return 1;
	}
	
	
}
