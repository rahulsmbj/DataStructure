package sunbeam;

import java.util.Scanner;

class CircularQueue2 {
	private int[] arr;
	private int front, rear;
	private int count;
	public CircularQueue2(int size) {
		arr = new int[size];
		front = -1;
		rear = -1;
		count = 0;
	}
	public void push(int val) {
		if(isFull())
			throw new RuntimeException("Queue is Full.");
		rear = (rear + 1) % arr.length;
		arr[rear] = val;
		count++;
	}
	public void pop() {
		if(isEmpty())
			throw new RuntimeException("Queue is Empty.");
		front = (front + 1) % arr.length;
		count--;
	}
	public int peek() {
		if(isEmpty())
			throw new RuntimeException("Queue is Empty.");
		int index = (front + 1) % arr.length;
		return arr[index];
	}
	public boolean isEmpty() {
		return count == 0;
	}
	public boolean isFull() {
		return count == arr.length;
	}
}

public class CircularQueue2Main {
	public static void main(String[] args) {
		CircularQueue2 q = new CircularQueue2(6);
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
