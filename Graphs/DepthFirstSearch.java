package Graphs;
import java.util.ArrayList;
import java.util.Scanner;

/*
We can use a DFS to identify componenets.
First, make sure all the nodes are labeled
from [0,n) where n is the he number of nodes

Time complexity : O(V+E)
*/
public class DepthFirstSearch {
	
	public static void dfs(UndirectedGraph graph, boolean[] visited,int node ) {
		if(visited[node]) return;
		visited[node] = true;
		//System.out.println(node);
		for(int i=0;i<graph.numberOfNodesConnected(node);i++)
			dfs(graph,visited, graph.edges(node).get(i));
		System.out.println(node);
	}
	
	public static void main(String[] args) {
		//Scanner inp = new Scanner(System.in); 
		//System.out.print("Enter the number of vertices  : " );
		//int v = inp.nextInt();
		int v =13;
		UndirectedGraph graph = new UndirectedGraph(v);
		//graph.addEdges(inp.nextInt());
		graph.addEdge(0, 1);
		graph.addEdge(0,9);
		graph.addEdge(1,8);
		graph.addEdge(1,9);
		graph.addEdge(8,7);
		graph.addEdge(7,3);
		graph.addEdge(2,3);
		graph.addEdge(3,4);
		graph.addEdge(5,3);
		graph.addEdge(5,6);
		graph.addEdge(7,6);
		graph.addEdge(7,11);
		graph.addEdge(7,10);
		graph.addEdge(11,10);
		graph.printGraph();
		dfs(graph, new boolean[v],0);
		System.out.println("\nNumber of connected components "+connectedComponents(graph, v, new boolean[v]));
	}
	
	public static int connectedComponents(UndirectedGraph graph, int V, boolean[] visited) {
		int n=0;
		for(int node=0;node<V;node++) 
			if(!visited[node]) {
				System.out.println("Head of Component " + node);
				n++;
				dfs(graph,visited,node);
			}
		
		return n;
	}
} 


/*
 * Applications of DFS :
 *  
 *  Compute a graph's minimum spanning tree
 *  Detect and find cycles in a graph
 *  Check if a graph is bipartite
 *  Find strongly connected components
 *  Topologically sort the nodes of a graph
 *  Find bridges and articulation points
 *  Find augmenting paths in a flow network
 *  Generate mazes
 */
