package algorithms;


public class SearchAlgorithms {
	public static int binarySearch(int[] array, int b) {
		int left = 0;
		int right = array.length - 1;
		
		while(left <= right) {
			int middle = Math.floorDiv(right + left, 2);
			
			if (b == array[middle]) {
				return middle;
			} else if (b < array[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		
		return -(left + 1);
	}
	
	public static int binarySearch(int[] array, int from, int to, int b) {
		int left = from;
		int right = to;
		
		while(left <= right) {
			int middle = Math.floorDiv(right + left, 2);
			
			if (b == array[middle]) {
				return middle;
			} else if (b < array[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		
		return -(left + 1);
	}
	
	public static int linearSearch(int[] array, int b) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == b) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int maxIndex(int[] array) {
		return maxIndex(array, 0, array.length);
	}
	
	public static int maxIndex(int[] array, int from, int to) {
		int max = from;
		
		for (int i = from + 1; i < to + 1; i++) {
			if (array[i] > array[max]) {
				max = i;
			}
		}
		
		return max;
	}
	
	public static int minIndex(int[] array) {
		int min = 0;
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[min]) {
				min = i;
			}
		}
		
		return min;
	}
}
