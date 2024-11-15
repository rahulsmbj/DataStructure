package sunbeam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
	
	public List<Integer> topologicalOrder() {
		List<Integer> order = new ArrayList<>();
		// create queue for adding vertices with indeg=0.
		Queue<Integer> q = new LinkedList<>();
		// calculate in-degree of each vertex & add in queue if indeg=0
		int[] indeg = new int[vertCount];
		for(int j = 0; j < vertCount; j++) {
			for(int i = 0; i < vertCount; i++) {
				if(mat[i][j] != 0)
					indeg[j]++;
			}
			if(indeg[j] == 0)
				q.offer(j);
		}
		
		while(!q.isEmpty()) {
			// pop a vertex from queue & add in topological order
			int trav = q.poll();
			order.add(trav);
			// decrement in-deg of all neighbors
			for(int j=0; j < vertCount; j++) {
				if(mat[trav][j] != 0) {
					indeg[j]--;
					// if indeg[j] is zero, push to que
					if(indeg[j] == 0)
						q.offer(j);
				}
			}
		} // repeat until q is empty
		
		// check if all vertices are added in the order
		if(order.size() != vertCount)
			throw new RuntimeException("Topological Order Is not Possible");
		return order;
	}
}

public class TopologicalOrderMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		NonWeightedAdjMatrixGraph g = new NonWeightedAdjMatrixGraph(vCount);
		g.accept(sc);
		g.display();
		List<Integer> order = g.topologicalOrder();
		System.out.println(order);
		sc.close();
	}
}

/*
5
7
0 1
0 2
1 3
2 1
2 3
2 4
3 4
*/




