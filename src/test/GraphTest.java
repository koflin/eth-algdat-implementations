package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import algorithms.GraphAlgorithms;
import datastructures.DoubleNode;
import datastructures.Graph;

@TestMethodOrder(OrderAnnotation.class)
public class GraphTest {
	@Test
	@Order(1)
	public void test1DFS() {
		int[] result = GraphAlgorithms.depthFirstSearch(getDFSBFSExample());
		System.out.println("DFS");
		System.out.println(oneDtoString(result));
		assertArrayEquals(new int[] { 0, 1, 4, 5, 6, 7, 2, 3 }, result);
	}
	
	@Test
	@Order(2)
	public void test2BFS() {
		int[] result = GraphAlgorithms.breadthFirstSearch(getDFSBFSExample(), 0);
		System.out.println("BFS");
		System.out.println(oneDtoString(result));
		assertArrayEquals(new int[] { 0, 0, 0, 2, 1, 0, 5, 4 }, result);
	}
	
	@Test
	@Order(3)
	public void test3Dijkstra() {
		double[] result = GraphAlgorithms.dijkstra(getDinjkstraExample(), 0);
		System.out.println("Dijkstra");
		System.out.println(oneDtoString(result));
		assertArrayEquals(new double[] { 0d, 4d, 3d, 2d, 6d, 8d, 7d }, result, 0);
	}
	
	@Test
	@Order(4)
	public void test4BellmanFord() {
		double[] result = GraphAlgorithms.bellmanFord(getBellmanFordExample(), 0);
		System.out.println("Bellman-Ford");
		System.out.println(oneDtoString(result));
		assertArrayEquals(new double[] { 0d, 4d, 3d, 10d, 6d, -5d, 7d }, result, 0);
	}
	
	@Test
	@Order(5)
	public void test5Boruvka() {
		double result = GraphAlgorithms.boruvka(getMSTExample());
		System.out.println("Boruvka");
		System.out.println(result);
		assertEquals(10d, result, 0);
	}
	
	@Test
	@Order(6)
	public void test6Prim() {
		double result = GraphAlgorithms.prim(getMSTExample(), 0);
		System.out.println("Prim");
		System.out.println(result);
		assertEquals(10d, result, 0);
	}
	
	@Test
	@Order(7)
	public void test7Kruskal() {
		double result = GraphAlgorithms.kruskal(getMSTExample());
		System.out.println("Kruskal");
		System.out.println(result);
		assertEquals(10d, result, 0);
	}
	
	@Test
	@Order(8)
	public void test8FloydWarshall() {
		double[][] result = GraphAlgorithms.floydWarshall(getFloydWarshallExample());
		System.out.println("FloydWarshall");
		System.out.println(twoDtoString(result));
		assertArrayEquals(new double[] { 0, 2, 1, 1 }, result[0], 0);
		assertArrayEquals(new double[] { 3, 0, 2, 1 }, result[1], 0);
		assertArrayEquals(new double[] { 1, 1, 0, 2 }, result[2], 0);
		assertArrayEquals(new double[] { 2, 2, 1, 0 }, result[3], 0);
	}
	
	@Test
	@Order(9)
	public void test9Johnson() {
		double[][] result = GraphAlgorithms.johnson(getJohnsonExample());
		System.out.println("Johnson");
		System.out.println(twoDtoString(result));
		assertArrayEquals(new double[] { 0, -2, -1 }, result[0], 0);
		assertArrayEquals(new double[] { Double.MAX_VALUE, 0, 1 }, result[1], 0);
		assertArrayEquals(new double[] { Double.MAX_VALUE, Double.MAX_VALUE, 0 }, result[2], 0);
	}
	
	@SuppressWarnings("unchecked")
	private Graph getDFSBFSExample() {
		Graph g = new Graph();
		g.n = 8;
		g.m = 13;
		g.edges = new DoubleNode[] {
			// A ->
			new DoubleNode<Integer, Double>(5, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(2, 1d))
			.setPrevious(new DoubleNode<Integer, Double>(1, 1d)),
			// B ->
			new DoubleNode<Integer, Double>(4, 1d),
			// C ->
			new DoubleNode<Integer, Double>(3, 1d),
			// D ->
			new DoubleNode<Integer, Double>(7, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(0, 1d)),
			// E ->
			new DoubleNode<Integer, Double>(7, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(6, 1d))
			.setPrevious(new DoubleNode<Integer, Double>(5, 1d)),
			// F ->
			new DoubleNode<Integer, Double>(6, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(1, 1d)),
			// G ->
			null,
			// H ->
			new DoubleNode<Integer, Double>(6, 1d),
		};
		return g;
	}
	
