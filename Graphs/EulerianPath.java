package Graphs;

import java.util.*;


/*
 * An Eulerian Path (or Eulerian Trail) is a path of edges that visits
 * all the edges in a graph exactly once.
 * 
 * An Eulerian Circuit is an Eulerian path which starts and ends at the 
 * same vertex.
 * 
 * When finding paths/circuits, all vertices with non zero degree need
 * to belong to a single connected component.
 * 
 * Choosing the wrong starting node can lead to having unreachable edges.
 * 
 * If the graph is Eulerian Circuit than we can start dfs from any edge.
 * 
 * Singleton node has no incoming/outgoing edges, so it doesn't impact
 * whether or not we have an Eulerian path.	
 */



public class EulerianPath {
	
	// GLOBALLY DECLARED VARIABLES
	
	int n;//Numebr Of Nodes
	int m;//Number of Edges
	DirectedGraph graph;
	
	int[] in,out;
	//Tracking In and out degree of every node
	
	ArrayList<Integer> path;
	//Eulerian Path (or Circuit)
	
	EulerianPath(DirectedGraph graph, int n){
		this.graph = graph;
		this.n = n;
		m = graph.numberOfEdges();
	}
	
	ArrayList<Integer> findEulerianPath() {

		//MAIN FUNCTION
		
		in = new int[n];
		out = new int[n];
		path = new ArrayList<>();
		
		countInOutDegree();
		//Counts in out degree
		
		if (!graphHasEulerPath()) return null;
		//Return null if euler path condition is not satisfied
		
		dfs(findStartNode());
		//Performing dfs with finding start node
		
		Collections.reverse(path);
		
		if(path.get(0) == path.get(path.size()-1))
			System.out.println("Eulerian Circuit and Eulerian Path\n" + path);
		else
		System.out.println("Eulerian Path \n" +path);
		if(path.size() == m+1) return path;
		return null;
		
	}
	void dfs(int at) {
		while(out[at]>0) 
			dfs(graph.edges(at).get(--out[at]));
		//Reducing out degree
		
		//Adding node to path during backtracking
		path.add(at);
	}
	
	int findStartNode() {
		
		//Returns first node 
		int start=1;
		for(int i=0;i<n;i++)
			if(out[i] - in[i] == 1 ) 
				return i;
			//Unique Start Node
		
			else if(out[i] > 0) start=i;
			//Start at a node with an outgoing edge
		
		return start;
	}
	boolean graphHasEulerPath() {
		
		//Checks for euler path exists or not
		
		int c1=0,c2=0;
		
		for(int i=0;i<n;i++) {
			if(Math.abs(in[i] - out[i]) > 1) return false;
			if(in[i] - out[i] == 1) 
				c1++;
			if(out[i] - in[i] == 1 )
				c2++;
		}
		if(( c1==0 && c2==0) || (c1 == 1 && c2 ==1))return true;
		return false;
	}
	void countInOutDegree() {
		for(int i=0;i<n;i++)
			for(int to : graph.edges(i)) {
				in[to]++;
				out[i]++;
			}
		
		
	}
	public static void main(String[] args) {
		DirectedGraph graph = new DirectedGraph(7);
		graph.addEdge(1,3);
		graph.addEdge(3,1);
		graph.addEdge(3,5);
		graph.addEdge(5,6);
		graph.addEdge(6,3);
		graph.addEdge(4,3);
		graph.addEdge(4,6);
		graph.addEdge(2,4);
		graph.addEdge(2,4);
		graph.addEdge(2,2);
		graph.addEdge(1,2);
		graph.addEdge(3,2);
		EulerianPath  e = new EulerianPath(graph,7);
		e.findEulerianPath();
	}	

}


