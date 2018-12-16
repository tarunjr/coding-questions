package org.learning.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

import org.learning.datastructure.TreeNode;

public class BSTIterator {
    private Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
    public BSTIterator(TreeNode root) {
        addLeftmostSubtree(root, stack);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.removeFirst();
        addLeftmostSubtree(node.right, stack);
        return node.val;
    }
    private void addLeftmostSubtree(TreeNode node, Deque<TreeNode> stack) {
        while(node != null) {
            stack.addFirst(node);
            node = node.left;
        }
    }
}