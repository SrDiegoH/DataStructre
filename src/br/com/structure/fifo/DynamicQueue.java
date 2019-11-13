package br.com.structure.fifo;

import br.com.structure.fifo.node.QueueNode;

public class DynamicQueue<T> {
	private QueueNode<T> cursor;
	private int size;

	public DynamicQueue() {
		cursor = null;
		size = 0;
	}

	public boolean isEmpty() {
		return cursor == null;
	}

	public int size() {
		return size;
	}
	
	private QueueNode<T> loopUntilLast(QueueNode<T> node){
		if(node.getNext() != null) {
			return loopUntilLast(node.getNext());
		} else {
			return node;
		}
	}

	public void put(T value) {
		if (isEmpty()) {
			cursor = new QueueNode<T>(value);
		} else {
			QueueNode<T> actualNode = loopUntilLast(cursor);
			
			QueueNode<T> newNode = new QueueNode<T>(value);
			
			newNode.setPrevious(actualNode);
			
			actualNode.setNext(newNode);
		}
		
		size++;
	}

	public T pull() {
		if (!isEmpty()) {
			T value = cursor.getValue();
			
			cursor = cursor.getNext();
			
			if(cursor != null) {
				cursor.setPrevious(null);
			}
			
			size--;
			
			return value;
		}
		
		return null;
	}

	public T peek() {
		if(!isEmpty()) {
			return cursor.getValue();
		} else {
			return null;
		}
	}

	public void showAll() {
		QueueNode<T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}
	
    public static void main (String[] args) throws Exception {
        DynamicQueue<Integer> fila = new DynamicQueue<Integer>();
		fila.pull();
		fila.put(5);
		fila.pull();
		fila.put(7);
		fila.put(4);
		fila.put(1);
		fila.put(3);
		fila.pull();
		fila.pull();
		fila.pull();
		System.out.println(fila.isEmpty());
		fila.put(9);
		System.out.println(fila.peek());
		fila.put(15);
		System.out.println(fila.size());
		fila.showAll();
    }
}

