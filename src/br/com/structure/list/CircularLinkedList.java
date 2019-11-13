package br.com.structure.list;

import br.com.structure.list.node.DoubleNode;

public class CircularLinkedList<T> {
	private DoubleNode<T> cursor;
	private int size = 0;

	public CircularLinkedList() {
		cursor = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void set(T value) {
		if (isEmpty()) {
			cursor = new DoubleNode<T>(value);
			cursor.setPrevious(cursor);
			cursor.setNext(cursor);
		} else {
			DoubleNode<T> newNode = new DoubleNode<T>(value);
			newNode.setPrevious(cursor);
			newNode.setNext(cursor.getNext());

			cursor.getNext().setPrevious(newNode);
			cursor.setNext(newNode);

			cursor = newNode;
		}

		size ++;
	}

	public T get(int index) {
		DoubleNode<T> actualNode = cursor.getNext();

		for (int i = 0; i < index; i++) {
			actualNode = actualNode.getNext();
		}
		
		return actualNode.getValue();
	}

	public int getIndex(T value) {
		DoubleNode<T> actualNode = cursor.getNext();

		int index = 0;
		do {
			if (actualNode.getValue().equals(value)) {
				break;
			}
			
			actualNode = actualNode.getNext();
			
			index ++;
		} while (actualNode != cursor.getNext());

		return index;
	}

	public void removeFirst() {
		if (!isEmpty()) {
			if (size == 1) {
				cursor = null;
			} else {
				DoubleNode<T> node = cursor.getNext().getNext();
				
				node.setPrevious(cursor);
				
				cursor.setNext(node);
			}
			
			size --;
		}
	}

	public void remove(int index) {
		if (!isEmpty()) {
			if (size == 1) {
				cursor = null;
			} else {
				DoubleNode<T> actualNode = cursor.getNext();
	
				for (int i = 0; i < index; i++) {
					actualNode = actualNode.getNext();
				}
				
				actualNode.getPrevious().setNext(actualNode.getNext());
				
				actualNode.getNext().setPrevious(actualNode.getPrevious());
			}
			
			size --;
		}
	}

	public void removeLast() {
		if (!isEmpty()) {
			if (size == 1) {
				cursor = null;
			} else {
				cursor.getPrevious().setNext(cursor.getNext());
				cursor.getNext().setPrevious(cursor.getPrevious());
				cursor = cursor.getPrevious();
			}
			
			size --;
		}
	}

	public void showAllFoward() {
		if (!isEmpty()) {
			DoubleNode<T> actualNode = cursor.getNext();

			do {
				System.out.println(actualNode.getValue());
				actualNode = actualNode.getNext();
			} while (actualNode != cursor.getNext());
		}
	}

	public void showAllBackward() {
		if (!isEmpty()) {
			DoubleNode<T> actualNode = cursor;

			do {
				System.out.println(actualNode.getValue());
				actualNode = actualNode.getPrevious();
			} while (actualNode != cursor);
		}
	}

	public static void main(String[] args) {
		CircularLinkedList<String> list = new CircularLinkedList<String>();
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("index(" + i + ") = " + (i*5));
			list.set((i * 5) + "");
		}
		
		System.out.println("---------------------------------------");
		
		list.removeFirst();
		list.showAllFoward();
		
		System.out.println("---------------------------------------");
		
		list.remove(7);
		list.showAllFoward();
		
		System.out.println("---------------------------------------");
		
		list.removeLast();
		list.showAllFoward();
		
		System.out.println("---------------------------------------");
		
		System.out.println("Size: " + list.size());
		System.out.println(list.get(list.size() -1));
		
		System.out.println("---------------------------------------");
		
		System.out.println(list.get(5));
		System.out.println(list.getIndex("25"));
		
	}
}
