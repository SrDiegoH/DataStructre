package br.com.structure.hash;

public class Hash<V> {
    private V vector [];
    public int size;
    
    @SuppressWarnings("unchecked")
	public Hash(int maxSize) {
        vector = (V[]) new Object[maxSize];
        this.size = 0;
    }
    
    private int hashFunction(V value){
        return Math.abs(value.hashCode() % vector.length);
    }
    
    public void set(V value){
    	int hashIndex = hashFunction(value);
    	
        vector[hashIndex] = value;
        size ++;
    }
    
    public V get(V value){
    	int hashIndex = hashFunction(value);
    	
        return vector[hashIndex];
    }
    
    public void remove(V value){
    	int hashIndex = hashFunction(value);
    	
        vector[hashIndex] = null;
        size --;
    }
    
    public boolean contains(V value) {
    	int hashIndex = hashFunction(value); 
    	
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