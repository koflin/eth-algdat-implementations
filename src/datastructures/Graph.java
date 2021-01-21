package datastructures;

import java.util.Iterator;

public class Graph {
	// Vertex count
	public int n;
	// Edge count
	public int m;
	
	// Edges are stored in a adjacency list, double nodes contain the end vertex and the weight
	DoubleNode<Integer, Double>[] edges;
	
	public Graph() {
		
	}
	
	/**
	 * Returns all vertices of the graph in O(n)
	 * @return
	 */
	public Iterable<Integer> getAllVertices() {
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					
					int next = n > 0 ? 0 : -1;

					@Override
					public boolean hasNext() {
						return next != -1;
					}

					@Override
					public Integer next() {
						int vertex = next;
						
						next++;
						
						if (vertex == n) {
							next = -1;
						}
						
						return vertex;
					}
					
				};
			}
		};
	}
	
	/**
	 * Returns all edges of the graph in O(m)
	 * @return
	 */
	public Iterable<Edge> getAllEdges() {
		return new Iterable<Edge>() {
			@Override
			public Iterator<Edge> iterator() {
				return new Iterator<Edge>() {
					int u = getFirst();
					DoubleNode<Integer, Double> next = n > 0 ? edges[u] : null;
					
					@Override
					public boolean hasNext() {
						return next != null;
					}

					@Override
					public Edge next() {
						Edge e = new Edge(u, next.valueOne, next.valueTwo);
						next = next.next;
						
						if (next == null && u < n - 1) {
							u++;
							next = edges[u];
						}
						
						return e;
					}
					
				};
			}
		};
	}
	
	private int getFirst() {
		for (int u = 0; u < n; u++) {
			if (edges[u] != null) {
				return u;
			}
		}
		
		return 0;
	}
	
	/**
	 * Returns all successors of vertex u in O(deg+(u))
	 * @param u
	 * @return
	 */
	public Iterable<Edge> getSuccessors(int u) {
		return new Iterable<Edge>() {
			@Override
			public Iterator<Edge> iterator() {
				return new Iterator<Edge>() {
					DoubleNode<Integer, Double> next = edges[u];
					
					@Override
					public boolean hasNext() {
						return next != null;
					}

					@Override
					public Edge next() {
						Edge e = new Edge(u, next.valueOne, next.valueTwo);
						next = next.next;
						return e;
					}
					
				};
			}
		};
	}

	/**
	 * Returns edge if graph contains edge from u to v in O(deg+(u))
	 * @param u
	 * @param v
	 * @return
	 */
	public Edge getEdge(int u, int v) {
		for (Edge e : getSuccessors(u)) {
			if (e.to == v) {
				return e;
			}
		}
		
		return null;
	}
}