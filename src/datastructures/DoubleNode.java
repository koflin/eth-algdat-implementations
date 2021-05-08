package datastructures;

public class DoubleNode<T1, T2> {
	public T1 valueOne;
	public T2 valueTwo;
	public DoubleNode<T1, T2> next;
	public DoubleNode<T1, T2> previous;
	
	public DoubleNode(T1 valueOne, T2 valueTwo) {
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}
	
	public DoubleNode<T1, T2> setPrevious(DoubleNode<T1, T2> previous) {
		this.previous = previous;
		previous.next = this;
		return previous;
	}
}
