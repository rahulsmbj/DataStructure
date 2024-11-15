package sunbeam;

import java.util.Scanner;

public class RecursionMain {
	public static int binarySearch(int[] arr, int left, int right, int key) {
		// if invalid part, ele not found (return -1)
		if(left > right)
			return -1;
		// find mid of current partition (left to right)
		int index, mid = (left + right) / 2;
		// if key == mid ele, return mid
		if(key == arr[mid])
			return mid;
		// if key < mid ele, search in left partition (left to mid-1)
		if(key < arr[mid])
			index = binarySearch(arr, left, mid-1, key);
		// else, search in right partition (mid+1 to right)
		else
			index = binarySearch(arr, mid+1, right, key);
		return index;
	}
	public static void main(String[] args) {
		int[] arr = {11, 22, 33, 44, 55, 66, 77, 88, 99};
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter element to find: ");
		int key = sc.nextInt();
		int index = binarySearch(arr, 0, arr.length-1, key);
		if(index == -1)
			System.out.println("Element not found.");
		else
			System.out.println("Element found at " + index);
		sc.close();
	}
}
