package datastructures;

public class MinHeap extends Heap {
	public MinHeap() {
		super();
	}
	
	/**
	 * Extracts min key in O(log n)
	 * @return
	 */
	public int extractMin() {
		return this.extractFirst();
	}
	
	/**
	 * Inserts key in O(log n)
	 * @param key
	 */
	public void insert(int key) {
		insert(key, key);
	}

	@Override
	public void insert(int key, double priority) {
		super.insert(key, -priority);
	}

	@Override
	public void changePriority(int key, double priority) {
		super.changePriority(key, -priority);
	}
}
