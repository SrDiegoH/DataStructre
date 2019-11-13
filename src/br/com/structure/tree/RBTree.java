package br.com.structure.tree;

import br.com.structure.tree.color.RBTreeColor;
import br.com.structure.tree.node.RBTreeNode;
import br.com.structure.tree.node.TreeNode;

import static br.com.structure.util.ComparableHelper.*;

public class RBTree<T extends Comparable<T>> implements Tree<T> {
	private RBTreeNode<T> root;
    
    private int size;
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public RBTree(){
    	setClearState();
    }    
    
    private void setClearState() {
    	root = null;
        size = 0;
    }
    
    public void set(T value) throws Exception {
        root = set(value, root);
        
        if (root != null) {
        	root.setColor(RBTreeColor.BLACK);
        }
    }
    
    private RBTreeNode<T> set(T value, TreeNode<T> node) throws Exception {
    	if(node == null) {
            size ++;
        	node = new RBTreeNode<T>(value);
        } else if (isBiggerThen(node.getValue(), value)) {
        	TreeNode<T> leftNode = set(value,  node.getLeft());
        	
            node.setLeft(leftNode);
            
        } else if (isSmallerThen(node.getValue(), value)) {
        	TreeNode<T> rightNode = set(value, node.getRight());
        	
        	node.setRight(rightNode);
        	
        } else { 
        	throw new Exception("Value already exists");
        }
        
    	
        if(color(node.getRight()) == RBTreeColor.RED && 
           color(node.getLeft())  == RBTreeColor.BLACK) {
        	node = leftRotation((RBTreeNode<T>) node);
        }
        
        if(color(node.getLeft()) == RBTreeColor.RED && 
           color(node.getLeft().getLeft()) == RBTreeColor.RED) {
        	node = rightRotation((RBTreeNode<T>) node);
        }
        
        if(color(node.getLeft()) == RBTreeColor.RED && 
           color(node.getRight ()) == RBTreeColor.RED) {
        	changeNodeColor(node);
        }
        
        return (RBTreeNode<T>) node;
    }

    public void removeAll() {
    	setClearState();
    }
    
    public void remove(T valor){
    	if(get(valor) != null){
    		root = remove(valor, root);
    		
    		if (root != null) {
    			root.setColor(RBTreeColor.BLACK); 
    		}
    	}
    }
    
    private RBTreeNode<T> remove(T value, RBTreeNode<T> node) {
    	if (isBiggerThen(node.getValue(), value)) {
    		
        	if (color((RBTreeNode<T>) node.getLeft()) == RBTreeColor.BLACK && 
        		color((RBTreeNode<T>) node.getLeft().getLeft()) == RBTreeColor.BLACK) {
        		node = moveRedNodeToLeft(node);
        	}
        	
        	node.setLeft(remove(value, (RBTreeNode<T>) node.getLeft()));
        } else {
        	if (color((RBTreeNode<T>) node.getLeft()) == RBTreeColor.RED) {
        		node = rightRotation((RBTreeNode<T>) node);
        	}
        	
        	if (isEqualsAs(node.getValue(), value) && node.getRight() == null){
        		node = null;
        		return null;
        	}
        	
        	
        	if (color ((RBTreeNode<T>) node.getRight()) == RBTreeColor.BLACK && 
        		color ((RBTreeNode<T>) node.getRight().getLeft()) == RBTreeColor.BLACK) {
        		node = moveRedNodeToLeft(node);
        	}
        	
        	if (isEqualsAs(node.getValue(), value)){
        		RBTreeNode<T> lowerNode = getLowerNode((RBTreeNode<T>) node.getRight());
        		
        		node.setValue(lowerNode.getValue());
        		
        		node.setRight(removeLower((RBTreeNode<T>) node.getRight()));
        	} else {
        		node.setRight (remove(value, (RBTreeNode<T>) node.getRight ()));
        	}
        }
    	
    	return balance(node);
    } 
    
    public RBTreeNode<T> removeLower(RBTreeNode<T> node){
    	if (node.getLeft() == null){
    		node = null;
    		return null;
    	}
    	
    	if (color((RBTreeNode<T>) node.getLeft()) == RBTreeColor.BLACK && 
    		color((RBTreeNode<T>) node.getLeft().getLeft()) == RBTreeColor.BLACK) {
    		node = moveRedNodeToLeft(node);
    	}
    	
    	node.setLeft(removeLower((RBTreeNode<T>) node.getLeft()));
    	
    	return balance(node);
    }
    
    public RBTreeNode<T> get(T value){
        return get(value, root);
    }
    
    private RBTreeNode<T> get(T value, TreeNode<T> node){
        if(node == null) {
            return null;
            
        } else if (isBiggerThen(node.getValue(), value)) {
        	node = get(value, node.getLeft());
        	
        } else if (isSmallerThen(node.getValue(), value)) { 
        	node = get(value, node.getRight());
    	}
        return (RBTreeNode<T>) node;
    }
    

