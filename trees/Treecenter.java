package trees;
import java.util.*;
import Graphs.UndirectedGraph;
public class Treecenter {
	/*
	 * Center(s) of an Undirected Acyclic Graph.
	 * It can have either 1 or 2 center
	 */
	public static ArrayList<Integer> centerOfTree(UndirectedGraph graph) {
		int n = graph.numberOfNodes();
		int[] degree = new int[n];
		ArrayList<Integer> leaves = new ArrayList<>();
		int i,j;
		for(i=0;i<n;i++) {
			degree[i] = graph.numberOfNodesConnected(i);
			if(degree[i] <=1) {
				leaves.add(i);
				//degree[i] =0;
				// ^ to be verify 
			}
		}
		System.out.println(leaves);
		
		ArrayList<Integer> newLeaves;
		int count = leaves.size();
		int node,neighbour;
		while(count < n) {
			newLeaves =new ArrayList<>();
			for( i=0;i<leaves.size();i++) {
				node = leaves.get(i);
				for(j=0;j< graph.edges(node).size();j++) {
					neighbour =graph.edges(node).get(j);
					degree[neighbour]--;
					if(degree[neighbour]==1)
						newLeaves.add(neighbour);
				}
				degree[node] =0;
			}
			count += newLeaves.size();
			leaves = newLeaves;
		}
		
		return leaves;
	}
	
	
	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(6);
		graph.addEdges(5);
		ArrayList<Integer> arr = centerOfTree(graph);
		System.out.print("Centers");
		for(int i=0;i<arr.size();i++)
			System.out.print(" " + arr.get(i));
		//graph.printGraph();
		
	}

}
