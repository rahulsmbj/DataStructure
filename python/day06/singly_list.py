
class Node:
	def __init__(self, val=0):
		self.data = val
		self.next = None


class SinglyList:

	def __init__(self):
		self.head = None

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

	def selectionSort(self):
		i = self.head
		while i is not None:
			j = i.next
			while j is not None:
				if i.data > j.data:
					temp = i.data
					i.data = j.data
					j.data = temp
				j = j.next
			i = i.next

	def reverse(self):
		oldhead = self.head
		self.head = None
		while oldhead is not None:
			temp = oldhead
			oldhead = oldhead.next
			temp.next = self.head
			self.head = temp

	def recReverseDisplay(self, trav):
		if trav is None:
			return
		self.recReverseDisplay(trav.next)
		print(trav.data, end=", ")

	def reverseDisplay(self):
		print("Rev Display: ", end='')
		self.recReverseDisplay(self.head)
		print()

	def recurReverse(self, trav):
		if trav.next is None:
			self.head = trav
			return
		self.recurReverse(trav.next)
		trav.next.next = trav
		trav.next = None

	def recReverse(self):
		if self.head is not None:
			self.recurReverse(self.head)

	def findMiddle(self):
		fast = self.head
		slow = self.head
		while fast is not None and fast.next is not None:
			slow = slow.next
			fast = fast.next.next
		if slow is not None:
			return slow.data
		return 0

def main():
	l1 = SinglyList()
	l1.addLast(30)
	l1.addLast(10)
	l1.addLast(40)
	l1.addLast(20)
	l1.display()

	l1.selectionSort()
	l1.display()

	l1.reverse()
	l1.display()

	l1.reverseDisplay()
	l1.display()

	l1.recReverse()
	l1.display()

	l1.addLast(50)
	l1.display()
	res = l1.findMiddle()
	print("Middle : " + str(res))


if __name__ == "__main__":
	main()

