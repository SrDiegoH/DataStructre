package br.com.structure.hash.list;

import br.com.structure.hash.list.node.HashNode;

public class SimpleLinkedList<T> {
	private HashNode<Integer, T> cursor;

	public SimpleLinkedList() {
		cursor = null;
	}
	
	public boolean isEmpty() {
		return cursor == null;
	}
	
	public void set(int key, T value) {
		if (isEmpty()) {
    		cursor = new HashNode<Integer, T>(key, value);
		} else {
			HashNode<Integer, T> actualNode = cursor;
			
			while(actualNode.getNext() != null) {
				actualNode = actualNode.getNext();
			}
			
			HashNode<Integer, T> newNode = new HashNode<Integer, T>(key, value);
			actualNode.setNext(newNode);
		}
	}
	
	public void remove(int key) {
		if(!isEmpty()){
			HashNode<Integer, T> actualNode = cursor;

			HashNode<Integer, T> previousNode = null;
			while(actualNode.getKey() != key && actualNode.getNext() != null) {
				previousNode = actualNode;
				actualNode = actualNode.getNext();
			}
			
			if(previousNode != null) {
				previousNode.setNext(actualNode.getNext());
			} else {
				cursor = actualNode.getNext();
			}
		}
	}

	public T get(int key) {
		if(!isEmpty()){
			HashNode<Integer, T> actualNode = cursor;
			
			while(actualNode.getKey() != key && actualNode.getNext() != null) {
				actualNode = actualNode.getNext();
			}
			
			return actualNode.getValue();
		}
		
		return null;
	}

	public void showAllFoward() {
		HashNode<Integer, T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}
}