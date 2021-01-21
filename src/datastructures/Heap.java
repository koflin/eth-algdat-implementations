package datastructures;

class Heap {
	final static int MAX_HEAPSIZE = 100000;

	private int n = 0;
	// Stores the index of each key in the heap
	private Integer[] keyIndex = new Integer[MAX_HEAPSIZE];
	private HeapNode[] values = new HeapNode[MAX_HEAPSIZE];


	protected Heap () { 
	}    

	/**
	 * Returns true if a has a higher priority than b
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean isHigher(HeapNode a, HeapNode b) {
		return a.priority > b.priority;
	}

	/**
	 * Swaps two heap nodes
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		HeapNode tmp = values[i];
		
		// Swap key indexing
		keyIndex[values[j].key] = i;
		keyIndex[tmp.key] = j;
		
		// Swap values in heap
		values[i] = values[j];
		values[j] = tmp;
	}

	/**
	 * Searches a HeapNode by its key in O(1)
	 * @param key
	 * @return
	 */
	private HeapNode search(int key) {
		if (keyIndex[key] == null) {
			return null;
		}
		
		return values[keyIndex[key]];
	}
	
	/**
	 * Restores heap from i to m (top down) in O(log n)
	 * @param i
	 * @param m
	 */
	private void restoreHeapDownwards(int i, int m) {
		// In range
		while(2*i + 1 < m) {
			int j = 2 * i + 1;

			// Right child
			if (j + 1 < m) {
				// Right has higher priority
				if (isHigher(values[j + 1], values[j])) {
					j = j + 1;
				}
			}

			// Check if heap condition fullfilled
			if (isHigher(values[i], values[j])) {
				return;
			}

			swap(i, j);

			i = j;
		}
	}

	/**
	 * Restores heap from i to root (bottom up) in O(log n)
	 * @param i
	 */
	private void restoreHeapUpwards(int i) {
		int j = (i - 1) / 2;
		
		while(i > 0 && isHigher(values[i], values[j])) {
			swap(i, j);
			i = j;
			j = (i - 1)/2;
		}
	}
	
	/**
	 * Inserts a key with custom priority in O(log n)
	 * @param value
	 * @param priority
	 */
	public void insert(int key, double priority) {
		values[n] = new HeapNode(key, priority);

		restoreHeapUpwards(n);
		n++;
	}
	
	/**
	 * Changes/Inserts a key priority in O(log n)
	 * @param key
	 * @param priority
	 */
	public void changePriority(int key, double priority) {
		HeapNode node = search(key);
		
		if (node == null) {
			insert(key, priority);
		} else {
			double old = node.priority;
			node.priority = priority;
			
			// Check downwards
			if (old > priority) {
				restoreHeapDownwards(keyIndex[key], n);
			}
			// Check upwards
			else {
				restoreHeapUpwards(keyIndex[key]);
			}
		}
	}
	
	/**
	 * Extracts the value with the highest priority in O(log n)
	 */
	protected int extractFirst () {
		HeapNode value = values[0];

		values[0] = values[n - 1];
		values[n - 1] = null;
		n--;
		restoreHeapDownwards(0, n);
		return value.key;
	}

}
