package Graphs;
import java.util.*;
	
/*
 * Given an undirected graph with weighted edges, a Minimum Spanning Tree (MST) is
 * a subset of the edges in the graph which connects all vertices together ( without
 * creating any cycles ) while minimizing the total edge cost.
 * 
 * All nodes must be connected to form a spanning tree ( 1 connected component)
 * 
 * Prim's is a greedy MST algorithm that works well on dense graphs. On these graphs, 
 * Prim's meets or improves on the time bounds of its popular rivals (Kruskal's).
 * 
 * However, when it comes to finding the minimum spanning forest on a disconnected graph.
 * Prim's cannot do this as easily (the algorithm must be run on each connected component
 * individually ).
 * 
 * The lazy version of Prim's has a runtime of O(E*log(E)), but the eager version has a
 * better runtime of O(E*log(V)).
 * 
 */

//PRIM'S ALGORITHM
public class MinimumSpanningTree {
	static void MST(UndirectedWeightedGraph graph) {

		//For especially dense graphs, prefer using an adjacency matrix instead of an adjacency
		//list to improve performance.
		int n = graph.numberOfNodes();
		int mstCost=0;
		//For tracking cost of the MST
		
		PriorityQueue<WeightedEdge> q = new PriorityQueue<>();
		//A min PriorityQueue that sorts edges based on min edge cost. This will be used to
		// determine the next node to visit and the edge used to get there.
		
		
		boolean[] visited = new boolean[n];
		
		//Start the algo on any node and mark it as visited & iterate over all edges adding to q
		visited[0] = true;
		for(WeightedEdge to : graph.edgesWithWeight(0))
			q.add(to);
		
		
		ArrayList<WeightedEdge> path = new ArrayList<>();
		WeightedEdge current;
		
		
		//While PQ is not empty and a MST has not been formed, poll the next cheapest edge from PQ
		while(!q.isEmpty() && path.size() < n) {
			current = q.poll();
			if(visited[current.getTo()]) continue; 
				visited[current.getTo()] = true;
				mstCost += current.getWeight();
				path.add(current);
				for(WeightedEdge to : graph.edgesWithWeight(current.getTo()))
					if(!visited[to.getTo()])
						q.add(to);
			
		}
		
		if(path.size()  < n-1) {
			System.out.println("Cannot find MST");
			return ;
		}
		System.out.println("Cost of MST : " + mstCost); 
		System.out.println(path);
			
	}
	
	static void EagerPrimMST(UndirectedWeightedGraph graph) {
		IndexedPriorityQueue3  q = new IndexedPriorityQueue3(graph.numberOfNodes());
		int n = graph.numberOfNodes();
		boolean[] visited = new boolean[n];
		visited[0] = true;
		ArrayList<Integer> arr = new ArrayList<>();
		for(WeightedEdge edge : graph.edgesWithWeight(0))
			q.insert(edge.getTo(),edge.getWeight());
		int totalCost =0;
		int currentTo;
		int currentWeight;
		while(!q.isEmpty() && arr.size() < n) {
			currentTo = q.peekMinKey();
			currentWeight = q.pollMinValue();
			if(visited[currentTo]) continue;
			totalCost += currentWeight;
			visited[currentTo] = true;
			arr.add(currentTo);
			
			for(WeightedEdge edge : graph.edgesWithWeight(currentTo)) {
				if(visited[edge.getTo()] ) continue; 
				if( q.contains(edge.getTo())) {
					if(edge.getWeight() < q.valueOf(edge.getTo()))
						q.decreaseKey(edge.getTo(), edge.getWeight());
				}
				else {
					q.insert(edge.getTo(),edge.getWeight());
					
					}
			}
		}
		System.out.println(arr);
		System.out.println(totalCost);
	}
	public static void main(String[] args) {
		UndirectedWeightedGraph 	graph = new UndirectedWeightedGraph(10);
		graph.addEdge(0,1,10);
		graph.addEdge(0,2,1);
		graph.addEdge(0,3,4);
		graph.addEdge(1,2,3);
		graph.addEdge(2,3,2);
		graph.addEdge(1,4,0);
		graph.addEdge(2,5,8);
		graph.addEdge(3,5,2);
		graph.addEdge(3,6,7);
		graph.addEdge(4,5,1);
		graph.addEdge(5,6,6);
		graph.addEdge(4,7,8);
		graph.addEdge(5,7,9);
		graph.addEdge(6,7,12);
		graph.printGraph();
		//MST(graph);
		
//		UndirectedWeightedGraph 	graph2 = new UndirectedWeightedGraph(8);
//		graph2.addEdges(12);
		EagerPrimMST(graph);
	}

}




