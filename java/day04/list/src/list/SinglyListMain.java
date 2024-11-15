package list;

class SinglyList {
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
	
	public SinglyList() {
		head = null;
	}
	
	public void addLast(int val) { // O(n)
		// create and init new node
		Node newNode = new Node(val);
		// if list is empty, make this node as first node
		if(head == null)
			head = newNode;
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			// last node's next to newnode
			trav.next = newNode;
		}
	}
	
	public void addFirst(int val) { // O(1)
		// create and init new node
		Node newNode = new Node(val);
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
}

public class SinglyListMain {
	public static void main(String[] args) {
		SinglyList l = new SinglyList();
		l.addFirst(5);
		l.addLast(11);
		l.addLast(22);
		l.addLast(33);
		l.addLast(44);
		l.addLast(55);
		//l.addFirst(10);
		l.display();
	}
}

