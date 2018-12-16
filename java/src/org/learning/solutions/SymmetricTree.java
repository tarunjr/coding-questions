package org.learning.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.learning.datastructure.TreeNode;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
            
        List<Integer> inOrder = new ArrayList<Integer>();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
      
        TreeNode n = root;
        
        while(true) {
        	while (n != null) {
                stack.addFirst(n);
                n = n.left;
            }
            if(stack.isEmpty())
            	break;
        	n = stack.removeFirst();
            inOrder.add(n.val);
            n = n.right;      
    	}
        int len = inOrder.size();
        int i=0; 
        int j= len-1;
        while ( i < j) {
            if (inOrder.get(i) != inOrder.get(j))
                break;
            i++;
            j--;
        }
        return i == j;
    }
}