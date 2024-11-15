package sunbeam;

import java.util.Stack;

public class ExpressionNotationMain {
	public static int calc(int a, int b, char op) {
		switch (op) {
			case '$': return (int)Math.pow(a, b);
			case '*': return a * b;
			case '/': return a / b;
			case '%': return a % b;
			case '+': return a + b;
			case '-': return a - b;
		}
		return 0;
	}
	public static int postfixEvaluation(String postfix) {
		Stack<Integer> s = new Stack<Integer>();
		//1. access symbols from postfix from left to right.
		for (int i = 0; i < postfix.length(); i++) {
			char sym = postfix.charAt(i);
			//2. if symbol is operand, push it on the stack.
			if(Character.isDigit(sym))
				s.push(Character.getNumericValue(sym));
			//3. if symbol is operator, pop two operands from stack, calculate result & push on stack.
			else {
				int b = s.pop();
				int a = s.pop();
				int res = calc(a, b, sym);
				s.push(res);
			}
		} //4. repeat until all symbols from postfix are finished.
		//5. pop the final result from stack and return it.
		return s.pop();
	}
	public static int prefixEvaluation(String prefix) {
		Stack<Integer> s = new Stack<Integer>();
		//1. access symbols from prefix from right to left.
		for (int i = prefix.length()-1; i >= 0; i--) {
			char sym = prefix.charAt(i);
			//2. if symbol is operand, push it on the stack.
			if(Character.isDigit(sym))
				s.push(Character.getNumericValue(sym));
			//3. if symbol is operator, pop two operands from stack, calculate result & push on stack.
			else {
				int a = s.pop();
				int b = s.pop();
				int res = calc(a, b, sym);
				s.push(res);
			}
		} //4. repeat until all symbols from prefix are finished.
		//5. pop the final result from stack and return it.
		return s.pop();
	}
	
	public static String postfixToInfix(String postfix) {
		Stack<String> s = new Stack<String>();
		for (int i = 0; i < postfix.length(); i++) {
			char sym = postfix.charAt(i);
			if(Character.isDigit(sym))
				s.push(""+sym);
			else {
				String b = s.pop();
				String a = s.pop();
				s.push("(" + a + sym + b + ")");
			}
		}
		return s.pop();
	}
	
	public static String prefixToPostfix(String prefix) {
		Stack<String> s = new Stack<String>();
		for (int i = prefix.length()-1; i >= 0; i--) {
			char sym = prefix.charAt(i);
			if(Character.isDigit(sym))
				s.push(""+sym);
			else {
				String a = s.pop();
				String b = s.pop();
				s.push(a + b + sym);
			}
		}
		return s.pop();
	}
	public static void main(String[] args) {
		String postfix = "59+4862/-*-173-$+";
		int res = postfixEvaluation(postfix);
		System.out.println("Postfix  Result: " + res);
		String prefix = "+-+59*4-8/62$1-73";
		res = prefixEvaluation(prefix);
		System.out.println("Prefix Result: " + res);
		String infix = postfixToInfix(postfix);
		System.out.println("Infix Expr: " + infix);
		String convertedPostfix = prefixToPostfix(prefix);
		System.out.println("Postfix Expr: " + convertedPostfix);
	}
}

//Infix = "5+9-4*(8-6/2)+1$(7-3)";
//Postfix = 59+4862/-*-173-$+
//Prefix = +-+59*4-8/62$1-73
