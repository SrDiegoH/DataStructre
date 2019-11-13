package br.com.structure.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ListTest {
	
	@Test
	void testListIsEmpty() {
		List<Integer> list = new List<Integer>();
		boolean isEmpty = list.isEmpty();
		assertEquals(true, isEmpty);
	}
	
	@Test
	void testSetFirstTime() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(20);
		
		Integer value = list.get(0);
		assertEquals(Integer.valueOf(20), value);
		
		Integer size = list.size();
		assertEquals(Integer.valueOf(1), size);
	}
	
	@Test
	void testSetInTheBegin() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(20);
		
		list.setBegin(10);
		
		Integer value = list.get(0);
		assertEquals(Integer.valueOf(10), value);
		
		Integer size = list.size();
		assertEquals(Integer.valueOf(2), size);
	}
	
	@Test
	void testSetInTheMiddle() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		
		list.setMiddle(1, 15);
		
		Integer firstValue = list.get(0);
		assertEquals(Integer.valueOf(10), firstValue);
				
		Integer middleValue = list.get(1);
		assertEquals(Integer.valueOf(15), middleValue);
		
		Integer lastValue = list.get(2);
		assertEquals(Integer.valueOf(20), lastValue);
		
		Integer size = list.size();
		assertEquals(Integer.valueOf(3), size);
	}
	
	@Test
	void testUpdateFirst() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		list.set(30);
		
		list.update(0, 1);
		
		Integer value = list.get(0);
		assertEquals(Integer.valueOf(1), value);
	}
	
	@Test
	void testUpdateMiddle() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		list.set(40);
		list.set(50);
		
		list.update(2, 30);
		
		Integer value = list.get(2);
		assertEquals(Integer.valueOf(30), value);
	}
	
	@Test
	void testUpdateLast() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		list.set(40);
		list.set(50);
		
		Integer lastIndex = list.size() -1;
		
		list.update(lastIndex, 3);
		
		Integer value = list.get(lastIndex);
		assertEquals(Integer.valueOf(3), value);
	}
	
	@Test
	void testUpdateOverflow() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		
		Integer lastIndex = list.size() -1; 
		
		list.update(lastIndex, 30);
		
		final Integer indexOverflow = lastIndex +1;  
		assertThrows(Exception.class, () -> {
			list.update(indexOverflow, 40);
		});
	}

	@Test
	void testExchangeValuesAtFirst() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		list.set(30);
		list.set(40);
				
		list.exchangeValuesAt(1, 2);

		Integer firstValue = list.get(1);
		assertEquals(Integer.valueOf(30), firstValue);
		
		Integer lastValue = list.get(2);
		assertEquals(Integer.valueOf(20), lastValue);
	}
	
	@Test
	void testRemoveFirst() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		
		list.removeFirst();
		
		Integer firstValue = list.get(0);
		assertEquals(Integer.valueOf(20), firstValue);
	}
	
	@Test
	void testRemoveLast() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		
		list.removeLast();
		
		Integer size = list.size() -1;
		
		Integer firstValue = list.get(size);
		assertEquals(Integer.valueOf(10), firstValue);
	}
	
	@Test
	void testRemoveMiddle() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		list.remove(1);
		
		Integer firstValue = list.get(1);
		assertEquals(Integer.valueOf(30), firstValue);
	}
	
	@Test
	void testGetFirst() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer firstValue = list.getFirst();
		assertEquals(Integer.valueOf(10), firstValue);
	}
	
	@Test
	void testGetMiddle() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer firstValue = list.get(1);
		assertEquals(Integer.valueOf(20), firstValue);
	}
	
	@Test
	void testGetLast() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer firstValue = list.getLast();
		assertEquals(Integer.valueOf(30), firstValue);
	}
	
	@Test
	void testGetFirstIndex() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer value = list.getIndex(10);
		assertEquals(Integer.valueOf(0), value);
	}
	
	@Test
	void testGetMiddleIndex() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer value = list.getIndex(20);
		assertEquals(Integer.valueOf(1), value);
	}
	
	@Test
	void testGetLastIndex() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer value = list.getIndex(30);
		assertEquals(Integer.valueOf(2), value);
	}
	
	@Test
	void testPush() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		list.push(40);
		
		Integer value = list.getLast();
		assertEquals(Integer.valueOf(40), value);
		
		Integer size = list.size();
		assertEquals(Integer.valueOf(4), size);
	}
	
	@Test
	void testPop() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer value = list.pop();
		assertEquals(Integer.valueOf(30), value);
		
		Integer size = list.size();
		assertEquals(Integer.valueOf(2), size);
	}	
	
	@Test
	void testEnqueue() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		list.enqueue(40);
		
		Integer value = list.getLast();
		assertEquals(Integer.valueOf(40), value);
	}	
	
	@Test
	void testDequeue() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		Integer value = list.dequeue();
		assertEquals(Integer.valueOf(30), value);
		
		Integer size = list.size();
		assertEquals(Integer.valueOf(2), size);
	}
	
	@Test
	void testHead() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		
		Integer value = list.head();
		assertEquals(Integer.valueOf(10), value);
	}
	
	@Test
	void testContains() throws Exception {
		List<Integer> list = new List<Integer>();

		list.set(10);
		list.set(20);
		list.set(30);
		
		boolean contains = list.contains(20);
		assertEquals(Boolean.TRUE, contains);
	}
	
	@Test
	void testNotContains() throws Exception {
		List<Integer> list = new List<Integer>();
		
		list.set(10);
		list.set(20);
		list.set(30);
		
		boolean contains = list.contains(50);
		assertEquals(Boolean.FALSE, contains);
	}
}
