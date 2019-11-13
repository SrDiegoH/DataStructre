package br.com.structure.list;

import br.com.structure.list.node.DoubleNode;

public class List <T> implements Cloneable {
	private DoubleNode<T> cursor;
	private int size;
	
	public List() {
		cursor = null;
		size = 0;
	}
	
	public boolean isEmpty () {
		return cursor == null;
	}
	
	public int size() {
		return size;
	}
	
	private DoubleNode<T> loopUntilIndex(int finalIndex, DoubleNode<T> node){
		return loopUntilIndex(node, 0, finalIndex);
	}
	
	private DoubleNode<T> loopUntilIndex(DoubleNode<T> node, int actualIndex, int finalIndex){
		if(actualIndex < finalIndex) {
			return loopUntilIndex(node.getNext(), actualIndex +1, finalIndex);
		} else {
			return node;
		}
	}
	
	private DoubleNode<T> loopUntilLast(DoubleNode<T> node){
		if(node.getNext() != null) {
			return loopUntilLast(node.getNext());
		} else {
			return node;
		}
	}
	
	private void setFirstTime(T value){
		cursor = new DoubleNode<T>(value);
	}
	
	public void setBegin(T value) {
		if (isEmpty()) {
			setFirstTime(value);
		} else {
			DoubleNode<T> newNode = new DoubleNode<T>(value);

			newNode.setNext(cursor);

			cursor.setPrevious(newNode);

			cursor = newNode;
		}

		size++;
	}

	public void setMiddle(int index, T value) {
		if (isEmpty() || index <= 0) {
			setBegin(value);
		} else if (index > 0 && index < size()) {
			DoubleNode<T> actualNode = loopUntilIndex(index, cursor);
			
			DoubleNode<T> newNode = new DoubleNode<T>(value);

			newNode.setPrevious(actualNode.getPrevious());

			newNode.setNext(actualNode);

			actualNode.getPrevious().setNext(newNode);

			newNode.getNext().setPrevious(newNode);

			size++;
		} else if (index >= size() -1) {
			set(value);
		}
	}

	public void set(T value) {
		if (isEmpty()) {
			setFirstTime(value);
		} else {
			DoubleNode<T> actualNode = loopUntilLast(cursor);
			
			DoubleNode<T> newNode = new DoubleNode<T>(value);
			
			newNode.setPrevious(actualNode);
			
			actualNode.setNext(newNode);
		}
		
		size++;
	}

	public void update(int index, T value) {
		DoubleNode<T> actualNode = loopUntilIndex(index, cursor);
		
		actualNode.setValue(value);
	}

	public void exchangeValuesAt(int firstPosition, int secondPosition) {
		DoubleNode<T> firstNode = loopUntilIndex(firstPosition, cursor);
	
		DoubleNode<T> secondNode = loopUntilIndex(secondPosition, cursor);

		T firstValue = firstNode.getValue();
		
		firstNode.setValue(secondNode.getValue());
		
		secondNode.setValue(firstValue);
	}
    
	public void removeFirst() {
		if (!isEmpty()) {
			cursor = cursor.getNext();
			
			if(cursor != null) {
				cursor.setPrevious(null);
			}
			size--;
		}
	}

	public void remove(int index) {
		if(!isEmpty()){
			if (index == 0) {
				removeFirst();
			} else if (index > 0 && index < size() -1) {
				DoubleNode<T> actualNode = loopUntilIndex(index, cursor);
				
				actualNode.getPrevious().setNext(actualNode.getNext());
				
				if (actualNode.getNext() != null) {
					actualNode.getNext().setPrevious(actualNode.getPrevious());
				}
				
				size--;
			} else if (index == size()) {
				removeLast();
			}
		}
	}

	public void removeLast() {
		if (!isEmpty()) {
			DoubleNode<T> actualNode = cursor;

			DoubleNode<T> previousNode = actualNode;

			while (actualNode.getNext() != null) {
				previousNode = actualNode;
				actualNode = actualNode.getNext();
			}

			if (actualNode.equals(previousNode)) {
				cursor = null;
			} else {
				previousNode.setNext(null);
			}

			size--;
		}
	}

