package br.com.structure.list.node;

public class SimpleNode<T> {
    private T value;
    private SimpleNode<T> next;
    
    public SimpleNode (T value) {
        setValue(value);
        setNext(null);
    }
    
    public SimpleNode<T> getNext() {
        return next;
    }
    
    public void setNext(SimpleNode<T> next) {
        this.next = next;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
}
