package org.learning.solutions;


public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        return maxProductHelper(nums, 0, len-1);
    }
    public int maxProductHelper(int[] nums, int start, int end) {
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        else if (start == end) {
            return nums[start];
        }
        int mid = start + (end-start) / 2;
        int leftMax = maxProductHelper(nums, start, mid);
        int rightMax = maxProductHelper(nums, mid+1, end);
        
        int crossMaxLeft = Integer.MIN_VALUE;
        int prod = 1;
        for(int j=mid; j >=start ; j--) {
            prod *= nums[j];
            crossMaxLeft = Math.max(crossMaxLeft, prod);
        }
        prod = 1;
        int crossMaxRight = Integer.MIN_VALUE;
        for(int k=mid+1; k <= end ; k++) {
            prod *= nums[k];
            crossMaxRight = Math.max(crossMaxRight, prod);
        }
        int crossMax = crossMaxLeft + crossMaxRight;
        
        return Math.max(Math.max(leftMax, rightMax) , crossMax);
    }
    public int maxProductNaive(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        else if (len == 1) {
            return nums[0];
        }
        int maxsofar = Integer.MIN_VALUE;
        for (int i=0; i < len; i++) {
            int product = 1;
            for (int j=i; j < len; j++) {
                product *= nums[j];
                maxsofar = Math.max(maxsofar, product);
            }
        }
        return maxsofar;
    }
}