package Graphs;
import java.util.*;


/*
 * Strongly Connected Components (SCCs) can be thought of as self-
 * contained cycles within a directed graph where every vertex in a 
 * given cycle can reach every other vertex in the same cycle.
 * 
 * The low-link value of a node is the smallest[lowest] node id reachable
 * from that node when doing a DFS (including itself).
 * 
 * Depending on where the DFS starts and which edges are visited the 
 * low-link values could be wrong. In the context of Tarjan's SCC
 * algorithm we maintain an invariant that prevents SCCs to interfere
 * with each others' low-link values.
 * 
 * NEW LOW-LINK UPDATE CONDITION : If u and v are nodes in a graph and 
 * we're currently exploring u then our new low-link update condition is that :
 * 		To update node u's low-link value to node v's low-link value there has
 * to be a path of edges from u to v and node v must be on the stack.
 * 
 * TIME COMPLEXITY : O(V+E)
 * 
 * STEPS : 
 * 1. Mark the id of each node as unvisited.
 * 2. Start DFS. Upon visiting a node assign it an id and a low-link value.
 * Also mark current nodes as visited and add them to a seen stack.
 * 3. On DFS callback, if the previous node is on the stack then min the 
 * current node's low-link value with the last node's low-link value.
 * 4. After visiting all neighbours, if the current node started a connected
 * component then pop nodes off stack until current node is reached. 
 * 
 * 
 */
public class TarjanSCC {
	
	int[] low,ids;
	int id,n,sccc,unvisited=-1;
	DirectedGraph graph;
	
	
	boolean[] onStack;
	Stack<Integer> q;
	/*
	 * To cope with the random traversal order of the DFS, Tarjan's algorithm
	 * maintains a set (often as a stack) of valid nodes from which to update
	 * low-link values from.
	 * Nodes are added to the stack of valid nodes as they're explored for the
	 * first time.
	 * Nodes are removed from the stack each time a complete SCC is found.
	 */
	
	TarjanSCC(DirectedGraph graph,int n){
		this.graph = graph;
		this.n = n;
	}
	public  int[] tarjan() {
		sccc=0;
		id=0;
		low = new int[n];
		ids = new int[n];
		onStack = new boolean[n];
		q = new Stack<>();
		
		Arrays.fill(ids, unvisited);
		
		
		for(int i=0;i<n;i++)
			if(ids[i] == unvisited)
				dfs(i);
		
		return low;
			
	} 
	public void dfs(int at ) {
		low[at] = ids[at] = id++;
		onStack[at] = true;
		q.push(at);
		
		//Visit all neighbours & min low-link on callback
		for(int to : graph.edges(at)){
			if(ids[to] == unvisited ) 
				dfs(to);
			if(onStack[to] ) 
				low[at] = Math.min(low[at], low[to]);
			}
		
		//After having visited all the neighbours of 'at'
		// if we're at the start of a SCC empty the seen
		// stacks until we're back to the start of the SCC.
		if(ids[at] == low[at]) {
			for(int node = q.pop() ;; node = q.pop()) {
				onStack[node] = false;
				low[node] = ids[at];
				if(node == at) break;}
			sccc++;}
			
				
	}
		
	
	

	public static void main(String[] args) {
			DirectedGraph graph = new DirectedGraph(8);
			graph.addEdge(0,1);
			graph.addEdge(1,2);
			graph.addEdge(2,0);
			graph.addEdge(6,2);
			graph.addEdge(6,0);
			graph.addEdge(6,4);
			graph.addEdge(5,6);
			graph.addEdge(5,0);
			graph.addEdge(3,4);
			graph.addEdge(4,5);
			graph.addEdge(3,7);
			graph.addEdge(7,3);
			graph.addEdge(7,5);
			
			graph.printGraph();
			
			TarjanSCC t = new TarjanSCC(graph,8);
			int[] sccs = t.tarjan();
			
			//Prints : 
			//Number of Strongly Connected components : 3
			// Array indexes having same value represent SCC
			System.out.println("Number of Strongly Connected Compnents " + t.sccc);
			for(int x : sccs)
				System.out.print(x + " ");
			
	}

}
