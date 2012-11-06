package com.boes.interviews;

public class Stack {

	Node stack;
	
	public void push(Object value) {
		if (stack == null) stack = new Node(value);
		else stack = stack.cons(value);
	}
	
	public Object pop() {
		Object value = stack.get(0).value;
		stack = stack.remove(0);
		return value;
	}
	
	public boolean isEmpty() {
		if (stack == null) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		if (stack == null) return "()";
		else return stack.toString();
	}
	
}
