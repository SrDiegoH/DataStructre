package br.com.structure.hash.list.node;

public class HashItem<K, V> {
    private K key;
    private V value;
    
    public HashItem (K key, V value) {
        setKey(key);
        setValue(value);
    }
    
    public V getValue() {
        return value;
    }
    
    public void setValue(V value) {
        this.value = value;
    }

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}
}
