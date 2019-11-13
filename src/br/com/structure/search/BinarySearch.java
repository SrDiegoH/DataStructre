package br.com.structure.search;

import static br.com.structure.util.ComparableHelper.isBiggerThen;
import static br.com.structure.util.ComparableHelper.isEqualsOrSmallerThen;
import static br.com.structure.util.ComparableHelper.isSmallerThen;

import java.util.List;

public class BinarySearch {
	    		
    public static <T extends Comparable<T>> T get(T [] array, T value){
        int left = 0;
        int right = array.length - 1;
        int size;
        
        while(left <= right) {
        	size = (left + right) / 2;
        	
        	System.out.print("\nValor: " + array[size]);
        	
            if (isSmallerThen(array[size], value)) {
            	left = size +1;
            } else if (isBiggerThen(array[size], value)) {
            	right = size -1;
            } else {
            	return array[size];
            }
        }

        return null;
    }
    
    public static <T extends Comparable<T>> T get(List<T> list, T value){
        int left = 0;
        int right = list.size() - 1;
        int size;
        
        while(left <= right) {
        	size = (left + right) / 2;
        	
        	System.out.print("\nValor: " + list.get(size));
        	
            if (isSmallerThen(list.get(size), value)) {
            	left = size +1;
            } else if (isBiggerThen(list.get(size), value)) {
            	right = size -1;
            } else {
            	return list.get(size);
            }
        }

        return null;
    }
    
    public static <T extends Comparable<T>> T getLeftMost(T [] array, T value){
    	int left = 0;
    	int right = array.length;
    	int size;
    	
    	while(left < right) {
    		size = (left + right) / 2;
    		
    		System.out.print("\nValor: " + array[size]);
    		
    		if (isSmallerThen(array[size], value)) {
    			left = size +1;
    		} else {
    			right = size;
    		}
    	}
    	
    	return array[left];
    }
    
    public static <T extends Comparable<T>> T getLeftMost(List<T> list, T value){
    	int left = 0;
    	int right = list.size();
    	int size;
    	
    	while(left < right) {
    		size = (left + right) / 2;
    		
    		System.out.print("\nValor: " + list.get(size));
    		
    		if (isSmallerThen(list.get(size), value)) {
    			left = size +1;
    		} else { 
    			right = size;
    		}
    	}
    	
    	return list.get(left);
    }
    
    public static <T extends Comparable<T>> T getRightMost(T [] array, T value){
    	int left = 0;
    	int right = array.length;
    	int size;
    	
    	while(left < right) {
    		size = (left + right) / 2;
    		
    		System.out.print("\nValor: " + array[size]);
    		
    		if (isEqualsOrSmallerThen(array[size], value)) {
    			left = size +1;
    		} else {
    			right = size;
    		}
    	}
    	
    	return array[left -1];
    }
    
    public static <T extends Comparable<T>> T getRightMost(List<T> list, T value){
    	int left = 0;
    	int right = list.size();
    	int size;
    	
    	while(left < right) {
    		size = (left + right) / 2;
    		
    		System.out.print("\nValor: " + list.get(size));
    		
    		if (isEqualsOrSmallerThen(list.get(size), value)) {
    			left = size +1;
    		} else {
    			right = size;
    		}
    	}
    	
    	return list.get(left -1);
    }
    
    public static void main(String[] args) {
    	Integer [] array = { 1, 5, 8, 11, 20, 50, 55, 110 };
    	
        for (int i = 0; i < array.length; i ++) {
            System.out.print(array[i] + " ");
        }
        
        System.out.println("\nResult: " + BinarySearch.get(array, 55));
        System.out.println();
        System.out.println("\nResult: " + BinarySearch.getLeftMost(array, 55));
        System.out.println();
        System.out.println("\nResult: " + BinarySearch.getRightMost(array, 55));
    }
}