	public T get(int index) throws Exception {
		if(index < 0 || index > size() -1) {
			throw new Exception("Invalid index");
		} else if (isEmpty()) {
			return null;
		} else {
			DoubleNode<T> actualNode = loopUntilIndex(index, cursor);
			
			return actualNode.getValue();
		}
	}

	public Integer getIndex(T value) {
		DoubleNode<T> actualNode = cursor;

		for (int i = 0; i < size(); i++) {
			if(actualNode.getValue().equals(value)) {
				return i;
			}
				
			actualNode = actualNode.getNext();
		}

		return null;
	}

    public List<T> push(T value) {
    	set(value);
    	return this;
    }

    public T pop() {
    	DoubleNode<T> actualNode = loopUntilLast(cursor);
		
		T value = actualNode.getValue();
		
        removeLast();
        
        return value;
    }
    
    public List<T> enqueue(T value) {
    	return push(value);
    }
    
    public T dequeue() {
    	DoubleNode<T> actualNode = loopUntilLast(cursor);
    	
    	T value = actualNode.getValue();
    	
    	removeFirst();
    	
    	return value;
    }

	public T head() {
		return getFirst();
	}
	
	public List<T> tail() {
		List<T> list = this.clone();
		list.removeFirst();
		return list;
	}
    
    public T getFirst() {
    	return cursor.getValue();
    }
    
    public T getLast() {
    	DoubleNode<T> actualNode = loopUntilLast(cursor);
		
		T value = actualNode.getValue();
        
        return value;
    }

    public void showAll() {
		DoubleNode<T> actualNode = cursor;

		while (actualNode != null) {
			System.out.println(actualNode.getValue());
			actualNode = actualNode.getNext();
		}
	}
    
    public void foreach(IForeach<T> iForeach) {
    	foreach(this.clone(), iForeach);
    }
    
    private void foreach(List<T> list, IForeach<T> iForeach) {
    	if (!list.isEmpty()) {
    		iForeach.action(list.head());
    		foreach(list.tail(), iForeach);
    	}
    }
        
    public void forEach(IForeach <T> iForeach) {
    	forEach(cursor, iForeach);
    }
    
    private void forEach(DoubleNode<T> node, IForeach<T> iForeach) {
    	if (node!= null) {
    		iForeach.action(node.getValue());
    		forEach(node.getNext(), iForeach);
    	}
    }

    public void forEachIndex(IForeachIndex<T> iForeach) {
    	forEachIndex(cursor, 0, iForeach);
    }
    
    private void forEachIndex(DoubleNode<T> node, int index, IForeachIndex<T> iForeach) {
    	if (node!= null) {
    		iForeach.action(index, node.getValue());
    		forEachIndex(node.getNext(), index +1, iForeach);
    	}
    }
    
    public <R> List<R> map(IMap<R, T> iMap) {
    	return map(this.clone(), new List<R>(), iMap);
    }
    
    private <R> List<R> map(List<T> list, List<R> accumulated, IMap<R, T> iMap) {
    	if(list.isEmpty()) {
    		return accumulated;
    	} else {
    		return map(list.tail(), accumulated.enqueue(iMap.action(list.head())), iMap);
    	}
    }
    
    public <R> List<R> mapping(IMap<R, T> iMap) {
    	return map(cursor, new List<R>(), iMap);
    }
    
    private <R> List <R> map (DoubleNode<T> node, List<R> accumulated, IMap<R, T> iMap) {
    	return node == null? accumulated : map(node.getNext(), accumulated.enqueue(iMap.action(node.getValue())), iMap);
    }
    
    public <R> List<R> mapIndex(IMapIndex<R, T> iMap) {
    	return map(cursor, 0, new List<R>(), iMap);
    }
    
    private <R> List<R> map(DoubleNode<T> node, Integer index, List <R> accumulated, IMapIndex <R, T> iMap) {
    	return node == null? accumulated : map(node.getNext(), index +1, accumulated.enqueue(iMap.action(index, node.getValue())), iMap);
    }
    
