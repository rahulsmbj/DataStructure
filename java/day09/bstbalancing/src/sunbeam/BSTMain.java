package sunbeam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import sunbeam.BinSearchTree.Node;

class BinSearchTree {
	static class Node {
		int data;
		Node left, right;
		public Node() {
			data = 0;
			left = null;
			right = null;
		}
		public Node(int val) {
			data = val;
			left = null;
			right = null;
		}
		@Override
		public String toString() {
			return "Node [data=" + data + "]";
		}
	}
	
	private Node root;
	public BinSearchTree() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public void add(int val) {
		Node newNode = new Node(val);
		if(isEmpty())
			root = newNode;
		else {
			Node trav = root;
			while(true) {	
				if(val < trav.data) { // if less, goto left
					if(trav.left == null) {
						trav.left = newNode;
						break;
					}
					trav = trav.left;
				}
				else { // if greater or equal, goto right
					if(trav.right == null) {
						trav.right = newNode;
						break;
					}
					trav = trav.right;
				}
			}
		}
	}
	public void preorder(Node trav) {
		if(trav == null)
			return;
		System.out.print(trav.data + ", ");
		preorder(trav.left);
		preorder(trav.right);
	}
	public void preorder() {
		System.out.print("PRE : ");
		preorder(root);
		System.out.println();
	}
	
	public int height(Node trav) {
		if(trav == null)
			return -1;
		int hl = height(trav.left);
		int hr = height(trav.right);
		return Math.max(hl, hr) + 1;
	}
	public int height() {
		return height(root);
	}
	public int balanceFactor(Node trav) {
		return height(trav.left) - height(trav.right);
	}
	
	public Node leftRotate(Node axis, Node parent) {
		if(axis == null || axis.right == null)
			return axis;
		Node newAxis = axis.right;
		axis.right = newAxis.left;
		newAxis.left = axis;
		if(axis == root)
			root = newAxis;
		else if(axis == parent.left)
			parent.left = newAxis;
		else
			parent.right = newAxis;
		return newAxis;
	}
	
	public Node rightRotate(Node axis, Node parent) {
		if(axis == null || axis.left == null)
			return axis;
		Node newAxis = axis.left;
		axis.left = newAxis.right;
		newAxis.right = axis;
		if(axis == root)
			root = newAxis;
		else if(axis == parent.left)
			parent.left = newAxis;
		else
			parent.right = newAxis;
		return newAxis;
	}
	
	// skewed bst --> balanced bst
	public void balance(Node trav, Node parent) {
		if(trav == null)
			return;
		int bf = balanceFactor(trav);
		while(bf < -1 || bf > +1) {
			if(bf < -1) {
				trav = leftRotate(trav, parent);
				bf = bf + 2;
			}
			if(bf > +1) {
				trav = rightRotate(trav, parent);
				bf = bf - 2;
			}
		}
		balance(trav.left, trav);
		balance(trav.right, trav);
	}
	public void balance() {
		balance(root, null);
	}
}

public class BSTMain {
	public static void main(String[] args) {
		BinSearchTree t = new BinSearchTree();
		t.add(70);
		t.add(60);
		t.add(50);
		t.add(40);
		t.add(30);
		t.add(20);
		t.add(10);
		t.preorder();
		System.out.println("Height: " + t.height());
		t.balance();
		System.out.println("Height: " + t.height());
		t.preorder();
	}
}







