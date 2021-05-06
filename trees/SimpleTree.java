package trees;
import java.util.*;

	

public class SimpleTree {
	
	public static void main(String[] args) {
		TreeNode parent = new TreeNode(0);
		TreeNode child1 = new TreeNode(1);
		TreeNode child2 = new TreeNode(2);
		TreeNode child3 = new TreeNode(3);
		TreeNode child4 = new TreeNode(4);
		TreeNode child5 = new TreeNode(5);
		TreeNode child6 = new TreeNode(6);
		
		parent.addChild(child1);
		parent.addChild(child2);
		parent.addChild(child5);
		child2.addChild(child3);
		child5.addChild(child4);
		child5.addChild(child6);
		
		parent.printTree();
		System.out.println("Child bhao  " +  child6.depth());
		
		
	}
	
}
