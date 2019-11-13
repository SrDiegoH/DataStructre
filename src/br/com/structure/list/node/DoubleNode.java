package br.com.structure.list.node;

public class DoubleNode<T> {
    private T value;
    private DoubleNode<T> previous, next;
    
    public DoubleNode (T value) {
        setValue(value);
        setNext(null);
        setPrevious(null);
    }
    
    public DoubleNode<T> getNext() {
        return next;
    }
    
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
    
    public DoubleNode<T> getPrevious() {
        return previous;
    }
    
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
       
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
}
