package Graphs;
import java.util.*;

public class Graph {
//Adjacency List
	/*
	 * Each node has list of nodes & weight(optional) which it directs
	 * 
	 * Pros: 
	 * Space Efficient for representing sparse graphs
	 * Iterating over all edges is efficient
	 * 
	 * Cons: 
	 * Less space efficient for denser graphs
	 * Edge weight lookup is O(E)
	 * 
	 */
	
	
	
	int nodes;
	Scanner inp =new Scanner(System.in);
	protected ArrayList<ArrayList<Integer>> arr;
	Graph(int nodes){
		this.nodes = nodes;
		arr = new ArrayList<>(nodes);
		for(int i=0;i<nodes;i++)
			arr.add(new ArrayList<Integer>());
	}
	
	public int numberOfNodes() {
		return nodes;
	}
	
		
	public void printGraph() {
		int i,j;
		for(i=0;i<arr.size();i++) {
			System.out.print(i);
			for(j=0;j<arr.get(i).size();j++)
				System.out.print(" -> " + arr.get(i).get(j));
			System.out.println();
		}
	}
	
	public ArrayList<Integer> edges(int node){
		return arr.get(node);
	}
	
	public int numberOfNodesConnected(int node) {
		return arr.get(node).size();
	}
	
	
}
/*
public class Graph{

//Adjacency Matrix
 //Pros: 
 // Space efficient for dense graphs
 // Edge weight lookup is O(1)
  
 //Cons:
 // Requires O(V^2) space
 // Iterating over all edges takes O(V^2) time
	
	static void addEdge(int[][] graph, int i, int j){
		graph[i][j] = 1;
		graph[j][i] = 1;
	}
	
	static void printGraph(int[][] graph){
		int i,j;
		for(i=0;i<graph.length;i++)
			System.out.print(i + " -> ");
		System.out.println();
		for(i=0;i<graph.length;i++) {
			System.out.print(i);
		for(j=0;j<graph[i].length;j++)
			System.out.print(" -> " + graph[i][j]);
		System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int V = 6;
		int[][] graph = new int[V][V];
		addEdge(graph, 1,2);
		addEdge(graph, 1,3);
		addEdge(graph, 1,4);
		addEdge(graph, 1,5);
		addEdge(graph, 2,3);
		addEdge(graph, 2,4);
		addEdge(graph, 2,5);
		addEdge(graph, 3,4);
		addEdge(graph, 3,5);
		addEdge(graph, 4,5);
		printGraph(graph);
		
		
	}
}*/
