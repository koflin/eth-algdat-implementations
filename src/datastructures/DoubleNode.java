package datastructures;

public class DoubleNode<T1, T2> {
	protected T1 valueOne;
	protected T2 valueTwo;
	protected DoubleNode<T1, T2> next;
	protected DoubleNode<T1, T2> previous;
	
	protected DoubleNode(T1 valueOne, T2 valueTwo) {
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}
}
