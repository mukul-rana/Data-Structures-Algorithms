package Graphs;
import java.util.*;
public class TopologicalSort extends DepthFirstSearch {

	/*
	 * 
	 * -> Only applicable for Directed Acyclic Graph(DAG's)
	 * 
	 * A topological ordering is an ordering of the nodes
	 * in a directed graph where for each directed edge from node A 
	 * to node B, node A appears before node B in the ordering.
	 * 
	 */
	
	
	
	public static int dfs(int i,int node, boolean[] visited, int[] ordering,Graph graph){
		visited[node] = true;
		for(int i1=0;i1<graph.numberOfNodesConnected(node);i1++)
			if(!visited[graph.edges(node).get(i1)])
				 i = dfs(i,graph.edges(node).get(i1),visited,ordering,graph);
		
		ordering[i] = node;
		return i-1;
	}
	public static int[] topsort(DirectedGraph graph) {
		
		/*
		 * Simple Intuition but cannot detect cycles
		 */
		
		int N = graph.numberOfNodes();
		boolean visited[] = new boolean[N];
		int[] ordering =new int[N];
		int i=N-1;
		
		for(int j=0;j<N;j++) 
			if(!visited[j]) {
				i = dfs(i,j,visited,ordering, graph);
			}
		
		return ordering;
	}
	public static void main(String[] args) {
		int N=14;
		DirectedGraph graph = new DirectedGraph(N);
		//graph.addEdges(18);
		graph.addEdge(0,3);
		graph.addEdge(4,0);
		graph.addEdge(4,3);
		graph.addEdge(4,5);
		graph.addEdge(5,9);
		graph.addEdge(5,10);
		graph.addEdge(10,9);
		graph.addEdge(9,11);
		graph.addEdge(9,12);
		graph.addEdge(8,11);
		graph.addEdge(7,8);
		graph.addEdge(7,9);
		graph.addEdge(6,8);
		graph.addEdge(1,3);
		graph.addEdge(2,0);
		graph.addEdge(2,1);
		graph.addEdge(3,6);
		graph.addEdge(3,7);
		graph.printGraph();
		
		
//		 ystem.out.println("\n After all " + marr.length);
		
		int arr[] = kahnsTopSort(graph);
		System.out.println("\n");
		for(int x : arr)
			System.out.println(x);
		
		
		
	}
	
	
	public static int[] kahnsTopSort(DirectedGraph graph) {
		
		/*
		 * The intuition behind Kahs's algorithm is to repeatedly remove
		 * nodes without any dependencies from the graph and add them
		 * to topological ordering.
		 * As nodes without dependencies (and their outgoing edges)
		 * are removed from the graph, new nodes without dependencies should
		 * become free. We repeat removing nodes without dependencies from the
		 * graph until all nodes are processed, or a cycle is discovered.
		 * 
		 *  
		 *  
		 *  -> It can detect cycles, return an array of 1 element contating -1 if 
		 * it is cyclic graph.
		 */
		
		
		int[] ordering = new int[graph.numberOfNodes()];
		int[] incomingEdges = new int[graph.numberOfNodes()];
		ListIterator<Integer> iterator;
		for(int i=0;i<graph.numberOfNodes();i++) {
			iterator = graph.edges(i).listIterator();
			while(iterator.hasNext()) 
				incomingEdges[iterator.next()]++;
		}
		ArrayDeque<Integer> q = new ArrayDeque<>();
				
		int ord=0;
		for(int i=0;i<incomingEdges.length;i++)
			if(incomingEdges[i] == 0) {
				q.addLast(i);
				//ordering[ord++] = incomingEdges[i];
			}
		System.out.println(q);
		int node,present;
		while(!q.isEmpty()) {
			
				node= q.removeFirst();
				incomingEdges[node]--;
				ordering[ord++] = node;
					iterator = graph.edges(node).listIterator();
					while(iterator.hasNext()) {
						present = iterator.next();
						incomingEdges[present]--;
						if(incomingEdges[present] ==0) q.addLast(present);
					}		
			}
				
		if(ord != graph.numberOfNodes()) 
			return  new int[1];
		return ordering;
		
		
	}

}





//Topological Sorting is mainly used for
//
//1. Finding deadlocks in OS
//2. Scheduling jobs from the given dependencies among jobs.
//3. Instruction scheduling
//4. Ordering of formula cell evaluation when recomputing formula values in spreadsheets
//5. Logic synthesis
//6. Determining the order of compilation tasks to perform in makefiles
//7. Data serialization
//8. Resolving symbol dependencies in linkers
//9. Finding cycles in a graph
//10. Finding prerequisites of a task