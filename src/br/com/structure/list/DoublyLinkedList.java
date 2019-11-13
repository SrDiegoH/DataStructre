package br.com.structure.list;

import br.com.structure.list.node.DoubleNode;

public class DoublyLinkedList<T> {
	private DoubleNode<T> cursor;
	private int size;

	public DoublyLinkedList() {
		cursor = null;
		size = 0;
	}

	public boolean isEmpty() {
		return cursor == null;
	}

	public int size() {
		return size;
	}
	
	private DoubleNode<T> loopUntilIndex(int finalIndex, DoubleNode<T> node){
		return loopUntilIndex(node, 0, finalIndex);
	}
	
	private DoubleNode<T> loopUntilIndex(DoubleNode<T> node, int actualIndex, int finalIndex){
		if(actualIndex < finalIndex) {
			return loopUntilIndex(node.getNext(), actualIndex +1, finalIndex);
		} else {
			return node;
		}
	}
	
	private DoubleNode<T> loopUntilLast(DoubleNode<T> node){
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
			DoubleNode<T> newNode = new DoubleNode<T>(value);

			newNode.setNext(cursor);

			cursor.setPrevious(newNode);

			cursor = newNode;
		}

		size++;
	}

	public void setMiddle(int index, T value) {
		if (isEmpty() || index <= 0) {
			setBegin(value);
		} else if (index > 0 && index < size() -1) {
			DoubleNode<T> actualNode = loopUntilIndex(index, cursor);
			
			DoubleNode<T> newNode = new DoubleNode<T>(value);

			newNode.setPrevious(actualNode.getPrevious());

			newNode.setNext(actualNode);

			actualNode.getPrevious().setNext(newNode);

			newNode.getNext().setPrevious(newNode);

			size++;
		} else if (index >= size() -1) {
			set(value);
		}
	}

	public void set(T value) {
		if (isEmpty()) {
			setFirstTime(value);
		} else {
			DoubleNode<T> actualNode = loopUntilLast(cursor);
			
			DoubleNode<T> newNode = new DoubleNode<T>(value);
			
			newNode.setPrevious(actualNode);
			
			actualNode.setNext(newNode);
		}
		
		size++;
	}
	
	private void setFirstTime(T value){
		cursor = new DoubleNode<T>(value);
	}

	public void update(int index, T value) {
		DoubleNode<T> actualNode = loopUntilIndex(index, cursor);
		
		actualNode.setValue(value);
	}

	public void exchangeValuesAt(int firstPosition, int secondPosition) {
		DoubleNode<T> firstNode = loopUntilIndex(firstPosition, cursor);
	
		DoubleNode<T> secondNode = loopUntilIndex(secondPosition, cursor);
		
		T firstValue = firstNode.getValue();
		
		firstNode.setValue(secondNode.getValue());
		
		secondNode.setValue(firstValue);
	}

	public void removeFirst() {
		if (!isEmpty()) {
			cursor = cursor.getNext();
			
			if(cursor != null) {
				cursor.setPrevious(null);
			}
			size--;
		}
	}

	public void remove(int index) {
		if(!isEmpty()){
			if (index == 0) {
				removeFirst();
			} else if (index > 0 && index < size() -1) {
				DoubleNode<T> actualNode =  loopUntilIndex(index, cursor);
				
				actualNode.getPrevious().setNext(actualNode.getNext());
				
				if (actualNode.getNext() != null) {
					actualNode.getNext().setPrevious(actualNode.getPrevious());
				}
				
				size--;
			} else if (index == size()) {
				removeLast();
			}
		}
	}

	public void removeLast() {
		if (!isEmpty()) {
			DoubleNode<T> actualNode = cursor;

			DoubleNode<T> previousNode = actualNode;

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
			DoubleNode<T> actualNode = loopUntilIndex(index, cursor);
			
			return actualNode.getValue();
		}
	}

	public Integer getIndex(T value) {
		DoubleNode<T> actualNode = cursor;

		for (int i = 0; i < size() -1; i++) {
			if(actualNode.getValue().equals(value)) {
				return i;
			}
				
			actualNode = actualNode.getNext();
		}

		return null;
	}

	public void showAll() {
		DoubleNode<T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}

	public static void main(String[] args) throws Exception {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("index(" + i + ") = " + (i*5));
			list.set((i * 5) + "");
		}
		
		System.out.println("---------------------------------------");
		
		list.removeFirst();
		list.showAll();
		
		System.out.println("---------------------------------------");
		
		list.remove(7);
		list.showAll();
		
		System.out.println("---------------------------------------");
		
		list.removeLast();
		list.showAll();
		
		System.out.println("---------------------------------------");
		
		System.out.println("Size: " + list.size());
		System.out.println(list.get(list.size() -1));
		
		System.out.println("---------------------------------------");
		
		System.out.println(list.get(5));
		System.out.println(list.getIndex("25"));
		
		System.out.println("---------------------------------------");
		
		list.setMiddle(5, "100");
		list.showAll();
		
		System.out.println("---------------------------------------");
		
		list.update(5, "200");
		list.showAll();
		
		System.out.println("---------------------------------------");
		
		list.exchangeValuesAt(5, 7);
		list.showAll();
	}
}
