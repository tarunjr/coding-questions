package org.learning.solutions;

import org.learning.datastructure.TreeNode;

/*
 * https://leetcode.com/problems/binary-tree-upside-down/
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
 * flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 */
public class BinaryTreeUpsideDown {
	public static TreeNode  upsideDownBinaryTree(TreeNode root) {
		if(root == null || root.left == null) {
			return root;
		}
		TreeNode curr_left = root.left;
		TreeNode curr_right = root.right;
		TreeNode newRoot = upsideDownBinaryTree(root.left);
		
		curr_left.left = curr_right;
		curr_left.right = root;
		root.left = null;
		root.right = null;
			
		return newRoot;
		
	}
}
