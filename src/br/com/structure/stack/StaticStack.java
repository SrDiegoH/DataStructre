package br.com.structure.stack;

public class StaticStack<T> {

    private int top;
    private T [] stackArray;

	@SuppressWarnings("unchecked")
	public StaticStack (int size) {
        stackArray = (T[]) new Object[size];
        top = -1;
    }

    public int size() {
        return top +1;
    }
    
    public int getMaxStackSize() {
    	return this.stackArray.length;
    }

    public boolean isFull () {
        return top == this.stackArray.length -1;
    }

    public boolean isEmpty () {
        return top == -1;
    }

    public void push(T valor) throws Exception {
        if (isFull()) {
        	new Exception("Full stack");
        } 
        
    	top ++;
    	stackArray[top] = valor;
    }

    public T[] getStack () {
        return stackArray;
    }

    public void setStackAsArray (T[] stackArray) {
        this.stackArray = stackArray;
        this.top = this.stackArray.length -1;
    }

    public T pop() {
        if (isEmpty()) {
        	new Exception("The stack is Empty");
        }

    	T value = stackArray[top]; 
    	stackArray[top] = null;
    	top --;
    	return value;
    }

    public void showAll() {
        for (int i = stackArray.length -1; i >= 0; i--) {
        	System.out.println(stackArray[i]);
        }
    }

    public static void main(String[] args) {
    	try {
	        StaticStack<Integer> pilha = new StaticStack<Integer>(5);
	        pilha.push(5);
	        pilha.push(0);
	        System.out.println("Size: " + pilha.size());
	        System.out.println("Max size: " + pilha.getMaxStackSize());
	        System.out.println("Pop: " + pilha.pop());
	        System.out.println("Size: " + pilha.size());
	        pilha.push(7);
	        pilha.push(4);
	        pilha.push(8);
	        pilha.push(2);
	        System.out.println("Size: " + pilha.size());
	        pilha.showAll();
		} catch (Exception e) {
		}
    }
    
}
