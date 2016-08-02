package org.learning.solutions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import org.learning.datastructure.TreeNode;

public class HouseRobber {
    public static int robLinear(int[] nums) {
        if (nums.length == 0)
            return 0;
         
        // looted[i] holds the max loot which ends with looting i
        // preMaxLooted contains the max of looted[j] where 0 < j < i-1 
        int[] looted = new int[nums.length];
        looted[0] = nums[0];
        int maxLooted = nums[0];
        int predMaxLooted = 0;
        
        for(int i=1; i < nums.length; i++) {
           looted[i] = nums[i] + predMaxLooted;
           maxLooted = Math.max(maxLooted,looted[i]);
           predMaxLooted = Math.max(predMaxLooted, looted[i-1]);
        }
        return maxLooted;
    }
    public static int robCircular(int[] nums) {
       
        return 0;
    }
    
    
    public static int robBinaryTree(TreeNode root) {
        Map<TreeNode, Integer> nodeValue = new HashMap<TreeNode, Integer>();
        treeValue(root, nodeValue);
        
        int maxLoot = 0;
        for(Entry<TreeNode, Integer> entry: nodeValue.entrySet()) {
        	maxLoot = Math.max(entry.getValue(),maxLoot);
        }
        return maxLoot;
    }
    private static void treeValue(TreeNode root, Map<TreeNode,Integer> nodeValue) {
    	if(root == null) {
    		return;
    	}
    	if(root.left == null && root.right == null) {
    		nodeValue.put(root, root.val);
    		return;
    	}
    	treeValue(root.left, nodeValue);
    	treeValue(root.right, nodeValue);
    	int maxValue = maxSubTree(root, nodeValue);
    	nodeValue.put(root,  maxValue);
    	return;
    }
    private static int maxSubTree(TreeNode root, Map<TreeNode,Integer> nodeValue) {
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	if(root.left != null) {
			queue.add(root.left.left);
			queue.add(root.left.right);
		}
		if(root.right != null) {
			queue.add(root.right.left);
			queue.add(root.right.right);
		}
		int maxValue = 0;
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.remove();
    		if(node == null)
    			continue;
    		Integer value = nodeValue.get(node);
    		if(value !=  null && value.intValue() > maxValue) {
    			maxValue = value.intValue();
    		}
    		queue.add(node.left);
    		queue.add(node.right);
    	}
    	return root.val + maxValue;
    }
}