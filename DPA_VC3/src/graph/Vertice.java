package graph;

public class Vertice {
	
	int verticeID;
	int ports;
	int degree;
	colour verticeColour;
	status verticeStatus;

	
	/* 
	* UR = unmatched and running 
	* MR = matched and running
	* US = unmatched and stopped 
	* MS = matched and stopped
	*/	
	 public enum status{ UR , MR , US , MS }
	
	 public enum colour{ Black, White }
	 
	 
	
	 
	public Vertice(int verticeID, int degree, colour verticeColour) {
		super();
		this.verticeID = verticeID;
		this.degree = degree;
		this.verticeColour = verticeColour;
	}
	
	public Vertice(int nodeID, int degree) {
		super();
		this.verticeID = nodeID;
		this.degree = degree;
	}
	
	public int getVerticeID() {
		return verticeID;
	}
	
	public void setVerticeID(int verticeID) {
		this.verticeID = verticeID;
	}
	
	public int getPorts() {
		return ports;
	}
	
	public void setPorts(int ports) {
		this.ports = ports;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public colour getVerticeColour() {
		return verticeColour;
	}
	
	public void setVerticeColour(colour verticeColour) {
		this.verticeColour = verticeColour;
	}
	
	public status getVerticeStatus() {
		return verticeStatus;
	}
	
	public void setVerticeStatus(status verticeStatus) {
		this.verticeStatus = verticeStatus;
	}; 
	
	public void sendProposal() 
	{
		
	}
	
	public void recieveProposal() 
	{
		
	}
	
	public void sendMatched() 
	{
		
	}
	
	public void recieveMatched() 
	{
		
	}
	
	public void sendAccept() 
	{
		
	}
	
	public void recieveAccept() 
	{
		
	}
	 

 
 

}
