package com.boes.interviews;

public class Node {
	
	Object value;
	Node next;
	
	public Node(Object value, Node next) {
		this.value = value;
		this.next = next;
	}
	
	public Node(Object value) {
		this(value, null);
	}
	
	public Node cons(Object value) {
		return new Node(value, this);
	}
		
	public Node remove(int index) {
		if (index == 0) return next;
		else return new Node(value, next.remove(index-1));
	}
		
	public Node insert(Node node, int index) {
		if (index == 0) return cons(node);
		else return new Node(value, next.insert(node, index-1));
	}

	public Node append(Node other) {
		if (next == null) return new Node(value, other);
		else return new Node(value, next.append(other));
	}

	public Node reverse() {
		// O(n^2)
		if (next == null) return new Node(value);
		else return next.reverse().append(new Node(value));
	}
	
	public Node reverse2() {
		return reverse(null);
	}
	
	private Node reverse(Node result) {
		// O(n), Unfortunately JVM does optimize tail call recursion
		if (next == null) return new Node(value, result);
		else return next.reverse(new Node(value, result));
	}
	
	public Node reverse3() {
		// O(n), Unrolls recursion into loop avoiding lack of TCO in JVM (see reverse2)
		Node result = null;
		for (Node current = new Node(value, next); current != null; current = current.next)
			result = new Node(current.value, result);
		return result;
	}

	public Node reverse4() {
		// O(n), reverse in place -- destructive!
		Node lst = this;
		Node newlst = null;
		while (lst != null) {
			Node rest = lst.next;
			lst.next = newlst;
			newlst = lst;
			lst = rest;
		}
		return newlst;
	}
		
	public int find(Node node) {
		return find(node, 0);
	}
	
	private int find(Node node, int index) {
		if (this.equals(node)) return index;
		else return next.find(node, index+1);
	}
	
	public Node get(int index) {
		if (index == 0) return this;
		else return next.get(index-1);
	}
	
	public Node getLast() {
		if (next == null) return this;
		else return next.getLast();
	}
	
	public Node getMthToLast(int index) {
		Node leader = get(index);
		return get(this, leader);
	}
	
	private Node get(Node follower, Node leader) {
		if (leader.next == null) return follower;
		else return get(follower.next, leader.next);
	}
	
	@Override
	public boolean equals(Object other) {
		Node otherNode = (Node) other;
		if (next == null && otherNode.next == null) return value.equals(otherNode.value);
		else if (next == null || otherNode.next == null) return false;
		else return value.equals(otherNode.value) && next.equals(otherNode.next);
	}
	
	@Override
	public String toString() {
		if (next == null) return value.toString();
		else return "(" + value.toString() + ", " + next.toString() + ")";
	}
	
}
