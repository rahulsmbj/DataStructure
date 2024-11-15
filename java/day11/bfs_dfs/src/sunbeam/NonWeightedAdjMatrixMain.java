package sunbeam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

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
	
	public void bfs(int start) {
		System.out.print("BFS: ");
		boolean[] marked = new boolean[vertCount]; // init = false
		Queue<Integer> q = new LinkedList<>();
		// put start vertex in queue and mark it
		q.offer(start);
		marked[start] = true;
		while(!q.isEmpty()) {
			// pop a vertex from queue and visit it.
			int trav = q.poll();
			System.out.print(trav + ", ");
			// find all non-marked neighbors, add in queue and mark them.
			for(int dest=0; dest<vertCount; dest++) {
				if(mat[trav][dest] != 0 && marked[dest] == false) {
					q.offer(dest);
					marked[dest] = true;
				}
			}
		} // repeat until queue is empty
		System.out.println();
	}

	public void bfsSpanningTree(int start) {
		System.out.println("BFS Spanning Tree: ");
		boolean[] marked = new boolean[vertCount]; // init = false
		Queue<Integer> q = new LinkedList<>();
		// put start vertex in queue and mark it
		q.offer(start);
		marked[start] = true;
		while(!q.isEmpty()) {
			// pop a vertex from queue and visit it.
			int trav = q.poll();
			// find all non-marked neighbors, add in queue and mark them.
			for(int dest=0; dest<vertCount; dest++) {
				if(mat[trav][dest] != 0 && marked[dest] == false) {
					q.offer(dest);
					marked[dest] = true;
					// print edge from current vertex to its neighbor.
					System.out.println(trav + " -> " + dest);
				}
			}
		} // repeat until queue is empty
		System.out.println();
	}

	public int[] bfsSingleSourcePathLength(int start) {
		// create dist array to keep track of dist to each vertex.
		int[] dist = new int[vertCount];
		// dist of starting vertex is zero.
		dist[start] = 0;
		
		boolean[] marked = new boolean[vertCount]; // init = false
		Queue<Integer> q = new LinkedList<>();
		// put start vertex in queue and mark it
		q.offer(start);
		marked[start] = true;
		while(!q.isEmpty()) {
			// pop a vertex from queue and visit it.
			int trav = q.poll();
			// find all non-marked neighbors, add in queue and mark them.
			for(int dest=0; dest<vertCount; dest++) {
				if(mat[trav][dest] != 0 && marked[dest] == false) {
					q.offer(dest);
					marked[dest] = true;
					// calculate dist of neighbor
					dist[dest] = dist[trav] + 1;
				}
			}
		} // repeat until queue is empty
		return dist;
	}
	
	public boolean isBipartite() {
		final int NO_COLOR = 0, GREEN = 1, YELLOW = -1;
		int[] marked = new int[vertCount]; // init = non-colored
		Queue<Integer> q = new LinkedList<>();
		// put start vertex in queue and color it
		int start = 0;
		q.offer(start);
		marked[start] = GREEN;
		while(!q.isEmpty()) {
			// pop a vertex from queue and visit it.
			int trav = q.poll();
			for(int dest=0; dest<vertCount; dest++) {
				if(mat[trav][dest] != 0) {
					// if neighbor is colored with same color, this is not bi-partite
					if(marked[dest] == marked[trav])
						return false;
					// non-colored neighbors to colored with opposite color
					// and add into queue
					if(marked[dest] == NO_COLOR) {
						marked[dest] = -1 * marked[trav];
						q.offer(dest);
					}
				}
			}
		} // repeat until queue is empty
		// if all vertices are colored in opposite colors of neighbor
		// means graph is bi-partite
		return true;
	}

	public void dfs(int start) {
		System.out.print("DFS: ");
		boolean[] marked = new boolean[vertCount]; // init = false
		Stack<Integer> s = new Stack<>();
		// put start vertex in stack and mark it
		s.push(start);
		marked[start] = true;
		while(!s.isEmpty()) {
			// pop a vertex from stack and visit it.
			int trav = s.pop();
			System.out.print(trav + ", ");
			// find all non-marked neighbors, add in stack and mark them.
			for(int dest=0; dest<vertCount; dest++) {
				if(mat[trav][dest] != 0 && marked[dest] == false) {
					s.push(dest);
					marked[dest] = true;
				}
			}
		} // repeat until stack is empty
		System.out.println();
	}


	public boolean isConnected() {
		int start = 0;
		int vCount = 0;
		boolean[] marked = new boolean[vertCount]; // init = false
		Stack<Integer> s = new Stack<>();
		// put start vertex in stack and mark it and count it.
		s.push(start);
		marked[start] = true;
		vCount++;
		while(!s.isEmpty()) {
			// pop a vertex from stack and visit it.
			int trav = s.pop();
			// find all non-marked neighbors, add in stack and mark them and count them.
			for(int dest=0; dest<vertCount; dest++) {
				if(mat[trav][dest] != 0 && marked[dest] == false) {
					s.push(dest);
					marked[dest] = true;
					vCount++;
					// if vCount is same as graph vertex count, it means graph is connected
					if(vCount == vertCount)
						return true;
				}
			}
		} // repeat until stack is empty
		// after completing dfs if all vertices are not counted, then graph is dis-connected.
		return false;
	}

	public void dfsSpanningTree(int start) {
		System.out.println("DFS Spanning Tree: ");
		boolean[] marked = new boolean[vertCount]; // init = false
		Stack<Integer> s = new Stack<>();
		// put start vertex in stack and mark it
		s.push(start);
		marked[start] = true;
		while(!s.isEmpty()) {
			// pop a vertex from stack and visit it.
			int trav = s.pop();
			// find all non-marked neighbors, add in stack and mark them.
			for(int dest=0; dest<vertCount; dest++) {
				if(mat[trav][dest] != 0 && marked[dest] == false) {
					s.push(dest);
					marked[dest] = true;
					// print edge from current vertex to neighbor vertex
					System.out.println(trav + " -> " + dest);
				}
			}
		} // repeat until stack is empty
		System.out.println();
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
		g.bfs(0);
		g.dfs(0);
		System.out.println("Is Connected: " + g.isConnected());
		g.dfsSpanningTree(0);
		g.bfsSpanningTree(0);
		int start = 0;
		int[] dist = g.bfsSingleSourcePathLength(start);
		for(int i=0; i<dist.length; i++)
			System.out.println("Dist from " + start + " to " + i + " = " + dist[i]);
		System.out.println("Is Bipartite: " + g.isBipartite());
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




