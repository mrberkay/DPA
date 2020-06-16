package application;

import java.util.ArrayList;
import graph.Edge;
import graph.Generator;
import graph.Port;

import java.util.List;
import java.util.Scanner;

import bmm.BmmAlgorithm;
import database.Data;
import graph.Vertex;
import graph.Samples;

public class Main {
	
	public static Data dataContainer = new Data();
	public static Generator r = new Generator();
	public static List<Vertex> listOfVertices = new ArrayList<Vertex>(); 
	public static List<Port> listOfPorts = new ArrayList<Port>();
    public static List<Edge> listOfEdges = new ArrayList<Edge>();
	
	public static void main(String[] args) throws Exception {
		
		
		int cont = 0;
		while(cont != 9) 
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("----------  Menu  ----------");
			System.out.println("1.) Run algorithm with sample graph from VC3 paper");
			System.out.println("2.) Run algorithm with 10 Vertices");
			System.out.println("3.) Run algorithm with 15 Vertices");
			System.out.println("9.) Press 9 to exit");
			
			Scanner input = new Scanner(System.in);
			cont = input.nextInt();
			if(cont == 1) 
			{
				Samples sample1 = new Samples();
				sample1.createGraph6();
				listOfVertices = sample1.getListOfVertices();
				listOfEdges = sample1.getListOfEdges();
				runAlgorithm();
				cont = 9;
				System.out.println("Exiting Program...");
			}
			if(cont == 2) 
			{
				String fileName = "src\\adjacency10.txt";
				r.returnDimension(fileName);
				r.createAdjacencyMatrixFromFile(fileName);
				r.getNumberOfPorts();
				listOfVertices = r.createVerticesAndPorts();
				listOfEdges = r.createEdges();
				runAlgorithm();
				cont = 9;
				System.out.println("Exiting Program...");
			}
			if(cont == 3) 
			{
				String fileName = "src\\adjacency15.txt";
				r.returnDimension(fileName);
				r.createAdjacencyMatrixFromFile(fileName);
				r.getNumberOfPorts();
				listOfVertices = r.createVerticesAndPorts();
				listOfEdges = r.createEdges();
				//r.printAdjacencyMatrix();
				runAlgorithm();
				cont = 9;
				System.out.println("Exiting Program...");
			}
			if(cont == 4) 
			{
				String fileName = "src\\adjacency31.txt";
				r.returnDimension(fileName);
				r.createAdjacencyMatrixFromFile(fileName);
				r.getNumberOfPorts();
				listOfVertices = r.createVerticesAndPorts();
				listOfEdges = r.createEdges();
				//r.printAdjacencyMatrix();
				runAlgorithm();
				cont = 9;
				System.out.println("Exiting Program...");
			}

			
		}
		

		



		
		
	// End of Main	
	}
	public static void runAlgorithm() throws Exception 
	{
		System.out.println("Created Graph:");
		System.out.println("number of vertices in graph: " + listOfVertices.size());
		System.out.println("number of edges in graph: " + listOfEdges.size());
		BmmAlgorithm algorithm = new BmmAlgorithm();
		List<Edge> virtualNetwork = new ArrayList<Edge>();
		virtualNetwork = algorithm.createVirtualNetwork(listOfVertices, listOfEdges);
		algorithm.runBMM();
		List<Vertex> bipatiteVertex = algorithm.sendResultGraph();
		System.out.println("total number of timesteps: " + algorithm.getTimeStep());
		System.out.println("total number of sent messages: " + algorithm.getSentMessages());
		System.out.println("total number of recieved messages: " + algorithm.getRecievedMessages());
		
		System.out.println("\n Do you want to print the virtual network? (Y/N)");
		Scanner input = new Scanner(System.in);
		String a = input.nextLine();
		if(a.equalsIgnoreCase("Y")) 
		{
			printVirtualNetwork(virtualNetwork);
		}
		System.out.println("\n Do you want to print output status of BMM? (Y/N)");
		Scanner input2 = new Scanner(System.in);
		String ab = input2.nextLine();
		if(ab.equalsIgnoreCase("Y")) 
		{
			printBMMwithEndStatus(bipatiteVertex);
		}
		
	}
	
	public static void printGraph(List<Edge> graph) 
	{
		System.out.println("List of Edges:");
		for(Edge i: graph) 
		{
				System.out.println( 
				"  " + i.getLeftEnd().vertexInstance.getVertexID() +
					"." +	i.getLeftEnd().getPortNumber() +
				" <---Edge---> " + i.getRightEnd().vertexInstance.getVertexID() +
					"." +	i.getRightEnd().getPortNumber());
		}
	}
	public static void printVirtualNetwork(List<Edge> virtualNetwork) 
	{
		System.out.println("Virtual Network:");
		for(Edge i: virtualNetwork) 
		{
			System.out.println("Edge " + i.getLeftEnd().vertexInstance.getVertexColour() + " "
					+ i.getLeftEnd().vertexInstance.getVertexID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <----Edge----> " + i.getRightEnd().vertexInstance.getVertexColour() + " " + i.getRightEnd().vertexInstance.getVertexID() +
				"." +	i.getRightEnd().getPortNumber());
		}
	}
	public static void printBMMwithEndStatus(List<Vertex> bipatiteVertex) 
	{
		for(Vertex i : bipatiteVertex) 
		{
			System.out.println("Current Vertex " + i.getVertexColour() + " " + i.getVertexID() + " Status: " + i.getVertexStatus() );
		}
	}
}
