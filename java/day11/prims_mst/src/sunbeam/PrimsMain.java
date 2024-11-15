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

	public int[] primsMst(int start) {
		// key(v) = INF, parent(v) = -1, mstVert(v) = false
		int[] key = new int[vertCount];
		int[] parent = new int[vertCount];
		boolean[] mstVert = new boolean[vertCount];
		for (int v = 0; v < vertCount; v++) {
			key[v] = INF;
			parent[v] = -1;
			mstVert[v] = false;
		}
		// make key of start vertex as 0
		key[start] = 0;
		
		int mstVertexCount = 0;
		while(mstVertexCount < vertCount) {
			// pick vertex (u) with min key (that is not in mst)
			int u = getMinKeyVertex(key, mstVert);
			// add vertex in mst
			mstVert[u] = true;
			mstVertexCount++;
			// update keys & parent of all neighbors (v), when weight(u,v) < key(v)
			for (int v = 0; v < vertCount; v++) {
				if(mat[u][v] != INF && !mstVert[v] && mat[u][v] < key[v]) {
					key[v] = mat[u][v];
					parent[v] = u;
				}
			}	
		}// repeat for V vertices
		return parent;
	}

	private int getMinKeyVertex(int[] key, boolean[] mstVert) {
		int minKey = INF, minKeyVert = -1;
		for (int v = 0; v < vertCount; v++) {
			if(!mstVert[v] && key[v] < minKey) {
				minKey = key[v];
				minKeyVert = v;
			}
		}
		return minKeyVert;
	}
	
	public int getWeight(int src, int dest) {
		return mat[src][dest];
	}
}

public class PrimsMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		WeightedAdjMatrixGraph g = new WeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		int[] parent = g.primsMst(0);
		int mstWeight = 0;
		for (int v = 0; v < parent.length; v++) {
			System.out.println(parent[v] + " -> " + v);
			if(parent[v] != -1) // root of mst
				mstWeight = mstWeight + g.getWeight(parent[v], v);
		}
		System.out.println("MST Weight : " + mstWeight);
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
