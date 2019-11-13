package br.com.structure.tree;

import static br.com.structure.util.ComparableHelper.isBiggerThen;
import static br.com.structure.util.ComparableHelper.isEqualsAs;
import static br.com.structure.util.ComparableHelper.isSmallerThen;

import br.com.structure.tree.node.AVLTreeNode;
import br.com.structure.tree.node.TreeNode;

public class AVLTree<T extends Comparable<T>> implements Tree<T> {
	private AVLTreeNode<T> root;
    
    private int size;
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public AVLTree(){
    	setClearState();
    }    
    
    private void setClearState() {
    	root = null;
        size = 0;
    }
    
    public void set(T value) throws Exception {
        root = set(value, root);
    }
    
    private AVLTreeNode<T> set(T value, AVLTreeNode<T> node) throws Exception {
        if(node == null) {
            size ++;
        	node = new AVLTreeNode<T>(value);
        } else if (isBiggerThen(node.getValue(), value)) {
        	AVLTreeNode<T> leftNode = set(value, (AVLTreeNode<T>) node.getLeft());
            
        	node.setLeft(leftNode);
        } else if (isSmallerThen(node.getValue(), value)) {
        	AVLTreeNode<T> rightNode = set(value, (AVLTreeNode<T>) node.getRight());
        	
            node.setRight(rightNode);
        } else { 
        	throw new Exception("Value already exists");
        }
        
        node = balance(node);
        return (AVLTreeNode<T>) node;
    }

    public void removeAll() {
    	setClearState();
    }
    
    public void remove(T value){
        remove(value, root);
    }
    
    private AVLTreeNode<T> remove(T value, TreeNode<T> node) {
        if (node == null) {
        	return null;
        } else if (isBiggerThen(node.getValue(), value)) {
        	node.setLeft(remove(value, node.getLeft()));
        	
        } else if (isSmallerThen(node.getValue(), value)) {
        	node.setRight(remove(value, node.getRight()));
        	
        } else {
            if (node.getLeft () == null && node.getRight () == null) {
                size --;
            	node = null;
            } else if (node.getLeft () != null && node.getRight () != null){
            	TreeNode<T> greaterLeft =  node.getLeft();
            	
                while (greaterLeft.getRight() != null) {
                	greaterLeft = greaterLeft.getRight();
                }
                
                node.setValue (greaterLeft.getValue());
                
                remove(greaterLeft.getValue(), node.getLeft());
                
                if(isEqualsAs(node.getLeft().getValue(), node.getValue())) {
                    size --;
                	node.setLeft (null);
                }
                
                if(isEqualsAs(node.getRight ().getValue(), node.getValue ())) {
                    size --;
                	node.setRight (null);
                }
            } else {
                if(node == root) {
                	root = (AVLTreeNode<T>)  (node.getLeft() != null? node.getLeft() : node.getRight());
                }
                
                node = (node.getLeft() != null)? node.getLeft() : node.getRight();
                size --;
            }
        }
        
        return (AVLTreeNode<T>) node;
    }    
    

    public AVLTreeNode<T> get(T value){
        return get(value, root);
    } 
    
    private AVLTreeNode<T> get(T value, TreeNode<T> node){
        if(node == null) {
        	
            return null;
        } else if (isBiggerThen(node.getValue(), value)) {
        	node = get(value, node.getLeft());
        	
        } else if (isSmallerThen(node.getValue(), value)) { 
        	node = get(value, node.getRight());  
    	}
        return (AVLTreeNode<T>) node;
    }

    
    public AVLTreeNode<T> getLowerValue(){
        TreeNode<T> node = root.getLeft();
        
        while(node.getLeft() != null) { 
        	node = node.getLeft();
        }
        
        return (AVLTreeNode<T>) node;
    }

    public AVLTreeNode<T> getGreaterValue(){
        TreeNode<T> node = root.getRight();
        
        while(node.getRight () != null) {
        	node = node.getRight();
        }
        
        return (AVLTreeNode<T>) node;
    }
    
    private AVLTreeNode<T> getPreviousNode(T value) {
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
        
    	return (node != null && isEqualsAs(node.getValue(), value))? (AVLTreeNode<T>) previous : null;        
    }
    
    private int getHeight(AVLTreeNode<T> node) {
        return node == null? -1 : node.getHeight();
    }
    
    private int greatestHeight (int leftHeight, int rightHeight) {
        return leftHeight > rightHeight? leftHeight : rightHeight;
    }
    
    private int getBalanceFactor (AVLTreeNode<T> node) {
        return getHeight((AVLTreeNode<T>) node.getLeft()) - getHeight((AVLTreeNode<T>) node.getRight());
    }
    
