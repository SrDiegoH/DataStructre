package br.com.structure.hash.list.node;

public class HashNode<K, V> extends HashItem<K, V>{
    private HashNode<K,V> next;
    
    public HashNode (K key, V value) {
    	super(key, value);
        setNext(null);
    }
    
    public HashNode<K,V> getNext() {
        return next;
    }
    
    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
}
