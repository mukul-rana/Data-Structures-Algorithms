package Graphs;

import Graphs.*;
import java.util.*;

/*
 * Dijkstra's algorithm is a Single Source Shortest Path (SSSP) algorithm for 
 * graphs with **non-negative edge weights** ( This constraint is imposed to ensure
 * that once a node has been visited its optimal distance cannot be improved. This
 * property is especially important because it enables Dijkstra's algorithm to act
 * in a greedy manner by always selecting the next most promising node.) 
 * 
 * Depending on how the algorithm is implemented and what data structures are 
 * used the time complexity is typically O(E*log(V)) which is competitive against
 * other shortest path algorithms.
 * 
 */
public class DijkstraAlgo {
	
	//Returns prev nodes array
	public static Integer[] lazyDijkstra(DirectedWeightedGraph graph, Integer[] dist) {
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		//Array of distances with initially null values to represent infinite
		
		boolean[] visited = new boolean[graph.numberOfNodes()];
		
		
		Integer[] prev = new Integer[graph.numberOfNodes()];
		//For tracking the shortest path
		
		dist[0] = 0;
		//Distance of 0 from 0 is 0
		
		
		
		WeightedEdge p ;
		PriorityQueue<WeightedEdge> q = new PriorityQueue<>();
		//PQ of key-value WeightedEdges of (node, distance) WeightedEdges which tells which
		//visit next based on sorted value
		q.add(new WeightedEdge(0,0,0));
		int prevNode = 0;
		int newDist;
		while(!q.isEmpty()) {
			p = q.poll();
			//Polling out edge with least weight
				System.out.println("Chwce");
				visited[p.getTo()] = true;
				if(dist[p.getTo()] < p.getWeight()) 
				continue;
				//A neat optimization we can do which ignores stale(node, distance)
				//pairs in our PQ is to skip nodes where we already found a better path 
				//routing through other nodes before we go to processing this node.
				
				
			prev[p.getTo()] = prevNode;
				
			prevNode = p.getTo();
//			System.out.println(p);
			for (  WeightedEdge edge: graph.edgesWithWeight(p.getTo())) {
				
				if(visited[edge.getTo()]) continue;
				
				newDist = dist[p.getTo()] + edge.getWeight();
				
				//It is possible to have duplicate node indices in the PQ. This is not
				//ideal, but inserting a new key-value pair in O(log(n)) is much faster
				//than searching for the key in the PQ which takes O(n)
				
				
				
				if(newDist < dist[edge.getTo()]) { 
					q.add(new WeightedEdge(edge.getAt(),edge.getTo() , newDist));
					dist[edge.getTo()] = newDist  ;
					prev[edge.getTo()] = p.getTo(); 
				}
			
			//System.out.println(p.getTo());
			}
		}
//		graph.printGraph();
//		
//		System.out.println();
//		
		for( Integer c : dist)
			System.out.println(c);
		
		//Lazy Dijkstra is also efficient to obtain minimum distance of a particular node
		// because in Dijkstra there is no need to visit every node therefore, when we find the 
		//value, we can return minimum distance of that node.
		
		return prev;
	}
	
	static ArrayList<Integer> findShortestPath(DirectedWeightedGraph graph, int start, int end){
		ArrayList<Integer> path = new ArrayList<>();
		Integer[] dist = new Integer[graph.numberOfNodes()];
		Integer[] prev = lazyDijkstra(graph,dist);
		if(dist[end] == Integer.MAX_VALUE) return path;
		for(int at = end ; at != 0;at = prev[at])
			path.add(at);
		Collections.sort(path);
			
		//We can optimize it comparing dijkstra's edge.getTo() == end 
		// THan we can stop
		
		return path;
		
	}
	public static void main(String[] args) {
			DirectedWeightedGraph graph = new DirectedWeightedGraph(5);
			graph.addEdge(0,1,4);
			graph.addEdge(0,2,1);
			graph.addEdge(2,1,2);
			graph.addEdge(1,3,1);
			graph.addEdge(3,4,3);
			graph.addEdge(2,3,5);
			
			lazyDijkstra(graph, new Integer[5]);
			System.out.println();
			
			DirectedWeightedGraph graph2 = new DirectedWeightedGraph(6);
			graph2.addEdge(0,1,5);
			graph2.addEdge(0,2,1);
			graph2.addEdge(1,2,2);
			graph2.addEdge(2,1,3);
			graph2.addEdge(1,3,3);
			graph2.addEdge(2,4,12);
			graph2.addEdge(1,4,20);
			graph2.addEdge(3,2,3);
			graph2.addEdge(3,4,2);
			graph2.addEdge(3,5,6);
			graph2.addEdge(4,5,1);
			EagerDijkstra(graph2);
//			
//			lazyDijkstra(graph, new Integer[5]);
	}
	
	static void EagerDijkstra(DirectedWeightedGraph graph) {
		
		/*
		 * Lazy implementation inserts duplicate key-value pairs in our PQ
		 * This approach is inefficient for dense graphs because we end up
		 * with several stale outdated key-value pairs in our PQ. 
		 * The Eager version of Dijkstra's avoids duplicate key-value pairs 
		 * and supports efficient value updates in O(log(n)) by using an
		 * Indexed PriorityQueue(IPQ).
		 * 
		 */
		Integer[] dist = new Integer[graph.numberOfNodes()];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		
		int n = graph.numberOfNodes(),newDist;
		
		IndexedPriorityQueue2 q = new IndexedPriorityQueue2(graph.numberOfNodes());
		q.insert(0,0);
		
		
		int currentTo,currentWeight;
		
		boolean[] visited = new boolean[n];
		while(!q.isEmpty()) {
			 
			currentTo = q.peekMinKey();
			currentWeight = q.pollMinValue();
			
			visited[currentTo] = true;
			
			if(dist[currentTo] < currentWeight ) continue; 
			for(WeightedEdge edge : graph.edgesWithWeight(currentTo)) {
				if(visited[edge.getTo()]) continue;
				newDist = dist[currentTo] + edge.getWeight();
				if(newDist < dist[edge.getTo()]){
					dist[edge.getTo()] = newDist;
					if(!q.contains(edge.getTo()))
						q.insert(edge.getTo(), newDist);
					else
					q.decreaseKey(edge.getTo(),newDist);
					//The main advantage to using decreaseKey is to prevent
					//duplicate node indexes to be present in the PQ. 
					
				}
			}
			
		}
		
		for(Integer x : dist)
			System.out.println(x);
		
	}
}




class IndexedPriorityQueue{
	Integer keys[];
	int[] ki,pm,im,heap;
	int mapki,n,sz;
	Integer values[];
	HashMap<Integer, Integer> si;
	HashMap<Integer,Integer> is;
	boolean[] contains ;
	
	IndexedPriorityQueue(int n){
		this.n = n;
		contains = new boolean[n];
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
		return contains[si.get(key)];
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
		contains[ki] = true;
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
		contains[ki] = false;
				
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

class IndexedPriorityQueue2{
	Integer keys[];
	int[] ki,pm,im,heap;
	int mapki,n,sz;
	Integer values[];
	HashMap<Integer, Integer> si;
	HashMap<Integer,Integer> is;
	
	IndexedPriorityQueue2(int n){
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


