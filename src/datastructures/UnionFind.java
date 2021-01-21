package datastructures;

import java.util.HashSet;

public class UnionFind {
	
	public int size;
	Component[] component;
	
	/**
	 * Creates components for size vertices in O(n)
	 * @param size
	 */
	public UnionFind(int size) {
		this.size = size;
		component = new Component[size];
		
		for (int i = 0; i < size; i++) {
			component[i] = new Component(i);
		}
	}
	
	/**
	 * Checks if u and v are in the same component in O(1)
	 * @param u
	 * @param v
	 * @return
	 */
	public boolean same(int u, int v) {
		return component[u] == component[v];
	}
	
	/**
	 * Unites the component of u and v in O(log n) amortized over whole graph
	 * @param u
	 * @param v
	 */
	public void union(int u, int v) {
		if (!same(u, v)) {
			Component big = component[u];
			Component small = component[v];
			
			if (big.size < small.size) {
				big = component[v];
				small = component[u];
			}
			
			Node<Integer> current = small.head;
			
			while(current != null) {
				component[current.value] = big;
				
				current = current.next;
			}
			
			big.merge(small);
			
			size--;
		}
	}

	/**
	 * Gets all components in O(n)
	 * @return
	 */
	public int[][] getComponents() {
		HashSet<Component> set = new HashSet<Component>();
		
		for (int i = 0; i < component.length; i++) {
			set.add(component[i]);
		}
		
		int[][] all = new int[set.size()][];
		
		int i = 0;
		for (Component c : set) {
			all[i] = c.toArray();
			i++;
		}
		
		return all;
	}
}

class Component {
	int size;
	Node<Integer> head;
	Node<Integer> tail;
	
	Component(int v) {
		this.size = 1;
		this.head = new Node<Integer>(v);
		this.tail = head;
	}
	
	void merge(Component other) {
		this.size += other.size;
		this.tail.next = other.head;
		this.tail = other.tail;
	}
	
	int[] toArray() {
		int[] array = new int[size];
		Node<Integer> current = head;
		
		for (int i = 0; i < size; i++) {
			array[i] = current.value;
			current = current.next;
		}
		
		return array;
	}
}