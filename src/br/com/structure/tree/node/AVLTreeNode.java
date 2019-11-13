package br.com.structure.tree.node;

public class AVLTreeNode<T> extends TreeNode<T>{

	private int height;
	
	public AVLTreeNode(T valor) {
		super(valor);
		setHeight(0);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
