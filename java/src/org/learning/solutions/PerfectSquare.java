package org.learning.solutions;


public class PerfectSquare {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;
        
        while ( start <= end) {
            int mid = start + (end - start) / 2;
            long square = mid * mid;
            if (square <= 0) {
            	end = mid-1;
            }
            else if (square == num)
                return true;
            else if  ( square < num ) {
                start = mid + 1;
            }
            else {
                end = mid-1;
            }
        }
        
        return false;
    }
}