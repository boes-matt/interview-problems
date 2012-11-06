package com.boes.interviews;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.Assert;

public class MyTests extends TestCase {

	public void testListEquals() {
		Node list1 = new Node(1, new Node(2, new Node(3)));
		Node list2 = new Node(1, new Node(2, new Node(3)));
		Node list3 = new Node(1, new Node(2));
		
		Assert.assertTrue(list1.equals(list2));
		Assert.assertTrue(!list1.equals(list3));
		Assert.assertTrue(!list3.equals(list1));
		
		System.out.println(list1); // => (1, (2, 3))
		System.out.println(list2); // => (1, (2, 3))
		System.out.println(list3); // => (1, 2)
	}
	
	public void testListOfLists() {
		Node list1 = new Node(1, new Node(2));
		Node list2 = new Node(3, new Node(4));
		Node list3 = new Node(5, new Node(6));
		Node list4 = new Node(7, new Node(8));
		
		Node masterList1 = new Node(list1, new Node(list2, list3));
		Node masterList2 = new Node(masterList1, list4);
		
		System.out.println(masterList1); // => ((1, 2), ((3, 4), (5, 6)))
		System.out.println(masterList2); // => (((1, 2), ((3, 4), (5, 6))), (7, 8))
	}
	
	public void testCons() {
		Node list1 = new Node(1, new Node(2));
		Assert.assertEquals(new Node(0, list1), list1.cons(0));		
		System.out.println(list1.cons(0));
	}
	
	public void testRemove() {
		Node list1 = new Node(1, new Node(2, new Node(3)));
		
		Assert.assertEquals(new Node(1, new Node(3)), list1.remove(1));
		System.out.println(list1.remove(1));
		
		Assert.assertEquals(new Node(2, new Node(3)), list1.remove(0));
		System.out.println(list1.remove(0));

		Assert.assertEquals(new Node(1, new Node(2)), list1.remove(2));
		System.out.println(list1.remove(2));	
	}
	
	
	public void testGetAndFind() {
		Node list1 = new Node(1, new Node(2, new Node(3)));
		int index = 1;
		Node node = list1.get(index);
		Assert.assertTrue(list1.find(node) == index);
	}
	
	public void testGetLast() {
		Node list4 = new Node(4);
		Node list3 = new Node(3, list4);
		Node list2 = new Node(2, list3);
		Node list1 = new Node(1, list2);
		Assert.assertEquals(list4, list1.getLast());
		Assert.assertEquals(list4, list1.getMthToLast(0));
		Assert.assertEquals(list3, list1.getMthToLast(1));
		Assert.assertEquals(list2, list1.getMthToLast(2));
		Assert.assertEquals(list1, list1.getMthToLast(3));
		System.out.println(list1);
		System.out.println(list1.getMthToLast(1));		
	}
	
	public void testAppend() {
		Node list1 = new Node(1, new Node(2));
		list1 = list1.append(new Node(3, new Node(4)));
		Assert.assertEquals(new Node(1, new Node(2, new Node(3, new Node(4)))), list1);
	}
	
	public void testReverse() {
		Node list1 = new Node(1, new Node(2, new Node(3)));
		Node reversed = new Node(3, new Node(2, new Node(1)));
		
		System.out.println(list1.reverse());
		Assert.assertEquals(reversed, list1.reverse());

		System.out.println(list1.reverse2());
		Assert.assertEquals(reversed, list1.reverse2());
		
		System.out.println(list1.reverse3());
		Assert.assertEquals(reversed, list1.reverse3());

		list1 = list1.reverse4(); // Destructive!  Must reassign.
		System.out.println(list1);
		Assert.assertEquals(reversed, list1);		
	}
	
