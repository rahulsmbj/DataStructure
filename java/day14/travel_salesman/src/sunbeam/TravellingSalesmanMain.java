package sunbeam;

import java.util.Scanner;

class WeightedAdjMatrixGraph {
	public static final int INF = 9999; //Integer.MAX_VALUE;
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
	
	public int travellingSalesman(int cur, boolean[] visited, int count, int cost, int result) {
		if(count == vertCount && mat[cur][0] > 0) {
			result = Math.min(result, cost + mat[cur][0]);
			return result;
		}
		
		for(int i=0; i<vertCount; i++) {
			if(!visited[i] && mat[cur][i] > 0) {
				visited[i] = true;
				result = travellingSalesman(i, visited, count+1, cost + mat[cur][i], result);
				visited[i] = false;
			}
		}
		
		return result;
	}
	
	public void travellingSalesman() {
		boolean[] visited = new boolean[vertCount];
		
		visited[0] = true;
		int result = INF;
		
		result = travellingSalesman(0, visited, 1, 0, result);
	
		System.out.println("Travelling Salesman Cycle Distance: " + result);
	}
}

public class TravellingSalesmanMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		WeightedAdjMatrixGraph g = new WeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		g.travellingSalesman();
		sc.close();
	}
}

/*
4
16
0 0 0
0 1 10
0 2 15
0 3 20
1 0 5
1 1 0
1 2 9
1 3 10
2 0 6
2 1 13
2 2 0
2 3 12
3 0 8
3 1 8
3 2 9
3 3 0
*/




