package sunbeam;

import java.util.Scanner;
import java.util.Stack;

public class ExpressionMain {
	public static int pri(char op) {
		switch (op) {
		case '$':	return 8;
		case '*':	return 5;
		case '/':	return 5;
		case '+':	return 2;
		case '-':	return 2;
		}
		return 0;
	}
	public static String infixToPostfix(String in) {
		Stack<Character> s = new Stack<Character>();
		StringBuilder post = new StringBuilder();
		//1. traverse infix expr from left to right
		for(int i=0; i<in.length(); i++) {
			char sym = in.charAt(i);
			//2. if operand, append to postfix
			if(Character.isDigit(sym))
				post.append(sym);
			//5. if opening (, push it on stack
			else if(sym == '(')
				s.push(sym);
			//6. if closing ), then pop all ops from stack & append to postfix, until opening ( is found
			else if(sym == ')') {
				while(s.peek() != '(')
					post.append(s.pop());
				s.pop(); // also pop & discard (
			}
			//3. if operator, push on stack. but if pri(topmost op) >= pri(cur op), then pop and append to postfix expr
			else { // if operator
				while(!s.isEmpty() && pri(s.peek()) >= pri(sym))
					post.append(s.pop());
				s.push(sym);
			}
		}
		//4. when all syms from infix are done, append all ops from stack and append to postfix
		while(!s.isEmpty())
			post.append(s.pop());
		return post.toString();
	}
	
	public static String infixToPrefix(String in) {
		Stack<Character> s = new Stack<Character>();
		StringBuilder pre = new StringBuilder();
		//1. traverse infix expr from right to left
		for(int i=in.length()-1; i>=0; i--) {
			char sym = in.charAt(i);
			//2. if operand, append to prefix
			if(Character.isDigit(sym))
				pre.append(sym);
			//5. if closing ), push it on stack
			else if(sym == ')')
				s.push(sym);
			//6. if opening (, then pop all ops from stack & append to prefix, until closing ) is found
			else if(sym == '(') {
				while(s.peek() != ')')
					pre.append(s.pop());
				s.pop(); // also pop & discard )
			}
			//3. if operator, push on stack. but if pri(topmost op) > pri(cur op), then pop and append to prefix expr
			else { // if operator
				while(!s.isEmpty() && pri(s.peek()) > pri(sym))
					pre.append(s.pop());
				s.push(sym);
			}
		}
		//4. when all syms from infix are done, append all ops from stack and append to prefix
		while(!s.isEmpty())
			pre.append(s.pop());
		//7. reverse the pre expr and return it 
		return pre.reverse().toString();
	}
	
	public static int calc(int a, int b, char op) {
		switch (op) {
		case '$':	return (int)Math.pow(a, b);
		case '/':	return a / b;
		case '*':	return a * b;
		case '+':	return a + b;
		case '-':	return a - b;
		}
		return 0;
	}
	
	public static int postfixEvaluate(String post) {
		Stack<Integer> s = new Stack<Integer>();
		//1. traverse postfix from left to right
		for(int i=0; i<post.length(); i++) {
			char sym = post.charAt(i); // '4'
			//2. if operand, push it on stack
			if(Character.isDigit(sym))
				s.push(Integer.parseInt(Character.toString(sym))); // 4
			//3. if operator, pop two operand from stack, calc result and push it on stack.
			else {
				int b = s.pop();
				int a = s.pop();
				int res = calc(a, b, sym);
				s.push(res);
			}
		}
		//4. pop final result from stack & return.
		return s.pop();
	}

	public static int prefixEvaluate(String pre) {
		Stack<Integer> s = new Stack<Integer>();
		//1. traverse prefix from right to left
		for(int i=pre.length()-1; i>=0; i--) {
			char sym = pre.charAt(i); 
			//2. if operand, push it on stack
			if(Character.isDigit(sym))
				s.push(Integer.parseInt(Character.toString(sym))); 
			//3. if operator, pop two operand from stack, calc result and push it on stack.
			else {
				int a = s.pop();
				int b = s.pop();
				int res = calc(a, b, sym);
				s.push(res);
			}
		}
		//4. pop final result from stack & return.
		return s.pop();
	}
	
	public static void main(String[] args) {
		/*
		String infix = "5+9-4*(8-6/2)+1$(7-3)";
		String postfix = "", prefix = "";
		int result;
		System.out.println("Infix:   " + infix);
		postfix = infixToPostfix(infix);
		System.out.println("Postfix: " + postfix);
		result = postfixEvaluate(postfix);
		System.out.println("Result: " + result);
		prefix = infixToPrefix(infix);
		System.out.println("Prefix:  " + prefix);
		result = prefixEvaluate(prefix);
		System.out.println("Result: " + result);
		*/
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter infix expr: ");
		String infix = sc.nextLine();
		String postfix = infixToPostfix(infix);
		int result = postfixEvaluate(postfix);
		System.out.println("Result: " + result);
		sc.close();
	}
}






