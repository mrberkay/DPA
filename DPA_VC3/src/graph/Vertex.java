package graph;

import java.util.ArrayList;
import java.util.List;

import bmm.Message;

public class Vertex {
	
	int vertexID;
	int ports;
	int degree;
	colour vertexColour;
	status vertexStatus;
	List<String> messageContainer;
	List<Integer> mV;
	List<Integer> xV;

	
	/* 
	* UR = unmatched and running 
	* MR = matched and running
	* US = unmatched and stopped 
	* MS = matched and stopped
	*/	
	 public enum status{ UR , MR , US , MS }
	
	 public enum colour{ Black, White }
	 
	 public void fillxV(int degree) 
	 {

	 }
	
	 
	public Vertex(int vertexID, int degree, colour vertexColour) {
		super();
		this.vertexID = vertexID;
		this.degree = degree;
		this.vertexColour = vertexColour;
		// if a vertex is created, its state must be unmatched and running
		this.vertexStatus = status.UR;
		// Vertex can receive multiple messages in one round
		this.messageContainer = new ArrayList<String>();
		this.mV = new ArrayList<Integer>();
		this.xV = new ArrayList<Integer>();
		for(int i = 1; i <= degree; i++) 
		{
			 xV.add(i);
		}
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
	public Message sendProposal(Port targetPort, Vertex targetVertex) 
	{
		String proposal = "proposal";
		Message m = new Message(proposal, targetPort, targetVertex);
		return m;
	}
	
	public void recieveProposal(String proposal) throws Exception 
	{
		if(proposal.equalsIgnoreCase("proposal")) {messageContainer.add(proposal);}
		else throw new Exception("Wrong message recieved: proposal");
	}
	
	public Message sendMatched(Port targetPort, Vertex targetVertex) 
	{
		String matched = "matched";
		Message m = new Message(matched, targetPort, targetVertex);
		return m;
	}
	
	public void recieveMatched(String matched) throws Exception 
	{
		if(matched.equalsIgnoreCase("matched")) {messageContainer.add(matched);}
		else throw new Exception("Wrong message recieved: matched");
	}
	
	public Message sendAccept(Port targetPort, Vertex targetVertex) 
	{
		String accept = "accept";
		Message m = new Message(accept, targetPort, targetVertex);
		return m;
	}
	// TO DO FALSE
	/*
	public boolean recieveAccept(List<String> allMessages)
	{
		if(allMessages.contains("accept")) 
		{
			return true;
		}
		else 
			return false;
	}
	 */
	public void removeXV(int toRemove) 
	{
		xV.remove(new Integer(toRemove));
	}
	
	public void fillMV(int toFill) 
	{
		mV.add(toFill);
	}
	
	public List<Integer> getMV()
	{
		return mV;
	}
	
	public List<Integer> getXV()
	{
		return xV;
	}
 
 

}
