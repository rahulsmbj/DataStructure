package day06_linkedlist;

class DoublyCircularList {
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
	
	private Node head;
	private int count;
	
	public DoublyCircularList() {
		head = null;
		count = 0;
	}
	
	public int getCount() {
		return count;
	}
	
	public void display() {
		System.out.print("Fwd List: ");
		Node trav = head;
		if(head != null) {
			do {
				System.out.print(trav.data + ", ");
				trav = trav.next;
			}while(trav != head);
		}
		System.out.println();
		
		System.out.print("Rev List: ");
		if(head != null) {
			trav = head.prev;
			do {
				System.out.print(trav.data + ", ");
				trav = trav.prev;
			}while(trav != head.prev);
		}
		System.out.println();
		
	}
	
	public void addLast(int val) {
		// create new node
		Node newNode = new Node(val);
		// spl: if list is empty, add node at the start.
		if(head == null) {
			head = newNode;
			newNode.next = head;
			newNode.prev = head;
		} else {
			// mark last node as trav.
			Node trav = head.prev;
			// add the node in between trav and head.
			newNode.next = head;
			newNode.prev = trav;
			trav.next = newNode;
			head.prev = newNode;
		}
		count++;
	}
	
	
}

public class DoublyCircularListMain {
	public static void main(String[] args) {
		DoublyCircularList obj = new DoublyCircularList();
		obj.addLast(11);
		obj.addLast(22);
		obj.addLast(33);
		System.out.println("Count = " + obj.getCount());
		obj.display();
	}
}
