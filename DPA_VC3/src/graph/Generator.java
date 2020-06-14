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
	
	public void createVertices() 
	{
		for(int i = 0; i < dimension; i++) 
		{
			Vertex vertice1 = new Vertex (i+1,numberOfPortsMatrix[i][0]);
			listOfVertices.add(vertice1);
		}
		
		for(Vertex i : listOfVertices) 
		{
			System.out.println("Vertex " + i.getVertexID() + " has " + i.getDegree() + " Ports");
		}
		
	}

	
//End of Class	
}
