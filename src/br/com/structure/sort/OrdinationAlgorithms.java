package br.com.structure.sort;

import static br.com.structure.util.ComparableHelper.isBiggerThen;
import static br.com.structure.util.ComparableHelper.isEqualsOrSmallerThen;
import static br.com.structure.util.ComparableHelper.isSmallerThen;

public class OrdinationAlgorithms {

	public static <T extends Comparable<T>> T[] boubbleSort(T[] array) {
		T item;

		for (int i = 0; i < array.length; i++) {

			for (int j = 0; j < array.length; j++) {

				if (isBiggerThen(array[j], array[i])) {
					item = array[i];

					array[i] = array[j];

					array[j] = item;
				}
			}
		}

		return array;
	}

	public static <T extends Comparable<T>> T[] insertionSort(T[] array) {
		T item;
		int index;

		for (int i = 1; i < array.length; i++) {
			item = array[i];

			index = i - 1;

			while (index >= 0 && isSmallerThen(item, array[index])) {
				array[index + 1] = array[index];

				index--;
			}

			array[index + 1] = item;
		}

		return array;
	}

	public static <T extends Comparable<T>> T[] selectionSort(T[] array) {
		T item;
		int index;

		for (int i = 0; i < array.length; i++) {
			index = i;

			for (int j = i; j < array.length; j++) {
				if (isSmallerThen(array[j], array[index])) {
					index = j;
				}
			}

			item = array[i];
			array[i] = array[index];
			array[index] = item;
		}

		return array;
	}

	public static <T extends Comparable<T>> T[] shellSort(T[] array) {
		int size = 1;
		while (size < array.length) {
			size = size * 3 + 1;
		}
		size = size / 3;

		int index;

		T item;

		while (size > 0) {
			for (int i = size; i < array.length; i++) {
				item = array[i];
				index = i;

				while (index >= size && isBiggerThen(array[index - size], item)) {
					array[index] = array[index - size];
					index -= size;
				}

				array[index] = item;
			}

			size /= 2;
		}

		return array;
	}

	public static <T extends Comparable<T>> T[] mergeSort(T[] array) {
		mergeSort(array, 0, array.length - 1);
		return array;
	}

	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> void mergeSort(T[] array, int begin, int end) {
		if (end <= begin) {
			return;
		}

		int middle = (begin + end) / 2;

		mergeSort(array, begin, middle);

		mergeSort(array, middle + 1, end);

		T[] leftArray = (T[]) new Comparable[middle - begin + 1];

		T[] rightArray = (T[]) new Comparable[end - middle];

		for (int i = 0; i <= middle - begin; i++) {
			leftArray[i] = array[begin + i];
		}

		for (int i = 0; i <= end - middle - 1; i++) {
			rightArray[i] = array[middle + 1 + i];
		}

		merge(array, leftArray, rightArray, begin, end);
	}

	private static <T extends Comparable<T>> void merge(T[] finalArray, T[] leftArray, T[] rightArray, int begin,
			int end) {
		int leftIndex = 0;
		int rightIndex = 0;

		for (int i = begin; i <= end; i++) {

			if (leftIndex < leftArray.length && rightIndex < rightArray.length) {

				if (isSmallerThen(leftArray[leftIndex], rightArray[rightIndex])) {
					finalArray[i] = leftArray[leftIndex++];
				} else {
					finalArray[i] = rightArray[rightIndex++];
				}

			} else if (leftIndex < leftArray.length) {
				finalArray[i] = leftArray[leftIndex++];

			} else if (rightIndex < rightArray.length) {
				finalArray[i] = rightArray[rightIndex++];
			}
		}
	}

	public static <T extends Comparable<T>> T[] quickSort(T[] array) {
		quickSort(array, 0, array.length - 1);
		return array;
	}

	private static <T extends Comparable<T>> void quickSort(T[] array, int begin, int end) {
		if (begin < end) {
			int pivotIndex = split(array, begin, end);

			quickSort(array, begin, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, end);
		}
	}

	private static <T extends Comparable<T>> int split(T[] array, int begin, int end) {
		T pivotItem = array[begin];

		int i = begin + 1;
		int j = end;

		while (i <= j) {
			if (isEqualsOrSmallerThen(array[i], pivotItem)) {
				i++;
			} else if (isSmallerThen(pivotItem, array[j])) {
				j--;
			} else {
				T item = array[i];
				array[i] = array[j];
				array[j] = item;

				i++;
				j--;
			}
		}

		array[begin] = array[j];
		array[j] = pivotItem;

		return j;
	}

	public static void main(String[] args) {
		Integer array[] = { 6, 3, 2, 10, 1, 9, 4, 7, 5, 8 };

		// array = boubbleSort(array);
		// array = insertionSort(array);
		// array = selectionSort(array);
		// array = shellSort(array);
		array = mergeSort(array);
		// array = quickSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
}