class IndexedPriorityQueue3{
	Integer keys[];
	int[] ki,pm,im,heap;
	int mapki,n,sz;
	Integer values[];
	HashMap<Integer, Integer> si;
	HashMap<Integer,Integer> is;
	
	IndexedPriorityQueue3(int n){
		this.n = n;
		mapki=0;
		values = new Integer[n];
		pm = new int[n];//POSITIN MAP: for going from key to node in PriorityQueue
		im = new int[n]; //INVERSE MAP: for going from node number in PriorityQueue to a key
		keys = new Integer[n];	
		 sz=0;
		 
		si = new HashMap<>();
		is = new HashMap<>();
	}
	

	int valueOf(Integer key) {
		
		return values[si.get(key)];
	}
	
	boolean contains(int key) {
		for(Integer x : keys) 
			if(x != null && x == key) return true;
		
		return false;
	}
	
	boolean isEmpty() {
		return sz==0;
	}
	
	int peekMinKey() {
		return keys[im[0]];
	}
	
	
	int pollMinKey() {
		int re = keys[im[0]];
		remove(keys[im[0]]);
		return re;
	}
	int peekMinValue() {
		return values[im[0]];
	}
	
	int pollMinValue() {
		return remove(keys[im[0]]);
	}
	
	
	void insert (int key, int value) {
		keys[mapki] = key;
		si. put(key, mapki);
		is.put( mapki,key);
		int ki = mapki++;
		values[ki] = value;
		pm[ki] = sz;
		im[sz] = ki; 
		swim(sz);
		sz++;
		
	}
	void swim(int i) {
		for(int p = (i-1)/2;i>0 && less(i,p) ;) {
			swap(i,p);
			i =p;
			p = (i-1)/2;
		}
	}
	void swap(int i,int j) {
		
		pm[im[j]] = i;
		pm[im[i]] = j;
		int tmp = im[i];
		im[i] = im[j];
		im[j] = tmp;
	}
	boolean less(int i, int j) {
		return values[im[i]] < values[im[j]];
	}
	void update(Integer key, int value) {
		int ki= si.get(key);
		
		int i = pm[ki];
		values[ki] = value;
		sink(i);
		swim(i);
	}
	
	void decreaseKey(Integer key, int value) {
		int ki= si.get(key);
		
		if(value < values[ki]) {
			values[ki] = value;
			swim(pm[ki]);
		}
		
	}
	
	void increaseKey(Integer key, int value) {
		int ki= si.get(key);
		
		if(values[ki]<value) {
			values[ki] = value;
			sink(pm[ki]);
		}
	}
	
	
	
	
	int remove(Integer key) {
		int ki= si.get(key);
		int i = pm[ki];
		
		swap(i,sz-1);
		
		
		sz--;
		sink(i);
		swim(i);
		int re = values[ki];
		values[ki] = 0;
		pm[ki] = 0;
		im[sz] = 0;
		return re;
	}
	
	void sink(int i) {
		int left,right,smallest;
		while(true) {
			left = 2*i +1;
			right = 2*i +2;
			smallest = left;
			if(right < sz && less(right,left))
				smallest = right;
			if(left >= sz || less(i,smallest))
				break;
			swap(smallest,i);
			i = smallest;
		
		} 
	}
	void printa() {
		int ki;
		for(int i=0;i<values.length;i++) {
			if(i>=sz) break;
			ki = im[i];
			if(im[i] <0) continue;
			//System.out.println(values[i] + " "  + pm[i] + " " +  im[i] );
			
		System.out.print(", KEys " + keys[ki] + " " + values[ki] );
		}
		System.out.println();

	}
	
}  
