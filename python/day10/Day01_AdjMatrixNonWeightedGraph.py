
class AdjMatrixNonWeightedGraph:


	def __init__(self, vCount):
		self.vertCount = vCount
		self.edgeCount = 0
		self.mat = []
		for i in range(self.vertCount):
			self.mat.append([0] * self.vertCount)
	

	def accept(self):
		self.edgeCount = int(input("Enter number of edges: "))
		print(f"Enter {self.edgeCount} edges -- src dest:")
		for i in range(self.edgeCount):
			(src_str, dest_str) = input().split()
			(src,dest) = (int(src_str), int(dest_str))
			self.mat[src][dest] = 1
			self.mat[dest][src] = 1 # for undirected graph src -> dest => dest -> src 


	def display(self):
		for i in range(self.vertCount):
			print(f"v{i}", end='')
			for j in range(self.vertCount):
				print("\t{}".format(self.mat[i][j]), end='')
			print()


	def dfsTrav(self, start):
		marked = [False] * self.vertCount
		s = list()
		print("DFS: ", end='')
		# push start vertex on stack and mark it
		s.append(start)
		marked[start] = True
		# while stack is not empty
		while s:
			# pop vertex from stack and visit it
			trav = s.pop()
			print(f"{trav}, ", end='')
			# put all its non-marked adjacent vertices on the stack & mark them
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
		print()

	
	def bfsTrav(self, start):
		marked = [False] * self.vertCount
		s = list()
		print("BFS: ", end='')
		# push start vertex on queue and mark it
		s.append(start)
		marked[start] = True
		# while queue is not empty
		while s:
			# pop vertex from queue and visit it
			trav = s.pop(0)
			print(f"{trav}, ", end='')
			# put all its non-marked adjacent vertices on the queue & mark them
			for j in range(self.vertCount):
				if self.mat[trav][j]!=0 and marked[j]==False:
					s.append(j)
					marked[j] = True
		print()


if __name__=="__main__":
	g = AdjMatrixNonWeightedGraph(6)
	g.accept()
	g.display()
	g.dfsTrav(0)
	g.bfsTrav(0)


"""
7
0 1
0 2
0 3
1 2
1 4
3 4
3 5
"""
