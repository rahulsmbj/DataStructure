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
	
	public Node bfs(int key) {
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			Node trav = q.poll();
			//System.out.println(trav.data + ", ");
			if(key == trav.data)
				return trav;
			if(trav.left != null)
				q.offer(trav.left);
			if(trav.right!= null)
				q.offer(trav.right);
		}
		return null;
	}
	
	public Node dfs(int key) {
		Stack<Node> s = new Stack<>();
		s.push(root);
		while(!s.isEmpty()) {
			Node trav = s.pop();
			//System.out.println(trav.data + ", ");
			if(key == trav.data)
				return trav;
			if(trav.right!= null)
				s.push(trav.right);
			if(trav.left != null)
				s.push(trav.left);
		}
		return null;
	}
	
	// O(height) -- max num of iterations = height of tree.
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
	
	void del(int key) {
		// find node to be deleted along with its parent
		Node[] arr = binSearchWithParent(key);
		Node trav = arr[0], parent = arr[1];
		if(trav == null) {
			System.out.println("Node not found.");
			return;
		}
		// if node has left & right child
		if(trav.left != null && trav.right != null) {
			// find succ with its parent
			parent = trav;
			Node succ = trav.right;
			while(succ.left != null) {
				parent = succ;
				succ = succ.left;
			}
			// replace trav data with succ data
			trav.data = succ.data;
			// mark succ node to be deleted
			trav = succ;
		}
		// if node do not have left child
		if(trav.left == null) {
			// do not loose trav.right, put it appripriate place
			if(trav == root)
				root = trav.right;
			else if(trav == parent.left)
				parent.left = trav.right;
			else
				parent.right = trav.right;
		}
		// else if not do not have right child
		else if(trav.right == null) {
			// do not loose trav.left, put it appripriate place
			if(trav == root)
				root = trav.left;
			else if(trav == parent.left)
				parent.left = trav.left;
			else
				parent.right = trav.left;			
		}
		// trav node will be garbage collected.
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
		t.inorder();
		t.postorder();
		
		Node found[] = t.binSearchWithParent(50);
		System.out.println("Found: " + found[0]);
		System.out.println("Parent: " + found[1]);
	
		Node temp1 = t.bfs(60);
		System.out.println("BFS Found: " + temp1);
		Node temp2 = t.dfs(60);
		System.out.println("DFS Found: " + temp2);
		
		t.inorder();
		t.del(80);
		t.inorder();
	}
}







