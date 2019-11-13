package br.com.structure.list;

import br.com.structure.list.node.SimpleNode;

public class LinkedList<T> {
	private SimpleNode<T> cursor;
	private int size;

	public LinkedList() {
		cursor = null;
		size = 0;
	}

	public boolean isEmpty() {
		return cursor == null;
	}

	public int size() {
		return size;
	}

	public void setBegin(T value) {
		if (isEmpty()) {
			setFirstTime(value);
		} else {
			SimpleNode<T> newNode = new SimpleNode<T>(value);

			newNode.setNext(cursor);

			cursor = newNode;
		}

		size++;
	}

	public void setMiddle(int index, T value) {
		if (isEmpty() || index <= 0) {
			setBegin(value);
		} else if (index > 0 && index < size() -1) {
			SimpleNode<T> actualNode = cursor;

			for (int i = 0; i < index -1; i++) {
				actualNode = actualNode.getNext();
			}
			
			SimpleNode<T> newNode = new SimpleNode<T>(value);
			
			newNode.setNext(actualNode.getNext());
			
			actualNode.setNext(newNode);
			
			size++;
		} else if (index >= size() -1) {
			set(value);
		}
	}

	public void set(T value) {
		if (isEmpty()) {
			setFirstTime(value);
		} else {
			SimpleNode<T> actualNode = cursor;
			
			while(actualNode.getNext() != null) {
				actualNode = actualNode.getNext();
			}
			
			SimpleNode<T> newNode = new SimpleNode<T>(value);
			actualNode.setNext(newNode);
		}
		
		size++;
	}
	
	private void setFirstTime(T value){
		cursor = new SimpleNode<T>(value);
	}

	public void update(int index, T value) {
		SimpleNode<T> atualNode = cursor;

		for (int i = 0; i < index; i++) {
			atualNode = atualNode.getNext();
		}
		
		atualNode.setValue(value);
	}

	public void exchangeValuesAt(int firstPosition, int secondPosition) {
		SimpleNode<T> firstNode = cursor;
		for (int index = 0; index < firstPosition; index++) {
			firstNode = firstNode.getNext();
		}
	
		SimpleNode<T> secondNode = cursor;
		for (int index = 0; index < secondPosition; index++) {
			secondNode = secondNode.getNext();
		}
		
		T firstValue = firstNode.getValue();
		
		firstNode.setValue(secondNode.getValue());
		
		secondNode.setValue(firstValue);
	}

	public void removeFirst() {
		if (!isEmpty()) {
			cursor = cursor.getNext();
			size--;
		}
	}

	public void remove(int index) {
		if(!isEmpty()){
			if (index == 0) {
				removeFirst();
			} else if (index > 0 && index < size() -1) {
				SimpleNode<T> actualNode = cursor;
	
				for (int i = 0; i < index -1; i++) {
					actualNode = actualNode.getNext();
				}
				
				SimpleNode<T> nextNode = actualNode.getNext().getNext();
				
				actualNode.setNext(nextNode);
				
				size--;
			} else if (index == size()) {
				removeLast();
			}
		}
	}

	public void removeLast() {
		if (!isEmpty()) {
			SimpleNode<T> actualNode = cursor;

			SimpleNode<T> previousNode = actualNode;

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
			SimpleNode<T> actualNode = cursor;

			for (int i = 0; i < index; i++) {
				actualNode = actualNode.getNext();
			}
			return actualNode.getValue();
		}
	}

	public Integer getIndex(T value) {
		SimpleNode<T> actualNode = cursor;

		int index = -1;
		while(!actualNode.getValue().equals(value)) {
			actualNode = actualNode.getNext();
			index ++;
		}
		
		return index == -1? null : index +1;
	}

	public void showAll() {
		SimpleNode<T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}

	public static void main(String[] args) throws Exception {
		LinkedList<String> list = new LinkedList<String>();
		list.set("a");
		list.set("b");
//		for (int i = 1; i <= 10; i++) {
//			System.out.println("index(" + i + ") = " + (i*5));
//			list.set((i * 5) + "");
//		}
		
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
