package algorithms;

public class SortAlgorithms {
	public static boolean isSorted(int array[]) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int[] bubbleSort(int[] array) {
		
		for (int j = 0; j < array.length - 1; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					// Swap elements
					int temp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = temp;
				}
			}
		}
		
		return array;
	}
	
	public static int[] selectionSort(int[] array) {
		
		for (int k = array.length - 1; k > 0; k--) {
			// Get max in subarray
			int i = SearchAlgorithms.maxIndex(array, 0, k);
			// Swap elements
			int temp = array[i];
			
			array[i] = array[k];
			array[k] = temp;
		}
		
		return array;
	}
	
	public static int[] insertionSort(int[] array) {
		for (int k = 0; k < array.length - 1; k++) {
			int i = Math.abs(SearchAlgorithms.binarySearch(array, 0, k, array[k + 1]) + 1);
			
			int b = array[k + 1];
			
			for (int j = k; j >= i; j--) {
				array[j + 1] = array[j];
			}
			
			array[i] = b;
		}
		
		return array;
	}
	
	public static int[] heapSort(int[] array) {
		int n = array.length;
		// Create heap
		for (int i = Math.floorDiv(n, 2); i >= 0; i--) {
			restoreHeapCondition(array, i, n - 1);
		}
		
		for (int m = n - 1; m >= 1; m--) {
			//Put max of rest to m-th place
			int temp = array[0];
			array[0] = array[m];
			array[m] = temp;
			
			restoreHeapCondition(array, 0, m - 1);
		}
		
		return array;
	}
	
	private static void restoreHeapCondition(int[] array, int i, int m) {
		// Go down tree
		while(2 * i + 1 <= m) {
			int j = 2 * i + 1;
			
			// Has right child
			if (j + 1 <= m) {
				// Right child greater
				if (array[j] < array[j + 1]) {
					j = j + 1;
				}
			}
			
			// Heap condition fullfilled
			if (array[i] >= array[j]) {
				return;
			}
			
			// Switch i and j
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		
			i = j;
		}
	}
	
	public static int[] mergeSort(int[] array) {
		return mergeSort(array, 0, array.length - 1);
	}
	
	private static int[] mergeSort(int[] array, int left, int right) {
		if (left < right) {
			int middle = Math.floorDiv(left + right, 2);
			mergeSort(array, left, middle);
			mergeSort(array, middle + 1, right);
			merge(array, left, middle, right);
		}
		
		return array;
	}
	
	private static void merge(int[] array, int left, int middle, int right) {
		int[] b = new int[right - left + 1];
		int i = left;
		int j = middle + 1;
		int k = 0;
		
		// Add smallest of left and right array
		while(i <= middle && j <= right) {
			if (array[i] <= array[j]) {
				b[k] = array[i];
				i++;
			} else {
				b[k] = array[j];
				j++;
			}
			
			k++;
		}
		
		// left empty
		if (i > middle) {
			while (j <= right) {
				b[k] = array[j];
				j++;
				k++;
			}
		}
		// right empty
		else {
			while (i <= middle) {
				b[k] = array[i];
				i++;
				k++;
			}
		}
		
		// copy b back to a
		for (int l = 0; l < b.length; l++) {
			array[left + l] = b[l];
		}
	}
	
	public static int[] quickSort(int[] array) {
		return quickSort(array, 0, array.length - 1);
	}
	
	private static int[] quickSort(int[] array, int l, int r) {
		if (l < r) {
			int k = partition(array, l, r);
			quickSort(array, l, k - 1);
			quickSort(array, k + 1, r);
		}
		
		return array;
	}
	
	private static int partition(int[] array, int l , int r) {
		int i = l;
		int j = r - 1;
		int p = array[r];
		
		do {
			// Left bound
			while (i < r && array[i] < p) {
				i++;
			}
			
			// Right bound
			while (j > l && array[j] > p) {
				j--;
			}
			
			if (i < j) {
				// Swap i and j
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		} while (i < j);
		
		// Swap i and r
		int temp = array[i];
		array[i] = array[r];
		array[r] = temp;
		
		return i;
	}
}
