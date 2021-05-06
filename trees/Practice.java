import Graphs.UndirectedGraph;
import java.util.*;
import java.util.*;
import trees.TreeNode;

public class practice {

	public static boolean isomorphic(UndirectedGraph g1, UndirectedGraph g2) {
		String s1 = 
		
		
		return false;
	}
	
	public static TreeNode rootingATree(UndirectedGraph Graph) {
		return new TreeNode(3);
	}
	
	public static void main(String[] args) {
		UndirectedGraph graph1,graph2;
		 graph1 = new UndirectedGraph(6);
		graph1.addEdge(0,1);
		graph1.addEdge(2,1);
		graph1.addEdge(2,3);
		graph1.addEdge(2,4);
		graph1.addEdge(2,5);
		
		graph2 = new UndirectedGraph(6);
		graph2.addEdge(0, 1);
		graph2.addEdge(2, 1);
		graph2.addEdge(4, 1);
		graph2.addEdge(4, 3);
		graph2.addEdge(5, 4);
		
		graph1.printGraph();
		System.out.println("\n\n");
		graph2.printGraph();
		
		System.out.println("\n\n");
		System.out.println("\n\n");
		
		System.out.println("Above Graphs G1 and G2 are Isomorphic ? " + isomorphic(graph1,graph2) );
		
		System.out.println("\n\n");
		System.out.println("\n\n");
		System.out.println("\n\n");
		System.out.println("\n\n");
		
		graph1 = new UndirectedGraph(7);
		graph1.addEdge(0,2);
		graph1.addEdge(0,1);
		graph1.addEdge(2,6);
		graph1.addEdge(3,4);
		graph1.addEdge(2,5);
		graph1.addEdge(2,3);
		
		graph2 = new UndirectedGraph(7);
		graph2.addEdge(2,1);
		graph2.addEdge(3, 2);
		graph2.addEdge(3, 0);
		graph2.addEdge(3, 5);
		graph2.addEdge(4, 3);
		graph2.addEdge(5, 6);
		
		graph1.printGraph();
		System.out.println("\n\n");
		graph2.printGraph();
		
		System.out.println("\n\n");
		System.out.println("\n\n");
		
		System.out.println("Above Graphs G1 and G2 are Isomorphic ? " + isomorphic(graph1,graph2) );
		
	
				
	}

}


