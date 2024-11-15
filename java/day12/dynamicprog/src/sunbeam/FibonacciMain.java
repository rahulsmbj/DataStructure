package sunbeam;

public class FibonacciMain {
	private static int count = 0;
	
	public static int recFib(int n) {
		count++;
		if(n == 1 || n == 2)
			return 1;
		int result = recFib(n-1) + recFib(n-2);
		return result;
	}
	
	public static int memFib(int n, int[] terms) {
		count++;
		if(n == 1 || n == 2)
			return (terms[n] = 1);
		if(terms[n] != 0)
			return terms[n];
		terms[n] = memFib(n-1, terms) + memFib(n-2, terms);
		return terms[n];
	}
	
	public static int memFib(int n) {
		int[] terms = new int[n+1];
		return memFib(n, terms);
	}
	
	public static int dpFib(int n) {
		count++;
		// to save state (like memoization)
		int[] terms = new int[n+1];
		// from base condition of recursion
		terms[1] = terms[2] = 1;
		// calculate terms similar to recursive formula
		for(int i=3; i<=n; i++)
			terms[i] = terms[i-1] + terms[i-2];
		// return result
		return terms[n];
	}
	
	public static void main(String[] args) {
		int n = 30, term;
		count = 0;
		term = recFib(n);
		System.out.println("term = " + term + " with fn calls: " + count);

		count = 0;
		term = memFib(n);
		System.out.println("term = " + term + " with fn calls: " + count);

		count = 0;
		term = dpFib(n);
		System.out.println("term = " + term + " with fn calls: " + count);
	}
}
