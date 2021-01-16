package datastructures;

public class Node<T> {
	protected T value;
	protected Node<T> next;
	protected Node<T> previous;
	
	protected Node(T value) {
		this.value = value;
	}
}
