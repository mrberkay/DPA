package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	
	int vertexID;
	int ports;
	int degree;
	colour vertexColour;
	status vertexStatus;
	List<String> messageContainer; 

	
	/* 
	* UR = unmatched and running 
	* MR = matched and running
	* US = unmatched and stopped 
	* MS = matched and stopped
	*/	
	 public enum status{ UR , MR , US , MS }
	
	 public enum colour{ Black, White }
	 
	 
	
	 
	public Vertex(int vertexID, int degree, colour vertexColour) {
		super();
		this.vertexID = vertexID;
		this.degree = degree;
		this.vertexColour = vertexColour;
		// if a vertex is created, its state must be unmatched and running
		this.vertexStatus = status.UR;
		// Vertex can receive multiple messages in one round
		this.messageContainer = new ArrayList<String>();
	}
	
	public Vertex(int nodeID, int degree) {
		super();
		this.vertexID = nodeID;
		this.degree = degree;
	}
	
	public int getVertexID() {
		return vertexID;
	}
	
	public void setVertexID(int vertexID) {
		this.vertexID = vertexID;
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
	
	public colour getVertexColour() {
		return vertexColour;
	}
	
	public void setVertexColour(colour vertexColour) {
		this.vertexColour = vertexColour;
	}
	
	public status getVertexStatus() {
		return vertexStatus;
	}
	
	public void setVertexStatus(status vertexStatus) {
		this.vertexStatus = vertexStatus;
	}; 
	
	
	////// Messaging //////
	public String sendProposal() 
	{
		String proposal = "proposal";
		return proposal;
	}
	
	public void recieveProposal(String proposal) throws Exception 
	{
		if(proposal.equalsIgnoreCase("proposal")) {messageContainer.add(proposal);}
		else throw new Exception("Wrong message recieved: proposal");
	}
	
	public String sendMatched() 
	{
		String matched = "matched";
		return matched;
	}
	
	public void recieveMatched(String matched) throws Exception 
	{
		if(matched.equalsIgnoreCase("matched")) {messageContainer.add(matched);}
		else throw new Exception("Wrong message recieved: matched");
	}
	
	public String sendAccept() 
	{
		String accept = "accept";
		return accept;
	}
	
	public void recieveAccept(String accept) throws Exception 
	{
		if(accept.equalsIgnoreCase("accept")) {messageContainer.add(accept);}
		else throw new Exception("Wrong message recieved: accept");
	}
	 

 
 

}