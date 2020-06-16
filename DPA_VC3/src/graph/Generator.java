package graph;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generator {
	
	public int [][] adjacencyMatrix;
	public int dimension;
	public int[][] numberOfPortsMatrix;
	List<Vertex> listOfVertices = new ArrayList<Vertex>(); 
	List<Port> listOfPorts = new ArrayList<Port>();
	List<Edge> listOfEdges = new ArrayList<Edge>();
	
	public int returnDimension(String FileName) throws Exception 
	{
		File inFile = new File(FileName);
		Scanner sc2 = new Scanner(inFile);
	    String[] line1 = sc2.nextLine().trim().split("    ");
	    dimension = line1.length;
	    sc2.close();
	    return dimension;
	}
	
	public int[][] createAdjacencyMatrixFromFile(String FileName) throws Exception {
	    
		adjacencyMatrix = new int[dimension][dimension];
	    File inFile = new File(FileName);
	    Scanner sc = new Scanner(inFile);
	    while(sc.hasNextLine()) 
	    {
	         for (int i=0; i<adjacencyMatrix.length; i++)
	         {
	            String[] line = sc.nextLine().trim().split("    ");
	            for (int j=0; j<line.length; j++) 
	            {
	               adjacencyMatrix[i][j] = Integer.parseInt(line[j]);
	            }
	         }
	     }
	    sc.close();
	    return adjacencyMatrix;
	}
	
	public void getNumberOfPorts() 
	{
		numberOfPortsMatrix = new int[dimension][1];
		for(int i = 0; i < dimension; i++) 
		{
			for(int j = 0; j < dimension; j++) 
			{
				if(adjacencyMatrix[i][j] == 1) 
				{
					numberOfPortsMatrix[i][0]++;
				}
			}
		}
	}
	
	public List<Vertex> createVerticesAndPorts() 
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
		return listOfVertices;
	}
	
	public List<Edge> createEdges() 
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
		
		return listOfEdges;
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
	
	
	
	

	
//End of Class	
}
