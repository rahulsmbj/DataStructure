package sunbeam;

import java.util.Arrays;
import java.util.Stack;

public class StackQuestionsMain {
	public static void reverse(int[] arr) {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < arr.length; i++)
			s.push(arr[i]);
		int i = 0;
		while(!s.isEmpty()) {
			arr[i] = s.pop();
			i++;
		}
	}
	
	public static boolean isPalindrome(int[] arr) {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < arr.length; i++)
			s.push(arr[i]);
		int i = 0;
		while(!s.isEmpty()) {
			if(arr[i] != s.pop())
				return false;
			i++;
		}
		return true;
	}
	
	public static boolean isArrayPalindrome(int[] arr) {
		for(int i=0, j=arr.length-1; i < j; i++, j--) {
			if(arr[i] != arr[j])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {11, 22, 33, 44, 55};
		System.out.println(Arrays.toString(arr1));
		reverse(arr1);
		System.out.println(Arrays.toString(arr1));
	
		int[] arr2 = {11, 22, 33, 22, 11};
		System.out.println("Is palindrome: " + isPalindrome(arr2));
		System.out.println("Is palindrome: " + isArrayPalindrome(arr2));
	}
}
