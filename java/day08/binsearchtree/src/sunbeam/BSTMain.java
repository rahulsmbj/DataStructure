package sunbeam;

import java.util.Stack;

import sunbeam.BinSearchTree.Node;

class BinSearchTree {
	static class Node {
		int data;
		Node left, right;
		boolean visited;
		public Node() {
			data = 0;
			left = null;
			right = null;
			visited = false;
		}
		public Node(int val) {
			data = val;
			left = null;
			right = null;
			visited = false;
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
	public void preorderNonrec() {
		System.out.print("PRE : ");
		Stack<Node> s = new Stack<>();
		Node trav = root;
		while(trav != null || !s.isEmpty()) {
			while(trav != null) {
				System.out.print(trav.data + ", ");
				if(trav.right != null)
					s.push(trav.right);
				trav = trav.left;
			}
			if(!s.isEmpty())
				trav = s.pop();
		}
		System.out.println();
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
	public void inorderNonrec() {
		System.out.print("IN  : ");
		Stack<Node> s = new Stack<>();
		Node trav = root;
		while(trav != null || !s.isEmpty()) {
			while(trav != null) {
				s.push(trav);
				trav = trav.left;
			}
			if(!s.isEmpty()) {
				trav = s.pop();
				System.out.print(trav.data + ", ");
				trav = trav.right;
			}
		}
		System.out.println();		
	}
	public void inorder(Node trav) {
		if(trav == null)
			return;
		inorder(trav.left);
		System.out.print(trav.data + ", ");
		inorder(trav.right);		
	}
	public void inorder() {
		System.out.print("IN  : ");
		inorder(root);
		System.out.println();
	}
	public void postorderNonrec() {
		System.out.print("POST: ");
		Stack<Node> s = new Stack<>();
		Node trav = root;
		while(trav != null || !s.isEmpty()) {
			while(trav != null) {
				s.push(trav);
				trav = trav.left;
			}
			if(!s.isEmpty()) {
				trav = s.pop();
				if(trav.right == null || trav.right.visited) {
					System.out.print(trav.data + ", ");
					trav.visited = true;
					trav = null;
				}
				else {
					s.push(trav);
					trav = trav.right;
				}
			}
		}
		System.out.println();		
	}
	public void postorder(Node trav) {
		if(trav == null)
			return;
		postorder(trav.left);
		postorder(trav.right);				
		System.out.print(trav.data + ", ");
	}
	public void postorder() {
		System.out.print("POST: ");
		postorder(root);
		System.out.println();
	}
	
	/*
	public Node binSearch(Node trav, int key) {
		if(trav == null)
			return null;
		if(key == trav.data)
			return trav;
		if(key < trav.data)
			return binSearch(trav.left, key);
		else // if(key >= trav.data)
			return binSearch(trav.right, key);
	}
	public Node binSearch(int key) {
		return binSearch(root, key);
	}
	*/
	
	public Node binSearch(int key) {
		Node trav = root;
		while(trav != null) {
			if(key == trav.data)
				return trav;
			if(key < trav.data)
				trav = trav.left;
			else // if(key >= trav.data)
				trav = trav.right;
		}
		return null;
	}
	
	public Node[] binSearchWithParent(int key) {
		Node trav = root, parent = null;
		while(trav != null) {
			if(key == trav.data)
				return new Node[] {trav, parent};
			parent = trav;
			if(key < trav.data)
				trav = trav.left;
			else // if(key >= trav.data)
				trav = trav.right;
		}
		return new Node[] {null, null};
	}
}

public class BSTMain {
	public static void main(String[] args) {
		BinSearchTree t = new BinSearchTree();
		t.add(50);
		t.add(30);
		t.add(90);
		t.add(10);
		t.add(40);
		t.add(70);
		t.add(100);
		t.add(20);
		t.add(60);
		t.add(80);
		t.preorder();
		t.preorderNonrec();
		t.inorder();
		t.inorderNonrec();
		t.postorder();
		t.postorderNonrec();
		
//		Node temp = t.binSearch(80);
//		System.out.println("Found: " + temp);
		
		Node found[] = t.binSearchWithParent(50);
		System.out.println("Found: " + found[0]);
		System.out.println("Parent: " + found[1]);
	}
}







