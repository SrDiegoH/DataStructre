package br.com.structure.tree.node;

public class TreeNode<T> {
	private T value;
	private TreeNode<T> right, left;

	public TreeNode(T valor) {
		setValue(valor);
		setLeft(null);
		setRight(null);
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