    public <R> List<R> mapFilter(IMap<R, T> iMap, IFilter<T> iFilter) {
    	return mapFilter(cursor, new List<R>(), iMap, iFilter);
    }
    
    private <R> List<R> mapFilter(DoubleNode<T> node, List <R> accumulated, IMap<R, T> iMap, IFilter<T> iFilter) {
    	if(node == null) {
    		return accumulated;
    	} else {
    		if(iFilter.action(node.getValue())) {
    			return mapFilter(node.getNext(), accumulated.enqueue(iMap.action(node.getValue())), iMap, iFilter);
    		} else {
    			return mapFilter(node.getNext(), accumulated, iMap, iFilter);
    		}
    	}
    }
        
    public List<T> collect(IFilter<T> iFilter) {
    	return filter(this.clone(), new List<T>(), iFilter);
    }
    
    private List<T> filter(List <T> list, List<T> accumulated, IFilter <T> iFilter) {
    	if(list.isEmpty()) {
    		return accumulated;	
    	} else {
    		if(iFilter.action(list.head())) {
    			return filter(list.tail(), accumulated.enqueue(list.head()), iFilter);
    		} else {
    			return filter(list.tail(), accumulated, iFilter);
    		}
    	}
    }
    
    public List<T> filter(IFilter<T> iFilter) {
    	return filter(cursor, new List<T>(), iFilter);
    }
    
    private List<T> filter(DoubleNode<T> node, List<T> accumulated, IFilter<T> iFilter) {
    	if(node == null) {
    		return accumulated;
    	} else {
    		if(iFilter.action(node.getValue())) {
    			return filter(node.getNext(), accumulated.enqueue(node.getValue()), iFilter);
    		} else {
    			return filter(node.getNext(), accumulated, iFilter);
    		}
    	}
    }
    
    public List <T> filterIndex(IFilterIndex<T> iFilter) {
    	return filter(cursor, 0, new List<T>(), iFilter);
    }
    
    private List<T> filter (DoubleNode<T> node, Integer index, List<T> accumulated, IFilterIndex<T> iFilter) {
    	if(node == null) {
    		return accumulated;
    	} else {
    		if(iFilter.action (index, node.getValue())) {
				return filter(node.getNext(), index +1, accumulated.enqueue(node.getValue()), iFilter);
    		} else {
    			return filter(node.getNext(), index +1, accumulated, iFilter);
    		}
    	}
    }
    
	public List<Integer> range(Integer begin, Integer end) {
		return begin >= end? new List<Integer>() : range(begin, end, new List<Integer>());
	}
	
	private List<Integer> range(Integer begin, Integer end, List <Integer> accumulated) {
		return begin >= end? accumulated : range(begin +1, end, accumulated.enqueue (begin));
	}
	
	public List<Integer> range(Integer begin, Integer end, IRange stepByStep) {
		return begin >= end? new List<Integer>() : range(begin, end, stepByStep, new List<Integer>());
	}
	
	private List<Integer> range(Integer begin, Integer end, IRange stepByStep, List<Integer> accumulated) {
		return begin >= end? accumulated : range(stepByStep.action(begin), end, stepByStep, accumulated.enqueue(begin));
	}
	
    public T reduce(IReduce <T> iReduce) {
    	return reduce(this.clone().tail(), this.clone().head(), iReduce);
    }
    
    private T reduce(List <T> list, T accumulated, IReduce <T> iReduce) {
    	return list.isEmpty()? accumulated : reduce(list.tail(), iReduce.action(accumulated, list.head()), iReduce);
    }
    
    public T reduceLeft (IReduce <T> iReduce) {
    	return reduceLeft(cursor.getNext(), cursor.getValue(), iReduce);
    }
    
    private T reduceLeft(DoubleNode <T> node, T accumulated, IReduce <T> iReduce) {
    	return node == null? accumulated : reduceLeft(node.getNext(), iReduce.action(accumulated, node.getValue()), iReduce);
    }
    
    public T reduceRight(IReduce <T> iReduce) {
    	DoubleNode<T> ultimo = loopUntilLast(cursor); 
    	return reduceRight(ultimo.getPrevious(), ultimo.getValue(), iReduce);
    }
    
