package org.learning.solutions;


public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1,-1};
        binarySearch(nums, 0, nums.length, target, result);
        return result;
    }
    private void binarySearch(int[] nums, int start, int end, int target, int[] result) {
        if(start > end) {
            return;
        }
        int mid = start + (end-start)/2;
        if (nums[mid] == target) {
        	int i = start;
            while( i >= start && nums[i] == target)
                i--;
            
            result[0] = i+1;
            int j = start;
            while( j <= end && nums[j] == target)
                j++;
            result[1] = j-1;
        }
        if( target <= nums[mid]) {
            binarySearch(nums, start, mid, target, nums);
        } else {
            binarySearch(nums, mid+1, end, target, nums);
        }
    }
}