package br.com.structure.util;

public class ComparableHelper {
	
	public static <T extends Comparable<T>> boolean isBiggerThen(T firsrItem, T secondItem) {
		return firsrItem.compareTo(secondItem) > 0;
	}
    
	public static <T extends Comparable<T>> boolean isEqualsOrBiggerThen(T firsrItem, T secondItem) {
		return firsrItem.compareTo(secondItem) >= 0;
	}
		
	public static <T extends Comparable<T>> boolean isSmallerThen(T firsrItem, T secondItem) {
		return firsrItem.compareTo(secondItem) < 0;
	}
	
	public static <T extends Comparable<T>> boolean isEqualsOrSmallerThen(T firsrItem, T secondItem) {
		return firsrItem.compareTo(secondItem) <= 0;
	}
	
	public static <T extends Comparable<T>> boolean isEqualsAs(T firsrItem, T secondItem) {
		return firsrItem.compareTo(secondItem) == 0;
	}
}