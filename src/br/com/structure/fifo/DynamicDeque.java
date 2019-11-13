package br.com.structure.fifo;

import br.com.structure.fifo.node.QueueNode;

public class DynamicDeque<T> {
	private QueueNode<T> cursor;
	private int size;

	public DynamicDeque() {
		cursor = null;
		size = 0;
	}

	public boolean isEmpty() {
		return cursor == null;
	}

	public int size() {
		return size;
	}
	
	private QueueNode<T> loopUntilIndex(int finalIndex, QueueNode<T> node){
		return loopUntilIndex(node, 0, finalIndex);
	}
	
	private QueueNode<T> loopUntilIndex(QueueNode<T> node, int actualIndex, int finalIndex){
		if(actualIndex < finalIndex) {
			return loopUntilIndex(node.getNext(), actualIndex +1, finalIndex);
		} else {
			return node;
		}
	}
		
	private QueueNode<T> loopUntilLast(QueueNode<T> node){
		if(node.getNext() != null) {
			return loopUntilLast(node.getNext());
		} else {
			return node;
		}
	}

	public void setBegin(T value) {
		if (isEmpty()) {
			setFirstTime(value);
		} else {
			QueueNode<T> newNode = new QueueNode<T>(value);

			newNode.setNext(cursor);

			cursor.setPrevious(newNode);

			cursor = newNode;
		}

		size++;
	}

	public void setEnd(T value) {
		if (isEmpty()) {
			setFirstTime(value);
		} else {
			QueueNode<T> actualNode = loopUntilLast(cursor);
			
			QueueNode<T> newNode = new QueueNode<T>(value);
			
			newNode.setPrevious(actualNode);
			
			actualNode.setNext(newNode);
		}
		
		size++;
	}
	
	private void setFirstTime(T value){
		cursor = new QueueNode<T>(value);
	}

	public void removeBegin() {
		if (!isEmpty()) {
			cursor = cursor.getNext();
			
			if(cursor != null) {
				cursor.setPrevious(null);
			}
			size--;
		}
	}
	
	public void removeLast() {
		if (!isEmpty()) {
			QueueNode<T> actualNode = cursor;

			QueueNode<T> previousNode = actualNode;

			while (actualNode.getNext() != null) {
				previousNode = actualNode;
				actualNode = actualNode.getNext();
			}

			if (actualNode.equals(previousNode)) {
				cursor = null;
			} else {
				previousNode.setNext(null);
			}

			size--;
		}
	}

	public T get(int index) throws Exception {
		if(index < 0 || index > size() -1) {
			throw new Exception("Invalid index");
		} else if (isEmpty()) {
			return null;
		} else {
			QueueNode<T> actualNode = loopUntilIndex(index, cursor);
			
			return actualNode.getValue();
		}
	}

	public void showAll() {
		QueueNode<T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}
}