class Node:
	def __init__(self, val=0):
		self.data = val
		self.next = None


class SinglyList:

	def __init__(self):
		self.head = None

	# time complexity - O(1)
	def addFirst(self, val):
		newNode = Node(val)
		newNode.next = self.head
		self.head = newNode


	# time complexity - O(n)
	def addLast(self, val):
		newNode = Node(val)
		# special case: if list is empty
		if self.head is None:
			self.head = newNode # newNode itself is first node of list
		else: # general case: if list already have few nodes
			# traverse till last node
			trav = self.head
			while trav.next is not None:
				trav = trav.next
			# add address of newNode into last node's next
			trav.next = newNode


	# time complexity - O(n)
	def display(self):
		print("List: ", end='')
		trav = self.head
		while trav is not None:
			print(trav.data, end=", ")
			trav = trav.next
		print()


def main():
	l1 = SinglyList()
	l1.addLast(11)
	l1.addLast(22)
	l1.addLast(33)
	l1.addFirst(44)
	l1.display()


if __name__ == "__main__":
	main()

