package br.com.structure.hash;

public class HashMap<K, V> {
    private V vector [];
    public int size;
    
    @SuppressWarnings("unchecked")
	public HashMap(int maxSize) {
        vector = (V[]) new Object[maxSize];
        this.size = 0;
    }
    
    private int hashFunction(K key){
        return Math.abs(key.hashCode() % vector.length);
    }
    
    public void set(K key, V value){
    	int hashIndex = hashFunction(key);
    	
        vector[hashIndex] = value;
        size ++;
    }
    
    public V get(K key){
    	int hashIndex = hashFunction(key);
    	
        return vector[hashIndex];
    }
    
    public void remove(K key){
    	int hashIndex = hashFunction(key);
    	
        vector[hashIndex] = null;
        size --;
    }
    
    public boolean containsKey(K key) {
    	int hashIndex = hashFunction(key);
    	
        return vector[hashIndex] != null;
    }
    
    public boolean isEmpty() {
    	return size == 0;
    }
    
    public void showAll(){
        for (int index = 0; index < vector.length; index ++) {
        	System.out.println(vector[index]);
        }
    }
}