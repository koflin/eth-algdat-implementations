package datastructures;

public class Edge implements Comparable<Edge> {
	public int from;
	public int to;
	public double weight;
	
	protected Edge(int from, int to) {
		this.from = from;
		this.to = to;
		this.weight = 1;
	}
	
	public Edge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		return (int)(this.weight - other.weight);
	}
}
