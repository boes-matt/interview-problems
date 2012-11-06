package com.boes.interviews;

public class NodeT {

	int value;
	NodeT left;
	NodeT right;
	
	public NodeT(int value, NodeT left, NodeT right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public NodeT(int value) {
		this(value, null, null);
	}
	
	public NodeT findBST(int value) {
		if (value == this.value) return this;
		else if (value < this.value && left != null) return left.findBST(value);
		else if (value > this.value && right != null) return right.findBST(value);
		else return null;
	}
	
	public NodeT insert(int value) {
		if (value < this.value && left != null) return new NodeT(this.value, left.insert(value), right);
		else if (value < this.value && left == null) return new NodeT(this.value, new NodeT(value), right);
		
		else if (value > this.value && right != null) return new NodeT(this.value, left, right.insert(value));
		else if (value > this.value && right == null) return new NodeT(this.value, left, new NodeT(value));
		
		return this;
	}
	
	public String toStringInOrder() {
		String s = "";
		if (left != null) s += left.toStringInOrder();
		s += value + ", ";
		if (right != null) s += right.toStringInOrder();
		return s;
	}
	
	public String toStringPreOrder() {
		String s = Integer.toString(value);
		if (left != null) s += ", " + left.toStringPreOrder();
		if (right != null) s += ", " + right.toStringPreOrder();
		return s;
	}
	
	public String toStringPostOrder() {
		String s = Integer.toString(value);
		if (right != null) s += ", " + right.toStringPostOrder();
		if (left != null) s += ", " + left.toStringPostOrder();
		return s;
	}
}
