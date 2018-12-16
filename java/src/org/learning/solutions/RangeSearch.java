package org.learning.solutions;


public class RangeSearch {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1,-1};
        binarySearchItertative(nums, 0, nums.length-1, target, result);
        return result;
    }
    private void binarySearchRecursive(int[] nums, int start, int end, int target, int[] result) {
        if(start > end) {
            return;
        }
        int mid = start + (end-start)/2;
        if (nums[mid] == target) {
        	int i = mid;
            while (i >= 0 && nums[i] == target) {
            	result[0] = i;
            	i--;
            }
            int j = mid;
            while (j < nums.length && nums[j] == target) {
            	result[1] = j;
            	j++;
            }
            return;
        } 
        else if ( target < nums[mid]) {
        	binarySearchRecursive(nums, start, mid-1, target, result);
        } 
        else if (target > nums[mid]) {
        	binarySearchRecursive(nums, mid+1, end, target, result);
        }
    }
    private void binarySearchItertative(int[] nums, int start, int end, int target, int[] result) {
       
    	while (start <= end) {
    		int mid = start + (end-start)/2;
            if (nums[mid] == target) {
            	int i = mid;
                while (i >= 0 && nums[i] == target) {
                	result[0] = i;
                	i--;
                }
                int j = mid;
                while (j < nums.length && nums[j] == target) {
                	result[1] = j;
                	j++;
                }
                return;
            } 
            else if ( target < nums[mid]) {
                end = mid-1;
            } 
            else if (target > nums[mid]) {
                start = mid+1;
            }
    	}  
    }
}