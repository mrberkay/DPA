package bmm;

import java.util.ArrayList;
import java.util.Collections;
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
			
			dataContainer.addVertice(whiteVertice);	
			dataContainer.addVertice(blackVertice);
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
	
	List<Vertex> listToSort = dataContainer.getListOfVertices();
	List<Vertex> bipatiteVertex = new ArrayList<Vertex>();
	
	public void sortVerticesByColor() 
	{
		for(Vertex i : listToSort) 
		{
			if(i.getVertexColour() == colour.White) 
			{
				bipatiteVertex.add(i);
			}
		}
		for(Vertex i : listToSort) 
		{
			if(i.getVertexColour() == colour.Black) 
			{
				bipatiteVertex.add(i);
			}
		}
	}
	/*
	public void natascha() 
	{
		for(Vertex i : bipatiteVertex) 
		{
			List<Integer> xV = i.getXV();
			for(int e : xV) 
			{
				System.out.println("xV: " + e);
			}
		}
	}
	*/
	public void runBMM() 
	{	
		int k = 1;
		int round = 1;
		while(isConverged() == 0) 
		{
			System.out.println("Round: " + round);
			System.out.println("k: " + k);
			k = round/2+1;
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
						System.out.println("Odd Round if 1");
					}
					else if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR && k > vertex.getDegree()) 
					{
						vertex.setVertexStatus(status.US);
						System.out.println("Odd Round if 2");
					}
					else if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.MR) 
					{
						// Send matched to all ports
						for(int i = 1; i <= vertex.getDegree(); i++) 
						{
							Port targetPort = dataContainer.getTargetPort(dataContainer.getListOfEdges(), vertex, i);
							networkBuffer.addMessage(vertex.sendMatched(targetPort, targetPort.vertexInstance));
						} 
						vertex.setVertexStatus(status.MS);
						System.out.println("Odd Round if 3");
					}
					else if(vertex.getVertexColour() == colour.Black && vertex.getVertexStatus() == status.UR) 
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
								System.out.println("Odd Round if 4");
							}
							if(m.equalsIgnoreCase("proposal")) 
							{
								vertex.fillMV(recievedPortNumber);
								System.out.println("Odd Round if 5");
							}
						}
					}
					
					
				}
				//round++;
				// Even Round 
				if(round % 2 == 0) 
				{
					if(vertex.getVertexColour() == colour.Black && vertex.getVertexStatus() == status.UR && !(vertex.getMV().isEmpty())) 
					{
							int minIndex = vertex.getMV().indexOf(Collections.min(vertex.getMV()));
							int min_i = vertex.getMV().get(minIndex);
							Port targetPort = dataContainer.getTargetPort(dataContainer.getListOfEdges(), vertex, min_i);
							networkBuffer.addMessage(vertex.sendAccept(targetPort, targetPort.vertexInstance));
							vertex.setVertexStatus(status.MS);
							System.out.println("Even Round if 6");
					}
					else if(vertex.getVertexColour() == colour.Black && vertex.getVertexStatus() == status.UR && vertex.getXV().size() == 0) 
					{
							vertex.setVertexStatus(status.US);
							System.out.println("Even Round if 7");
					}			
					
					else if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR) 
					{
						localMessages = networkBuffer.getAllMessagesByVertex(vertex, networkBuffer.getBufferOfMessages());
						for(String currentMessage : localMessages) 
						{	
							String m = currentMessage.substring(1);
							if(m.equalsIgnoreCase("accept")) 
							{
								vertex.setVertexStatus(status.MR);
								System.out.println("Even Round if 8");
							}
						}
					}	
				}	
				
			}
			round++;
			//k++;		
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
	
	public List<Vertex> sendResultGraph(){ return bipatiteVertex;}
	
	
}
