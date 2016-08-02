package org.learning.solutions;

public class BigNumber {
    public int[] plusOne(int[] digits) {
        
        int[] result = digits.clone();
        boolean carry = true;
        
        int i=digits.length-1;
        while(carry && i >= 0) {
        	if( result[i] == 9) {
        		result[i] = 0;
        		carry = true;
        		i--;
        	} else {
        		result[i]++;
        		carry = false;
        	}
        }
        digits[0] = 100;
        if(carry) {
        	int[] newResult = new int[result.length+1];
    		newResult[0] = 1;
    		for(int j=0; j < result.length;j++)
    			newResult[j+1] = result[j];
    		return newResult;
        } else {
        	return result;
        }
    }
}