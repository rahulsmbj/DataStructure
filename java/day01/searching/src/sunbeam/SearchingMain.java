package sunbeam;

import java.util.Scanner;

public class SearchingMain {
	public static int linearSearch(int[] arr, int key) {
		for(int i=0; i<arr.length; i++) {
			if(key == arr[i])
				return i;
		}
		return -1;
	}
	public static int binarySearch(int[] arr, int key) {
		int left = 0, right = arr.length-1, mid;
		while(left <= right) {	
			mid = (left + right) / 2;
			if(key == arr[mid])
				return mid;
			if(key < arr[mid])
				right = mid - 1;
			else //(key > arr[mid])
				left = mid + 1;
		}
		return -1;
	}
	public static void main(String[] args) {
		//int[] arr = {88, 33, 66, 99, 11, 77, 22, 55, 11};
		int[] arr = {11, 22, 33, 44, 55, 66, 77, 88, 99};
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter element to be searched: ");
		int key = sc.nextInt();
		int index = linearSearch(arr, key);
		if(index == -1)
			System.out.println("Element not found.");
		else
			System.out.println("Element found at index " + index);
		sc.close();
	}
}
