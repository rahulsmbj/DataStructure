
class Pair:
	def __init__(self, key=None, value=None):
		self.key = key
		self.value = value


class HashTable:
	def __init__(self):
		self.SLOTS = 100
		self.table = [None] * self.SLOTS
		for i in range(0, len(self.table)):
			self.table[i] = list()

	def put(self, key, value):
		# decide the slot of the table in which value to be added.
		index = hash(key) % self.SLOTS
		# check if key is already present in that slot/bucket.
		for p in self.table[index]:
			if key == p.key:
				p.value = value # overwrite value if key already present.
				return
		# if not present, add key-value pair in that slot/bucket.
		pair = Pair(key, value)
		self.table[index].append(pair)

	def get(self, key):
		# decide the slot of the table in which value to be added.
		index = hash(key) % self.SLOTS
		# check if key is already present in that slot/bucket.
		for p in self.table[index]:
			if key == p.key:
				return p.value
		return None


def main():
	ht = HashTable()
	ht.put(2203, "Analyst")
	ht.put(4605, "Developer")
	ht.put(7401, "Tester")
	ht.put(5998, "HR")
	ht.put(6401, "CEO")
	ht.put(7101, "Manager")
	ht.put(6401, "Director")

	key = 6401
	res = ht.get(key)
	print(str(key) + " -> " + str(res))


if __name__ == "__main__":
	main()
