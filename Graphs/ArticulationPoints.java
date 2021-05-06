package Graphs;

/*
 * An articulation point / cut vertex is any node in a graph whose
 * removal increases the number of connected components.
 * (very related to bridges)
 * 
 * On a connected component with three or more vertices if an edge (u,v) is a
 * bridge then either u or v is an articulation point. 
 * 
 * There exist cases where there is an articulation point without a bridge due to cycles which can be solved
 * by determining the starting of the cycle but the only time id.(e.from) == lowlink(e.to)
 * fails is when the starting node has 0 or 1 outgoing directed edges. This is because 
 * either the node is singleton (0 case) or the node is trapped in a cycle(1 case).
 * However, when there are more than 1 outgoing edge the starting node can escape the 
 * cycle and thus becomes an articulation point.
 */
public class ArticulationPoints {
	int id ,n, outEdgeCount ;
	UndirectedGraph graph;
	int[] low,ids;
	boolean[] visited,isArt;
	
	ArticulationPoints(UndirectedGraph graph, int n){
		this.graph = graph;
		this.n = n;
		
	}
	
	public boolean[] findArtPoints() {
		id=0;
		low = new int[n];
		ids = new int[n];
		visited =  new boolean[n];
		isArt = new boolean[n];
		for(int i=0;i<n;i++)
			if(!visited[i]) {
				outEdgeCount =0;
				dfs(i,i,-1);
				isArt[i] = (outEdgeCount >1 );
			}
		for(int i=0;i<isArt.length;i++)
			if(isArt[i])
				System.out.print(i + " ");
		return isArt;
	}
	public void dfs(int root, int at, int parent ) {
		if(parent == root ) outEdgeCount++;
		visited[at] = true;
		low[at] = ids[at] = id++;
		
		for(int to : graph.edges(at)) {
			if( to == parent) continue;
			if(!visited[to]) {
				dfs(root,to,at);
				low[at] = Math.min(low[at], low[to]);
				
				if(ids[at] <= low[to]) 
			isArt[at] = true;//Articulation point found via bridge
			
			
				
//				if(ids[at] == low[to])
//					isArt[at] = true;//Articulation point found via cycle
			}
			else
				low[at] = Math.min(low[at], low[to]);
		}
		
	}
	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(9);
		 graph.addEdge( 0, 1);
		    graph.addEdge( 0, 2);
		    graph.addEdge( 1, 2);
		    graph.addEdge (2, 3);
		    graph.addEdge (3, 4);
		    graph.addEdge (2, 5);
		    graph.addEdge (5, 6);
		    graph.addEdge (6, 7);
		    graph.addEdge (7, 8);
		    graph.addEdge (8, 5);
		ArticulationPoints p = new ArticulationPoints(graph,9);
		boolean[] art  = p.findArtPoints();
//		for(int i=0;i<art.length;i++)
//			if(art[i])
//				System.out.print(i + " ");

	}

}