	@SuppressWarnings("unchecked")
	private Graph getDinjkstraExample() {
		Graph g = new Graph();
		g.n = 7;
		g.m = 12;
		g.edges = new DoubleNode[] {
			// A ->
			new DoubleNode<Integer, Double>(1, 5d)
			.setPrevious(new DoubleNode<Integer, Double>(2, 3d))
			.setPrevious(new DoubleNode<Integer, Double>(3, 2d)),
			// B ->
			new DoubleNode<Integer, Double>(4, 3d),
			// C ->
			new DoubleNode<Integer, Double>(1, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(4, 3d)),
			// D ->
			new DoubleNode<Integer, Double>(2, 2d)
			.setPrevious(new DoubleNode<Integer, Double>(5, 8d)),
			// E ->
			new DoubleNode<Integer, Double>(6, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(5, 3d)),
			// F ->
			new DoubleNode<Integer, Double>(2, 1d),
			// G ->
			new DoubleNode<Integer, Double>(5, 1d)
		};
		return g;
	}
	
	@SuppressWarnings("unchecked")
	private Graph getBellmanFordExample() {
		Graph g = new Graph();
		g.n = 7;
		g.m = 12;
		g.edges = new DoubleNode[] {
			// A ->
			new DoubleNode<Integer, Double>(1, 5d)
			.setPrevious(new DoubleNode<Integer, Double>(2, 3d))
			.setPrevious(new DoubleNode<Integer, Double>(3, 10d)),
			// B ->
			new DoubleNode<Integer, Double>(4, 3d),
			// C ->
			new DoubleNode<Integer, Double>(1, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(4, 3d)),
			// D ->
			new DoubleNode<Integer, Double>(2, 2d)
			.setPrevious(new DoubleNode<Integer, Double>(5, -15d)),
			// E ->
			new DoubleNode<Integer, Double>(6, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(5, 3d)),
			// F ->
			new DoubleNode<Integer, Double>(2, 1d),
			// G ->
			new DoubleNode<Integer, Double>(5, 1d)
		};
		return g;
	}
	
	@SuppressWarnings("unchecked")
	private Graph getMSTExample() {
		Graph g = new Graph();
		g.n = 5;
		g.m = 12;
		g.edges = new DoubleNode[] {
			// A ->
			new DoubleNode<Integer, Double>(2, 4d)
			.setPrevious(new DoubleNode<Integer, Double>(1, 1d)),
			// B ->
			new DoubleNode<Integer, Double>(3, 3d)
			.setPrevious(new DoubleNode<Integer, Double>(0, 1d)),
			// C ->
			new DoubleNode<Integer, Double>(4, 2d)
			.setPrevious(new DoubleNode<Integer, Double>(3, 6d))
			.setPrevious(new DoubleNode<Integer, Double>(0, 4d)),
			// D ->
			new DoubleNode<Integer, Double>(4, 5d)
			.setPrevious(new DoubleNode<Integer, Double>(2, 6d))
			.setPrevious(new DoubleNode<Integer, Double>(1, 3d)),
			// E ->
			new DoubleNode<Integer, Double>(3, 5d)
			.setPrevious(new DoubleNode<Integer, Double>(2, 2d)),
		};
		return g;
	}
	
	@SuppressWarnings("unchecked")
	private Graph getFloydWarshallExample() {
		// TODO non-uniformed weights
		Graph g = new Graph();
		g.n = 4;
		g.m = 6;
		g.edges = new DoubleNode[] {
			// A ->
			new DoubleNode<Integer, Double>(3, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(2, 1d)),
			// B ->
			new DoubleNode<Integer, Double>(3, 1d),
			// C ->
			new DoubleNode<Integer, Double>(1, 1d)
			.setPrevious(new DoubleNode<Integer, Double>(0, 1d)),
			// D ->
			new DoubleNode<Integer, Double>(2, 1d),
		};
		return g;
	}
	
	@SuppressWarnings("unchecked")
	private Graph getJohnsonExample() {
		Graph g = new Graph();
		g.n = 3;
		g.m = 3;
		g.edges = new DoubleNode[] {
			// A ->
			new DoubleNode<Integer, Double>(2, -0.5d)
			.setPrevious(new DoubleNode<Integer, Double>(1, -2d)),
			// B ->
			new DoubleNode<Integer, Double>(2, 1d),
			// C ->
		};
		return g;
	}
	
	private String oneDtoString(int[] array) {
		return oneDtoString(Arrays.stream(array).asDoubleStream().toArray());
	}
	
	private String oneDtoString(double[] array) {
		String head = "";
		String separator = "";
		String body = "";
		
		for (int j = 0; j < array.length; j++) {
			head += j + "   ";
			separator += "----";
			body += (array[j] != Double.MAX_VALUE || array[j] != Integer.MAX_VALUE ? array[j] : "-") + " ";
		}
		
		return head + "\n" + separator + "\n" + body + "\n";
	}
	
	private String twoDtoString(double[][] array) {
		String head = "";
		String separator = "";
		String body = "";
		
		for (int i = 0; i < array.length; i++) {
			head += i + "   ";
			separator += "----";
			
			String row = "";
			for (int j = 0; j < array[i].length; j++) {
				row += (array[i][j] != Double.MAX_VALUE || array[i][j] != Integer.MAX_VALUE ? array[i][j] : "-") + " ";
			}
			
			body += row + "\n";
		}
		
		return head + "\n" + separator + "\n" + body + "\n";
	}
}
