package application;

import java.util.ArrayList;
import graph.Edge;
import java.util.List;

import bmm.BmmAlgorithm;
import database.Data;
import graph.Vertex;
import graph.Port;
import graph.Samples;

public class Main {
	
	public static void main(String[] args) {
		
		Samples sample1 = new Samples();
		//sample1.createGraph3();
		sample1.createGraph6();
		Data dataContainer = new Data();
		dataContainer.addListOfVertices(sample1.getListOfVertices());
		dataContainer.addListOfPorts(sample1.getListOfPorts());
		dataContainer.addListOfEdges(sample1.getListOfEdges());
		
		System.out.println("Created Graph:");
		System.out.println("number of vertices in graph: " + dataContainer.getListOfVertices().size());
		System.out.println("number of ports in graph: " + dataContainer.getListOfPorts().size());
		System.out.println("number of edges in graph: " + dataContainer.getListOfEdges().size());
		
		for(Edge i: dataContainer.getListOfEdges()) 
		{
			System.out.println("Edge" + 
			"  " + i.getLeftEnd().vertexInstance.getVertexID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <---> " + i.getRightEnd().vertexInstance.getVertexID() +
				"." +	i.getRightEnd().getPortNumber());
		}
		

		
		
		BmmAlgorithm algorithm = new BmmAlgorithm();
		List<Edge> virtualNetwork = new ArrayList<Edge>();
		virtualNetwork = algorithm.createVirtualNetwork(dataContainer.getListOfVertices(), dataContainer.getListOfEdges());
		
		
		for(Edge i: virtualNetwork) 
		{
			System.out.println("Edge " + i.getLeftEnd().vertexInstance.getVertexColour() + " "
					+ i.getLeftEnd().vertexInstance.getVertexID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <---> " + i.getRightEnd().vertexInstance.getVertexColour() + " " + i.getRightEnd().vertexInstance.getVertexID() +
				"." +	i.getRightEnd().getPortNumber());
		}
		
		
		//algorithm.runBMM();
		
		String s = "berkay";
		int a = 1;
		String s1 = Integer.toString(a)+s;
		System.out.println(s1);
		String s2 = s1.substring(0,1);
		System.out.println(s2);
		int first = Integer.parseInt(s2);
		System.out.println(first);
	
	}
	

}
