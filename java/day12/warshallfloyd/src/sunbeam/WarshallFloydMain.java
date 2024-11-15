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
	
	public int[][] warshallFloyd() {
		// dp state -- dist matrix = adj mat with dist[v][v] = 0.
		int[][] dist = new int[vertCount][vertCount];
		for (int s = 0; s < dist.length; s++) {
			for (int d = 0; d < dist.length; d++)
				dist[s][d] = mat[s][d];
			dist[s][s] = 0;
		}
		// all pair shortest path algorithm
		for (int i = 0; i < dist.length; i++) {
			for (int s = 0; s < dist.length; s++) {
				for (int d = 0; d < dist.length; d++) {
					if(dist[s][i] != INF && dist[i][d] != INF && dist[s][i] + dist[i][d] < dist[s][d])
						dist[s][d] = dist[s][i] + dist[i][d];
				}
			}
		}
		return dist;
	}
}

public class WarshallFloydMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		WeightedAdjMatrixGraph g = new WeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		System.out.println("\nDistance Matrix:\n");
		int[][] dist = g.warshallFloyd();
		for (int s = 0; s < dist.length; s++) {
			for (int d = 0; d < dist.length; d++)
				System.out.print(dist[s][d] + "\t");
			System.out.println();
		}
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




