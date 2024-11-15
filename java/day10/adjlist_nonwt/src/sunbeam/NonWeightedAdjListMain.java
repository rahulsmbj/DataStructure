package sunbeam;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class NonWeightedAdjListGraph {
	private int vertCount, edgeCount;
	private List []adjlist;
	
	public NonWeightedAdjListGraph(int vCount) {
		edgeCount = 0;
		vertCount = vCount;
		adjlist = new LinkedList[vertCount];
		for (int i = 0; i < adjlist.length; i++)
			adjlist[i] = new LinkedList<Integer>();
	}
	
	public void accept(Scanner sc) {
		System.out.print("Enumber number of edges: ");
		edgeCount = sc.nextInt();
		for(int i=0; i<edgeCount; i++) {
			System.out.print("Enter edge (src dest): ");
			int src = sc.nextInt(); // from
			int dest = sc.nextInt(); // to
			adjlist[src].add(dest);
			adjlist[dest].add(src); // remove this line for directed graph
		}
	}
	
	public void display() {
		System.out.println();
		for (int i = 0; i < vertCount; i++) {
			System.out.print(i + " --> ");
			System.out.println(adjlist[i].toString());
		}
	}
}

public class NonWeightedAdjListMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int vCount = sc.nextInt();
		NonWeightedAdjListGraph g = new NonWeightedAdjListGraph(vCount);
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




