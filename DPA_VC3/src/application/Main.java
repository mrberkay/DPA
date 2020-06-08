package application;

import java.util.ArrayList;
import graph.Edge;
import java.util.List;

import bmm.BmmAlgorithm;
import database.Data;
import graph.Vertice;
import graph.Port;
import graph.Samples;

public class Main {
	
	public static void main(String[] args) {
		
		Samples sample1 = new Samples();
		sample1.createGraph3();
		Data dataContainer = new Data();
		dataContainer.addListOfVertices(sample1.getListOfVertices());
		dataContainer.addListOfPorts(sample1.getListOfPorts());
		dataContainer.addListOfEdges(sample1.getListOfEdges());
		
		System.out.println("Created Graph:");
		System.out.println("number of nodes in graph: " + dataContainer.getListOfVertices().size());
		System.out.println("number of ports in graph: " + dataContainer.getListOfPorts().size());
		System.out.println("number of edges in graph: " + dataContainer.getListOfEdges().size());
		
		for(Edge i: dataContainer.getListOfEdges()) 
		{
			System.out.println("Edge" + 
			"  " + i.getLeftEnd().verticeInstance.getVerticeID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <---> " + i.getRightEnd().verticeInstance.getVerticeID() +
				"." +	i.getRightEnd().getPortNumber());
		}
		

		
		
		BmmAlgorithm algorithm = new BmmAlgorithm();
		List<Edge> virtualNetwork = new ArrayList<Edge>();
		virtualNetwork = algorithm.createVirtualNetwork(dataContainer.getListOfVertices(), dataContainer.getListOfEdges());
		
		
		for(Edge i: virtualNetwork) 
		{
			System.out.println("Edge " + i.getLeftEnd().verticeInstance.getVerticeColour() +
			"  " + i.getLeftEnd().verticeInstance.getVerticeID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <---> " + i.getRightEnd().verticeInstance.getVerticeColour() + i.getRightEnd().verticeInstance.getVerticeID() +
				"." +	i.getRightEnd().getPortNumber());
		}
		
	
	}
	

}
