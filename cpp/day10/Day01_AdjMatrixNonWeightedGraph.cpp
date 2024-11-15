#include <iostream>
#include <stack>
#include <queue>
#include <cstring>
using namespace std;

class AdjMatrixNonWeightedGraph
{
private:
	int **mat;
	int vertCount, edgeCount;

public:
	AdjMatrixNonWeightedGraph(int vCount)
	{
		vertCount = vCount;
		edgeCount = 0;
		mat = new int *[vertCount];
		for (int i = 0; i < vertCount; i++)
		{
			mat[i] = new int[vertCount];
			for (int j = 0; j < vertCount; j++)
				mat[i][j] = 0;
		}
	}
	~AdjMatrixNonWeightedGraph()
	{
		for (int i = 0; i < vertCount; i++)
			delete[] mat[i];
		delete[] mat;
		mat = NULL;
		vertCount = 0;
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
			mat[dest][src] = 1; // for undirected graph src -> dest => dest -> src
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

	void dfsTrav(int start)
	{
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		stack<int> s;
		cout << "DFS: ";
		// push start vertex on stack and mark it
		s.push(start);
		marked[start] = true;
		// while stack is not empty
		while (!s.empty())
		{
			// pop vertex from stack and visit it
			int trav = s.top();
			s.pop();
			cout << "\t" << trav << ", ";
			// put all its non-marked adjacent vertices on the stack & mark them
			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] != 0 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
				}
			}
		}
		cout << endl;
		delete[] marked;
	}


	void bfsTrav(int start)
	{
		bool *marked = new bool[vertCount];
		memset(marked, 0, vertCount * sizeof(bool));
		queue<int> s;
		cout << "BFS: ";
		// push start vertex on queue and mark it
		s.push(start);
		marked[start] = true;
		// while queue is not empty
		while (!s.empty())
		{
			// pop vertex from queue and visit it
			int trav = s.front();
			s.pop();
			cout << "\t" << trav << ", ";
			// put all its non-marked adjacent vertices on the queue & mark them
			for (int j = 0; j < vertCount; j++)
			{
				if (mat[trav][j] != 0 && marked[j] == false)
				{
					s.push(j);
					marked[j] = true;
				}
			}
		}
		cout << endl;
		delete[] marked;
	}


};

int main()
{
	AdjMatrixNonWeightedGraph g(6);
	g.accept();
	g.display();
	g.dfsTrav(0);
	g.bfsTrav(0);
	return 0;
}

/*
7
0 1
0 2
0 3
1 2
1 4 
3 4
3 5 
*/

