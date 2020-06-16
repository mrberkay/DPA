package graph;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Generator {
	
	public int [][] adjacencyMatrix;
	public int dimension;
	public int[][] numberOfPortsMatrix;
	List<Vertex> listOfVertices = new ArrayList<Vertex>(); 
	List<Port> listOfPorts = new ArrayList<Port>();
	List<Edge> listOfEdges = new ArrayList<Edge>();
	
	public int returnDimension() throws Exception 
	{
		File inFile = new File("src\\adjacency.txt");
		Scanner sc2 = new Scanner(inFile);
	    String[] line1 = sc2.nextLine().trim().split("     ");
	    dimension = line1.length;
	    return dimension;
	}
	
	public int[][] createAdjacencyMatrixFromFile() throws Exception {
	    
		adjacencyMatrix = new int[dimension][dimension];
	    File inFile = new File("src\\adjacency.txt");
	    Scanner sc = new Scanner(inFile);
	    while(sc.hasNextLine()) 
	    {
	         for (int i=0; i<adjacencyMatrix.length; i++)
	         {
	            String[] line = sc.nextLine().trim().split("     ");
	            for (int j=0; j<line.length; j++) 
	            {
	               adjacencyMatrix[i][j] = Integer.parseInt(line[j]);
	            }
	         }
	     }
	    return adjacencyMatrix;
	}
	
	public void printAdjacencyMatrix() 
	{
		for(int i = 0; i < dimension; i++) 
		{
			for(int j = 0; j < dimension; j++) 
			{
				System.out.print(adjacencyMatrix[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	public void getAndPrintNumberOfPorts() 
	{
		numberOfPortsMatrix = new int[dimension][1];
		for(int i = 0; i < dimension; i++) 
		{
			//int count = 1;
			for(int j = 0; j < dimension; j++) 
			{
				if(adjacencyMatrix[i][j] == 1) 
				{
					numberOfPortsMatrix[i][0]++;
				}
				//count++;
			}
		}
		// number of ports for each vertex
		for(int i = 0; i < dimension; i++) 
		{
				System.out.println("Vertex " + (i+1) + " has " +  numberOfPortsMatrix[i][0] + " Ports");
		}
	}
	
	public void createVerticesAndPorts() 
	{
		for(int i = 0; i < dimension; i++) 
		{
			Vertex newVertex = new Vertex (i+1,numberOfPortsMatrix[i][0]);
			listOfVertices.add(newVertex);
			for(int k = 1; k <= numberOfPortsMatrix[i][0]; k++) 
			{
				Port newPort = new Port(newVertex, k);
				listOfPorts.add(newPort);
			}
		}
		
		for(Vertex i : listOfVertices) 
		{
			System.out.println("Vertex " + i.getVertexID() + " has " + i.getDegree() + " Ports");
		}
		for(Port p : listOfPorts) 
		{
			System.out.println("Vertex " + p.vertexInstance.getVertexID() + " Port " + p.getPortNumber());
		}
		
	}
	
	public void createEdges() 
	{
		//adjacencyMatrix
		for(int i = 0; i < dimension; i++) 
		{
			for(int k = 0; k < dimension; k++) 
			{
				if(adjacencyMatrix[i][k] == 1) 
				{
					// create edge
					Vertex vertex1 = listOfVertices.get(i);
					Vertex vertex2 = listOfVertices.get(k);
					Port port1 = getFirstEmptyPort(vertex1);
					Port port2 = getFirstEmptyPort(vertex2);
					Edge edge1 = new Edge(port1 , port2);
					listOfEdges.add(edge1);
					adjacencyMatrix[k][i] = 0;
				}
			}
		}
		
		System.out.println("List of Edges:");
		for(Edge i: listOfEdges) 
		{
			System.out.println( 
			"  " + i.getLeftEnd().vertexInstance.getVertexID() +
				"." +	i.getLeftEnd().getPortNumber() +
			" <---Edge---> " + i.getRightEnd().vertexInstance.getVertexID() +
				"." +	i.getRightEnd().getPortNumber());
		}
	}
	
	public Port getFirstEmptyPort(Vertex vertex) 
	{
		for(Port p : listOfPorts) 
		{
			if(p.getVerticeInstance() == vertex && p.isConnected() == false) 
			{
				p.markAsConnected();
				return p;
			}
		}
		return null;
	}
	
	public boolean checkEdge(Vertex vertex1, Vertex vertex2) 
	{
		return false;
	}
	
	

	
//End of Class	
}