    public RBTreeNode<T> getLowerValue(){
        TreeNode<T> node = root.getLeft();
        
        while(node.getLeft () != null) { 
        	node = node.getLeft();
        }
        
        return (RBTreeNode<T>) node;
    }

    public RBTreeNode<T> getGreaterValue(){
        TreeNode<T> node = root.getRight();
        
        while(node.getRight () != null) {
        	node = node.getRight();
        }
        
        return (RBTreeNode<T>) node;
    }
    
    private RBTreeNode<T> getPreviousNode(T value) {
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
        
    	return (node != null && isEqualsAs(node.getValue(), value))? (RBTreeNode<T>) previous : null;        
    }
    
    private RBTreeNode<T> leftRotation(RBTreeNode<T> firstNode){
    	RBTreeNode<T> secondNode = (RBTreeNode<T>) firstNode.getRight();
		
		firstNode.setRight(secondNode.getLeft());
		
		secondNode.setLeft (firstNode);
		secondNode.setColor(firstNode.getColor());
		
		 firstNode.setColor(RBTreeColor.RED);
				
		return secondNode;
	}
	
	private RBTreeNode<T> rightRotation(RBTreeNode<T> firstNode){
		RBTreeNode<T> secondNode = (RBTreeNode<T>) firstNode.getLeft();
		
		firstNode.setLeft (secondNode.getRight());
		
		secondNode.setRight (firstNode);
		secondNode.setColor (firstNode.getColor());
		
		firstNode.setColor (RBTreeColor.RED);
		
		return secondNode;
	} 

	private RBTreeColor color(TreeNode<T> node){
		return node == null? RBTreeColor.BLACK : ((RBTreeNode<T>) node).getColor();		
	}
	
	private void changeNodeColor(TreeNode<T> node){
		RBTreeColor changedColor = changeColor(((RBTreeNode<T>) node).getColor());
		
		((RBTreeNode<T>) node).setColor(changedColor);
		
		if(node.getLeft() != null) {
			TreeNode<T> leftNode =  node.getLeft();
			
			changedColor = changeColor(((RBTreeNode<T>) leftNode).getColor());
			
			((RBTreeNode<T>) leftNode).setColor(changedColor);
		}
		
		if(node.getRight() != null) {
			TreeNode<T> rightNode = node.getRight();

			changedColor = changeColor(((RBTreeNode<T>) rightNode).getColor());
			
			((RBTreeNode<T>) rightNode).setColor(changedColor);
		}
	}
	
	private RBTreeColor changeColor(RBTreeColor color) {
		return color == RBTreeColor.RED? RBTreeColor.BLACK : RBTreeColor.RED;
	}
	
	private RBTreeNode<T> moveRedNodeToLeft(RBTreeNode<T> node){
		changeNodeColor(node);
		
		if(color(node.getRight().getLeft()) == RBTreeColor.RED){
			node.setRight(rightRotation((RBTreeNode<T>) node.getRight()));
			
			node = (RBTreeNode<T>) leftRotation(node);
			
			changeNodeColor(node);
		}
		
		return (RBTreeNode<T>) node;
	}
	
	private RBTreeNode<T> balance(RBTreeNode<T> node){
		if(color (node.getRight()) == RBTreeColor.RED) {
			node = leftRotation(node);
		}
		
		if(node.getLeft () != null && 
		   color(node.getRight()) == RBTreeColor.RED && 
		   color(node.getLeft().getLeft()) == RBTreeColor.RED) {
		   node = rightRotation(node);
		}
		
		if(color(node.getLeft())  == RBTreeColor.RED && 
		   color(node.getRight()) == RBTreeColor.RED) {
			changeNodeColor(node);
		}
		
		return node;
	}
	
	public RBTreeNode<T> getLowerNode(RBTreeNode<T> node){
        while (node.getLeft() != null) {
        	node = (RBTreeNode<T>) node.getLeft();
        }
        return node;
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
    
    private void showAllAsTree(TreeNode<T> node, String separatorFlag, String space) {
        if (node != null) {
            TreeNode<T> previous = getPreviousNode (node.getValue());
            
        	System.out.println(separatorFlag + node.getValue() + "(" + ((RBTreeNode<T>) node).getColor() + ")" + (node.equals (previous.getLeft ())? " L" : " R"));
            
            showAllAsTree(node.getRight (), space + separatorFlag, space);
            showAllAsTree(node.getLeft (), space + separatorFlag, space);
        }
    }
    
    public static void main(String[] args) {
    	try {
			RBTree<Integer> av = new RBTree<Integer>();
			av.set(10);
			av.set(20);
			av.set(30);
			av.set(40);
			av.set(50);
			av.set(60);
			av.set(70);
	         
	        av.showAllAsTree();
	        System.out.println("\n");
	         
	        av.remove(15);
	        av.showAllAsTree();
        } catch (Exception e) {
		}
	}
}

