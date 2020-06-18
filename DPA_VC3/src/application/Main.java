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
import graph.Vertex.colour;
import graph.Vertex.status;
import vc3.Vc3Algorithm;
import graph.Samples;

public class Main {
	
	public static Data dataContainer = new Data();
	public static Generator r = new Generator();
	public static List<Vertex> listOfVertices = new ArrayList<Vertex>(); 
	public static List<Port> listOfPorts = new ArrayList<Port>();
    public static List<Edge> listOfEdges = new ArrayList<Edge>();
	
	public static void main(String[] args) throws Exception {
		
		
		int cont = 0;
		while(cont != 8) 
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("----------  Menu  ----------");
			System.out.println("1.) Run algorithm with sample graph from VC3 paper");
			System.out.println("2.) Run algorithm with 10 Vertices");
			System.out.println("3.) Run algorithm with 20 Vertices");
			System.out.println("4.) Run algorithm with 30 Vertices");
			System.out.println("5.) Run algorithm with 40 Vertices");
			System.out.println("6.) Run algorithm with 50 Vertices");
			System.out.println("7.) Run algorithm with 30 Vertices with dense edges");
			System.out.println("8.) Press 8 to exit");
			System.out.println("----------  Menu  ----------");
			System.out.println();
			System.out.println();
			System.out.println();
			
			Scanner input = new Scanner(System.in);
			cont = input.nextInt();
			if(cont == 1) 
			{
				Samples sample1 = new Samples();
				sample1.createGraph6();
				System.out.println("Created Graph:");
				listOfVertices = sample1.getListOfVertices();
				listOfEdges = sample1.getListOfEdges();
				for(Edge i: listOfEdges) 
				{
						System.out.println( 
						"  " + i.getLeftEnd().vertexInstance.getVertexID() +
							"." +	i.getLeftEnd().getPortNumber() +
						" <---Edge---> " + i.getRightEnd().vertexInstance.getVertexID() +
							"." +	i.getRightEnd().getPortNumber());
				}
				BmmAlgorithm bmmalgorithm = new BmmAlgorithm();
				List<Edge> virtualNetwork = new ArrayList<Edge>();
				virtualNetwork = bmmalgorithm.createVirtualNetwork(listOfVertices, listOfEdges);
				System.out.println("Virtual Network:");
				for(Edge i: virtualNetwork) 
				{
					System.out.println("  " +"Edge " + i.getLeftEnd().vertexInstance.getVertexColour() + " "
							+ i.getLeftEnd().vertexInstance.getVertexID() +
						"." +	i.getLeftEnd().getPortNumber() +
					" <----Edge----> " + i.getRightEnd().vertexInstance.getVertexColour() + " " + i.getRightEnd().vertexInstance.getVertexID() +
						"." +	i.getRightEnd().getPortNumber());
				}
				bmmalgorithm.runBMM();
				List<Vertex> bmmOutput = bmmalgorithm.sendResultGraph();
				System.out.println("total number of timesteps: " + bmmalgorithm.getTimeStep());
				System.out.println("total number of sent messages: " + bmmalgorithm.getSentMessages());
				System.out.println("total number of recieved messages: " + bmmalgorithm.getRecievedMessages());
				test(bmmOutput);
				Vc3Algorithm vc3 = new Vc3Algorithm();
				List<Integer> covers = vc3.findC(bmmOutput);
				System.out.println("Covers in C:");
				for(int c : covers) 
				{
					System.out.print(c + " ");
				}
				
			}
			if(cont == 2) 
			{
				Generator r2 = new Generator();
				String fileName = "src\\adjacency10.txt";
				r2.returnDimension(fileName);
				r2.createAdjacencyMatrixFromFile(fileName);
				r2.getNumberOfPorts();
				listOfVertices = r2.createVerticesAndPorts();
				listOfEdges = r2.createEdges();
				runAlgorithm();
			}
			if(cont == 3) 
			{
				Generator r3 = new Generator();
				String fileName = "src\\adjacency20.txt";
				r3.returnDimension(fileName);
				r3.createAdjacencyMatrixFromFile(fileName);
				r3.getNumberOfPorts();
				listOfVertices = r3.createVerticesAndPorts();
				listOfEdges = r3.createEdges();
				runAlgorithm();
			}
			if(cont == 4) 
			{
				Generator r4 = new Generator();
				String fileName = "src\\adjacency30.txt";
				r4.returnDimension(fileName);
				r4.createAdjacencyMatrixFromFile(fileName);
				r4.getNumberOfPorts();
				listOfVertices = r4.createVerticesAndPorts();
				listOfEdges = r4.createEdges();
				runAlgorithm();
			}
			if(cont == 5) 
			{
				Generator r5 = new Generator();
				String fileName = "src\\adjacency40.txt";
				r5.returnDimension(fileName);
				r5.createAdjacencyMatrixFromFile(fileName);
				r5.getNumberOfPorts();
				listOfVertices = r5.createVerticesAndPorts();
				listOfEdges = r5.createEdges();
				runAlgorithm();
			}
			if(cont == 6) 
			{
				Generator r6 = new Generator();
				String fileName = "src\\adjacency50.txt";
				r6.returnDimension(fileName);
				r6.createAdjacencyMatrixFromFile(fileName);
				r6.getNumberOfPorts();
				listOfVertices = r6.createVerticesAndPorts();
				listOfEdges = r6.createEdges();
				runAlgorithm();
			}
			if(cont == 7) 
			{
				//Generator r7 = new Generator();
				String fileName = "src\\adjacencyDense.txt";
				r.returnDimension(fileName);
				r.createAdjacencyMatrixFromFile(fileName);
				r.getNumberOfPorts();
				listOfVertices = r.createVerticesAndPorts();
				listOfEdges = r.createEdges();
				runAlgorithm();
			}

			
		}
	}
	public static void runAlgorithm() throws Exception 
	{
		System.out.println("Created Graph:");
		System.out.println("number of vertices in graph: " + listOfVertices.size());
		System.out.println("number of edges in graph: " + listOfEdges.size());
		BmmAlgorithm bmmalgorithm = new BmmAlgorithm();
		bmmalgorithm.createVirtualNetwork(listOfVertices, listOfEdges);
		bmmalgorithm.runBMM();
		List<Vertex> bmmOutput = bmmalgorithm.sendResultGraph();
		System.out.println("total number of timesteps: " + bmmalgorithm.getTimeStep());
		System.out.println("total number of sent messages: " + bmmalgorithm.getSentMessages());
		System.out.println("total number of recieved messages: " + bmmalgorithm.getRecievedMessages());
		test(bmmOutput);
		Vc3Algorithm vc3 = new Vc3Algorithm();
		vc3.findC(bmmOutput);
	}
	
	public static void test(List<Vertex> bipatiteVertex) throws Exception 
	{
		System.out.print("Validating result ");
		for(Vertex v : bipatiteVertex) 
		{
			if(v.getVertexStatus() == status.US && v.getVertexColour() == colour.Black && v.getXV().size() != 0) 
			{
				throw new Exception("Algorithm FAILED");
			}
			else if(v.getVertexStatus() == status.MS && v.getVertexColour() == colour.Black && v.getMV().size() == 0) 
			{
				throw new Exception("Algorithm FAILED");
			}
			else if(v.getVertexStatus() == status.MR && v.getVertexStatus() == status.UR) 
			{
				throw new Exception("Algorithm FAILED");
			}
			
			System.out.print(".");
		}
		System.out.println();
	}


}
