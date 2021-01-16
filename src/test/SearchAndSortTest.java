package test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import algorithms.SortAlgorithms;

public class SearchAndSortTest {
	int[] small = new int[] { 3, 7, 5, 1, 4 };
	int[] smallSorted = new int[] { 1, 3, 4, 5, 7 };
	
	int[] medium = new int[] { 9, 7, 3, 2, 1, 8, 4, 6 };
	int[] mediumSorted = new int[] { 1, 2, 3, 4, 6, 7, 8, 9 };
	
	int[] large = new int[] { 9, 7, 5, 11, 12, 2, 14, 3, 10, 6 };
	int[] largeSorted = new int[] { 2, 3, 5, 6, 7, 9, 10, 11, 12, 14 };
	
	@Test
	public void testBubbleSort() {
		assertArrayEquals(smallSorted, SortAlgorithms.bubbleSort(small.clone()));
		assertArrayEquals(mediumSorted, SortAlgorithms.bubbleSort(medium.clone()));
		assertArrayEquals(largeSorted, SortAlgorithms.bubbleSort(large.clone()));
	}
	
	@Test
	public void testSelectionSort() {
		assertArrayEquals(smallSorted, SortAlgorithms.selectionSort(small.clone()));
		assertArrayEquals(mediumSorted, SortAlgorithms.selectionSort(medium.clone()));
		assertArrayEquals(largeSorted, SortAlgorithms.selectionSort(large.clone()));
	}
	
	@Test
	public void testInsertionSort() {
		assertArrayEquals(smallSorted, SortAlgorithms.insertionSort(small.clone()));
		assertArrayEquals(mediumSorted, SortAlgorithms.insertionSort(medium.clone()));
		assertArrayEquals(largeSorted, SortAlgorithms.insertionSort(large.clone()));
	}
	
	@Test
	public void testHeapSort() {
		assertArrayEquals(smallSorted, SortAlgorithms.heapSort(small.clone()));
		assertArrayEquals(mediumSorted, SortAlgorithms.heapSort(medium.clone()));
		assertArrayEquals(largeSorted, SortAlgorithms.heapSort(large.clone()));
	}
	
	@Test
	public void testMergeSort() {
		assertArrayEquals(smallSorted, SortAlgorithms.mergeSort(small.clone()));
		assertArrayEquals(mediumSorted, SortAlgorithms.mergeSort(medium.clone()));
		assertArrayEquals(largeSorted, SortAlgorithms.mergeSort(large.clone()));
	}
	
	@Test
	public void testQuickSort() {
		assertArrayEquals(smallSorted, SortAlgorithms.quickSort(small.clone()));
		assertArrayEquals(mediumSorted, SortAlgorithms.quickSort(medium.clone()));
		assertArrayEquals(largeSorted, SortAlgorithms.quickSort(large.clone()));
	}
}
