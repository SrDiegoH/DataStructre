package br.com.structure.fifo;

public class PreferentialQueue<T> {
	private DynamicQueue<T> regular = new DynamicQueue<T>();
	private DynamicQueue<T> preferential = new DynamicQueue<T>();
	
	private int step;
	private int counter;

	public PreferentialQueue() {
		step = 2;
		counter = 2;
	}
	
	public PreferentialQueue(int step) {
		this.step = step;
		this.counter = step;
	}
	
	public void put(boolean isPreferencial, T valor) {
		if (isPreferencial) {
			preferential.put(valor);
		} else { 
			regular.put(valor);
		}
	}

	public T pull() {
		if (preferential.isEmpty() && regular.isEmpty()) {
			return null;
		} else if (!preferential.isEmpty() && !regular.isEmpty()) {
			if (counter == step) {
				counter = 0;
				return preferential.pull();
			} else {
				counter ++;
				return regular.pull();
			}
		} else {
			return preferential.isEmpty()? regular.pull() : preferential.pull();
		}
	}

	public void percorrerTudo() {
		regular.showAll();
		preferential.showAll();
	}

	public static void main(String args[]) {
		PreferentialQueue<Integer> f = new PreferentialQueue<Integer>(5);
		f.put(false, 0);
		f.put(false, 0);
		f.put(true,  1);
		f.put(false, 0);
		f.put(true,  1);
		f.put(false, 0);
		f.put(false, 0);
		f.put(false, 0);
		f.put(true,  1);
		f.put(true,  1);
		f.put(true,  1);
		f.put(false, 0);
		f.put(true,  1);
		f.put(true,  1);
		f.put(true,  1);
		f.put(false, 0);

		System.out.println(f.pull()); // 1
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 1
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 1
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 1
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 0
		System.out.println(f.pull()); // 1
		System.out.println(f.pull()); // 1
		System.out.println(f.pull()); // 1
		System.out.println(f.pull()); // 1
	}
}
