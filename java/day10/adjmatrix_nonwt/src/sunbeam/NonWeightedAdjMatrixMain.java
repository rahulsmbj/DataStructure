package sunbeam;

import java.util.Scanner;

class NonWeightedAdjMatrixGraph {
	private int[][] mat;
	private int vertCount, edgeCount;
	
	public NonWeightedAdjMatrixGraph(int vCount) {
		edgeCount = 0;
		vertCount = vCount;
		mat = new int[vertCount][vertCount];
		// in java all int[][] elements are 0 by default.
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enumber number of edges: ");
		edgeCount = sc.nextInt();
		for(int i=0; i<edgeCount; i++) {
			System.out.print("Enter edge (src dest): ");
			int src = sc.nextInt(); // from
			int dest = sc.nextInt(); // to
			mat[src][dest] = 1;
			mat[dest][src] = 1; // for directed graph, remove this line.
		}
	}
	
	public void display() {
		System.out.println();
		for (int i = 0; i < vertCount; i++) {
			for (int j = 0; j < vertCount; j++)
				System.out.print(mat[i][j] + "\t");
			System.out.println();
		}
	}
}

public class NonWeightedAdjMatrixMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		NonWeightedAdjMatrixGraph g = new NonWeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		sc.close();
	}
}

/*
6
7
0 1
0 2
0 3
1 2
1 4
3 4
3 5
*/




