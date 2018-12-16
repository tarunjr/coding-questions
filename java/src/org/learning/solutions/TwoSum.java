package org.learning.solutions;

import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
      
        int[] results = {-1,-1};
        
        List<Integer> listNums = new ArrayList<Integer>();
        int start = 0;
        int end = nums.length-1;
        for(int i=0; i<=end;i++)
            listNums.add(nums[i]);
            
        Collections.sort(listNums);
        
        
        for(int i=0; i<= end;i++ ){
            int first = listNums.get(i);
            int second = target-first;
            int index = binarySearchIndex(listNums, i+1, end, second);
            // second is present in the nums 
            if(index != -1){
                results[0] = i;
                results[1] = index;
                break;
            }
        }
        return results;
    }
    private List<Integer> numbers = new ArrayList<Integer>();
    private Set<Integer> present = new HashSet<Integer>();
    
    public TwoSum() {
    
    }
    // return the index at which key is located in nums array.
    // returns -1 if key is not present.
    private int binarySearchIndex(List<Integer> nums, int start, int end, int key){
        if(start > end)
            return -1;
            
        int mid = (start+end)/2;
        if(nums.get(mid) == key)
            return mid;
        else if (key > nums.get(mid))
            start = mid+1;
        else
            end = mid-1;
        return binarySearchIndex(nums, start, end, key);
    }
	public void add(int number) {
		int index = findInsertionPoint(numbers, 0, numbers.size()-1, number);
		numbers.add(index, number);
	}
	private int findInsertionPoint(List<Integer> numbers, int start, int end, int number) {
		while( start <= end) {
			int mid = start + (end-start) / 2;
			int pivot = numbers.get(mid);
			if (pivot == number) 
				return mid;
			else if (pivot < number) {
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		return start;
	}
	public boolean find(int value) {
		if (present.contains(value)) 
			return true;
		
		int i = 0;
		int j = numbers.size()-1;
		while (i < j) {
			int sum = numbers.get(i) + numbers.get(j);
			present.add(sum);
			if (sum == value) {
				return true;
			} else if (value < sum) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}
}