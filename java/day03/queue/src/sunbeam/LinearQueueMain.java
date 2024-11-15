package sunbeam;

import java.util.Scanner;

class LinearQueue {
	private int[] arr;
	private int front, rear;

	public LinearQueue(int size) {
		arr = new int[size];
		front = -1;
		rear = -1;
	}
	public void push(int val) {
		if(isFull())
			throw new RuntimeException("Queue is Full.");
		rear++;
		arr[rear] = val;
	}
	public void pop() {
		if(isEmpty())
			throw new RuntimeException("Queue is Empty.");
		front++;
	}
	public int peek() {
		if(isEmpty())
			throw new RuntimeException("Queue is Empty.");
		int val = arr[front + 1];
		return val;
	}
	public boolean isEmpty() {
		return front == rear;
	}
	public boolean isFull() {
		return rear == arr.length-1;
	}
}

public class LinearQueueMain {
	public static void main(String[] args) {
		LinearQueue q = new LinearQueue(6);
		Scanner sc = new Scanner(System.in);
		int choice, val;
		do {
			System.out.print("\n1. Push\n2. Pop\n3. Peek\nEnter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // push
				try {
					System.out.print("Enter value to push: ");
					val = sc.nextInt();
					q.push(val);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2: // pop
				try {
					System.out.println("Value popped: " + q.peek());
					q.pop();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3: // peek
				try {
					val = q.peek();
					System.out.println("Value peeked: " + val);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		}while(choice != 0);
		sc.close();
		/*
		LinearQueue q = new LinearQueue(6);
		q.push(11);
		q.push(22);
		q.push(33);
		q.push(44);
		q.push(55);
		q.push(66);

		System.out.println("Queue Full: " + q.isFull());

		System.out.println("Popped: " + q.peek());
		q.pop();
		System.out.println("Popped: " + q.peek());
		q.pop();
		System.out.println("Popped: " + q.peek());
		q.pop();
		System.out.println("Popped: " + q.peek());
		q.pop();
		System.out.println("Popped: " + q.peek());
		q.pop();
		System.out.println("Popped: " + q.peek());
		q.pop();

		System.out.println("Queue Empty: " + q.isEmpty());
		 */
	}
}