    private T reduceRight(DoubleNode <T> node, T accumulated, IReduce <T> iReduce) {
    	return node == null? accumulated : reduceRight(node.getPrevious(), iReduce.action(accumulated, node.getValue()), iReduce);
    }
    
    public <R> R fold(R firstItem, IFold <R, T> iFold) {
    	return fold(this.clone(), firstItem, iFold);
    }
    
    private <R> R fold(List<T> list, R accumulated, IFold<R, T> iFold) {    	
    	return list.isEmpty()? accumulated : fold(list.tail(), iFold.action(accumulated, list.head()), iFold);
    }
    
    public <R> R foldLeft(R firstItem, IFold<R, T> iFold) {
    	return foldLeft(cursor, firstItem, iFold);
    }
    
    private <R> R foldLeft(DoubleNode<T> node, R accumulated, IFold<R, T> iFold) {
    	return node == null? accumulated : foldLeft(node.getNext(), iFold.action(accumulated, node.getValue()), iFold);
    }    
    
    public <R> R foldRight(R firstItem, IFold <R, T> iFold) {
    	DoubleNode<T> ultimo = loopUntilLast(cursor); 
    	return foldRight(ultimo, firstItem, iFold);
    }
    
    private <R> R foldRight (DoubleNode <T> node, R accumulated, IFold <R, T> iFold) {
    	return node == null? accumulated : foldRight(node.getPrevious(), iFold.action(accumulated, node.getValue()), iFold);
    }
	
    public <R> List<R> scanRight(R firstValue, IScan<R, T> iMap) {
    	List<R> list = new List<R>();
    	list.set(firstValue);
    	return scanRight(this.clone(), list, iMap);
    }
    
    private <R> List<R> scanRight(List<T> list, List<R> accumulated, IScan<R, T> iScan) {
    	if(list.isEmpty()) {
    		return accumulated;
    	} else {
    		return scanRight(list.tail(), accumulated.enqueue(iScan.action(accumulated.getLast(), list.head())), iScan);
    	}
    }
    
    public T filterReduce(IBoolTow <T> action) {
        return filterReduce(cursor, cursor.getValue(), action);
    }
    
    public T reduceFilter(IBoolTow <T> action) {
        return filterReduce (cursor, cursor.getValue(), action);
    }
    
    private T filterReduce(DoubleNode<T> node, T accumulated, IBoolTow<T> action) {
    	return node == null? accumulated : filterReduce (node.getNext(), (action.action(accumulated, node.getValue())? accumulated : node.getValue()), action);
    }

    public boolean contains(T value) {
        return contains(value, cursor);
    }
    
    private boolean contains(T value, DoubleNode <T> node) {
    	if(node == null) {
    		return false;
    	} else {
    		if(node.getValue().equals(value)) {
    			return true; 
    		} else {
    			return contains(value, node.getNext());
    		}
    	}
    }
    
    public boolean existsByCondition(IFilter <T> action) {
        return existsByCondition(cursor, action);
    }
    
    private boolean existsByCondition(DoubleNode <T> node, IFilter <T> action) {
    	if(node == null) {
    		return false;
    	} else {
    		if(action.action(node.getValue())) {
    			return true;
    		} else {
    			return existsByCondition(node.getNext(), action);	
    		}
    	}
    }
    
    public boolean forAll(IFilter<T> action) {
        return forAll(cursor, action, true);
    }
    
    private boolean forAll(DoubleNode <T> node, IFilter <T> action, boolean accumulated) {
    	if(node == null) {
    		return accumulated; 
    	} else {
    		boolean conditionReturn = !accumulated? false : action.action(node.getValue());
    		return forAll(node.getNext(), action, conditionReturn);
    	}
    }
    
    //reverse, flatMap, sortBy, zip (junta itens em duplas)
	//distinct, drop (divide em um indice), slice (divide de um indice a tal), flatten (juntar duas listas); 
	//partition (particionar em um predicado, tipo split), take (pega n primeiros itens)
    
