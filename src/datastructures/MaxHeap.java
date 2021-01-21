package datastructures;

public class MaxHeap extends Heap {
	
	public MaxHeap() {
		super();
	}
	
	/**
	 * Extracts max value in O(log n)
	 * @return
	 */
	public int extractMax() {
		return this.extractFirst();
	}
	
	/**
	 * Inserts key in O(log n)
	 * @param key
	 */
	public void insert(int key) {
		super.insert(key, key);
	}
}
