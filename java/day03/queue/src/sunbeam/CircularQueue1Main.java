package sunbeam;

import java.util.Scanner;

class CircularQueue1 {
	private int[] arr;
	private int front, rear;

	public CircularQueue1(int size) {
		arr = new int[size];
		front = -1;
		rear = -1;
	}
	public void push(int val) {
		if(isFull())
			throw new RuntimeException("Queue is Full.");
		rear = (rear + 1) % arr.length;
		arr[rear] = val;
	}
	public void pop() {
		if(isEmpty())
			throw new RuntimeException("Queue is Empty.");
		front = (front + 1) % arr.length;
		if(front == rear) {
			front = -1;
			rear = -1;
		}
	}
	public int peek() {
		if(isEmpty())
			throw new RuntimeException("Queue is Empty.");
		int index = (front + 1) % arr.length;
		return arr[index];
	}
	public boolean isEmpty() {
		return front == rear && front == -1;
	}
	public boolean isFull() {
		return (front == -1 && rear == arr.length-1) || (front == rear && front != -1);
	}
}

public class CircularQueue1Main {
	public static void main(String[] args) {
		CircularQueue1 q = new CircularQueue1(6);
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
	}
}
