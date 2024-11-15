#include <iostream>
#include <vector>
#include <list>
using namespace std;

class Edge {
	private:
		int src, dest, weight;
	public:
	Edge(int src, int dest, int weight) {
		this->src = src;
		this->dest = dest;
		this->weight = weight;
	}
	void display() {
		cout << "Edge [src=" << src << ", dest=" << dest << ", weight=" << weight << "]";
	}
};

class AdjacencyListGraph {
	private:
		int vertCount;
		vector< list<Edge> > vertexEdges;
	
	public:
		AdjacencyListGraph(int vCount) {
			vertCount = vCount;
			list<Edge> el;
			for (int i = 0; i < vertCount; i++)
				vertexEdges.push_back(el);
		}
	
	void accept() {
		int edgeCount, src, dest, wt;
		cout << "Enter number of edges: ";
		cin >> edgeCount;
		cout << "Enter " << edgeCount << " edges -- src dest weight:" << endl;
		for (int i = 0; i < edgeCount; i++) {
			cin >> src >> dest >> wt;
			Edge e1(src, dest, wt);
			vertexEdges[src].push_back(e1);
			Edge e2(dest, src, wt);
			vertexEdges[dest].push_back(e2);			
		}
	}
	
	void display() {
		for (int i = 0; i < vertCount; i++) {
			cout << "v" << i << "\t";
			list<Edge>::iterator itr = vertexEdges[i].begin();
			while (itr != vertexEdges[i].end()) {
				itr->display();
				cout << " --> ";
				itr++;
			}
			cout << endl;
		}
	}
};

int main()
{
	AdjacencyListGraph obj(6);
	obj.accept();
	obj.display();
}

/*
7
0 1 7
0 2 4
0 3 8
1 2 9
1 4 5
3 4 6
3 5 2
*/
