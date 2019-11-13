package br.com.structure.stack;

import br.com.structure.stack.node.StackNode;

public class DynamicStack<T> {
	private StackNode<T> cursor;
	private int size;

	public DynamicStack() {
		cursor = null;
		size = 0;
	}

	public boolean isEmpty() {
		return cursor == null;
	}

	public int size() {
		return size;
	}

	private StackNode<T> loopUntilLast(StackNode<T> node) {
		if (node.getNext() != null) {
			return loopUntilLast(node.getNext());
		} else {
			return node;
		}
	}

	public void push(T value) {
		if (isEmpty()) {
			cursor = new StackNode<T>(value);
		} else {
			StackNode<T> actualNode = loopUntilLast(cursor);

			StackNode<T> newNode = new StackNode<T>(value);

			newNode.setPrevious(actualNode);

			actualNode.setNext(newNode);
		}
		size++;
	}

	public T pop() {
		if (!isEmpty()) {
			StackNode<T> actualNode = cursor;

			StackNode<T> previousNode = actualNode;

			while (actualNode.getNext() != null) {
				previousNode = actualNode;
				actualNode = actualNode.getNext();
			}

			T value = actualNode.getValue();

			if (actualNode.equals(previousNode)) {
				cursor = null;
			} else {
				previousNode.setNext(null);
			}

			size--;
			return value;
		}

		return null;
	}

	public void showAll() {
		StackNode<T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}

	public static void main(String[] args) {
		DynamicStack<Integer> pilha = new DynamicStack<Integer>();
		pilha.push(5);
		pilha.push(0);
		System.out.println("Size: " + pilha.size());
		System.out.println("Pop: " + pilha.pop());
		System.out.println("Pop: " + pilha.pop());
		System.out.println("Pop: " + pilha.pop());
		System.out.println("Size: " + pilha.size());
		pilha.push(7);
		pilha.push(4);
		pilha.push(8);
		pilha.push(2);
		System.out.println("Size: " + pilha.size());
		pilha.showAll();
	}
}
