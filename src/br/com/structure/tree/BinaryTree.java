package br.com.structure.tree;

import static br.com.structure.util.ComparableHelper.isBiggerThen;
import static br.com.structure.util.ComparableHelper.isEqualsAs;
import static br.com.structure.util.ComparableHelper.isSmallerThen;

import br.com.structure.tree.node.TreeNode;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    private TreeNode<T> root;
    
    private int size;
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return root == null;
    }  
    
    public BinaryTree(){
    	setClearState();
    }    
    
    private void setClearState() {
    	root = null;
        size = 0;
    }
    
    public void set(T value) throws Exception {
        root = set(value, root);
    } 
    
    private TreeNode<T> set(T value, TreeNode<T> node) throws Exception {
        if(node == null) {
            size ++;
        	return new TreeNode<T>(value);
        	
        } else if (isSmallerThen(node.getValue(), value)) {
            node.setRight(set(value, node.getRight()));
            
        } else if (isBiggerThen(node.getValue(), value)) {
            node.setLeft(set(value, node.getLeft()));
            
        } else {
        	throw new Exception("Value already exists");
        }        
        return node;
    }
    
    public void removeAll() {
    	setClearState();
    }
    
    public void remove(T value){
        remove(value, root);
    }
    
    private TreeNode<T> remove(T value, TreeNode<T> node) {        
        if (node == null) {
        	return null;
        	
        } else if (isBiggerThen(node.getValue(), value)) {
        	node.setLeft(remove(value, node.getLeft()));
        	
        } else if (isSmallerThen(node.getValue(), value)) {
    		node.setRight(remove(value, node.getRight()));
    		
		} else {
            if (node.getLeft() == null && node.getRight() == null) {
                size --;
            	node = null;
            } else if (node.getLeft() != null && node.getRight() != null){
                TreeNode<T> greaterLeft = node.getLeft();
                
                while (greaterLeft.getRight() != null) {
                    greaterLeft = greaterLeft.getRight();
                }
                
                node.setValue(greaterLeft.getValue());
                
                remove(greaterLeft.getValue(), node.getLeft());
                
                if(isEqualsAs(node.getLeft().getValue(), node.getValue())) { 
                    size --;
                	node.setLeft(null);
                }
                
                if(isEqualsAs(node.getRight().getValue(), node.getValue())) { 
                    size --;
                	node.setRight(null);
                }
            } else {
                if(node == root) {
                	root = (node.getLeft() != null)? node.getLeft() : node.getRight();
                }
                
                node = (node.getLeft() != null)? node.getLeft() : node.getRight();
                size --;
            }
        }
        
        return node;
    }
    
    public TreeNode<T> get(T value){
        return get(value, root);
    } 
    
    private TreeNode<T> get(T value, TreeNode<T> node){
        if(node == null) {
            return null;
            
        } else if (isBiggerThen(node.getValue(), value)) {
    		node = get(value, node.getLeft());
    		
        } else if (isSmallerThen(node.getValue(), value)) { 
        	node = get(value, node.getRight());
        }	
        return node;
    }
    
    public TreeNode<T> getLowerValue(){
        TreeNode<T> node = root.getLeft();
        
        while(node.getLeft() != null) { 
        	node = node.getLeft();
        }
        
        return node;
    }

    public TreeNode<T> getGreaterValue(){
        TreeNode<T> node = root.getRight();
        
        while(node.getRight() != null) {
        	node = node.getRight();
        }
        
        return node;
    }
    
    private TreeNode<T> getPreviousNode(T value) {
        TreeNode<T> node = root;
        TreeNode<T> previous = null;
        
        while (node != null && !isEqualsAs(node.getValue(), value)) {
            previous = node;
            
        	if (isBiggerThen(node.getValue(), value)) {
        		node = node.getLeft();
            } else  {
            	node = node.getRight();
            }
        }
        
    	return (node != null && isEqualsAs(node.getValue(), value))? previous : null;        
    }
    
    public void showAllLeft(){
        TreeNode<T> node = root;
        
        do {
            System.out.println (node.getValue());
            node = node.getLeft();
        } while (node != null);
    }
    
    public void showAllRight(){
        TreeNode<T> node = root;
        
        do {
            System.out.println (node.getValue());
            node = node.getRight();
        } while (node != null);
    }
    
    public void showAll() {
    	showAll(root);
    }
    
    private TreeNode<T> showAll(TreeNode<T> node) {
        if (node != null) {
            System.out.print (node.getValue() + " - ");
            
            showAll (node.getLeft());
            
            showAll (node.getRight());
        }
        return node;
    }
    
    public void showAllAsc() {
    	showAllAsc(root);
    }
    
    private TreeNode<T> showAllAsc(TreeNode<T> node) {
    	if (node != null) {
    		showAllAsc (node.getLeft());
    		
    		System.out.print (node.getValue() + " - ");
    		
    		showAllAsc (node.getRight());
    	}
    	return node;
    }
    
    public void showAllDesc() {
    	showAllDesc(root);
    }
    
    private TreeNode<T> showAllDesc(TreeNode<T> node) {
    	if (node != null) {
    		showAllDesc (node.getRight());
    		
    		System.out.print (node.getValue() + " - ");
    		
    		showAllDesc (node.getLeft());
    	}
    	
    	return node;
    }

    public void showAllAsTree () {
        if (!isEmpty()){ 
            System.out.println (root.getValue() + " ROOT");

            showAllAsTree(root.getRight(), " |__", "   ");
            showAllAsTree(root.getLeft(), " |__", "   ");
        }
    }
    
    private void showAllAsTree (TreeNode<T> node, String separatorFlag, String space) {
        if (node != null) {
            TreeNode<T> previous = getPreviousNode (node.getValue());
            
        	System.out.println(separatorFlag + node.getValue () + (node.equals (previous.getLeft ())? " L" : " R"));
            
            showAllAsTree(node.getRight (), space + separatorFlag, space);
            showAllAsTree(node.getLeft (), space + separatorFlag, space);
        }
    }
    
    public static void main(String args[]){
    	try {
	        BinaryTree<Integer> ab = new BinaryTree<Integer>();
	        
			ab.set (11);
			ab.set (10);
			ab.set (20);
			ab.set (30);
			ab.set (40);
			ab.set (15);
			ab.set (12);
			ab.set (18);
			ab.set (16);
			
			System.out.println(ab.size());
			ab.showAllAsTree();
			System.out.println(ab.get(30).getValue());
//			ab.removeAll();
			ab.remove(20);
//			ab.remove(18);
			ab.showAllAsTree();
			System.out.println(ab.size());
			
		} catch (Exception e) {
		}
    }
    
}