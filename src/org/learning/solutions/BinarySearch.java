package org.learning.solutions;

public class BinarySearch {
	public int search(int[] nums, int target) {
		return searchHelper(nums, 0, nums.length-1, target);
	}
	public int searchRange(int[] nums, int target){
		return -1;
	}
	private int searchHelper(int[] nums, int start, int end, int target) {
		if(start > end) {
			return -1;
		}
		int mid = start + (end-start) / 2;
		if (nums[mid] == target) {
			if(mid > 0 && nums[mid-1] == target)
				return searchHelper(nums,start, mid-1, target);
			else
				return mid;
		}
		else if (target < nums[mid]) {
			return searchHelper(nums, start,mid-1, target);
		} else {
			return searchHelper(nums, mid+1, end, target);
		}
	}
	private int searchRangeHelper(int[] nums, int start, int end, int target) {
		if(start > end) {
			return -1;
		}
		int mid = start + (end-start) / 2;
		if (nums[mid] == target) {
			return mid;
		}
		else if (target < nums[mid]) {
			return searchHelper(nums, start,mid-1, target);
		} else {
			return searchHelper(nums, mid+1, end, target);
		}
	}
}
