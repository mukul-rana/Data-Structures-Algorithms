package trees;
import Graphs.UndirectedGraph;
import java.util.*;

/*
 * Rooting an undirected graph from any of its node
 * and converting to a SimpleTree
 */

public class RootingATree {

	public static void RootingATree(int n, UndirectedGraph graph) {
		
		//Using Breadth First Search
		
		TreeNode parent = new TreeNode(n);
		ArrayDeque<TreeNode> q = new ArrayDeque();
		boolean visited[] = new boolean[graph.numberOfNodes()];
		TreeNode current,arrivedOne ;
		int node;
		q.addLast(parent);
		while(!q.isEmpty())
		{
			current = q.removeLast();
			node = current.getData();
			visited[node] = true;
			if(graph.edges(node).size()==0) continue;
			for( int neighbour :  graph.edges(node)) {
				if(visited[neighbour]) continue;
				visited[neighbour] = true;
				arrivedOne = new TreeNode(neighbour);
				q.add(arrivedOne);
				current.addChild(arrivedOne);
			}
			
		}
		
		parent.printTree();
	}
	
	public static void RootingATree( UndirectedGraph graph, int n) {
		
		//Using Depth First Search
		
		TreeNode  current = new TreeNode(n);
				DFSBuildTree(graph, current, null);
		current.printTree();
	}
	
	public static void DFSBuildTree(UndirectedGraph graph, TreeNode node, TreeNode parent) {
		TreeNode current;
		for(int i=0;i< graph.edges(node.getData()).size();i++)
		{
			current = new TreeNode(graph.edges(node.getData()).get(i));
			if(parent != null && current.getData() == parent.getData() ) continue;
			node.addChild(current);
			DFSBuildTree(graph, current, node);
		}
		
	}
	
	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(6);
		graph.addEdges(5);
		//graph.printGraph();
	
		System.out.println("\n\nBreadth First Search : \n");
		RootingATree(1,graph);
		System.out.println("\n\nDepth First Search : \n");
		RootingATree(graph,1);

	}

}