	public void testStack() {
		Stack stack = new Stack();
		stack.push(3);
		stack.push(2);
		stack.push(1);
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack);
	}
	
	public void testTree() {
		NodeT bst = new NodeT(5, new NodeT(3, new NodeT(1), new NodeT(4)), new NodeT(10, new NodeT(7), new NodeT(12)));
		System.out.println(bst.toStringInOrder()); // => 1, 3, 4, 5, 7, 10, 12
		System.out.println(bst.toStringPreOrder()); // => 5, 3, 1, 4, 10, 7, 12
		System.out.println(bst.toStringPostOrder()); // => 5, 10, 12, 7, 3, 4, 1
		
		bst = bst.insert(6);
		System.out.println(bst.toStringInOrder()); // => 1, 3, 4, 5, 6, 7, 10, 12
		
		System.out.println(bst.findBST(3).toStringInOrder()); // => 1, 3, 4
	}
	
	public void testBinarySearch() {
		int[] nums = {1, 3, 5, 6, 7, 12}; // must be sorted
		// Should print the indices 0, 1, 2, 3, 4, 5
		for (int i = 0; i < nums.length; ++i) {
			System.out.println(search(nums, nums[i]));			
		}
		System.out.println(search(nums, 10)); // => -1
	}
	
	private int search(int[] nums, int n) {
		return search(nums, n, 0, nums.length-1);
		//return search2(nums, n, 0, nums.length-1);
	}
	
	private int search(int[] nums, int n, int start, int end) {
		// binary search returns index of n if exists, otherwise -1
		int center = (end - start) / 2 + start;
		if (nums[center] == n) return center;
		else if (start >= end) return -1;
		else if (n > nums[center]) return search(nums, n, center+1, end);
		else return search(nums, n, start, center-1);
	}
	
	private int search2(int[] nums, int n, int start, int end) {
		// iterative version of binary search
		int center = (end - start) / 2 + start;
		while (nums[center] != n) {
			if (start >= end) return -1;
			
			if (n > nums[center]) start = center + 1;
			else end = center - 1;
			center = (end - start) / 2 + start;
		}
		return center;
	}
		
	private String reverse(String word) {
		char[] c = word.toCharArray();
		for (int i = 0; i < c.length/2; ++i) {
			char tmp = c[i];
			c[i] = c[c.length-1-i];
			c[c.length-1-i] = tmp;
		}
		return new String(c);
	}
	
	public List<String> permute(String word) {
		// Running time is O(n!), which is > O(2^n)
		List<String> words = new ArrayList<String>();
		if (word.equals("")) words.add("");
		
		for (int i = 0; i < word.length(); ++i) {
			String first = word.substring(i, i+1);
			List<String> rest = permute(word.replaceFirst(first, ""));
			for (String w : rest) words.add(first+w);
		}
		
		return words;
	}
	
	public void testPermutations() {
		System.out.println(permute(""));
		System.out.println(permute("a"));
		System.out.println(permute("ab"));
		System.out.println(permute("abc"));
		System.out.println(permute("abcd"));
		
		System.out.println(permute("aaa"));
		
		// System.out.println(permute("abcdefghijklm")); // => O(n!), n = 13, n! > 6 billion, should crash
	}
	
	public List<String> powerset2(String word) {
		// Returns power set of word
		List<String> words = new ArrayList<String>();
		if (word.equals("")) {
			words.add(word);
		} else {
			String first = word.substring(0, 1);
			List<String> rest = powerset2(word.substring(1));
			words.addAll(rest);
			for (String w : rest) words.add(first+w);
		}
		return words;
	}
	
	public List<String> combinations(String word, int k) {
		// Choose k
		if (k == 0) {
			List<String> result = new ArrayList<String>();
			result.add("");
			return result;
		}
		
		if (word.equals("")) return new ArrayList<String>();
		
		String first = word.substring(0, 1);
		String rest = word.substring(1);

		List<String> result = new ArrayList<String>();
		List<String> substringsWith = combinations(rest, k-1);	
		for (String s : substringsWith) result.add(first+s);
		
		List<String> substringsWithout = combinations(rest, k);
		result.addAll(substringsWithout);
		return result;
	}
	
	public List<String> powerset(String word) {
		List<String> result = new ArrayList<String>();
		for (int k = 0; k <= word.length(); ++k) result.addAll(combinations(word, k));
		return result;
	}
	
	public void testPowerSet() {
		System.out.println(powerset(""));
		System.out.println(powerset("a"));
		System.out.println(powerset("ab"));
		System.out.println(powerset("abc"));
		System.out.println(powerset("abcd"));
		
		List<String> p1 = powerset("wxyz");
		List<String> p2 = powerset2("wxyz");
		
		System.out.println(p1);
		System.out.println(p2);
	}
	
	public List<String> telephoneWords(String number) {
		List<String> result = new ArrayList<String>();
		if (number.equals("")) {
			result.add("");
			return result;
		}

		List<String> subresult = telephoneWords(number.substring(1));
		String[] mapping = {"0", "1",		// 0, 1
							"ABC", "DEF",	// 2, 3
							"GHI", "JKL",	// 4, 5
							"MNO", "PRS",	// 6, 7
							"TUV", "WXY"};  // 8, 9
		
		int digit = Integer.valueOf(number.substring(0, 1));
		String letters = mapping[digit];
		for (int i = 0; i < letters.length(); ++i) {
			for (String s : subresult) result.add(letters.substring(i, i+1)+s);
		}
		return result;
	}
	
	public void testTelephoneWords() {
		System.out.println(telephoneWords("123"));
		System.out.println(telephoneWords("8939445"));		
	}
		
}
