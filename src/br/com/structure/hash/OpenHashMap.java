package br.com.structure.hash;

import br.com.structure.hash.list.MappedLinkedList;

public class OpenHashMap<K, V> {
	private MappedLinkedList<K, V>[] vector;
	private int size;

	@SuppressWarnings("unchecked")
	public OpenHashMap(int maxSize) {
		vector = new MappedLinkedList[maxSize];
		this.size = 0;
	}

	private int hashFunction(K key) {
        return Math.abs(key.hashCode() % vector.length);
	}

	public void set(K key, V value) {
		int hashIndex = hashFunction(key);

		if (vector[hashIndex] == null) {
			vector[hashIndex] = new MappedLinkedList<K, V>();
		}

		vector[hashIndex].set(key, value);

		size++;
	}

	public V get(K key) {
		int hashIndex = hashFunction(key);
		
		return vector[hashIndex].get(key);
	}

	public void remove(K key) {
		int hashIndex = hashFunction(key);
		
		vector[hashIndex].remove(key);
		size--;
	}
	
    public boolean containsKey(K key) {
    	int hashIndex = hashFunction(key);
    	
    	V searchResult = vector[hashIndex].get(key);
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
		OpenHashMap<Integer, String> hash = new OpenHashMap<Integer, String>(24);
		hash.set(0, "Diego");
		hash.set(1, "amanda");
		hash.set(2, "Bridjite");
		hash.set(3, "daniel");
		hash.set(4, "douglas");
		hash.set(5, "Danilo");
		hash.set(6, "Ana");

		hash.showAll();

		hash.remove(3);
		hash.remove(5);
		
		System.out.println("-----------------------");

		hash.showAll();

		hash.get(0);
		hash.get(1);
		hash.get(2);
		hash.get(3);
		hash.get(4);
		hash.get(5);
		hash.get(6);
	}
}
