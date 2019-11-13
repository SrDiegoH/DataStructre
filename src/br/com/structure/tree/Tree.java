package br.com.structure.tree;

import br.com.structure.tree.node.TreeNode;

public interface Tree <T extends Comparable<T>> {
	int size();
    boolean isEmpty();
    void set(T value) throws Exception;
    void removeAll();
    void remove(T valor);    
    TreeNode<T> get(T value);
    TreeNode<T> getLowerValue();
    TreeNode<T> getGreaterValue();    
    void showAllLeft();    
    void showAllRight();    
    void showAll();    
    void showAllAsc();
    void showAllDesc();
    void showAllAsTree();
}
