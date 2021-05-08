package algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import datastructures.DoubleNode;
import datastructures.Edge;
import datastructures.Graph;
import datastructures.MinHeap;
import datastructures.UnionFind;
import exceptions.NegativeCycleException;

public class GraphAlgorithms {
	/**
	 * Returns the topological order of the vertices with DFS in O(n + m)
	 * @return
	 */
	public static int[] depthFirstSearch(Graph g) {
		int[] topOrder = new int[g.n];
		boolean[] marked = new boolean[g.n];
		int counter = 0;
		
		for(int v : g.getAllVertices()) {
			if (!marked[v]) {
				counter = dfsVisit(g, v, topOrder, marked, counter);
			}
		}
		
		return topOrder;
	}
	
	private static int dfsVisit(Graph g, int v, int[] topOrder, boolean[] marked, int counter) {
		marked[v] = true;
		
		topOrder[counter] = v;
		counter++;
		
		// Pre number
		for(Edge e : g.getSuccessors(v)) {
			if (!marked[e.to]) {
				counter = dfsVisit(g, e.to, topOrder, marked, counter);
			}
		}
		// Post number
		
		return counter;
	}

	/**
	 * Returns the parent array for each vertex of BFS tree in O(n + m)
	 * @param g
	 * @param s
	 * @return
	 */
	public static int[] breadthFirstSearch(Graph g, int s) {
		int[] par = new int[g.n];
		Arrays.fill(par, Integer.MAX_VALUE);
		par[0] = 0;
		
		boolean[] marked = new boolean[g.n];
		// Queue of vertices to visit
		LinkedList<Integer> S = new LinkedList<Integer>();
		S.add(s);
		
		while(S.size() > 0) {
			int v = S.removeLast();
			
			if (!marked[v]) {
				marked[v] = true;
				
				for (Edge e : g.getSuccessors(v)) {
					S.addFirst(e.to);
					
					if (par[e.to] == Integer.MAX_VALUE) {
						par[e.to] = v;
					}
				}
			}
		}
		
		return par;
	}
	
	/**
	 * Returns the shortest paths from s to any on a non-negative weighted DAG with Dijkstra in O((m + n) * log n)
	 * @param g
	 * @param s
	 * @return
	 */
	public static double[] dijkstra(Graph g, int s) {
		// Set of vertices visited
		HashSet<Integer> S = new HashSet<Integer>();
		// Priority queue containing vertices distances
		MinHeap H = new MinHeap();
		H.insert(s, 0);
		// Distance array
		double[] d = new double[g.n];
		Arrays.fill(d, Double.MAX_VALUE);
		d[0] = 0;
		
		while(S.size() != g.n) {
			int u = H.extractMin();
			System.out.println(H);
			S.add(u);
			
			for (Edge e : g.getSuccessors(u)) {
				int v = e.to;
				
				if (!S.contains(v)) {
					if (d[v]  > d[u]  + e.weight) {
						d[v] = d[u] + e.weight;
						H.changePriority(v, d[v]);
					}
				}
			}
		}
		
		return d;
	}
	
	/**
	 * Returns the shortest paths from s to any on an arbitrary weighted DAG with Bellman-Ford in O(m * n)
	 * @param g
	 * @param s
	 * @return
	 */
	public static double[] bellmanFord(Graph g, int s) {
		double[] d = new double[g.n];
		Arrays.fill(d, Double.MAX_VALUE);
		d[0] = 0;
		
		for (int i = 0; i < g.n - 1; i++) {
			for (Edge e : g.getAllEdges()) {
				int v = e.from;
				int u = e.to;
				
				if (d[u] > d[v] + e.weight) {
					d[u] = d[v] + e.weight;
				}
			}
		}
		
		for (Edge e : g.getAllEdges()) {
			int v = e.from;
			int u = e.to;
			
			if (d[u] > d[v] + e.weight) {
				throw new NegativeCycleException();
			}
		}
		
		return d;
	}
	
	/**
	 * Returns the MST of a non-negative weighted UG with Boruvka in O((n + m) * log n)
	 * @param g
	 * @return
	 */
	public static double boruvka(Graph g) {
		UnionFind components = new UnionFind(g.n);
		
		double sum = 0;
		while(components.size != 1) {
			int[][] componentArray = components.getComponents();
			
			HashSet<Edge> minEdges = new HashSet<Edge>();
			
			// Iterate through all components
			for (int i = 0; i < componentArray.length; i++) {
				// Min edge of component
				Edge min = null;
				
				// Iterate through every vertex of the component
				for (int j = 0; j < componentArray[i].length; j++) {
					int v = componentArray[i][j];
					
					// Iterate through every outgoing edge of the component
					for (Edge e : g.getSuccessors(v)) {
						// Different component
						if (!components.same(v, e.to)) {
							if (min == null || min.weight > e.weight) {
								min = e;
							}
						}
					}
				}
				
				minEdges.add(min);
			}
			
			// Union min component edges
			for (Edge e : minEdges) {
				components.union(e.from, e.to);
				sum += e.weight;
			}
		}
		
		return sum;
	}
	
