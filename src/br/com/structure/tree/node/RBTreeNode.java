package br.com.structure.tree.node;

import br.com.structure.tree.color.RBTreeColor;

public class RBTreeNode<T> extends TreeNode<T>{
	
	private RBTreeColor color;

	public RBTreeNode(T valor) {
		super(valor);
		setColor(RBTreeColor.RED);
	}

	public RBTreeColor getColor() {
		return color;
	}

	public void setColor(RBTreeColor color) {
		this.color = color;
	}
}
