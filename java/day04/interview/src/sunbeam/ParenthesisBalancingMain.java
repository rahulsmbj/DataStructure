package sunbeam;

import java.util.Stack;

public class ParenthesisBalancingMain {
	public static boolean isParenthisisBalanced(String expr) {
		Stack<Character> s = new Stack<Character>();
		String opening = "([{";
		String closing = ")]}";
		// traverse expr from left to right
		for (int i = 0; i < expr.length(); i++) {
			char sym = expr.charAt(i);
			// if sym is opening parenthesis, push on stack
			int openIndex = opening.indexOf(sym);
			if(openIndex != -1)
				s.push(sym);
			else {
				// if sym is closing parenthesis, pop one sym from stack
				int closeIndex = closing.indexOf(sym);
				if(closeIndex != -1) {
					// if stack is empty, return error
					if(s.isEmpty())
						return false;
					// check if opening (popped) & closing (current) are matching, if not return error.
					openIndex = opening.indexOf(s.pop());
					if(openIndex != closeIndex)
						return false;
				}
			}
		}
		// if stack is not empty, return error.
		if(!s.isEmpty())
			return false;
		return true;
	}
	public static void main(String[] args) {
		String expr = "5+([9-4]*(8-{6/2}))";
		if(isParenthisisBalanced(expr))
			System.out.println("Parenethesis balanced.");
		else
			System.out.println("Parenethesis NOT balanced.");
	}
}
