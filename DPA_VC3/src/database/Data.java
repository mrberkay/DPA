package database;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Port;
import graph.Vertex;
import graph.Vertex.colour;

public class Data {
	
	
	List<Vertex> listOfVertices = new ArrayList<Vertex>(); 
	List<Port> listOfPorts = new ArrayList<Port>();
	List<Edge> listOfEdges = new ArrayList<Edge>();
	List<Port> matchedPorts = new ArrayList<Port>();
	List<Vertex> sortedList = new ArrayList<Vertex>();
	
	
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void addVertice(Vertex vertex) 
	{
		listOfVertices.add(vertex);
	}
	
	public List<Vertex> getListOfVertices() 
	{
		return listOfVertices;
	}
	
	public List<Vertex> addListOfVertices(List<Vertex> oldList) 
	{
		return listOfVertices = oldList;
	}
	
	public void addPort(Port port) 
	{
		listOfPorts.add(port);
	}
	
	public void addMatchedPort(Port port) 
	{
		matchedPorts.add(port);
	}
	
	public List<Port> getListOfPorts() 
	{
		return listOfPorts;
	}
	
	public List<Port> addListOfPorts(List<Port> oldList) 
	{
		return listOfPorts = oldList;
	}
	
	public void addEdge(Edge edge) 
	{
		listOfEdges.add(edge);
	}
	
	public List<Edge> getListOfEdges()
	{
		return listOfEdges;
	}
	
	public List<Edge> addListOfEdges(List<Edge> oldList)
	{
		return listOfEdges = oldList;
	}
	
	public Port getPortByVertice(List<Port> ports, int verticeID, int portID, colour desiredColour) 
	{
		for(Port desiredPort : ports) 
		{
			if(desiredPort.vertexInstance.getVertexID() == verticeID && 
			   desiredPort.getPortNumber() == portID && 
			   desiredPort.getVerticeInstance().getVertexColour() == desiredColour) 
			{
				return desiredPort;
			}
		}
		System.out.println("Port NOT found");
		return null;	
	}
	
	public Port getTargetPort(List<Edge> edges, Vertex vertex, int myPortNumber) 
	{
		for(Edge i : edges) 
		{
			if(i.getLeftEnd().vertexInstance.compare(vertex) && (i.getLeftEnd().getPortNumber() == myPortNumber)) 
			{
				return i.getRightEnd();
			}
			else if(i.getRightEnd().vertexInstance.compare(vertex) && (i.getRightEnd().getPortNumber() == myPortNumber)) 
			{
				return i.getLeftEnd();
			}
		}
		System.out.println("Target Port NOT FOUND");
		return null;
	}
	
	
	public void clearLists() 
	{
		listOfVertices.clear();
		listOfPorts.clear();
		listOfEdges.clear();
	}

}
