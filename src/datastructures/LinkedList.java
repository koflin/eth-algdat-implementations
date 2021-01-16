package datastructures;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
	private int size;
	private Node<T> first;
	private Node<T> last;
	
	public int size() {
		return this.size;
	}
	
	public LinkedList() {
		this.size = 0;
		this.first = null;
		this.last = null;
	}
	
	public void add(T element) {
		Node<T> node = new Node<T>(element);
		
		if (isEmpty()) {
			first = node;
			last = first;
		} else {
			last.next = node;
		}
		
		this.size++;
	}
	
	public T get(int index) {
		if (index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		int current = 0;
		for (T value : this) {
			if (current == index) {
				return value;
			}
			current++;
		}
		
		return null;
	}
	
	public T removeFirst() {
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		
		T value = this.first.value;
		this.first = this.first.next;
		size--;
		
		return value;
	}
	
	public void clear() {
		this.size = 0;
		this.first = null;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			protected Node<T> next = first;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				T value = next.value;
				next = next.next;
				return value;
			}
		};
	}
}
