package Graphs;

import java.util.Scanner;

public class DirectedGraph extends Graph{
	int edges;
	public DirectedGraph(int nodes){
		super(nodes);
		edges=0;
	}
	
	Scanner inp = new Scanner(System.in);
	//ArrayList<ArrayList<Integer>> arr;
	
	public void addEdges(int numberOfEdges) {
		for(int i=0;i<numberOfEdges;i++) {
			System.out.println("Enter the vertices to be connected ");
			addEdge(inp.nextInt(), inp.nextInt());
		}
	}
	
	public void addEdge(int i, int j) {
		arr.get(i).add(j);
		edges++;
	}
	public int numberOfEdges() {
		return edges;
	}
}
