package Graphs;

import java.util.Scanner;

public class DirectedWeightedGraph extends WeightedGraph{

	int numberOfEdges;
	Scanner inp = new Scanner(System.in);
	
	public DirectedWeightedGraph(int numberOfNodes) {
		super(numberOfNodes);
		numberOfEdges=0;
	}
	
	public void addEdges(int numberOfEdgesToBeConnected) {
		for(int i=0;i< numberOfEdgesToBeConnected;i++) {
			System.out.println("Enter the vertices to be connected with weight ");
			addEdge( inp.nextInt(), inp.nextInt(), inp.nextInt());
		}
	}
	
	public void addEdge(int i, int j, int weight) {
		arr.get(i).add(new WeightedEdge(i,j,weight));
		numberOfEdges++;
	}
	
	public int numberOfEdges() {
		return numberOfEdges;
	}
	
	public boolean removeEdge(int i, int j ) {
		return false;
		//Under Construction :~(
	}

}
