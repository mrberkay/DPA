package vc3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import graph.Vertex;
import graph.Vertex.status;

public class Vc3Algorithm {
	
	List<Integer> result = new ArrayList<Integer>();
	
	public List<Integer> findC(List<Vertex> bmmOutput) 
	{
		for(Vertex v : bmmOutput) 
		{
			for(Vertex v2 : bmmOutput) 
			{
				if(v.getVertexStatus() == status.MS && v2.getVertexStatus() == status.MS 
						&& v.getVertexColour() != v2.getVertexColour()
						&& (v.getVertexID() == v2.getVertexID())) 
				{
					if(!result.contains(v.getVertexID()))
						result.add(v.getVertexID());
				}
			}
		}
		return result;
	}
	

}
