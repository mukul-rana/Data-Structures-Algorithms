package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TreeNode {
	private int data ;
    private List<TreeNode> children = new ArrayList<>();
    private TreeNode parent = null;
    ListIterator iter;

    public TreeNode(int data) {
        this.data = data;
    }

    public void addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(int data) {
       TreeNode newChild = new TreeNode(data);
        this.addChild(newChild);
    }
    
    

    public void addChildren(List<TreeNode> children) {
        for(TreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }
	
	
	public void printTree() {
		System.out.print(data + " - > ");
		iter = children.listIterator();
		while(iter.hasNext())
			System.out.print( ((TreeNode) iter.next()).getData()  + " - ");
		System.out.println("\n");
		
		iter = children.listIterator();
		while(iter.hasNext())
			((TreeNode) iter.next()).printTree();
		
	}
	
	public int depth() {
		//Returns the depth of the node 
		//root node has 0 depth
		if(this.parent == null) return 0;
		return 1 + this.parent.depth();
	}
	

}
