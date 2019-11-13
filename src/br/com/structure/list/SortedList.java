package br.com.structure.list;

import static br.com.structure.util.ComparableHelper.isBiggerThen;
import static br.com.structure.util.ComparableHelper.isEqualsOrBiggerThen;
import static br.com.structure.util.ComparableHelper.isEqualsAs;
import static br.com.structure.util.ComparableHelper.isSmallerThen;

import br.com.structure.list.node.DoubleNode;

public class SortedList<T extends Comparable<T>> {
	private DoubleNode<T> cursor;
	private int size;

	public SortedList() {
		cursor = null;
		size = 0;
	}

	public boolean isEmpty() {
		return cursor == null;
	}

	public int size() {
		return size;
	}

	public void set(T value) {
		DoubleNode<T> newNode = new DoubleNode<T>(value);

		if (isEmpty()) {
			cursor = newNode;
		} else if (isBiggerThen(cursor.getValue(), value)) {
			newNode.setNext(cursor);
			cursor.setPrevious(newNode);
			cursor = newNode;
		} else if (isLastNodeSmaller(value)) {
			DoubleNode<T> actualNode = cursor;

			while (actualNode.getNext() != null) {
				actualNode = actualNode.getNext();
			}

			actualNode.setNext(newNode);
			newNode.setPrevious(actualNode);
			actualNode = newNode;
		} else {
			DoubleNode<T> actualNode = cursor;

			while (actualNode.getNext() != null) {
				if (isEqualsOrBiggerThen(actualNode.getValue(), value)) {
					break;
				}

				actualNode = actualNode.getNext();
			}

			newNode.setPrevious(actualNode.getPrevious());
			newNode.setNext(actualNode);

			if (actualNode.getPrevious() != null)
				actualNode.getPrevious().setNext(newNode);

			actualNode.setPrevious(newNode);
		}

		size++;
	}

	private boolean isLastNodeSmaller(T value) {
		DoubleNode<T> actualNode = cursor;

		while (actualNode.getNext() != null) {
			actualNode = actualNode.getNext();
		}

		return isSmallerThen(actualNode.getValue(), value);
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
		if (!isEmpty()) {
			if (index == 0) {
				removeFirst();
			} else if (index > 0 && index < size() - 1) {
				DoubleNode<T> actualNode = cursor;

				for (int i = 0; i < index; i++) {
					actualNode = actualNode.getNext();
				}

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
		if (index < 0 || index > size() - 1) {
			throw new Exception("Invalid index");
		} else if (isEmpty()) {
			return null;
		} else {
			DoubleNode<T> actualNode = cursor;

			for (int i = 0; i < index; i++) {
				actualNode = actualNode.getNext();
			}
			return actualNode.getValue();
		}
	}

	public Integer getIndex(T value) {
		DoubleNode<T> actualNode = cursor;

		for (int i = 0; i < size() - 1; i++) {
			if (isEqualsAs(actualNode.getValue(), value)) {
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
		SortedList<Integer> list = new SortedList<Integer>();

		list.set(8);
		list.set(1);
		list.set(6);
		list.set(7);
		list.set(7);
		list.set(10);
		list.set(9);
		list.set(25);
		list.set(20);
		list.set(2);
		list.set(0);

		list.showAll();

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
		System.out.println(list.get(list.size() - 1));

		System.out.println("---------------------------------------");

		System.out.println(list.get(5));
		// System.out.println(list.getIndex("25"));
	}
}