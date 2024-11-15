package sunbeam;

import java.util.Scanner;

class WeightedAdjMatrixGraph {
	public static final int INF = 999; //Integer.MAX_VALUE;
	private int[][] mat;
	private int vertCount, edgeCount;
	
	public WeightedAdjMatrixGraph(int vCount) {
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

	public int[] dijkstraSpt(int start, int[] dist) {
		// dist(v) = INF, parent(v) = -1, sptVert(v) = false
		int[] parent = new int[vertCount];
		boolean[] sptVert = new boolean[vertCount];
		for (int v = 0; v < vertCount; v++) {
			dist[v] = INF;
			parent[v] = -1;
			sptVert[v] = false;
		}
		// make dist of start vertex as 0
		dist[start] = 0;
		
		int sptVertexCount = 0;
		while(sptVertexCount < vertCount) { // O(V)
			// pick vertex (u) with min key (that is not in spt)
			int u = getMinDistVertex(dist, sptVert); // O(V) or O(log V)
			// add vertex in spt
			sptVert[u] = true;
			sptVertexCount++;
			// update dists & parent of all neighbors (v), when dist(u) + weight(u,v) < dist(v)
			for (int v = 0; v < vertCount; v++) { // O(V)
				if(mat[u][v] != INF && !sptVert[v] && (dist[u] + mat[u][v]) < dist[v]) {
					dist[v] = dist[u] + mat[u][v];
					parent[v] = u;
				}
			}	
		}// repeat for V vertices
		return parent;
	}

	private int getMinDistVertex(int[] dist, boolean[] sptVert) {
		int minDist = INF, minDistVert = -1;
		for (int v = 0; v < vertCount; v++) {
			if(!sptVert[v] && dist[v] < minDist) {
				minDist = dist[v];
				minDistVert = v;
			}
		}
		return minDistVert;
	}
	
	public int getWeight(int src, int dest) {
		return mat[src][dest];
	}
}

public class DijkstraMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		WeightedAdjMatrixGraph g = new WeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		int start = 0;
		int[] dist = new int[vCount];
		int[] parent = g.dijkstraSpt(start, dist);
		for (int i = 0; i < parent.length; i++)
			System.out.println("dist of vertex " + i + " from " + start + " = " + dist[i]);
		int dest = 4;
		for(int v = dest; v != -1; v = parent[v])
			System.out.print(v + " -> ");
		System.out.println();
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
