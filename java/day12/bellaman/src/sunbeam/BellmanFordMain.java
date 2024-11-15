package sunbeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	public int[] bellmanFord(int start) {
		// keep dist of all vertices, init = INF
		int[] dist = new int[vertCount];
		for(int v = 0; v < vertCount; v++)
			dist[v] = INF;
		// dp init: start vertex dist = 0
		dist[start] = 0;
		// repeat for V-1 times
		for(int i = 1; i < vertCount; i++) { // O(V)
			// for each edge
			for(Edge e:edgeList) { // O(E)
				if(dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
					dist[e.dest] = dist[e.src] + e.weight;
			}
		}
		// check for -ve weight cycle
		for(Edge e:edgeList) {
			if(dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
				throw new RuntimeException("Graph has -ve weight cycle.");
		}
		return dist;
	}
}

public class BellmanFordMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		WeightedAdjMatrixGraph g = new WeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		int[] dist = g.bellmanFord(0);
		for (int v = 0; v < vCount; v++)
			System.out.println("Distance from 0 to vertex " + v + " = " + dist[v]);
		sc.close();
	}
}

/*
5
7
3 4 3
2 4 3
2 3 4
2 1 -2
1 3 -1
0 2 5
0 1 6
*/