	@Override
	@SuppressWarnings("unchecked")
	public List <T> clone() {
		try {
			return (List <T>) super.clone ();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}
    
    @Override
    public String toString () {
    	return toString(0, cursor, "[") + "]";
    }
    
    private String toString (int index, DoubleNode <T> node, String accumulated) {
    	if(node != null) {
    		return toString(index +1, node.getNext(), accumulated + "(" + index + "," + node.getValue() + ")|");
    	} else {
    		return accumulated.substring(0, accumulated.length() -1);
    	}
    }
    
	private interface IForeach <I> {
		void action(I value);
	}
	
	private interface IForeachIndex <I> {
		void action(Integer index, I value);
	}
	
	private interface IFilter <I> {
		boolean action(I value);
	}
	
	private interface IFilterIndex <I> {
		boolean action(Integer index, I value);
	}
	
	private interface IMap <RETURN, PARAMETER> {
		RETURN action(PARAMETER value);
	}
	
	private interface IMapIndex <RETURN, PARAMETER> {
		RETURN action(Integer index, PARAMETER value);
	}
	
	private interface IReduce <T> {
		T action(T firstValue, T secondValue);
	}
	
	private interface IFold <RETURN, PARAMETER> {
		RETURN action(RETURN firstValue, PARAMETER secondValue);
	}
	
	private interface IRange { 
		Integer action(Integer value);
	}
	
	private interface IBoolTow <I> {
		boolean action(I firstValue, I secondValue);
	}
	
	private interface IScan <RETURN, PARAMETER> {
		RETURN action(RETURN firstValue, PARAMETER secondValue);
	}
    
//	public static void main(String[] args) {
//		List<String> lista = new List<String>(); 
////					  lista.setInicio("b");
////					  lista.setInicio("a");
////					  
////					  lista.setFim("d");
////					  lista.setFim("e");
////					  
////					  lista.setMeio(2, "c");
////					  lista.setMeio(0, "PRIMEIRO");
////					  lista.setMeio(100, "ULTIMO");
////					  lista.setMeio(3, "");
////					  lista.percorrerLista();					
////					  
////					  System.out.println(lista.toString());
////					  System.out.println();
////					  lista.percorrerListaFoward();
////					  System.out.println();
////					  lista.percorrerListaBackward();
////					  System.out.println();
////					  lista.percorrerListaNItens(4);
////					  System.out.println();
////					  lista.percorrerListaNItens(11);
////					  
////					  System.out.println(lista.size() + "\n");
////					  
////					  lista.update(3, "MEIO");
////					  lista.update(0, "INICIO");
////					  lista.update(lista.size() -1, "FIM 1");
////					  lista.update(100, "FIM 2");
////					  
////					  lista.percorrerLista();
////					  
////					  System.out.println(lista.size() + "\n");
////					  
////					  System.out.println(lista.getValor(4));
////					  System.out.println(lista.getValor(5));
////					  System.out.println(lista.getValor(2));
////					  System.out.println(lista.getValor(0) + "\n");
////					  
////					  System.out.println(lista.contains("d"));
////					  System.out.println(lista.contains("pa�oca"));
////					  System.out.println(lista.contains("e"));
////					  System.out.println(lista.contains("J"));					  
////					  System.out.println(lista.contains("a"));
////					  System.out.println(lista.contains("FIM 2") + "\n");
////					  
////					  System.out.println(lista.map(x -> "x" + x).forAll(x -> x.startsWith("x")));
////					  System.out.println(lista.forAll(x -> x.length() >= 1));
////					  System.out.println(lista.forAll(x -> x.length() < 1));
////					  
////					  System.out.println(lista.exists(x -> x.equalsIgnoreCase("d")));
////					  System.out.println(lista.exists(x -> x.equalsIgnoreCase("pa�oca")));
////					  System.out.println(lista.exists(x -> x.equalsIgnoreCase("a")));
////					  System.out.println(lista.exists(x -> x.equalsIgnoreCase("J")));
////					  System.out.println(lista.exists(x -> x.length() >= 8));
////					  System.out.println(lista.exists(x -> x.length() == 6));
////					  
////					  System.out.println(lista.getIndice("d"));
////					  System.out.println(lista.getIndice("pa�oca"));
////					  System.out.println(lista.getIndice("e"));
////					  System.out.println(lista.getIndice("J"));					  
////					  System.out.println(lista.getIndice("INICIO"));
////					  System.out.println(lista.getIndice("FIM 2") + "\n");					  
////					  
////					  System.out.println(lista.head());
////					  lista.tail().tail().percorrerLista();
////					  System.out.println();
////					  lista.percorrerLista();
////					  
////					  System.out.println();
////					  
////					  lista.removeInicio();
////					  lista.removeFim();
////					  lista.removeMeio(2);
////					  lista.removeMeio(2);
////					  lista.percorrerLista();
////					  
////					  System.out.println();
////					  lista.foreach(System.out::println);
////					  System.out.println();
////					  lista.foreach(x -> System.out.println(x));
////					  System.out.println();					  
////					  lista.filter(x -> x.equalsIgnoreCase("primeiro")).foreach(System.out::println);
////					  System.out.println();
////					  lista.filter(x -> x.length() <= 1).foreach(System.out::println);
////					  System.out.println();
////					  lista.map(x -> x.toUpperCase()).foreach(System.out::println);
////					  System.out.println();
////					  lista.map(x -> x.length()).foreach(x -> System.out.println (x *2));
////					  System.out.println();
////					  System.out.println(lista.reduce((x, y) -> x + y));
////					  System.out.println(lista.reduce((x, y) -> x + y.length()) + "\n");
////					  System.out.println(lista.fold("", (x, y) -> {if (y.length()%2 == 0) return y; else return x;}) + "\n");
////					  System.out.println(lista.fold(false, (x, y) -> {if (y.length()%2==2) return true; else return x;}) + "\n");
////					  System.out.println();
////					  lista.range(1, 10).map(x -> "a" + x).foreach(System.out::println);
////					  System.out.println();
////					  lista.range(0, 10, x -> x +2).map(x -> "(" + x + ")").foreach(System.out::println);
////					  System.out.println();
////					  System.out.println(lista.filterReduce((x, y) -> x.length() < y.length()));
////					  System.out.println();
////					  System.out.println(lista.reduceFilter((x, y) -> x.length() <= y.length()));
////					  System.out.println();
////					  System.out.println(lista.foldLeft(1, (x, y) -> x * y.length()));
////					  lista.range(1, 10).mapIndex((i, y) -> i%2==0? "(" + i + ")" : "" + y).forEach(System.out::println);
////					  System.out.println();
////					  lista.range(1, 10).mapIndex((i, y) -> i<=5? "(" + i + ")"  : "" + y).forEach(System.out::println);
////					  lista.range(1, 10).forEachIndex((i, y) -> System.out.print((i%2==0? "("+i+")?" : "(" + i + ")" + y) + " - "));
////					  System.out.println();
////					  lista.range(0, 10).forEachIndex((i, y) -> System.out.print("(" + i + ")" + y + ", "));//					  
////					  lista.range(1, 11).mapping(y -> y +"-" + y).foreach(System.out::println);
////					  lista.range(1, 11).filter(y -> y%2==0).foreach(System.out::println);
////					  System.out.println();
////					  lista.range(1, 11).filter(y -> y<=5).foreach(System.out::println);
////					  System.out.println(lista.range(1, 5).reduce((x, y) -> x -y));
////					  System.out.println(lista.range(1, 5).reduceLeft((x, y) -> x -y));
////					  System.out.println(lista.range(1, 5).reduceRight((x, y) -> x -y));
////					  System.out.println(lista.range(1, 5).fold(-1, (x, y) -> x -y));
////					  System.out.println(lista.range(1, 5).foldLeft(-1, (x, y) -> x -y));
////					  System.out.println(lista.range(1, 5).foldRight(-1, (x, y) -> x -y));
////					  System.out.println(lista.range(1, 10).filterIndex((i, y) -> i >= 5));
////					  lista.range(1, 10).filterIndex((i, y) -> i>=5).forEach(System.out::println);
//	}
}
