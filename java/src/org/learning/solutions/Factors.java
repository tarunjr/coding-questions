package org.learning.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factors {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> prefixFactors = new ArrayList<Integer>();
		getFactorsHelper(n, prefixFactors, result, 2);
		return result;
	}
	private void getFactorsHelper(int n, List<Integer> partialFactors, List<List<Integer>> result, int start) {
		if (n <= 1) {
			if (partialFactors.size() > 1) {
				List<Integer> allFactors = new ArrayList<Integer>(partialFactors);
				result.add(allFactors);
			}
			
		} else {
			List<Integer> factors = getFactorsInternal(n, start);
			for (Integer f: factors) {
				partialFactors.add(f);
				getFactorsHelper( n / f, partialFactors, result, f);
				partialFactors.remove(partialFactors.size()-1);
			}
		}	
		return;
	}
	private List<Integer> getFactorsInternal(int n, int start) {
		List<Integer> factors = new ArrayList<Integer>();
		for (int i=start;i <= n; i++) {
			if (n % i == 0)
				factors.add(i);
		}
		return factors;
	}
}
