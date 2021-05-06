package Graphs;
import java.util.ArrayDeque;

/*
 * The BFS is particularly useful for one thing: finding the shortest path on
 * unweighted graph
 * 
 * Time complexity : O(V+E)
 */


public class BreadthFirstSearch {
	public static int[] bfs(UndirectedGraph graph, int node) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.addFirst(node);
		boolean[] visited = new boolean[graph.numberOfNodes()];
		visited[node] = true;
		int[] prev = new int[graph.numberOfNodes()];
		for(int i=0;i<graph.numberOfNodes();i++)
			prev[i] = -1;
		int next;
		while(!q.isEmpty()) {
			node = q.removeFirst();
			//System.out.println(node);
			for(int i=0;i<graph.numberOfNodesConnected(node);i++) {
				next = graph.edges(node).get(i);
				if(!visited[next])
				{q.addLast(next);
				visited[next ] = true;
				prev[next] = node;}
			}
		}
		return prev;
		
	}
	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(13);
		graph.addEdge(10, 1);
		graph.addEdge(10, 9);
		graph.addEdge(8, 9);
		graph.addEdge(8, 1);
		graph.addEdge(12, 2);
		graph.addEdge(12, 8);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(3, 7);
		graph.addEdge(7, 6);
		graph.addEdge(5, 6);
		graph.addEdge(7, 0);
		graph.addEdge(7, 11);
		graph.addEdge(11, 0);
		graph.addEdge(0, 9);
		graph.printGraph();
		
		 bfs(graph,0);
		 
		 shortestPath(graph,10,5);

	}
	public static void shortestPath(UndirectedGraph graph, int s, int e) {
		int[] prev = bfs(graph,s);
		print(prev);
		print(reconstructPath(s,e,prev));
	}
	private static int[] reconstructPath(int s, int e,int[] prev) {
		int[] path = new int[prev.length];
		int j=0;
		for(int i = e; i != -1; i = prev[i])
			path[j++] = i;
		int arr[] = new int[j],k=0;
		System.out.println(j);
		while(j>0) 
			arr[k++] = path[--j];
		
		
		if(arr[0] == s)
			return arr;
		return new int[0];
	}
	 public static void swap(int[] arr, int i, int j) {
		 int t = arr[i];
		 arr[i] = arr[j];
		 arr[j] = t;
	 }
	public static void print(int[] arr) {
		System.out.println("\nHere is the path ");
		for(int x : arr)
			System.out.print(x + " - > ");
		System.out.println();
	}
	

}
