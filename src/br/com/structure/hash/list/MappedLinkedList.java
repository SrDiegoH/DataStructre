package br.com.structure.hash.list;

import br.com.structure.hash.list.node.HashNode;

public class MappedLinkedList<K, T> {
	private HashNode<K, T> cursor;

	public MappedLinkedList() {
		cursor = null;
	}
	
	public boolean isEmpty() {
		return cursor == null;
	}
	
	public void set(K key, T value) {
		if (isEmpty()) {
    		cursor = new HashNode<K,T>(key, value);
		} else {
			HashNode<K, T> actualNode = cursor;
			
			while(actualNode.getNext() != null) {
				actualNode = actualNode.getNext();
			}
			
			HashNode<K, T> newNode = new HashNode<K, T>(key, value);
			actualNode.setNext(newNode);
		}
	}
	
	public void remove(K key) {
		if(!isEmpty()){
			HashNode<K, T> actualNode = cursor;

			HashNode<K, T> previousNode = null;
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

	public T get(K key) {
		if(!isEmpty()){
			HashNode<K, T> actualNode = cursor;
			
			while(!actualNode.getKey().equals(key) && actualNode.getNext() != null) {
				actualNode = actualNode.getNext();
			}
			
			return actualNode.getValue();
		}
		
		return null;
	}

	public void showAllFoward() {
		HashNode<K, T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}
}