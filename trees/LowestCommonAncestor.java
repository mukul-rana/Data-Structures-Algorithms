package trees;
import java.util.*;

/*
 * Algorithm : Eulerian tour + Range Minimum Query (RMQ) method
 * This method can answer LCA queries in O(1) time with O(nlogn) 
 * pre-processing when using a Sparse Table to do the RMQs
 * 
 *  The pre-processing time can be improved to O(n) with the 
 *  French-Colton and Bender Optimization.
 * 
 * 
 * 
 * The Lowest Common Ancestor(LCA) of two nodes 'a' and 'b' in a 
 * rooted tree is the deepest node 'c' that has both 'a' and 'b'
 * as descendants (where a node can be a descendant of itself)
 * 
 * NOTE: The notion of a LCA also exists for Directed Acyclic Graphs(DAGs)
 * 
 * Other Algorithms :
 * 		Tarjan's Offline LCA algorithm
 * 		Heavy-Light Decomposition
 * 		Binary Lifting etc.
 */






public class LowestCommonAncestor {
	
	public static int lowestCommonAncestor(TreeNode c1, TreeNode c2, TreeNode parent) {
		
		//Main Function
		
		Depth[] depth = new Depth[2*17-1];
		depth[0] =  new Depth(0,0);
		//Adding parent
		
		
		
		
		
		
		int[] lastEncounter = new int[17];
		//Tracking last occurring index of all nodes
		
		EulerianPathing( parent,depth,lastEncounter,1);
		//Start Eulerian Tour at the root node and return to root node 
		for(Depth d : depth)
			System.out.println(d.node + " " + d.depth);
	
				
		int i,j;
		i = lastEncounter[c1.getData()] < lastEncounter[c2.getData()] ? lastEncounter[c1.getData()] : lastEncounter[c2.getData()];
		j = lastEncounter[c1.getData()] < lastEncounter[c2.getData()] ? lastEncounter[c2.getData()] : lastEncounter[c1.getData()];
		
		
		int temp = depth[i].depth, in=depth[i].node;
		for(int k =i ; k <= j ;k++)
			if(temp> depth[k].depth) {temp = depth[k].depth; in = depth[k].node;}
		
		return in;
	}
	
	
	public static int EulerianPathing(TreeNode parent, Depth[] depth  ,int[] lastEncounter, int i){
		
		
		for(TreeNode child : parent.getChildren()) {
			depth[i] = new Depth(child.getData() , child.depth());
			lastEncounter[child.getData()] = i;
			i = EulerianPathing(child, depth, lastEncounter, ++i);
			depth[i] = new Depth(parent.getData(), parent.depth());
			lastEncounter[parent.getData()] = i++;
		}
		return i; 
		
	}
	
	//public static

	public static void main(String[] args) {
		
		TreeNode parent = new TreeNode(0);
		TreeNode child1 = new TreeNode(1);
		TreeNode child2 = new TreeNode(2);
		TreeNode child3 = new TreeNode(3);
		TreeNode child4 = new TreeNode(4);
		TreeNode child5 = new TreeNode(5);
		TreeNode child6 = new TreeNode(6);
		TreeNode child7 = new TreeNode(7);
		TreeNode child8 = new TreeNode(8);
		TreeNode child9 = new TreeNode(9);
		TreeNode child10 = new TreeNode(10);
		TreeNode child11 = new TreeNode(11);
		TreeNode child12 = new TreeNode(12);
		TreeNode child13 = new TreeNode(13);
		TreeNode child14 = new TreeNode(14);
		TreeNode child15 = new TreeNode(15);
		TreeNode child16 = new TreeNode(16);
		
		parent.addChild(child1);
		parent.addChild(child2);
		child1.addChild(child3);
		child1.addChild(child4);
		child3.addChild(child8);
		child3.addChild(child9);
		child2.addChild(child5);
		child2.addChild(child6);
		child2.addChild(child7);
		child5.addChild(child10);
		child5.addChild(child11);
		child11.addChild(child14);
		child11.addChild(child15);
		child11.addChild(child16);
		child7.addChild(child12);
		child7.addChild(child13);
		

		
		// Sending two nodes to be compared and a parent node
				System.out.println( " \n\n" + lowestCommonAncestor(child1,parent,parent));
//		System.out.println( " \n\n" + lowestCommonAncestor(child11,child13,parent));
//		System.out.println( " \n\n" + lowestCommonAncestor(child10,child16,parent));
//		System.out.println( " \n\n" + lowestCommonAncestor(child3,child9,parent));
		
	}

}


class Depth{
	
	/*
	 * This class has two variables: Node value and Depth of that node
	 */
	 int depth,node;
	 Depth(int node, int depth){
		 this.node = node;
		 this.depth = depth;
	 }

}