    private AVLTreeNode<T> simpleRightRotation (AVLTreeNode<T> secondNode) {
    	AVLTreeNode<T> firstNode = (AVLTreeNode<T>) secondNode.getLeft();
    	
    	secondNode.setLeft(firstNode.getRight());
    	
    	firstNode.setRight(secondNode);
    	
    	
    	int nodeLeftHeight = getHeight((AVLTreeNode<T>) secondNode.getLeft());
    	int nodeRightHeight = getHeight((AVLTreeNode<T>) secondNode.getRight());
    	
    	int greatestHeight = greatestHeight(nodeLeftHeight, nodeRightHeight);
    	secondNode.setHeight(greatestHeight + 1);        
    	
    	
    	nodeLeftHeight = getHeight((AVLTreeNode<T>) firstNode.getLeft());
    	
    	greatestHeight = greatestHeight(nodeLeftHeight, secondNode.getHeight());
    	firstNode.setHeight(greatestHeight + 1);
    	
    	return firstNode;
    } 
        
    private AVLTreeNode<T> simpleLeftRotation (AVLTreeNode<T> firstNode) {
    	AVLTreeNode<T> secondNode = (AVLTreeNode<T>) firstNode.getRight();
    	
    	firstNode.setRight(secondNode.getLeft());
    	
    	secondNode.setLeft(firstNode);
    	
    	
    	int nodeLeftHeight = getHeight((AVLTreeNode<T>) firstNode.getLeft());
    	int nodeRightHeight = getHeight((AVLTreeNode<T>) firstNode.getRight());
    	
    	int greatestHeight = greatestHeight(nodeLeftHeight, nodeRightHeight);
    	firstNode.setHeight(greatestHeight + 1);
    	
    	
    	nodeRightHeight = getHeight((AVLTreeNode<T>) secondNode.getRight());
    	
    	greatestHeight = greatestHeight(nodeRightHeight, firstNode.getHeight());
    	secondNode.setHeight(greatestHeight + 1);
    	
    	return secondNode;
    }

    private AVLTreeNode<T> doubleRightRotation(AVLTreeNode<T> node) {
    	AVLTreeNode<T> rotationNode = simpleLeftRotation((AVLTreeNode<T>) node.getLeft());
    	
        node.setLeft(rotationNode);

        rotationNode = simpleRightRotation(node);
        
        return rotationNode;
    } 
    
    private AVLTreeNode<T> doubleLeftRotation (AVLTreeNode<T> node) {
        AVLTreeNode<T> rotationNode = simpleRightRotation((AVLTreeNode<T>) node.getRight());
    	
        node.setRight(rotationNode);
        
        rotationNode = simpleLeftRotation(node);
        
        return rotationNode;
    }    

    public AVLTreeNode<T> balance(AVLTreeNode<T> node) {
    	
    	if (getBalanceFactor(node) == 2) {
    		int balanceFactor = getBalanceFactor((AVLTreeNode<T>) node.getLeft());
    		
            node = (balanceFactor > 0)? simpleRightRotation (node) : doubleRightRotation (node);
        } else if (getBalanceFactor (node) == -2) {
        	int balanceFactor = getBalanceFactor((AVLTreeNode<T>) node.getRight());
        	
            node = (balanceFactor < 0)? simpleLeftRotation (node) : doubleLeftRotation (node);
        }

    	int nodeLeftHeight = getHeight((AVLTreeNode<T>) node.getLeft());
    	int nodeRightHeight = getHeight((AVLTreeNode<T>) node.getRight());
    	
    	int greatestHeight = greatestHeight(nodeLeftHeight, nodeRightHeight);
        node.setHeight(greatestHeight + 1);
        
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

    public void showAllAsTree() {
        if (!isEmpty()){ 
            System.out.println (root.getValue() + " ROOT");

            showAllAsTree(root.getRight(), " |__", "   ");
            showAllAsTree(root.getLeft(), " |__", "   ");
        }
    }
    

    private void showAllAsTree (TreeNode<T> node, String separatorFlag, String space) {
        if (node != null) {
            TreeNode<T> previous = getPreviousNode (node.getValue());
            
        	System.out.println(separatorFlag + node.getValue() + (node.equals(previous.getLeft())? " L" : " R"));
            
            showAllAsTree(node.getRight (), space + separatorFlag, space);
            showAllAsTree(node.getLeft (), space + separatorFlag, space);
        }
    }
    
    public static void main(String args[]){
    	try {
    		AVLTree<Integer> ab = new AVLTree<Integer>();
	        
			ab.set (11);
			ab.set (10);
			ab.set (20);
			ab.set (30);
			ab.set (40);
			ab.set (15);
			ab.set (12);
			ab.set (18);
			ab.set (16);
			
			ab.showAllAsTree();
			System.out.println(ab.get(30).getValue());
//			ab.removeAll();
			ab.remove(20);
//			ab.remove(18);
			ab.showAllAsTree();
			
		} catch (Exception e) {
		}
    }

}
