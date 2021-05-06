package Graphs;

public class BellmanFord {
	
	/*
	 * Single Source Shortest Path algorithm
	 * 
	 * Not so ideal for SSSP's due to complexity of O(VE) [Dijkstra come into play]
	 * 
	 *  BUT :) Dijkstra fails for graph with negative edge weights.
	 *  Then Bellman Ford comes    into play
	 *  
	 *  Can detect negative cycles and determine where they occur
	 *  
	 */
	
	public static void bellmanFord(DirectedWeightedGraph graph, int s) {
		int v=  graph.numberOfNodes();
		
		double[] dist = new double[graph.numberOfNodes()];
		java.util.Arrays.fill(dist, Double.POSITIVE_INFINITY);
		//Setting every entry in distance array to positive infinity
		
		dist[s] = 0;
		double newDist;
		
		for(int j=0;j<v-1;j++) {
		//relaxing each edge v-1 times	
			
			for(int i=0;i<v;i++) {
				for(WeightedEdge edge : graph.edgesWithWeight(i)) {
					newDist = dist[i] + edge.getWeight();
					if(newDist  < dist[edge.getTo()]) dist[edge.getTo()] = newDist;
				}
			}
		}
		
		for(int i=0;i<v;i++)
			System.out.println(i + " " +  dist[i] )  ;
		System.out.println("\n\n\n");
		
		
			for(int i=0;i<v;i++) {
				for(WeightedEdge edge : graph.edgesWithWeight(i)) {
					
					if(dist[i] + edge.getWeight() < dist[edge.getTo()]) dist[edge.getTo()] = Double.NEGATIVE_INFINITY;
		
				}
			}
		
		
		
		for(int i=0;i<v;i++)
			System.out.println(i + " " +  dist[i] )  ;
	}
	public static void main(String[] args) {
		DirectedWeightedGraph graph = new DirectedWeightedGraph(10);
		graph.addEdge(0,1,5);
		graph.addEdge(1,2,20);
		graph.addEdge(1,5,30);
		graph.addEdge(1,6,60);
		graph.addEdge(2,3,10);
		graph.addEdge(3,2,-15);
		graph.addEdge(4,9,100);
		graph.addEdge(5,4,25);
		graph.addEdge(5,6,5);
		graph.addEdge(5,8,50);
		graph.addEdge(6,7,-50);
		graph.addEdge(7,8,-10);
		graph.addEdge(2,4,75);
//		graph.printGraph();
		
		bellmanFord(graph,0);
	}

}
