package br.com.structure.fifo.node;

public class QueueNode<T> {
	private T value;
	private QueueNode<T> previous, next;

	public QueueNode(T value) {
		setValue(value);
		setNext(null);
		setPrevious(null);
	}

	public QueueNode<T> getNext() {
		return next;
	}

	public void setNext(QueueNode<T> next) {
		this.next = next;
	}

	public QueueNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(QueueNode<T> previous) {
		this.previous = previous;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
