package br.com.structure.hash;

import br.com.structure.hash.list.node.HashItem;

public class ClosedHashMap<K, V> {
	private HashItem<K, V> [] vector;
    public int size;

	@SuppressWarnings("unchecked")
	public ClosedHashMap(int initialSize) {
		vector = new HashItem[initialSize];
        this.size = 0;
	}

	private int hashFunction(K key) {
		return hashFunction(key, vector);
	}
	
	private int hashFunction(K key,  HashItem<K, V> [] vector) {
        return Math.abs(key.hashCode() % vector.length);
	}

	public void set(K key, V value) {
		int hashIndex = hashFunction(key);

		if (vector[hashIndex] == null) {
			vector[hashIndex] = new HashItem<>(key, value);
		} else {
			vector = findFreeSpace(key, value, vector, hashIndex, hashIndex);
		}
        size ++;
	}
	
	private HashItem<K, V> [] findFreeSpace(K key, V value, HashItem<K, V> [] vector, int hashIndex, int index) {
		if(vector[index] == null) {
			vector[index] = new HashItem<>(key, value);
			return vector;
		} else if (hashIndex == hashFunction(vector[index].getKey())) {
			return findFreeSpace(key, value, vector, hashIndex, index +1);
		} else if (hashIndex != hashFunction(vector[index].getKey())) {
			return findFreeSpace(key, value, increaseVector(vector), hashIndex, index +1);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private HashItem<K, V>[] increaseVector(HashItem<K, V> [] vector) {
		int size = vector.length * 2;
		
		while (!isPrime(size)) {
			size++;
		}

		HashItem<K, V> newVector[] = new HashItem[size];

		for (int i = 0; i < vector.length; i++) {
			if (vector[i] != null) {
		    	int hashIndex = hashFunction(vector[i].getKey(), newVector);
				if (newVector[hashIndex] == null) {
					newVector[hashIndex] = vector[i];
				} else {
					findFreeSpace(vector[i].getKey(), vector[i].getValue(), newVector, hashIndex, hashIndex);
				}
			}
		}

		return newVector;
	}

	private boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}

		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}

	public V get(K key) {
    	int hashIndex = hashFunction(key);
    	
		for (int i = hashIndex; i < vector.length; i++) {
			if(vector[i] == null) {
				continue;
			} else if(vector[i].getKey() == key) {
				return vector[i].getValue();
			} else if(hashIndex != hashFunction(vector[i].getKey())) {
				break;
			}
		}
		
		return null;
	}

	public boolean contains(K key) {
    	int hashIndex = hashFunction(key);
    	
		for (int i = hashIndex; i < vector.length; i++) {
			if(vector[i] == null) {
				continue;
			} else if(vector[i].getKey() == key) {
				return true;
			} else if(hashIndex != hashFunction(vector[i].getKey())) {
				break;
			}
		}
		
		return false;
	}

	public void remove(K key) {
		int hashIndex = hashFunction(key);
    	
		for (int i = hashIndex; i < vector.length; i++) {
			if(vector[i] == null) {
				continue;
			} else if(vector[i].getKey() == key) {
				vector[i] = null;
			} else if(hashIndex != hashFunction(vector[i].getKey())) {
				break;
			}
		}
		
        size ++;
	}
    
    public boolean isEmpty() {
    	return size == 0;
    }
    
    public void showAll(){
        for (int index = 0; index < vector.length; index ++) {
        	if(vector[index] != null) {
        		HashItem<K, V> item = vector[index]; 
        		System.out.println("(" + item.getKey()  + ", " + item.getValue() + ")");
        	}
        }
    }
	
	public static void main(String[] args) {
//		ClosedHashMap<Integer, String> a = new ClosedHashMap<>(5);
//		a.set(1, "1");
//		a.set(2, "2");
//		a.set(3, "3");
//		a.set(5, "5");
//		a.set(7, "7");
//		a.set(6, "6");
//		a.showAll();
//		System.out.println("-----------------------");
//		System.out.println(a.get(6));
//		System.out.println("-----------------------");
//		a.remove(5);
//		a.remove(3);
//		a.showAll();
		
		ClosedHashMap<Integer, String> hash = new ClosedHashMap<Integer, String>(3);
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