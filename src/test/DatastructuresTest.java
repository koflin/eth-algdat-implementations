package test;

import org.junit.Test;

import datastructures.AVLTree;

public class DatastructuresTest {
	@Test
	public void test1a() {
		AVLTree tree = new AVLTree();
		tree.insert(20);
		tree.insert(4);
		tree.insert(15);
		System.out.println(tree.toString());
	}
	
	@Test
	public void test2a() {
		AVLTree tree = new AVLTree();
		tree.insert(20);
		tree.insert(4);
		tree.insert(26);
		tree.insert(3);
		//tree.insert(9);
		//tree.insert(15);
		System.out.println(tree.toString());
	}
	
	@Test
	public void test3a() {
		AVLTree tree = new AVLTree();
		tree.insert(20);
		tree.insert(4);
		tree.insert(26);
		tree.insert(3);
		tree.insert(9);
		tree.insert(21);
		tree.insert(30);
		tree.insert(2);
		tree.insert(7);
		tree.insert(11);
		tree.insert(15);
		System.out.println(tree.toString());
	}
	
	@Test
	public void test1b() {
		AVLTree tree = new AVLTree();
		tree.insert(20);
		tree.insert(4);
		tree.insert(8);
		System.out.println(tree.toString());
	}
	
	@Test
	public void test2b() {
		AVLTree tree = new AVLTree();
		tree.insert(20);
		tree.insert(4);
		tree.insert(26);
		tree.insert(3);
		tree.insert(9);
		tree.insert(8);
		System.out.println(tree.toString());
	}
	
	@Test
	public void test3b() {
		AVLTree tree = new AVLTree();
		tree.insert(20);
		tree.insert(4);
		tree.insert(26);
		tree.insert(3);
		tree.insert(9);
		tree.insert(21);
		tree.insert(30);
		tree.insert(2);
		tree.insert(7);
		tree.insert(8);
		System.out.println(tree.toString());
	}
}
