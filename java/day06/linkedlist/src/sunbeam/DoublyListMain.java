package sunbeam;

import java.util.Scanner;


class DoublyList {
	// Node is static member class of List
	static class Node {
		private int data;
		private Node next;
		private Node prev;
		public Node() {
			data = 0;
			next = null;
			prev = null;
		}
		public Node(int val) {
			data = val;
			next = null;
			prev = null;
		}
	}
	
	// head is address of first node of the list.
	private Node head;
	
	public DoublyList() {
		head = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void addLast(int val) { // O(n)
		// create and init new node
		Node newNode = new Node(val);
		// if list is empty, make this node as first node
		if(isEmpty())
			head = newNode;
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			// last node's next to newnode
			trav.next = newNode;
			// newnode's prev to last node
			newNode.prev = trav;
		}
	}
	
	public void addFirst(int val) { // O(1)
		// create and init new node
		Node newNode = new Node(val);
		if(isEmpty())
			head = newNode;
		else {
			// newnode's next to first node (head)
			newNode.next = head;
			// head's prev to newnode
			head.prev = newNode;
			// head to newnode.
			head = newNode;
		}
	}
	
	public void displayForward() { // O(n) 
		System.out.print("Fwd List: ");
		Node trav = head;
		while(trav != null) {
			System.out.print(trav.data + ", ");
			trav = trav.next;
		}
		System.out.println();
	}
	
	public void displayReverse() { // O(n) 
		System.out.print("Rev List: ");
		if(!isEmpty()) {
			// traverse till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			// traverse back to first node & display each node
			while(trav != null) {
				System.out.print(trav.data + ", ");
				trav = trav.prev;
			}
		}
		System.out.println();
	}

	public void insert(int val, int pos) { // O(n)
		// if list empty, first pos or before 1st pos (invalid)
		if(isEmpty() || pos <= 1) {
			addFirst(val);
			return;
		}
		// create new node and init it
		Node newNode = new Node(val);
		// trav till pos-1
		Node trav = head;
		for(int i=1; i < pos-1; i++) {
			// if pos is beyond max, then stop on last node
			if(trav.next == null)
				break;
			trav = trav.next;
		}
		// get addr of next node
		Node temp = trav.next;
		// setup links of newnode
		newNode.next = temp;
		newNode.prev = trav;
		// setup next of trav
		trav.next = newNode;
		// setup prev of temp
		if(temp != null)
			temp.prev = newNode;
	}
	
	/*
	void delFirst() {	// O(1)
		if(!isEmpty()) {
			// take head to next node
			head = head.next;
			// new head's prev should be null
			if(head != null) // handle special case of single node in list.
				head.prev = null;
			// old head (first node) will be garbage collected.
		}
	}
	*/
	
	void delFirst() {	// O(1)
		// if list is empty, do nothing
		if(isEmpty())
			return;
		// if single node, make head null
		if(head.next == null) {
			head = null;
			return;
		}
		// if list have multiple nodes
		head = head.next;
		head.prev = null;
		// the old first node will be garbage collected.
	}
	
	void delLast() { // O(n)
		// if list is empty or have single node, then del first
		if(isEmpty() || head.next == null) {
			delFirst();
			return;
		}
		
		Node trav = head;
		// traverse till last node
		while(trav.next != null)
			trav = trav.next;
		// get addr of prev node
		Node prev = trav.prev;
		// prev next to null
		prev.next = null;
		// trav will be garbage collected.
	}
	
	void del(int pos) { // O(n)
		// if empty list or del at first pos, call delFirst()
		if(isEmpty() || pos==1) {
			delFirst();
			return;
		}
		// if pos < 1, do nothing
		if(pos < 1)
			return;
		// traverse till pos
		Node trav = head;
		for(int i=1; i<pos; i++) {
			trav = trav.next;
			// if pos is beyond max, do nothing
			if(trav == null)
				return;
		}
		// manage links of prev & next nodes
		trav.prev.next = trav.next;
		if(trav.next != null)
			trav.next.prev = trav.prev; // skip this if last pos
		// trav node will be g.c.
	}
	
	void delAll() { // clear() // O(1) 
		head = null;
		// all nodes will be garbage collected.
	}
}

public class DoublyListMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DoublyList l = new DoublyList();
		int choice, val, pos;
		do {
			System.out.print("\n1. display\n2. add first\n3. add last\n4. insert\n5. del first\n6. del last\n7. delete\n8. del all\nenter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // display
				l.displayForward();
				l.displayReverse();
				break;
			case 2: // add first
				System.out.print("enter value: ");
				val = sc.nextInt();
				l.addFirst(val);
				break;
			case 3: // add last
				System.out.print("enter value: ");
				val = sc.nextInt();
				l.addLast(val);
				break;
			case 4: // insert
				System.out.print("enter value: ");
				val = sc.nextInt();
				System.out.print("enter position (1-based): ");
				pos = sc.nextInt();				
				l.insert(val, pos);
				break;
			case 5: // del first
				l.delFirst();
				break;
			case 6: // del last
				l.delLast();
				break;
			case 7: // del last
				System.out.print("enter position (1-based): ");
				pos = sc.nextInt();				
				l.del(pos);
				break;
			case 8: // del all
				l.delAll();
				break;
			}
		}while(choice != 0);
		sc.close();
	}
}

