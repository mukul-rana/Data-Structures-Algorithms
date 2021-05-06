package Graphs;

import java.util.*;

//Directed Acyclic Graph
public class DAG {

	/*
	 * The Single Source Shortest Path (SSSP) and Single Source Longest Path (SSLP) problem can be solved
	 * in O(V+E) time. This is due to the fact that the nodes can be ordered in a topological ordering.
	 * 
	 * These are only type of graph which has a valid topological ordering.
	 * 
	 * To verify a DAG, use Tarjan's strongly connected component algorithm which can detect cycles
	 */
	
	
	public static int SSSP(DirectedWeightedGraph graph, int start, int end) {
		//Single Source Shortest Path (SSSP)
		
		int ordering[] = topologicalSort(graph);
		//topological ordering of graph
		
		Integer[] shortestPath = new Integer[graph.numberOfNodes()];
		shortestPath[start] = 0;
		//shortest path of start node from itself is 0
		
		int newDist;
		
		for(int node : ordering) { 
			if(shortestPath[node]== null) continue;
			//Those nodes which are not connected to start
			
			for(WeightedEdge edge : graph.edgesWithWeight(node)) {
				newDist = shortestPath[node] + edge.getWeight();
				shortestPath[edge.getTo()] = shortestPath[edge.getTo()]==null ? newDist : Math.min(shortestPath[edge.getTo()],newDist);
				//null represents infinity
				
			}	
		}
		
		return shortestPath[end] == null ? 0 : shortestPath[end];
		// 0 represents that there is no path between nodes start and end.
		
	}
	
	
	
	
	
	public static int SSLP(DirectedWeightedGraph graph, int start, int end) {
		//Single Source Longest Path (SSSP)
		
		int ordering[] = topologicalSort(graph);
		Integer[] longestPath = new Integer[graph.numberOfNodes()];
		longestPath[start] = 0;
		int k, newDist;
		
		for(int node : ordering) { 
			
			if(longestPath[node]== null) continue;
			for(WeightedEdge edge : graph.edgesWithWeight(node)) {
				newDist = longestPath[node] + edge.getWeight();
				longestPath[edge.getTo()] = longestPath[edge.getTo()]==null ? newDist : Math.max(longestPath[edge.getTo()],newDist); 
			}	
		}
		
		return longestPath[end];
	}
	
	
	
	public static void main(String[] args) {
//		DirectedWeightedGraph graph = new DirectedWeightedGraph(8);
//		graph.addEdge(0,1,3);
//		graph.addEdge(0,2,6);
//		graph.addEdge(1,2,4);
//		graph.addEdge(1,4,11);
//		graph.addEdge(1,3,4);
//		graph.addEdge(2,3,8);
//		graph.addEdge(2,6,11);
//		graph.addEdge(3,4,-4);
//		graph.addEdge(3,5,5);
//		graph.addEdge(3,6,2);
//		graph.addEdge(4,7,9);
//		graph.addEdge(5,7,1);
//		graph.addEdge(6,7,2);
		
		
		DirectedWeightedGraph graph = new DirectedWeightedGraph(13);
		graph.addEdge(0,3,2);
		graph.addEdge(4,0,1);
		graph.addEdge(4,3,2);
		graph.addEdge(4,5,3);
		graph.addEdge(5,9,8);
		graph.addEdge(5,10,5);
		graph.addEdge(10,9,2);
		graph.addEdge(9,11,2);
		graph.addEdge(9,12,1);
		graph.addEdge(8,11,3);
		graph.addEdge(7,8,1);
		graph.addEdge(7,9,7);
		graph.addEdge(6,8,6);
		graph.addEdge(1,3,3);
		graph.addEdge(2,0,1);
		graph.addEdge(2,1,3);
		graph.addEdge(3,6,-5);
		graph.addEdge(3,7,4);
		
		
		
		graph.printGraph();
		System.out.println(SSSP(graph,0,8));
		System.out.println(SSSP(graph,4,12));
		
		System.out.println();
		
		System.out.println(SSLP(graph,0,8));
		System.out.println(SSLP(graph,4,12));
		Integer n[] = new Integer[4];
		
		
	}
	
	public static int[] topologicalSort(DirectedWeightedGraph graph) {
		
		//Kahn's Algorithm
		
		int ordering[] = new int[graph.numberOfNodes()];
		int incomingEdges[] = new int[graph.numberOfNodes()];
		
		
		int i,j;
		for(i=0;i<graph.numberOfNodes();i++) 
			for(j = 0 ;j< graph.numberOfNodesConnected(i);j++)
				incomingEdges[graph.edgesWithWeight(i).get(j).getTo()]++;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int ord =0;
		boolean[] visited = new boolean[graph.numberOfNodes()];
		for(i=0;i< graph.numberOfNodes();i++) 
			if(incomingEdges[i] == 0) { q.add(i); visited[i]=true;}
		
		int node,neighbour;
		while(!q.isEmpty()) {
			node = q.removeFirst();
			ordering[ord++] = node;
			for( i=0;i<graph.numberOfNodesConnected(node);i++) {
				neighbour = graph.edgesWithWeight(node).get(i).getTo();
				if(!visited[neighbour]) {
				incomingEdges[neighbour]--;
				if(incomingEdges[neighbour] ==0 ) { q.add(neighbour);
					visited[neighbour] = true;
					}
				}
			}
		}
		
		return ordering;
	}

}
