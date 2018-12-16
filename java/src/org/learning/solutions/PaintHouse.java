package org.learning.solutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* 256. Paint House
 *  https://leetcode.com/problems/paint-house/
 */

public class PaintHouse {
    public int minCost(int[][] costs) {
    	if(costs.length == 0) {
    		return 0;
    	}
    	int colors = costs[0].length;
    	for(int i=1; i < costs.length; i++) {
    		for(int j=0; j < colors; j++) {
    			List<Integer> cost = new ArrayList<Integer>();
    			for(int c: costs[i-1]) {
    				cost.add(c);
    			}
    			cost.remove(j);
    			Integer min = Collections.min(cost);
    			costs[i][j] += min;
    		}
    	}
    	int n = costs.length-1;
    	List<Integer> lastHouseCost = new ArrayList<Integer>();
		for(int c: costs[n]) {
			lastHouseCost.add(c);
		}

		Integer min = Collections.min(lastHouseCost);
    	return min.intValue();
    }
}