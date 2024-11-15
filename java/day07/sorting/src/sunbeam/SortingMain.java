package sunbeam;

import java.util.Arrays;

public class SortingMain {
	
	public static void mergeSort(int[] arr, int left, int right) {
		// if part have single element or invalid partition, then return.
		if(left == right || left > right)
			return;
		// divide array in two equal parts -- left to mid, mid+1 to right
		int mid = (left + right) / 2;
		// sort both partitions independently
		mergeSort(arr, left, mid);
		mergeSort(arr, mid+1, right);
		// ** merging of two sorted partition
		// create temp array to accomodate both partitions
		int[] temp = new int[right - left + 1];
		// take indices to start of both parts (i & j) and temp array (k)
		int i = left, j = mid+1, k = 0;
		// compare elements from both parts and copy smaller element to temp array
		// increment respective indices
		while(i <= mid && j <= right) {
			if(arr[i] < arr[j]) {
				temp[k] = arr[i];
				i++;
				k++;
			} else {
				temp[k] = arr[j];
				j++;
				k++;			
			}
		} // repeat until any one partition is completed.
		// if any one partition is completed, then copy elements from another partition into temp array
		while(i <= mid) {
			temp[k] = arr[i];
			i++;
			k++;
		}
		while(j <= right){
			temp[k] = arr[j];
			j++;
			k++;			
		}
		// copy (overwrite) temp array back to main array
		for(k=0; k<temp.length; k++)
			arr[left+k] = temp[k]; 
	}

	/*
	public static void mergeSort(int[] arr, int left, int right) {
		if(left >= right)
			return;
		int mid = (left + right) / 2;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid+1, right);
		int[] temp = new int[right - left + 1];
		int i = left, j = mid+1, k = 0;
		while(i <= mid && j <= right)
			temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
		while(i <= mid)
			temp[k++] = arr[i++];
		while(j <= right)
			temp[k++] = arr[j++];
		for(k=0; k<temp.length; k++)
			arr[left+k] = temp[k];
	}
	*/
	
	public static void quickSort(int[] arr, int left, int right) {
		// if part have single element or invalid partition, then return.
		if(left == right || left > right)
			return;
		// consider left most element as pivot.
		// take indices from left & right of partition i.e. i & j
		int i = left, j = right;
		while(i < j) {
			// from left, find element > pivot
			while(i <= right && arr[i] <= arr[left])
				i++;
			// from right, find element <= pivot
			while(arr[j] > arr[left])
				j--;
			// if i & j are not crossed, swap ith element with jth element
			if(i < j) {
				int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
			}
		} // repeat until i & j are crossed
		// swap pivot element with jth element
		int temp = arr[left]; arr[left] = arr[j]; arr[j] = temp;
		// apply quick sort on elements before and after pivot.
		quickSort(arr, left, j-1);
		quickSort(arr, j+1, right);
	}

	public static void main(String[] args) {
		int[] arr = {4, 7, 9, 2, 8, 1, 6, 3, 5};
		//int[] arr = {4, 2, 3, 1};
		System.out.println(Arrays.toString(arr));
		//mergeSort(arr, 0, arr.length-1);
		quickSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}







