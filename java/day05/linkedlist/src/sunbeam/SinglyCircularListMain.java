package sunbeam;

import java.util.Scanner;

class SinglyCircularList {
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
	private Node head;
	
	//private int count;
		// initially -- constructor --> count = 0;
		// each add operation, count++;
		// each del operation, count--;
	
	public SinglyCircularList() {
		head = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void addLast(int val) { // O(n)
		// create and init new node
		Node newNode = new Node(val);
		// if list is empty, make this node as first node
		if(isEmpty()) {
			head = newNode;
			newNode.next = head; // make the node as circular
		}
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != head)
				trav = trav.next;
			// newnode's next to head
			newNode.next = head;
			// last node's next to newnode
			trav.next = newNode;
		}
	}
	
	public void addFirst(int val) { // O(n)
		// create and init new node
		Node newNode = new Node(val);
		// if list is empty, make this node as first node
		if(isEmpty()) {
			head = newNode;
			newNode.next = head; // make the node as circular
		}
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != head)
				trav = trav.next;
			// newnode's next to head
			newNode.next = head;
			// last node's next to newnode
			trav.next = newNode;
			// head to newnode
			head = newNode;
		}
	}
	
	public void display() { // O(n) 
		System.out.print("List: ");
		if(!isEmpty()) {
			Node trav = head;
			do {
				System.out.print(trav.data + ", ");
				trav = trav.next;
			} while(trav != head);
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
			if(trav.next == head)
				break;
			trav = trav.next;
		}
		// add node after pos-1
		newNode.next = trav.next;
		trav.next = newNode;
	}
	
	void delFirst() {	// O(n)
		// if list empty, do nothing.
		if(isEmpty())
			return;
		// if list have single node, then head = null (node will be gc).
		if(head.next == head) {
			head = null;
			return;
		}
		// traverse till last node
		Node trav = head;
		while(trav.next != head)
			trav = trav.next;
		// take head to next node
		head = head.next;
		// trav's next to (new) head
		trav.next = head;
		// old first node will be gc.
	}
	
	void del(int pos) {
		// if empty list or del at first pos, call delFirst()
		if(isEmpty() || pos==1) {
			delFirst();
			return;
		}
		// traverse till pos and also maintain prev pointer
		Node trav = head, prev = null;
		for(int i=1; i<pos; i++) {
			prev = trav;
			trav = trav.next;
			// if pos is beyond max, do nothing
			if(trav == head)
				return;
		}
		// prev's next to trav's next
		prev.next = trav.next;
		// trav node will be g.c.
	}
	
	void delLast() { // O(n)
		// home work
	}
	
	void delAll() { // clear() // O(1) 		
		head = null;
		// all nodes will be garbage collected.
	}
}

public class SinglyCircularListMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SinglyCircularList l = new SinglyCircularList();
		int choice, val, pos;
		do {
			System.out.print("\n1. display\n2. add first\n3. add last\n4. insert\n5. del first\n6. del last\n7. del all\nenter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // display
				l.display();
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
			case 7: // del all
				l.delAll();
				break;
			}
		}while(choice != 0);
		sc.close();
	}
}

