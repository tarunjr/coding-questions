package org.learning.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.learning.datastructure.TreeNode;

public class BinaryTreeLeaf {
    public List<List<Integer>> findLeaves(TreeNode root) {
   
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<TreeNode> visited = new ArrayList<TreeNode>();
        
        do {
        	visited.clear();
        	Map<TreeNode, TreeNode> leftChildParentMap = new HashMap<TreeNode, TreeNode>();
            Map<TreeNode, TreeNode> rightChildParentMap = new HashMap<TreeNode, TreeNode>();
            List<Integer> leafNodes = new ArrayList<Integer>();
            postOrder(root, visited,leftChildParentMap, rightChildParentMap, leafNodes);
            if (!leafNodes.isEmpty())
            	result.add(leafNodes);
        } while (!visited.isEmpty());
        
        return result;
    }
    private void postOrder(TreeNode root,  List<TreeNode> visited, Map<TreeNode, TreeNode> leftChildParentMap, Map<TreeNode, TreeNode> rightChildParentMap, List<Integer> leafNodes) {
        if (root == null)
        	return;
        else if (root.left == null && root.right == null) {
            leafNodes.add(root.val);
            // remove the parent linkage
            TreeNode parent = leftChildParentMap.get(root);
            if (parent != null) {
                parent.left = null;
                leftChildParentMap.remove(root);
            } 
            parent = rightChildParentMap.get(root);
            if (parent != null) {
                parent.right = null;
                rightChildParentMap.remove(root);
            } 
            return;
        }
        if (root.left != null) {
            leftChildParentMap.put(root.left, root);
            postOrder(root.left, visited,leftChildParentMap, rightChildParentMap, leafNodes );
        } 
        if (root.right != null) {
            rightChildParentMap.put(root.right, root);
            postOrder(root.right, visited,leftChildParentMap, rightChildParentMap, leafNodes );
        }
        visited.add(root);
    }
}