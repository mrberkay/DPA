package database;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Port;
import graph.Vertice;
import graph.Vertice.colour;

public class Data {
	
	
	List<Vertice> listOfVertices = new ArrayList<Vertice>(); 
	List<Port> listOfPorts = new ArrayList<Port>();
	List<Edge> listOfEdges = new ArrayList<Edge>();
	
	
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	public Data(List<Vertice> listOfVertices, List<Port> listOfPorts, List<Edge> listOfEdges) {
		super();
		this.listOfVertices = listOfVertices;
		this.listOfPorts = listOfPorts;
		this.listOfEdges = listOfEdges;
	} 
	*/
	public void addVertice(Vertice vertice) 
	{
		listOfVertices.add(vertice);
	}
	
	public List<Vertice> getListOfVertices() 
	{
		return listOfVertices;
	}
	
	public void addPort(Port port) 
	{
		listOfPorts.add(port);
	}
	
	public List<Port> getListOfPorts() 
	{
		return listOfPorts;
	}
	
	public void addEdge(Edge edge) 
	{
		listOfEdges.add(edge);
	}
	
	public List<Edge> getListOfEdges()
	{
		return listOfEdges;
	}
	
	public Port getPortByVertice(List<Port> ports, int verticeID, int portID, colour desiredColour) 
	{
		
		//int leftVerticeID = i.getLeftEnd().verticeInstance.getVerticeID();
		//int leftPortID = i.getLeftEnd().getPortNumber();
		for(Port desiredPort : ports) 
		{
			if(desiredPort.verticeInstance.getVerticeID() == verticeID && 
			   desiredPort.getPortNumber() == portID && 
			   desiredPort.getVerticeInstance().getVerticeColour() == desiredColour) 
			{
				System.out.println("Port gefunden");
				return desiredPort;
			}
		}
		System.out.println("Port NICHT gefunden");
		return null;	
	}
	
	

}
