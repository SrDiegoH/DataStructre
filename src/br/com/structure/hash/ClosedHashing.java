package br.com.structure.hash;

public class ClosedHashing<V> {
	private V vector[];
    public int size;

	@SuppressWarnings("unchecked")
	public ClosedHashing(int initialSize) {
		vector = (V[]) new Object[initialSize];
        this.size = 0;
	}

	private int hashFunction(V value) {
		return hashFunction(value, vector);
	}
	
	private int hashFunction(V value, V [] vector) {
		return Math.abs(value.hashCode() % vector.length);
	}

	public void set(V value) {
		int hashIndex = hashFunction(value);
		if (vector[hashIndex] == null) {
			vector[hashIndex] = value;
		} else {
			vector = findFreeSpace(value, vector, hashIndex, hashIndex);
		}
        size ++;
	}
	
	private V [] findFreeSpace(V value, V [] vector, int hashIndex, int index) {
		if(vector[index] == null) {
			vector[index] = value;
			return vector;
		} else if (hashIndex == hashFunction(vector[index])) {
			return findFreeSpace(value, vector, hashIndex, index +1);
		} else if (hashIndex != hashFunction(vector[index])) {
			return findFreeSpace(value, increaseVector(vector), hashIndex, index +1);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private V[] increaseVector(V [] vector) {
		int size = vector.length * 2;
		
		while (!isPrime(size)) {
			size++;
		}

		V newVector[] = (V[]) new Object[size];

		for (int i = 0; i < vector.length; i++) {
			if (vector[i] != null) {
		    	int hashIndex = hashFunction(vector[i], newVector);
				if (newVector[hashIndex] == null) {
					newVector[hashIndex] = vector[i];
				} else {
					findFreeSpace(vector[i], newVector, hashIndex, hashIndex);
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

	public V get(V value) {
    	int hashIndex = hashFunction(value);
    	
		for (int i = hashIndex; i < vector.length; i++) {
			if(vector[i] == null) {
				continue;
			} else if(vector[i] == value) {
				return vector[i];
			} else if(hashIndex != hashFunction(vector[i])) {
				break;
			}
		}
		
		return null;
	}

	public boolean contains(V value) {
    	int hashIndex = hashFunction(value);
    	
		for (int i = hashIndex; i < vector.length; i++) {
			if(vector[i] == null) {
				continue;
			} else if(vector[i] == value) {
				return true;
			} else if(hashIndex != hashFunction(vector[i])) {
				break;
			}
		}
		
		return false;
	}

	public void remove(V value) {
		int hashIndex = hashFunction(value);
    	
		for (int i = hashIndex; i < vector.length; i++) {
			if(vector[i] == null) {
				continue;
			} else if(vector[i] == value) {
				vector[i] = null;
			} else if(hashIndex != hashFunction(vector[i])) {
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
        		System.out.println(vector[index]);
        	}
        }
    }
	
	public static void main(String[] args) {
//		HashFechado<Integer> a = new HashFechado<>(5);
//		a.set(1);
//		a.set(2);
//		a.set(3);
//		a.set(5);
//		a.set(7);
//		a.set(6);
//		a.showAll();
//		System.out.println(a.get(6));
//		a.remove(5);
//		a.remove(3);
//		a.showAll();
		
		ClosedHashing<String> hash = new ClosedHashing<String>(24);
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
		
		System.out.println("-----------------------");

		System.out.println(hash.get("Diego"));
		System.out.println(hash.get("amanda"));
		System.out.println(hash.get("Bridjite"));
		System.out.println(hash.get("daniel"));
		System.out.println(hash.get("douglas"));
		System.out.println(hash.get("Danilo"));
		System.out.println(hash.get("Ana"));
	}
}