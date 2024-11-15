#include <iostream>
#include <stack>
#include <queue>
#include <cstring>
using namespace std;

#define vertCount 5

class AdjMatrixNonWeightedGraph
{
private:
	int mat[vertCount][vertCount];
	int edgeCount;

public:
	AdjMatrixNonWeightedGraph()
	{
		edgeCount = 0;
		for (int i = 0; i < vertCount; i++)
		{
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = 0;
		}
	}
	~AdjMatrixNonWeightedGraph()
	{
		edgeCount = 0;
	}

	void accept()
	{
		cout << "Enter number of edges: ";
		cin >> edgeCount;
		cout << "Enter " << edgeCount << " edges -- src dest weight:" << endl;
		for (int i = 0; i < edgeCount; i++)
		{
			int src, dest;
			cin >> src >> dest;
			mat[src][dest] = 1;
		}
	}

	void display()
	{
		for (int i = 0; i < vertCount; i++)
		{
			cout << "v" << i;
			for (int j = 0; j < vertCount; j++)
				cout << "\t" << mat[i][j];
			cout << endl;
		}
	}

	void dfsTrav(int trav, bool marked[], vector<int>& order) {
		if(marked[trav] == true)
			return;
		marked[trav] = true;
		order.push_back(trav);
		for (int j = 0; j < vertCount; j++) {
			if(mat[trav][j]==1)
				dfsTrav(j, marked, order);
		}
	}
	
	vector<int> dfsTrav(int start) {
		bool marked[vertCount];
		vector<int> order;
		dfsTrav(start, marked, order);
		return order;
	}

	void dfsTopologicalSort(int trav, bool marked[], bool stacked[], vector<int>& order) {
		if(stacked[trav] == true)
			throw "No topological order.";	
		if(marked[trav] == true)
			return;
		stacked[trav] = true;
		marked[trav] = true;
		for (int j = 0; j < vertCount; j++) {
			if(mat[trav][j]==1)
				dfsTopologicalSort(j, marked, stacked, order);
		}
		stacked[trav] = false;
		order.insert(order.begin(), trav);
	}
	
	vector<int> dfsTopologicalSort() {
		bool marked[vertCount];
		bool stacked[vertCount];
		vector<int> order;
		for (int start = 0; start < vertCount; start++) {
			order.clear();
			dfsTopologicalSort(start, marked, stacked, order);
			if(order.size() == vertCount)
				return order;
		}
		throw "No topological order.";
	}
	
	vector<int> kahnTopologicalSort() {
		vector<int> order;
		
		queue<int> q;
		int indeg[vertCount] = {0};
		// count in-degrees of all vertices -- O(V*V) or O(E)
		for (int j = 0; j < vertCount; j++) {
			for (int i = 0; i < vertCount; i++) {
				if(mat[i][j] != 0)
					indeg[j]++;
			}
			// if indeg of vertex is 0, add it to queue
			if(indeg[j] == 0)
				q.push(j);
		}
		
		// while start queue is not empty O(V*V) -- O(V+E)
		while(!q.empty()) {
			// visit a vertex
			int trav = q.front();
			q.pop();
			order.push_back(trav);
			// reduce in-degree of neighbors
			for (int j = 0; j < vertCount; j++) {
				if(mat[trav][j] == 1) {
					indeg[j]--;
					// indeg of vertex become 0, add it to queue
					if(indeg[j] == 0)
						q.push(j);
				}
			}
		}
		
		if(order.size() != vertCount)
			throw "No topological order.";
		
		return order;
	}
};

void print_vector(const char *label, vector<int>& vect) {
	cout << label;
	for (size_t i = 0; i < vect.size(); i++)
		cout << vect[i] << ", ";
	cout << endl;
}

int main()
{
	AdjMatrixNonWeightedGraph g;
	g.accept();
	g.display();

	vector<int> res = g.dfsTrav(0);
	print_vector("DFS: ", res);
	res = g.dfsTopologicalSort();
	print_vector("TOP: ", res);
	res = g.kahnTopologicalSort();
	print_vector("TOP: ", res);
	return 0;
}

/*
7
3 4
2 4
2 3
2 1
1 3
0 2
0 1 
*/

/*
6
9
0 1
1 2
1 4 
2 1
3 0
3 4
3 5
4 5
5 3
*/
