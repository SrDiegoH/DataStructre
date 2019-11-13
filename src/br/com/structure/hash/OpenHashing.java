package br.com.structure.hash;

import br.com.structure.hash.list.SimpleLinkedList;

public class OpenHashing<V> {
	private SimpleLinkedList<V>[] vector;
	private int size;

	@SuppressWarnings("unchecked")
	public OpenHashing(int maxSize) {
		vector = new SimpleLinkedList[maxSize];
		this.size = 0;
	}

	private int hashFunction(V value) {
		return Math.abs(value.hashCode() % vector.length);
	}

	public void set(V value) {
		int hashIndex = hashFunction(value);

		if (vector[hashIndex] == null) {
			vector[hashIndex] = new SimpleLinkedList<V>();
		}

		vector[hashIndex].set(hashIndex, value);

		size++;
	}

	public V get(V value) {
		int hashIndex = hashFunction(value);
		
		return vector[hashIndex].get(hashIndex);
	}

	public void remove(V value) {
		int hashIndex = hashFunction(value);
		
		vector[hashIndex].remove(hashIndex);
		size--;
	}

	public boolean contains(V value) {
		int hashIndex = hashFunction(value);
		
		V searchResult = vector[hashIndex].get(hashIndex);
		return searchResult != null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void showAll() {
		for (int index = 0; index < vector.length; index++) {
			if (vector[index] != null) {
				vector[index].showAllFoward();
			}
		}
	}

	public static void main(String args[]) {
		OpenHashing<String> hash = new OpenHashing<String>(24);
		hash.set("Diego");
		hash.set("amanda");
		hash.set("Bridjite");
		hash.set("daniel");
		hash.set("douglas");
		hash.set("Danilo");
		hash.set("Ana");

		hash.showAll();

		hash.remove("daniel");
		hash.remove("Danilo");
		
		System.out.println("-----------------------");

		hash.showAll();

		hash.get("Diego");
		hash.get("amanda");
		hash.get("Bridjite");
		hash.get("daniel");
		hash.get("douglas");
		hash.get("Danilo");
		hash.get("Ana");
	}
}