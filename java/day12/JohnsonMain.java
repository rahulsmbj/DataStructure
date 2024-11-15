package sunbeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Johnson {
    static class Edge {
        int src, dest, weight;
        public Edge() {
            this(0, 0, 0);
        }
        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
        @Override
        public String toString() {
            return src + " -> " + dest + " ( " + weight + " ) ";
        }
    }

    public static final int INF = 999;
    private int mat[][];
    private int vertCount, edgeCount;
    private Edge[] edges;

    public Johnson(int vCount) {
        vertCount = vCount;
        mat = new int[vertCount][];
        for (int i = 0; i < vertCount; i++) {
            mat[i] = new int[vertCount];
            for (int j = 0; j < vertCount; j++) {
                mat[i][j] = INF;
            }
        }
        edges = new Edge[0];
    }

    public void accept(Scanner sc) {
        System.out.print("Enter number of edges: ");
        edgeCount = sc.nextInt();
        edges = new Edge[edgeCount];
        int src, dest, weight;
        System.out.println("enter src dest weight for " + edgeCount + " edges");
        for (int i = 0; i < edgeCount; i++) {
            src = sc.nextInt();
            dest = sc.nextInt();
            weight = sc.nextInt();
            mat[src][dest] = weight;
            edges[i] = new Edge(src, dest, weight);
        }
    }

    void display() {
        char inf = '#';
        for (int i = 0; i < vertCount; i++) {
            for (int j = 0; j < vertCount; j++) {
                if (mat[i][j] == INF)
                    System.out.print(inf + "\t");
                else
                    System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void bellmanFord(List<Edge> edges, int start, int[] dist) {
        // init dist of each vertex to INF & dist of start vertex to 0.
        for (int i = 0; i < dist.length; i++)
            dist[i] = INF;
        dist[start] = 0;
        // repeat V-1 times
        for (int i = 1; i < vertCount; i++) {
            for (Edge e : edges) {
                // update dist of dest vertex (if applicable)
                if (dist[e.src] != INF && (dist[e.src] + e.weight) < dist[e.dest])
                    dist[e.dest] = dist[e.src] + e.weight;
            }
        }
        // check if -ve cycle
        for (Edge e : edges) {
           if (dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest])
               throw new RuntimeException("Graph contains -ve weight cycle.");
        }
    }
    
    public int getMin(int[] dist, boolean[] spt) {
		int min = INF, minKeyVertex = 0;
		for (int i = 0; i < vertCount; i++) {
			if(spt[i] == false && dist[i] < min) {
				min = dist[i];
				minKeyVertex = i;
			}
		}
		return minKeyVertex;
	}

    public int[] dijkstra(int[][] mat, int start, int[] dist, int vertCount) {
		// mst array to check if vertex is in mst.
		boolean[] spt = new boolean[vertCount];
		// parent array to keep parent of each vertex
		int[] parent = new int[vertCount];
		// initially no vertex in mst, key of all vertices as INF and parent of each vertex = -1
		for(int i=0; i<vertCount; i++) {
			spt[i] = false;
			dist[i] = INF;
			parent[i] = -1;
		}
		// key of start vertex should be 0
		dist[start] = 0;
		
		int sptVertCount = 0;
		// repeat until all vertices are added into mst
		while(sptVertCount < vertCount) {
			// get vertex with min key, which is not in mst
			int u = getMin(dist, spt);
			// add it into mst
			spt[u] = true;
			sptVertCount++;
			// update key & parent of its adjacent vertices (which are not in mst)
			for (int v = 0; v < vertCount; v++) {
				if(mat[u][v] != INF && spt[v] == false && dist[u] + mat[u][v] < dist[v]) {
					dist[v] = dist[u] + mat[u][v];
					parent[v] = u;
				}
			}
		}
		
		// return min spanning tree edges
		return parent;
	}
    
    public int[][] johnson() {
    	int[] distFromNewVertex = new int[vertCount+1];
    	// copy existing edges from graph
    	List<Edge> edges = new ArrayList<>();
    	for (int i = 0; i < edgeCount; i++)
			edges.add(this.edges[i]);
    	int newVertex = vertCount;
    	// add edges to all vertices from new vertex (with weight=0)
    	for (int i = 0; i < vertCount; i++)
			edges.add(new Edge(newVertex, i, 0));
    	bellmanFord(edges, newVertex, distFromNewVertex);
    	System.out.println(Arrays.toString(distFromNewVertex));
    	// re-weight all edges in the graph and copy adj matrix
    	int[][] mat = new int[vertCount+1][vertCount+1];
    	for (int i = 0; i < mat.length; i++)
    		Arrays.fill(mat[i], INF);
    	for (Edge e : edges) {
			e.weight = e.weight + distFromNewVertex[e.src] - distFromNewVertex[e.dest]; 
			mat[e.src][e.dest] = e.weight;
//			System.out.println(e);
    	}
    	
		// apply Dijkstra on all vertices
    	int[][]dist = new int[vertCount][vertCount];
    	for(int s=0; s<vertCount; s++) {
    		dijkstra(mat, s, dist[s], vertCount);
    		// re-weight distances
    		for (int d = 0; d < vertCount; d++) {
    			if(dist[s][d] != INF)
    				dist[s][d] = dist[s][d] + distFromNewVertex[d] - distFromNewVertex[s];
    		}
    	}
    	return dist;
    }    
}


public class JohnsonMain {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int vertCount = sc.nextInt();
        Johnson g = new Johnson(vertCount);
        g.accept(sc);
        g.display();
        int[][] dist = g.johnson();
        for (int i = 0; i < dist.length; i++) {
        	System.out.print("dist from v"+i);
			for (int j = 0; j < dist.length; j++) {
				if(dist[i][j] != Johnson.INF)
					System.out.print("\t" + dist[i][j]);
				else
					System.out.print("\t#");
			}
			System.out.println();
		}
        sc.close();
	}
}

/*
Input:
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

