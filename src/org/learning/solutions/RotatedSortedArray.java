package org.learning.solutions;

public class RotatedSortedArray {
    private class Indexer {
        private int length;
        private int minIndex;
        public Indexer(int lenght, int minIndex) {
            this.length = lenght;
            this.minIndex = minIndex;
        }
        public int getIndex(int i) {
            return (minIndex + i) % (length);
        }
    }
    public int findMinIndex(int[] nums) {
    	return findMinIndexHelper(nums, 0, nums.length - 1);
    }
    private int findMinIndexHelper(int[] nums, int start, int end) {
        if(start == end) {
            return start;
        }
        int mid = start + (end - start) / 2;
        if(nums[mid] > nums[end])
            return findMinIndexHelper(nums, mid+1, end);
        else
            return findMinIndexHelper(nums, start, mid);
    }
    
    public int search(int[] nums, int target) {
        int minIndex = findMinIndex(nums);
        Indexer indexer = new Indexer(nums.length ,minIndex );
        return binarySearch(nums, 0, nums.length-1, target, indexer);
    }
    private int  binarySearch(int[] nums, int start, int end, int target, Indexer indexer) {
        if(start > end) 
        	return -1;
        
        int mid = start + (end - start) / 2;
        int midActual = indexer.getIndex(mid);
        if(target == nums[midActual]) 
            return midActual;
        else if(target < nums[midActual])
            return binarySearch(nums, start, mid-1, target, indexer);
        else
            return binarySearch(nums, mid+1, end, target, indexer);
    }
}