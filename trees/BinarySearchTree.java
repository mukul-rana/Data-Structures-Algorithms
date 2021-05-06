package trees;

/*
 * Left Child node is smaller than parent node and Right child node is greater than
 * parent node.
 * 
 * JDK :
 * 	TreeMap<K,V> ,  TreeSet<E>
 */
import java.lang.Math;

class BSTreeNode{
	BSTreeNode left,right;
	int value;
	
	BSTreeNode(int value){
		
		left = null;
		this.value = value;
		right = null;
		
	}
	
	public void add(int newValue) {
		if(newValue < value ) {
			if(left != null)
				left.add(newValue);
			else
				left = new BSTreeNode(newValue);
		}
		else {
			if(right != null)
				right.add(newValue);
			else
				right = new BSTreeNode(newValue);
		}
			
	}
	
	public void print() {
		
		if(left != null)
		 left.print();
		System.out.println(value);
	 if(right != null)
		 right.print();
	 
		 
	 
	}
	
	public BSTreeNode getLeft() {
		return left;
	}

	public void setLeft(BSTreeNode left) {
		this.left = left;
	}

	public BSTreeNode getRight() {
		return right;
	}

	public void setRight(BSTreeNode right) {
		this.right = right;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
	
	public int get(int data) {
		if(value == data)
			return value;
		if(data < value) {
			return left.get(data);
		}
		else
			return right.get(data);
		
	}
	
	public int min() {
		if(left == null)
			return value;
		return left.min();
	}
	
	public int max() {
		if(right == null) return value;
		return right.max();
	}
	
	
	
	
	
	
}


public class BinarySearchTree {
	int size;
	BSTreeNode root;
	BinarySearchTree(){
		size = 0;
		
	}
	
	public void add(int newValue) {
		if(root == null) 
			root = new BSTreeNode(newValue);
		else
			root.add(newValue);
		size++;
	}
	
	public int get(int newValue) {
		return root.get(newValue);
	}
	
	public void remove(int  oldValue) {
		root = remove(root,oldValue);
	}
	
	public BSTreeNode remove(BSTreeNode subroot, int oldValue) {
		if(subroot == null ) return subroot;
		if(oldValue < subroot.value) {
			subroot.setLeft(remove(subroot.getLeft(),oldValue));
		}
		else if(oldValue > subroot.value)
			subroot.setRight(remove(subroot.getRight(),oldValue));
		else {
			if(subroot.getRight() == null ) return subroot.getLeft();
			else if(subroot.getLeft() == null) return subroot.getRight();
			
			subroot.setValue(subroot.getRight().min());
			subroot.setRight(remove(subroot.getRight(),subroot.getValue()));
		}
		return subroot;
	}
	
	public int height() {
		return height(root);
	}
	public int height(BSTreeNode node) {
		if(node == null) return -1;
		
		return Math.max(height(node.left),height(node.right)) +1;
	}
	
	public int leafSum(BSTreeNode node) {
		if(node == null) return 0;
		if(node.left == null && node.right == null)
			return node.value;
		int total =0;
		total = total + leafSum(node.getLeft()) + leafSum(node.getRight());
		return total;
	}
	
	public int leafSum() {
		return leafSum(root);
	}
	
	public int size() {
		return size;
	}
	public void print() {
		root.print();
	}
	
	public int max() {
		return root.max();
	}
	
	public int min() {
		return root.min();
	}
}
