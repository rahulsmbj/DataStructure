
class AdjListGraph:


	def __init__(self, vCount):
		self.vertCount = vCount
		self.edgeCount = 0
		self.vertexEdges = []
		for i in range(self.vertCount):
			self.vertexEdges.append([])
	

	def accept(self):
		self.edgeCount = int(input("Enter number of edges: "))
		print(f"Enter {self.edgeCount} edges -- src dest weight:")
		for i in range(self.edgeCount):
			(src_str, dest_str, wt_str) = input().split()
			sdw1 = (int(src_str), int(dest_str), int(wt_str))
			sdw2 = (sdw1[1], sdw1[0], sdw1[2])
			self.vertexEdges[sdw1[0]].append(sdw1)
			self.vertexEdges[sdw2[0]].append(sdw2) # for undirected graph src -> dest => dest -> src 


	def display(self):
		for i in range(self.vertCount):
			print(f"v{i}", end=': ')
			for e in self.vertexEdges[i]:
					print(e, end=' -> ')
			print()


if __name__=="__main__":
	g = AdjListGraph(6)
	g.accept()
	g.display()


"""
7
0 1 7
0 2 4
0 3 8
1 2 9
1 4 5
3 4 6
3 5 2
"""
