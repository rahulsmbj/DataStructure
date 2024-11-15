package sunbeam;

import java.util.Scanner;

// Stack using Linked List

class SinglyListHeadTail {
	// Node is static member class of List
	static class Node {
		private int data;
		private Node next;
		public Node() {
			data = 0;
			next = null;
		}
		public Node(int val) {
			data = val;
			next = null;
		}
	}
	
	// head is address of first node of the list.
	// tail is address of last node of the list.
	private Node head, tail;
	
	public SinglyListHeadTail() {
		head = null;
		tail = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	// use this to implement queue
	public void addLast(int val) { // O(1)
		// create and init new node
		Node newNode = new Node(val);
		// if list is empty, make this node as first & last node
		if(isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else {
			// last node's next to newnode
			tail.next = newNode;
			// update tail to newnode
			tail = newNode;
		}
	}
	
	// add first
	public void push(int val) { // O(1)
		// create and init new node
		Node newNode = new Node(val);
		// if list is empty, the first node itself is last node.
		if(isEmpty())
			tail = newNode;
		// newnode's next to first node (head)
		newNode.next = head;
		// head to newnode.
		head = newNode;
	}
	
	public void display() { // O(n) 
		System.out.print("List: ");
		Node trav = head;
		while(trav != null) {
			System.out.print(trav.data + ", ");
			trav = trav.next;
		}
		System.out.println();
	}
	
	// del first
	int pop() {	// O(1)
		if(isEmpty())
			throw new RuntimeException("List is empty");
		// take head to next node
		int val = head.data;
		head = head.next;
		if(isEmpty()) // if node deleted was last node.
			tail = null;
		return val;
	}
	
	int peek() {	// O(1)
		if(isEmpty())
			throw new RuntimeException("List is empty");
		return head.data;
	}
	
	void delAll() { // clear() // O(1) 		
		head = null;
		tail = null;
		// all nodes will be garbage collected.
	}
}

public class SinglyListWithHeadTailMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SinglyListHeadTail l = new SinglyListHeadTail();
		int choice, val, pos;
		do {
			System.out.print("\n1. display\n2. add first (push)\n3. del first (pop)\nenter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // display
				l.display();
				break;
			case 2: // add first
				System.out.print("enter value: ");
				val = sc.nextInt();
				l.push(val);
				break;
			case 3: // del first
				val = l.pop();
				System.out.println("Popped: " + val);
				break;
			}
		}while(choice != 0);
		sc.close();
	}
}

