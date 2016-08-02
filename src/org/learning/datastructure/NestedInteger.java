package org.learning.datastructure;

import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public class NestedInteger {
	 private Integer value;
	 private boolean isIntegerFlag = false;
	 private List<NestedInteger> elements = null;
     
	 // @return true if this NestedInteger holds a single integer, rather than a nested list.
     public boolean isInteger() {return isIntegerFlag;}

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger() { return value; }

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
     public List<NestedInteger> getList() { return elements;}
     
   
     public NestedInteger(Integer value) {
    	 this.value = value;
    	 this.isIntegerFlag = true;
     }
     public NestedInteger(List<NestedInteger> elements) {
    	 this.elements = elements;
    	 this.isIntegerFlag = false;
     }
}