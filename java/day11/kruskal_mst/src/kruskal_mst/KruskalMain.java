package kruskal_mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import kruskal_mst.WeightedAdjMatrixGraph.Edge;

class WeightedAdjMatrixGraph {
	static class Edge {
		private int src, dest, weight;
		public Edge() {
		}
		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
		public int getWeight() {
			return weight;
		}
		@Override
		public String toString() {
			return "Edge [src=" + src + ", dest=" + dest + ", weight=" + weight + "]";
		}
	}
	
	public static final int INF = 999; //Integer.MAX_VALUE;
	private int[][] mat;
	private int vertCount, edgeCount;
	private List<Edge> edgeList;
	
	public WeightedAdjMatrixGraph(int vCount) {
		edgeList = new ArrayList<>();
		edgeCount = 0;
		vertCount = vCount;
		mat = new int[vertCount][vertCount];
		for(int i=0; i<vertCount; i++) {
			for(int j=0; j<vertCount; j++)
				mat[i][j] = INF;
		}
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enumber number of edges: ");
		edgeCount = sc.nextInt();
		for(int i=0; i<edgeCount; i++) {
			System.out.print("Enter edge (src dest weight): ");
			int src = sc.nextInt(); // from
			int dest = sc.nextInt(); // to
			int wt = sc.nextInt();
			mat[src][dest] = wt;
			mat[dest][src] = wt; // for directed graph, remove this line.
			Edge e = new Edge(src, dest, wt);
			edgeList.add(e);
		}
	}
	
	public void display() {
		System.out.println();
		for (int i = 0; i < vertCount; i++) {
			for (int j = 0; j < vertCount; j++)
				System.out.print(mat[i][j] == INF ? "##\t" : mat[i][j]+"\t");
			System.out.println();
		}
	}

	public int find(int v, int[] parent) {
		while(parent[v] != -1)
			v = parent[v];
		return v;
	}
	
	public void union(int srcRoot, int destRoot, int[] parent) {
		parent[srcRoot] = destRoot;
	}
	
	public boolean unionFind(List<Edge> mst) {
		// array to keep track of parent of vertices - init all -1
		int[] parent = new int[vertCount];
		for(int i = 0; i < vertCount; i++)
			parent[i] = -1;
		// for each edge in mst
		for(Edge e:mst) {
			// find root of src & dest
			int srcRoot = find(e.src, parent);
			int destRoot = find(e.dest, parent);
			// if both are from same set (root), then it is a cycle
			if(srcRoot == destRoot)
				return true;
			// if both not from same set, union them.
			union(srcRoot, destRoot, parent);
		}
		return false;
	}
	
	public List<Edge> kruskalMst() {
		List<Edge> mst = new ArrayList<>();
		// sort all edges in asc order of weight
		edgeList.sort((e1,e2) -> e1.weight - e2.weight);
		
		// pick edges one by one
		for(Edge e: edgeList) {
			// add edge into mst
			mst.add(e);
			// check if forming cycle, discard it from mst (last element)
			if(unionFind(mst))	
				mst.remove(mst.size() - 1);
			// if vertCount-1 edges are in mst, stop
			if(mst.size() == vertCount - 1)
				break;
		}
		return mst; // return mst
	}
}

public class KruskalMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		WeightedAdjMatrixGraph g = new WeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		List<Edge> mst = g.kruskalMst();
		int mstWeight = 0;
		for (Edge e : mst) {
			System.out.println(e);
			mstWeight = mstWeight + e.getWeight();
		}
		System.out.println("MST Weight: " + mstWeight);
		sc.close();
	}
}

/*
9
14
7 6 1
8 2 2
6 5 2
0 1 4
2 5 4
8 6 6
2 3 7
7 8 7
0 7 8
1 2 8
3 4 9
5 4 10
1 7 11
3 5 14 
*/

/*
edgeList.sort(new Comparator<Edge>() {
	@Override
	public int compare(Edge e1, Edge e2) {
		return e1.weight - e2.weight;
	}
}); 
*/












