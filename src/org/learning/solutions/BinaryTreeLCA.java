package org.learning.solutions;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeLCA {
	private class Node{
		public int value;
		public Node left;
		public Node right;
		
		public int depth;
		Map<Integer, Node> kAncestors;
		public Node(int value){
			this.value = value;
			left = right = null;
			kAncestors = new HashMap<Integer, Node>();
			depth = -1;
		}
	}
	private Node root;
	public BinaryTreeLCA(){
		this.root = null;
	}
	public void add(int[] values){
		for(int value: values)
			this.root = insert(this.root, value);
		preprocess(this.root);
	}
	private Node insert(Node root, int value){
		if(root == null)
			return new Node(value);
		
		if(value < root.value)
			root = insert(root.left, value);
		else
			root = insert(root.right, value);
		return root;
	}
	public void preprocess(Node root){
		if(root == null)
			return;
		if(root.left != null){
			root.left.depth = root.depth+1;
			buildAncesstorLinks(root.left,root);
			preprocess(root.left);
		}
		if(root.right != null){
			root.right.depth = root.depth+1;
			buildAncesstorLinks(root.right,root);
			preprocess(root.right);
		}	
		return;
	}
	private void buildAncesstorLinks(Node n, Node parent){
		root.left.kAncestors.put(0,parent);
		int k=1;
		while(root.kAncestors.get(k-1) != null){
			root.kAncestors.put(k, root.kAncestors.get(k-1).kAncestors.get(k-1));
			k++;
		}
	}
	public int findLCA(int x, int y) throws IllegalArgumentException{
		Node p = find(this.root, x);
		Node q = find(this.root, y);
		if(p == null || q == null)
			throw new IllegalArgumentException();
		
		if(p.depth == q.depth){
			int k=0;
			while(k < 5){
			if(p.kAncestors.get(k) == q.kAncestors.get(k))
				return  p.kAncestors.get(0).value;
			else
				k++;
			}
		} else if (p.depth > q.depth){
			p = p.kAncestors.get(0);
		}
		return -1;
	}
	private Node find(Node root, int value){
		if(root == null)
			return null;
		if(root.value == value)
			return root;
		
		if(value < root.value)
			return find(root.left, value);
		else
			return find(root.right, value);
	}
}
