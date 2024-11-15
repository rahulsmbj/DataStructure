package sunbeam;

import java.util.LinkedList;
import java.util.Queue;

class StackUsingQ {
	private Queue<Integer> que;
	public StackUsingQ() {
		que = new LinkedList<Integer>();
	}
	public void push(int val) { // O(n)
		Queue<Integer> tempq = new LinkedList<Integer>();
		while(!que.isEmpty())
			tempq.offer(que.poll());
		que.offer(val);
		while(!tempq.isEmpty())
			que.offer(tempq.poll());		
	}
	public int pop() { // O(1)
		if(isEmpty())
			throw new RuntimeException("Stack is Empty");
		return que.poll();
	}
	public int peek() { // O(1)
		if(isEmpty())
			throw new RuntimeException("Stack is Empty");
		return que.peek();
	}
	public boolean isEmpty() { // O(1)
		return que.isEmpty();
	}
}

public class StackUsingQueue {
	public static void main(String[] args) {
		StackUsingQ s = new StackUsingQ();
		s.push(11);
		s.push(22);
		s.push(33);
		s.push(44);
		while(!s.isEmpty())
			System.out.println(s.pop());
	}
}	
