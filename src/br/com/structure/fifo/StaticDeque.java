package br.com.structure.fifo;

public class StaticDeque<T> {
	private int begin, end, size, total;
	private T [] vector;

	@SuppressWarnings("unchecked")
	public StaticDeque(int size) {
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

	public T peek() {
		return vector[begin];
	}

	public int size() {
		return total;
	}

	public boolean setBegin(T value) {
		if (isFull()) {
			return false;
		}

		begin = begin - 1 < 0 ? size - 1 : begin - 1;

		vector[begin] = value;

		total++;

		return true;
	}

	public boolean setEnd(T value) {
		if (isFull()) {
			return false;
		}

		vector[end] = value;

		end = (end + 1) % size;

		total++;

		return true;
	}

	public boolean removeBegin() {
		if (isEmpty()) {
			return false;
		}

		begin = (begin + 1) % size;

		total--;

		return true;
	}

	public boolean removeEnd() {
		if (isEmpty()) {
			return false;
		}

		end = end - 1 < 0 ? size - 1 : end - 1;

		total--;

		return true;
	}

	public T get(int i) {
		return vector[i];
	}

	public void exibirDeque() {
		if (!isEmpty()) {
			for (int i = begin; i <= total; i++) {
				// if (i == total) i = 0;
				System.out.println("(" + i + ", " + vector[i] + ")");
			}
		}
	}
}
