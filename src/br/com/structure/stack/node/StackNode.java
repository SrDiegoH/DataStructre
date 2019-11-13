package br.com.structure.stack.node;

public class StackNode<T> {
	private T value;
	private StackNode<T> previous, next;

	public StackNode(T value) {
		setValue(value);
		setNext(null);
		setPrevious(null);
	}

	public StackNode<T> getNext() {
		return next;
	}

	public void setNext(StackNode<T> next) {
		this.next = next;
	}

	public StackNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(StackNode<T> previous) {
		this.previous = previous;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
