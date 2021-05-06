package Graphs;
import java.util.*;
import static java.lang.Math.min;

/*
 * A bridge / cut edge is any edge in a graph whose removal increases
 *  the number of connected components.
 *  
 *  Start at any node and do a DFS traversal labeling nodes with an 
 *  increasing id value as we go. Keep track the id of each node 
 *  and the smallest low-link value. During the DFS, bridges will be
 *  found where the id of the node your edge is coming from is less
 *  than the low link value of the node your edge is going to.
 *  The low-link value of a node is defined as the smallest [lowest]
 *  id reachable from that node when doing a DFS (including itself).
 *  
 *   Time Complexity : O(V+E)
 */


public class Bridges {
	 private int n, id;
	  private int[] low, ids;
	  private boolean[] visited;
	  UndirectedGraph graph;
	  
	  Bridges(UndirectedGraph graph,int n){
		  this.graph = graph;
		  this.n = n;
	  } 
	  public List<Integer> findBridges() {
		    id =0;
		    low = new int[n];
		    ids = new int[n];
		    visited = new boolean[n];
		    
		    List<Integer> bridges = new ArrayList<>();
		    
		    for(int i=0;i<n;i++)
		    	if(!visited[i]) 
		    		dfs(i,-1,bridges);
		    
		    return bridges;
		    //The bridges list is always of even length and indexes (2*i, 2*i+1) form a bridge.
		  }
	
	  private void dfs(int at, int parent, List<Integer> bridges) {

		    visited[at] = true;
		    low[at] = ids[at] = id++;

		    for (Integer to : graph.edges(at)) {
		      if (to == parent) continue;
		      if (!visited[to]) {
		        dfs(to, at, bridges);
		        low[at] = min(low[at], low[to]);
		        if (ids[at] < low[to]) {
		          bridges.add(at);
		          bridges.add(to);
		        }
		      } else 
		        low[at] = min(low[at], ids[to]);
		      
		    }
		  }

	public static void main(String[] args) {
		Bridges n = new Bridges(new UndirectedGraph(9),9);
		
		n.graph.addEdge(0,1);
		n.graph.addEdge(0,2);
		n.graph.addEdge(1,2);
		n.graph.addEdge(2,3);
		n.graph.addEdge(3,4);
		n.graph.addEdge(2,5);
		n.graph.addEdge(5,6);
		n.graph.addEdge(6,7);
		n.graph.addEdge(7,8);
		n.graph.addEdge(8,5  );
		System.out.println(n.findBridges());
	}

}
/*
 * Bridges and articulation points are important in graph theory
 * because they often hint at weak points, bottlenecks or 
 * vulnerabilities in a graph. Therefore, it's important to be 
 * able to quickly find/detect when and where these occur.
 * 
 * Both problems are related so we will develop an algorithm to
 * find bridges and then modify it slightly to find articulation
 * points.
 */
