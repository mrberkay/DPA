package graph;

public class Edge {
	
	int edgeID;
	Port LeftEnd;
	Port RightEnd;
	
	
	public Edge(Port leftEnd, Port rightEnd) {
		super();
		LeftEnd = leftEnd;
		RightEnd = rightEnd;
	}
	
	public Port getRightEnd() 
	{
		return RightEnd;
	}
	
	public Port getLeftEnd() 
	{
		return LeftEnd;
	}
	
	public int getID() 
	{
		return edgeID;
	}
	
	

}
 //to do get method for port and node number
