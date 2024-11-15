package sunbeam;

import java.util.Scanner;

public class TimeComplexityMain {
	public static int factorial(int n) {
		int res = 1;
		for(int i=1; i<=n; i++)
			res = res * i;
		return res;
	}
	public static void printMatrix(int[][] mat) {
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++)
				System.out.print(mat[i][j] + " ");
			System.out.println("");
		}
	}
	
	public static void decimalToBinary(int num) {
		while(num > 0) {
			System.out.print(num % 2);
			num = num / 2;
		}
		System.out.println();
	}
	
	public static void printTable(int n) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(n * i);
		}
	}
	
	public static int linearSearch(int[] a, int key) {
		for (int i = 0; i < a.length; i++) {
			if(a[i] == key)
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
//		int result;
//		result = factorial(5);
//		System.out.println("5! = " + result);
		int[][] mat = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
		printMatrix(mat);		
//		decimalToBinary(10);
		
//		printTable(28);
		
		// Scanner sc = new Scanner(System.in);
		// int[] arr = { 88, 33, 66, 99, 11, 77, 22, 55, 11};
		// System.out.print("Enter number to be searched: ");
		// int num = sc.nextInt();
		// int index = linearSearch(arr, num);
		// if(index == -1)
		// 	System.out.println("Element is not found.");
		// else
		// 	System.out.println("Element found at " + index);
		//sc.close();
	}
}


	