package org.learning.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.learning.datastructure.NestedInteger;

public class DepthSumInverse {
	public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<NestedInteger>();
        Deque<List<Integer>> nodeValuesStack = new ArrayDeque<List<Integer>>();
        q.addAll(nestedList);
        
        
        while(!q.isEmpty()) {
             Queue<NestedInteger> p = new LinkedList<NestedInteger>();
             List<Integer> values = new ArrayList<Integer>();
             
             while(!q.isEmpty()) {
                 NestedInteger n = q.remove();
                
                 if(n.isInteger()) {
                     values.add(n.getInteger());
                 } else {
                     p.addAll(n.getList());
                 }
             }
             nodeValuesStack.addFirst(values);
             q.addAll(p);
        }
        int level = 1;
        int sum = 0;
        while(!nodeValuesStack.isEmpty()) {
            List<Integer> values = nodeValuesStack.removeFirst();
            for(int val: values) {
                sum += val * level;
            }
            level++;
        }
        return sum;
    }
	public int depthSumInverse2(List<NestedInteger> nestedList) {
        Deque<List<Integer>> nodeValuesStack = new ArrayDeque<List<Integer>>();
        List<Integer> nodeSums = new ArrayList<Integer>();
        depthFirstTraversal(nestedList, nodeValuesStack, nodeSums);
        
        int totalSum = 0;
        for(int s: nodeSums)
        	totalSum += s;
        return totalSum;
    }
    private int depthFirstTraversal(List<NestedInteger> nestedList, Deque<List<Integer>> nodeValuesStack, List<Integer> nodeSums) {
        List<Integer> v = new ArrayList<Integer>();
        List<List<NestedInteger>> children = new ArrayList<List<NestedInteger>>();
        
        for(NestedInteger n: nestedList) {
            if(n.isInteger())
                v.add(n.getInteger());
            else
                children.add(n.getList());
        }
        nodeValuesStack.addFirst(v);
       
        int nodeLevel = 1;
        for(List<NestedInteger> l: children) {
        	nodeLevel = depthFirstTraversal(l,nodeValuesStack, nodeSums); 
        }
        
        List<Integer> nodeValues = nodeValuesStack.removeFirst();
        int nodeSum = 0;
        for(Integer value: nodeValues ) {
        	nodeSum += value  * nodeLevel;
        }
        nodeSums.add(nodeSum);
        
        return ++nodeLevel;
    }
}