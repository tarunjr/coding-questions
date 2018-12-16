

package org.learning.solutions;

import java.util.PriorityQueue;

public class MedianFinder{
	public static double find(int[] numbers) {
		if  (numbers.length == 0 ) {
			throw new IllegalArgumentException("empty list");
		} else if (numbers.length == 1 ) {
			return numbers[0];
		}	
		PriorityQueue<Integer> maxLeft = new PriorityQueue<Integer> ();
		PriorityQueue<Integer> minRight = new PriorityQueue<Integer> ();
		
		for(int n: numbers) {
			if(maxLeft.size() == minRight.size()) {
				if(maxLeft.size() == 0) {
					maxLeft.add(-n);
				} else {
					if(n <= -maxLeft.peek()) {
						maxLeft.add(-n);
					} else {
						minRight.add(n);
					}
				}
			}
			else if (maxLeft.size() > minRight.size()) {
				if(n >  -maxLeft.peek()) {
					minRight.add(n);
				} else {
					minRight.add(-maxLeft.poll());
					maxLeft.add(-n);
				}
			} else {
				if(n < minRight.peek()) {
					maxLeft.add(-n);
				} else {
					maxLeft.add(-minRight.poll());
					minRight.add(n);
				}
			}
		}
		double median = 0;
		if(maxLeft.size() == minRight.size()) {
			median = (-maxLeft.poll() + minRight.poll()) / 2.0;
		} else if ( maxLeft.size() > minRight.size() ) {
			median = -maxLeft.poll();
		} else {
			median = minRight.poll();
		}
		return median;
	}
}
