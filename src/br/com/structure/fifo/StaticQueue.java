package br.com.structure.fifo;

public class StaticQueue<T> {

	private int begin, end, size, total;
	private T vector[];

	@SuppressWarnings("unchecked")
	public StaticQueue(int size) {
		total = 0;
		begin = 0;
		end = 0;
		this.size = size;
		vector = (T[]) new Object[size];
	}

	public boolean isEmpty() {
		return total == 0;
	}

	public boolean isFull() {
		return total == size;
	}

	public int size() {
		return total;
	}

	public void put(T value) throws Exception {
		if (!isFull()) {
			vector[end] = value;

			total++;

			end++;
			if (end == size) {
				end = 0;
			}
		} else {
			throw new Exception("The Queue is Full!");
		}
	}

	public T pull() {
		if (!isEmpty()) {
			T value = vector[begin];
			vector[begin] = null;
			
			total--;
			
			begin++;
			if (begin == size) {
				begin = 0;
			}
			
			return value;
		}
		
		return null;
	}

	public void showAll() {
		if (!isEmpty()) {
			for (int i = begin; i <= total; i++) {
//				if (i == total) i = 0;
				if (vector[i] != null) {
					System.out.println("(" + i + ", " + vector[i] + ")");
				}
			}
		}
	}

	public T peek() {
		return vector[begin];
	}

	public static void main(String[] args) throws Exception {
		StaticQueue<Integer> fila = new StaticQueue<Integer>(4);

		fila.pull();
		fila.put(5);
		fila.pull();
		fila.put(7);
		fila.put(4);
		fila.put(1);
		fila.put(3);
		fila.pull();
		fila.pull();
		fila.pull();
		System.out.println(fila.isEmpty());
		fila.put(9);
		System.out.println(fila.peek());
		fila.put(15);
		System.out.println(fila.size());
		fila.showAll();
	}
}
