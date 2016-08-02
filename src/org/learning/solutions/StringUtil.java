package org.learning.solutions;

public class StringUtil {
	 public int lengthOfLongestSubstringTwoDistinct(String s) {
	        if (s == null || s.length() == 0){
	        	return 0;
	        }
	        else if (s.length() == 1) {
	        	return 1;
	        }
		 	int maxLen = Integer.MIN_VALUE;
	        char first = '#';
	        boolean firstFound = false;
	        char second = '#';
	        boolean secondChar = false;
	        
	        int start = 0;
	        int end = 0;
	        char current;
	        
	        int i= 0;
	        while(i < s.length()){
	        	current = s.charAt(i);
	        	if (firstFound && current == first || 
	        		secondChar && current == second) { 
	        		end = i;
	        		i++;
	        	} else if (!firstFound) {
	        		firstFound = true;
	        		first = current;
	        		end = i;
	        		i++;
	        	} else if (!secondChar) {
	        		secondChar = true;
	        		second = current;
	        		end = i;
	        		i++;
	        	} else {
	        		end  = i-1;
	        		int len = end - start + 1;
	        		if (len > maxLen) {
	        			maxLen = len;
	        		}
	        		i -= 1;
	        		firstFound = true;
	        		first = s.charAt(i);
	        		second = '#';
	        		secondChar = false;
	        		while ( s.charAt(i) == first) {
	        			i -= 1;
	        		}
	        		i++;
	        		start = end = i;
	        		
	        	}
	        }
	        int len = end - start + 1;
    		if (len > maxLen) {
    			maxLen = len;
    		}
	        return maxLen;
	 }
}
