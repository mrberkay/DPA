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
	List<Vertex> listOfVertices = new ArrayList<Vertex>(); 
	List<Port> listOfPorts = new ArrayList<Port>();
	List<Edge> listOfEdges = new ArrayList<Edge>();
	List<Vertex> listToSort = new ArrayList<Vertex>();
	List<Vertex> bipatiteVertex = new ArrayList<Vertex>();
	int sentMessages = 0;
	int recievedMessages = 0;
	int timeStep = 1;
	
	public List<Edge> createVirtualNetwork(List<Vertex> graph, List<Edge> graphEdges) {
		
		for(Vertex i: graph) 
		{
			Vertex whiteVertice = new Vertex (i.getVertexID(),i.getDegree(), white);
			Vertex blackVertice = new Vertex (i.getVertexID(),i.getDegree(), black);
			
			listOfVertices.add(whiteVertice);	
			listOfVertices.add(blackVertice);
				for(int k = 0; k < i.getDegree() ; k++) 
				{
					Port wp = new Port(whiteVertice, k+1);
					Port bp = new Port(blackVertice, k+1);
					listOfPorts.add(wp);
					listOfPorts.add(bp);
				}
		}		
		
		System.out.println("total number of ports in Virtual Network (black and white): " + listOfPorts.size());
		
		for(Edge i: graphEdges) 
		{
			int leftVerticeID = i.getLeftEnd().vertexInstance.getVertexID();
			int leftPortID = i.getLeftEnd().getPortNumber();
			
			int rightVerticeID = i.getRightEnd().vertexInstance.getVertexID();
			int rightPortID = i.getRightEnd().getPortNumber();
			
			Port whitePortLeft = dataContainer.getPortByVertice(listOfPorts, leftVerticeID, leftPortID, colour.White);
			Port blackPortLeft = dataContainer.getPortByVertice(listOfPorts, leftVerticeID, leftPortID, colour.Black);
			Port whitePortRight = dataContainer.getPortByVertice(listOfPorts, rightVerticeID, rightPortID, colour.White);
			Port blackPortRight = dataContainer.getPortByVertice(listOfPorts, rightVerticeID, rightPortID, colour.Black);
			
			Edge edge1 = new Edge(whitePortLeft , blackPortRight);
			Edge edge2 = new Edge(blackPortLeft , whitePortRight);
			listOfEdges.add(edge1);
			listOfEdges.add(edge2);
		}
		
		
		return listOfEdges;
	}

	

	
	public List<Vertex> sortWhiteFirst() 
	{
		listToSort.clear();
		listToSort.addAll(bipatiteVertex);
		bipatiteVertex.clear();
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
		return bipatiteVertex;
	}
	
	public List<Vertex> sortBlackFirst() 
	{
		
		listToSort.clear();
		listToSort.addAll(bipatiteVertex);
		bipatiteVertex.clear();
		for(Vertex i : listToSort) 
		{
			if(i.getVertexColour() == colour.Black) 
			{
				bipatiteVertex.add(i);
			}
		}
		for(Vertex i : listToSort) 
		{
			if(i.getVertexColour() == colour.White) 
			{
				bipatiteVertex.add(i);
			}
		}
		return bipatiteVertex;
	}
	
	public void fill() 
	{
		for(Vertex i : listOfVertices) 
		{
			bipatiteVertex.add(i);
		}
		for(Vertex i : listOfVertices) 
		{
			listToSort.add(i);
		}
	}

	public void runBMM() 
	{	
		fill();
		int k = 1;
		//int timeStep = 1;
		while(isConverged() == 0) 
		{
			k = timeStep/2+1;
			if(timeStep % 2 == 1) {bipatiteVertex = sortWhiteFirst();}
			if(timeStep % 2 == 0) {bipatiteVertex = sortBlackFirst();}
			for(Vertex vertex : bipatiteVertex)
			{ 
				// Odd Round
				if(timeStep % 2 == 1) 
				{
					if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR && k <= vertex.getDegree()) 
					{
						// get target port
						Port targetPort = dataContainer.getTargetPort(listOfEdges, vertex, k);
						// send message to port v(round)
						networkBuffer.addMessage(vertex.sendProposal(targetPort, targetPort.vertexInstance));
						sentMessages++;
						//System.out.println("Odd round if 1");
					}
					else if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR && k > vertex.getDegree()) 
					{
						vertex.setVertexStatus(status.US);
						//System.out.println("Odd round if 2");
					}
					else if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.MR) 
					{
						// Send matched to all ports
						for(int i = 1; i <= vertex.getDegree(); i++) 
						{
							Port targetPort = dataContainer.getTargetPort(listOfEdges, vertex, i);
							networkBuffer.addMessage(vertex.sendMatched(targetPort, targetPort.vertexInstance));
							sentMessages++;
						} 
						vertex.setVertexStatus(status.MS);
						//System.out.println("Odd round if 3");
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
								recievedMessages++;
								//System.out.println("Odd round if 4");
							}
							else if(m.equalsIgnoreCase("proposal")) 
							{
								vertex.fillMV(recievedPortNumber);
								recievedMessages++;
								//System.out.println("Odd round if 5");
							}
						}
					}
					
					
				}
				// Even Round 
				if(timeStep % 2 == 0) 
				{
					if(vertex.getVertexColour() == colour.Black && vertex.getVertexStatus() == status.UR && vertex.getMV().size() != 0) 
					{
							int minIndex = vertex.getMV().indexOf(Collections.min(vertex.getMV()));
							int min_i = vertex.getMV().get(minIndex);
							Port targetPort = dataContainer.getTargetPort(listOfEdges, vertex, min_i);
							networkBuffer.addMessage(vertex.sendAccept(targetPort, targetPort.vertexInstance));
							vertex.setVertexStatus(status.MS);
							sentMessages++;
							//System.out.println("Even round if 6");
					}
					else if(vertex.getVertexColour() == colour.Black && vertex.getVertexStatus() == status.UR && vertex.getXV().size() == 0) 
					{
							vertex.setVertexStatus(status.US);
							//System.out.println("Even round if 7");
					}			
					
					if(vertex.getVertexColour() == colour.White && vertex.getVertexStatus() == status.UR) 
					{
						localMessages = networkBuffer.getAllMessagesByVertex(vertex, networkBuffer.getBufferOfMessages());
						for(String currentMessage : localMessages) 
						{	
							String m = currentMessage.substring(1);
							if(m.equalsIgnoreCase("accept")) 
							{
								vertex.setVertexStatus(status.MR);
								recievedMessages++;
								//System.out.println("Even round if 8");
							}
						}
					}	
				}	
				
			}
			timeStep++;
			//System.out.println(timeStep);
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
	public int getSentMessages() {return sentMessages;}
	public int getRecievedMessages() {return recievedMessages;}
	public int getTimeStep() {return timeStep;}
	
	
}
