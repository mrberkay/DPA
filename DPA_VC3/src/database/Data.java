package database;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.Port;
import graph.Vertice;

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
	
	
	
	

}
