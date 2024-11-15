package sunbeam;

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
		}
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
	
	public void selectionSort() {
		for (Node i = head; i !=null; i = i.next) {
			for (Node j = i.next; j !=null; j = j.next) {
				if(i.data > j.data) {
					int temp = i.data;
					i.data = j.data;
					j.data = temp;
				}
			}
		}
	}
	
	public int mid() {
		Node slow = head, fast = head;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow.data;
	}
	
	public void revDisplay(Node cur) {
		if(cur == null)
			return;
		revDisplay(cur.next);
		System.out.print(cur.data + ", ");
	}
	public void revDisplay() {
		System.out.print("Rev: ");
		revDisplay(head);
		System.out.println();
	}
	
	public void reverse() {
		Node oldhead = head;
		head = null;
		while(oldhead != null) {
			Node temp = oldhead;
			oldhead = oldhead.next;
			temp.next = head;
			head = temp;
		}
	}
	
	/*
	public Node recReverse(Node cur) {
		if(cur.next == null) {
			head = cur;
			return cur;
		}
		Node last = recReverse(cur.next);
		last.next = cur;
		cur.next = null;
		return cur;
	}
	*/
	public Node recReverse(Node cur) {
		if(cur.next == null) {
			head = cur;
			return cur;
		}
		recReverse(cur.next).next = cur;
		cur.next = null;
		return cur;
	}
	public void recReverse() {
		if(!isEmpty())
			recReverse(head);
	}
}

public class InterviewMain1 {
	public static void main(String[] args) {
		SinglyList l = new SinglyList();
		l.addLast(3);
		l.addLast(1);
		l.addLast(5);
		l.addLast(6);
		l.addLast(2);
		l.addLast(7);
		l.addLast(4);
		//l.display();
		l.selectionSort();
		l.display();
		
		//int middle = l.mid();
		//System.out.println("Middle: " + middle);
		
		//l.revDisplay();
	
		//l.reverse();
		l.recReverse();
		l.display();
	}
}