	/**
	 * Returns the MST of a non-negative weighted UG with Prim starting on s in O((n + m) * log n)
	 * @param g
	 * @param s
	 * @return
	 */
	public static double prim(Graph g, int s) {
		// Set of vertices visited
		HashSet<Integer> S = new HashSet<Integer>();
		// Priority queue containing vertices distances
		MinHeap H = new MinHeap();
		H.insert(s, 0);
		// Distance array
		double[] d = new double[g.n];
		Arrays.fill(d, Double.MAX_VALUE);
		d[0] = 0;
		
		while(S.size() != g.n) {
			int u = H.extractMin();
			S.add(u);
			
			for (Edge e : g.getSuccessors(u)) {
				int v = e.to;
				
				if (!S.contains(v)) {
					if (d[v] > e.weight) {
						d[v] = e.weight;
						H.changePriority(v, d[v]);
					}
				}
			}
		}
		
		double sum = 0;
		
		for(int i = 0; i < g.n; i++) {
			sum += d[i];
		}
		
		return sum;
	}
	
	/**
	 * Returns the MST of a non-negative weighted UG with Kruskal starting on s in O(n * log n + m * log m)
	 * @param g
	 * @return
	 */
	public static double kruskal(Graph g) {
		Edge[] edges = new Edge[g.m];
		
		int i = 0;
		for (Edge e : g.getAllEdges()) {
			edges[i] = e;
			i++;
		}
		
		Arrays.sort(edges);
		
		UnionFind components = new UnionFind(g.n);
		double sum = 0;
		
		for (int j = 0; j < edges.length; j++) {
			Edge e = edges[j];
			
			if (!components.same(e.from, e.to)) {
				components.union(e.from, e.to);
				
				sum += e.weight;
			}
		}
		
		return sum;
	}
	
	/**
	 * Returns the shortest paths from any to any on an arbitrary weighted DAG with Floyd-Warshall in O(n^3)
	 * @param g
	 * @return
	 */
	public static double[][] floydWarshall(Graph g) {
		double[][][] d = new double[g.n + 1][g.n][g.n];
		
		// Initialization
		for (int u = 0; u < g.n; u++) {
			for (int v = 0; v < g.n; v++) {
				if (u == v) {
					d[0][u][v] = 0;
				} else {
					Edge e = g.getEdge(u, v);
					
					if (e == null) {
						d[0][u][v] = Double.MAX_VALUE;
					} else {
						d[0][u][v] = e.weight;
					}
				}
			}
		}
		
		// DP
		for (int i = 1; i < g.n + 1; i++) {
			for (int u = 0; u < g.n; u++) {
				for (int v = 0; v < g.n; v++) {
					d[i][u][v] = min(d[i - 1][u][v], d[i - 1][u][i - 1] + d[i - 1][i - 1][v]);
				}
			}
		}
		
		// Detect negative cycles
		for (int u = 0; u < g.n; u++) {
			if (d[g.n + 1][u][u] != 0) {
				throw new NegativeCycleException();
			}
		}
		
		return d[g.n + 1];
	}

	/**
	 * Returns the shortest paths from any to any on an arbitrary weighted DAG with Johnson's Algorithm in O(n * m + n^2 * log n)
	 * @param g
	 * @return
	 */
	public static double[][] johnson(Graph g) {
		double[][] d = new double[g.n][g.n];
		
		Graph modified = new Graph();
		modified.n = g.n + 1;
		modified.m = g.m + g.n;
		
		@SuppressWarnings("unchecked")
		DoubleNode<Integer, Double>[] newEdges = new DoubleNode[modified.n];
		
		// Copy old edges
		for (int u : g.getAllVertices()) {
			for (Edge e : g.getSuccessors(u)) {
				DoubleNode<Integer, Double> newHead = new DoubleNode<Integer, Double>(e.to, e.weight);
				
				newHead.next = newEdges[u];
				newEdges[u] = newHead;
			}
		}
		
		// Insert zero edges
		for (int v = 0; v < g.n; v++) {
			DoubleNode<Integer, Double> newHead = new DoubleNode<Integer, Double>(v, 0d);
			
			newHead.next = newEdges[modified.n - 1];
			newEdges[modified.n - 1] = newHead;
		}
		
		modified.edges = newEdges;
		
		double[] h = new double[g.n];
		
		// Perform Bellman-Ford for height
		double[] distances = bellmanFord(modified, modified.n - 1);
		
		for (int v = 0; v < g.n; v++) {
			h[v] = distances[v];
		}
		
		// Change weights
		for (Edge e : modified.getAllEdges()) {
			e.weight = e.weight + h[e.from] - h[e.to];
		}
		
		// Perform n x dijkstra
		for (int v = 0; v < g.n; v++) {
			d[v] = dijkstra(modified, v);
			
			// Change weights to original
			for (int u = 0; u < g.n; u++) {
				d[v][u] = d[v][u] - h[u] + h[v];
			}
		}
		
		return d;
	}
	
	public static double min(double... numbers) {
		double min = numbers[0];
		
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] < min) {
				min = numbers[i];
			}
		}
		
		return min;
	}
}
