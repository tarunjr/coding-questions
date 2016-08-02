package org.learning.solutions;

/*
 * https://leetcode.com/problems/product-of-array-except-self/
 */


public class ArrayProduct {
    public int[] productExceptSelf(int[] nums) {
        if (nums.length == 0)
            return new int[0];
            
        int[] output = new int[nums.length];
        if(nums[0] == 0)
        	output[0] = 0;
        else 
        	output[0] = 1;
        
      
        
        for(int i=1; i < nums.length; i++) {
        	output[i] = output[i-1] * nums[i-1];
        }
        int result = 1;
        for(int j=nums.length-1; j >= 0; j--) {
        	output[j] *= result;
        	result *= nums[j];
        }
        
        return output;
    }
}