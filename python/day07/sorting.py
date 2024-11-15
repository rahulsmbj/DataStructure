
def quickSort(a, left, right):
	# 0. if single element or invalid partition, stop.
	if left >= right:
		return
	# 1. consider left-most element as pivot
	i = left
	j = right
	pivot = a[left]
	while i < j:
		# 2. from left find element greater than pivot - ith element
		while i <= right and a[i] <= pivot:
			i = i + 1
		# 3. from right find element less or equal to pivot - jth element
		while j >= left and a[j] > pivot:
			j = j - 1
		# 4. if i & j not crossed, then swap ith element with jth element
		if i < j :
			temp = a[i]
			a[i] = a[j]
			a[j] = temp
		# 5. repeat steps 2-4 until, i & j cross each other
	#  6. swap jth element with pivot=a[left]
	temp = a[left]
	a[left] = a[j]
	a[j] = temp
	#  7. apply quick sort to the left partition
	quickSort(a, left, j - 1)
	#  8. apply quick sort to the right partition
	quickSort(a, j + 1, right)


def mergeSort(a, left, right):
	# 0. if single element in partition or invalid partition, return
	if left >= right:
		return
	# 1. divide array into two equal partitions
	mid = int((left + right) / 2)
	# 2. apply merge sort to left partition - left to mid
	mergeSort(a, left, mid)
	# 3. apply merge sort to right partition - mid+1 to right
	mergeSort(a, mid+1, right)
	# 4. create temp array to accomodate both partitions
	temp = [0]*(right - left + 1)
	# 5. merge both sorted partitions into temp array
	i = left
	j = mid+1
	k = 0
	while i <= mid and j <= right:
		if a[i] < a[j]:
			temp[k] = a[i]
			i = i + 1
			k = k + 1
		else:
			temp[k] = a[j]
			j = j + 1
			k = k + 1
	while i <= mid:
		temp[k] = a[i]
		i = i + 1
		k = k + 1
	while j <= right:
		temp[k] = a[j]
		j = j + 1
		k = k + 1
	# 6. overwrite temp array back to the main array
	for i in range(0, len(temp)):
		a[left+i] = temp[i]


def main():
	arr = [20, 12, 35, 15, 10, 80, 30, 17, 2, 1]
	print(arr)
	# quickSort(arr, 0, len(arr)-1)
	mergeSort(arr, 0, len(arr) - 1)
	print(arr)



if __name__ == "__main__":
	main()
